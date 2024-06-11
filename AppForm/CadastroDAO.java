package iesb.app.sqliteapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import iesb.app.sqliteapp.model.ClienteVO;

public class ClienteDAO extends SQLiteOpenHelper {

    //DEFINIÇAO DO BD
    private static final int DATABASE_VERSION = 1;
    private static final String DATABA_NAME = "LOJA_DB";

    //DEFINIÇAO DAS TABELAS

    private static final String TB_CLIENTES = " tb_clientes ";

    private static final String KEY_ID = " id ";
    private static final String NOME = " nome ";
    private static final String EMAIL = " email ";
    
    public ClienteDAO(Context context){
        super(context, DATABA_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TB_CLIENTES = "CREATE TABLE " + TB_CLIENTES + " (" + KEY_ID + "INTEGER PRIMARY KEY," + NOME + " TEXT, " + EMAIL + " TEXT )";
        db.execSQL(CREATE_TB_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CLIENTES);
        onCreate(db);
    }

    public void addCliente(ClienteVO clienteVO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NOME, clienteVO.getNome());
        contentValues.put(EMAIL, clienteVO.getNome());

        //sql: INSERT INTO TB_CLIENTS (id, nome, email) VALUES (?, '',"")
        db.insert(TB_CLIENTES, null, contentValues);
        db.close();
    }

    public int getCountClientes(){
        int count = 0;

        // SQL: SELECT COUNT (*) FROM TB_CLIENTS;
        String countQuerySQL = "SELECT COUNT(*) FROM " + TB_CLIENTES;

        // ou: this.getWritableDatabase()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuerySQL, null);
        count = cursor.getCount();

        // Desalocar os recursos e liberar a Thread de processamento:
        cursor.close();
        db.close();

        return  count;
    }

    public List<ClienteVO> getAllClientes(){
        List<ClienteVO> ltClientes = new ArrayList<ClienteVO>();
        String selectQuery = "SELECT * FROM " + TB_CLIENTES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Colocar cursor na primeira linha e iterá-lo para preencherr a lista de clientes:
        if(cursor != null){
            // Questão de segurança. não ocorrer NoPointer
            if(cursor.moveToFirst()){
                do {
                    ClienteVO clienteVO = new ClienteVO();

                    clienteVO.setId(Integer.parseInt(cursor.getString(0)));
                    clienteVO.setNome(cursor.getString(1));
                    clienteVO.setEmail(cursor.getString(2));

                    ltClientes.add(clienteVO);
                }while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();
        return ltClientes;
    }

    public ClienteVO getCliente(int id){

        SQLiteDatabase db = this.getReadableDatabase() ;
        ClienteVO clienteVO = new ClienteVO();

        // SQL: SELECT NOME, EMAIL FROM TB_CLIENTE WHERE ID = ?:
        Cursor cursor = db.query(TB_CLIENTES,
                new String[]{KEY_ID, NOME, EMAIL},
                KEY_ID + " = ? ", // Cláusula WHERE
                new String[]{String.valueOf(id)},
                null, null, null);

        if(cursor != null){
            cursor.moveToFirst() ;

            clienteVO.setId(Integer.parseInt(cursor.getString(0)));
            clienteVO.setNome(cursor.getString(1));
            clienteVO.setEmail(cursor.getString(2));

        }

        cursor.close();
        db.close();

        return clienteVO;
    }

    public int updateCliente(ClienteVO clienteVO){

        int qtdRegistrosAtualizados = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL, clienteVO.getNome());

        //  SQL: UPDATE TB_CLIENTES SET EMAIL = "novo@email.com" WHERE ID = ?:
        qtdRegistrosAtualizados = db.update(TB_CLIENTES, contentValues,
                KEY_ID + " = ? ", new String[]{String.valueOf(clienteVO.getId())});

        db.close();

        return qtdRegistrosAtualizados;
    }

    // Uso do ClienteVO deixa os métodos mais ABSTRATOS
    public void deleteCliente(ClienteVO clienteVO){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TB_CLIENTES, KEY_ID + " = ? ",
                new String[]{String.valueOf(clienteVO.getId())});

        db.close();
    }
}
