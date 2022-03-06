package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.Database

class DeleteEcNumber :DialogFragment(R.layout.delete_ec_number_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val yes = view.findViewById<AppCompatButton>(R.id.btnYes)
        val no  = view.findViewById<AppCompatButton>(R.id.btnNo)
        val message = view.findViewById<AppCompatTextView>(R.id.deleteMessage)
        val dialogTitle = "Do You Want To Delete This Record"
        val db = Database(requireContext())
        val args = DeleteEcNumberArgs.fromBundle(requireArguments())

        message.text = dialogTitle

        yes.setOnClickListener {
            val isDeleted = db.deleteData(args.getEcNumber,args.getEcType)
            if (isDeleted)
                Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity, "Please Try Again", Toast.LENGTH_SHORT).show()
            ShowSavedData.refreshList()
            dialog?.dismiss()

        }
        no.setOnClickListener {
            dialog?.dismiss()
        }
    }
    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}