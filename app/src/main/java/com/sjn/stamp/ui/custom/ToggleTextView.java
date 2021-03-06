package com.sjn.stamp.ui.custom;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.sjn.stamp.R;


public class ToggleTextView extends AppCompatTextView {

    public boolean isBooleanValue() {
        return mBooleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        mBooleanValue = booleanValue;
    }

    private boolean mBooleanValue = false;
    Context mContext;

    public ToggleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        changeViewBackground(mBooleanValue);
    }

    public boolean performClick() {
        mBooleanValue = !mBooleanValue;
        changeViewBackground(mBooleanValue);
        return super.performClick();
    }

    public boolean callOnClick() {
        mBooleanValue = !mBooleanValue;
        changeViewBackground(mBooleanValue);
        return super.callOnClick();
    }

    private void changeViewBackground(boolean booleanValue) {
        int resourceId = booleanValue ? R.drawable.toggle_on_text : R.drawable.toggle_off_text;
        this.setBackground(ContextCompat.getDrawable(mContext, resourceId));
    }

}