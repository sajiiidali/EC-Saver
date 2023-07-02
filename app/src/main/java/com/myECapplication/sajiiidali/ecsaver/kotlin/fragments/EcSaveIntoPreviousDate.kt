package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

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
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.Database
import com.myECapplication.sajiiidali.ecsaver.kotlin.fragments.HomeFragment.Companion.updateUI
import java.util.ArrayList

class EcSaveIntoPreviousDate : DialogFragment(R.layout.activity_save_old_selecteddate) {
    private var mInterstitialAd: InterstitialAd? = null
    lateinit var mAdView : AdView
    private var ecType :String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInterstitialAd()

        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val autoCT = view.findViewById<AutoCompleteTextView>(R.id.previousAutoText)
        val reSources = resources.getStringArray(R.array.spinnerItem)
        val resourcesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, reSources)
        autoCT.setAdapter(resourcesArrayAdapter)
        autoCT.setOnItemClickListener { _, _, position, _ ->
            ecType = resourcesArrayAdapter.getItem(position)
        }

        val args = EcSaveIntoPreviousDateArgs.fromBundle(requireArguments())
        val byDay    = args.day
        val byMonth  = args.month
        val byYear   = args.year
        val textViewDate = view.findViewById<TextView>(R.id.tvdate)
        val saveButton   = view.findViewById<Button>(R.id.btnsave)
        val clearButton  = view.findViewById<Button>(R.id.button)
        val getEcNumber     = view.findViewById<EditText>(R.id.edttext)
        val database = Database(requireActivity())
        val getOldDate = "$byDay-$byMonth-$byYear"
        textViewDate.text = getOldDate

        saveButton.setOnClickListener {

            try {
                var isMatch = 0
                val checkEcNumber = getEcNumber.text.toString()
                val cursor = database.checkData(byMonth,byYear)
                if (cursor.count != 0){
                    while (cursor.moveToNext()){
                        if (cursor.getString(1).toString() == checkEcNumber){
                            isMatch++
                        }
                    }
                }

                if (ecType == null) {
                    Toast.makeText(requireContext(), "Select Type Of EC", Toast.LENGTH_SHORT).show()
                }else if (getEcNumber.text.toString().isEmpty()) {
                    getEcNumber.error = "Type EC Number"
                }else if (isMatch == 0){
                    val ecNumber = getEcNumber.text.toString()
                    val dayOfMonth = byDay.toString()
                    val monthOfYear = byMonth.toString()
                    val yYear       = byYear.toString()
                    val isInsert = database.insertData(ecType,ecNumber,getOldDate,monthOfYear,dayOfMonth,yYear,"")
                    if (isInsert){
                        Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
                        updateUI()
                    }else{
                        Toast.makeText(activity, "not Saved", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "This Record Already Saved", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "please first Select EC Type and Type EC_NO", Toast.LENGTH_SHORT).show()
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