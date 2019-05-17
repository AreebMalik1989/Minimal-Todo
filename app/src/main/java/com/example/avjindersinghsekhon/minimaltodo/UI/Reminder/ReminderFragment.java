package com.example.avjindersinghsekhon.minimaltodo.UI.Reminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.avjindersinghsekhon.minimaltodo.MinimalToDo;
import com.example.avjindersinghsekhon.minimaltodo.UI.BaseFragment;
import com.example.avjindersinghsekhon.minimaltodo.UI.Main.MainActivity;
import com.example.avjindersinghsekhon.minimaltodo.UI.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Utility.StoreRetrieveData;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.TodoNotificationService;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import fr.ganfra.materialspinner.MaterialSpinner;

import static android.content.Context.MODE_PRIVATE;

public class ReminderFragment extends BaseFragment implements ReminderContract.View{

    public static final String EXIT = "com.avjindersekhon.exit";

    public static int SECONDARY_COLOR;

    private ReminderContract.Presenter presenter;

    private TextView mtoDoTextTextView;
    private Button mRemoveToDoButton;
    private MaterialSpinner mSnoozeSpinner;
    private String[] snoozeOptionsArray;
    private StoreRetrieveData storeRetrieveData;
    private ArrayList<ToDoItem> mToDoItems;
    private ToDoItem mItem;
    private TextView mSnoozeTextView;
    private String theme;
    private MinimalToDo app;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SECONDARY_COLOR = getResources().getColor(R.color.secondary_text);
        
        app = (MinimalToDo) getActivity().getApplication();

        theme = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
        presenter.setTheme(theme);

        storeRetrieveData = new StoreRetrieveData(getContext(), MainFragment.FILENAME);
        mToDoItems = MainFragment.getLocallyStoredData(storeRetrieveData);

        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

        Intent i = getActivity().getIntent();
        UUID id = (UUID) i.getSerializableExtra(TodoNotificationService.TODOUUID);
        mItem = presenter.findToDoItem(mToDoItems, id);

        snoozeOptionsArray = getResources().getStringArray(R.array.snooze_options);

        mRemoveToDoButton = (Button) view.findViewById(R.id.toDoReminderRemoveButton);
        mtoDoTextTextView = (TextView) view.findViewById(R.id.toDoReminderTextViewBody);
        mSnoozeTextView = (TextView) view.findViewById(R.id.reminderViewSnoozeTextView);
        mSnoozeSpinner = (MaterialSpinner) view.findViewById(R.id.todoReminderSnoozeSpinner);

//        mtoDoTextTextView.setBackgroundColor(item.getTodoColor());
        mtoDoTextTextView.setText(mItem.getToDoText());

        presenter.setSnoozeTextView(theme);

        mRemoveToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToDoItems.remove(mItem);
                changeOccurred();
                saveData();
                closeApp();
//                finish();
            }
        });


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, snoozeOptionsArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text_view, snoozeOptionsArray);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        mSnoozeSpinner.setAdapter(adapter);
//        mSnoozeSpinner.setSelection(0);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_reminder;
    }

    private void closeApp() {
        Intent i = new Intent(getContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.putExtra(EXIT, true);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainFragment.SHARED_PREF_DATA_SET_CHANGED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EXIT, true);
        editor.apply();
        startActivity(i);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_reminder, menu);
        return true;
    }

    private void changeOccurred() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainFragment.SHARED_PREF_DATA_SET_CHANGED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MainFragment.CHANGE_OCCURED, true);
//        editor.commit();
        editor.apply();
    }

    private int valueFromSpinner() {
        return presenter.valueForSpinner(mSnoozeSpinner.getSelectedItemPosition());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toDoReminderDoneMenuItem:
                Date date = presenter.addTimeToDate(valueFromSpinner());
                mItem.setToDoDate(date);
                mItem.setHasReminder(true);
                Log.d("OskarSchindler", "Date Changed to: " + date);
                changeOccurred();
                saveData();
                closeApp();
                //foo
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static ReminderFragment newInstance() {
        return new ReminderFragment();
    }

    private void saveData() {
        presenter.saveData(storeRetrieveData, mToDoItems);
    }

    @Override
    public void setPresenter(ReminderContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setTheme(int theme) {
        getActivity().setTheme(theme);
    }

    @Override
    public void setSnoozeTextViewColor(int color) {
        mSnoozeTextView.setTextColor(color);
    }

    @Override
    public void setSnoozeTextViewDrawable() {
        mSnoozeTextView.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_snooze_white_24dp, 0, 0, 0);
    }
}
