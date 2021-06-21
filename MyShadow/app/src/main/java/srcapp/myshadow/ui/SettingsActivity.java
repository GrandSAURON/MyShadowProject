/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with app settings
 * @author  Terminator
 * @version 210412_01
 */
package srcapp.myshadow.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import srcapp.myshadow.kbs.DBObject;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;


public class SettingsActivity extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private DBObject tempObj;
    private EditText editServ;
    private EditText editUserName;
    private EditText editPass;
    private EditText editGatePhone;

    /**
     * Creating function
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editServ = (EditText) findViewById(R.id.aditServer);
        editUserName = (EditText) findViewById(R.id.editUsername);
        editPass = (EditText) findViewById(R.id.editPass);
        editGatePhone = (EditText) findViewById(R.id.editTextPhone);
        Switch swi = (Switch) findViewById(R.id.switch1);

        tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_HOST_NAME);
        if (tempObj != null){
            editServ.setText(tempObj.getName());
        }
        tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_NAME);
        if (tempObj != null){
            editUserName.setText(tempObj.getName());
        }
        tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_PASS);
        if (tempObj != null){
            editPass.setText(tempObj.getName());
        }
        tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_GATE_NUMBER);
        if (tempObj != null){
            editGatePhone.setText(tempObj.getName());
        }
        tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_AUTO_SEND);
        if (tempObj != null){
            swi.setChecked(Boolean.parseBoolean(tempObj.getName()));
        }
        tempObj = DataStoreSQLite.getCurDataStore().findObjToName(DataDefine.I_TYPE_STATE_OBJ, "Отправлена исполнителю");
        if (tempObj != null){
            sysLog.Info(String.valueOf(tempObj.getId()));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == false) {
                    new Single().getInstance().mode = false;
                    new Single().getInstance().test = false;
                } else {
                    new Single().getInstance().mode = true;
                    new Single().getInstance().test = true;
                }
            }
        });
    }

    /**
     * Preparing menu
     * @param menu -- menu
     * @return true
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
            tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_HOST_NAME);
            if (tempObj != null){
                tempObj.setName(String.valueOf(editServ.getText()));
            }
            tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_NAME);
            if (tempObj != null){
                tempObj.setName(String.valueOf(editUserName.getText()));
            }
            tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_PASS);
            if (tempObj != null){
                tempObj.setName(String.valueOf(editPass.getText()));
            }
            tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_GATE_NUMBER);
            if (tempObj != null){
                tempObj.setName(String.valueOf(editGatePhone.getText()));
            }
            
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
