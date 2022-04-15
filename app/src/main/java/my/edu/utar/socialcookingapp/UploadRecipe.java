package my.edu.utar.socialcookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

import my.edu.utar.socialcookingapp.Model.FoodData;

public class UploadRecipe extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_description,ingredient1,
            amount1,ingredient2,amount2,ingredient3,amount3,
            ingredient4,amount4,ingredient5,amount5,
            ingredient6,amount6, ingredient7,amount7,
            txt_method;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);
        recipeImage = (ImageView) findViewById(R.id.iv_foodImage);
        txt_name = (EditText) findViewById(R.id.txt_recipeName);
        txt_description = (EditText) findViewById(R.id.txt_recipeDesc);
        ingredient1 = (EditText) findViewById(R.id.txt_recipeIngredient);
        amount1 = (EditText) findViewById(R.id.txt_recipeIngredientAmount);
        ingredient2 = (EditText) findViewById(R.id.txt_recipeIngredient2);
        amount2 = (EditText) findViewById(R.id.txt_recipeIngredient2Amount);
        ingredient3 = (EditText) findViewById(R.id.txt_recipeIngredient3);
        amount3 = (EditText) findViewById(R.id.txt_recipeIngredient3Amount);
        ingredient4 = (EditText) findViewById(R.id.txt_recipeIngredient4);
        amount4 = (EditText) findViewById(R.id.txt_recipeIngredient4Amount);
        ingredient5 = (EditText) findViewById(R.id.txt_recipeIngredient5);
        amount5 = (EditText) findViewById(R.id.txt_recipeIngredient5Amount);
        ingredient6 = (EditText) findViewById(R.id.txt_recipeIngredient6);
        amount6 = (EditText) findViewById(R.id.txt_recipeIngredient6Amount);
        ingredient7 = (EditText) findViewById(R.id.txt_recipeIngredient7);
        amount7 = (EditText) findViewById(R.id.txt_recipeIngredient7Amount);
        txt_method = (EditText) findViewById(R.id.txt_recipeStep);
    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent (Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult (photoPicker,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            uri = data.getData();
            recipeImage.setImageURI(uri);
        }
        else
            Toast.makeText(UploadRecipe.this, "You Haven't Selected Image", Toast.LENGTH_SHORT);
    }

    public void uploadImage(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("RecipeImage").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading...");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { progressDialog.dismiss();
            }
        });
    }

    public void btnUploadRecipe(View view) {
        uploadImage();

    }

    public void uploadRecipe(){

        FoodData foodData = new FoodData(
                txt_name.getText().toString(),txt_description.getText().toString(),
                ingredient1.getText().toString(),amount1.getText().toString(),
                ingredient2.getText().toString(),amount2.getText().toString(),
                ingredient3.getText().toString(),amount3.getText().toString(),
                ingredient4.getText().toString(),amount4.getText().toString(),
                ingredient5.getText().toString(),amount5.getText().toString(),
                ingredient6.getText().toString(),amount6.getText().toString(),
                ingredient7.getText().toString(),amount7.getText().toString(),
                txt_method.getText().toString(),imageUrl
        );
        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Recipe").child(myCurrentDateTime)
        .setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UploadRecipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadRecipe.this, e.getMessage().toString(), Toast.LENGTH_SHORT);
            }
        });
    }


}