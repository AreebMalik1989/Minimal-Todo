package com.example.avjindersinghsekhon.minimaltodo.UI.Reminder;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.avjindersinghsekhon.minimaltodo.UI.BaseActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class ReminderActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.reminder_layout;
    }

    @NonNull
    @Override
    protected ReminderFragment createInitialFragment() {
        return ReminderFragment.newInstance();
    }


}
