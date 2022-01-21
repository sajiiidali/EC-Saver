package com.myECapplication.sajiiidali.ecsaver.kotlin.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.myECapplication.sajiiidali.ecsaver.R

class HomeFragment : Fragment(R.layout.home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentView = view
    }




companion object{
    @SuppressLint("StaticFieldLeak")
    lateinit var homeFragmentView:View
    fun jumpToAgeCalculator(){
    val navDirection = HomeFragmentDirections.actionHomeFragmentToKotlinAgeCalculator()
        Navigation.findNavController(homeFragmentView).navigate(navDirection)
    }
    fun jumpToBMICalculator(){
        val navDirection = HomeFragmentDirections.actionHomeFragmentToKotlinBMICalculator()
        Navigation.findNavController(homeFragmentView).navigate(navDirection)
    }
}

}