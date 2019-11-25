package com.quest.smsverify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private final String TAG ="mwenda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

        SmsRetrieverClient client = SmsRetriever.getClient(this /* context */);
    }



    public void verifyUserNumber(){
        // Get an instance of SmsRetrieverClient, used to start listening for a matching
        // SMS message.
                SmsRetrieverClient client = SmsRetriever.getClient(this /* context */);

        // Starts SmsRetriever, which waits for ONE matching SMS message until timeout
        // (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
        // action SmsRetriever#SMS_RETRIEVED_ACTION.
                Task<Void> task = client.startSmsRetriever();

        // Listen for success/failure of the start Task. If in a background thread, this
        // can be made blocking using Tasks.await(task, [timeout]);
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Successfully started retriever, expect broadcast intent
                        // ...
                        Toast.makeText(MainActivity.this, "started the listener", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onSuccess: successfully started receiver");
                    }
                });

                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to start retriever, inspect Exception for more details
                        // ...
                        Toast.makeText(MainActivity.this, "listener failed to start", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: receiver didnt start");
                    }
                });
    }

    public void verifyPhone(View view) {
        EditText phonetxt = findViewById(R.id.editText);
        String phone = phonetxt.getText().toString().trim();
        if(!TextUtils.isEmpty(phone)){
            //send phone to server to request OTP
            verifyUserNumber();
        }

    }
}
