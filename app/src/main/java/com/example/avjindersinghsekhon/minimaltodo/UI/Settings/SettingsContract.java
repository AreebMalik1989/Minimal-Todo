package com.example.avjindersinghsekhon.minimaltodo.UI.Settings;

import com.example.avjindersinghsekhon.minimaltodo.UI.BasePresenter;
import com.example.avjindersinghsekhon.minimaltodo.UI.BaseView;

public class SettingsContract {

    public interface View extends BaseView<Presenter> {
        boolean isCheckBoxPreferenceChecked();
        void themeEditorPutBoolean(boolean value);
        void themeEditorPutString(String value);
    }

    public interface Presenter extends BasePresenter {
        void changeMode(boolean changed, String lightTheme, String darkTheme);
    }
}
