package com.example.techonehub;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.techonehub.Dto.DtoServico;
import com.example.techonehub.Dto.DtoSocio;
import com.example.techonehub.MeuAdapter.MeuAdapterServico;
import com.example.techonehub.MeuAdapter.RecyclerItemClickListenerCliente;

import java.util.ArrayList;

public class SocioAgendaActivity extends AppCompatActivity {

    String cpf;
    ImageView imageViewCall, imagemViewBack;
    RecyclerView recyclerView;
    ArrayList<DtoServico> arrayListDtoServico = new ArrayList<>();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(SocioAgendaActivity.this);
    DtoServico dtoServico = new DtoServico();
    EditText editTextBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio_agenda);

        imageViewCall = findViewById(R.id.imageViewCallSocioAgenda);
        imagemViewBack = findViewById(R.id.imageViewBackSocioAgenda);
        recyclerView = findViewById(R.id.recyclerViewSocioAgenda);
        editTextBuscar = findViewById(R.id.editTextBuscarSocioAgenda);

        Bundle bundle = getIntent().getExtras();
        cpf = bundle.getString("cpf");

        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                arrayListDtoServico = daoTechOneHub.LikeServico(s.toString(), cpf);
                Atualizar();
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
                Intent intent = new Intent(SocioAgendaActivity.this, AgendaActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListenerCliente(SocioAgendaActivity.this, recyclerView,
                new RecyclerItemClickListenerCliente.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String nome = arrayListDtoServico.get(position).getNm();

                        AlertDialog.Builder msg = new AlertDialog.Builder(SocioAgendaActivity.this);
                        msg.setMessage("Deseja Fazer alguma Alteração no Serviço da Empresa "+nome+" ?");
                        msg.setNegativeButton("Não", null);
                        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SocioAgendaActivity.this, AlterarServicoActivity.class);
                                intent.putExtra("id", arrayListDtoServico.get(position).getId());
                                intent.putExtra("nome", arrayListDtoServico.get(position).getNm());
                                intent.putExtra("cpf", arrayListDtoServico.get(position).getCpf());
                                intent.putExtra("email", arrayListDtoServico.get(position).getEmail());
                                intent.putExtra("tel", arrayListDtoServico.get(position).getTel());
                                intent.putExtra("ende", arrayListDtoServico.get(position).getEnde());
                                intent.putExtra("cnpj", arrayListDtoServico.get(position).getCnpj());
                                intent.putExtra("data", arrayListDtoServico.get(position).getDtInicio());
                                intent.putExtra("prazo", arrayListDtoServico.get(position).getDtPrazo());
                                intent.putExtra("servico", arrayListDtoServico.get(position).getServico());
                                intent.putExtra("sistema", arrayListDtoServico.get(position).getSistema());
                                intent.putExtra("desc", arrayListDtoServico.get(position).getDesc());
                                intent.putExtra("espc", arrayListDtoServico.get(position).getEspc());
                                intent.putExtra("valor", arrayListDtoServico.get(position).getValor());
                                startActivity(intent);
                            }
                        });
                        msg.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        int iid = arrayListDtoServico.get(position).getId();

                        AlertDialog.Builder msg = new AlertDialog.Builder(SocioAgendaActivity.this);
                        msg.setNegativeButton("Não", null);
                        msg.setMessage("Realmente deseja excluir ?");
                        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int Linha = daoTechOneHub.ExcluirServico(iid);

                                if(Linha > 0){
                                    arrayListDtoServico = daoTechOneHub.SelectServico();
                                    Atualizar();
                                    Toast.makeText(SocioAgendaActivity.this, "Excluido com Sucesso!!!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(SocioAgendaActivity.this, "Erro ao Excluir!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        msg.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        arrayListDtoServico = daoTechOneHub.SelectServico(cpf);
        Atualizar();
    }

    private void Atualizar() {
        MeuAdapterServico adapter = new MeuAdapterServico(arrayListDtoServico);
        recyclerView.setLayoutManager(new LinearLayoutManager(SocioAgendaActivity.this));
        recyclerView.setAdapter(adapter);
    }
}