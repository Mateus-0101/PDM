package com.example.appform;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        EditText nomeEditText = (EditText) findViewById(R.id.nome);
        String nomeCompleto = nomeEditText.getText().toString();

        //  Popular VO com dados:
        CadastroVO vo;
        Cadastro cadastro = new Cadastro();
        vo = cadastro.efetuaCadastro(1,"701", "Mateus Adolfo", "Homi", "22", "c 2", "6157");

        //  Simula insert em um Banco de Dados
        log.i("info", vo.getNomeCompelto());
    }
}
