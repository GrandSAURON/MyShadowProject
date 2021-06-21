/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with detail activity
 * @author  Terminator
 * @version 210505_01
 */
package srcapp.myshadow.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.DocumentObject;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;

/**
 * A class for displaying all attributes and their values for a given task
 * @author  Terminator
 * @version  210505_01
 * @since    JDK 1.8
 */
public class DetailActivity extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);
    public static final String POSITION = "position";
    private ArrayList taskData = null;
    private ArrayList images = null;
    int position;

    /**
     * Creating all interface objects and initializing them
     * @author  Terminator
     * @param savedInstanceState -- bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /** Creating all graphics objects */
        TextView projectDetail = (TextView) findViewById(R.id.taskDescriptionTextViewShow);
        TextView viewTypeTask = (TextView) findViewById(R.id.typeTaskTextViewShow);
        TextView viewName = (TextView) findViewById(R.id.taskNameTextViewShow);
        TextView viewTypeState = (TextView) findViewById(R.id.typeStateTextViewShow);
        TextView viewCreationDate = (TextView) findViewById(R.id.registrationTimeTextViewShow);
        TextView viewPrioritet = (TextView) findViewById(R.id.prioritetTextViewShow);
        TextView viewPercentComplete = (TextView) findViewById(R.id.percentTextViewShow);
        TextView viewApplicant = (TextView) findViewById(R.id.senderTextViewShow);
        TextView viewRecipient = (TextView) findViewById(R.id.recieverTextViewShow);
        TextView viewLocation = (TextView) findViewById(R.id.taskLocationTextViewShow);
        TextView viewTypeStateDate = (TextView) findViewById(R.id.typeStateDateTextViewShow);

        /** Creating all button objects */
        Button taskDocuments = (Button) findViewById(R.id.showTaskDocumentsButton);
        ImageView projectPicutre = (ImageView) findViewById(R.id.taskImage);

        /** Toolbar making */
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /** Creating all system objects */
        DataStoreSQLite ds = DataStoreSQLite.getCurDataStore();
        DocumentObject doc;
        position = getIntent().getIntExtra(POSITION, 0);
        this.taskData = ds.getData(DataDefine.I_TASK_OBJ);
        TaskObject task = (TaskObject) taskData.get(position);

        /** Initializing and working with graphical and system objects */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /** Titles for toolbars */
        collapsingToolbar.setTitle(task.getName());

        /** Initializing text views in card */
        projectDetail.setText(task.getNote());
        viewName.setText(task.getName());
        viewTypeState.setText(task.getTypeStateName());
        viewCreationDate.setText(task.getCreateTime());
        viewPrioritet.setText(Integer.toString(task.getPrioritet()));
        viewPercentComplete.setText(Integer.toString(task.getPercentCompl()) + "%");
        viewTypeTask.setText(task.getTypeTaskName());
        viewLocation.setText(task.getLocationName());
        sysLog.Error(task.getLocationName());
        viewApplicant.setText(task.getSenderName());
        sysLog.Error(String.valueOf(task.getReceiverName()));
        viewRecipient.setText(task.getReceiverName());
        images = task.getTaskDocList();
        if (images != null && images.size() > 0) {
            doc = (DocumentObject) images.get(0);
            projectPicutre.setImageDrawable(Drawable.createFromPath((String) doc.getFileName()));
        }
        taskDocuments.setOnClickListener(new View.OnClickListener() {
            /**
             * Open activity with documents
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, GalleryActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent,0);
            }
        });

        Button delBut = (Button) findViewById(R.id.delButton);
        delBut.setOnClickListener(new View.OnClickListener() {
            /**
             * Open activity with documents
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                task.Dalete();
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("refresh",1);
                startActivity(intent);
                finish();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Задание успешно удалено", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    /**
     * Creating a menu for navigation
     * @author  Terminator
     * @param menu -- menu object
     * @return true
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.RED), 0, spanString.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);
        }
        return true;
    }

    /**
     * Actions depending on the selected menu component
     * @author  Terminator
     * @param item - menuItem object
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_publish){
            Context context = this;
            Intent intent = new Intent(context, EditDetailActivity.class);
            intent.putExtra("position", position);
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
        recreate();
    }
}
