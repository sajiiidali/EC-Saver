package com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.drive.Drive.Builder
import com.myECapplication.sajiiidali.ecsaver.R
import java.util.ArrayList

abstract class GoogleDriveActivity : GoogleSignInActivity() {
    fun startGoogleDriveSignIn() {
        startGoogleSignIn()
    }

    protected abstract fun onGoogleDriveSignedInSuccess(driveApi: Drive?)
    protected abstract fun onGoogleDriveSignedInFailed(exception: ApiException?)
    override val googleSignInOptions: GoogleSignInOptions
        get() {
            val scopeDriveAppFolder = Scope(Scopes.DRIVE_APPFOLDER)
            return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(scopeDriveAppFolder)
                .build()
        }

    fun initializeDriveClient(signInAccount: GoogleSignInAccount) {
        val scopes: MutableList<String> = ArrayList()
        scopes.add(DriveScopes.DRIVE_APPDATA)
        val credential: GoogleAccountCredential = GoogleAccountCredential.usingOAuth2(this, scopes)
        credential.setSelectedAccount(signInAccount.account)
        val builder: Drive.Builder = Builder(
            AndroidHttp.newCompatibleTransport(),
            GsonFactory(),
            credential
        )
        val appName = getString(R.string.app_name)
        val driveApi: Drive = builder
            .setApplicationName(appName)
            .build()
        onGoogleDriveSignedInSuccess(driveApi)
    }
}
