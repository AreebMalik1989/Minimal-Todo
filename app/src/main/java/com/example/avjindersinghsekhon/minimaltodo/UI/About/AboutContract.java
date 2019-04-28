package com.example.avjindersinghsekhon.minimaltodo.UI.About;

import com.example.avjindersinghsekhon.minimaltodo.UI.BasePresenter;
import com.example.avjindersinghsekhon.minimaltodo.UI.BaseView;

public class AboutContract {

    public interface View extends BaseView<Presenter> {

        void showVersion();
        void setContactMe();

    }

    public interface Presenter extends BasePresenter {

    }
}
