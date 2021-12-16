package com.ops.flipclass.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ops.flipclass.utilities.FontsUtils;

/**
 * Created by Shashank Khochikar on 12/16/2021
 */
public class FCEditText extends AppCompatTextView {
    public FCEditText(Context context) {
        this(context, null);
        //FontsUtils.setFont(this,context,null);
    }

    public FCEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        //FontsUtils.setFont(this,context,attrs);
    }

    public FCEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontsUtils.setFont(this, context, attrs);
    }
}
