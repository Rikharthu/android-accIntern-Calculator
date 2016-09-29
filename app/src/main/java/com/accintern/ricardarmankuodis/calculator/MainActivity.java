package com.accintern.ricardarmankuodis.calculator;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private int a;
    private int b ;
    private int rez;
    private boolean aSet;
    private boolean bSet;
    private int operation;
    private boolean refresh;
    private Operation op;

    @BindView(R.id.output)
    TextView mOutputTextView;

    @BindViews({ R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5
            ,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9
            ,R.id.btnDel,R.id.btnDiv,R.id.btnDot,R.id.btnMin,R.id.btnPlus,R.id.btnRes})
    List<Button> mButtons;

    final ButterKnife.Action<View> SET_LISTENER = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setOnClickListener(MainActivity.this);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ButterKnife.apply(mButtons, SET_LISTENER);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mOutputTextView.setElevation(10);
        }


    }

    public void onButtonClicked(View view) {

    }

    private String replaceLastInputChar(String text, String character){
        return text.substring(0,getInputText().length()-2)+character;
    }

    /** check if last input charact ir operation sign */
    private boolean isOperation(String input){
        if(input.equals("+")||input.equals("-")||input.equals("÷")||input.equals("×")||input.equals("=")){
            return true;
        }
        return false;
    }

    private String getInputText(){
        return mOutputTextView.getText().toString();
    }

    private void setOutputText(String str){
        mOutputTextView.setText(str);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        String pressedStr = btn.getText().toString();

        if(pressedStr.equals("del")) {
            a=0; b=0; aSet=false; bSet=false;
            mOutputTextView.setText("");
            return;
        }

        String input = getInputText();

        if(isOperation(pressedStr)){
            switch(pressedStr){
                case "+":
                    Log.d(TAG,"operation = plus");
                    op=Operation.PLUS;
                    break;
                case "-":
                    Log.d(TAG,"operation = minus");
                    op=Operation.MINUS;
            }

            if(!aSet && !input.isEmpty()){
                a=toInt(input);
                aSet=true;
                Log.d(TAG,"a is set to "+a);
            }else if(!bSet && !input.isEmpty()){
                b=toInt(input);
                bSet=true;
                Log.d(TAG,"b is set to "+b);
            }
            setOutputText("");

        }else{
            // digit entered
            if(refresh){
                setOutputText(pressedStr);
                refresh=false;
            }else
            setOutputText(getInputText()+pressedStr);
        }

        if(pressedStr.equals("=")||(bSet&&aSet)){
            Log.d(TAG,"a="+a+" b="+b);
            int rez=a+b;
            a=0; b=0; aSet=false; bSet=false;
            mOutputTextView.setText(rez+"");
            refresh=true;
            return;
        }

    }

    private int toInt(String str){
        return Integer.parseInt(str);
    }

    private enum Operation{
        PLUS, MINUS;
    }

}
