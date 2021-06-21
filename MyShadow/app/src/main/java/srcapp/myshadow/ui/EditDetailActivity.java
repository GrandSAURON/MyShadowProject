/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with task editing
 * @author  Terminator
 * @version 210504_01
 */
package srcapp.myshadow.ui;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.LocateObject;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.TypeStateObject;
import srcapp.myshadow.kbs.TypeTaskObject;
import srcapp.myshadow.kbs.UserObject;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;

/**
 * Class for working with a editing task
 * @author  Terminator
 * @version  210504_01
 * @since    JDK 1.8
 */
public class EditDetailActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static final Logger sysLog = new Logger(EditDetailActivity.class);

    private ArrayList peoplesList;
    private ArrayList statesList;
    private ArrayList typeList;
    private ArrayList locationList;
    private String[] peoples;
    private String[] states;
    private String[] type;
    private String[] locations;
    private TextView mTextView;
    private TaskObject task;
    private EditText editTaskDescription;
    private EditText editTaskName;
    private ArrayList data = null;

    /**
     * Creating the entire graphical job shell and writing to it from
     * the database and writing them to it
     * @author  Terminator
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        /** Getting access to a task from a database */
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position", 0);
        DataStoreSQLite ds = DataStoreSQLite.getCurDataStore();
        this.data = ds.getData(DataDefine.I_TASK_OBJ);
        task = (TaskObject) data.get(position);

        peoplesList = ds.getData(3);
        UserObject tempObjUser;
        peoples = new String[peoplesList.size()];
        for(int i = 0; i < peoplesList.size(); i++){
            tempObjUser = (UserObject) peoplesList.get(i);
            peoples[i] = tempObjUser.getName();
        }
        statesList = ds.getData(5);
        TypeStateObject tempObjState;
        states = new String[statesList.size()];
        for(int i = 0; i < statesList.size(); i++){
            tempObjState = (TypeStateObject) statesList.get(i);
            states[i] = tempObjState.getName();
        }
        typeList = ds.getData(6);
        TypeTaskObject tempObjTask;
        type = new String[typeList.size()];
        for(int i = 0; i < typeList.size(); i++){
            tempObjTask = (TypeTaskObject) typeList.get(i);
            type[i] = tempObjTask.getName();
        }
        locationList = ds.getData(4);
        LocateObject tempObjLoc;
        locations = new String[locationList.size()];
        for(int i = 0; i < locationList.size(); i++){
            tempObjLoc = (LocateObject) locationList.get(i);
            locations[i] = tempObjLoc.getName();
        }

        /** Initialization of edit text objects */
        editTaskDescription = (EditText) findViewById(R.id.editTaskDescription);
        editTaskName = (EditText) findViewById(R.id.editTaskName);

        /** Initialization of view text objects */
        TextView viewRegisterDate = (TextView) findViewById(R.id.registrationTimeTextViewShow);

        /** Creating toolbar for this activity */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /** Creating spinners for choosing one element from list */
        Spinner senderSpinner = (Spinner) findViewById(R.id.senderSpinner);
        Spinner typeStateSpinner = (Spinner) findViewById(R.id.typeStateSpinner);
        Spinner taskTypeSpinner = (Spinner) findViewById(R.id.taskTypeSpinner);
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        Spinner reciverSpinner = (Spinner) findViewById(R.id.senderSpinner2);

        /** Adapters for spinners */
        ArrayAdapter<String> senderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, peoples);
        ArrayAdapter<String> typeStateAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, states);
        ArrayAdapter<String> taskTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, type);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, locations);

        /** Setting adapters */
        senderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /** Setting spinner adapters */
        senderSpinner.setAdapter(senderAdapter);
        typeStateSpinner.setAdapter(typeStateAdapter);
        taskTypeSpinner.setAdapter(taskTypeAdapter);
        locationSpinner.setAdapter(locationAdapter);
        reciverSpinner.setAdapter(senderAdapter);

        senderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(String.valueOf(position));
                task.setSenderNameAndId(peoples[position]);
                task.Save();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        typeStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(String.valueOf(position));
                task.setTypeStateNameAndId(states[position]);
                task.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        taskTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(String.valueOf(position));
                task.setTypeTaskNameAndId(type[position]);
                task.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(String.valueOf(position));
                task.setLocationNameAndId(locations[position]);
                task.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        reciverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(String.valueOf(position));
                task.setReceiverNameAndId(peoples[position]);
                task.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        /** Setting text to fields for editing */
        editTaskDescription.setText(task.getNote());
        editTaskName.setText(task.getName());

        /** Initialization of toolbar */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /** Seekbar for percentage completion */
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        mTextView = (TextView)findViewById(R.id.textView20);
        mTextView.setText("0%");

        viewRegisterDate.setText(task.getCreateTime());
    }

    /**
     * Creating a menu for navigation
     * @author  Terminator
     * @param menu -- menu for this activity
     * @return true
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_detail_activity, menu);

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
            task.setName(String.valueOf(editTaskName.getText()));
            task.setNote(String.valueOf(editTaskDescription.getText()));
            task.Save();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Actions that should occur when the slider moves
     * @author  Terminator
     * @param seekBar -- current seekbar
     * @param progress -- one step progress
     * @param fromUser -- boolean var
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTextView.setText(String.valueOf(seekBar.getProgress())  + "%");
        task.setPercentCompl(seekBar.getProgress());

    }

    /**
     * This function is not currently in use
     * @author  Terminator
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mTextView.setText(String.valueOf(task.getPercentCompl())  + "%");
    }

    /**
     * This function is not currently in use
     * @author  Terminator
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
