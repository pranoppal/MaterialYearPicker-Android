package com.pranoppal.yearpicker.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pranoppal.yearpicker.R;
import com.pranoppal.yearpicker.interfaces.DialogInterface;
import com.pranoppal.yearpicker.interfaces.OnCancelListener;
import com.pranoppal.yearpicker.interfaces.OnDismissListener;
import com.pranoppal.yearpicker.interfaces.OnShowListener;
import com.pranoppal.yearpicker.model.DialogButton;
import com.pranoppal.yearpicker.ui.adapters.NumberAdapter;
import com.pranoppal.yearpicker.ui.adapters.YearListAdapter;

public class AbstractDialog implements DialogInterface {

    //Constants
    public static final int BUTTON_POSITIVE = 1;
    public static final int BUTTON_NEGATIVE = -1;

    protected Dialog mDialog;
    protected Activity mActivity;
    protected DialogButton mPositiveButton;
    protected DialogButton mNegativeButton;
    protected int startDate;
    protected int endDate;

    protected OnDismissListener mOnDismissListener;
    protected OnCancelListener mOnCancelListener;
    protected OnShowListener mOnShowListener;


    protected AbstractDialog(@NonNull Activity mActivity,
                             @NonNull DialogButton mPositiveButton,
                             @NonNull DialogButton mNegativeButton,
                             int startDate,
                             int endDate) {
        this.mActivity = mActivity;
        this.mPositiveButton = mPositiveButton;
        this.mNegativeButton = mNegativeButton;
        this.endDate = endDate;
        this.startDate = startDate;
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        RecyclerView recyclerView = dialogView.findViewById(R.id.rv_year_list);
        YearListAdapter yearListAdapter = new YearListAdapter(getYearsList());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(yearListAdapter);
        yearListAdapter.notifyDataSetChanged();
//        GridView gridView = dialogView.findViewById(R.id.year_grid_view);
//        NumberAdapter numberAdapter = new NumberAdapter(mActivity,startDate, endDate);
//        gridView.setAdapter(numberAdapter);

        return dialogView;
    }

    private ArrayList<Integer> getYearsList(){
        ArrayList<Integer> years = new ArrayList<>();
        years.add(2000);
        years.add(2001);
        years.add(2002);
        years.add(2003);
        years.add(2004);
        years.add(2005);
        years.add(2006);
        years.add(2007);
        return years;
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
