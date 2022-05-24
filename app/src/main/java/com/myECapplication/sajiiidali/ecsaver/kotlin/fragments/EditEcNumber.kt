package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.Database

class EditEcNumber : DialogFragment(R.layout.edit_row_item) {
    private var mInterstitialAd: InterstitialAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInterstitialAd()
        val editEcNumber = view.findViewById<AppCompatEditText>(R.id.edit_ec_number)
        val editEcType = view.findViewById<AppCompatEditText>(R.id.edit_ec_Type)
        val saveButtonAfterEdit = view.findViewById<AppCompatButton>(R.id.saveButtonAfterEdit)
        val cancelButton = view.findViewById<AppCompatButton>(R.id.cancel_button)
        val db = Database(requireContext())
        val args = EditEcNumberArgs.fromBundle(requireArguments())

        editEcNumber.setText(args.getEcNumber)
        editEcType.setText(args.getEcType)

        saveButtonAfterEdit.setOnClickListener {
        try {
            if (editEcNumber.text.toString().isEmpty()){
                editEcNumber.error = "Should not be empty"
            }else if (editEcType.text.toString().isEmpty()){
                editEcNumber.error = "Should not be empty"
            }else{
                val getCursorOffLeave = db.checkDayOffLeave(args.getEcNumber,args.getEcType)
                val getCursorOfEcNumber = db.checkByEcNumber(args.getEcNumber,args.getEcType)
                if (getCursorOffLeave.count != 0){
                    val isEdited = db.updateDayOff(editEcNumber.text.toString(),editEcType.text.toString(),args.getEcNumber,args.getEcType)
                    if (isEdited)
                        Toast.makeText(activity, " List Updated ", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(activity, "List Not Updated ", Toast.LENGTH_SHORT).show()
                    ShowSavedData.refreshList()
                    dialog?.dismiss()
                }
                if (getCursorOfEcNumber.count != 0){
                    val isEdited = db.updateRowData(editEcNumber.text.toString(),editEcType.text.toString(),args.getEcNumber,args.getEcType)
                    if (isEdited)
                        Toast.makeText(activity, " List Updated ", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(activity, "List Not Updated ", Toast.LENGTH_SHORT).show()
                    ShowSavedData.refreshList()
                    dialog?.dismiss()

                    if (mInterstitialAd != null) {
                        mInterstitialAd?.show(requireActivity())
                    }
                }
            }

        } catch (e: Exception) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
        }
        }

        cancelButton.setOnClickListener {
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