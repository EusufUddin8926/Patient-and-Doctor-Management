package com.example.hackathonviewpagerlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PatientFragment extends Fragment implements View.OnClickListener {
    private EditText SignInEmail,SignInPassword;
    private Button SignInButton;
    private TextView SignUpTextView;
    Context context;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_patient, container, false);

        mAuth = FirebaseAuth.getInstance();
        SignInEmail = v.findViewById(R.id.SignINEmail);
        SignInPassword = v.findViewById(R.id.SignInPassword);
        SignInButton = v.findViewById(R.id.SignInBtn);
        SignUpTextView = v.findViewById(R.id.SignUpTxtview);

        SignInButton.setOnClickListener(this);
        SignUpTextView.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SignInBtn:
                handlelogin();
                break;
            case R.id.SignUpTxtview:
                Intent intent = new Intent(getContext(),SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void handlelogin() {
        String email = SignInEmail.getText().toString().trim();
        String password = SignInPassword.getText().toString().trim();

        if(email.isEmpty()){
            SignInEmail.setError("Enter Your Email Address");
            SignInEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            SignInEmail.setError("Enter Your valid Email Address");
            SignInEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            SignInPassword.setError("Enter Your Password");
            SignInPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            SignInPassword.setError("Enter your Password with minimum 6 Character");
            SignInPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                 Intent intent = new Intent(getContext(), PatientDashBoardActivity.class);
                 startActivity(intent);
                  Toast.makeText(getContext(), "Login Successful !", Toast.LENGTH_SHORT).show();

              }
              else{
                  Toast.makeText(getContext(), "Some Error Occured"+task.getException(), Toast.LENGTH_SHORT).show();
              }
            }
        });

    }
}
