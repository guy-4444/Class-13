package com.guy.class13;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeekView mWeekView = (WeekView) findViewById(R.id.weekView);

        mWeekView.setHorizontalFlingEnabled(false);
        mWeekView.setVerticalFlingEnabled(false);
        mWeekView.setXScrollingSpeed(0);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        mWeekView.goToDate(calendar);

        mWeekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {
                Log.d("pttt", event.getName());
            }
        });

        mWeekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                Log.d("pttt","onMonthChange newYear="  + newYear + " newMonth=" + newMonth);


                    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

                    Calendar calendar = Calendar.getInstance();
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.add(Calendar.HOUR, 1);
                    WeekViewEvent someEvent = new WeekViewEvent(1,"someEvent",calendar,calendar1);
                    events.add(someEvent);


                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.YEAR, newYear);
                    startTime.set(Calendar.MONTH, newMonth - 1);


                    Calendar endTime = (Calendar) startTime.clone();
                    endTime.set(Calendar.YEAR, newYear);
                    endTime.set(Calendar.MONTH, newMonth - 1);
                    endTime.add(Calendar.HOUR, 2);
                    WeekViewEvent event1 = new WeekViewEvent(2, "My First Event", startTime, endTime);
                    event1.setColor(getResources().getColor(R.color.colorPrimaryDark));
                    events.add(event1);

                    startTime = Calendar.getInstance();
                    startTime.set(Calendar.YEAR, newYear);
                    startTime.set(Calendar.MONTH, newMonth - 1);
                    startTime.add(Calendar.DAY_OF_YEAR, 1);
                    endTime = (Calendar) startTime.clone();
                    endTime.set(Calendar.YEAR, newYear);
                    endTime.set(Calendar.MONTH, newMonth - 1);
                    endTime.add(Calendar.HOUR, 4);
                    WeekViewEvent event2 = new WeekViewEvent(10, "My Second Event", startTime, endTime);
                    event2.setColor(getResources().getColor(R.color.colorPrimary));
                    events.add(event2);

                    return events;
            }
        });
    }
}
