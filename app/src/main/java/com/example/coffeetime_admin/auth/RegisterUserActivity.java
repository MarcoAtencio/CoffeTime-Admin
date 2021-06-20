package com.example.coffeetime_admin.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeetime_admin.MainActivity2;
import com.example.coffeetime_admin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterUserActivity extends AppCompatActivity {

    EditText et_email, et_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        et_email  = (EditText) findViewById(R.id.text_login);
        et_password = (EditText) findViewById(R.id.text_password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.auth_menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_register:
                return  true;

            case R.id.menu_login:
                Intent intent = new Intent(this, LoginUserActivity.class);
                startActivity(intent);
        }
        return  super.onOptionsItemSelected(item);
    }

    public void registerUser(View view){
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           Toast.makeText(RegisterUserActivity.this,"Se a registrado exitosamente",Toast.LENGTH_SHORT).show();
                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterUserActivity.this,"Ya esta registrado este usuario",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterUserActivity.this,"Ocurrio algo en el proceso",Toast.LENGTH_SHORT).show();
                            }

                        }

                        // ...
                    }
                });
    }
}