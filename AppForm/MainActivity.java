package com.example.appform;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import iesb.app.CadastroVO;
import iesb.app.Cadastro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void loadCadastroLayout(View view){

        EditText nomeEditText = (EditText) findViewById(R.id.nomeCompleto);
        String nomeCompleto = nomeEditText.getText().toString();

        //  Popular VO com dados:
        CadastroVO vo;
        Cadastro cadastro = new Cadastro();
        vo = new CadastroVO();
        vo.setNomeCompleto(nomeCompleto);
        vo = cadastro.efetuaCadastro(1,"701", "Mateus Adolfo", "Homi", "22", "c 2", "6157");

        //  Simula insert em um Banco de Dados
        Log.i("info", vo.getNomeCompleto());
    }
}

 public void btnOnClickCadastrarCliente(View view){

        EditText txtNome = (EditText) findViewById(R.id.Nome);
        String Nome = txtNome.getText().toString();

        EditText txtEmail = (EditText) findViewById(R.id.email);
        String Email = txtEmail.getText().toString();

        ClienteDA0 db = new ClienteDA0(this);
        ClienteVO vo = new ClienteVO(Nome, Email);


        db.addCliente(new ClienteVO(Nome, Email));

        db.addCliente(vo);

        Log.d("Insert:","Inserindo clientes");
    }
}
