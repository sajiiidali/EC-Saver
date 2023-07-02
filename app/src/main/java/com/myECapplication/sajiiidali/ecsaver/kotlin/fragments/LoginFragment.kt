package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myECapplication.sajiiidali.ecsaver.databinding.LoginFragmentBinding
import com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.google.GoogleDriveActivity

class LoginFragment : Fragment(){
    private var _binding : LoginFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val kotlinMainActivity = requireActivity() as GoogleDriveActivity

        with(binding!!){
            loginButton.setOnClickListener {
                kotlinMainActivity.startGoogleDriveSignIn()
            }
        }
    }
    companion object{
        val mLoginFile                 = "loginFile"
        val isUserLoged                = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
