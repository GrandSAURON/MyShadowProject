/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Main Classes
 * @author  Terminator
 * @version 210428_01
 */
package srcapp.myshadow.ui;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;

/**
 * Main class in hole project
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class MainActivity extends AppCompatActivity{
    private static final Logger sysLog = new Logger(MainActivity.class);
    private  MyAdapter myAdapter;
    private DrawerLayout mDrawerLayout;
    private DataStoreSQLite dataStoreSQLite = null;

    private void mainFunction(){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,"profile.jpg");
        /** Creating new SQLite database */
        dataStoreSQLite = new DataStoreSQLite(this);
        dataStoreSQLite.open();

        /** Creating all graphics objects */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        sysLog.Info("All graphics objects created");

        /** Initializing all graphics objects */
        setSupportActionBar(toolbar);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        myAdapter.add(new ListContentFragment(myAdapter), "Список");
        myAdapter.add(new TileContentFragment(), "Плитки");
        myAdapter.add(new CardContentFragment(), "Карточки");
        viewPager.setAdapter(myAdapter);
        tabs.setupWithViewPager(viewPager);
        sysLog.Info("All graphics objects initialized");
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Opening a new activity to create a task
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                sysLog.Info("The function for creating a new task is selected");
                Intent intent = new Intent(MainActivity.this, CreateNewTask.class);
                startActivityForResult(intent,1);
            }
        });
    }

    /**
     * Requesting app permissions
     */
    public void requestMultiplePermissions() {
        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                },
                0);
    }

    /**
     * Requesting app permissions
     * @param requestCode -- special code
     * @param permissions -- permissions
     * @param grantResults -- results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 0 && grantResults.length == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mainFunction();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Android method that is called when the application is opened
     * @param savedInstanceState -- bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My shadow");

        int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionStatus1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionStatus2 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED &&
                permissionStatus1 == PackageManager.PERMISSION_GRANTED &&
                permissionStatus2 == PackageManager.PERMISSION_GRANTED) {
            mainFunction();
        } else {
            requestMultiplePermissions();
        }
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
        this.invalidateOptionsMenu();
        if(requestCode == 1){
            myAdapter.refresh();
        }
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

    /**
     * Menu item selected
     * @param item -- menu item
     * @return super.onOptionsItemSelected(item)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_publish){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menuPurchasesListNewRecord){
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent,0);
        }else if(item.getItemId() == R.id.menu_send){
            if(hasConnection(this)){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешно отправлено", Toast.LENGTH_SHORT);
                toast.show();
                DataStoreSQLite.getCurDataStore().sendDataIntoMainServer();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Нет подключения к сети", Toast.LENGTH_SHORT);
                toast.show();
            }
        }else if(item.getItemId() == R.id.menu_helpers){
            //RAPListHelpersFragment helpersList = new RAPListHelpersFragment();
            Intent intent = new Intent(MainActivity.this, RAPActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Actions when menu creating
     * @param menu -- menu
     * @return super.onCreateOptionsMenu(menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
       // DBObject tempObj = DataStoreSQLite.getCurDataStore().findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_AUTO_SEND);
        //if(Boolean.valueOf(tempObj.getName())){
           /// menu.getItem(0).setVisible(true);
        //}else{
       //   ///  menu.getItem(0).setVisible(false);
       // }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Adapter class for fragments
     * @author  Terminator
     * @version  210428_01
     * @since    JDK 1.8
     */
    static class MyAdapter extends FragmentPagerAdapter {
        private final List<String> titleList = new ArrayList<String>();
        private final List<Fragment> fragmentList = new ArrayList<Fragment>();
        private FragmentManager tempManager;

        /**
         * Class constructor
         * @param manager -- fragmentManager object
         */
        public MyAdapter(FragmentManager manager) {
            super(manager);
            tempManager = manager;
        }

        /**
         * Ger current item in fragment list
         * @param position -- current position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        /**
         * Getting title of current page
         * @param position -- current position
         * @return titleList.get(position)
         */
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        /**
         * Size of array
         * @return fragmentList.size()
         */
        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * Adding new column to main view app
         * @param fragment -- fragment
         * @param title menu title
         */
        public void add(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        /**
         * Refreshing adapter
         */
        public void refresh(){
            for(int i = 0; i < fragmentList.size(); i++){
                tempManager.beginTransaction()
                        .detach(fragmentList.get(i))
                        .commitNowAllowingStateLoss();


                tempManager.beginTransaction()
                        .attach(fragmentList.get(i))
                        .commitAllowingStateLoss();
            }
        }
    }

    /**
     * Actions when class destroying
     * @author  Terminator
     */
    protected void onDestroy() {
        if (dataStoreSQLite != null){
            dataStoreSQLite.close();
        }
        super.onDestroy();
    }
}