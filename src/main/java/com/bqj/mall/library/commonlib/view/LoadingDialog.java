package com.bqj.mall.library.commonlib.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bqj.mall.library.commonlib.R;
import com.github.ybq.android.spinkit.SpinKitView;

/**
 * @author Vondear
 * @date 2017/3/16
 */

public class LoadingDialog extends Dialog {

    private View mDialogContentView;
    private TextView mTextView;
    private SpinKitView spinKitView;


    public LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);
        initView(context);
    }

    public LoadingDialog(Activity context) {
        super(context, R.style.LoadingDialogStyle);
        initView(context);
    }


    private void initView(Context context) {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        spinKitView = (SpinKitView) mDialogContentView.findViewById(R.id.spinkit_view);
        mTextView = (TextView) mDialogContentView.findViewById(R.id.name);
        setContentView(mDialogContentView);
    }

    public void setLoadingText(CharSequence charSequence) {
        mTextView.setText(charSequence);
    }


    public View getDialogContentView() {
        return mDialogContentView;
    }

    public TextView getTextView() {
        return mTextView;
    }

}
