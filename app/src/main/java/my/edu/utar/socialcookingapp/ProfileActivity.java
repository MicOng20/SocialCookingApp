package my.edu.utar.socialcookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //ActionBar and its title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");

        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //bottom navigation
        //BottomNavigationView navigationView = findViewById(R.id.navigation);
        //navigationView.setOnNavigationItemSelectedListener();
        NavigationBarView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(selectedListener);
        // NavigationBarView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener)

        //home fragment transaction - default on start
        actionBar.setTitle("Home"); //change actionbar title
        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content1, fragment1, "");
        ft1.commit();
    }

    //navigationBarView.OnItemSelectedListener
    private NavigationBarView.OnItemSelectedListener selectedListener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //handle item clicked
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            //home fragment transaction
                            actionBar.setTitle("Home"); //change actionbar title
                            HomeFragment fragment1 = new HomeFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content1, fragment1, "");
                            ft1.commit();
                            return true;
                        case R.id.nav_search:
                            //search fragment transaction
                            //search fragment transaction
                            actionBar.setTitle("Search"); //change actionbar title
                            SearchFragment fragment2 = new SearchFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content1, fragment2, "");
                            ft2.commit();
                            return true;
                        case R.id.nav_profile:
                            //profile fragment transaction
                            //home fragment transaction
                            actionBar.setTitle("Profile"); //change actionbar title
                            ProfileFragment fragment3 = new ProfileFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content1, fragment3, "");
                            ft3.commit();
                            return true;
                    }
                    return false;
                }
            };


}