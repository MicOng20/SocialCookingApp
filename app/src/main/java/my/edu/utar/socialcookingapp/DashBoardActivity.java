package my.edu.utar.socialcookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoardActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        //Hide top action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

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
                        case R.id.nav_post:
                            actionBar.setTitle("Post?"); //change actionbar title
                            PostLayoutFragment fragment3 = new PostLayoutFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content1, fragment3, "");
                            ft3.commit();
                            return true;
                        case R.id.nav_profile:
                            //profile fragment transaction
                            //home fragment transaction
                            actionBar.setTitle("Profile"); //change actionbar title
                            ProfileFragment fragment4 = new ProfileFragment();
                            FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                            ft4.replace(R.id.content1, fragment4, "");
                            ft4.commit();
                            return true;
                    }
                    return false;
                }
            };


}