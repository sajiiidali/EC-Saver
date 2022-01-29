package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.kotlin.adapters.expandAbleListAdapter
import com.myECapplication.sajiiidali.ecsaver.kotlin.database.dataBaseClass
import java.util.ArrayList
import java.util.HashMap

class showSavedData : Fragment(R.layout.show_saved_data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val args = showSavedDataArgs.fromBundle(requireArguments())
        val expandAbleListview = view.findViewById<ExpandableListView>(R.id.expandAbleListView)
        val parentGroupName = ArrayList<String>()
        val listItem = ArrayList<String>()
        listItem.add("Testing")
        val hashMap= HashMap<String, List<String>>()
        val db = dataBaseClass(requireContext())
        val getCursorOfYear = db.showAllData()
        if (getCursorOfYear != null) {
            val i = 0
            while (getCursorOfYear.moveToNext()){
                parentGroupName.add(getCursorOfYear.getString(6))
                hashMap.put(parentGroupName.get(i),listItem)
            }
        }
        val myExpandableAdapter = expandAbleListAdapter(hashMap,parentGroupName)
        expandAbleListview.setAdapter(myExpandableAdapter)

    }
}