package leo.integrador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends Activity {
    private Gerenciador dbm;
    private ImageView calendar;
    private ImageView explorer;
    private ImageView profile;
    private ImageView profile2;
    private Button runner;
    private WebView wv;
    private ConstraintLayout wv2;
    private TextView historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        wv = findViewById(R.id.webviewHome);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(databasePath);

        //wv.loadUrl("https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d5642.605709197461!2d-47.44085210535468!3d-23.504535467620396!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1spt-BR!2sbr!4v1650982492004!5m2!1spt-BR!2sbr");
        wv.loadData("<iframe src=\"https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d7317.554273188379!2d-47.4408521!3d-23.5045355!3m2!1i1024!2i768!4f13.1!5e0!3m2!1spt-BR!2sbr!4v1650982738630!5m2!1spt-BR!2sbr\" style=\"border:0; width:100vw; height:100vh;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>", "text/html", "UTF-8");


        // ir para Atividades_activity
        calendar = findViewById(R.id.imageView12);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AtividadesActivity.class));
            }
        });

        // ir para Explorar_activity
        explorer = findViewById(R.id.imageView13);

        explorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ExplorarActivity.class));
            }
        });

        // ir para Perfil_activity
        profile = findViewById(R.id.imageView14);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PerfilActivity.class));
            }
        });

        // ir para Corrida_activity
        profile2 = findViewById(R.id.imageView16);

        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PerfilActivity.class));
            }
        });

        // ir para Corrida_activity
        runner = findViewById(R.id.bt2);

        runner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CorridaActivity.class));
            }
        });

        // ir para Atividades_activity
        historico = findViewById(R.id.textView);

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AtividadesActivity.class));
            }
        });

        // ir para Atividades_activity
        wv2 = findViewById(R.id.Descricao);

        wv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AtividadesActivity.class));
            }
        });

        // Painel ultima atividade
        dbm = new Gerenciador(getApplicationContext());

        //dbm.inserirHistorico("1","06/05/2022","1","60000");

        Cursor dados = dbm.listarHome();

        TextView tvKm = findViewById(R.id.textViewKm);
        TextView tvTempo = findViewById(R.id.textViewTempo);
        TextView tvCal = findViewById(R.id.textViewCalorias);

        Float distancia = Float.parseFloat(dados.getString(2));
        tvKm.setText(distancia.toString()+"Km");

        Float tempo = (Float.parseFloat(dados.getString(3)) / 1000)/60;
        tvTempo.setText(tempo.toString()+"Min");

        Float velocidade = distancia/tempo;

        Float calorias = 80/velocidade;
        tvCal.setText(calorias.toString());

    }
}