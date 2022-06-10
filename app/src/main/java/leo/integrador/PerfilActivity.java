package leo.integrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PerfilActivity extends Activity {

    private TextView sobre;
    private TextView historico;
    private TextView politica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // ir para sobre_activity
        sobre = findViewById(R.id.textView6);

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilActivity.this, SobreActivity.class));
            }
        });

        // ir para sobre_activity
        historico = findViewById(R.id.historico);

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilActivity.this, AtividadesActivity.class));
            }
        });

        // ir para politica_activity
        politica = findViewById(R.id.politicaID);

        politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilActivity.this, PoliticaActivity.class));
            }
        });
    }
}