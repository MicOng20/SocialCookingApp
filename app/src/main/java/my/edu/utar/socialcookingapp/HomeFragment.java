package my.edu.utar.socialcookingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import my.edu.utar.socialcookingapp.Adapter.FoodAdapter;
import my.edu.utar.socialcookingapp.Model.FoodData;

public class HomeFragment extends Fragment {

    private RecyclerView mRecycleView1;
    private List<FoodData> myFoodList1;
    private FoodData mFoodData1;
    private FoodAdapter foodAdapter1;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        final FloatingActionButton fab = view.findViewById(R.id.home_fab);

        mRecycleView1 = view.findViewById(R.id.home_recycleView);
        mRecycleView1.setHasFixedSize(true);
        mRecycleView1.setLayoutManager(new GridLayoutManager(getActivity(),1));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Items...");

        myFoodList1 = new ArrayList<>();

        foodAdapter1 = new FoodAdapter(getContext(),myFoodList1);
        mRecycleView1.setAdapter(foodAdapter1);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Items...");

        //readRecipe();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UploadRecipe.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myFoodList1.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren())
                {
                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    myFoodList1.add(foodData);
                }
                foodAdapter1.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });

        return view;
    }

    /*private void readRecipe()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myFoodList1.clear();

                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    myFoodList1.add(foodData);
                }
                foodAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}