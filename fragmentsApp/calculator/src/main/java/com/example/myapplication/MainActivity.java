package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    double num1 = 0;
    double num2 = 0;
    String operand = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewResult);
    }

    public void buttonClicked(View view) {
        Button button = (Button) view;
        textView.append(button.getText().toString());
    }

    public void operandClicked(View view) {
        Button button = (Button) view;
        operand = button.getText().toString();
        num1 = Double.parseDouble(textView.getText().toString());
        textView.setText("");
        // TODO: highlight the chosen operand button
    }

    public String numFormat(double num) {
        NumberFormat nf = new DecimalFormat("##.###");
        return (nf.format(num));
    }

    public void clearView(View view) {
        num1 = 0;
        num2 = 0;
        textView.setText("");
    }

    public void showResult(View view) {
        // Button button = (Button) view;
        // textView.append(button.getText().toString());

        num2 = Double.parseDouble(textView.getText().toString());

        switch (operand) {
            case "+":
                textView.setText(numFormat(num1 + num2));
                break;

            case "-":
                textView.setText(numFormat(num1 - num2));
                break;

            case "X":
                textView.setText(numFormat(num1 * num2));
                break;

            case "/":
                textView.setText(numFormat(num1 / num2));
                break;

            case "" :
                break;
        }
    }
}