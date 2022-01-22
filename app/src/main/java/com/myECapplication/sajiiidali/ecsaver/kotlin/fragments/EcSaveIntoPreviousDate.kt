package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.myECapplication.sajiiidali.ecsaver.R
import java.util.ArrayList

class EcSaveIntoPreviousDate : DialogFragment(R.layout.activity_save_old_selecteddate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = EcSaveIntoPreviousDateArgs.fromBundle(requireArguments())
        val day    = args.day
        val month  = args.month
        val year   = args.year
        val textViewDate = view.findViewById<TextView>(R.id.tvdate)
        val clearButton  = view.findViewById<Button>(R.id.button)
        val editText     = view.findViewById<EditText>(R.id.edttext)
        val spin = view.findViewById<Spinner>(R.id.spinner)
        val date = "$day-$month-$year"
        textViewDate.text = date
        val arrayList = ArrayList<String>()
        arrayList.add("<Select EC Type>")
        arrayList.add("RC")
        arrayList.add("ME")
        arrayList.add("FO")
        arrayList.add("WR")
        arrayList.add("RO")
        arrayList.add("OO")
        arrayList.add("REF")
        val array = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayList)
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = array

        clearButton.setOnClickListener {
            editText.setText("")
        }




    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog!!
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}