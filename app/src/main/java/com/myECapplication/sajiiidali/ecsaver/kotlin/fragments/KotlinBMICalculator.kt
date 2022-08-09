package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.myECapplication.sajiiidali.ecsaver.R
import java.lang.NumberFormatException
import java.text.DecimalFormat

class KotlinBMICalculator : Fragment(R.layout.activity_bmi__calculator) {
    private var mInterstitialAd: InterstitialAd? = null
    lateinit var mAdView : AdView

    private val myFileName                        = "Bmi_file"
    private val sharePrefWeight                   = "my_weight"
    private val sharePrefFeet                     = "my_feet"
    private val sharePrefInch                     = "my_inch"
    private lateinit var getWeight               :EditText
    private lateinit var getFeet                 :EditText
    private lateinit var getInch                 :EditText
    private lateinit var btnCalculate            :Button
    private lateinit var btnClear                :Button
    private lateinit var showResult              :TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getWeight       =     view.findViewById(R.id.edittext1_weight)
        getFeet         =     view.findViewById(R.id.edittext2_feet)
        getInch         =     view.findViewById(R.id.edittext3_inch)
        btnCalculate    =     view.findViewById(R.id.btncalculate)
        btnClear        =     view.findViewById(R.id.button2)
        showResult      =     view.findViewById(R.id.showresult)

        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        showInterstitialAd()

        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(myFileName, Context.MODE_PRIVATE)!!
        val savePreference1 = sharedPreferences.getString(sharePrefWeight, "80")
        val savePreference2 = sharedPreferences.getString(sharePrefFeet, "5")
        val savePreference3 = sharedPreferences.getString(sharePrefInch, "10")

        getWeight.setText(savePreference1)
        getFeet.setText(savePreference2)
        getInch.setText(savePreference3)

        btnCalculate.setOnClickListener {
            if (getWeight.text.toString().isEmpty()){
                getWeight.error = "Enter Your Weight"
            }else if (getFeet.text.toString().isEmpty()){
                getFeet.error = "Enter Your Weight"
            }else if (getInch.text.toString().isEmpty()){
                getInch.error = "Enter Your Weight"
            }else{
                val editor: SharedPreferences.Editor =
                    activity?.getSharedPreferences(myFileName, Context.MODE_PRIVATE)!!.edit()
                editor.putString(sharePrefWeight, getWeight.text.toString())
                editor.putString(sharePrefFeet, getFeet.text.toString())
                editor.putString(sharePrefInch, getInch.text.toString())
                editor.apply()

                closeKeyBoard()
                var category = ""
                var totalWeight = 0
                var wait = 0
                try {
                    val a: Float = getFeet.text.toString().toFloat()
                    val convertFtIntoMeter = (a / 3.28).toFloat()
                    val b: Float = getInch.text.toString().toFloat()
                    val inchIntoFt = (b * 0.0833).toFloat()
                    val convertInchIntoMeter = (inchIntoFt / 3.28).toFloat()
                    val totalHieghtInMeter = convertFtIntoMeter + convertInchIntoMeter
                    val weight: Float = getWeight.text.toString().toFloat()
                    val bmi = (weight / (totalHieghtInMeter * totalHieghtInMeter)).toDouble()
                    val df = DecimalFormat("##.#")
                    val bmiValue: Float = df.format(bmi).toFloat()
                    val w: Int = Integer.valueOf(getWeight.text.toString())
                    val f: Int = Integer.valueOf(getFeet.text.toString())
                    val i: Int = Integer.valueOf(getInch.text.toString())
                    if (f == 4 && i == 9) {
                        wait = 52
                    }
                    if (f == 4 && i == 10) {
                        wait = 54
                    }
                    if (f == 4 && i == 11) {
                        wait = 56
                    }
                    if (f == 4 && i == 12) {
                        wait = 58
                    }
                    if (f == 5 && i == 0) {
                        wait = 58
                    }
                    if (f == 5 && i == 1) {
                        wait = 60
                    }
                    if (f == 5 && i == 2) {
                        wait = 62
                    }
                    if (f == 5 && i == 3) {
                        wait = 64
                    }
                    if (f == 5 && i == 4) {
                        wait = 66
                    }
                    if (f == 5 && i == 5) {
                        wait = 68
                    }
                    if (f == 5 && i == 6) {
                        wait = 70
                    }
                    if (f == 5 && i == 7) {
                        wait = 72
                    }
                    if (f == 5 && i == 8) {
                        wait = 74
                    }
                    if (f == 5 && i == 9) {
                        wait = 76
                    }
                    if (f == 5 && i == 10) {
                        wait = 78
                    }
                    if (f == 5 && i == 11) {
                        wait = 81
                    }
                    if (f == 5 && i == 12) {
                        wait = 83
                    }
                    if (f == 6 && i == 0) {
                        wait = 83
                    }
                    if (f == 6 && i == 1) {
                        wait = 86
                    }
                    if (f == 6 && i == 2) {
                        wait = 88
                    }
                    if (f == 6 && i == 3) {
                        wait = 91
                    }
                    if (f == 6 && i == 4) {
                        wait = 93
                    }
                    if (f == 6 && i == 5) {
                        wait = 95
                    }
                    if (f == 6 && i == 6) {
                        wait = 98
                    }
                    if (f == 6 && i == 7) {
                        wait = 101
                    }
                    if (bmiValue.compareTo(18.5.toFloat()) <= 0) category =
                        "underweight" else if (bmiValue.compareTo(18.6.toFloat()) >= 0 && bmiValue.compareTo(
                            24.9.toFloat()
                        ) <= 0
                    ) category = "Normal" else if (bmiValue.compareTo(25.0.toFloat()) >= 0 && bmiValue.compareTo(29.9.toFloat()) <= 0
                    ) {
                        category = "Overweight"
                        totalWeight = w - wait
                    } else if (bmiValue.compareTo(30.toFloat()) >= 0) {
                        category = "Obese"
                        totalWeight = w - wait
                    }
                    val showResultt = "Your BMI is $bmiValue\nYou are $category\nYour Over-Weight is $totalWeight KG"
                    showResult.text = showResultt
                } catch (e: NumberFormatException) {
                    Toast.makeText(activity, "Please First Put Weight and Height ", Toast.LENGTH_LONG).show()
                }
            }
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(requireActivity())
            }

        }
        btnClear.setOnClickListener {
            getWeight.setText("")
            getFeet.setText("")
            getInch.setText("")

            if (mInterstitialAd != null) {
                mInterstitialAd?.show(requireActivity())
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

    private fun closeKeyBoard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}