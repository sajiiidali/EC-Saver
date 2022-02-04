package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.Toast
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
        val parentName = ArrayList<String>()
        val childList = ArrayList<myRow_data>()
        val hashMap= HashMap<String, List<myRow_data>>()
        val db = dataBaseClass(requireContext())
        try {
            var index = 0
            for (byDate:Int in 0..30){
                val getCursorByMonth = db.checkDataByMonth(byDate,args.getMonth)
                if (getCursorByMonth?.count != 0){
                    if (parentName.isEmpty())
                    {
                        index = 0
                    }else
                    {
                        index++
                    }
                    parentName.add(index,"A")
                      while (getCursorByMonth?.moveToNext()!!)
                  {

                      if (parentName.get(index).equals("A")) {
                          parentName.set(index,getCursorByMonth.getString(3))
                      }
                      childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                  }
                    hashMap.put(parentName.get(index), childList)
                }
            }

        } catch (e: Exception) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
        }
        val myExpandableAdapter = expandAbleListAdapter(activity,hashMap,parentName)
        expandAbleListview.setAdapter(myExpandableAdapter)
    }
}