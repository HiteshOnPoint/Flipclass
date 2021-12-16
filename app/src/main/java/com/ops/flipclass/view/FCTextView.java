package com.ops.flipclass.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ops.flipclass.utilities.FontsUtils;

/**
 * Created by Shashank Khochikar on 12/16/2021
 */
public class FCTextView extends AppCompatTextView {
    public FCTextView(Context context) {
        this(context, null);
        //FontsUtils.setFont(this,context,null);
    }

    public FCTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
        //FontsUtils.setFont(this,context,attrs);
    }

    public FCTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontsUtils.setFont(this, context, attrs);
    }
}
