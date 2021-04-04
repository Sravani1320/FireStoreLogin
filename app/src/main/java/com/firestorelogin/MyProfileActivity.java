package com.firestorelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MyProfileActivity extends AppCompatActivity {
    EditText etEmail, etpassword, etName, etDob, etCity;
    Button btnRegister;
    TextView tvLogin;
    FirebaseFirestore db;
    ProgressDialog loadingBar;
    FirebaseAuth fAuth;
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etName = (EditText) findViewById(R.id.etName);
        etDob = (EditText) findViewById(R.id.etDob);
        etCity = (EditText) findViewById(R.id.etCity);

        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        userId = fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = db.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    etEmail.setText(documentSnapshot.getString("password"));
                    etName.setText(documentSnapshot.getString("name"));
                    etpassword.setText(documentSnapshot.getString("email"));
                    etCity.setText(documentSnapshot.getString("city"));
                    etDob.setText(documentSnapshot.getString("birthdate"));
                    if(documentSnapshot.getString("gender").equals("Male")){
                        radioMale.setChecked(true);
                    }
                    else {
                        radioFemale.setChecked(true);
                    }


                }else {
                    Toast.makeText(MyProfileActivity.this, "Somethig went wrong please try again later", Toast.LENGTH_SHORT).show();
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}