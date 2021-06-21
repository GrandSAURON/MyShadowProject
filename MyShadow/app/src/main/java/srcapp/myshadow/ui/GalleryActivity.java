/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with the internal gallery
 * @author  Terminator
 * @version 210504_01
 */
package srcapp.myshadow.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.DocumentObject;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;

/**
 * Class for displaying a gallery of task photos
 * @author  Terminator
 * @version  210504_01
 * @since    JDK 1.8
 */
public class GalleryActivity extends AppCompatActivity {
    private static final Logger sysLog = new Logger(GalleryActivity.class);
    private ArrayList data = null;
    private ArrayList images = null;
    private int positionTask;
    private GridView gridView;
    private TaskObject task;
    private boolean bFlag = false;

    /**
     * Processing of the graphic part and processing of button clicks
     * @author  Terminator
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galley_view);

        /** opening array list with photos for gallery */
        Bundle bundle = getIntent().getExtras();
        positionTask = bundle.getInt("position", 0);
        if (positionTask != -1){
            DataStoreSQLite ds = DataStoreSQLite.getCurDataStore();
            this.data = ds.getData(DataDefine.I_TASK_OBJ);
            task = (TaskObject) data.get(positionTask);
            images = task.getTaskDocList();
        }

        /** graphic objects */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridView = (GridView) findViewById(R.id.gridder);

        /** start graphic objects */
        setSupportActionBar(toolbar);
        ImageAdapter adapterImage = new ImageAdapter(this, images);
        gridView.setAdapter(adapterImage);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Click to open a new activity to enlarge photos
             * @author  Terminator
             * @param parent -- array view
             * @param view -- view object
             * @param position -- current position
             * @param id -- id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FullImageActivity.class);
                intent.putExtra("id", position);
                intent.putExtra("position", positionTask);
                sysLog.Error(String.valueOf(position));
                startActivityForResult(intent,0);
                bFlag = true;
            }
        });
    }

    /**
     * Recreating activity on result
     */
    protected void onResume( ) {
        super.onResume();
        if (bFlag == true) {
            recreate();
            bFlag = false;
        }
    }

    /**
     * Obligatory function for creating menu and menu colors
     * @author  Terminator
     * @param menu -- menu for this activity
     * @return true
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.RED), 0,     spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //fix the color to white
            item.setTitle(spanString);
        }
        return true;
    }

    /**
     * Obligatory function for processing of clicks on menu items
     * @author  Terminator
     * @param item -- menu item
     * @return super.onOptionsItemSelected(item)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_publish){
            finish();
        }
        if(item.getItemId() == R.id.menuPurchasesListNewRecord){
            Intent intent = new Intent(getApplicationContext(), PhotoFromGallery.class);
            startActivityForResult(intent,0);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Actions when activity finishing
     * @param requestCode -- special code
     * @param resultCode -- result code
     * @param data -- intent data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for(int i = 0; i < new Single().getInstance().filecount; i++){
            DocumentObject doc = new DocumentObject();
            sysLog.Error(new Single().getInstance().filepath);
            doc.setFileName(new Single().getInstance().filepath);
            task.addTaskDoc( doc );
            task.Save();
        }
        new Single().getInstance().filecount = 0;
        recreate();
    }
}

