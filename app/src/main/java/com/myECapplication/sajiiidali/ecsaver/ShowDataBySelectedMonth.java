package com.myECapplication.sajiiidali.ecsaver;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowDataBySelectedMonth extends AppCompatActivity {
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_by_selected_month);
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ec_saver_app_logo);// set app icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv = (TextView)findViewById(R.id.tview);

        tv.setText("\n\n\tThis Application Created by SAJID ALI \n\t Under The Supervision Of \n\t DISTRIC EMERGENCY OFFICER \n\t ENGINEER SHARAFAT ALI Sb. \n\t BAHAWALNAGAR\n\n\t APP Version = 1.7\n\t Creation_Date 14-August-2018\n\n\t Email ID:     sajiiidali1122@gmail.com\n\t Phone #     0302 329 4411\n\n\t RESCUE 1122 BAHAWALNAGAR\n\t PAKISTAN ZINDABAD");

    }
}
