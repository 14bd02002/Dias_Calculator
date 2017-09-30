package dias.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView _screen;
    private String display = "";
    private String currentOperator = "";
    private String first_term = "", second_term = "";
    private Double result=0.0;
    private Boolean lastOperation = false, first = false, second = false;
    private static final String TAG = "Android Lifecycle";


    private void updateScreen(){
        _screen.setText(display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = (TextView)findViewById(R.id.main_text);

        if (savedInstanceState != null){
            display = savedInstanceState.getString("text");
            first_term = savedInstanceState.getString("first_term");
            second_term = savedInstanceState.getString("second_term");
            currentOperator = savedInstanceState.getString("currentOperator");
            first = savedInstanceState.getBoolean("first");
            second = savedInstanceState.getBoolean("second");
            lastOperation = savedInstanceState.getBoolean("lastOperation");
            updateScreen();
        }
    }

    public void OnClickNumber(View v) {
        Button b = (Button) v;
        if (lastOperation){
            display = b.getText().toString();
            lastOperation = false;
        }
        else{
            display += b.getText();
        }
        updateScreen();
    }

    private void clear(){
        display = "";
        currentOperator = "";
        first_term = "";
        second_term = "";
        first = false;
        second = false;
        lastOperation = false;
    }

    public void OnClickClear(View v) {
        clear();
        updateScreen();
    }

    public void OnClickOperator(View v) {
        Button b = (Button) v;
        lastOperation = true;
        currentOperator = b.getText().toString();
        if (!first){
            first_term = display;
            first = true;
            display = b.getText().toString();
        }
        else if (first && !lastOperation && !second){
            second_term = display;
            result = operate(first_term, second_term, currentOperator);
            first_term = result.toString();
            display = b.getText().toString();
        }
        else{
            display = currentOperator;
        }
        updateScreen();
    }

    private double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "*": return Double.valueOf(a) * Double.valueOf(b);
            case "/": return Double.valueOf(a) / Double.valueOf(b);
            default: return -1;
        }
    }

    public void OnClickEqual(View v) {
        if (first && !lastOperation){
            second_term = display;
            result = operate(first_term, second_term, currentOperator);
            first_term = result.toString();
            second = false;
        }
        display = first_term;
        lastOperation = false;
        updateScreen();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("text", _screen.getText().toString());
        outState.putString("first_term", first_term);
        outState.putString("second_term", second_term);
        outState.putString("currentOperator", currentOperator);
        outState.putBoolean("first", first);
        outState.putBoolean("second", second);
        outState.putBoolean("lastOperation", lastOperation);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        Log.d(TAG, "onDestroy");
    }
}