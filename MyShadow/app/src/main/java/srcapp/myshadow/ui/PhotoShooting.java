package srcapp.myshadow.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import srcapp.myshadow.service.Logger;

import static androidx.core.content.FileProvider.getUriForFile;
import static java.security.AccessController.getContext;

public class PhotoShooting extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_documents);
        dispatchTakePictureIntent();
    }
    String mCurrentPhotoPath;

    static final int REQUEST_TAKE_PHOTO = 0;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // создать файл для фотографии
        File photoFile = null;
        try {
            sysLog.Error("1");
            photoFile = createImageFile();
        } catch (IOException ex) {
            sysLog.Error("2");
        }

        // если файл создан, запускаем приложение камеры
        if (photoFile != null) {
            sysLog.Error(String.valueOf(Uri.fromFile(photoFile)));
            Uri photoURI = getUriForFile(this,
                   "srcapp.myshadow.ui",
                    photoFile);
            sysLog.Error(String.valueOf(photoURI));
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }
    //File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Images/" + image_name + ".jpg"); Uri imageUri = Uri.fromFile(file); Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); startActivityForResult(intent, 0);



    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
       // String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       // String imageFileName = "JPEG_" + timeStamp + "_";
        File imagePath = new File("/data/data/srcapp.myshadow.ui/app_imageDir");
        File newFile = new File(imagePath, "default_image.jpg");
        //Uri contentUri = getUriForFile(this, "srcapp.myshadow.ui", newFile);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = newFile.getAbsolutePath();
        return newFile;
    }
}

