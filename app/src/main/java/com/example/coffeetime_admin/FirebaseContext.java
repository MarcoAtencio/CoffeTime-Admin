package com.example.coffeetime_admin;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseContext extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
