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
import com.myECapplication.sajiiidali.ecsaver.Database
import com.myECapplication.sajiiidali.ecsaver.R
import java.util.ArrayList


class SaveDayOffLeave : DialogFragment(R.layout.save_dayoff_leave) {
    private var mInterstitialAd: InterstitialAd? = null
    lateinit var mAdView : AdView
    private var dayOffLeave :String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInterstitialAd()

        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val autoCT = view.findViewById<AutoCompleteTextView>(R.id.SaveDayOffAutoText)
        val reSources = resources.getStringArray(R.array.spinnerItem2)
        val resourcesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item2, reSources)
        autoCT.setAdapter(resourcesArrayAdapter)
        autoCT.setOnItemClickListener { _, _, position, _ ->
            dayOffLeave = resourcesArrayAdapter.getItem(position)
        }

        val args = SaveDayOffLeaveArgs.fromBundle(requireArguments())
        val byDay    = args.day
        val byMonth  = args.month
        val byYear   = args.year
        val textViewDate = view.findViewById<TextView>(R.id.tvdateforDayoff)
        val saveButton   = view.findViewById<Button>(R.id.btnsaveOfDayoff)
        val database = Database(requireActivity())
        val getOldDate = "$byDay-$byMonth-$byYear"
        textViewDate.text = getOldDate

        saveButton.setOnClickListener {

            try {
                val cursor = database.checkDataByMonthAndYear(byDay,byMonth,byYear)
                if (cursor?.count != 0){
                    Toast.makeText(activity, "Can't Save Because On this day you were on duty", Toast.LENGTH_SHORT)
                        .show()
                    if (mInterstitialAd != null) {
                        mInterstitialAd?.show(requireActivity())
                    }
                }else{
                    if (dayOffLeave == null){
                        Toast.makeText(activity, "please Select your Type of Leave ", Toast.LENGTH_SHORT).show()
                    }else {
                        val dayOfMonth      = byDay.toString()
                        val monthOfYear     = byMonth.toString()
                        val yYear           = byYear.toString()

                        val isInsert = database.insertData(dayOffLeave,"",getOldDate,monthOfYear,dayOfMonth,yYear,"")
                        dismiss()
                        if (isInsert) {
                            Toast.makeText(activity, "$dayOffLeave Saved", Toast.LENGTH_SHORT).show()
                            if (mInterstitialAd != null) {
                                mInterstitialAd?.show(requireActivity())
                            }
                        }else
                            Toast.makeText(activity, "not Saved", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
            }
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
    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}