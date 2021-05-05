package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

    }

    public void onClickVoltar(View view) {
        Intent intent = new Intent(ClienteActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClickTelefone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:(11) 99472-9075"));
        startActivity(intent);
    }

    public void onClickConsultarCliente(View view) {
        Intent intent = new Intent(ClienteActivity.this, ConsultarClienteActivity.class);
        startActivity(intent);
    }

    public void onClickCadastrarCliente(View view) {
        Intent intent = new Intent(ClienteActivity.this, CadastrarClienteActivity.class);
        startActivity(intent);
    }
}