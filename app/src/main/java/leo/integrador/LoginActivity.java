package leo.integrador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import leo.integrador.CadastroActivity;
import leo.integrador.Gerenciador;
import leo.integrador.HomeActivity;
import leo.integrador.R;

public class LoginActivity extends Activity {

    private TextView cadastro;
    private Button lg;
    private Gerenciador gerenciador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView mimageView = (ImageView) findViewById(R.id.imageView);
        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.user)).getBitmap();
        Bitmap imageRounded=Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas=new Canvas(imageRounded);
        Paint mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);

        //Criar gerenciador
        gerenciador = new Gerenciador(getApplicationContext());

        //Ir para pagina cadastro
        cadastro = findViewById(R.id.textocadastro);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });

        lg = findViewById(R.id.bt);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editEmail = findViewById(R.id.editTextTextPersonName);
                EditText editSenha = findViewById(R.id.editTextTextPassword);

                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

               if(gerenciador.login(email,senha)) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }else{
                   Toast.makeText(getApplicationContext(),"Usuario não encontrado!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}