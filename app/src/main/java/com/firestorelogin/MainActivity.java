package com.firestorelogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {

                Fragment fragment = null;
                if (view == findViewById(R.id.btnLogin)) {
                    fragment = new LoginFragment();
                } else {
                    fragment = new RegistrationFragment();
                }
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.commit();
            }
        };


        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setSelected(true);
        btnLogin.setOnClickListener(listener);

        btnRegistration = (Button) findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(listener);
    }
}