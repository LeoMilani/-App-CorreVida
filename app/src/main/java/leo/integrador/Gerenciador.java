package leo.integrador;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Gerenciador {
    private BancodeDados db;
    private SQLiteDatabase database;
    private Context ctx;

    public Gerenciador(Context context){
        db = new BancodeDados(context);
        database = db.getWritableDatabase();
        ctx = context;
    }

    public long inserir(String codigo, String usuario, String senha){
        ContentValues values = new ContentValues();
        values.put("cod", codigo);
        values.put("usuario", usuario);
        values.put("senha", senha);

        return database.insert("usuario", null, values);
    }

    public long inserirHistorico(String codigo, String data, String km,String tempo){
        ContentValues values = new ContentValues();
        values.put("cod", codigo);
        values.put("data", data);
        values.put("tempo", tempo);
        values.put("km", km);

        return database.insert("historico", null, values);
    }

    public boolean login(String U, String S) {
        Cursor mCursor = database.rawQuery("SELECT * FROM usuario WHERE usuario = '"+U+"' AND senha = '"+S+"' ",null);
        //Toast.makeText(ctx,"Total="+mCursor.getCount(),Toast.LENGTH_LONG).show();
        if(mCursor.getCount() >= 1){
            return true;
        }else {
            return false;
        }
    }

    public Cursor listarHome() { //selectRecords()
        Cursor mCursor = database.rawQuery("SELECT * FROM historico ORDER BY cod DESC LIMIT 1",null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor listarAtividades() { //selectRecords()
        Cursor mCursor = database.rawQuery("SELECT * FROM historico ORDER BY cod DESC",null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public int nRegistros() {
        Cursor mCursor = database.rawQuery("SELECT * FROM usuario",null);
        //Toast.makeText(ctx,"Total de Registros="+mCursor.getCount(),Toast.LENGTH_LONG).show();
        return mCursor.getCount();
    }
}
