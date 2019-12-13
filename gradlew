package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button clear,minus,plus,mod,mul,divide,equals,del,num1,num2,num3,num4,num5,num6,num7,num8,num9,numdot,num0,sign;
    TextView res,oper;
    View.OnClickListener numListener,opListener;
    float resNum=0;
    char operation = 'n';
    boolean disabledFlag=false;
    boolean equalFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res   = findViewById(R.id.result);
        clear = findViewById(R.id.btn_clear);
        minus = findViewById(R.id.btn_minus);
        plus  = findViewById(R.id.btn_plus);
        mod = findViewById(R.id.btn_mod);
        mul = findViewById(R.id.btn_mul);
        divide = findViewById(R.id.btn_divide);
        equals = findViewById(R.id.btn_equals);
        del = findViewById(R.id.btn_del);
        num1 = findViewById(R.id.btn_1);
        num2 = findViewById(R.id.btn_2);
        num3 = findViewById(R.id.btn_3);
        num4 = findViewById(R.id.btn_4);
        num5 = findViewById(R.id.btn_5);
        num6 = findViewById(R.id.btn_6);
        num7 = findViewById(R.id.btn_7);
        num8 = findViewById(R.id.btn_8);
        num9 = findViewById(R.id.btn_9);
        num0 = findViewById(R.id.btn_0);
        numdot = findViewById(R.id.btn_dot);
        sign = findViewById(R.id.btn_sign);
        oper = findViewById(R.id.operate);
        numListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(res.getText().toString().equalsIgnoreCase("Infinity")){
                    res.setText("");
                    return;
                }
                if(disabledFlag){
                    disabledFlag = false;
                    setAllEnabled();
                }
                switch (view.getId()){
                    case R.id.btn_1:
                        setResText("1");
                        break;
                    case R.id.btn_2:
                        setResText("2");
                        break;
                    case R.id.btn_3:
                        setResText("3");
                        break;
                    case R.id.btn_4:
                        setResText("4");
                        break;
                    case R.id.btn_5:
                        setResText("5");
                        break;
                    case R.id.btn_6:
                        setResText("6");
                        break;
                    case R.id.btn_7:
                        setResText("7");
                        break;
                    case R.id.btn_8:
                        setResText("8");
                        break;
                    case R.id.btn_9:
                        setResText("9");
                        break;
                    case R.id.btn_0:
                        setResText("0");
                        break;
                    case R.id.btn_dot:
                        setResText(".");
                        break;
                }
            }
        };
        opListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(res.getText().toString().equalsIgnoreCase("Infinity")){
                    res.setText("");
                    return;
                }
                switch (view.getId()){
                    case R.id.btn_plus:
                        performOp('+');
                        oper.setText("+");
                        break;
                    case R.id.btn_minus:
                        performOp('-');
                        oper.setText("-");
                        break;
                    case R.id.btn_mul:
                        performOp('*');
                        oper.setText("*");
                        break;
                    case R.id.btn_divide:
                        performOp('/');
                        oper.setText("/");
                        break;
                    case R.id.btn_mod:
                        performOp('%');
                        oper.setText("%");
                        break;
                }

            }
        };
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(disabledFlag){
                    disabledFlag = false;
                    setAllEnabled();

                }
                oper.setText("");
                operation = 'n';
                resNum = 0;
                res.setText("0");
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOp(operation);
                operation = 'n';
                equalFlag = true;
                oper.setText