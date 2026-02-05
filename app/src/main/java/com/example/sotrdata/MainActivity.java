package com.example.sotrdata;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.Manifest;

public class MainActivity extends AppCompatActivity {
    private Button saveButton,showButton,nextButton;
    private EditText name,age;

    private ProgressBar loginProgressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String readMediaVideo = Manifest.permission.READ_MEDIA_VIDEO;
    String readMediaImages = Manifest.permission.READ_MEDIA_IMAGES;
    String readMeadiaAudio = Manifest.permission.READ_MEDIA_AUDIO;
    String postNotifications = Manifest.permission.POST_NOTIFICATIONS;
    String foregroundService = Manifest.permission.RECORD_AUDIO;
    String manageExternalStorage = Manifest.permission.MANAGE_EXTERNAL_STORAGE;



    private static final int AUDIO_PERMISSION_REQUEST_CODE = 1;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityCompat.requestPermissions(this, new String[]{readMeadiaAudio,readMediaImages,readMediaVideo,postNotifications,foregroundService,manageExternalStorage}, AUDIO_PERMISSION_REQUEST_CODE);


        saveButton = findViewById(R.id.SaveButtonId);
        name = findViewById(R.id.NameId);
         age = findViewById(R.id.AgeId);
        showButton = findViewById(R.id.ShowButtonId);
        nextButton = findViewById(R.id.NextButtonId);
        loginProgressBar = findViewById(R.id.loginProgressbarId);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Information");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });


    }

    public void saveData()
    {
        String Name = name.getText().toString().trim();
        String Age = age.getText().toString().trim();

        String key = databaseReference.push().getKey();

        Student student = new Student(Name,Age);

        databaseReference.child(key).setValue(student);
        Toast.makeText(this, "Data Add", Toast.LENGTH_SHORT).show();
        name.setText("");
        age.setText("");

    }
}