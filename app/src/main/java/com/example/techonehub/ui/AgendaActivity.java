package com.example.techonehub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.techonehub.R;
import com.example.techonehub.model.Dto.DtoSocio;
import com.example.techonehub.meuadapter.MeuAdapterSocio;
import com.example.techonehub.meuadapter.OnClickItemListener;
import com.example.techonehub.model.DaoTechOneHub;

import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity implements OnClickItemListener {

    RecyclerView recyclerView;
    ArrayList<DtoSocio> arrayListSocio = new ArrayList<>();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(AgendaActivity.this);
    DtoSocio dtoSocio = new DtoSocio();
    EditText editTextBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        recyclerView = findViewById(R.id.recyclerViewAgenda);
        editTextBuscar = findViewById(R.id.editTextBuscarAgenda);

        Adicionar();
        arrayListSocio = daoTechOneHub.Select(dtoSocio);
        Atualizar();

        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                arrayListSocio = daoTechOneHub.LikeAgenda(s.toString());
                Atualizar();
            }
        });
    }

    public void onClickVoltar(View view) {
        finish();
    }

    public void onClickTelefone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:(11) 99472-9075"));
        startActivity(intent);
    }

    private void Atualizar() {
        MeuAdapterSocio adapter = new MeuAdapterSocio(arrayListSocio, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        recyclerView.setAdapter(adapter);
    }

    private void Adicionar() {
        dtoSocio = new DtoSocio(1,"Daniel Santos Ribeiro","123456789-01","Desenvolvedor Mobile");
        arrayListSocio.add(dtoSocio);
        daoTechOneHub.Insert(dtoSocio);
        
        dtoSocio = new DtoSocio(2,"Julia Nóbrega","020406080-00","Desenvolvedor Front-End");
        arrayListSocio.add(dtoSocio);
        daoTechOneHub.Insert(dtoSocio);
        
        DtoSocio dtoSocio = new DtoSocio(3,"Vitor Lopes","003006009-01","Infraestrutura");
        arrayListSocio.add(dtoSocio);
        daoTechOneHub.Insert(dtoSocio);

        dtoSocio = new DtoSocio(4,"Milena Osorio do Amaral","103050709-01","Cientista de Dados");
        arrayListSocio.add(dtoSocio);
        daoTechOneHub.Insert(dtoSocio);

        dtoSocio = new DtoSocio(5,"João Miziaria","120450780-01","Desenvolvedor Back-End");
        arrayListSocio.add(dtoSocio);
        daoTechOneHub.Insert(dtoSocio);

    }

    @Override
    public void onClick(int posicao) {
        Intent intent = new Intent(AgendaActivity.this, SocioAgendaActivity.class);
        intent.putExtra("id", arrayListSocio.get(posicao).getId());
        intent.putExtra("nome", arrayListSocio.get(posicao).getNm());
        intent.putExtra("cpf", arrayListSocio.get(posicao).getCpf());
        intent.putExtra("espec", arrayListSocio.get(posicao).getEspec());
        startActivity(intent);
    }

    @Override
    public void onLongClick(int posicao) {

    }
}