package com.example.mobilecarapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.mobilecarapp.R;
import com.example.mobilecarapp.adapter.CarRecyclerViewAdapter;
import com.example.mobilecarapp.databinding.ActivityUserInterfaceBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserInterfaceActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private ActivityUserInterfaceBinding binding;
    private FirebaseFirestore db;
    private CarRecyclerViewAdapter carRecyclerViewAdapter;

    public UserInterfaceActivity() {

    }

    ArrayList<String> carParkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInterfaceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        carParkList = new ArrayList<>();

        carParkList.add("Number 1");
        carParkList.add("Number 2");
        carParkList.add("Number 3");
        carParkList.add("Number 4");
        carParkList.add("Number 5");
        carParkList.add("Number 6");
        carParkList.add("Number 7");
        carParkList.add("Number 8");
        carParkList.add("Number 9");
        carParkList.add("Number 10");
        carParkList.add("Number 11");
        carParkList.add("Number 12");
        carParkList.add("Number 13");
        carParkList.add("Number 14");
        carParkList.add("Number 15");


        binding.carParkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        carRecyclerViewAdapter = new CarRecyclerViewAdapter(carParkList,this,db,auth.getUid());
        binding.carParkRecyclerView.setAdapter(carRecyclerViewAdapter);



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (carRecyclerViewAdapter != null)
            carRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.signout){
            //Signout

            auth.signOut();

            Intent intentToMain = new Intent(UserInterfaceActivity.this,MainActivity.class);
            startActivity(intentToMain);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}