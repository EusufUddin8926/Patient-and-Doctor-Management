package com.example.hackathonviewpagerlogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AppointmentActivity extends AppCompatActivity {
    private EditText nameEditText,problemEditText,ageEditText,phoneEditText,locationEditText;
    private TextView departmentNameTextView,departmentTypeTextView;
    private String department;
    private String doctorName;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        this.setTitle("Appoinment Activity");
        nameEditText = findViewById(R.id.appointmentNameId);
        phoneEditText = findViewById(R.id.appointmentPhoneId);
        problemEditText = findViewById(R.id.appointmentProblemNameId);
        ageEditText = findViewById(R.id.appointmentAgeId);
        locationEditText = findViewById(R.id.appointmentLocationId);
        departmentNameTextView = findViewById(R.id.appointmentDepartmentNameId);
        departmentTypeTextView = findViewById(R.id.appointmentDepartmentTypeId);

        departmentTypeTextView.setText("Department Name: "+getIntent().getStringExtra("departmentName"));
        departmentNameTextView.setText("Doctor Name: "+getIntent().getStringExtra("doctorName"));
        department = getIntent().getStringExtra("departmentName");
        doctorName = getIntent().getStringExtra("doctorName");

        dataBaseHelper = new DataBaseHelper(AppointmentActivity.this);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    }

    public void onClick(View view) {
        if(view.getId()==R.id.appointmentSubmitId){
            String dName = doctorName;
            String dDepartment = department;
            String pName = nameEditText.getText().toString();
            String pPhone = phoneEditText.getText().toString();
            String pAge = ageEditText.getText().toString();
            String pLocation = locationEditText.getText().toString();
            String pProblem = problemEditText.getText().toString();
            if(nameEditText.getText().toString()==""||phoneEditText.getText().toString()==""||
                    problemEditText.getText().toString()==""||ageEditText.getText().toString()==""||locationEditText.getText().toString()==""){
                Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show();
            }
            else{
                insertData(dName,dDepartment,pName,pPhone,pAge,pLocation,pProblem);
                nameEditText.setText("");
                phoneEditText.setText("");
                problemEditText.setText("");
                ageEditText.setText("");
                locationEditText.setText("");
            }

        }
        else if(view.getId()==R.id.appointmentCancelId){
            Intent intent = new Intent(AppointmentActivity.this,DetailsActivity.class);
            intent.putExtra("dep","skin");
            startActivity(intent);
            finish();
        }
    }
    private void insertData(String dName, String dDepartment, String pName,String pPhone,
                            String pAge,String pLocation,String pProblem ){
       long l =  dataBaseHelper.insert(dName,dDepartment,pName,pPhone,pAge,pLocation,pProblem);

       if(l<=0){
           Toast.makeText(this, "Appoinment not inserted", Toast.LENGTH_SHORT).show();
       }
       else{
           Toast.makeText(this, "Appointment Successfully Done", Toast.LENGTH_LONG).show();
       }

    }
}