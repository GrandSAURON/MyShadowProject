/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with gallery
 * @author  Terminator
 * @version 210505_01
 */
package srcapp.myshadow.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import srcapp.myshadow.service.Logger;

/**
 * A class for taking photos from gallery
 * @author  Terminator
 * @version  210505_01
 * @since    JDK 1.8
 */
public class PhotoFromGallery extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private final int Pick_image = 1;
    private int MAX_IMAGE_SIZE = 200 * 1024;

    /**
     * Create actions
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_documents);
        dispatchTakePictureIntent();
    }

    /**
     * Type of file
     */
    private void dispatchTakePictureIntent() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Pick_image);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    final Uri imageUri = imageReturnedIntent.getData();
                    sysLog.Error(getRealPathFromURI(this, imageUri));
                    File file = null;
                    try {
                        file = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    File file1 = new File(getRealPathFromURI(this, imageUri));
                    try {
                        copy(file1, file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sysLog.Error(file.getPath());
                    new Single().getInstance().filepath = file.getPath();
                    new Single().getInstance().filecount += 1;
                    finish();
                    //Button shoPhoto = (Button) findViewById(R.id.button2);
                    //shoPhoto.setVisibility(View.VISIBLE);
                }
        }
    }

    /**
     * Getting real path to file
     * @param context -- current context
     * @param contentURI -- uri
     * @return result
     */
    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result = null;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            if(idx >= 0) {
                result = cursor.getString(idx);
            }
            cursor.close();
        }
        return result;
    }

    /**
     * Creating unique file
     * @return image
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        sysLog.Error(timeStamp);
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File("/data/data/srcapp.myshadow.ui/app_imageDir");
        File image = File.createTempFile( imageFileName, ".jpg", storageDir);
        return image;
    }

    /**
     * Coping one file to another place
     * @param src -- src file
     * @param dst -- dst file
     * @throws IOException
     */
    public void copy(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }
}
