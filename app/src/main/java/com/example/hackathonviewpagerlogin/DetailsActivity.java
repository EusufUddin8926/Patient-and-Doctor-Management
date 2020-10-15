package com.example.hackathonviewpagerlogin;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements ClickInterface{

    private TextView textView;
    private List<ModelDoctor> doctors;
    private String[] doctorsName;
    private String[] doctorsEmail;
    private String[] doctorsPhone;
    private String[] doctorsDesignation;
    private int[] doctorImages;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private String department;
    private EditText emailSubText,emailMassageText;
    public static final int REQUEST_CALL=1;
    private TextView doctorInfoNameText,doctorInfoDesignationText,doctorInfoPhoneText,doctorInfoEmailText,doctorInfoTimeText;
    private ImageView doctorImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textView = findViewById(R.id.detailsDepartmentTextID);
        String type = getIntent().getStringExtra("dep");
        this.setTitle(type+" Department");

        department=type;
        textView.setText(department);
        recyclerView= findViewById(R.id.detailsRecycleViewId);

        doctors = new ArrayList<>();

        if(type.equals("Skin")){
            doctorsName = getResources().getStringArray(R.array.skin_doctor_name);
            doctorsEmail = getResources().getStringArray(R.array.skin_doctor_email);
            doctorsPhone = getResources().getStringArray(R.array.skin_doctor_phone);
            doctorsDesignation = getResources().getStringArray(R.array.skin_doctor_designation);
            doctorImages = new int[]{R.drawable.skin_hasib, R.drawable.skin_nas, R.drawable.skin_rahim};

        }
        else if(type.equals("Child")){
            doctorsName = getResources().getStringArray(R.array.child_doctor_name);
            doctorsEmail = getResources().getStringArray(R.array.child_doctor_email);
            doctorsPhone = getResources().getStringArray(R.array.child_doctor_phone);
            doctorsDesignation = getResources().getStringArray(R.array.child_doctor_designation);
            doctorImages = new int[]{R.drawable.child_abbas,R.drawable.child_samia,R.drawable.skin_rahim};

        }else if(type.equals("Bone")){
            doctorsName = getResources().getStringArray(R.array.bone_doctor_name);
            doctorsEmail = getResources().getStringArray(R.array.bone_doctor_email);
            doctorsPhone = getResources().getStringArray(R.array.bone_doctor_phone);
            doctorsDesignation = getResources().getStringArray(R.array.bone_doctor_designation);
            doctorImages = new int[]{R.drawable.bone_amir,R.drawable.bone_hamja,R.drawable.bone_rahima};

        }else if(type.equals("Heart")){
            doctorsName = getResources().getStringArray(R.array.heart_doctor_name);
            doctorsEmail = getResources().getStringArray(R.array.heart_doctor_email);
            doctorsPhone = getResources().getStringArray(R.array.heart_doctor_phone);
            doctorsDesignation = getResources().getStringArray(R.array.heart_doctor_designation);
            doctorImages = new int[]{R.drawable.heart_anita,R.drawable.heart_nowsin,R.drawable.heart_zara};

        }else if(type.equals("Emergency")){
            doctorsName = getResources().getStringArray(R.array.emergency_doctor_name);
            doctorsEmail = getResources().getStringArray(R.array.emergency_doctor_email);
            doctorsPhone = getResources().getStringArray(R.array.emergency_doctor_phone);
            doctorsDesignation = getResources().getStringArray(R.array.emergency_doctor_designation);
            doctorImages = new int[]{R.drawable.emergency_anil,R.drawable.emergency_hasan,R.drawable.emergency_rahat};

        }else if(type.equals("Corona")){
            doctorsName = getResources().getStringArray(R.array.corona_doctor_name);
            doctorsEmail = getResources().getStringArray(R.array.corona_doctor_email);
            doctorsPhone = getResources().getStringArray(R.array.corona_doctor_phone);
            doctorsDesignation = getResources().getStringArray(R.array.corona_doctor_designation);
            doctorImages = new int[]{R.drawable.corona_fahmida,R.drawable.corona_mim,R.drawable.corona_sadia};

        }
        for (int i = 0; i<doctorImages.length; i++){
            ModelDoctor model = new ModelDoctor(doctorsName[i],doctorsDesignation[i],doctorsPhone[i],doctorsEmail[i],doctorImages[i]);
            doctors.add(model);
        }

        adapter = new MyAdapter(doctors,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.doctor_info_show,null);
        doctorInfoNameText = view.findViewById(R.id.doctorInfoNameTextId);
        doctorInfoDesignationText = view.findViewById(R.id.doctorInfoDesignationTextId);
        doctorInfoPhoneText = view.findViewById(R.id.doctorInfoPhoneTextId);
        doctorInfoEmailText = view.findViewById(R.id.doctorInfoEmailTextId);
        doctorInfoTimeText = view.findViewById(R.id.doctorInfoTimeTextId);
        doctorImage = view.findViewById(R.id.doctorInfoImageId);

        doctorImage.setImageResource(doctorImages[position]);
        doctorInfoNameText.setText("Name : "+doctorsName[position]);
        doctorInfoDesignationText.setText("Designation: "+doctorsDesignation[position]);
        doctorInfoPhoneText.setText("Phone: "+doctorsPhone[position]);
        doctorInfoEmailText.setText("Email: "+doctorsEmail[position]);
        String time = ""+(11-position)+":30 am";
        doctorInfoTimeText.setText(time);

        builder.setView(view).setCancelable(true).create().show();


    }

    @Override
    public void onLongItemClick(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        String[] info ={"Book Appointment","Email","Call"};
        builder.setTitle("to contact with doctor").setItems(info, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    Intent intent = new Intent(DetailsActivity.this,AppointmentActivity.class);
                    intent.putExtra("doctorName",doctorsName[position]);
                    intent.putExtra("departmentName",department);
                    startActivity(intent);
                }
                else if(which==1){
                    makeEmail(doctorsEmail[position]);

                }else if(which==2){
                    makePhone(doctorsPhone[position]);
                }
            }
        }).setCancelable(true).create().show();
    }



    private void makeEmail(final String address) {

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.email_layout,null);
        builder.setView(view).setCancelable(true)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        emailSubText = view.findViewById(R.id.subjectEditText);
                        emailMassageText = view.findViewById(R.id.massageEditId);
                        String to = address;
                        String[] address = to.split(",");
                        String subject = emailSubText.getText().toString();
                        String message = emailMassageText.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                        intent.putExtra(Intent.EXTRA_EMAIL,address);
                        intent.putExtra(Intent.EXTRA_TEXT,message);
                        intent.setType("massage/rfc822");
                        startActivity(Intent.createChooser(intent,"select application"));
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    private void makePhone(String number) {
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(DetailsActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else {
                String dial = "tel:"+number;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(dial));
                startActivity(intent);
            }
        }
        else{
            Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show();
        }

    }
}