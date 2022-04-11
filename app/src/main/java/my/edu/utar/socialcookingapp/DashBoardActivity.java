package my.edu.utar.socialcookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    private void checkUserStatus(){
        //get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user ==null)
        {
            //user not signed in, go to main activity
            startActivity(new Intent(DashBoardActivity.this, Connect.class));
            finish();
        }
    }

    @Override
    protected void onStart(){
        //check on start of app
        checkUserStatus();
        super.onStart();
    }

    /* Inflate options menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflating menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Handle menu item click */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // get item id
        int id = item.getItemId();
        if (id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
        }
        return super.onOptionsItemSelected(item);
    }

    //back press key -> go back to login page
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT?");
        builder.setMessage("Are you sure want to Exit?");

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(DashBoardActivity.this, Connect.class);
                startActivity(intent);
                finish();
                firebaseAuth.signOut();
                checkUserStatus();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.show();
    }
}