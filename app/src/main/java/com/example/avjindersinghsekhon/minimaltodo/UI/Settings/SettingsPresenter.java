package com.example.avjindersinghsekhon.minimaltodo.UI.Settings;

public class SettingsPresenter implements SettingsContract.Presenter {

    SettingsContract.View view;

    public SettingsPresenter(SettingsContract.View view) {
        this.view = view;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void changeMode(boolean changed, String lightTheme, String darkTheme) {
        view.themeEditorPutBoolean(changed);
        if(view.isCheckBoxPreferenceChecked()){
            view.themeEditorPutString(darkTheme);
        }else {
            view.themeEditorPutString(lightTheme);
        }
    }
}
