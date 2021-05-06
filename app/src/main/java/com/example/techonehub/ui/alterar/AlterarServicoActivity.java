package com.example.techonehub.ui.alterar;

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
import com.example.techonehub.ui.consultar.ConsultarServicoActivity;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Calendar;

public class AlterarServicoActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int ano = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH);
    int dia = cal.get(Calendar.DAY_OF_MONTH);
    String data = dia+ "/"+ (1+mes)+"/"+ano;
    String Prazo;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    int id;
    double valor;
    DtoServico dtoServico = new DtoServico();
    DaoTechOneHub daoTechOneHub = new DaoTechOneHub(AlterarServicoActivity.this);
    Button buttonAlterar;
    Spinner spinnerServico, spinnerSistema;
    String Sistema, Servico;
    EditText editTextDescricao, editTextTel, editTextEmail,
            editTextCNPJ, editTextCpf, editTextNome,
            editTextEndereco, editTextValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_servico);

        initView();
        initBundle();

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AlterarServicoActivity.this, "Não tem como voltar para o passado. Por Favor coloca um Ano que ainda nao passou", Toast.LENGTH_SHORT).show();
                }
                else if(me > month && ano >= year){
                    Toast.makeText(AlterarServicoActivity.this, "Não tem como voltar para o passado. Por Favor coloca um Mês que ainda nao passou", Toast.LENGTH_SHORT).show();
                }
                else if(dia > day &&  me >= month && ano >= year){
                    Toast.makeText(AlterarServicoActivity.this, "Não tem como voltar para o passado. Por Favor coloca um dia que ainda nao passou", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(Servico.equals("Todos os Serviços")){
                        if(Sistema.equals("Todos os Sistemas")){
                            dtoServico.setEspc("123456789-01 // 020406080-00 // 003006009-01 // 103050709-01 // 120450780-01");
                            valor = 12300;
                            editTextValor.setText(""+valor);
                        }
                        else if(Sistema.equals("Mobile")){
                            dtoServico.setEspc("123456789-01");
                            valor = 3450;
                            editTextValor.setText(""+valor);
                        }
                        else if(Sistema.equals("Web")){
                            dtoServico.setEspc("020406080-00");
                            valor = 2340;
                            editTextValor.setText(""+valor);
                        }
                        else{
                            dtoServico.setEspc("120450780-01 // 003006009-01 // 103050709-01");
                            valor = 6490;
                            editTextValor.setText(""+valor);
                        }
                    }
                    else if(Servico.equals("Desenvolvimento de Sistema")){
                        if(Sistema.equals("Todos os Sistemas")){
                            dtoServico.setEspc("123456789-01 // 020406080-00 // 003006009-01 // 103050709-01 // 120450780-01");
                            valor = 11000;
                            editTextValor.setText(""+valor);
                        }
                        else if(Sistema.equals("Mobile")){
                            dtoServico.setEspc("123456789-01");
                            valor = 3000;
                            editTextValor.setText(""+valor);
                        }
                        else if(Sistema.equals("Web")){
                            dtoServico.setEspc("020406080-00");
                            valor = 2000;
                            editTextValor.setText(""+valor);
                        }
                        else{
                            dtoServico.setEspc("120450780-01 // 003006009-01 // 103050709-01");
                            valor = 6000;
                            editTextValor.setText(""+valor);
                        }
                    }
                    else{

                        if(Sistema.equals("Todos os Sistemas")){
                            dtoServico.setEspc("123456789-01 // 020406080-00 // 003006009-01 // 103050709-01 // 120450780-01");
                            valor = 1500;
                            editTextValor.setText(""+valor);
                        }
                        else if(Sistema.equals("Mobile")){
                            dtoServico.setEspc("123456789-01");
                            valor = 420;
                            editTextValor.setText(""+valor);
                        }
                        else if(Sistema.equals("Web")){
                            dtoServico.setEspc("020406080-00");
                            valor = 359;
                            editTextValor.setText(""+valor);
                        }
                        else{
                            dtoServico.setEspc("120450780-01 // 003006009-01 // 103050709-01");
                            valor = 670;
                            editTextValor.setText(""+valor);
                        }
                    }

                    AlertDialog.Builder msg = new AlertDialog.Builder(AlterarServicoActivity.this);
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

    private void initBundle() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        editTextNome.setText(bundle.getString("nome"));
        editTextCpf.setText(bundle.getString("cpf"));
        editTextCNPJ.setText(bundle.getString("cnpj"));
        editTextEmail.setText(bundle.getString("email"));
        editTextTel.setText(bundle.getString("tel"));
        editTextEndereco.setText(bundle.getString("ende"));
        editTextDescricao.setText(bundle.getString("desc"));
        editTextValor.setText(""+bundle.getDouble("valor"));
        data = bundle.getString("data");
        Prazo = bundle.getString("Prazo");
    }

    private void initView() {
        buttonAlterar = findViewById(R.id.buttonAlterarServico);
        editTextDescricao = findViewById(R.id.editTextDescricaoAlterarServico);
        spinnerSistema = findViewById(R.id.SpinnerSistemaAlterarServico);
        spinnerServico = findViewById(R.id.SpinnerAlterarServico);
        editTextEndereco = findViewById(R.id.editTextEnderecoAlterarServico);
        editTextTel = findViewById(R.id.editTextTelAlterarServico);
        editTextEmail = findViewById(R.id.editTextEmailAlterarServico);
        editTextCNPJ = findViewById(R.id.editTextCNPJAlterarServico);
        editTextCpf = findViewById(R.id.editTextCpfAlterarServico);
        editTextNome = findViewById(R.id.editTextNmEmpresaAlterarServico);
        editTextValor = findViewById(R.id.editTextValorAlterarServico);
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

        SimpleMaskFormatter smfCNPJ = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher mtwCNPJ = new MaskTextWatcher(editTextCNPJ, smfCNPJ);
        editTextCNPJ.addTextChangedListener(mtwCNPJ);

    }

    private void IfElse() {
        if(editTextNome.length() < 1){
            Toast.makeText(AlterarServicoActivity.this, "É Necessário Preencher o campo Nome", Toast.LENGTH_SHORT).show();
        }
        else if(editTextCpf.length() != 12){
            Toast.makeText(AlterarServicoActivity.this, "O Campo CPF tem que ter 11 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextCNPJ.length() < 18){
            Toast.makeText(AlterarServicoActivity.this, "O Campo CNPJ tem que ter 14 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextEmail.length() < 5){
            Toast.makeText(AlterarServicoActivity.this, "O Campo E-Mail é Necessário estar Preenchido", Toast.LENGTH_SHORT).show();
        }
        else if(editTextTel.length() < 15){
            Toast.makeText(AlterarServicoActivity.this, "O Telefone tem que ter 9 digitos", Toast.LENGTH_SHORT).show();
        }
        else if(editTextEndereco.length() < 3){
            Toast.makeText(AlterarServicoActivity.this, "É Necessário colocar o seu Endereço(Rua, Nº, CEP)", Toast.LENGTH_SHORT).show();
        }
        else if(editTextDescricao.length() < 5){
            Toast.makeText(AlterarServicoActivity.this, "É Necessário fazer uma Descrição sobre o Sistema", Toast.LENGTH_SHORT).show();
        }
        else{
            String Cpf = editTextCpf.getText().toString();
            boolean Linha = daoTechOneHub.SelectCPF(Cpf);

            if(Linha == true){

                DatePickerDialog dialog = new DatePickerDialog(AlterarServicoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, ano,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setMessage("Defina o Prazo desejado");
                dialog.show();
            }
            else{
                Toast.makeText(AlterarServicoActivity.this, "Esse CPF não está Cadastrado!!! É Necessário Cadastrar Primeiro um Cliente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Inserir() {
        dtoServico.setNm(editTextNome.getText().toString());
        dtoServico.setCpf(editTextCpf.getText().toString());
        dtoServico.setCnpj(editTextCNPJ.getText().toString());
        dtoServico.setEmail(editTextEmail.getText().toString());
        dtoServico.setTel(editTextTel.getText().toString());
        dtoServico.setEnde(editTextEndereco.getText().toString());
        dtoServico.setServico(Servico);
        dtoServico.setSistema(Sistema);
        dtoServico.setDesc(editTextDescricao.getText().toString());
        dtoServico.setDtInicio(data);
        dtoServico.setDtPrazo(Prazo);

        dtoServico.setValor(valor);

        try{
            long Linha = daoTechOneHub.Update(dtoServico, id);

            if (Linha > 0){
                Toast.makeText(this, "Alterado com Sucesso!!!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder msg = new AlertDialog.Builder(AlterarServicoActivity.this);
                msg.setMessage("Deseja Voltar para a Tela anterior?");
                msg.setNegativeButton("Não", null);
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AlterarServicoActivity.this, ConsultarServicoActivity.class);
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