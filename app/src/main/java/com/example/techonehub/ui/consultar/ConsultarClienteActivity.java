package com.example.techonehub.ui.consultar;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.techonehub.R;
import com.example.techonehub.model.Dto.DtoCliente;
import com.example.techonehub.meuadapter.MeuAdapterCliente;
import com.example.techonehub.meuadapter.OnClickItemListener;
import com.example.techonehub.model.DaoTechOneHub;
import com.example.techonehub.ui.ClienteActivity;
import com.example.techonehub.ui.alterar.AlterarClienteActivity;

import java.util.ArrayList;

public class ConsultarClienteActivity extends AppCompatActivity implements OnClickItemListener {

    DtoCliente dtoCliente = new DtoCliente();
    EditText editTextBuscar;
    RecyclerView recyclerView;
    ArrayList<DtoCliente> arrayListDtoCliente = new ArrayList<>();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(ConsultarClienteActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);

        recyclerView = findViewById(R.id.recyclerViewConsultarCliente);
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

        arrayListDtoCliente = daoTechOneHub.Select();
        arrayListDtoCliente.add(dtoCliente);
        Atualizar();
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
        MeuAdapterCliente adapter = new MeuAdapterCliente(arrayListDtoCliente, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarClienteActivity.this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(int posicao) {
        AlertDialog.Builder msg = new AlertDialog.Builder(ConsultarClienteActivity.this);
        msg.setMessage("Deseja Fazer alguma Alteração ?");
        msg.setNegativeButton("Não", null);
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int id = arrayListDtoCliente.get(posicao).getId();
                String nome = arrayListDtoCliente.get(posicao).getNm();
                String cpf = arrayListDtoCliente.get(posicao).getCpf();
                String rg = arrayListDtoCliente.get(posicao).getRg();
                String email = arrayListDtoCliente.get(posicao).getEmail();
                String tel = arrayListDtoCliente.get(posicao).getTel();
                String dt = arrayListDtoCliente.get(posicao).getDt();
                String ende = arrayListDtoCliente.get(posicao).getEnde();

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
    public void onLongClick(int posicao) {
        int iid = arrayListDtoCliente.get(posicao).getId();
        String cpf = arrayListDtoCliente.get(posicao).getCpf();

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

}