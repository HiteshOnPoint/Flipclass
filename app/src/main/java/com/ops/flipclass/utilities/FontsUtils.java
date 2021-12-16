package com.ops.flipclass.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ops.flipclass.R;

import java.util.HashMap;

public class FontsUtils {

    public static final int Poppins_Bold = 0;
    public static final int Poppins_BoldItalic = 1;
    public static final int Poppins_ExtraBold = 2;
    public static final int Poppins_ExtraBoldItalic = 3;
    public static final int Poppins_ExtraLight = 4;
    public static final int Poppins_ExtraLightItalic = 5;
    public static final int Poppins_Italic = 6;
    public static final int Poppins_Light = 7;
    public static final int Poppins_LightItalic = 8;
    public static final int Poppins_Medium = 9;
    public static final int Poppins_MediumItalic = 10;
    public static final int Poppins_Regular = 11;
    public static final int Poppins_SemiBold = 12;
    public static final int Poppins_SemiBoldItalic = 13;
    public static final int Poppins_Thin = 14;
    public static final int Poppins_ThinItalic = 15;

    private static HashMap<Integer, Typeface> fontCache = new HashMap<>();

    public static void setFont(TextView element, Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.text_style);
        int textStyle = attributeArray.getInt(R.styleable.text_style_textStyle, Poppins_Regular);
        element.setTypeface(getTypeface(textStyle, context));
        attributeArray.recycle();
    }

    private static Typeface getTypeface(int fontStyle, Context context) {
        Typeface typeface = fontCache.get(fontStyle);
        if (typeface == null) {
            try {
                typeface = getFont(fontStyle, context);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontStyle, typeface);
        }
        return typeface;
    }

    private static Typeface getFont(int fontForStyle, Context context) {
        Typeface typeface = null;
        switch (fontForStyle) {
            case FontsUtils.Poppins_Bold:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-Bold.ttf");
                break;
            case FontsUtils.Poppins_BoldItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-BoldItalic.ttf");
                break;
            case FontsUtils.Poppins_ExtraBold:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-ExtraBold.ttf");
                break;
            case FontsUtils.Poppins_ExtraBoldItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-ExtraBoldItalic.ttf");
                break;
            case FontsUtils.Poppins_ExtraLight:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-ExtraLight.ttf");
                break;
            case FontsUtils.Poppins_ExtraLightItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-ExtraLightItalic.ttf");
                break;
            case FontsUtils.Poppins_Italic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-Italic.ttf");
                break;
            case FontsUtils.Poppins_Light:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-Light.ttf");
                break;
            case FontsUtils.Poppins_LightItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-LightItalic.ttf");
                break;
            case FontsUtils.Poppins_Medium:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-Medium.ttf");
                break;

            case FontsUtils.Poppins_MediumItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-MediumItalic.ttf");
                break;
            case FontsUtils.Poppins_Regular:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-Regular.ttf");
                break;
            case FontsUtils.Poppins_SemiBold:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-SemiBold.ttf");
                break;
            case FontsUtils.Poppins_SemiBoldItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-SemiBoldItalic.ttf");
                break;
            case FontsUtils.Poppins_Thin:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-Thin.ttf");
                break;
            case FontsUtils.Poppins_ThinItalic:
                typeface = Typeface.createFromAsset(context.getAssets(), "Poppins-ThinItalic.ttf");
                break;
        }
        return typeface;
    }
}
