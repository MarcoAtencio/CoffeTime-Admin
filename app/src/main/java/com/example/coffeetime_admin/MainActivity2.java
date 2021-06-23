package com.example.coffeetime_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coffeetime_admin.auth.LoginUserActivity;
import com.example.coffeetime_admin.model.ListAdapter;
import com.example.coffeetime_admin.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public List<Product> listProduct = new ArrayList<Product>();
    ArrayAdapter<Product> arrayAdapterProduct;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String url = "https://static.india.com/wp-content/uploads/2018/09/42-4.jpg";
    View view;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initFirebase();
        listProduct();
    }*/

public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.activity_main2, container, false);
    initFirebase();
    listProduct();
    return view;
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
                break;

            case R.id.menu_list:
                return  true;

            case R.id.menu_logout:
                Intent intent2 = new Intent(this, LoginUserActivity.class);
                startActivity(intent2);
                break;
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
                }
                ListAdapter listAdapter = new ListAdapter(listProduct,MainActivity2.this);
                RecyclerView recyclerView = view.findViewById(R.id.listProduct);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(MainActivity2.this,"Aviso De Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}