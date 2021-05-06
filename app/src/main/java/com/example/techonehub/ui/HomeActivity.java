package com.example.techonehub.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.techonehub.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onClickAgenda(View view) {
        Intent intent = new Intent(HomeActivity.this, AgendaActivity.class);
        startActivity(intent);
    }

    public void onClickCliente(View view) {
        Intent intent = new Intent(HomeActivity.this, ClienteActivity.class);
        startActivity(intent);
    }

    public void onClickServico(View view) {
        Intent intent = new Intent(HomeActivity.this, ServicoActivity.class);
        startActivity(intent);
    }

    public void onClickSobre(View view) {
        Intent intent = new Intent(HomeActivity.this, SobreActivity.class);
        startActivity(intent);
    }

    public void onClickTelefone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:(11) 99472-9075"));
        startActivity(intent);
    }
}