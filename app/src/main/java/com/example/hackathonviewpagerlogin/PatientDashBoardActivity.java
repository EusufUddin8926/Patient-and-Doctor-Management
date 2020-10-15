package com.example.hackathonviewpagerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientDashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_patient);
        setTitle("Department");
    }

    public void onClick(View view) {
        Intent intent = new Intent(PatientDashBoardActivity.this, DetailsActivity.class);
        if(view.getId()==R.id.skin){

            intent.putExtra("dep","Skin");
        }
        else if(view.getId()==R.id.child){

            intent.putExtra("dep","Child");
        }
        else if(view.getId()==R.id.bone){

            intent.putExtra("dep","Bone");
        }
        else if(view.getId()==R.id.heart){

            intent.putExtra("dep","Heart");
        }
        else if(view.getId()==R.id.emergency){

            intent.putExtra("dep","Emergency");
        }
        else if(view.getId()==R.id.corona){

            intent.putExtra("dep","Corona");
        }
        startActivity(intent);
    }
}