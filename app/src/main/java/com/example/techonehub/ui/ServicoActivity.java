package com.example.techonehub.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.techonehub.R;
import com.example.techonehub.ui.cadastrar.CadastrarServicoActivity;
import com.example.techonehub.ui.consultar.ConsultarServicoActivity;

public class ServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

    }

    public void onClickVoltar(View view) {
        finish();
    }

    public void onClickTelefone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:(11) 99472-9075"));
        startActivity(intent);
    }

    public void onClickCadastrarServico(View view) {
        Intent intent = new Intent(ServicoActivity.this, CadastrarServicoActivity.class);
        startActivity(intent);
    }

    public void onClickConsultarServico(View view) {
        Intent intent = new Intent(ServicoActivity.this, ConsultarServicoActivity.class);
        startActivity(intent);
    }
}