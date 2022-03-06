package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myECapplication.sajiiidali.ecsaver.R

class HomeFragment : Fragment(R.layout.home_fragment) {
  private  lateinit var toDayLayout             : ConstraintLayout
  private  lateinit var dayOffLeave             : ConstraintLayout
  private  lateinit var previousDayLayout       : ConstraintLayout
  private  lateinit var showSavedDataLayout     : ConstraintLayout

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.app_name)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        homeFragmentView        = view
        toDayLayout             = view.findViewById(R.id.ToDayLayout)
        previousDayLayout       = view.findViewById(R.id.previousDayLayout)
        dayOffLeave             = view.findViewById(R.id.saveDayOff)
        showSavedDataLayout     = view.findViewById(R.id.showSavedDataLayout)

        toDayLayout.setOnClickListener {
            val navigation = HomeFragmentDirections.actionHomeFragmentToEcSaveIntoCurrentDate2()
            findNavController().navigate(navigation)
            }

        dayOffLeave.setOnClickListener {
            val layoutInflater = layoutInflater
            homeFragmentView = layoutInflater.inflate(R.layout.datepicker_layout,null)
            val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
            builder.setView(homeFragmentView)

            builder.setPositiveButton(R.string.Yes) { dialog, btn->
                val datePickerDialog = homeFragmentView.findViewById<DatePicker>(R.id.datepicker)
                val selectedDate = datePickerDialog.dayOfMonth
                val selectedMonth = datePickerDialog.month + 1
                val selectedYear = datePickerDialog.year

                try {
                    val directions = HomeFragmentDirections.actionHomeFragmentToSaveDayOffLeave2(selectedDate,selectedMonth,selectedYear)
                    findNavController().navigate(directions)
                    dialog.dismiss()
                } catch (e: Exception) {
                    Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
                }
            }.show()
        }
        previousDayLayout.setOnClickListener {
            val layoutInflater = layoutInflater
            homeFragmentView = layoutInflater.inflate(R.layout.datepicker_layout,null)
            val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
            builder.setView(homeFragmentView)

            builder.setPositiveButton(R.string.Yes) { dialog, btn->
            val datePickerDialog = homeFragmentView.findViewById<DatePicker>(R.id.datepicker)
            val selectedDate = datePickerDialog.dayOfMonth
            val selectedMonth = datePickerDialog.month + 1
            val selectedYear = datePickerDialog.year

                try {
                    val directions = HomeFragmentDirections.actionHomeFragmentToEcSaveIntoPreviousDate(selectedDate,selectedMonth,selectedYear)
                    findNavController().navigate(directions)
                    dialog.dismiss()
                } catch (e: Exception) {
                    Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
                }
            }.show()



        }



        showSavedDataLayout.setOnClickListener {
         val directions = HomeFragmentDirections.actionHomeFragmentToGetMonthByUser()
            findNavController().navigate(directions)
                 }
    }


    companion object{
    @SuppressLint("StaticFieldLeak")
    lateinit var homeFragmentView:View
}

    override fun onStart() {
        super.onStart()
        homeFragmentView= requireView()
    }

}