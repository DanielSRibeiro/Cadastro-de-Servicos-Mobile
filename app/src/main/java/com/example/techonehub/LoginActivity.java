package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.techonehub.model.Dto.DtoLogin;
import com.example.techonehub.model.DaoTechOneHub;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(LoginActivity.this);
    EditText editTextLogin, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextSenha = findViewById(R.id.editTextSenha);

        DtoLogin dtoLogin = new DtoLogin("Tech","123456");
        daoTechOneHub.Insert(dtoLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Usuario = editTextLogin.getText().toString();
                String Senha = editTextSenha.getText().toString();

                boolean Scan = daoTechOneHub.Select(Usuario, Senha);

                if(Scan == true){
                    Toast.makeText(LoginActivity.this, "Cadastrado com Sucesso!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Username ou a Senha está incorreto!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}