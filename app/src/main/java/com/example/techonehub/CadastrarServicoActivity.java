package com.example.techonehub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastrarServicoActivity extends AppCompatActivity {

    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(CadastrarServicoActivity.this);
    ImageView imageViewCall, imagemViewBack;
    Button buttonCadastrarServico;
    EditText editTextDescricao, editTextSistema, editTextTipo, editTextNEmpresa, editTextRuaServico, 
             editTextCepServico, editTextTelServico, editTextEmailServico, editTextCNPJServico,
            editTextCpfServico, editTextNmEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);

        imageViewCall = findViewById(R.id.imageViewCallCadastrarServico);
        imagemViewBack = findViewById(R.id.imageViewBackCadastrarServico);
        buttonCadastrarServico = findViewById(R.id.buttonCadastrarServico);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextSistema = findViewById(R.id.editTextSistema);
        editTextTipo = findViewById(R.id.editTextTipo);
        editTextNEmpresa = findViewById(R.id.editTextNEmpresa);
        editTextRuaServico = findViewById(R.id.editTextRuaServico);
        editTextCepServico = findViewById(R.id.editTextCepServico);
        editTextTelServico = findViewById(R.id.editTextTelServico);
        editTextEmailServico = findViewById(R.id.editTextEmailServico);
        editTextCNPJServico = findViewById(R.id.editTextCNPJServico);
        editTextCpfServico = findViewById(R.id.editTextCpfServico);
        editTextNmEmpresa = findViewById(R.id.editTextNmEmpresa);
        
        buttonCadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextNmEmpresa.length() < 1){
                    Toast.makeText(CadastrarServicoActivity.this, "É Necessário Preencher o campo Nome", Toast.LENGTH_SHORT).show();
                }
                else if(editTextCpfServico.length() != 12){
                    Toast.makeText(CadastrarServicoActivity.this, "O Campo CPF tem que ter 11 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextCNPJServico.length() < 18){
                    Toast.makeText(CadastrarServicoActivity.this, "O Campo CNPJ tem que ter 14 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextEmailServico.length() < 5){
                    Toast.makeText(CadastrarServicoActivity.this, "O Campo E-Mail é Necessário estar Preenchido", Toast.LENGTH_SHORT).show();
                }
                else if(editTextTelServico.length() < 15){
                    Toast.makeText(CadastrarServicoActivity.this, "O Telefone tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextCepServico.length() < 9){
                    Toast.makeText(CadastrarServicoActivity.this, "O CEP tem que ter 8 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextRuaServico.length() < 2){
                    Toast.makeText(CadastrarServicoActivity.this, "É Necessário Preencher o campo Rua", Toast.LENGTH_SHORT).show();
                }
                else if(editTextNEmpresa.length() < 2){
                    Toast.makeText(CadastrarServicoActivity.this, "è Necessário Informar o Nº", Toast.LENGTH_SHORT).show();
                }
                else if(editTextTipo.length() < 5){
                    Toast.makeText(CadastrarServicoActivity.this, "É Necessário Informar o Serviço que deseja no Sistema", Toast.LENGTH_SHORT).show();
                }
                else if(editTextSistema.length() < 3){
                    Toast.makeText(CadastrarServicoActivity.this, "É Necessário Informar o Sistema", Toast.LENGTH_SHORT).show();
                }
                else if(editTextDescricao.length() < 5){
                    Toast.makeText(CadastrarServicoActivity.this, "É Necessário fazer uma Descrição sobre o Sistema", Toast.LENGTH_SHORT).show();
                }
                else{

                    String Cpf = editTextCpfServico.getText().toString();
                    boolean Linha = daoTechOneHub.SelectCPF(Cpf);

                    if(Linha == true){
                        Inserir();
                        Toast.makeText(CadastrarServicoActivity.this, "Cadastrado com Sucesso!!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(CadastrarServicoActivity.this, "Esse CPF não está Cadastrado!!! É Necessário Cadastrar Primeiro um Cliente", Toast.LENGTH_SHORT).show();
                    }
                }
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
                Intent intent = new Intent(CadastrarServicoActivity.this, ServicoActivity.class);
                startActivity(intent);
            }
        });
        Mascara();
    }
    private void Inserir() {}
    private void Mascara() {

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTextTelServico,smf);
        editTextTelServico.addTextChangedListener(mtw);

        SimpleMaskFormatter smfcpf = new SimpleMaskFormatter("NNNNNNNNN-NN");
        MaskTextWatcher mtwcpf = new MaskTextWatcher(editTextCpfServico, smfcpf);
        editTextCpfServico.addTextChangedListener(mtwcpf);

        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(editTextCepServico,smfCep);
        editTextCepServico.addTextChangedListener(mtwCep);

        SimpleMaskFormatter smfCNPJ = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher mtwCNPJ = new MaskTextWatcher(editTextCNPJServico, smfCNPJ);
        editTextCNPJServico.addTextChangedListener(mtwCNPJ);
    }
}