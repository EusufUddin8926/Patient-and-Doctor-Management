package com.example.hackathonviewpagerlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class DoctorSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText SignUpEmail,SignUpPassword;
    private Button SignUpButton;
    private TextView SigninTextView;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        this.setTitle("Doctor Registration Activity");

        SignUpEmail = findViewById(R.id.SignUpEmail);
        SignUpPassword = findViewById(R.id.SignUpPassword);
        SignUpButton = findViewById(R.id.SignUpBtn);
        SigninTextView = findViewById(R.id.SignInTxtview);

        SignUpButton.setOnClickListener(this);
        SigninTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SignUpBtn:
                doctorregister();
                break;
            case R.id.SignInTxtview:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void doctorregister() {

        String email = SignUpEmail.getText().toString().trim();
        String Password = SignUpPassword.getText().toString().trim();

        if(email.isEmpty()){
            SignUpEmail.setError("Enter Your Email Address");
            SignUpEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            SignUpEmail.setError("Enter Your valid Email Address");
            SignUpEmail.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            SignUpPassword.setError("Enter Your Password");
            SignUpPassword.requestFocus();
            return;
        }
        if(Password.length()<6){
            SignUpPassword.setError("Enter your Password with minimum 6 Character");
            SignUpPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(DoctorSignUpActivity.this, "Sign Up Is Successful !", Toast.LENGTH_SHORT).show();
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(DoctorSignUpActivity.this, "User is already registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DoctorSignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}