package com.accintern.ricardarmankuodis.calculator;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputTextView = (TextView) findViewById(R.id.output);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mOutputTextView.setElevation(10);
        }


    }

    public void onButtonClicked(View view) {
        Button btn = (Button)view;
        String pressedStr = btn.getText().toString();
        mOutputTextView.setText(mOutputTextView.getText()+pressedStr);

        if(pressedStr.equals("del")){
            mOutputTextView.setText("");
        }
    }
}
