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
public class RAPActivity extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private MyAdapter myAdapter;

    private void mainFunction() {

        /** Creating all graphics objects */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        /** Initializing all graphics objects */
        setSupportActionBar(toolbar);
        setTitle("Виртуальные агенты");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new MyAdapter(getSupportFragmentManager());
        myAdapter.add(new RAPListHelpersFragment());
        viewPager.setAdapter(myAdapter);
    }


    /**
     * Android method that is called when the application is opened
     *
     * @param savedInstanceState -- bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap);
        mainFunction();
    }

    /**
     * Adapter class for fragments
     *
     * @author Terminator
     * @version 210428_01
     * @since JDK 1.8
     */
    static class MyAdapter extends FragmentPagerAdapter {
        private final List<String> titleList = new ArrayList<String>();
        private final List<Fragment> fragmentList = new ArrayList<Fragment>();
        private FragmentManager tempManager;

        /**
         * Class constructor
         *
         * @param manager -- fragmentManager object
         */
        public MyAdapter(FragmentManager manager) {
            super(manager);
            tempManager = manager;
        }

        /**
         * Ger current item in fragment list
         *
         * @param position -- current position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        /**
         * Getting title of current page
         *
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
         *
         * @return fragmentList.size()
         */
        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * Adding new column to main view app
         *
         * @param fragment -- fragment
         */
        public void add(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}