package com.example.app_play.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by vel-4009 on 2019-09-23.
 *
 *  Love your Job
 *
 */

fun addFragment(fragmentManager: FragmentManager,
                fragment: Fragment,
                tag: String,
                frameId: Int,
                addToBackStack: Boolean)
{
    val transaction = fragmentManager.beginTransaction()

    //cross fade animation
    //        transaction.setCustomAnimations(R.anim.fade_ins,R.anim.fade_outs);
    transaction.add(frameId, fragment, tag)

    if (addToBackStack)
    {
        transaction.addToBackStack(tag)
    }
    transaction.commit()
}

fun Context.hideKeyboard(view: View?)
{
    view?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

