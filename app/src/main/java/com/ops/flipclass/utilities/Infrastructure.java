package com.ops.flipclass.utilities;
//All common useful functions & parameters are declare here

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.ops.flipclass.R;
import com.ops.flipclass.ui.activity.BaseActivity;

import java.io.File;

public class Infrastructure {
    private static final String TAG = Infrastructure.class.getSimpleName();
    public static Dialog dialog;

    //    public static String ABOUT_US_URL = "https://www.google.com/";
//    public static String PRIVACY_POLICY_URL = "https://www.google.com/";
//    public static String COOKIE_POLICY_URL = "https://www.google.com/";
//    public static String TERMS_OF_USE_URL = "https://www.google.com/";
//    public static String FAQ_URL = "https://www.google.com/";
    public static int MY_CART_COUNT = 0;
    public static String TOKEN_INVALID_MESSAGE = "Token Invalid";
    public static String GOOGLE_PLACES_API_KEY = "AIzaSyDWI7jF12WdwKtAQW_TrCtI_2P_9EfFySI";

    private static FragmentManager mFragmentManager;
    private static BaseActivity baseActivity = null;

    public static BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public static void setBaseActivity(BaseActivity baseParentActivity) {
        baseActivity = baseParentActivity;
    }

    public static boolean isInternetPresent() {
        boolean isInternetPresent = false;
        try {
            ConnectionDetector cd = new ConnectionDetector(getBaseActivity());
            isInternetPresent = cd.isConnectingToInternet();
        } catch (Exception e) {

        }
        return isInternetPresent;
    }


    /*public static void showSuccessDialog(Context mContext, String strTitle, String subTitle, String btnTitle) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.dialog_success,null);
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext, R.style.FullScreenAlertDialogTheme).setView(view);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        TextView tv_subMsg = view.findViewById(R.id.tv_subMsg);
        TextView tv_btnAction = view.findViewById(R.id.tv_btnAction);
        tv_msg.setText(strTitle);
        tv_subMsg.setText(subTitle);
        tv_btnAction.setText(btnTitle);
        tv_btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onPositiveButtonClick();
                //ab.dismiss();
            }
        });
        //ab.create();
        ab.show();
    }*/

