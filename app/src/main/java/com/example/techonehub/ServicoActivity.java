package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ServicoActivity extends AppCompatActivity {

    ImageView imageViewCall, imagemViewBack;
    CardView cardViewCadastrarServiço;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

        imagemViewBack = findViewById(R.id.imageViewBackServico);
        cardViewCadastrarServiço = findViewById(R.id.cardViewCadastrarServiço);
        imageViewCall = findViewById(R.id.imageViewCallServico);

        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(11) 99472-9075"));
                startActivity(intent);
            }
        });
        cardViewCadastrarServiço.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicoActivity.this, CadastrarServicoActivity.class);
                startActivity(intent);
            }
        });
        imagemViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}