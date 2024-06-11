package iesb.app.sqliteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import iesb.app.sqliteapp.dao.ClienteDAO;
import iesb.app.sqliteapp.model.ClienteVO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.tela_inicial);

    }

    public void btnOnclickIniciarCadastro(View view){
        setContentView(R.layout.activity_main);

    }
    public void btnOnclickVoltarTelaInicial(View view){
        setContentView(R.layout.tela_inicial);

    }

    public void btnOnclickTelaAtualizar(View view){
        setContentView(R.layout.atualizar);

    }

    public void btnOnclickTelaDeletar(View view){
        setContentView(R.layout.deletar);

    }

    public void btnOnclickAtualizarUsuario(View view){

        EditText txtNome = (EditText) findViewById(R.id.NomeAtualizar);
        String Nome = txtNome.getText().toString();

        EditText txtEmail = (EditText) findViewById(R.id.EmailAtualizar);
        String Email = txtEmail.getText().toString();

        ClienteDAO db = new ClienteDAO(this);
        ClienteVO vo = new ClienteVO(Nome, Email);

        //  db.updateCliente(new ClienteVO(Nome, Email));

        db.updateCliente(vo);

        Log.d("Update:","Atualizando clientes");
    }

    public void btnOnclickDeletarUsuario(View view){

        EditText txtNome = (EditText) findViewById(R.id.NomeAtualizar);
        String Nome = txtNome.getText().toString();

        EditText txtEmail = (EditText) findViewById(R.id.EmailAtualizar);
        String Email = txtEmail.getText().toString();

        ClienteDAO db = new ClienteDAO(this);
        ClienteVO vo = new ClienteVO(Nome, Email);

        //  db.deleteCliente(new ClienteVO(Nome, Email));

        db.deleteCliente(vo);

        Log.d("Delete:","Deletando clientes");
    }

    public void btnOnClickCadastrarCliente(View view){

        EditText txtNome = (EditText) findViewById(R.id.Nome);
        String Nome = txtNome.getText().toString();

        EditText txtEmail = (EditText) findViewById(R.id.email);
        String Email = txtEmail.getText().toString();

        ClienteDAO db = new ClienteDAO(this);
        ClienteVO vo = new ClienteVO(Nome, Email);


        db.addCliente(new ClienteVO(Nome, Email));

        db.addCliente(vo);

        Log.d("Insert:","Inserindo clientes");
    }

    public void btnOnclickListarUsuarios(View view){

        ClienteDAO db = new ClienteDAO(this);

        List<ClienteVO> clientes = db.getAllClientes();

        TextView lista = (TextView) findViewById(R.id.Nome);

        for (ClienteVO cliente: clientes){
            lista.setText(lista.getText() +
                    "Nome: " + cliente.getNome() +
                    " , Email: " + cliente.getEmail() +
                    "\n"
            );
        }

        db.close();
    }
}
