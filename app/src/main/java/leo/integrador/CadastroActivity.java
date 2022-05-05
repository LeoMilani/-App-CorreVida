package leo.integrador;

import android.app.Activity;
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
import android.widget.Toast;

public class CadastroActivity extends Activity {

    private Button botaoVoltar;
    private Button botaoCadastrar;
    private Gerenciador gerenciador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ImageView mimageView = (ImageView) findViewById(R.id.imageView3);
        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.user)).getBitmap();
        Bitmap imageRounded=Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas=new Canvas(imageRounded);
        Paint mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);

        //Conecta ao banco
        gerenciador = new Gerenciador(getApplicationContext());

        //voltar para activity_login
        botaoVoltar = findViewById(R.id.buttonVoltar);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                finish();
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editEmail = findViewById(R.id.editTextUsuario);
                EditText editSenha = findViewById(R.id.editTextSenha);

                String usuario = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

                String proxId = gerenciador.nRegistros()+"";

                //Toast.makeText(getApplicationContext(),proxId,Toast.LENGTH_LONG).show();

                Long status = gerenciador.inserir(proxId,usuario,senha);

                //Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_LONG).show();

                startActivity(new Intent(CadastroActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
}