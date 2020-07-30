package com.pranoppal.yearpicker;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pranoppal.yearpicker.interfaces.DialogInterface;
import com.pranoppal.yearpicker.interfaces.OnCancelListener;
import com.pranoppal.yearpicker.interfaces.OnDismissListener;
import com.pranoppal.yearpicker.interfaces.OnShowListener;
import com.pranoppal.yearpicker.model.DialogButton;

public class AbstractDialog implements DialogInterface {

    //Constants
    public static final int BUTTON_POSITIVE = 1;
    public static final int BUTTON_NEGATIVE = -1;

    protected Dialog mDialog;
    protected Activity mActivity;
    protected DialogButton mPositiveButton;
    protected DialogButton mNegativeButton;

    protected OnDismissListener mOnDismissListener;
    protected OnCancelListener mOnCancelListener;
    protected OnShowListener mOnShowListener;


    protected AbstractDialog(@NonNull Activity mActivity,
                             @NonNull DialogButton mPositiveButton,
                             @NonNull DialogButton mNegativeButton) {
        this.mActivity = mActivity;
        this.mPositiveButton = mPositiveButton;
        this.mNegativeButton = mNegativeButton;
    }

    protected View createView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.main_calendar, container, false);

        // Set Positive Button

        dialogView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPositiveButton.getOnClickListener().onClick(AbstractDialog.this, BUTTON_POSITIVE);
            }
        });

        // Set Negative Button

        dialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNegativeButton.getOnClickListener().onClick(AbstractDialog.this, BUTTON_NEGATIVE);
            }
        });


//        // Apply Styles
//        TypedArray a = mActivity.getTheme().obtainStyledAttributes(R.styleable.MaterialDialog);
//
//        try {
//            // Set Dialog Background
//            dialogView.setBackgroundColor(
//                    a.getColor(R.styleable.MaterialDialog_material_dialog_background,
//                            mActivity.getResources().getColor(R.color.material_dialog_background)));
//
//            // Set Title Text Color
//            mTitleView.setTextColor(
//                    a.getColor(R.styleable.MaterialDialog_material_dialog_title_text_color,
//                            mActivity.getResources().getColor(R.color.material_dialog_title_text_color)));
//
//            // Set Message Text Color
//            mMessageView.setTextColor(
//                    a.getColor(R.styleable.MaterialDialog_material_dialog_message_text_color,
//                            mActivity.getResources().getColor((R.color.material_dialog_message_text_color))));
//
//            // Set Positive Button Icon Tint
//            ColorStateList mPositiveButtonTint = a.getColorStateList(
//                    R.styleable.MaterialDialog_material_dialog_positive_button_text_color);
//
//            if (mPositiveButtonTint == null) {
//                mPositiveButtonTint = ContextCompat.getColorStateList(
//                        mActivity.getApplicationContext(),
//                        R.color.material_dialog_positive_button_text_color);
//            }
//            mPositiveButtonView.setTextColor(mPositiveButtonTint);
//            mPositiveButtonView.setIconTint(mPositiveButtonTint);
//
//            // Set Negative Button Icon & Text Tint
//            ColorStateList mNegativeButtonTint = a.getColorStateList(
//                    R.styleable.MaterialDialog_material_dialog_negative_button_text_color);
//
//            if (mNegativeButtonTint == null) {
//                mNegativeButtonTint = ContextCompat.getColorStateList(
//                        mActivity.getApplicationContext(),
//                        R.color.material_dialog_negative_button_text_color);
//            }
//            mNegativeButtonView.setIconTint(mNegativeButtonTint);
//            mNegativeButtonView.setTextColor(mNegativeButtonTint);
//
//            // Set Positive Button Background Tint
//            ColorStateList mBackgroundTint = a.getColorStateList(
//                    R.styleable.MaterialDialog_material_dialog_positive_button_color);
//
//            if (mBackgroundTint == null) {
//                mBackgroundTint = ContextCompat.getColorStateList(
//                        mActivity.getApplicationContext(),
//                        R.color.material_dialog_positive_button_color);
//            }
//            mPositiveButtonView.setBackgroundTintList(mBackgroundTint);
//            if (mBackgroundTint != null) {
//                mNegativeButtonView.setRippleColor(mBackgroundTint.withAlpha(75));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            a.recycle();
//        }

        return dialogView;
    }

    /**
     * Displays the Dialog
     */
    public void show() {
        if (mDialog != null) {
            mDialog.show();
        } else {
            throwNullDialog();
        }
    }

    /**
     * Cancels the Dialog
     */
    @Override
    public void cancel() {
        if (mDialog != null) {
            mDialog.cancel();
        } else {
            throwNullDialog();
        }
    }

    /**
     * Dismisses the Dialog
     */
    @Override
    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        } else {
            throwNullDialog();
        }
    }

    /**
     * @param onShowListener interface for callback events when dialog is showed.
     */
    public void setOnShowListener(@NonNull final OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;

        mDialog.setOnShowListener(new android.content.DialogInterface.OnShowListener() {
            @Override
            public void onShow(android.content.DialogInterface dialogInterface) {
                showCallback();
            }
        });
    }

    /**
     * @param onCancelListener interface for callback events when dialog is cancelled.
     */
    public void setOnCancelListener(@NonNull final OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;

        mDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(android.content.DialogInterface dialogInterface) {
                cancelCallback();
            }
        });
    }

    /**
     * @param onDismissListener interface for callback events when dialog is dismissed;
     */
    public void setOnDismissListener(@NonNull final OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;

        mDialog.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(android.content.DialogInterface dialogInterface) {
                dismissCallback();
            }
        });
    }


    private void showCallback() {
        if (mOnShowListener != null) {
            mOnShowListener.onShow(this);
        }
    }

    private void dismissCallback() {
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(this);
        }
    }

    private void cancelCallback() {
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(this);
        }
    }

    private void throwNullDialog() {
        throw new NullPointerException("Called method on null Dialog. Create dialog using `Builder` before calling on Dialog");
    }

    public interface OnClickListener {
        void onClick(DialogInterface dialogInterface, int which);
    }
}
