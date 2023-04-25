package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result_Text, solution_Text;
    MaterialButton button_Open,button_Close, button_C;
    MaterialButton button_Div,button_Mul, button_Sub, button_Add,button_Equal;
    MaterialButton button_7,button_8,button_9,button_4,button_5,button_6,button_1,button_2,button_3,
    button_00,button_0,button_Dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_Text=findViewById(R.id.result_text);
        solution_Text=findViewById(R.id.solution_text);

        assignId(button_Open, R.id.button_open);
        assignId(button_Close, R.id.button_close);
        assignId(button_C, R.id.button_c);
        assignId(button_Div, R.id.button_div);
        assignId(button_Mul,R.id.button_mul);
        assignId(button_Sub,R.id.button_sub);
        assignId(button_Add,R.id.button_add);
        assignId(button_Equal,R.id.button_equal);
        assignId(button_7,R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_1,R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_00, R.id.button_00);
        assignId(button_0,R.id.button_0);
        assignId(button_Dot,R.id.button_dot);



    }

    void assignId(MaterialButton btn,int id){
        btn= findViewById(id);
        btn.setOnClickListener(this);

    }

    // #181717
    //#262525
    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText =button.getText().toString();
        String dataTocalculate = solution_Text.getText().toString();

        if(buttonText.equals("AC")){
            solution_Text.setText("");
            result_Text.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solution_Text.setText(result_Text.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataTocalculate =dataTocalculate.substring(0,dataTocalculate.length()-1);
        }else{
            dataTocalculate= dataTocalculate+buttonText;
        }
        solution_Text.setText(dataTocalculate);
        String finalResult = getResult(dataTocalculate);

        if(!finalResult.equals("Err")){
            result_Text.setText(finalResult);

        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "JavaScript",1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult= finalResult.replace(".0", "");
            }
            return finalResult;
        }catch(Exception e){
            return "Err";
        }
    }


}