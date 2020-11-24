package com.example.techonehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.techonehub.Dto.DtoCliente;

import java.util.ArrayList;

public class ConsultarClienteActivity extends AppCompatActivity {

    ImageView imageViewCall, imagemViewBack;
    EditText editTextBuscarCliente;
    ListView listViewConsultarCliente;
    ArrayList<DtoCliente> arrayListDtoCliente = new ArrayList<>();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(ConsultarClienteActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);

        imageViewCall = findViewById(R.id.imageViewCallConsultarCliente);
        listViewConsultarCliente = findViewById(R.id.listViewConsultarCliente);
        imagemViewBack = findViewById(R.id.imageViewBackConsultarCliente);
        editTextBuscarCliente = findViewById(R.id.editTextBuscarCliente);

        editTextBuscarCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                arrayListDtoCliente = daoTechOneHub.Like(s.toString());
                Atualizar();
            }
        });
        imagemViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultarClienteActivity.this, ClienteActivity.class);
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

        arrayListDtoCliente = daoTechOneHub.Select();
        Atualizar();
    }

    private void Atualizar() {
        ArrayAdapter adapter = new ArrayAdapter(ConsultarClienteActivity.this, android.R.layout.simple_list_item_1, arrayListDtoCliente);
        listViewConsultarCliente.setAdapter(adapter);
    }
}