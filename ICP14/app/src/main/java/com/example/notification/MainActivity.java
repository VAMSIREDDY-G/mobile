// Created by Vijaya Yeruva on 11/20/2020
// Reference: https://developer.android.com/guide/topics/ui/notifiers/notifications

package com.example.notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    TextView dateView;
    CalendarView calendarView;

    int MONTH, DAY, YEAR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dateView = findViewById(R.id.dateView);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setDate(Calendar.getInstance().getTimeInMillis());

        final DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        dateView.setText(df.format(calendarView.getDate()));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
                YEAR = year; MONTH = month; DAY = dayOfMonth;

                dateView.setText(df.format(date));
            }
        });

        }


        public void open(View view){
            Calendar cal = Calendar.getInstance();

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                    Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    YEAR = year; MONTH = month; DAY = dayOfMonth;

                }
            });

            //used to open google calenar app
            Intent intent = new Intent(Intent.ACTION_INSERT,
                    CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE, "write Event Name here!");
            intent.putExtra(CalendarContract.Events.DESCRIPTION,
                    "Android Coding");
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                    "UMKC SCE");
            Calendar startTime = Calendar.getInstance();
            startTime.set(YEAR, MONTH, DAY);
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                    startTime.getTimeInMillis());
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
            // Use the Calendar app to add the new event.
            startActivity(intent);
        }
    }
