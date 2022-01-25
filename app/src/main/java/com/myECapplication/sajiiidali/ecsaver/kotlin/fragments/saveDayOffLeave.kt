package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.myECapplication.sajiiidali.ecsaver.R
import java.util.ArrayList

class saveDayOffLeave : DialogFragment(R.layout.activity_save__dayoff__leave) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = saveDayOffLeaveArgs.fromBundle(requireArguments())
        val byDay    = args.day
        val byMonth  = args.month
        val byYear   = args.year
        val Date = "$byDay-$byMonth-$byYear"
        val spiner      = view.findViewById<Spinner>(R.id.spinner)
        val saveButton  = view.findViewById<Button>(R.id.btnsave)
        val showDate    = view.findViewById<TextView>(R.id.tvdate)
        showDate.setText(Date)
        val arrayList = ArrayList<String>()
        arrayList.add("<Select Your Choice>")
        arrayList.add("Rest")
        arrayList.add("DayOff")
        arrayList.add("cLeave")
        arrayList.add("DutyChange")
        arrayList.add("NationalDay")
        arrayList.add("Other")
        val array = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayList)
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner.adapter = array

        saveButton.setOnClickListener {

        }

    }
}