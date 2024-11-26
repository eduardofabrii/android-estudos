package com.example.contadordegestacao;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

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

        TextView tituloApp = findViewById(R.id.tituloApp);
        TextView tvMensagemBebe = findViewById(R.id.tvMensagemBebe);
        Button btnPickDate  = findViewById(R.id.btnPickDate);

        Switch switchTheme = findViewById(R.id.switch1);

        switchTheme.setChecked(false);

        tituloApp.setTextColor(ContextCompat.getColor(this, R.color.pink));
        btnPickDate.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.pink)));
        tvMensagemBebe.setTextColor(ContextCompat.getColor(this, R.color.pink));

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tituloApp.setTextColor(ContextCompat.getColor(this, R.color.blue));
                btnPickDate.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.blue)));
                tvMensagemBebe.setTextColor(ContextCompat.getColor(this, R.color.blue));
                Toast.makeText(MainActivity.this, "Tema Blue", Toast.LENGTH_SHORT).show();
            } else {
                tituloApp.setTextColor(ContextCompat.getColor(this, R.color.pink));
                btnPickDate.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.pink)));
                tvMensagemBebe.setTextColor(ContextCompat.getColor(this, R.color.pink));
                Toast.makeText(MainActivity.this, "Tema Pink", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void botaoEscolherData(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = "Última Menstruação: " + selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;

                    TextView tvSelectedDate = findViewById(R.id.tvSelectedDate);
                    tvSelectedDate.setText(selectedDate);

                    calcularGestacao(selectedYear, selectedMonth, selectedDay);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    public void calcularGestacao(int year, int month, int day) {
        try {
            Calendar hoje = Calendar.getInstance();

            Calendar dataUltimaMenstruacao = Calendar.getInstance();
            dataUltimaMenstruacao.set(year, month, day);

            long diffMillis = hoje.getTimeInMillis() - dataUltimaMenstruacao.getTimeInMillis();

            if (diffMillis < 0) {
                TextView tvSemanaAtual = findViewById(R.id.tvSemanaAtual);
                TextView tvDiasRestantes = findViewById(R.id.tvDiasRestantes);
                ImageView ivTamanhoBebe = findViewById(R.id.ivTamanhoBebe);
                TextView tvMensagemBebe = findViewById(R.id.tvMensagemBebe);

                tvSemanaAtual.setText("Data inválida! A data inserida é no futuro.");
                tvDiasRestantes.setText("");

                ivTamanhoBebe.setImageResource(R.drawable.mulher_brava);
                tvMensagemBebe.setText("Selecione uma data válida para calcular a gestação.");
                return;
            }

            int diasTotais = (int) (diffMillis / (1000 * 60 * 60 * 24));
            int semanaAtual = diasTotais / 7;
            int diasRestantes = (40 * 7) - diasTotais;

            TextView tvSemanaAtual = findViewById(R.id.tvSemanaAtual);
            TextView tvDiasRestantes = findViewById(R.id.tvDiasRestantes);
            ImageView ivTamanhoBebe = findViewById(R.id.ivTamanhoBebe);
            TextView tvMensagemBebe = findViewById(R.id.tvMensagemBebe);

            if (semanaAtual <= 40) {
                tvSemanaAtual.setText("Semana atual de gestação: " + semanaAtual);
                tvDiasRestantes.setText("Dias restantes até o parto: " + diasRestantes);

                int drawableResId = getDrawableForSemana(semanaAtual);
                String mensagem = getMensagemParaSemana(semanaAtual);
                ivTamanhoBebe.setImageResource(drawableResId);
                tvMensagemBebe.setText(mensagem);
            } else {
                tvSemanaAtual.setText("A gestação passou de 40 semanas.");
                tvDiasRestantes.setText("");
                ivTamanhoBebe.setImageResource(R.drawable.nenem);
                tvMensagemBebe.setText("O bebê já nasceu ou a gestação ultrapassou 40 semanas.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int getDrawableForSemana(int semana) {
        switch (semana) {
            case 1:
                return R.drawable.bebe_semana_1;
            case 2:
                return R.drawable.bebe_semana_2;
            case 3:
                return R.drawable.bebe_semana_3;
            case 4:
                return R.drawable.bebe_semana_4;
            case 5:
                return R.drawable.bebe_semana_4;
            case 6:
                return R.drawable.bebe_semana_4;
            case 7:
                return R.drawable.bebe_semana_9;
            case 8:
                return R.drawable.bebe_semana_9;
            case 9:
                return R.drawable.bebe_semana_9;
            case 10:
                return R.drawable.bebe_semana_10;
            case 11:
                return R.drawable.bebe_semana_11;
            case 12:
                return R.drawable.bebe_semana_12;
            case 13:
                return R.drawable.bebe_semana_13;
            case 14:
                return R.drawable.bebe_semana_14;
            case 15:
                return R.drawable.bebe_semana_15;
            case 16:
                return R.drawable.bebe_semana_16;
            case 17:
                return R.drawable.bebe_semana_17;
            case 18:
                return R.drawable.bebe_semana_18;
            case 19:
                return R.drawable.bebe_semana_19;
            case 20:
                return R.drawable.bebe_semana_20;
            case 21:
                return R.drawable.bebe_semana_21;
            case 22:
                return R.drawable.bebe_semana_22;
            case 23:
                return R.drawable.bebe_semana_23;
            case 24:
                return R.drawable.bebe_semana_24;
            case 25:
                return R.drawable.bebe_semana_25;
            case 26:
                return R.drawable.bebe_semana_26;
            case 27:
                return R.drawable.bebe_semana_27;
            case 28:
                return R.drawable.bebe_semana_28;
            case 29:
                return R.drawable.bebe_semana_29;
            case 30:
                return R.drawable.bebe_semana_30;
            case 31:
                return R.drawable.bebe_semana_31;
            case 32:
                return R.drawable.bebe_semana_32;
            case 33:
                return R.drawable.bebe_semana_33;
            case 34:
                return R.drawable.bebe_semana_34;
            case 35:
                return R.drawable.bebe_semana_35;
            case 36:
                return R.drawable.bebe_semana_36;
            case 37:
                return R.drawable.bebe_semana_37;
            case 38:
                return R.drawable.bebe_semana_38;
            case 39:
                return R.drawable.bebe_semana_39;
            case 40:
                return R.drawable.bebe_semana_40;
            default:
                return R.drawable.bebe_semana_40;
        }
    }

    private String getMensagemParaSemana(int semana) {
        switch (semana) {
            case 1:
                return "Seu bebê está começando a se formar. A fecundação aconteceu recentemente e o embrião está se desenvolvendo.";
            case 2:
                return "O embrião é pequeno, do tamanho de uma semente de feijão. Os primeiros órgãos começam a se formar.";
            case 3:
                return "Seu bebê tem o tamanho de uma lentilha. O coração e outros órgãos vitais estão se desenvolvendo.";
            case 4:
                return "O bebê tem o tamanho de uma ameixa. As extremidades, como braços e pernas, estão começando a se formar.";
            case 5:
                return "Agora do tamanho de uma azeitona, seu bebê tem mais detalhes em seu corpo, como dedos e unhas.";
            case 6:
                return "Seu bebê é do tamanho de um limão. Todos os órgãos principais já estão formados, e o bebê já tem movimento.";
            case 7:
                return "Agora do tamanho de uma laranja, o bebê começa a engolir e fazer movimentos mais coordenados.";
            case 8:
                return "O bebê tem o tamanho de uma maçã. As articulações estão se desenvolvendo e o bebê já pode se mover com mais agilidade.";
            case 9:
                return "Do tamanho de um pepino, seu bebê começa a ouvir sons e já tem o sistema nervoso mais desenvolvido.";
            case 10:
                return "Agora do tamanho de uma banana grande, o bebê já possui cabelo e pode até fazer caretas!";
            case 11:
                return "Seu bebê tem o tamanho de uma berinjela. O bebê está começando a mover os olhos.";
            case 12:
                return "Agora do tamanho de uma couve-flor, o bebê está se preparando para um grande crescimento.";
            case 13:
                return "O bebê está do tamanho de uma maçã. Ele já tem feições mais definidas.";
            case 14:
                return "O bebê está do tamanho de um pêssego. Seu sistema nervoso está mais desenvolvido.";
            case 15:
                return "O bebê é do tamanho de uma pera. O cérebro começa a se desenvolver mais rapidamente.";
            case 16:
                return "Agora do tamanho de uma maçã, o bebê pode mover os braços e pernas.";
            case 17:
                return "Do tamanho de uma beterraba, o bebê está criando músculos e ossos.";
            case 18:
                return "O bebê tem o tamanho de uma maçã. O bebê começa a ouvir sons do mundo externo.";
            case 19:
                return "Agora do tamanho de uma banana, o bebê pode abrir e fechar os olhos.";
            case 20:
                return "Do tamanho de uma melancia pequena, o bebê já possui cabelo.";
            case 21:
                return "O bebê é do tamanho de uma cenoura. Seu sistema digestivo está em desenvolvimento.";
            case 22:
                return "Agora do tamanho de uma laranja, o bebê está praticando movimentos respiratórios.";
            case 23:
                return "Do tamanho de um pimentão, o bebê tem o sistema circulatório quase pronto.";
            case 24:
                return "Agora do tamanho de uma abóbora, o bebê está crescendo rapidamente!";
            case 25:
                return "O bebê tem o tamanho de uma beterraba. Seus ossos estão ficando mais fortes.";
            case 26:
                return "Do tamanho de uma laranja, os pulmões continuam se desenvolvendo.";
            case 27:
                return "Agora do tamanho de um pepino, o bebê pode se mover e reagir mais rapidamente.";
            case 28:
                return "O bebê tem o tamanho de uma melancia. Ele está se preparando para o nascimento.";
            case 29:
                return "Agora do tamanho de um melão, o bebê está ganhando mais peso.";
            case 30:
                return "O bebê tem o tamanho de uma abóbora. Os olhos estão mais sensíveis à luz.";
            case 31:
                return "Do tamanho de um abacate, o bebê continua crescendo e se preparando para o parto.";
            case 32:
                return "O bebê é do tamanho de uma melancia. Ele está quase pronto para o mundo externo.";
            case 33:
                return "Agora do tamanho de um caqui, o bebê está desenvolvendo seus pulmões e coração.";
            case 34:
                return "O bebê tem o tamanho de um coco. O desenvolvimento está se acelerando.";
            case 35:
                return "Agora do tamanho de uma batata-doce, o bebê está se preparando para o parto.";
            case 36:
                return "O bebê é do tamanho de um abacaxi. Está quase pronto para nascer.";
            case 37:
                return "Agora do tamanho de uma melancia, o bebê está se ajustando para o nascimento.";
            case 38:
                return "O bebê é do tamanho de uma abóbora grande. Os órgãos estão completamente formados.";
            case 39:
                return "Agora do tamanho de uma alface/abacaxi, o bebê está pronto para nascer a qualquer momento.";
            case 40:
                return "O bebê chegou ao seu tamanho final e está pronto para nascer!";
            default:
                return "A gestação passou de 40 semanas.";
        }
    }
}
