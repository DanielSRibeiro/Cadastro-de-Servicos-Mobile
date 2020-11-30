package com.example.techonehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.techonehub.Dto.DtoCliente;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Calendar;
import java.util.Date;

public class AlterarClienteActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int ano = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH);
    int dia = cal.get(Calendar.DAY_OF_MONTH);
    String data = dia+ "/"+ (1+mes)+"/"+ano;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    Button buttonAlterar;
    ImageView imageViewCall, imageViewBack;
    EditText editTextEndereco, editTextCPF, editTextRG, editTexttel, editTextEmail;
    EditText editTextData, editTextNome;
    DtoCliente dtoCliente = new DtoCliente();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(AlterarClienteActivity.this);
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cliente);
        buttonAlterar = findViewById(R.id.buttonAlterarCliente);
        imageViewBack = findViewById(R.id.imageViewBackAlterarCliente);
        imageViewCall = findViewById(R.id.imageViewCallAlterarCliente);
        editTextNome = findViewById(R.id.editTextNomeAlterar);
        editTextCPF = findViewById(R.id.editTextCPFAlterar);
        editTextRG = findViewById(R.id.editTextRGAlterar);
        editTexttel = findViewById(R.id.editTextTelAlterar);
        editTextEmail = findViewById(R.id.editTextEmailAlterar);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextData = findViewById(R.id.editTextDataAlterar);

        editTextCPF.setEnabled(false);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        editTextNome.setText(bundle.getString("nome"));
        editTextCPF.setText(bundle.getString("cpf"));
        editTextRG.setText(bundle.getString("rg"));
        editTextData.setText(bundle.getString("dt"));
        editTextEmail.setText(bundle.getString("email"));
        editTexttel.setText(bundle.getString("tel"));
        editTextEndereco.setText(bundle.getString("ende"));

        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog date = new DatePickerDialog(AlterarClienteActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, ano,mes,dia);
                date.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                date.setMessage("Qual é a data do seu Nascimento ?");
                date.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(month >= 9 && dayOfMonth >9){
                    data = dayOfMonth+"/"+(1+month)+"/"+year;
                }
                else if(month <= 8 && dayOfMonth >9){
                    data = dayOfMonth+"/"+"0"+(1+month)+"/"+year;
                }
                else if(month > 8 && dayOfMonth < 10){
                    data = "0"+dayOfMonth+"/"+(1+month)+"/"+year;
                }
                else {
                    data = "0"+dayOfMonth+"/"+"0"+(1+month)+"/"+year;
                }
                editTextData.setText(data);
            }
        };


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
                Intent intent = new Intent(AlterarClienteActivity.this, ConsultarClienteActivity.class);
                startActivity(intent);
            }
        });

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextNome.length() < 1){
                    Toast.makeText(AlterarClienteActivity.this, "É Necessário Preencher o campo Nome", Toast.LENGTH_SHORT).show();
                }
                else if(editTextRG.length() < 12){
                    Toast.makeText(AlterarClienteActivity.this, "O Campo RG tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextEmail.length() < 5){
                    Toast.makeText(AlterarClienteActivity.this, "O Campo E-Mail é Necessário estar Preenchido", Toast.LENGTH_SHORT).show();
                }
                else if(editTexttel.length() < 15){
                    Toast.makeText(AlterarClienteActivity.this, "O Telefone tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
                }
                else if(editTextEndereco.length() < 12){
                    Toast.makeText(AlterarClienteActivity.this, "O CEP tem que ter 8 digitos", Toast.LENGTH_SHORT).show();
                }
                else{
                        Inserir();
                }
            }

        });

        Mascara();
    }

    private void Inserir() {
        dtoCliente.setNm(editTextNome.getText().toString());
        dtoCliente.setCpf(editTextCPF.getText().toString());
        dtoCliente.setEnde(editTextEndereco.getText().toString());

        dtoCliente.setDt(editTextData.getText().toString());
        dtoCliente.setTel(editTexttel.getText().toString());
        dtoCliente.setRg(editTextRG.getText().toString());
        dtoCliente.setEmail(editTextEmail.getText().toString());

        try {
            long Linha = daoTechOneHub.Update(dtoCliente, id);

            if(Linha > 0){
                Toast.makeText(AlterarClienteActivity.this, "Alterado com Sucesso!!!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder msg = new AlertDialog.Builder(AlterarClienteActivity.this);
                msg.setMessage("Deseja voltar para a Tela do Cliente?");
                msg.setNegativeButton("Não",null);
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AlterarClienteActivity.this, ConsultarClienteActivity.class);
                        startActivity(intent);
                    }
                });
                msg.show();
            }
            else{
                Toast.makeText(AlterarClienteActivity.this, "Erro ao inserir!!!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(AlterarClienteActivity.this, "Erro ao Inserir "+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void Mascara() {

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTexttel,smf);
        editTexttel.addTextChangedListener(mtw);

        SimpleMaskFormatter smfcpf = new SimpleMaskFormatter("NNNNNNNNN-NN");
        MaskTextWatcher mtwcpf = new MaskTextWatcher(editTextCPF, smfcpf);
        editTextCPF.addTextChangedListener(mtwcpf);

        SimpleMaskFormatter smfRg = new SimpleMaskFormatter("NN.NNN.NNN-N");
        MaskTextWatcher mtwRg = new MaskTextWatcher(editTextRG, smfRg);
        editTextRG.addTextChangedListener(mtwRg);

        SimpleMaskFormatter smkData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwData = new MaskTextWatcher(editTextData, smkData);
        editTextData.addTextChangedListener(mtwData);
    }
}