package com.quest.smsverify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import in.arjsna.passcodeview.PassCodeView;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PassCodeView passCodeView = (PassCodeView) findViewById(R.id.pass_code_view);

        passCodeView.setKeyTextColor(R.color.black_shade);
        passCodeView.setEmptyDrawable(R.drawable.empty_dot);
        passCodeView.setFilledDrawable(R.drawable.filled_dot);

        passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                Log.i("Passcode", text);
            }
        });

    }
}
