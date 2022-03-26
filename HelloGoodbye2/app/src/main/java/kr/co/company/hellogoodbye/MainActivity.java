package kr.co.company.hellogoodbye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int opnd = 0;
    private int operator = 0;
    private int value0 = 0;
    private int value1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtResult = (TextView) findViewById(R.id.text_result);

        ((Button)findViewById(R.id.button_0)).setOnClickListener(
                (v) -> { appendDigit(0); }
        );
        ((Button)findViewById(R.id.button_1)).setOnClickListener(
                (v) -> { appendDigit(1); }
        );
        ((Button)findViewById(R.id.button_2)).setOnClickListener(
                (v) -> { appendDigit(2); }
        );
        ((Button)findViewById(R.id.button_3)).setOnClickListener(
                (v) -> { appendDigit(3); }
        );
        ((Button)findViewById(R.id.button_4)).setOnClickListener(
                (v) -> { appendDigit(4); }
        );
        ((Button)findViewById(R.id.button_5)).setOnClickListener(
                (v) -> { appendDigit(5); }
        );
        ((Button)findViewById(R.id.button_6)).setOnClickListener(
                (v) -> { appendDigit(6); }
        );
        ((Button)findViewById(R.id.button_7)).setOnClickListener(
                (v) -> { appendDigit(7); }
        );
        ((Button)findViewById(R.id.button_8)).setOnClickListener(
                (v) -> { appendDigit(8); }
        );
        ((Button)findViewById(R.id.button_9)).setOnClickListener(
                (v) -> { appendDigit(9); }
        );
        ((Button)findViewById(R.id.button_c)).setOnClickListener(
                (v) -> {
                    opnd = value0 = 0;
                    txtResult.setText(""+opnd);
                }
        );
        ((Button)findViewById(R.id.button_plus)).setOnClickListener(
                (v) -> {
                    operator = 1;
                    value0 = opnd;
                    opnd = 0;
                }
        );
        ((Button)findViewById(R.id.button_sub)).setOnClickListener(
                (v) -> {
                    operator = 2;
                    value0 = opnd;
                    opnd = 0;
                }
        );
        ((Button)findViewById(R.id.button_mul)).setOnClickListener(
                (v) -> {
                    operator = 3;
                    value0 = opnd;
                    opnd = 0;
                }
        );
        ((Button)findViewById(R.id.button_div)).setOnClickListener(
                (v) -> {
                    operator = 4;
                    value0 = opnd;
                    opnd = 0;
                }
        );
        ((Button)findViewById(R.id.button_calc)).setOnClickListener(
                (v) -> {
                    value1 = opnd;
                    switch (operator){
                        case 1:
                            txtResult.setText(""+(value0 + value1));
                            break;
                        case 2:
                            txtResult.setText(""+(value0 - value1));
                            break;
                        case 3:
                            txtResult.setText(""+(value0 * value1));
                            break;
                        case 4:
                            if (value1 == 0){
                                txtResult.setText("DivideByZero");
                            }
                            else if (value0 % value1 == 0){
                                txtResult.setText("" + (value0 / value1));
                            }
                            else{
                                txtResult.setText(""+(value0 / (float)value1));
                            }
                            break;
                    }
                    value0 = value1 = opnd = operator = 0;
                }
        );


    }
    private void appendDigit(int a){
        opnd = opnd*10 + a;
        final TextView txtResult = (TextView)findViewById(R.id.text_result);
        txtResult.setText(""+opnd);
    }

}