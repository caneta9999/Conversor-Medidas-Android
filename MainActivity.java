package com.example.trabalhoandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText edtUnidadeImp;
    private EditText edtUnidadeInt;
    private EditText edtArredondamento;
    private TextView txtUltimaConversao;
    private Spinner spnOpcoes;
    private TextView txtDescricao;
    private TextView txtTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUnidadeImp = findViewById(R.id.edtUnidadeImp);
        edtUnidadeInt = findViewById(R.id.edtUnidadeInt);
        txtUltimaConversao = findViewById(R.id.txtUltimaConversao);
        spnOpcoes = findViewById(R.id.spnOpcoes);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtTitulo = findViewById(R.id.txtTitulo);
        edtArredondamento = findViewById(R.id.edtArredondamento);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            txtDescricao.setVisibility(View.INVISIBLE);
            txtTitulo.setVisibility(View.INVISIBLE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            txtDescricao.setVisibility(View.VISIBLE);
            txtTitulo.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        TextView txt;
        EditText edt;
        txt = findViewById(R.id.txtUltimaConversao);
        savedInstanceState.putString("ultimaconversao", txt.getText().toString());
        edt = findViewById(R.id.edtUnidadeImp);
        savedInstanceState.putString("unidadeimp", edt.getText().toString());
        edt = findViewById(R.id.edtUnidadeInt);
        savedInstanceState.putString("unidadeint", edt.getText().toString());
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView txt;
        EditText edt;
        String s;
        s = savedInstanceState.getString("ultimaconversao");
        txt = findViewById(R.id.txtUltimaConversao);
        txt.setText(s);
        s = savedInstanceState.getString("unidadeimp");
        edt = findViewById(R.id.edtUnidadeImp);
        edt.setText(s);
        s = savedInstanceState.getString("unidadeint");
        edt = findViewById(R.id.edtUnidadeInt);
        edt.setText(s);
    }

    //funções para converter os valores
    private double polegadasParaCentimetros(double valor){
        return valor*2.54;}
    private double centimetrosParaPolegadas(double valor){
        return valor/2.54;}
    private double pesParaMetros(double valor){
        return valor/3.281;}
    private double metrosParaPes(double valor){
        return valor*3.281;}
    private double milhasParaQuilometros(double valor){
        return valor*1.609;}
    private double quilometrosParaMilhas(double valor){
        return valor/1.609;}
    private double celsiusParaFahrenheit(double valor){
        return (valor * 9.0/5) + 32;}
    private double fahrenheitParaCelsius(double valor){
        return (valor-32)*(5.0/9);}
    private double nosParaQuilometrosPorHora(double valor){
        return valor*1.852;}
    private double quilometrosPorHoraParaNos(double valor){
        return valor/1.852;}
    //função de arredondar
    private static String arredondar(double valor, int numerodecasas) {
        String s = "0";
        if(numerodecasas != 0){
            s += ".";
        }
        for(int i = 0;i<numerodecasas;i++){
            if(i == 9){
                break;
            }
            s += "0";
        }
        DecimalFormat formatador = new DecimalFormat(s);
        return (formatador.format(valor)).replace(",",".");
    }
    //definição do OnClick dos botões
    @SuppressLint("DefaultLocale")
    public void btnConverteImpIntOnClick(View view){
        try{
        if (spnOpcoes.getSelectedItemPosition() == 0){
            String unidadeimp = edtUnidadeImp.getText().toString();
            String unidadeint;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeimp.length()>30){
                unidadeint = Double.toString(polegadasParaCentimetros(Double.parseDouble(unidadeimp)));}
            else{
                unidadeint = (arredondar(polegadasParaCentimetros(Double.parseDouble(unidadeimp)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeInt.setText(unidadeint);
            if(unidadeimp.length() < 50) txtUltimaConversao.setText(String.format("%s%sin = %scm", getString(R.string.ultimaconversaorealizada),unidadeimp,edtUnidadeInt.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));}
        else if (spnOpcoes.getSelectedItemPosition() == 1){
            String unidadeimp = edtUnidadeImp.getText().toString();
            String unidadeint;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeimp.length()>30){
                unidadeint = Double.toString(pesParaMetros(Double.parseDouble(unidadeimp)));}
            else{
                unidadeint = (arredondar(pesParaMetros(Double.parseDouble(unidadeimp)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeInt.setText(unidadeint);
            if(unidadeimp.length() < 50) txtUltimaConversao.setText(String.format("%s%sft = %sm", getString(R.string.ultimaconversaorealizada),unidadeimp,edtUnidadeInt.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }
        else if (spnOpcoes.getSelectedItemPosition() == 2){
            String unidadeimp = edtUnidadeImp.getText().toString();
            String unidadeint;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeimp.length()>30){
                unidadeint = Double.toString(milhasParaQuilometros(Double.parseDouble(unidadeimp)));}
            else{
                unidadeint = (arredondar(milhasParaQuilometros(Double.parseDouble(unidadeimp)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeInt.setText(unidadeint);
            if(unidadeimp.length() < 50) txtUltimaConversao.setText(String.format("%s%smi = %skm", getString(R.string.ultimaconversaorealizada),unidadeimp,edtUnidadeInt.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }
        else if (spnOpcoes.getSelectedItemPosition() == 3){
            String unidadeimp = edtUnidadeImp.getText().toString();
            String unidadeint;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeimp.length()>30){
                unidadeint = Double.toString(fahrenheitParaCelsius(Double.parseDouble(unidadeimp)));}
            else{
                unidadeint = (arredondar(fahrenheitParaCelsius(Double.parseDouble(unidadeimp)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeInt.setText(unidadeint);
            if(unidadeimp.length() < 50) txtUltimaConversao.setText(String.format("%s%s°F = %s°C", getString(R.string.ultimaconversaorealizada),unidadeimp,edtUnidadeInt.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }
        else if (spnOpcoes.getSelectedItemPosition() == 4){
            String unidadeimp = edtUnidadeImp.getText().toString();
            String unidadeint;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeimp.length()>30){
                unidadeint = Double.toString(nosParaQuilometrosPorHora(Double.parseDouble(unidadeimp)));}
            else{
                unidadeint = (arredondar(nosParaQuilometrosPorHora(Double.parseDouble(unidadeimp)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeInt.setText(unidadeint);
            if(unidadeimp.length() < 50) txtUltimaConversao.setText(String.format("%s%skn = %skm/h", getString(R.string.ultimaconversaorealizada),unidadeimp,edtUnidadeInt.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }} catch (Exception e) {
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("DefaultLocale")
    public void btnConverteIntImpOnClick(View view){
        try{
        if (spnOpcoes.getSelectedItemPosition() == 0){
            String unidadeint = edtUnidadeInt.getText().toString();
            String unidadeimp;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeint.length()>30){
                unidadeimp = Double.toString(centimetrosParaPolegadas(Double.parseDouble(unidadeint)));}
            else{
                unidadeimp = (arredondar(centimetrosParaPolegadas(Double.parseDouble(unidadeint)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeImp.setText(unidadeimp);
            if(unidadeint.length() < 50) txtUltimaConversao.setText(String.format("%s%scm = %sin", getString(R.string.ultimaconversaorealizada),unidadeint, edtUnidadeImp.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));}
        else if (spnOpcoes.getSelectedItemPosition() == 1){
            String unidadeint = edtUnidadeInt.getText().toString();
            String unidadeimp;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeint.length()>30){
                unidadeimp = Double.toString(metrosParaPes(Double.parseDouble(unidadeint)));}
            else{
                unidadeimp = (arredondar(metrosParaPes(Double.parseDouble(unidadeint)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeImp.setText(unidadeimp);
            if(unidadeint.length() < 50) txtUltimaConversao.setText(String.format("%s%sm = %sft", getString(R.string.ultimaconversaorealizada),unidadeint,edtUnidadeImp.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }
        else if (spnOpcoes.getSelectedItemPosition() == 2){
            String unidadeint = edtUnidadeInt.getText().toString();
            String unidadeimp;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeint.length()>30){
                unidadeimp = Double.toString(quilometrosParaMilhas(Double.parseDouble(unidadeint)));}
            else{
                unidadeimp = (arredondar(quilometrosParaMilhas(Double.parseDouble(unidadeint)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeImp.setText(unidadeimp);
            if(unidadeint.length() < 50) txtUltimaConversao.setText(String.format("%s%skm =  %smi", getString(R.string.ultimaconversaorealizada),unidadeint,edtUnidadeImp.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }
        else if (spnOpcoes.getSelectedItemPosition() == 3){
            String unidadeint = edtUnidadeInt.getText().toString();
            String unidadeimp;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeint.length()>30){
                unidadeimp = Double.toString(celsiusParaFahrenheit(Double.parseDouble(unidadeint)));}
            else{
                unidadeimp = (arredondar(celsiusParaFahrenheit(Double.parseDouble(unidadeint)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeImp.setText(unidadeimp);
            if(unidadeint.length() < 50) txtUltimaConversao.setText(String.format("%s%s°C = %s°F", getString(R.string.ultimaconversaorealizada),unidadeint,edtUnidadeImp.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }
        else if (spnOpcoes.getSelectedItemPosition() == 4){
            String unidadeint = edtUnidadeInt.getText().toString();
            String unidadeimp;
            if(edtArredondamento.getText().toString().trim().length() == 0 || unidadeint.length()>30){
                unidadeimp = Double.toString(quilometrosPorHoraParaNos(Double.parseDouble(unidadeint)));}
            else{
                unidadeimp = (arredondar(quilometrosPorHoraParaNos(Double.parseDouble(unidadeint)),Integer.parseInt(String.valueOf(edtArredondamento.getText()))));}
            edtUnidadeImp.setText(unidadeimp);
            if(unidadeint.length() < 50) txtUltimaConversao.setText(String.format("%s%skm/h = %skn", getString(R.string.ultimaconversaorealizada),unidadeint,edtUnidadeImp.getText()));
            else txtUltimaConversao.setText(getString(R.string.ultimaconversaotextomuitogrande));
        }}
        catch (Exception e){
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
        }
    }

}
