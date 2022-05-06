package com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.app

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class AppActivity : AppCompatActivity() {
    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected fun showMessage(@StringRes res: Int) {
        showMessage(getString(res))
    }
}