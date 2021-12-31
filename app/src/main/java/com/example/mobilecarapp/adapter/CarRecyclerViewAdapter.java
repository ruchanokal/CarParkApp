package com.example.mobilecarapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecarapp.R;
import com.example.mobilecarapp.activities.LotActivity;
import com.example.mobilecarapp.databinding.RecyclerviewRowBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class CarRecyclerViewAdapter extends RecyclerView.Adapter<CarRecyclerViewAdapter.CarHolder> {


    ArrayList<String> carParkList;
    Context mContext;
    FirebaseFirestore db;
    String uid;

    public CarRecyclerViewAdapter(ArrayList<String> carParkList, Context mContext,FirebaseFirestore db,String uid) {
        this.carParkList = carParkList;
        this.mContext = mContext;
        this.db = db;
        this.uid = uid;
    }

    @Override
    public CarRecyclerViewAdapter.CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerviewRowBinding recyclerviewRowBinding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CarHolder(recyclerviewRowBinding);
    }

    @Override
    public void onBindViewHolder(CarRecyclerViewAdapter.CarHolder holder, int position) {



        db.collection("CarParks").document("Lot"+position).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot value, FirebaseFirestoreException error) {

                if (value != null && value.exists()) {

                     String posString = (String) value.get(String.valueOf(position));

                    if (posString!= null){

                        if (posString.equals("bos")){

                            holder.binding.mainLayout.setBackgroundResource(R.drawable.car_park_bg);

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(mContext, LotActivity.class);
                                    intent.putExtra("index",position);
                                    intent.putExtra("emptyButton",false);
                                    mContext.startActivity(intent);
                                }
                            });


                        } else if (posString.equals(uid)){

                            holder.binding.mainLayout.setBackgroundResource(R.drawable.car_park_your_place_bg);

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(mContext, LotActivity.class);
                                    intent.putExtra("index",position);
                                    intent.putExtra("emptyButton",true);
                                    mContext.startActivity(intent);
                                }
                            });


                        } else {

                            holder.binding.mainLayout.setBackgroundResource(R.drawable.car_park_full_bg);

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Toast.makeText(mContext,"You cannot park here! It is full",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                    } else {

                        holder.binding.mainLayout.setBackgroundResource(R.drawable.car_park_bg);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(mContext, LotActivity.class);
                                intent.putExtra("index",position);
                                intent.putExtra("emptyButton",false);
                                mContext.startActivity(intent);
                            }
                        });


                    }


                } else {

                    holder.binding.mainLayout.setBackgroundResource(R.drawable.car_park_bg);

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(mContext, LotActivity.class);
                            intent.putExtra("index",position);
                            intent.putExtra("emptyButton",false);
                            mContext.startActivity(intent);
                        }
                    });

                }
            }
        });


        holder.binding.carParkTextView.setText(carParkList.get(position));

    }

    @Override
    public int getItemCount() {
        return carParkList.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder {

        private RecyclerviewRowBinding binding;

        public CarHolder(RecyclerviewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
