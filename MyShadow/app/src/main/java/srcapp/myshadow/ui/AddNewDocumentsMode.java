/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with app settings
 * @author  Terminator
 * @version 210512_01
 */
package srcapp.myshadow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import srcapp.myshadow.service.Logger;

/**
 * Class for working with settings
 * @author  Terminator
 * @version  210512_01
 * @since    JDK 1.8
 */
public class AddNewDocumentsMode extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);

    /**
     *  Creating all graphic and server options
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_documents);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button photoFromGallery = (Button) findViewById(R.id.choosingPhotoButton);
        Button photoFromCamera = (Button) findViewById(R.id.button4);
        photoFromGallery.setOnClickListener(new View.OnClickListener() {
            /**
             * Open activity with documents
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewDocumentsMode.this, PhotoFromGallery.class);
                startActivityForResult(intent,0);
            }
        });
        photoFromCamera.setOnClickListener(new View.OnClickListener() {
            /**
             * Open activity with documents
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewDocumentsMode.this, PhotoShooting.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Activity result options
     * @param requestCode -- special code
     * @param resultCode -- resulting code
     * @param data -- intent data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
