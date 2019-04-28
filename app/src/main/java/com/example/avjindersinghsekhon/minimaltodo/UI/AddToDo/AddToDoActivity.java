package com.example.avjindersinghsekhon.minimaltodo.UI.AddToDo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.avjindersinghsekhon.minimaltodo.UI.BaseActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class AddToDoActivity extends BaseActivity {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_add_to_do;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return AddToDoFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

