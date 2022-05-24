package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.Database

class DeleteEcNumber :DialogFragment(R.layout.delete_ec_number_layout) {
    private var mInterstitialAd: InterstitialAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInterstitialAd()
        val yes = view.findViewById<AppCompatButton>(R.id.btnYes)
        val no  = view.findViewById<AppCompatButton>(R.id.btnNo)
        val message = view.findViewById<AppCompatTextView>(R.id.deleteMessage)
        val dialogTitle = "Do You Want To Delete This Record"
        val db = Database(requireContext())
        val args = DeleteEcNumberArgs.fromBundle(requireArguments())
        message.text = dialogTitle

        yes.setOnClickListener {
            val getCursorOffLeave = db.checkDayOffLeave(args.getEcNumber,args.getEcType)
            val getCursorOfEcNumber = db.checkByEcNumber(args.getEcNumber,args.getEcType)

            if (getCursorOffLeave.count != 0){
                val isDeleted = db.deleteDayOffLeave(args.getEcNumber,args.getEcType)
                if (isDeleted)
                    Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(activity, "Please Try Again", Toast.LENGTH_SHORT).show()
                ShowSavedData.refreshList()
                dialog?.dismiss()
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(requireActivity())
                }
            }
            if (getCursorOfEcNumber.count != 0){
                val isDeleted = db.deleteData(args.getEcNumber,args.getEcType)
                if (isDeleted)
                    Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(activity, "Please Try Again", Toast.LENGTH_SHORT).show()
                ShowSavedData.refreshList()
                dialog?.dismiss()
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(requireActivity())
                }
            }


        }
        no.setOnClickListener {
            dialog?.dismiss()
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

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}