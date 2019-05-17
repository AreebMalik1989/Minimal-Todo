package com.example.avjindersinghsekhon.minimaltodo.UI.Reminder;

import com.example.avjindersinghsekhon.minimaltodo.UI.BasePresenter;
import com.example.avjindersinghsekhon.minimaltodo.UI.BaseView;
import com.example.avjindersinghsekhon.minimaltodo.Utility.StoreRetrieveData;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ReminderContract {

    public interface View extends BaseView<Presenter> {
        void setTheme(int theme);
        void setSnoozeTextViewColor(int color);
        void setSnoozeTextViewDrawable();

    }

    public interface Presenter extends BasePresenter {
        int valueForSpinner(int value);
        Date addTimeToDate(int mins);
        void saveData(StoreRetrieveData storeRetrieveData, ArrayList<ToDoItem> mToDoItems);
        void setTheme(String theme);
        ToDoItem findToDoItem(ArrayList<ToDoItem> mToDoItems, UUID id);
        void setSnoozeTextView(String theme);
    }
}
