package com.example.techonehub.ui.cadastrar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.techonehub.R;
import com.example.techonehub.model.Dto.DtoServico;
import com.example.techonehub.model.DaoTechOneHub;
import com.example.techonehub.ui.ServicoActivity;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Calendar;

public class CadastrarServicoActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int ano = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH);
    int dia = cal.get(Calendar.DAY_OF_MONTH);
    String data = dia+ "/"+ (1+mes)+"/"+ano;
    String Prazo;
    DatePickerDialog.OnDateSetListener onDateSetListener;


    double valor;
    DtoServico dtoServico = new DtoServico();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(CadastrarServicoActivity.this);
    Button buttonCadastrar;
    Spinner spinnerServico, spinnerSistema;
    String Sistema, Servico;
    EditText    editTextDescricao, editTextN, editTextRua,
                editTextCep, editTextTel, editTextEmail, editTextCNPJ,
                editTextCpf, editTextNome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);

        initView();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IfElse();
            }

        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                int me= 1+mes;
                if(month >= 9 && day >9){
                    month += 1;
                    Prazo = day+"/"+month+"/"+year;
                }
                else if(month <= 8 && day >9){
                    month += 1;
                    Prazo = day+"/"+"0"+month+"/"+year;
                }
                else if(month > 8 && day < 10){
                    month += 1;
                    Prazo = "0"+day+"/"+month+"/"+year;
                }
                else {
                    month += 1;
                    Prazo = "0"+day+"/"+"0"+month+"/"+year;
                }

                if(ano > year){
                    Toast.makeText(CadastrarServicoActivity.this, "Não tem como voltar para o passado. Por Favor coloca um Ano que ainda nao passou", Toast.LENGTH_SHORT).show();
                }
                else if(me > month && ano >= year){
                    Toast.makeText(CadastrarServicoActivity.this, "Não tem como voltar para o passado. Por Favor coloca um Mês que ainda nao passou", Toast.LENGTH_SHORT).show();
                }
                else if(dia > day &&  me >= month && ano >= year){
                    Toast.makeText(CadastrarServicoActivity.this, "Não tem como voltar para o passado. Por Favor coloca um dia que ainda nao passou", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(Servico.equals("Todos os Serviços")){
                        if(Sistema.equals("Todos os Sistemas")){
                            dtoServico.setEspc("123456789-01 // 020406080-00 // 003006009-01 // 103050709-01 // 120450780-01");
                            valor = 12300;
                        }
                        else if(Sistema.equals("Mobile")){
                            dtoServico.setEspc("123456789-01");
                            valor = 3450;
                        }
                        else if(Sistema.equals("Web")){
                            dtoServico.setEspc("020406080-00");
                            valor = 2340;
                        }
                        else{
                            dtoServico.setEspc("120450780-01 // 003006009-01 // 103050709-01");
                            valor = 6490;
                        }
                    }
                    else if(Servico.equals("Desenvolvimento de Sistema")){
                        if(Sistema.equals("Todos os Sistemas")){
                            dtoServico.setEspc("123456789-01 // 020406080-00 // 003006009-01 // 103050709-01 // 120450780-01");
                            valor = 11000;
                        }
                        else if(Sistema.equals("Mobile")){
                            dtoServico.setEspc("123456789-01");
                            valor = 3000;
                        }
                        else if(Sistema.equals("Web")){
                            dtoServico.setEspc("020406080-00");
                            valor = 2000;
                        }
                        else{
                            dtoServico.setEspc("120450780-01 // 003006009-01 // 103050709-01");
                            valor = 6000;
                        }
                    }
                    else{

                        if(Sistema.equals("Todos os Sistemas")){
                            dtoServico.setEspc("123456789-01 // 020406080-00 // 003006009-01 // 103050709-01 // 120450780-01");
                            valor = 1500;
                        }
                        else if(Sistema.equals("Mobile")){
                            dtoServico.setEspc("123456789-01");
                            valor = 420;
                        }
                        else if(Sistema.equals("Web")){
                            dtoServico.setEspc("020406080-00");
                            valor = 359;
                        }
                        else{
                            dtoServico.setEspc("120450780-01 // 003006009-01 // 103050709-01");
                            valor = 670;
                        }
                    }

                    AlertDialog.Builder msg = new AlertDialog.Builder(CadastrarServicoActivity.this);
                    msg.setMessage("O Valor Total saira por R$"+valor);
                    msg.setNegativeButton("Cancela", null);
                    msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Inserir();
                        }
                    });
                    msg.show();
                }
            }
        };

        Spineer();
        Mascara();
    }

    private void initView() {
        buttonCadastrar = findViewById(R.id.buttonCadastrarServico);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        spinnerSistema = findViewById(R.id.SpinnerSistema);
        spinnerServico = findViewById(R.id.SpinnerServico);
        editTextN = findViewById(R.id.editTextNEmpresa);
        editTextRua = findViewById(R.id.editTextRuaServico);
        editTextCep = findViewById(R.id.editTextCepServico);
        editTextTel = findViewById(R.id.editTextTelServico);
        editTextEmail = findViewById(R.id.editTextEmailServico);
        editTextCNPJ = findViewById(R.id.editTextCNPJServico);
        editTextCpf = findViewById(R.id.editTextCpfServico);
        editTextNome = findViewById(R.id.editTextNmEmpresa);
    }

    private void Spineer() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Sistema, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSistema.setAdapter(arrayAdapter);
        spinnerSistema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sistema = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.Servico, android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServico.setAdapter(arrayAdapter1);
        spinnerServico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Servico = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Mascara() {

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTextTel,smf);
        editTextTel.addTextChangedListener(mtw);

        SimpleMaskFormatter smfcpf = new SimpleMaskFormatter("NNNNNNNNN-NN");
        MaskTextWatcher mtwcpf = new MaskTextWatcher(editTextCpf, smfcpf);
        editTextCpf.addTextChangedListener(mtwcpf);

        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(editTextCep,smfCep);
        editTextCep.addTextChangedListener(mtwCep);

        SimpleMaskFormatter smfCNPJ = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher mtwCNPJ = new MaskTextWatcher(editTextCNPJ, smfCNPJ);
        editTextCNPJ.addTextChangedListener(mtwCNPJ);

    }

    private void IfElse() {
        if(editTextNome.length() < 1){
            Toast.makeText(CadastrarServicoActivity.this, "É Necessário Preencher o campo Nome", Toast.LENGTH_SHORT).show();
        }
        else if(editTextCpf.length() != 12){
            Toast.makeText(CadastrarServicoActivity.this, "O Campo CPF tem que ter 11 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextCNPJ.length() < 18){
            Toast.makeText(CadastrarServicoActivity.this, "O Campo CNPJ tem que ter 14 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextEmail.length() < 5){
            Toast.makeText(CadastrarServicoActivity.this, "O Campo E-Mail é Necessário estar Preenchido", Toast.LENGTH_SHORT).show();
        }
        else if(editTextTel.length() < 15){
            Toast.makeText(CadastrarServicoActivity.this, "O Telefone tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextCep.length() < 9){
            Toast.makeText(CadastrarServicoActivity.this, "O CEP tem que ter 8 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextRua.length() < 2){
            Toast.makeText(CadastrarServicoActivity.this, "É Necessário Preencher o campo Rua", Toast.LENGTH_SHORT).show();
        }
        else if(editTextN.length() < 2){
            Toast.makeText(CadastrarServicoActivity.this, "è Necessário Informar o Nº", Toast.LENGTH_SHORT).show();
        }
        else if(editTextDescricao.length() < 5){
            Toast.makeText(CadastrarServicoActivity.this, "É Necessário fazer uma Descrição sobre o Sistema", Toast.LENGTH_SHORT).show();
        }
        else{
            String Cpf = editTextCpf.getText().toString();
            boolean Linha = daoTechOneHub.SelectCPF(Cpf);

            if(Linha == true){

                DatePickerDialog dialog = new DatePickerDialog(CadastrarServicoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    onDateSetListener, ano,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setMessage("Defina o prazo desejado");
                dialog.show();
            }
            else{
                Toast.makeText(CadastrarServicoActivity.this, "Esse CPF não está Cadastrado!!! É Necessário Cadastrar Primeiro um Cliente", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void Inserir() {
        dtoServico.setNm(editTextNome.getText().toString());
        dtoServico.setCpf(editTextCpf.getText().toString());
        dtoServico.setCnpj(editTextCNPJ.getText().toString());
        dtoServico.setEmail(editTextEmail.getText().toString());
        dtoServico.setTel(editTextTel.getText().toString());
        dtoServico.setEnde(editTextRua.getText().toString()+", "+editTextN.getText().toString()+", " +
                ""+editTextCep.getText().toString());
        dtoServico.setServico(Servico);
        dtoServico.setSistema(Sistema);
        dtoServico.setDesc(editTextDescricao.getText().toString());
        dtoServico.setDtInicio(data);
        dtoServico.setDtPrazo(Prazo);
        dtoServico.setValor(valor);

        try{
            long Linha = daoTechOneHub.InsertServico(dtoServico);
            long Linha1 = daoTechOneHub.InsertContrato(dtoServico);

            if (Linha > 0 && Linha1 > 0 ){
                Toast.makeText(this, "Serviço Cadastrado com Sucesso!!!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder msg = new AlertDialog.Builder(CadastrarServicoActivity.this);
                msg.setMessage("Deseja Voltar para a Tela anterior?");
                msg.setNegativeButton("Não", null);
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CadastrarServicoActivity.this, ServicoActivity.class);
                        startActivity(intent);
                    }
                });
                msg.show();
            }
            else {
                Toast.makeText(this, "Erro ao Inserir!!!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(this, ""+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickVoltar(View view) {
        finish();
    }

    public void onClickTelefone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:(11) 99472-9075"));
        startActivity(intent);
    }
}