package com.myECapplication.sajiiidali.ecsaver.kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.myECapplication.sajiiidali.ecsaver.R

class expandAbleListAdapter(
     var childListMap: Map<String, List<String>>,
     var parentList: List<String>) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int {
        return parentList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childListMap[parentList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return parentList.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childListMap[parentList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val titleOfList = getGroup(groupPosition).toString()
        var varView = convertView
        if (convertView == null){
            varView = LayoutInflater.from(parent?.context).inflate(R.layout.get_list_title, null as ViewGroup?)
        }
        val setListName = varView?.findViewById<TextView>(R.id.group_title)
        setListName?.setText(titleOfList)
        return varView!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val EcNumber = getChild(groupPosition,childPosition).toString()
        var varView = convertView
        if (convertView == null){
            varView = LayoutInflater.from(parent?.context).inflate(R.layout.data_row_layout_listview, null as ViewGroup?)
        }
        val DATE = varView?.findViewById<TextView>(R.id.edit_date)
        val EC_NUMBER = varView?.findViewById<TextView>(R.id.edit_ec_number)
        val EC_TYPE = varView?.findViewById<TextView>(R.id.edit_ec_Type)
        val DAYOFF = varView?.findViewById<TextView>(R.id.edit_dayoff)
        return varView!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }


}