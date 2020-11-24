package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ClienteActivity extends AppCompatActivity {

    ImageView imageViewCall, imagemViewBack;
    CardView cardViewCadastrarCliente, cardViewConsultarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        cardViewCadastrarCliente = findViewById(R.id.cardViewCadastrarCliente);
        imageViewCall = findViewById(R.id.imageViewCallCliente);
        imagemViewBack = findViewById(R.id.imageViewBackCliente);
        cardViewConsultarCliente = findViewById(R.id.cardViewConsultarCliente);

        cardViewConsultarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, ConsultarClienteActivity.class);
                startActivity(intent);
            }
        });

        cardViewCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, CadastrarClienteActivity.class);
                startActivity(intent);
            }
        });


        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(11) 99472-9075"));
                startActivity(intent);
            }
        });

        imagemViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}