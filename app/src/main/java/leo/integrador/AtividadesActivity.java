package leo.integrador;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AtividadesActivity extends Activity {

    private WebView wv;
    private Cursor dados;
    private Gerenciador dbm;
    private ListaActivity ld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        wv = findViewById(R.id.webviewAtiv);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(databasePath);

        //wv.loadUrl("https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d5642.605709197461!2d-47.44085210535468!3d-23.504535467620396!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1spt-BR!2sbr!4v1650982492004!5m2!1spt-BR!2sbr");
        wv.loadData("<iframe src=\"https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d7317.554273188379!2d-47.4408521!3d-23.5045355!3m2!1i1024!2i768!4f13.1!5e0!3m2!1spt-BR!2sbr!4v1650982738630!5m2!1spt-BR!2sbr\" style=\"border:0; width:100vw; height:100vh;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>", "text/html", "UTF-8");

        //Listar atividades Feitas
        dbm = new Gerenciador(getApplicationContext());

        atualizarLista();

    }
    public void atualizarLista(){
        // Criando Lista de nomes
        ArrayList<String> lAtividade = new ArrayList<>();
        dados = dbm.listarAtividades();
        DecimalFormat df = new DecimalFormat("0.00");

        while(!dados.isLast()) {
            String atividade = "Data: "+dados.getString(1);

                   Float distancia = Float.parseFloat(dados.getString(2));

                   atividade+= "\nKM: "+df.format(distancia);

                   Float tempo = (Float.parseFloat(dados.getString(3)) / 1000)/60;
                   atividade+= " | Tempo: "+df.format(tempo)+"Min";

                   Float velocidade = distancia/tempo;
                   Float calorias = 80/velocidade;
                   atividade+= " | Calorias: "+df.format(calorias)+"Kcal";

            lAtividade.add(atividade);
            dados.moveToNext();
        }

        //Atualizando RecyclerView
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ld = new ListaActivity(this, lAtividade);
        ld.setClickListener(this::onItemClick);
        recyclerView.setAdapter(ld);
    }

    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Você clicou em: " + ld.getItem(position) + " N: " + position, Toast.LENGTH_SHORT).show();

        //Float distancia = Float.parseFloat(dados.getString(2));

        //Float tempo = (Float.parseFloat(dados.getString(3)) / 1000)/60;

        //Float velocidade = distancia/tempo;

        //Float calorias = 80/velocidade;


    }
}