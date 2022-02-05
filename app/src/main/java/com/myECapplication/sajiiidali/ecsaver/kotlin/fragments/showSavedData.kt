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
        val hashMap= HashMap<String, List<myRow_data>>()
        val db = dataBaseClass(requireContext())
        try {
            var index = 0
                for (byDate:Int in 1..31){
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

                        if (byDate == 1){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 2){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 3){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 4){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 5){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 6){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 7){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 8){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 9){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 10){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 11){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 12){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 13){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 14){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 15){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 16){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 17){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 18){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 19){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 20){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 21){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 22){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 23){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 24){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 25){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 26){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 27){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 28){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 29){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 30){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(3))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(2), getCursorByMonth.getString(1)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 31){
                            val childList = ArrayList<myRow_data>()
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
            }
            val myExpandableAdapter = expandAbleListAdapter(activity,hashMap,parentName)
            expandAbleListview.setAdapter(myExpandableAdapter)
        } catch (e: Exception) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
        }
    }
    fun refreshList(){

    }
}