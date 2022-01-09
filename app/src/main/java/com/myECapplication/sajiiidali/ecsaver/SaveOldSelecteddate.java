package com.myECapplication.sajiiidali.ecsaver;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SaveOldSelecteddate extends AppCompatActivity {
    Spinner spin;
    EditText edttext;
    Button btnsave;
    Database mydb;
    String thisDate;
    TextView textView_date;
    int date,month,year;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_old_selecteddate);
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ec_saver_app_logo);// set app icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spin = (Spinner) findViewById(R.id.spinner);
        edttext = (EditText) findViewById(R.id.edttext);
        btnsave = (Button) findViewById(R.id.btnsave);
        textView_date = (TextView)findViewById(R.id.tvdate);
        mydb = new Database(this);


        List<String> spinnerarray = new ArrayList<String>();
        spinnerarray.add("<Select EC Type>");
        spinnerarray.add("RC");
        spinnerarray.add("ME");
        spinnerarray.add("FO");
        spinnerarray.add("WR");
        spinnerarray.add("RO");
        spinnerarray.add("OO");
        spinnerarray.add("REF");
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(array);

        Intent mIntent = getIntent();
        date = mIntent.getIntExtra("date", 0);
        month = mIntent.getIntExtra("month", 0);
        year = mIntent.getIntExtra("year", 0);

        textView_date.setText("Date "+date+"-"+month+"-"+year);

            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int match = 0;
                        int ch = Integer.parseInt(edttext.getText().toString());
                        Cursor res = mydb.checkdata(month, year);
                        while (res.moveToNext()) {
                            if (Integer.parseInt(res.getString(1)) == ch)
                                match++;
                        }
                        if (spin.getSelectedItem().toString() == "<Select EC Type>") {
                            Toast.makeText(SaveOldSelecteddate.this, " Please first Select EC Type and Type EC_NO ", Toast.LENGTH_LONG).show();
                        } else if (match == 0) {
                            String a1, a2, a3, a4, a5, a6;
                            a1 = spin.getSelectedItem().toString();
                            a2 = edttext.getText().toString();
                            a3 = getmydate();
                            a4 = String.valueOf(month);
                            a5 = String.valueOf(date);
                            a6 = String.valueOf(year);

                            String rowstring = getrowstring(a3, a2, a1);
                            boolean isInsert = mydb.insertData(a1, a2, a3, a4, a5, a6, rowstring);

                            if (isInsert) {
                                Toast.makeText(getApplicationContext(), " Data Saved ", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(getApplicationContext(), " Data Not Saved ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SaveOldSelecteddate.this, "This Record Already Saved ", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(SaveOldSelecteddate.this, "Please Type Just Numeric Value", Toast.LENGTH_LONG).show();
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
    public String getrowstring ( String a1,String a2,String a3){
        return(a1+"\t\t||\t\t"+a2+"\t\t||\t\t"+a3);
    }

    public void Clear_Ec_Number(View view) {
        edttext.setText("");
        if (edttext.getText().toString().equals("")){
            Toast.makeText(SaveOldSelecteddate.this, "Empty Space", Toast.LENGTH_SHORT).show();
        }
    }
}
