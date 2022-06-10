package leo.integrador;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.Date;
import java.util.Locale;


public class CorridaActivity extends Activity {

    private ImageView calendar;
    private ImageView home;
    private ImageView profile;
    private ImageView explorer;
    private Button start;
    private Button stop;
    private Button restart;
    private WebView wv;
    private Localizacao localizacao;
    private Gerenciador dbm;

    //CONDOMETRO
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    //LOCALIZACAO


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrida);

        dbm = new Gerenciador(getApplicationContext());

        wv = findViewById(R.id.webView);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(databasePath);

        wv.loadUrl("https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d5642.605709197461!2d-47.44085210535468!3d-23.504535467620396!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1spt-BR!2sbr!4v1650982492004!5m2!1spt-BR!2sbr");
        wv.loadData("<iframe src=\"https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d7317.554273188379!2d-47.4408521!3d-23.5045355!3m2!1i1024!2i768!4f13.1!5e0!3m2!1spt-BR!2sbr!4v1650982738630!5m2!1spt-BR!2sbr\" style=\"border:0; width:100vw; height:100vh;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>", "text/html", "UTF-8");

        // ir para Home_activity
        home = findViewById(R.id.imageView17);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorridaActivity.this, HomeActivity.class));
            }
        });

        // ir para Atividades_activity
        calendar = findViewById(R.id.imageView18);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorridaActivity.this, AtividadesActivity.class));
            }
        });

        // ir para Home_activity
        explorer = findViewById(R.id.imageView22);

        explorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorridaActivity.this, ExplorarActivity.class));
            }
        });

        // ir para Perfil_activity
        profile = findViewById(R.id.imageView23);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorridaActivity.this, PerfilActivity.class));
            }
        });

        // ir para Perfil_activity
        start = findViewById(R.id.bt01);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });

        // ir para Perfil_activity
        stop = findViewById(R.id.bt02);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
                Date data = new Date();
                String dataTexto = formataData.format(data);

                Log.i("teste","Data:"+dataTexto);
                Log.i("teste","Tempo:"+seconds);

                dbm.inserirHistorico(dataTexto,"1",seconds+"");
            }
        });

        // ir para Perfil_activity
        restart = findViewById(R.id.bt03);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });

        // LOCALIZACAO


        //CRONOMETRO
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){

        // Get the text view.
        final TextView timeView
                = (TextView)findViewById(
                R.id.time_view);

        // Creates a new Handler
        final Handler handler
                = new Handler();

        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timeView.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}