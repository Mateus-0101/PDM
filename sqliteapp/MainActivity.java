package iesb.app.sqliteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import iesb.app.sqliteapp.dao.DatabaseHandler;
import iesb.app.sqliteapp.model.ClienteVO;

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

    public void btnOnClickCadastrarCliente(View view){
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserindo clientes...");
        db.addCliente(new ClienteVO("Satan"));
        db.addCliente(new ClienteVO("Hades"));
        db.addCliente(new ClienteVO("Lula"));

        //EditText nomeEditText = (EditText) findViewById(R.id.nome);
        //CadastroVO vo = new CadastroVO();
        //vo.setNome(nomeEditText.getText().toString());
        Log.d("Select: ", "Lendo os Clientes...");
        List<ClienteVO> clientes = db.getAllClientes();

        for (ClienteVO cliente : clientes){
            String log = "Id: " + cliente.getId() + ",Nome: " + cliente.getNome();
            Log.d("Nome >> ",log);
        }
    }

}
