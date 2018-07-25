package com.manuelperera.topmovies20.presentation.base

import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity(), View {

    override fun showToast(message: String, resId: Int) {
        if (resId == -1) makeText(this, message, LENGTH_LONG).show()
        else makeText(this, getString(resId), LENGTH_LONG).show()
    }

}