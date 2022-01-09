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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class SaveDataCurrentDay extends AppCompatActivity {

    Spinner spin;
    EditText edttext;
    Button btnsave, btnclear;
    Database mydb;
    String thisDate;
    TextView textView_current_date;
    int date, month, year, currentmonth;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_current_day);
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);


        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ec_saver_app_logo);// set app icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spin = (Spinner) findViewById(R.id.spinner);
        edttext = (EditText) findViewById(R.id.edttext);
        btnsave = (Button) findViewById(R.id.btnsave);
        btnclear = (Button) findViewById(R.id.btnclear);
        textView_current_date = (TextView) findViewById(R.id.tvdate);

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

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        thisDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        currentmonth = calendar.get(Calendar.MONTH);
        month = currentmonth + 1;
        date = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);

        textView_current_date.setText("Current Date " + date + "-" + month + "-" + year);

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edttext.setText("");
                if (edttext.getText().toString().equals("")) {
                    Toast.makeText(SaveDataCurrentDay.this, "Empty Space", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
                        Toast.makeText(SaveDataCurrentDay.this, "Please first Select EC Type and Type EC_NO", Toast.LENGTH_LONG).show();
                    } else if (match == 0) {
                        String a1, a2, a4, a5, a6;
                        a1 = spin.getSelectedItem().toString();
                        a2 = String.valueOf(ch);
                        a4 = String.valueOf(month);
                        a5 = String.valueOf(date);
                        a6 = String.valueOf(year);
                        String rowstring = getrowstring(thisDate,a2,a1);//thisDate,a2,a1 = a1, a2, thisDate
                        boolean isInsert = mydb.insertData(a1, a2, thisDate, a4, a5, a6, rowstring);
                        if (isInsert == true) {
                            Toast.makeText(getApplicationContext(), " Data Saved ", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), " Data Not Saved ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SaveDataCurrentDay.this, "This Record Already Saved ", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SaveDataCurrentDay.this, "Please Type Just Numeric Value", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public String getrowstring(String a1, String a2, String a3) {
        return (a1 + "\t\t||\t\t" + a2 + "\t\t||\t\t" + a3);
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
               Intent it = new Intent(SaveDataCurrentDay.this,ShowDataBySelectedMonth.class);
                SaveDataCurrentDay.this.startActivity(it);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
