package my.edu.utar.socialcookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EmailRegister extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mConfirmPassword;
    Button mRegisterBtn;
    TextView mGotoLoginBtn;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);

        mFullName = findViewById(R.id.txt_fullname);
        mEmail = findViewById(R.id.txt_email);
        mPassword = findViewById(R.id.txt_password);
        mConfirmPassword = findViewById(R.id.txt_confirmPassword);
        mRegisterBtn = findViewById((R.id.btn_register));
        mGotoLoginBtn = findViewById(R.id.txt_gotologin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mFullName.getText().toString();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirm = mConfirmPassword.getText().toString();

                //if(fAuth.getCurrentUser() != null){
                //    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                //    finish();
                //}

                if(TextUtils.isEmpty(username)){
                    mFullName.setError("Username is Required.");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(TextUtils.isEmpty(confirm)){
                    mConfirmPassword.setError("Confirm Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be >= 6 Characters");
                    return;
                }

                if(password.equals(confirm)){
                    //register user in firebase
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){

                            //send verification link
                            FirebaseUser fUser = fAuth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(EmailRegister.this, "Verification Email has been Sent", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: Email not sent " + e.getMessage());
                                }
                            });

                            //FirebaseUser user = fAuth.getCurrentUser();

                            //get user email and uid from auth
                            String email1 = fUser.getEmail();
                            String uid1 = fUser.getUid();

                            HashMap<Object, String> hashMap = new HashMap<>();
                            //put info to hashmap
                            hashMap.put("email", email1);
                            hashMap.put("uid", uid1);
                            hashMap.put("name", "");
                            hashMap.put("phone", "");
                            hashMap.put("image", "");
                            hashMap.put("cover", "");

                            //firebase database instance
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            //path to store user data named "Users"
                            DatabaseReference reference = database.getReference("Users");
                            //put data within hashmap in database
                            reference.child(uid1).setValue(hashMap);

                            Toast.makeText(EmailRegister.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",username);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                                Log.d("TAG", "onSuccess: user Profile is created for " + userID);
                            }).addOnFailureListener((e) -> {
                                Log.d("TAG", "onFailure: " + e.toString());
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(EmailRegister.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),EmailRegister.class));
                            finish();
                        }
                    });
                }
                else{
                    mConfirmPassword.setError("Please enter correct password");
                    return;
                }



            }
        });

        mGotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EmailLogin.class));
            }
        });
    }
}