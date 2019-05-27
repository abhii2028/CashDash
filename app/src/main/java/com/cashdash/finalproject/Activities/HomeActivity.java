package com.cashdash.finalproject.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cashdash.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity {

    ConstraintLayout numberLayout,codeLayout;
    EditText otpPhone,otpVerify;
    private FirebaseAuth mAuth;
    private final String TAG="auth";
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String verificationcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_phonenumber);

        numberLayout = findViewById(R.id.numberLayout);
        codeLayout = findViewById(R.id.codelayout);
        otpPhone = findViewById(R.id.otpPhone);
        otpVerify = findViewById(R.id.otpverify);

        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                verificationcode = verificationId;
                Toast.makeText(HomeActivity.this, "sent", Toast.LENGTH_SHORT).show();
            }
        };


    }

    public void numberinsert(View view)
    {
        numberLayout.setVisibility(View.GONE);
        codeLayout.setVisibility(View.VISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + otpPhone.getText().toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }


    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = task.getResult().getUser();

                        } else {

                        }
                    }
                });
    }

    public void verified(View view)
    {
        String otpCode = otpVerify.getText().toString();
        verifyPhoneNumber(verificationcode,otpCode);
        startActivity(new Intent(HomeActivity.this, MapActivity.class));
    }

    public void verifyPhoneNumber(String verificationcode, String otpCode) {
        PhoneAuthCredential credential =  PhoneAuthProvider.getCredential(verificationcode,otpCode);
        signInWithPhoneAuthCredential(credential);
    }


}
