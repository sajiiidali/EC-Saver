package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.databinding.MoreLayoutBinding

class MoreFragment : Fragment() {
    private var _binding: MoreLayoutBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MoreLayoutBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.ageCalculator?.setOnClickListener {
            findNavController().navigate(R.id.kotlin_AgeCalculator)
        }
        binding?.bmiCalculator?.setOnClickListener {
            findNavController().navigate(R.id.kotlin_BMI_Calculator)
        }
        binding?.share?.setOnClickListener { share() }
        binding?.Rateus?.setOnClickListener { rateUs() }
        binding?.privacypolicy?.setOnClickListener { privacyPolicy() }
        binding?.update?.setOnClickListener { rateUs() }
        binding?.aboutUs?.setOnClickListener { aboutUS() }

    }

    private fun privacyPolicy() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ecsaver1122.blogspot.com/"))
        startActivity(browserIntent)
    }

    private fun rateUs() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.myECapplication.sajiiidali.ecsaver")))
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=com.myECapplication.sajiiidali.ecsaver")
                )
            )
        }
    }

    private fun aboutUS() {
        val dialog : AlertDialog.Builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogButtonColor)
        dialog.setMessage(R.string.aboutUS)
        dialog.setPositiveButton(R.string.moreApps)
        {
                _,
                _ -> moreApps()
        }.show()
    }
    private fun moreApps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://search?q=pub:Digital Finger Apps")
        startActivity(intent)
    }
    private fun share() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=com.myECapplication.sajiiidali.ecsaver"
        )
        startActivity(Intent.createChooser(shareIntent, "Share Via"))
    }



















    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}