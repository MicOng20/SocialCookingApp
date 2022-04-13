package my.edu.utar.socialcookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import my.edu.utar.socialcookingapp.Adapter.FoodAdapter;
import my.edu.utar.socialcookingapp.Model.FoodData;

public class SearchMenu extends AppCompatActivity {

    RecyclerView mRecycleView;
    List<FoodData> myFoodList;
    FoodData mFoodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);

        mRecycleView = (RecyclerView)findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchMenu.this,1);
        mRecycleView.setLayoutManager(gridLayoutManager);

        myFoodList = new ArrayList<>();

        mFoodData = new FoodData("Slow Cooker Lemon Garlic Chicken",
                "Chicken With Lemon Garlic Flavour",
                R.drawable.slowcooker_lemon_garlic_chicken);
        myFoodList.add(mFoodData);

        mFoodData = new FoodData("Semisweet Chocolate Mousse",
                "Fresh Dessert with Chocolate",
                R.drawable.semisweet_chocolate_mousse);
        myFoodList.add(mFoodData);

        mFoodData = new FoodData("Meal Prep Pesto Chicken Veggie",
                "A Dish Suit Meat with Veggie",
                R.drawable.mealprep_pesto_chicken_veggies);
        myFoodList.add(mFoodData);

        mFoodData = new FoodData("Peanut Butter Cup Trifle",
                "Sweet Dessert for Everyone",
                R.drawable.peanutbutter_cup_trifle);
        myFoodList.add(mFoodData);

        mFoodData = new FoodData("Grandma Tomato Soup",
                "Your Memory Flavour",
                R.drawable.grandma_tomato_soup);
        myFoodList.add(mFoodData);

        FoodAdapter foodAdapter = new FoodAdapter(SearchMenu.this,myFoodList);
        mRecycleView.setAdapter(foodAdapter);

    }
}