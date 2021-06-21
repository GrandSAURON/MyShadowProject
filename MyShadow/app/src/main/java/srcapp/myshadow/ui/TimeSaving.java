/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with time
 * @author  Terminator
 * @version 210419_01
 */
package srcapp.myshadow.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

/**
 * A class for working with time. Select the time
 * and date using android widgets. Display in a
 * special field. Select the start of the task and the end.
 * @author   Terminator
 * @version  2104219_01
 * @since    JDK 1.8
 */
public class TimeSaving extends Activity {
    /** Special flags for choosing date or time */
    int DIALOG_TIME = 1;
    int DIALOG_DATE = 2;
    int OUTPUT_FLAG = 0;

    /** Variables for saving the current time and date */
    int myHour = Calendar.getInstance().get(Calendar.HOUR);
    int myMinute = Calendar.getInstance().get(Calendar.MINUTE);
    int myYear = Calendar.getInstance().get(Calendar.YEAR);
    int myMonth = Calendar.getInstance().get(Calendar.MONTH);
    int myDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    /** All text view objects */
    TextView textViewTime;
    TextView textViewDate;
    TextView textViewTimeEnd;
    TextView textViewDateEnd;

    /**
     * Required method that is called when creating an
     * object of the class
     * @author  Terminator
     * @param savedInstanceState -- bundle object
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_change);

        /** Initializing objects of text view */
        textViewTime = (TextView) findViewById(R.id.textViewTimeDateStartShow);
        textViewDate = (TextView) findViewById(R.id.textViewTimeDateStartShow2);
        textViewTimeEnd = (TextView) findViewById(R.id.textViewTimeDateEndShow);
        textViewDateEnd = (TextView) findViewById(R.id.textViewTimeDateEndShow2);

        /** Initializing objects of all buttons */
        Button timeButton = (Button) findViewById(R.id.timeChangeButton);
        Button dateButton = (Button) findViewById(R.id.dateChangeButton);
        Button timeButton1 = (Button) findViewById(R.id.timeChangeButtonSec);
        Button dateButton1 = (Button) findViewById(R.id.dateChangeButtonSec);
        Button exitActivityBut = (Button) findViewById(R.id.exitTimeActivityButton);

        timeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows dialog for getting start time
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TIME);
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows dialog for getting start date
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        timeButton1.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows dialog for getting end time
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                OUTPUT_FLAG = 1;
                showDialog(DIALOG_TIME);
            }
        });

        dateButton1.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows dialog for getting end date
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                OUTPUT_FLAG = 1;
                showDialog(DIALOG_DATE);
            }
        });

        exitActivityBut.setOnClickListener(new View.OnClickListener() {
            /**
             * Finishes this activity
             * @author  Terminator
             * @param v -- view object
             */
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Creating new dialog
     * @author  Terminator
     * @param id -- flag state for selecting between date and time
     * @return super.onCreateDialog(id)
     */
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog timeDialog = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return timeDialog;
        }
        if (id == DIALOG_DATE) {
            DatePickerDialog dateDialog = new DatePickerDialog(this, yCallBack, myYear, myMonth, myDay);
            return dateDialog;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        /**
         * Setting time using android widget
         * @author  Terminator
         * @param view -- widget
         * @param hourOfDay -- choosing hour
         * @param minute -- choosing minute
         */
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            if(OUTPUT_FLAG == 1){
                textViewTimeEnd.setText("Время: " + myHour + " часов " + myMinute + " минут");
            }
            if(OUTPUT_FLAG == 0){
                textViewTime.setText("Время: " + myHour + " часов " + myMinute + " минут");
            }
        }
    };

    DatePickerDialog.OnDateSetListener yCallBack = new DatePickerDialog.OnDateSetListener() {
        /**
         *
         * @author  Terminator
         * @param view -- widget
         * @param year -- choosing year
         * @param monthOfYear -- choosing month
         * @param dayOfMonth -- choosing day
         */
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            if(OUTPUT_FLAG == 1){
                textViewDateEnd.setText("Дата: " + myDay + "/" + myMonth + "/" + myYear);
            }
            if(OUTPUT_FLAG == 0){
                textViewDate.setText("Дата: " + myDay + "/" + myMonth + "/" + myYear);
            }
        }
    };
}
