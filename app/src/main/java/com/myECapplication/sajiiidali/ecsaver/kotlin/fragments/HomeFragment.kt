package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.myECapplication.sajiiidali.ecsaver.Database
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.databinding.HomeFragmentBinding
import java.util.Calendar
import java.util.TimeZone

class HomeFragment : Fragment() {
    lateinit var mAdView : AdView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db = Database(requireContext())
        val calendar    = Calendar.getInstance(TimeZone.getDefault())
        year       = calendar.get(Calendar.YEAR)

        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        var homeView = view

        with(binding!!){
            var month = 1
            var totalEcNo = 0
            while (month != 12){
                when(month){
                    1->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        JanEC.text = EcNo.toString()
                    }
                    2->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        FebEC.text = EcNo.toString()
                    }
                    3->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        MarEC.text = EcNo.toString()
                    }
                    4->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        AprEC.text = EcNo.toString()
                    }
                    5->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        MayEC.text = EcNo.toString()
                    }
                    6->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        JuneEC.text = EcNo.toString()
                    }
                    7->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        JulyEC.text = EcNo.toString()
                    }
                    8->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        AugEC.text = EcNo.toString()
                    }
                    9->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        SepEC.text = EcNo.toString()
                    }
                    10->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        OctoberEC.text = EcNo.toString()
                    }
                    11->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        NovemberEC.text = EcNo.toString()
                    }
                    12->{
                        var byDate = 0
                        var EcNo = 0
                        while (byDate <= 32 ){
                            val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                            while (getCursorByMonth?.moveToNext()!!)
                            {
                                if (getCursorByMonth.getString(1) != ""){
                                    EcNo++
                                    totalEcNo++
                                }
                            }
                            byDate++
                        }
                        DecemberEC.text = EcNo.toString()
                    }
                }
                month++
            }

            val tEcString = "Total EC: $totalEcNo"
            ecText.text = tEcString

            ToDayLayout.setOnClickListener {
                val navigation = HomeFragmentDirections.actionHomeFragmentToEcSaveIntoCurrentDate2()
                Navigation.findNavController(view).navigate(navigation)
            }

            saveDayOff.setOnClickListener {
                val layoutInflater = layoutInflater
                homeView = layoutInflater.inflate(R.layout.datepicker_layout,null)
                val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
                builder.setView(homeView)

                builder.setPositiveButton(R.string.Yes) { dialog, btn->
                    val datePickerDialog = homeView.findViewById<DatePicker>(R.id.datepicker)
                    val selectedDate = datePickerDialog.dayOfMonth
                    val selectedMonth = datePickerDialog.month + 1
                    val selectedYear = datePickerDialog.year

                    try {
                        val directions = HomeFragmentDirections.actionHomeFragmentToSaveDayOffLeave2(selectedDate,selectedMonth,selectedYear)
                        Navigation.findNavController(view).navigate(directions)
                        dialog.dismiss()
                    } catch (e: Exception) {
                        Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
                    }
                }.show()
            }

            previousDayLayout.setOnClickListener {
                val layoutInflater = layoutInflater
                homeView = layoutInflater.inflate(R.layout.datepicker_layout,null)
                val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
                builder.setView(homeView)

                builder.setPositiveButton(R.string.Yes) { dialog, _ ->
                    val datePickerDialog = homeView.findViewById<DatePicker>(R.id.datepicker)
                    val selectedDate = datePickerDialog.dayOfMonth
                    val selectedMonth = datePickerDialog.month + 1
                    val selectedYear = datePickerDialog.year

                    try {
                        val directions = HomeFragmentDirections.actionHomeFragmentToEcSaveIntoPreviousDate(selectedDate,selectedMonth,selectedYear)
                        Navigation.findNavController(view).navigate(directions)
                        dialog.dismiss()
                    } catch (e: Exception) {
                        Toast.makeText(activity, ""+e, Toast.LENGTH_LONG).show()
                    }
                }.show()
            }

            JanEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(1,year)
                findNavController().navigate(directions)
            }
            FebEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(2,year)
                findNavController().navigate(directions)
            }
            MarEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(3,year)
                findNavController().navigate(directions)
            }
            AprEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(4,year)
                findNavController().navigate(directions)
            }
            MayEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(5,year)
                findNavController().navigate(directions)
            }
            JuneEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(6,year)
                findNavController().navigate(directions)
            }
            JulyEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(7,year)
                findNavController().navigate(directions)
            }
            AugEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(8,year)
                findNavController().navigate(directions)
            }
            SepEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(9,year)
                findNavController().navigate(directions)
            }
            OctoberEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(10,year)
                findNavController().navigate(directions)
            }
            NovemberEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(11,year)
                findNavController().navigate(directions)
            }
            DecemberEC.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToShowSavedData(12,year)
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        year = 0
        db = null
    }
    companion object{
        @SuppressLint("StaticFieldLeak")
        private var _binding: HomeFragmentBinding? = null
        private val binding get() = _binding
        var db:Database? = null
        var year = 0
        fun updateUI(){
            with(binding!!){
                var month = 1
                var totalEcNo = 0
                while (month != 12){
                    when(month){
                        1->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            JanEC.text = EcNo.toString()
                        }
                        2->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            FebEC.text = EcNo.toString()
                        }
                        3->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            MarEC.text = EcNo.toString()
                        }
                        4->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            AprEC.text = EcNo.toString()
                        }
                        5->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            MayEC.text = EcNo.toString()
                        }
                        6->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            JuneEC.text = EcNo.toString()
                        }
                        7->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            JulyEC.text = EcNo.toString()
                        }
                        8->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            AugEC.text = EcNo.toString()
                        }
                        9->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            SepEC.text = EcNo.toString()
                        }
                        10->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            OctoberEC.text = EcNo.toString()
                        }
                        11->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            NovemberEC.text = EcNo.toString()
                        }
                        12->{
                            var byDate = 0
                            var EcNo = 0
                            while (byDate <= 32 ){
                                val getCursorByMonth = db?.checkDataByMonthAndYear(byDate,month,year)
                                while (getCursorByMonth?.moveToNext()!!)
                                {
                                    if (getCursorByMonth.getString(1) != ""){
                                        EcNo++
                                        totalEcNo++
                                    }
                                }
                                byDate++
                            }
                            DecemberEC.text = EcNo.toString()
                        }
                    }
                    month++
                }

                val tEcString = "Total EC: $totalEcNo"
                ecText.text = tEcString
            }
        }
    }
}