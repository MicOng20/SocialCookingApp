package my.edu.utar.socialcookingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import my.edu.utar.socialcookingapp.Adapter.FoodAdapter;
import my.edu.utar.socialcookingapp.Model.FoodData;

public class SearchFragment extends Fragment {

    /*private RecyclerView mRecycleView;
    private List<FoodData> myFoodList;
    private FoodData mFoodData;
    private FoodAdapter foodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        mRecycleView = view.findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        myFoodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(getContext(),myFoodList);
        mRecycleView.setAdapter(foodAdapter);

        return view;
    }*/
}