/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with app settings
 * @author Terminator
 * @version 210412_01
 */
package srcapp.myshadow.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import srcapp.myshadow.kbs.DBObject;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.GateOpenClass;
import srcapp.myshadow.service.Logger;


public class GatesOpenActivity extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private DBObject tempObj;

    /**
     * Creating function
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gates);

        Context context = this;
        Button openButton = (Button) findViewById(R.id.button5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows dialog for getting start time
             *
             * @param v -- view object
             * @author Terminator
             */
            @Override
            public void onClick(View v) {
                if (hasConnection(context) == true) {
                    GateOpenClass opener = new GateOpenClass();
                    opener.open();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ворота открываются", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (hasConnection(context) == false) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Нет подключения к сети", Toast.LENGTH_SHORT);
                    toast.show();
                    String phoneNo = null;
                    tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_HOST_NAME);
                    if (tempObj != null){
                        phoneNo = tempObj.getName();
                    }
                    if (!TextUtils.isEmpty(phoneNo)) {
                        String dial = "tel:" + phoneNo;
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                "Ворота открываются", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "Нет подключения к интернету или сотовой связи", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
    }

    /**
     * Checking your internet connection
     * @param context -- current context
     * @return
     */
    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}
