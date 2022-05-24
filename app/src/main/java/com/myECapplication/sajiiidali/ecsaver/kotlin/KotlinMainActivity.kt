package com.myECapplication.sajiiidali.ecsaver.kotlin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.api.services.drive.Drive
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.Database
import com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google.GoogleDriveActivity
import com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google.GoogleDriveApiDataRepository
import java.io.File

//com.myECapplication.sajiiidali.ecsaver
//83:EC:FD:57:75:67:E9:6E:C5:09:18:66:45:63:88:F5:22:86:6A:CC


class KotlinMainActivity : GoogleDriveActivity() {
    private lateinit var navController: NavController
    private var repository: GoogleDriveApiDataRepository? = null
    private val LOG_TAG = "MainActivity"
    private val GOOGLE_DRIVE_DB_LOCATION = "db"
    private lateinit var myProgressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        myProgressBar = findViewById(R.id.myProgressBar)

        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this,navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.backup -> {
                startGoogleDriveSignIn()
                true
            }
            R.id.saveToGDrive -> {
                saveToGDrive()
                true
            }
            R.id.downloadFromGDrive -> {
                downloadFromGDrive()
                true
            }
            R.id.age_calculator -> {
                navController.navigate(R.id.kotlin_AgeCalculator)
                true
            }
            R.id.itemabout -> {
                aboutUS()
                true
            }
            R.id.rateus -> {
                rateUs()
                true
            }
            R.id.mshare -> {
                share()
                true
            }
            R.id.bmi_calculator -> {
                navController.navigate(R.id.kotlin_BMI_Calculator)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun downloadFromGDrive() {
        if (repository == null) {
            showMessage(R.string.message_google_sign_in_failed)
            return
        }else{
            myProgressBar.visibility = View.VISIBLE
        }
        val file = File(Database.DB_LOCATION)
        file.getParentFile()?.mkdirs()
        file.delete()
        repository!!.downloadFile(file, GOOGLE_DRIVE_DB_LOCATION)
            .addOnSuccessListener { _ ->
                myProgressBar.visibility = View.GONE
                showMessage("Data Retrieved")
            }
            .addOnFailureListener { e ->
                Log.e(LOG_TAG, "error download file", e)
                showMessage("Error download")
            }
    }

    private fun saveToGDrive() {
        val file = File(Database.DB_LOCATION)
        if (repository == null) {
            showMessage(R.string.message_google_sign_in_failed)
            return
        }else{
            myProgressBar.visibility = View.VISIBLE
        }
        repository!!.uploadFile(file, GOOGLE_DRIVE_DB_LOCATION)
            .addOnSuccessListener { _ -> showMessage("Upload success")
            myProgressBar.visibility = View.GONE
            }
            .addOnFailureListener { e ->
                Log.e(LOG_TAG, "error upload file", e)
                showMessage("Error upload")
            }
    }

/*    private fun deleteData() {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(this, R.style.AlertDialogButtonColor)
        dialog.setMessage(R.string.confirm)
        dialog.setPositiveButton(R.string.Yes)
        {
                thisdialog,
                btn->yes()
        }.show()
        dialog.setNegativeButton(R.string.No){
            thisdialog,btn->thisdialog.dismiss()
        }

    }*/

    private fun yes() {
        val deleteAllData = Database(this)
        deleteAllData.deleteAll()
    }

    private fun aboutUS() {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(this,R.style.AlertDialogButtonColor)
        dialog.setMessage(R.string.aboutUS)
        dialog.setPositiveButton(R.string.moreApps)
        { 
                myDialog,
                btn-> moreApps()
        }.show()
    }

    private fun moreApps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://search?q=pub:Digital Finger Apps")
        startActivity(intent)
    }

    private fun share() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=com.myECapplication.sajiiidali.ecsaver"
        )
        startActivity(Intent.createChooser(shareIntent, "Share Via"))
    }

    private fun rateUs() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }
    override fun onGoogleDriveSignedInSuccess(driveApi: Drive?) {
        repository = GoogleDriveApiDataRepository(driveApi)
        Toast.makeText(this, "Google Drive SignedIn Success", Toast.LENGTH_LONG).show()
    }
    override fun onGoogleDriveSignedInFailed(exception: ApiException?) {
        Toast.makeText(this, "Google Drive SignedIn Failed", Toast.LENGTH_LONG).show()
    }
    override fun onGoogleSignedInSuccess(signInAccount: GoogleSignInAccount?) {
        initializeDriveClient(signInAccount!!)
    }
    override fun onGoogleSignedInFailed(exception: ApiException?) {
        onGoogleDriveSignedInFailed(exception)
    }
}