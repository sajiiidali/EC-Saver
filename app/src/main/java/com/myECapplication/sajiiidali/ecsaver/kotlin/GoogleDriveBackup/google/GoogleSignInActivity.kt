package com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google

import android.content.Intent
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.app.AppActivity

abstract class GoogleSignInActivity : AppActivity() {
    protected abstract val googleSignInOptions: GoogleSignInOptions
    lateinit var googleSignInClient:GoogleSignInClient

    protected abstract fun onGoogleSignedInSuccess(signInAccount: GoogleSignInAccount?)
    protected abstract fun onGoogleSignedInFailed(exception: ApiException?)

    protected fun startGoogleSignIn() {
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN_REQUEST) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            onGoogleSignedInSuccess(account)
        } catch (e: ApiException) {
            onGoogleSignedInFailed(e)
        }
    }

    companion object {
        private const val GOOGLE_SIGN_IN_REQUEST = 1010
    }
}
