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
import com.myECapplication.sajiiidali.ecsaver.kotlin.Fragments.HomeFragment

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController: NavController = navHost.navController
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
//                deletedata()
                true
            }
            R.id.age_calculator -> {
            HomeFragment.jumpToAgeCalculator()
                true
            }
            R.id.itemabout -> {
                aboutUS()
                true
            }
            R.id.rateus -> {
                rateus()
                true
            }
            R.id.mshare -> {
                share()
                true
            }
            R.id.bmi_calculator -> {
            HomeFragment.jumpToBMICalculator()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun aboutUS() {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(this,R.style.AlertDialogButtonColor)
        dialog.setMessage(R.string.aboutUS)
        dialog.setPositiveButton(R.string.moreApps)
        { 
                dialog,
                btn-> moreapps()
        }.show()
    }

    private fun moreapps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://search?q=pub:Andreno Group")
        startActivity(intent)
    }

    private fun share() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=com.myECapplication.sajiiidali.ecsaver"
        )
        startActivity(Intent.createChooser(shareIntent, "Share Via"))
    }

    private fun rateus() {
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