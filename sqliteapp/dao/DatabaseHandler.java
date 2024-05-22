package iesb.app.sqliteapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import iesb.app.sqliteapp.model.ClienteVO;

public class DatabaseHandler extends SQLiteOpenHelper {

    //  Definições do Banco de Dados:
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lOJA_DB";

    //  Definições das tabelas: mapeando VO na tabela
    private static final String TB_CLIENTES = "tb_clientes";
    private static final String KEY_ID = "id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_CLIENTS = "CREATE TABLE " + TB_CLIENTES + " ("
                + KEY_ID + "INTEGER PRIMARY KEY,"
                + NOME + "TEXT,"
                + EMAIL + "TEXT )";

        db.execSQL(CREATE_TB_CLIENTS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CLIENTES);
        onCreate(db);
    }

    public void addCliente(ClienteVO clienteVO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); //Vo TEMPORÁRIO

        contentValues.put(NOME, clienteVO.getNome());
        contentValues.put(NOME, clienteVO.getEmail());

        db.insert(TB_CLIENTES, null, contentValues);
        db.close();
    }

    public int getCountClients(){
        int count = 0;

        String countQuery = "SELECT COUNT(*) FROM " + TB_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null); //Ler dado/cliente
        count = cursor.getCount();
        cursor.close(); // Desalocar objeto

        return count;
    }

    public List<ClienteVO> getAllClientes(){
        List<ClienteVO> ltClientes = new ArrayList<ClienteVO>();
        String selectQuery = "SELECT * FROM " + TB_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //  Iterar o cursor e preencher a lista de Clientes:
        if(cursor.moveToFirst()){       // Ver se o cursor está na primeira linha da tabela
            do{
                ClienteVO clienteVO = new ClienteVO(); // Reescrever um novo VO a cada linha/novo cliente
                //  Ler cada coluna, informação do objeto/cliente
                clienteVO.setId(Integer.parseInt(cursor.getString(0)));
                clienteVO.setNome(cursor.getString(1));
                clienteVO.setEmail(cursor.getString(2));

                ltClientes.add(clienteVO);

            } while(cursor.moveToNext());
        }
        
        return ltClientes;
    }
}