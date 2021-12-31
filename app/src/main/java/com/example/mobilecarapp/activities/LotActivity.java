package com.example.mobilecarapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobilecarapp.R;
import com.example.mobilecarapp.databinding.ActivityLotBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class LotActivity extends AppCompatActivity {

    private ActivityLotBinding binding;
    private FirebaseFirestore db;
    private HashMap<String,String> hashMap;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLotBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        hashMap = new HashMap<>();


        int index = getIntent().getIntExtra("index",0);
        boolean emptyButton = getIntent().getBooleanExtra("emptyButton",false);

        if (emptyButton){
            binding.emptyTheLot.setEnabled(true);
            binding.fillTheLot.setEnabled(false);
        } else {
            binding.emptyTheLot.setEnabled(false);
            binding.fillTheLot.setEnabled(true);
        }

        switch (index) {

            case 0:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_1st_lot);

                break;
            case 1:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_2nd_lot);
                break;

            case 2:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_3rd_lot);
                break;

            case 3:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_4th_lot);
                break;

            case 4:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_5th_lot);
                break;

            case 5:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_6th_lot);
                break;

            case 6:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_7th_lot);
                break;

            case 7:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_8th_lot);
                break;

            case 8:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_9th_lot);
                break;

            case 9:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_10th_lot);
                break;

            case 10:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_11th_lot);
                break;

            case 11:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_12th_lot);
                break;

            case 12:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_13th_lot);
                break;

            case 13:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_14th_lot);
                break;

            case 14:
                binding.carParkPlaceImageView.setImageResource(R.drawable.car_park_for_15th_lot);
                break;

        }





        binding.fillTheLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hashMap.put(String.valueOf(index),auth.getUid());

                db.collection("CarParks").document("Lot"+index).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void unused) {
                        binding.fillTheLot.setEnabled(false);
                        binding.emptyTheLot.setEnabled(true);

                    }
                });

            }
        });


        binding.emptyTheLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hashMap.put(String.valueOf(index),"bos");

                db.collection("CarParks").document("Lot"+index).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void unused) {
                        binding.fillTheLot.setEnabled(true);
                        binding.emptyTheLot.setEnabled(false);

                    }
                });

            }
        });

    }
}