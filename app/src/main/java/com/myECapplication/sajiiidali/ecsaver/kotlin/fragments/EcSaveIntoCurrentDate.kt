package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.Database
import com.myECapplication.sajiiidali.ecsaver.kotlin.fragments.HomeFragment.Companion.updateUI
import java.util.*

class EcSaveIntoCurrentDate: DialogFragment(R.layout.activity_save_data_current_day) {
    lateinit var mAdView : AdView
    private var mInterstitialAd: InterstitialAd? = null
    private var ecType :String? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        showInterstitialAd()

        val currentDate = view.findViewById<TextView>(R.id.tvdate)
        val getEcNumber = view.findViewById<EditText>(R.id.edttext)
        val saveButton  = view.findViewById<Button>(R.id.btnsave)
        val clearButton = view.findViewById<Button>(R.id.btnclear)
        val autoCT = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val database = Database(requireActivity())


        val reSources = resources.getStringArray(R.array.spinnerItem)
        val resourcesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, reSources)
            autoCT.setAdapter(resourcesArrayAdapter)
            autoCT.setOnItemClickListener { _, _, position, _ ->
                ecType = resourcesArrayAdapter.getItem(position)
        }

        val calendar    = Calendar.getInstance(TimeZone.getDefault())
        val month       = calendar.get(Calendar.MONTH)
        val byMonth    = month+1
        val byDay      = calendar.get(Calendar.DAY_OF_MONTH)
        val byYear     = calendar.get(Calendar.YEAR)
        val getCurrentDate = "$byDay-$byMonth-$byYear"
        currentDate.text = getCurrentDate

        saveButton.setOnClickListener {
            var isMatch = 0
            try {
                val checkEcNumber = getEcNumber.text.toString()
                val cursor = database.checkData(byMonth,byYear)
                if (cursor.count != 0){
                    while (cursor.moveToNext()){
                        if (cursor.getString(1).toString() == checkEcNumber){
                            isMatch++
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
            }

            if (ecType == null) {
                Toast.makeText(requireContext(), "Select Type Of EC", Toast.LENGTH_SHORT).show()
            }else if (getEcNumber.text.toString().isEmpty()) {
                getEcNumber.error = "Enter EC Number"
            }else  if (isMatch == 0){
                val ecNumber = getEcNumber.text.toString()
                val dayOfMonth = byDay.toString()
                val monthOfYear = byMonth.toString()
                val yYear       = byYear.toString()
                val isInsert = database.insertData(ecType,ecNumber,getCurrentDate,monthOfYear,dayOfMonth,yYear,"")
                if (isInsert){
                    Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
                    updateUI()
                }else{
                    Toast.makeText(activity, "not Saved", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity, "This Record Already Saved", Toast.LENGTH_SHORT).show()
            }

        }
        clearButton.setOnClickListener {
            getEcNumber.setText("")
        }
    }

    private fun showInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireActivity(),
            resources.getString(R.string.Interstitial_ad),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    mInterstitialAd = null
                }
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
    }


    override fun onStop() {
        super.onStop()
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
        }
    }
    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}
