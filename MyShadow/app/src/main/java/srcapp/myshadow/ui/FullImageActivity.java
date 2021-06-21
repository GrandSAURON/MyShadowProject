/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with full-size photo from the gallery
 * @author  Terminator
 * @version 210504_01
 */
package srcapp.myshadow.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.store.DataStoreSQLite;

/**
 * Class for working with a full-size photo from the gallery
 * @author  Terminator
 * @version  210504_01
 * @since    JDK 1.8
 */
public class FullImageActivity extends AppCompatActivity {
    private ArrayList data = null;
    private ArrayList imagestemp = null;

    /**
     * Working on a full-size photo from the gallery
     * @author  Terminator
     * @param savedInstanceState -- bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        /** Graphical and common objects */
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position", 0);
        DataStoreSQLite ds = DataStoreSQLite.getCurDataStore();
        this.data = ds.getData(DataDefine.I_TASK_OBJ);
        TaskObject task = (TaskObject) data.get(position);
        imagestemp = task.getTaskDocList();

        /** Creating toolbar for this activity */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /** Initialization of toolbar */
        setSupportActionBar(toolbar);

        /** Making graphical objects */
        int positionPhoto = intent.getExtras().getInt("id");
        ImageAdapter adapter = new ImageAdapter(this, imagestemp);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        String pathToPhotoFile = adapter.images.get(positionPhoto);
        Button deleteButton = (Button) findViewById(R.id.button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Open activity with documents
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                String filePath = pathToPhotoFile;
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
                finish();
            }
        });

        /** Initialisation graphical and common objects */
        imageView.setImageDrawable(Drawable.createFromPath(pathToPhotoFile));
    }

    /**
     *
     * @param menu -- current menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_full_image, menu);

        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.RED),0, spanString.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //fix the color to white
            item.setTitle(spanString);
        }
        return true;
    }

    /**
     * Actions depending on the selected menu component
     * @author  Terminator
     * @param item -- menu item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_publish){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}