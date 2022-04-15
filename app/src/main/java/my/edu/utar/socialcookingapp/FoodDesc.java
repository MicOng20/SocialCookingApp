package my.edu.utar.socialcookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class FoodDesc extends AppCompatActivity {

    TextView foodName,foodDesc, foodMethod;
    ImageView foodImage;
    TextView ingredient1, amount1,ingredient2, amount2,ingredient3, amount3,
            ingredient4, amount4,ingredient5, amount5,ingredient6, amount6,
            ingredient7, amount7;
    Button btn_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_desc);

        foodImage = (ImageView) findViewById(R.id.fd_ivImage);
        foodName = (TextView) findViewById(R.id.fd_tvTitle);
        foodDesc = (TextView) findViewById(R.id.fd_tvDesc);
        foodMethod = (TextView) findViewById(R.id.fd_tvMethod);
        ingredient1 = (TextView) findViewById(R.id.fd_tvIngredient1);
        amount1 = (TextView) findViewById(R.id.fd_tvAmount1);
        ingredient2 = (TextView) findViewById(R.id.fd_tvIngredient2);
        amount2 = (TextView) findViewById(R.id.fd_tvAmount2);
        ingredient3 = (TextView) findViewById(R.id.fd_tvIngredient3);
        amount3 = (TextView) findViewById(R.id.fd_tvAmount3);
        ingredient4 = (TextView) findViewById(R.id.fd_tvIngredient4);
        amount4 = (TextView) findViewById(R.id.fd_tvAmount4);
        ingredient5 = (TextView) findViewById(R.id.fd_tvIngredient5);
        amount5 = (TextView) findViewById(R.id.fd_tvAmount5);
        ingredient6 = (TextView) findViewById(R.id.fd_tvIngredient6);
        amount6 = (TextView) findViewById(R.id.fd_tvAmount6);
        ingredient7 = (TextView) findViewById(R.id.fd_tvIngredient7);
        amount7 = (TextView) findViewById(R.id.fd_tvAmount7);
        btn_confirmation = (Button)findViewById(R.id.fd_btnConfirm);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            foodName.setText(mBundle.getString("FoodName"));
            //foodImage.setImageResource(mBundle.getInt("Image"));
            foodDesc.setText(mBundle.getString("Description"));

            ingredient1.setText(mBundle.getString("Ingredient1"));
            amount1.setText(mBundle.getString("Amount1"));
            ingredient2.setText(mBundle.getString("Ingredient2"));
            amount2.setText(mBundle.getString("Amount2"));
            ingredient3.setText(mBundle.getString("Ingredient3"));
            amount3.setText(mBundle.getString("Amount3"));
            ingredient4.setText(mBundle.getString("Ingredient4"));
            amount4.setText(mBundle.getString("Amount4"));
            ingredient5.setText(mBundle.getString("Ingredient5"));
            amount5.setText(mBundle.getString("Amount5"));
            ingredient6.setText(mBundle.getString("Ingredient6"));
            amount6.setText(mBundle.getString("Amount6"));
            ingredient7.setText(mBundle.getString("Ingredient7"));
            amount7.setText(mBundle.getString("Amount7"));

            foodMethod.setText(mBundle.getString("Method"));

            Glide.with(this).load(mBundle.getString("Image")).into(foodImage);
        }

        btn_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDesc.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(FoodDesc.this, "Hooray! Enjoy your Food.", Toast.LENGTH_SHORT).show();

            }
        });
    }

}