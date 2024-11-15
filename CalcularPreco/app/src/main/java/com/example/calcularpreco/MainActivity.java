package com.example.calcularpreco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_calcular;
    CheckBox cb1, cb2, cb3, cb4, cb5;
    TextView valor_real;
    EditText et1, et2, et3, et4, et5;
    double precot1, precot2, precot3, precot4, precot5;



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

        btn_calcular = (Button) findViewById(R.id.btn_calcular_total);

        cb1 = (CheckBox) findViewById(R.id.cb_item_1);
        cb2 = (CheckBox) findViewById(R.id.cb_item_2);
        cb3 = (CheckBox) findViewById(R.id.cb_item_3);
        cb4 = (CheckBox) findViewById(R.id.cb_item_4);
        cb5 = (CheckBox) findViewById(R.id.cb_item_5);

        valor_real = (TextView) findViewById(R.id.tv_valor_total);

        et1 = (EditText) findViewById(R.id.et_item_um);
        et2 = (EditText) findViewById(R.id.et_item_dois);
        et3 = (EditText) findViewById(R.id.et_item_tres);
        et4 = (EditText) findViewById(R.id.et_item_quatro);
        et5 = (EditText) findViewById(R.id.et_item_cinco);

        precot1 = 3.5;
        precot2 = 6.90;
        precot3 = 5.90;
        precot4 = 3.5;
        precot5 = 3.5;

        cb1.setText(cb1.getText().toString() + precot1);
        cb2.setText(cb2.getText().toString() + precot2);
        cb3.setText(cb3.getText().toString() + precot3);
        cb4.setText(cb4.getText().toString() + precot4);
        cb5.setText(cb5.getText().toString() + precot5);

        valor_real.setText("Preço Total: " + String.format("R$ %.2f", 0.0));
    }

    public void calcularPrecoTotal(View v) {
        double total = 0.0;

        if (cb1.isChecked()) {
            String qtde = et1.getText().toString();
            if (!qtde.isEmpty()) {
                total += Double.parseDouble(qtde) * precot1;
            }
        }

        if (cb2.isChecked()) {
            String qtde = et2.getText().toString();
            if (!qtde.isEmpty()) {
                total += Double.parseDouble(qtde) * precot2;
            }
        }

        if (cb3.isChecked()) {
            String qtde = et3.getText().toString();
            if (!qtde.isEmpty()) {
                total += Double.parseDouble(qtde) * precot3;
            }
        }

        if (cb4.isChecked()) {
            String qtde = et4.getText().toString();
            if (!qtde.isEmpty()) {
                total += Double.parseDouble(qtde) * precot4;
            }
        }

        if (cb5.isChecked()) {
            String qtde = et5.getText().toString();
            if (!qtde.isEmpty()) {
                total += Double.parseDouble(qtde) * precot5;
            }
        }

        valor_real.setText("Preço Total: " + String.format("R$ %.2f", total));

        String msg = "Calculado com sucesso!";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}