/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with info menu
 * @author  Terminator
 * @version 210514_01
 */
package srcapp.myshadow.ui;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import srcapp.myshadow.service.Logger;

/**
 * Class for working with app info
 * @author  Terminator
 * @version  210514_01
 * @since    JDK 1.8
 */
public class AboutActivity extends AppCompatActivity {
    private static final Logger sysLog = new Logger(MainActivity.class);

    /**
     * Creating aii graphic of info
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
