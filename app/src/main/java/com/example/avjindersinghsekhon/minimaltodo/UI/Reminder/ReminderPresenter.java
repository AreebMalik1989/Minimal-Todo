package com.example.avjindersinghsekhon.minimaltodo.UI.Reminder;

import android.graphics.Color;

import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.UI.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.Utility.StoreRetrieveData;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ReminderPresenter implements ReminderContract.Presenter {

    private ReminderContract.View view;

    public ReminderPresenter(ReminderContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public int valueForSpinner(int value) {
        switch (value) {
            case 0:
                return 10;
            case 1:
                return 30;
            case 2:
                return 60;
            default:
                return 0;
        }
    }

    @Override
    public Date addTimeToDate(int mins) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, mins);
        return calendar.getTime();
    }

    @Override
    public void saveData(StoreRetrieveData storeRetrieveData, ArrayList<ToDoItem> mToDoItems) {
        try {
            storeRetrieveData.saveToFile(mToDoItems);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTheme(String theme) {
        if (theme.equals(MainFragment.LIGHTTHEME)) {
            view.setTheme(R.style.CustomStyle_LightTheme);
        } else {
            view.setTheme(R.style.CustomStyle_DarkTheme);
        }
    }

    @Override
    public ToDoItem findToDoItem(ArrayList<ToDoItem> mToDoItems, UUID id) {

        for (ToDoItem toDoItem : mToDoItems) {
            if (toDoItem.getIdentifier().equals(id)) {
                return toDoItem;
            }
        }

        return null;
    }

    @Override
    public void setSnoozeTextView(String theme) {
        if (theme.equals(MainFragment.LIGHTTHEME)) {
            view.setSnoozeTextViewColor(ReminderFragment.SECONDARY_COLOR);
        } else {
            view.setSnoozeTextViewColor(Color.WHITE);
            view.setSnoozeTextViewDrawable();
        }
    }
}
