package br.com.progiv.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //objetos formatadores de moeda:
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    private double vrConta = 0.0; //valor da conta inserido pelo usuario
    private double percent = 0.15; //porcentagem inicial da gorjeta
    private TextView valorContaTextView; // mostra o valor da conta
    private TextView valorGorjetaTextView; //mostra o valor da gorjeta
    private TextView valorTotalTextView; // mostra o valor total da conta calculada
    private TextView textoPorcentagemTextView; // mostra a porcentagem da gorjeta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obter referencias para o TextView manipulados via programação
        valorContaTextView = (TextView)findViewById(R.id.valorConta);
        valorTotalTextView = (TextView)findViewById(R.id.valorTotal);
        valorGorjetaTextView = (TextView)findViewById(R.id.valorGorjeta);
        textoPorcentagemTextView = (TextView)findViewById(R.id.textoPorcentagem);

        //zerando exibição na tela
        valorTotalTextView.setText(currencyFormat.format(0));
        valorGorjetaTextView.setText(currencyFormat.format(0));

        //configura o receptor TextWatcher de valorContaEditText
        EditText valorContaEditText = (EditText)findViewById(R.id.valorConta);
        valorContaEditText.addTextChangedListener(valorContaEditWatcher);

        //configura o receptor OnSeekBarChangeListener de porcentagemBar
        SeekBar percentSeekBar = (SeekBar)findViewById(R.id.porcentagemBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

    }

    private void calcular(){
        try{
            double gorjeta = vrConta * percent;
            double total = vrConta + gorjeta;
            valorTotalTextView.setText(currencyFormat.format(total));
            valorGorjetaTextView.setText(currencyFormat.format(gorjeta));

            textoPorcentagemTextView.setText(Math.round((percent * 100) / 30) + "%");
            //%
        }catch (Exception ex){
            String y = ex.getMessage();
        }
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            calcular();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher valorContaEditWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                vrConta = Double.parseDouble(s.toString());
                valorTotalTextView.setText(currencyFormat.format(vrConta));
            }catch (Exception ex){
                String y = ex.getMessage();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}