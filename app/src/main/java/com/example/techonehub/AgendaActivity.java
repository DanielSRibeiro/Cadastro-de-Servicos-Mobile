package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AgendaActivity extends AppCompatActivity {

    ImageView imageViewCall, imagemViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        imageViewCall = findViewById(R.id.imageViewCallAgenda);
        imagemViewBack = findViewById(R.id.imageViewBackAgenda);

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
                Intent intent = new Intent(AgendaActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}