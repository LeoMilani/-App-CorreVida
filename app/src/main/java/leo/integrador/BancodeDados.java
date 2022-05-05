package leo.integrador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BancodeDados extends SQLiteOpenHelper {

    private static final int versao = 2;
    private static final String nomebd = "chat";

    public BancodeDados(@Nullable Context context) {
        super(context, nomebd, null, versao);
    }

    public BancodeDados(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (cod INTEGER PRIMARY KEY,usuario TEXT NOT NULL,senha TEXT NOT NULL);");
        db.execSQL("CREATE TABLE historico (cod INTEGER PRIMARY KEY,data TEXT NOT NULL,km TEXT NOT NULL, tempo TEXT NOT NULL);");
        Log.i("Aviso","Banco Criado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS historico");
        onCreate(db);
    }
}
