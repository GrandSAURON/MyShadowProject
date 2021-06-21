/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with new task
 * @author  Terminator
 * @version 210417_01
 */
package srcapp.myshadow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.DocumentObject;
import srcapp.myshadow.kbs.LocateObject;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.TypeStateObject;
import srcapp.myshadow.kbs.TypeTaskObject;
import srcapp.myshadow.kbs.UserObject;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;

/**
 * A class for creating a new task to perform and
 * adding all the files that accompany the task
 * @author  Terminator
 * @version  210417_01
 * @since    JDK 1.8
 */
public class CreateNewTask extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private ArrayList peoplesList;
    private ArrayList statesList;
    private ArrayList typeList;
    private ArrayList locationList;
    private String[] peoples;
    private String[] states;
    private String[] type;
    private String[] locations;
    private EditText editTaskDescription;
    private EditText editTaskName;
    private EditText editPrioritet;
    private TaskObject newObj;
    private ArrayList data = null;
    TextView mTextView;
    /********************************************************
     *                       EVENTS
     *******************************************************/

    /**
     * Required method that is called when creating an
     * object of the class
     * @author  Terminator
     * @param savedInstanceState -- bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        newObj = (TaskObject) DataStoreSQLite.getCurDataStore().createObject(DataDefine.I_TASK_OBJ);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creating_new_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newObj.Dalete();
                onBackPressed();
            }
        });


        DataStoreSQLite ds = DataStoreSQLite.getCurDataStore();
        this.data = ds.getData(DataDefine.I_TASK_OBJ);

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

        /** Creating spinners for choosing one element from list */
        Spinner senderSpinner = (Spinner) findViewById(R.id.senderSpinner1);
        Spinner typeStateSpinner = (Spinner) findViewById(R.id.typeStateSpinner1);
        Spinner taskTypeSpinner = (Spinner) findViewById(R.id.taskTypeSpinner1);
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinnerNew);
        Spinner recieverSpinner = (Spinner) findViewById(R.id.senderSpinner3);

        Button addDocuments = (Button) findViewById(R.id.showTaskDocumentsButton);

        addDocuments.setOnClickListener(new View.OnClickListener() {
            /**
             * Open activity with documents
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewTask.this, AddNewDocumentsMode.class);
                startActivityForResult(intent,0);
            }
        });

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
        recieverSpinner.setAdapter(senderAdapter);

        /** Seekbar for percentage completion */
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        mTextView = (TextView)findViewById(R.id.textView20);
        mTextView.setText("0%");
        newObj.Save();
        senderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(peoples[position]);
                newObj.setSenderNameAndId(peoples[position]);
                newObj.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        recieverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sysLog.Error(peoples[position]);
                newObj.setReceiverNameAndId(peoples[position]);
                newObj.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        typeStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newObj.setTypeStateNameAndId(states[position]);
                newObj.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        taskTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newObj.setTypeTaskNameAndId(type[position]);
                newObj.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                newObj.setLocationNameAndId(locations[position]);
                newObj.Save();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        newObj.Save();
        Button saveBut = (Button) findViewById(R.id.delButton);
        saveBut.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows dialog for getting start date
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                editTaskDescription = (EditText) findViewById(R.id.editTaskDescription1);
                editTaskName = (EditText) findViewById(R.id.editTaskName1);
                editPrioritet = (EditText) findViewById(R.id.editTextTextPersonName61);
                newObj.setName(String.valueOf(editTaskName.getText()));
                newObj.setNote(String.valueOf(editTaskDescription.getText()));
                newObj.setPrioritet(Integer.parseInt(editPrioritet.getText().toString()));
                newObj.Save();
                finish();
            }
        });
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
            newObj.addTaskDoc( doc );
            newObj.Save();
        }
        new Single().getInstance().filecount = 0;
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
        mTextView.setText(String.valueOf(seekBar.getProgress()) + "%");
        newObj.setPrioritet(67);
        newObj.Save();
    }

    /**
     * This function is not currently in use
     * @author  Terminator
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

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
