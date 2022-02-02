package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.kotlin.adapters.expandAbleListAdapter
import com.myECapplication.sajiiidali.ecsaver.kotlin.adapters.myRow_data
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
        val listItem = ArrayList<myRow_data>()
        val hashMap= HashMap<String, List<myRow_data>>()
        val db = dataBaseClass(requireContext())
        val getCursorByMonth = db.checkDataByMonth(args.getMonth)
        if (getCursorByMonth != null) {
            var i = 0
            while (getCursorByMonth.moveToNext()){
                parentGroupName.add(getCursorByMonth.getString(3))
                listItem.add(myRow_data(getCursorByMonth.getString(3),
                    getCursorByMonth.getString(2),
                    getCursorByMonth.getString(1)))
                hashMap.put(parentGroupName.get(i),listItem)
                i++
            }
        }
        val myExpandableAdapter = expandAbleListAdapter(activity,hashMap,parentGroupName)
        expandAbleListview.setAdapter(myExpandableAdapter)

    }
}