package my.edu.utar.socialcookingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class SearchFragment extends Fragment {

    private RecyclerView mRecycleView2;
    private List<FoodData> myFoodList2;
    private FoodData mFoodData2;
    private FoodAdapter foodAdapter2;

    private DatabaseReference databaseReference2;
    private ValueEventListener eventListener2;
    ProgressDialog progressDialog2;

    EditText txt_Search2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        mRecycleView2 = view.findViewById(R.id.fs_recycleView);
        mRecycleView2.setHasFixedSize(true);
        mRecycleView2.setLayoutManager(new GridLayoutManager(getActivity(),1));

        txt_Search2 = view.findViewById(R.id.fs_search);

        progressDialog2 = new ProgressDialog(getActivity());
        progressDialog2.setMessage("Loading Items...");

        myFoodList2 = new ArrayList<>();

        foodAdapter2 = new FoodAdapter(getContext(),myFoodList2);
        mRecycleView2.setAdapter(foodAdapter2);

        progressDialog2 = new ProgressDialog(getActivity());
        progressDialog2.setMessage("Loading Items...");

        databaseReference2 = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog2.show();
        eventListener2 = databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myFoodList2.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren())
                {
                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    myFoodList2.add(foodData);
                }
                foodAdapter2.notifyDataSetChanged();
                progressDialog2.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog2.dismiss();
            }
        });

        txt_Search2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());

            }
        });

        return view;
    }

    private void filter (String text){
        ArrayList<FoodData> filterList = new ArrayList<>();

        for(FoodData item:myFoodList2){
            if(item.getFoodName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        foodAdapter2.filteredList(filterList);
    }

}