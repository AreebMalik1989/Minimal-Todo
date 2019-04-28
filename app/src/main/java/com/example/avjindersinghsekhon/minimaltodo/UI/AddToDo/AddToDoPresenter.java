package com.example.avjindersinghsekhon.minimaltodo.UI.AddToDo;

public class AddToDoPresenter implements AddToDoContract.Presenter {

    AddToDoContract.View view;

    public AddToDoPresenter(AddToDoContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void hideKeyboard() {

    }
}
