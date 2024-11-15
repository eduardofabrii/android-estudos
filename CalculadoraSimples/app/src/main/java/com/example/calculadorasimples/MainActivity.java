package com.example.calculadorasimples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnMultiplicar;
    Button btnDividir;
    Button btnSomar;
    Button btnSubtrair;
    EditText numeroUm;
    EditText numeroDois;
    EditText resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMultiplicar = findViewById(R.id.btn_multiplicar);
        btnDividir = (Button) findViewById(R.id.btn_dividir);
        btnSomar = (Button) findViewById(R.id.btn_somar);
        btnSubtrair = (Button) findViewById(R.id.btn_subtrair);
        numeroUm = (EditText) findViewById(R.id.et_numero_um);
        numeroDois = (EditText) findViewById(R.id.et_numero_dois);
        resultado = (EditText) findViewById(R.id.et_resultado);
    }

    public void somar(View v) {
        double num1 = Double.parseDouble(numeroUm.getText().toString());
        double num2 = Double.parseDouble(numeroDois.getText().toString());
        double res = num1 + num2;

        resultado.setText(String.valueOf(res));
    }

    public void subtrair(View v) {
        double num1 = Double.parseDouble(numeroUm.getText().toString());
        double num2 = Double.parseDouble(numeroDois.getText().toString());
        double res = num1 - num2;

        resultado.setText(String.valueOf(res));
    }

    public void multiplicar(View v) {
        double num1 = Double.parseDouble(numeroUm.getText().toString());
        double num2 = Double.parseDouble(numeroDois.getText().toString());
        double res = num1 * num2;

        resultado.setText(String.valueOf(res));
    }

    public void dividir(View v) {
        double num1 = Double.parseDouble(numeroUm.getText().toString());
        double num2 = Double.parseDouble(numeroDois.getText().toString());
        double res = num1 / num2;

        resultado.setText(String.valueOf(res));
    }
}