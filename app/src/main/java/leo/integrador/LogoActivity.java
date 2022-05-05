package leo.integrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LogoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        Handler tempo = new Handler();
        tempo.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}