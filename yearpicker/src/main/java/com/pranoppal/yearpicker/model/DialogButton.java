package com.pranoppal.yearpicker.model;

import com.pranoppal.yearpicker.ui.AbstractDialog;

public class DialogButton {
    private AbstractDialog.OnClickListener onClickListener;

    public DialogButton(AbstractDialog.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AbstractDialog.OnClickListener getOnClickListener() {
        return onClickListener;
    }

}
