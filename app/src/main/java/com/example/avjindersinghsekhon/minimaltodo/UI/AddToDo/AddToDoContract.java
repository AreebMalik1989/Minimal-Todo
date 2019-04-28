package com.example.avjindersinghsekhon.minimaltodo.UI.AddToDo;

import com.example.avjindersinghsekhon.minimaltodo.UI.BasePresenter;
import com.example.avjindersinghsekhon.minimaltodo.UI.BaseView;

public class AddToDoContract {

    public interface View extends BaseView<Presenter> {
        String getTitleText();
        String getDescriptionText();
        boolean isDateSwitchOn();
        void setUserDateSpinnerLayoutVisibility(int visibility);
        void setReminderTextViewVisibility(int visibility);
        void setReminderTextViewTextColor(int color);
        void setReminderTextViewText(String text);
    }

    public interface Presenter extends BasePresenter {
        void hideKeyboard();
    }
}
