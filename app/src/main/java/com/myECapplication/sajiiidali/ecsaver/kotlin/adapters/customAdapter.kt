package com.myECapplication.sajiiidali.ecsaver.kotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.myECapplication.sajiiidali.ecsaver.R
import kotlin.collections.ArrayList

class customAdapter(context: Context, rowData: ArrayList<myRow_data>) : BaseAdapter() {
     var appContext: Context = context
     var rowDatas: ArrayList<myRow_data> = rowData


    override fun getCount(): Int {
        return rowDatas.size
    }

    override fun getItem(position: Int): Any {
        return rowDatas.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var newConvertView = convertView
        if (convertView == null){
            val layoutInflater = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newConvertView = layoutInflater.inflate(R.layout.data_row_layout_listview, null)

            val DATE = newConvertView.findViewById<TextView>(R.id.edit_date)
            val EC_NUMBER = newConvertView.findViewById<TextView>(R.id.edit_ec_number)
            val EC_TYPE = newConvertView.findViewById<TextView>(R.id.edit_ec_Type)
//            val DAYOFF = newConvertView.findViewById<TextView>(R.id.edit_dayoff)

            val user: myRow_data = rowDatas[position]

//            DATE.setText(user.DATE)
            EC_NUMBER.setText(user.EC_NUMBER)
            EC_TYPE.setText(user.EC_TYPE)
//            DAYOFF.setText(user.DAYOFF)

        }
        return newConvertView!!
    }
}