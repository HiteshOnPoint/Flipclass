package com.ops.flipclass.ui.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ops.flipclass.R
import com.ops.flipclass.utilities.ErrorStatus
import com.ops.flipclass.utilities.Infrastructure


open class BaseActivity : AppCompatActivity() {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus
        if (v != null &&
            (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) &&
            v is EditText &&
            !v.javaClass.name.startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            v.getLocationOnScreen(scrcoords)
            val x = ev.rawX + v.getLeft() - scrcoords[0]
            val y = ev.rawY + v.getTop() - scrcoords[1]
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                Infrastructure.hideSoftKeyboard(this)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out)
        Infrastructure.setBaseActivity(this)
    }

    override fun onStart() {
        super.onStart()
        Infrastructure.setBaseActivity(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity)
    }

    open fun setInfoViewSuccess(
        mDataView: View?,
        mErrorView: View?
    ) {
        try {
            setInfoView(
                ErrorStatus.OK, mDataView!!, mErrorView!!, null, null, null,
                0, "", false
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setInfoView(
        statusCode: Int,
        mDataView: View,
        mErrorView: View,
        mImgError: ImageView?,
        mTvMessage: TextView?,
        mLlRetry: View?,
        mErrorIconId: Int,
        mMessage: String?,
        isRetry: Boolean
    ) {

        var mErrorIconId = mErrorIconId
        var mMessage = mMessage

        try {
            when (statusCode) {
                ErrorStatus.OK -> {
                    mDataView.visibility = View.VISIBLE
                    mErrorView.visibility = View.GONE
                    mErrorIconId = 0
                    mMessage = ""
                }
                ErrorStatus.BAD_REQUEST -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage

                }
                ErrorStatus.UN_AUTHORIZED -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
                ErrorStatus.FORBIDDEN -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage

                }
                ErrorStatus.PAGE_NOT_FOUND -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
                ErrorStatus.METHOD_NOT_ALLOWED -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
                ErrorStatus.INTERNAL_SERVER_ERROR -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
                ErrorStatus.BAD_GATEWAY -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
                ErrorStatus.GATEWAY_TIME_OUT -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
                else -> {
                    mDataView.visibility = View.GONE
                    mErrorView.visibility = View.VISIBLE
                    mTvMessage!!.text = mMessage
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}