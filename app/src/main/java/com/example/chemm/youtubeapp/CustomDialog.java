package com.example.chemm.youtubeapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;


import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialog extends Dialog {

    private ICustomDialogEventListener listener;

    @OnClick(R.id.dialog_ok)
    public void onClick(){
        listener.onClickListener();
        dismiss();
    }

    public interface ICustomDialogEventListener {
        public void onClickListener();
    }

    public CustomDialog(@NonNull Context context, ICustomDialogEventListener listener) {
        super(context, R.style.Theme_AppCompat_DayNight_Dialog);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
    }
}