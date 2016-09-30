package com.accintern.ricardarmankuodis.calculator;

import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
    private String op;
    List<String> logEntries;

    ArrayAdapter<String>  mArrayAdapter;

    @BindView(R.id.output)
    TextView mOutputTextView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
//    @BindView(R.id.drawerRecyclerView)
//    RecyclerView mDrawerRecycler;
//    LogRecyclerAdapter mAdapter;

    @BindView(R.id.left_drawer)
    ListView mDrawerListView;

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

        logEntries=new ArrayList<String>();
        logEntries.add("test");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Set the adapter for the list view
        mArrayAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, logEntries);
        mDrawerListView.setAdapter(mArrayAdapter);

//        mAdapter=new LogRecyclerAdapter(logEntries);
//        mDrawerRecycler.setLayoutManager(new LinearLayoutManager(this));
////
//        mDrawerRecycler.setAdapter(mAdapter);

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
        if(input.equals("+")||input.equals("-")||input.equals("÷")||input.equals("×")){
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
            a=0; b=0; rez=0;
            mOutputTextView.setText("");
            return;
        }

        String input = getInputText();

        if(isOperation(pressedStr)) {
            // get user input
            a=toInt(input);

            // refresh screen for next number
            setOutputText("");

            // capture operation
            op=pressedStr;

        }else if(pressedStr.equals("=")){
            // perform calculation
            b=toInt(input);
            String logMsg="";
            switch(op){
                case "+":
                    rez=a+b;
                    logMsg=a+"+"+b+"="+rez;
                    break;
                case "-":
                    rez=a-b;
                    logMsg=a+"-"+b+"="+rez;
                    break;
            }
            setOutputText(rez+"");
            // refresh fields
            // a=0;
            b=0; rez=0;
            // TODO append to log
            logEntries.add(logMsg);
            mArrayAdapter.notifyDataSetChanged();
//            mAdapter.notifyDataSetChanged();
            Log.d(TAG,logMsg);
            // TODO maybe replace string with stringbuffer
        }else{
            // digit
            setOutputText(input+pressedStr);
        }

    }

    private int toInt(String str){
        return Integer.parseInt(str);
    }

}
