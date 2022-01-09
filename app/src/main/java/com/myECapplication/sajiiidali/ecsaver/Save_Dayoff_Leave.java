package com.myECapplication.sajiiidali.ecsaver;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class Save_Dayoff_Leave extends AppCompatActivity {

    Button btnsave;
    Spinner spinner;
    Intent it;
    int month, date, year;
    Database mydb;
    TextView date_text;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__dayoff__leave);
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ec_saver_app_logo);// set app icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner = (Spinner)findViewById(R.id.spinner);
        btnsave = (Button)findViewById(R.id.btnsave);
        date_text = (TextView)findViewById(R.id.tvdate);
        mydb = new Database(this);

        List<String> spinnerarray = new ArrayList<String>();
        spinnerarray.add("<Select Your Choice>");
        spinnerarray.add("DayOff");
        spinnerarray.add("Leave");
        spinnerarray.add("Rest");
        spinnerarray.add("DutyChange");
        spinnerarray.add("NationalDay");

        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array);

        it = getIntent();
        date = it.getIntExtra("date", 0);
        month = it.getIntExtra("month", 0);
        year = it.getIntExtra("year", 0);

        date_text.setText("Selected Date "+date+"-"+month+"-"+year);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int match = 0;
                    int keyPair = 1;
                    String dayoff, date,month;
                    Cursor res = mydb.checkdata(Save_Dayoff_Leave.this.month, year);
                    dayoff = spinner.getSelectedItem().toString();
                    date = getmydate();
                    month = String.valueOf(Save_Dayoff_Leave.this.month);
                    String rowstring = getrowstring(dayoff,date);
                    while (res.moveToNext()) {
                        if ( spinner.getSelectedItem().toString().equals(res.getString(0)) && Integer.parseInt(res.getString(3)) == Save_Dayoff_Leave.this.month && Integer.parseInt(res.getString(4)) == Save_Dayoff_Leave.this.date && Integer.parseInt(res.getString(5)) == year )
                            match++;
                    }
                    if (spinner.getSelectedItem().toString() == "<Select Your Choice>") {
                        Toast.makeText(Save_Dayoff_Leave.this, " Please Select Your Choice First ", Toast.LENGTH_LONG).show();
                    }
                    else if (match == 0) {
                        String keypair, Date, Year;
                        keypair = String.valueOf(keyPair);
                        Date = String.valueOf(Save_Dayoff_Leave.this.date);
                        Year = String.valueOf(year);

                        boolean isInsert = mydb.insertData(dayoff, keypair, date, month, Date, Year, rowstring);

                        if (isInsert == true)
                            Toast.makeText(Save_Dayoff_Leave.this, dayoff+" Saved ", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Save_Dayoff_Leave.this, dayoff+" Not Saved ", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(Save_Dayoff_Leave.this, dayoff+" Already Saved ", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(Save_Dayoff_Leave.this, "Please Select Your Choice First", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public String getmydate(){
        String string="";
        Intent intent = getIntent();
        date = intent.getIntExtra("date",0);
        month = intent.getIntExtra("month",0);
        year = intent.getIntExtra("year",0);
        if ( month == 1)
            string = "Jan";
        if ( month == 2)
            string = "Feb";
        if ( month == 3)
            string = "Mar";
        if ( month == 4)
            string = "Apr";
        if ( month == 5)
            string = "May";
        if ( month == 6)
            string = "Jun";
        if ( month == 7)
            string = "Jul";
        if ( month == 8)
            string = "Aug";
        if ( month == 9)
            string = "Sep";
        if ( month == 10)
            string = "Oct";
        if ( month == 11)
            string = "Nov";
        if ( month == 12)
            string = "Dec";

        return (string+" "+date+", "+year);
    }
    public String getrowstring ( String a1,String a3){
        return(a1+"\t\t||\t\t"+a3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_for_save_current_date, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemabout:
                Intent it = new Intent(Save_Dayoff_Leave.this,ShowDataBySelectedMonth.class);
                Save_Dayoff_Leave.this.startActivity(it);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
