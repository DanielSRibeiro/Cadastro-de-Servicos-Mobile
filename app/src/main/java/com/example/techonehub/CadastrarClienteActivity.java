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

import com.example.techonehub.Dto.DtoCliente;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastrarClienteActivity extends AppCompatActivity {

    Button buttonCadastrarCliente;
    ImageView imageViewCall, imageViewBack;
    EditText editTextNCasa, editTextRua, editTextCPF, editTextRG, editTexttel, editTextEmail, editTextCEP;
    EditText editTextData, editTextNome;
    DtoCliente dtoCliente = new DtoCliente();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(CadastrarClienteActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);


        buttonCadastrarCliente = findViewById(R.id.buttonCadastrarCliente);
        imageViewBack = findViewById(R.id.imageViewBackCadastrarCliente);
        imageViewCall = findViewById(R.id.imageViewCallCadastrarCliente);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCPF = findViewById(R.id.editTextCPF);
        editTextRG = findViewById(R.id.editTextRG);
        editTexttel = findViewById(R.id.editTextTel);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextRua = findViewById(R.id.editTextRua);
        editTextCEP = findViewById(R.id.editTextCEP);
        editTextNCasa = findViewById(R.id.editTextNCasa);
        editTextData = findViewById(R.id.editTextData);

        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(11) 99472-9075"));
                startActivity(intent);
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarClienteActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });
        buttonCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextNome.length() < 1){
                    Toast.makeText(CadastrarClienteActivity.this, "É Necessário Preencher o campo Nome", Toast.LENGTH_SHORT).show();
                }
                else if(editTextCPF.length() != 12){
                    Toast.makeText(CadastrarClienteActivity.this, "O Campo CPF tem que ter 11 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextRG.length() < 12){
                    Toast.makeText(CadastrarClienteActivity.this, "O Campo RG tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextData.length() < 10){
                    Toast.makeText(CadastrarClienteActivity.this, "O Campo Data é Necessário ter Dia/Mês/Ano", Toast.LENGTH_SHORT).show();
                }
                else if(editTextEmail.length() < 5){
                    Toast.makeText(CadastrarClienteActivity.this, "O Campo E-Mail é Necessário estar Preenchido", Toast.LENGTH_SHORT).show();
                }
                else if(editTexttel.length() < 15){
                    Toast.makeText(CadastrarClienteActivity.this, "O Telefone tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextCEP.length() < 9){
                    Toast.makeText(CadastrarClienteActivity.this, "O CEP tem que ter 8 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextRua.length() < 2){
                    Toast.makeText(CadastrarClienteActivity.this, "É Necessário Preencher o campo Rua", Toast.LENGTH_SHORT).show();
                }
                else if(editTextNCasa.length() < 1){
                    Toast.makeText(CadastrarClienteActivity.this, "É Necessário Preencher o Nº da Casa", Toast.LENGTH_SHORT).show();
                }
                else{

                    String Cpf = editTextCPF.getText().toString();
                    boolean Linha = daoTechOneHub.SelectCPF(Cpf);

                    if(Linha == true){
                        Toast.makeText(CadastrarClienteActivity.this, "Esse CPF já está cadastrado. So pode ter um único CPF Cadastrado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Inserir();
                    }
                }
            }

        });

        Mascara();
    }

    private void Inserir() {
        dtoCliente.setNm(editTextNome.getText().toString());
        dtoCliente.setCpf(editTextCPF.getText().toString());
        dtoCliente.setEnde(editTextRua.getText().toString()+", "+editTextNCasa.getText().toString()+"," +
                " "+editTextCEP.getText().toString());

        dtoCliente.setDt(editTextData.getText().toString());
        dtoCliente.setTel(editTexttel.getText().toString());
        dtoCliente.setRg(editTextRG.getText().toString());
        dtoCliente.setEmail(editTextEmail.getText().toString());

        try {
            long Linha = daoTechOneHub.Insert(dtoCliente);

            if(Linha > 0){
                Toast.makeText(CadastrarClienteActivity.this, "SUCESSO ao Inserir!!!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder msg = new AlertDialog.Builder(CadastrarClienteActivity.this);
                msg.setMessage("Deseja voltar para a Tela do Cliente?");
                msg.setNegativeButton("Não",null);
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CadastrarClienteActivity.this, ClienteActivity.class);
                        startActivity(intent);
                    }
                });
                msg.show();
            }
            else{
                Toast.makeText(CadastrarClienteActivity.this, "Erro ao inserir!!!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(CadastrarClienteActivity.this, "Erro ao Inserir "+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void Mascara() {

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTexttel,smf);
        editTexttel.addTextChangedListener(mtw);

        SimpleMaskFormatter smfcpf = new SimpleMaskFormatter("NNNNNNNNN-NN");
        MaskTextWatcher mtwcpf = new MaskTextWatcher(editTextCPF, smfcpf);
        editTextCPF.addTextChangedListener(mtwcpf);

        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(editTextCEP,smfCep);
        editTextCEP.addTextChangedListener(mtwCep);

        SimpleMaskFormatter smfRg = new SimpleMaskFormatter("NN.NNN.NNN-N");
        MaskTextWatcher mtwRg = new MaskTextWatcher(editTextRG, smfRg);
        editTextRG.addTextChangedListener(mtwRg);

        SimpleMaskFormatter smkData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwData = new MaskTextWatcher(editTextData, smkData);
        editTextData.addTextChangedListener(mtwData);
    }
}