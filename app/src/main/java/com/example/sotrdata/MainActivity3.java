package com.example.sotrdata;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private Button selectImageButton,uploadButton,showButton;
    private EditText editText;
    private ImageView imageView;
    private Uri imageUri;
    private static final int IMAGE_REQUEST = 1;
    //DatabaseReference databaseReference;
    //StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

      //  databaseReference = FirebaseDatabase.getInstance().getReference("upload");
       // storageReference = storageReference.getStorage().getReference("upload");

        selectImageButton = findViewById(R.id.selectImageButtonId);
        editText = findViewById(R.id.editTextId);
        imageView = findViewById(R.id.imageViewId);
        uploadButton = findViewById(R.id.uploadButtonId);
        showButton = findViewById(R.id.showButtonId);

        selectImageButton.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        showButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.selectImageButtonId) {
            selectImage();
        } else if (view.getId() == R.id.uploadButtonId) {
            uploadImage();
        } else if (view.getId() == R.id.showButtonId) {
            showImage();
        }
    }

    private void showImage() {

    }
    /*
//Getting File extention like- (.jpg, .png)
    public String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    */

    private void uploadImage() {

    /*
        String imageName = editText.getText().toString().trim();
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            // Create a reference to "mountains.jpg"
            StorageReference mountainsRef = fileReference.child("mountains.jpg");

// Create a reference to 'images/mountains.jpg'
            StorageReference mountainImagesRef = fileReference.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
            mountainsRef.getName().equals(mountainImagesRef.getName());    // true
            mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
        }
        */

    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            }

    }
}