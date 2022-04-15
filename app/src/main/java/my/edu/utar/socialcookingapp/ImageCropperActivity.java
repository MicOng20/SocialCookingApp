package my.edu.utar.socialcookingapp;

import static com.yalantis.ucrop.UCrop.REQUEST_CROP;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.UUID;

public class ImageCropperActivity extends AppCompatActivity {
    String result;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cropper);
        getSupportActionBar().hide();
        readIntent();

        String destUri = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        UCrop.Options options = new UCrop.Options();
        UCrop.of(imageUri, Uri.fromFile(new File(getCacheDir(), destUri)))
                .withOptions(options)
                .withAspectRatio(0, 0)
                .useSourceImageAspectRatio()
                .withMaxResultSize(2000, 2000)
                .start(ImageCropperActivity.this);
    }

    private void readIntent(){
        Intent intent = getIntent();
        if(intent.getExtras()!=null){
            result = intent.getStringExtra("DATA");
            imageUri = Uri.parse(result);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CROP){
            final Uri resultUri = UCrop.getOutput(data);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("RESULT", resultUri+"");
            setResult(-1, returnIntent);
            finish();
        }
        else if(resultCode == UCrop.RESULT_ERROR){
            final Throwable cropError = UCrop.getError(data);
            Toast.makeText(getApplicationContext(), ""+cropError, Toast.LENGTH_SHORT).show();
        }
    }
}