package com.myECapplication.sajiiidali.ecsaver.listview_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.myECapplication.sajiiidali.ecsaver.R;
import com.myECapplication.sajiiidali.ecsaver.Row_Data.Row_data;

import java.util.ArrayList;

public class EC_Number_Adapter extends BaseAdapter {

    Context appContext;
    ArrayList<Row_data> rowDatas;

    public EC_Number_Adapter(Context appContext, ArrayList<Row_data> rowDatas) {
        this.appContext = appContext;
        this.rowDatas = rowDatas;
    }

    @Override
    public int getCount() {
        return rowDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return rowDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        try {
            MyViewHolder myViewHolder = null;
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.data_row_layout_listview, null);

                myViewHolder = new MyViewHolder();

                myViewHolder.DATE       = view.findViewById(R.id.edit_date);
                myViewHolder.EC_NUMBER  = view.findViewById(R.id.edit_ec_number);
                myViewHolder.EC_TYPE    = view.findViewById(R.id.edit_ec_Type);
                myViewHolder.DAYOFF     = view.findViewById(R.id.edit_dayoff);

                Row_data user = rowDatas.get(position);

                myViewHolder.DATE.setText(user.getDATE());
                myViewHolder.EC_NUMBER.setText(user.getEC_NUMBER());
                myViewHolder.EC_TYPE.setText(user.getEC_TYPE());
                myViewHolder.DAYOFF.setText(user.getDAYOFF());

                view.setTag(myViewHolder);

            }else{
                myViewHolder = (MyViewHolder) view.getTag();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
    class MyViewHolder{

    TextView DATE, EC_TYPE, EC_NUMBER,DAYOFF;
}

