package com.example.avjindersinghsekhon.minimaltodo.UI.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;

import com.example.avjindersinghsekhon.minimaltodo.MinimalToDo;
import com.example.avjindersinghsekhon.minimaltodo.UI.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Utility.PreferenceKeys;

public class SettingsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener, SettingsContract.View {

    private SettingsContract.Presenter presenter;

    private CheckBoxPreference checkBoxPreference;
    private SharedPreferences.Editor themeEditor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_layout);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PreferenceKeys preferenceKeys = new PreferenceKeys(getResources());
        if (key.equals(preferenceKeys.night_mode_pref_key)) {
            SharedPreferences themePreferences = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, Context.MODE_PRIVATE);
            themeEditor = themePreferences.edit();
            themeEditor.putBoolean(MainFragment.RECREATE_ACTIVITY, true);

            checkBoxPreference = (CheckBoxPreference) findPreference(preferenceKeys.night_mode_pref_key);

            presenter.changeMode(true, MainFragment.LIGHTTHEME, MainFragment.DARKTHEME);

            themeEditor.apply();

            getActivity().recreate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isCheckBoxPreferenceChecked() {
        return checkBoxPreference.isChecked();
    }

    @Override
    public void themeEditorPutBoolean(boolean value) {
        themeEditor.putBoolean(MainFragment.RECREATE_ACTIVITY, value);
    }

    @Override
    public void themeEditorPutString(String value) {
        themeEditor.putString(MainFragment.THEME_SAVED, value);
    }
}
