package com.example.avjindersinghsekhon.minimaltodo.AddToDo;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.DefaultPresenter;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.DefaultView;

public class AddToDoContract {

    public interface View extends DefaultView<Presenter> {
        String getTitleText();
        String getDescriptionText();
        boolean isDateSwitchOn();
        void setUserDateSpinnerLayoutVisibility(int visibility);
        void setReminderTextViewVisibility(int visibility);
        void setReminderTextViewTextColor(int color);
        void setReminderTextViewText(String text);
    }

    public interface Presenter extends DefaultPresenter {
        void hideKeyboard();
    }
}
