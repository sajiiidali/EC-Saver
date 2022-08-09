package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myECapplication.sajiiidali.ecsaver.databinding.RescueDashboardLayoutBinding

class RescueDashBoard : Fragment(){
    private var _binding: RescueDashboardLayoutBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = RescueDashboardLayoutBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.activeJobs?.setOnClickListener {
            val directions = RescueDashBoardDirections.actionRescueDashBoardToRescueAsWebview("https://www.rescue.gov.pk/Jobs/ActiveJobs.aspx")
            findNavController().navigate(directions)
        }
        binding?.mainPage?.setOnClickListener {
            val directions = RescueDashBoardDirections.actionRescueDashBoardToRescueAsWebview("https://www.rescue.gov.pk/")
            findNavController().navigate(directions)
        }
        binding?.finalResult?.setOnClickListener {
            val directions = RescueDashBoardDirections.actionRescueDashBoardToRescueAsWebview("https://rescue.gov.pk/Jobs/InterviewResults.aspx")
            findNavController().navigate(directions)
        }
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}