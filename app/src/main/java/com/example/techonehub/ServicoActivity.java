package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

    }

    public void onClickVoltar(View view) {
        Intent intent = new Intent(ServicoActivity.this, HomeActivity.class);
        startActivity(intent);
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