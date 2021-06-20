package com.example.coffeetime_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coffeetime_admin.auth.LoginUserActivity;
import com.example.coffeetime_admin.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private List<Product> listProduct = new ArrayList<Product>();
    ArrayAdapter<Product> arrayAdapterProduct;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listViewProduct = (ListView) findViewById(R.id.list_product);
        initFirebase();
        listProduct();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_crud:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            case R.id.menu_list:
                return  true;

            case R.id.menu_logout:
                Intent intent2 = new Intent(this, LoginUserActivity.class);
                startActivity(intent2);
        }
        return  super.onOptionsItemSelected(item);
    }


    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void listProduct(){
        databaseReference.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                listProduct.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    listProduct.add(product);
                    arrayAdapterProduct = new ArrayAdapter<Product>(MainActivity2.this,android.R.layout.simple_list_item_1,listProduct);
                    listViewProduct.setAdapter(arrayAdapterProduct);
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }
}