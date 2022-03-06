package com.myECapplication.sajiiidali.ecsaver.kotlin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.kotlin.fragments.HomeFragment
import com.myECapplication.sajiiidali.ecsaver.Database

class KotlinMainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this,navController)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Deletewhole -> {
                deleteData()
                true
            }
            R.id.backup -> {
                showBackupDialog()
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

    private fun showBackupDialog() {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(this, R.style.AlertDialogButtonColor)
        dialog.setMessage(R.string.backup)
        dialog.setPositiveButton(R.string.checkThisVideo)
        {
                thisdialog,
                btn->showBackupVideo()
            thisdialog.dismiss()
        }.show()
    }

    private fun showBackupVideo() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.youtube.com/watch?v=cGaebK-Nfkc&t=17s&ab_channel=SajidAli")
        startActivity(intent)
    }

    private fun deleteData() {
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

    }

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
}