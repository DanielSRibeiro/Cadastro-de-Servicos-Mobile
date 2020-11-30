package com.example.techonehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.techonehub.Dto.DtoCliente;
import com.example.techonehub.MeuAdapter.MeuAdapterCliente;
import com.example.techonehub.MeuAdapter.RecyclerItemClickListenerCliente;

import java.util.ArrayList;

public class ConsultarClienteActivity extends AppCompatActivity {

    DtoCliente dtoCliente = new DtoCliente();
    ImageView imageViewCall, imagemViewBack;
    EditText editTextBuscar;
    RecyclerView recyclerView;
    ArrayList<DtoCliente> arrayListDtoCliente = new ArrayList<>();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(ConsultarClienteActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);

        imageViewCall = findViewById(R.id.imageViewCallConsultarCliente);
        recyclerView = findViewById(R.id.recyclerViewConsultarCliente);
        imagemViewBack = findViewById(R.id.imageViewBackConsultarCliente);
        editTextBuscar = findViewById(R.id.editTextBuscarCliente);

        editTextBuscar.addTextChangedListener(new TextWatcher() {
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListenerCliente(ConsultarClienteActivity.this, recyclerView,
                new RecyclerItemClickListenerCliente.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        AlertDialog.Builder msg = new AlertDialog.Builder(ConsultarClienteActivity.this);
                        msg.setMessage("Deseja Fazer alguma Alteração ?");
                        msg.setNegativeButton("Não", null);
                        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int id = arrayListDtoCliente.get(position).getId();
                                String nome = arrayListDtoCliente.get(position).getNm();
                                String cpf = arrayListDtoCliente.get(position).getCpf();
                                String rg = arrayListDtoCliente.get(position).getRg();
                                String email = arrayListDtoCliente.get(position).getEmail();
                                String tel = arrayListDtoCliente.get(position).getTel();
                                String dt = arrayListDtoCliente.get(position).getDt();
                                String ende = arrayListDtoCliente.get(position).getEnde();

                                Intent intent = new Intent(ConsultarClienteActivity.this, AlterarClienteActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("nome", nome);
                                intent.putExtra("cpf", cpf);
                                intent.putExtra("rg", rg);
                                intent.putExtra("email", email);
                                intent.putExtra("tel", tel);
                                intent.putExtra("dt", dt);
                                intent.putExtra("ende", ende);
                                startActivity(intent);
                            }
                        });
                        msg.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        int iid = arrayListDtoCliente.get(position).getId();
                        String cpf = arrayListDtoCliente.get(position).getCpf();

                        AlertDialog.Builder msg = new AlertDialog.Builder(ConsultarClienteActivity.this);
                        msg.setNegativeButton("Não", null);
                        msg.setMessage("Realmente deseja excluir ?");
                        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean CPF = daoTechOneHub.SelectCPF_(cpf);
                                if(CPF == false){
                                    int Linha = daoTechOneHub.Excluir(iid);

                                    if(Linha > 0){
                                        arrayListDtoCliente = daoTechOneHub.Select();
                                        Atualizar();
                                    }
                                    else{
                                        Toast.makeText(ConsultarClienteActivity.this, "Erro ao Excluir!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(ConsultarClienteActivity.this, "O cliente já inicio uma atividade/serviço, é Necessário primeiro excluir essa tarefa  para poder excluir " +
                                            "esse Cliente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        msg.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        arrayListDtoCliente = daoTechOneHub.Select();
        arrayListDtoCliente.add(dtoCliente);
        Atualizar();
    }

    private void Atualizar() {
        MeuAdapterCliente adapter = new MeuAdapterCliente(arrayListDtoCliente);
        recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarClienteActivity.this));
        recyclerView.setAdapter(adapter);
    }

}