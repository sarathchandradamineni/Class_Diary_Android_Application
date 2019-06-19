package com.example.sarath.cseiimdpjntuk;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class share_image extends AppCompatActivity {
    TextView pick_image_from_phone,opencamera;
    EditText image_descrition;
    ImageView image_timeline;
    ProgressBar image_upload_progress;
    Button submit_button;
    Uri mImageUri;
    StorageTask uploadtask;
    public StorageReference storageReference;
    public DatabaseReference databaseReference;
    public static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
        //Toast.makeText(this, "roll is"+LocalDatabase.retrieveName(), Toast.LENGTH_SHORT).show();
        storageReference = FirebaseStorage.getInstance().getReference("Timeline uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("Timeline uploads");
        pick_image_from_phone = (TextView) findViewById(R.id.pick_image);
        opencamera = (TextView) findViewById(R.id.open_camera);
        image_descrition = (EditText) findViewById(R.id.image_description);
        image_timeline = (ImageView) findViewById(R.id.image_upload);
        image_upload_progress = (ProgressBar) findViewById(R.id.progress_uploading);
        submit_button = (Button) findViewById(R.id.submit_image);
        pick_image_from_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uploadtask != null && uploadtask.isInProgress())
                {
                    Toast.makeText(share_image.this, "uploading in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadToFirebase();
                }
            }
        });
    }
    public String getFileExtension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    public void uploadToFirebase()
    {
        if(mImageUri != null)
        {
            StorageReference sf = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            uploadtask = sf.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            image_upload_progress.setProgress(0);
                        }
                    },500);
                    Toast.makeText(share_image.this, "image added to Time Line Successfuly", Toast.LENGTH_SHORT).show();
                   /* HashMap<String,String> hashMap = new HashMap<String, String>();
                    hashMap.put("roll","15026A0501");
                    hashMap.put("name","DAMINENI SARATH CHANDRA");
                    hashMap.put("description",image_descrition.getText().toString().trim());
                    hashMap.put("image url",taskSnapshot.getDownloadUrl().toString());*/
                    UploadTimeline uploadTimeline = new UploadTimeline("15026A0501","DAMINENI SARATH CHANDRA",image_descrition.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());
                    String uploadId = databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(uploadTimeline);
                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(intent);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(share_image.this, "uploading failed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),TimeLine.class);
                    startActivity(i);
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    image_upload_progress.setProgress((int) progress);
                }
            });
        }
        else
        {
            Toast.makeText(this, "please pick or capture an image", Toast.LENGTH_SHORT).show();
        }
    }
    public void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(image_timeline);
        }
    }
}
