package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.kotlin.adapters.expandAbleListAdapter
import com.myECapplication.sajiiidali.ecsaver.kotlin.adapters.myRow_data
import com.myECapplication.sajiiidali.ecsaver.kotlin.database.dataBaseClass
import java.util.ArrayList
import java.util.HashMap

class showSavedData : Fragment(R.layout.show_saved_data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSavedView = view
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val args = showSavedDataArgs.fromBundle(requireArguments())
        val expandAbleListview = view.findViewById<ExpandableListView>(R.id.expandAbleListView)
        val hashMap= HashMap<String, List<myRow_data>>()
        val db = dataBaseClass(requireContext())
        var isYearExist = args.getCurrentYear
        try {
            var index = 0
            var byDate = 0
            while (byDate <= 32){
                    val getCursorByMonth = db.checkDataByMonthAndYear(byDate,args.getMonth,isYearExist)
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
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 2){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 3){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 4){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 5){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 6){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 7){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 8){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 9){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 10){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 11){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 12){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 13){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 14){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 15){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 16){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 17){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 18){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 19){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 20){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 21){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 22){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 23){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 24){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 25){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 26){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 27){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 28){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 29){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 30){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }else if (byDate == 31){
                            val childList = ArrayList<myRow_data>()
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (parentName.get(index).equals("A")) {
                                    parentName.set(index,getCursorByMonth.getString(2))
                                }
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                            }
                            hashMap.put(parentName.get(index), childList)
                        }
                    }
                    if(byDate == 32){
                        isYearExist -= 1
                        val getCursorOfYear = db.checkByYear(isYearExist)
                        if (getCursorOfYear?.count != 0){
                            byDate = 0
                        }
                    }
                byDate++
            }
            myExpandableAdapter = expandAbleListAdapter(activity,hashMap,parentName)
            expandAbleListview.setAdapter(myExpandableAdapter)
        } catch (e: Exception) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
        }
    }

    companion object
    {
        @SuppressLint("StaticFieldLeak")
        lateinit var showSavedView:View
        lateinit var myExpandableAdapter :expandAbleListAdapter
        var parentName = ArrayList<String>()

        fun getEditLayout(ecNumber: String, ecType: String) {
            val directions = showSavedDataDirections.actionShowSavedDataToEditECNumber2(ecNumber,ecType)
            Navigation.findNavController(showSavedView).navigate(directions)
        }
        fun refereshList(){
            parentName.clear()
            myExpandableAdapter.notifyDataSetChanged()
        }
    }
}