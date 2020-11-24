package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    CardView cardViewAgenda,cardViewAbout, cardViewConsultar, cardViewCadastrar;
    ImageView imageViewCall, imagemViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardViewAgenda = findViewById(R.id.cardViewAgenda);
        cardViewAbout = findViewById(R.id.cardViewAbout);
        cardViewConsultar = findViewById(R.id.cardViewConsultar);
        cardViewCadastrar = findViewById(R.id.cardViewCadastrar);
        imageViewCall = findViewById(R.id.imageViewCallHome);

        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(11) 99472-9075"));
                startActivity(intent);
            }
        });
        cardViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });

        cardViewAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AgendaActivity.class);
                startActivity(intent);
            }
        });

        cardViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        cardViewConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ServicoActivity.class);
                startActivity(intent);
            }
        });
    }
}