package com.myECapplication.sajiiidali.ecsaver.kotlin


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.api.services.drive.Drive
import com.myECapplication.sajiiidali.ecsaver.Database
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.databinding.ActivityKotlinMainBinding
import com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google.GoogleDriveActivity
import com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google.GoogleDriveApiDataRepository
import com.myECapplication.sajiiidali.ecsaver.kotlin.fragments.LoginFragment
import java.io.File

class KotlinMainActivity : GoogleDriveActivity() {
    private lateinit var navController: NavController
    private var repository: GoogleDriveApiDataRepository? = null
    private val LOG_TAG = "MainActivity"
    private val GOOGLE_DRIVE_DB_LOCATION = "db"
    private lateinit var myProgressBar:ProgressBar
    private lateinit var binding: ActivityKotlinMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}

        binding = ActivityKotlinMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myProgressBar = findViewById(R.id.myProgressBar)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation_id)
        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupWithNavController(bottomNavigation, navController)
//        checkLoginState(navController)
    }

    private fun checkLoginState(navController: NavController) {
            val sharedPreferences: SharedPreferences = this.getSharedPreferences(LoginFragment.mLoginFile, Context.MODE_PRIVATE)!!
            val isUserLogin = sharedPreferences.getBoolean("isUserLoged", false)

            if (!isUserLogin){
                navController.navigate(R.id.loginFragment)
            }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveToGDrive -> {
                saveToGDrive()
                true
            }
            R.id.downloadFromGDrive -> {
                downloadFromGDrive()
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
        file.parentFile?.mkdirs()
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

  /*  private fun yes() {
        val deleteAllData = Database(this)
        deleteAllData.deleteAll()
    }*/

    override fun onGoogleDriveSignedInSuccess(driveApi: Drive?) {
        repository = GoogleDriveApiDataRepository(driveApi)
        Toast.makeText(this, "Google Drive Signed-In Success", Toast.LENGTH_LONG).show()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(LoginFragment.mLoginFile, Context.MODE_PRIVATE)!!
        val editor = sharedPreferences.edit()
        editor.putBoolean("isUserLoged", true)
        editor.apply()
    }
    override fun onGoogleDriveSignedInFailed(exception: ApiException?) {
        Toast.makeText(this, "Google Drive Signed-In Failed", Toast.LENGTH_LONG).show()
    }
    override fun onGoogleSignedInSuccess(signInAccount: GoogleSignInAccount?) {
        Toast.makeText(this, "Google Sign-In Success", Toast.LENGTH_SHORT).show()
        initializeDriveClient(signInAccount!!)
    }
    override fun onGoogleSignedInFailed(exception: ApiException?) {
        Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
        onGoogleDriveSignedInFailed(exception)
    }
}