    public static void showProgressDialog(Context mContext) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = new Dialog(mContext, R.style.loaderDialog);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.loader);
            ImageView imgLoader = (ImageView) dialog.findViewById(R.id.imgLoader);
            Glide.with(mContext).asGif().load(R.raw.ic_loader_new).into(imgLoader);

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (NullPointerException e) {
            Log.e(TAG, "Null Pointer Exception in showProgressDialog", e);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in showProgressDialog", e);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }


    public static void dismissProgressDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
        }
    }

    public static FragmentTransaction getFragmentTransaction() {
        return getFragmentTransaction(true);
    }

    public static FragmentTransaction getFragmentTransaction(boolean is_animation) {
        if (mFragmentManager != null) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            if (is_animation) {
                ft.setCustomAnimations(R.anim.right_enter, R.anim.left_out, R.anim.left_enter, R.anim.right_out);
            }
            return ft;
        }
        return null;
    }

    public static FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public static void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public static void showToastMessage(Activity context, String string) {
        try {
            LayoutInflater inflater = context.getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast, (ViewGroup)context.findViewById(R.id.toast_layout_root));

            //ImageView image = (ImageView) layout.findViewById(R.id.image);
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(string);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            //toast.setMargin(1F,2F);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            //Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in showToastMessage", e);
        }
    }

    public static void showToastMessage1(Activity context, String string) {
        try {
            LayoutInflater inflater = context.getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast, (ViewGroup)context.findViewById(R.id.toast_layout_root));

            //ImageView image = (ImageView) layout.findViewById(R.id.image);
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(string);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.BOTTOM, 0, 350);
            //toast.setMargin(1F,2F);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            //Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in showToastMessage", e);
        }
    }

    public static void showSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.showSoftInput(activity.getCurrentFocus(), 0);//toggleSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                View view = activity.getCurrentFocus();
                if (view == null) {
                    view = new View(activity);
                }
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in hideSoftKeyboard", e);
        }
    }

    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isSoftKeyboardVisible(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        return imm.isAcceptingText();
    }

    public static String[] getAppVersion(Context context) {
        String[] AppVer = new String[2];
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            AppVer[0] = "" + info.versionCode;
            AppVer[1] = info.versionName;
        } catch (Exception e) {
            Log.e(TAG, "Exception in getAppVersion()", e);
        }
        return AppVer;
    }

    /************************************************/
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static Drawable buildCounterDrawable(int count, int drawable_image_id) {
        LayoutInflater inflater = LayoutInflater.from(getBaseActivity());
        View view = inflater.inflate(R.layout.ic_menu, null);
        view.setBackgroundResource(drawable_image_id);
        TextView textView = view.findViewById(R.id.tvCount);
        if (count <= 0) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText("" + count);
            // textView.setBackgroundResource(R.drawable.bg_circle_hifinite_yellow);
            textView.setVisibility(View.VISIBLE);
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return new BitmapDrawable(getBaseActivity().getResources(), bitmap);
    }

    public static Bitmap createBitmapFromView(int count, int imageResId) {
        LayoutInflater inflater = (LayoutInflater) getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ic_menu, null);
        ImageView imgMenuIcon = view.findViewById(R.id.imgCart);
        TextView textView = view.findViewById(R.id.tvCount);
        if (count <= 0) {
            textView.setVisibility(View.GONE);
            imgMenuIcon.setPadding(0, 0, 0, 0);
        } else {
            textView.setText("" + count);
            textView.setVisibility(View.VISIBLE);
            int dp10 = dpToPx(getBaseActivity(), getBaseActivity().getResources().getDimension(R.dimen.normal_padding));
            imgMenuIcon.setPadding(0, 20, 20, 0);
        }
        imgMenuIcon.setImageResource(imageResId);
        view.setLayoutParams(new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(c);
        return bitmap;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static float pxToDp(Context context, float px) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

//    public static String getSessionToken(Context context) {
//        if (sessionToken == null || sessionToken.equals("")) {
//            // Write code to generate Session Token
//            sessionToken = SharedPrefsUtils.getUserId(context) + "" + System.currentTimeMillis();
//        }
//        return sessionToken;
//    }

    public static int setTextColorFromAttribute(Context context, int themeColor) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(themeColor, typedValue, true);
        return ContextCompat.getColor(context, typedValue.resourceId);
    }

    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

//    public static void downloadFile(Context context, String fileName, String filePath) {
//        try {
//            if (filePath != null && !fileName.equals("")) {
//                Uri uri = Uri.parse(filePath);
//                // Create request for android download manager
//                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//
//                // set title and description
//                request.setTitle(fileName);
//                request.setDescription(String.format(context.getString(R.string.download_description), fileName));
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//                //set the local destination for download file to a path within the application's external files directory
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
//                request.setMimeType("*/*");
//                request.allowScanningByMediaScanner();
//                downloadManager.enqueue(request);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "Exception in downloadFile", e);
//        }
//    }

    public static long getFileSize(File mFile) {
        // Get length of file in bytes
        long fileSizeInBytes = mFile.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        //  Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        return fileSizeInKB / 1024;
    }

    /*public static CharSequence getAddress(UserAddress userAddress) {
        String address = "";
        String houseNo = "";
        if (!userAddress.getHouse_no().equals("")) {
            houseNo = userAddress.getHouse_no()+", ";
        }else{
            houseNo = "";
        }
        if (userAddress != null) {
            //address += userAddress.getHouse_no();
            address += houseNo;
            address += userAddress.getFull_address();
            address += "\n"+userAddress.getLandmark();
            address += "\n"+userAddress.getCity();
        }
        return address;
    }*/

//    private void showMessageAlert(Context context, String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View dialogView = inflater.inflate(R.layout.dialog_message, null);
//        builder.setView(dialogView);
//        final AlertDialog myAlertDialog = builder.create();
//        TextView mTvMessage = dialogView.findViewById(R.id.tvMessage);
//        TextView okButton = dialogView.findViewById(R.id.tvOk);
//        mTvMessage.setText(message);
//        okButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View id) {
//                myAlertDialog.dismiss();
//            }
//        });
//        myAlertDialog.show();
//    }
}
