package my.edu.utar.socialcookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView tv = (TextView) findViewById(R.id.hw);

        //tv.setText("I am superman");
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);

    }
}