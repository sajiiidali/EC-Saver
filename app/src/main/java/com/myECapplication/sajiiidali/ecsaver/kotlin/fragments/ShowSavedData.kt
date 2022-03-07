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
import com.myECapplication.sajiiidali.ecsaver.Database
import java.util.ArrayList
import java.util.HashMap

class ShowSavedData : Fragment(R.layout.show_saved_data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSavedView = view
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val args = ShowSavedDataArgs.fromBundle(requireArguments())
        val expandAbleListview = view.findViewById<ExpandableListView>(R.id.expandAbleListView)
        db = Database(requireContext())
        month = args.getMonth
        alwaysMaintainYearValue = args.getCurrentYear
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
                        val childList = ArrayList<myRow_data>()
                        while (getCursorByMonth?.moveToNext()!!)
                        {
                            if (parentName[index] == "A") {
                                parentName[index] = getCursorByMonth.getString(2)
                            }
                            if (getCursorByMonth.getString(1) == "")
                                    childList.add(myRow_data(getCursorByMonth.getString(2),getCursorByMonth.getString(0)))
                                else
                                    childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                        }
                        hashMap[parentName[index]] = childList
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
            myExpandableAdapter = expandAbleListAdapter(hashMap,parentName)
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
        lateinit var db: Database
        var parentName = ArrayList<String>()
        var month:Int = 0
        private var refreshYear:Int = 0
        var alwaysMaintainYearValue = 0
        val hashMap= HashMap<String, List<myRow_data>>()


        fun getEditLayout(ecNumber: String, ecType: String) {
            val directions = ShowSavedDataDirections.actionShowSavedDataToEditECNumber2(ecNumber,ecType)
            Navigation.findNavController(showSavedView).navigate(directions)
        }
        fun getDeleteData(ecNumber: String, ecType: String){
            val directions = ShowSavedDataDirections.actionShowSavedDataToDeleteEcNumber(ecNumber,ecType)
            Navigation.findNavController(showSavedView).navigate(directions)
        }
        fun refreshList(){
            parentName.clear()
            myExpandableAdapter.notifyDataSetChanged()
            refreshYear = alwaysMaintainYearValue
            val expandAbleListview = showSavedView.findViewById<ExpandableListView>(R.id.expandAbleListView)
            try {
                var index = 0
                var byDate = 0
                while (byDate <= 32){
                    val getCursorByMonth = db.checkDataByMonthAndYear(byDate, month,refreshYear)
                    if (getCursorByMonth?.count != 0){
                        if (parentName.isEmpty())
                        {
                            index = 0
                        }else
                        {
                            index++
                        }
                        parentName.add(index,"A")
                        val childList = ArrayList<myRow_data>()
                        while (getCursorByMonth?.moveToNext()!!)
                        {
                            if (parentName[index] == "A") {
                                parentName[index] = getCursorByMonth.getString(2)
                            }
                            if (getCursorByMonth.getString(1) == "")
                                childList.add(myRow_data(getCursorByMonth.getString(2),getCursorByMonth.getString(0)))
                            else
                                childList.add(myRow_data(getCursorByMonth.getString(1), getCursorByMonth.getString(0)))
                        }
                        hashMap[parentName[index]] = childList
                    }
                    if(byDate == 32){
                        refreshYear -= 1
                        val getCursorOfYear = db.checkByYear(refreshYear)
                        if (getCursorOfYear?.count != 0){
                            byDate = 0
                        }
                    }
                    byDate++
                }
                myExpandableAdapter = expandAbleListAdapter(hashMap,parentName)
                expandAbleListview.setAdapter(myExpandableAdapter)
            } catch (e: Exception) {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        parentName.clear()
        myExpandableAdapter.notifyDataSetChanged()
    }
}