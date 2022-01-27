package com.myECapplication.sajiiidali.ecsaver;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.myECapplication.sajiiidali.ecsaver.Row_Data.Row_data;
import com.myECapplication.sajiiidali.ecsaver.listview_adapter.EC_Number_Adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Main2Activity extends AppCompatActivity {

    AlertDialog.Builder builder,mbuilder;
    ArrayList<Row_data> row_dataArrayList;
    EC_Number_Adapter ECNumber_adapter;
    int mymonth, year,selected_year;
    ListView listView;
    String string="";
//    TextView tvmonth;
    Database mydb;
    String text;
    Intent it;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_saved_data);
        overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ec_saver_app_logo);// set app icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        tvmonth = (TextView)findViewById(R.id.tvmonth);
        row_dataArrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);
        mydb = new Database(this);
        builder = new AlertDialog.Builder(this);
        mbuilder= new AlertDialog.Builder(this);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        year = calendar.get(Calendar.YEAR);



        it = getIntent();
        mymonth = it.getIntExtra("month", 0);
        selected_year = it.getIntExtra("selected_year",0);

        if (mymonth == 1) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 2) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 3) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 4) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 5) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 6) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 7) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 8) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 9) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 10) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 11) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }
        if (mymonth == 12) {
            showdatabymonthlistAdapter(mymonth, selected_year);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                text = row_dataArrayList.get(position).getEC_NUMBER();
                final String date = row_dataArrayList.get(position).getDATE();
                final String dayoff = row_dataArrayList.get(position).getDAYOFF();

                final PopupMenu menu = new PopupMenu(Main2Activity.this, view);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.delete:
                                deleteThisItem(text,dayoff);
                                break;
                            case R.id.deleteDayoff:
                                deleteDayoff(date,text);
                                break;
                            case R.id.edit_ec_number:
                                editThisItem(position);
                                break;
                        }
                        return false;
                    }
                });

                menu.inflate(R.menu.popup_menu);
                menu.show();

            }
        });
    }

    private void deleteDayoff(final String date, final String EC_NO) {

        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(this);

        deleteBuilder.setTitle("Delete DayOff Record").setMessage("Do you want to delete this Records?").setIcon(R.mipmap.ec_saver_app_logo).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!(EC_NO == null)) {
                    Toast.makeText(Main2Activity.this, "Not Deleted find Correct Record", Toast.LENGTH_SHORT).show();
                } else {
                    mydb.deleteDayoff(date);
                    row_dataArrayList.clear();
                    showdatabymonthlistAdapter(mymonth, year);
                    ECNumber_adapter.notifyDataSetChanged();
                    Toast.makeText(Main2Activity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    private void editThisItem(final int position) {

        try {
            // declare local variables
            LayoutInflater myLayoutInflater;

            // Initialized all variables here
            myLayoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View updatView = myLayoutInflater.inflate(R.layout.edit_row_item,null);

            final TextView edit_Date = updatView.findViewById(R.id.edit_date);
            final TextView edit_EcNu = updatView.findViewById(R.id.edit_ec_number);
            final TextView edit_EcTy = updatView.findViewById(R.id.edit_ec_Type);

            edit_Date.setText(row_dataArrayList.get(position).getDATE());
            edit_EcNu.setText(row_dataArrayList.get(position).getEC_NUMBER());
            edit_EcTy.setText(row_dataArrayList.get(position).getEC_TYPE());



            mbuilder.setView(updatView).setTitle("Edit Your Data").setIcon(R.mipmap.ec_saver_app_logo).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String EC_NO = row_dataArrayList.get(position).EC_NUMBER;

                   boolean isEdited = mydb.editThisRowItems(edit_Date.getText().toString(),edit_EcNu.getText().toString(),edit_EcTy.getText().toString(),EC_NO);
                   row_dataArrayList.clear();
                    showdatabymonthlistAdapter(mymonth, year);
                }
            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void deleteThisItem( final String item, final String dayoff) {

        builder.setTitle("Delete EC Record").setMessage("Do you want to delete this Records?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!(dayoff == null)){
                            Toast.makeText(Main2Activity.this, "Select Correct Option", Toast.LENGTH_SHORT).show();
                        }else{
                            mydb.delete(item);
                            row_dataArrayList.clear();
                            showdatabymonthlistAdapter(mymonth, year);
                            ECNumber_adapter.notifyDataSetChanged();
                        }

                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }

    private void showdatabymonthlistAdapter(int month, int year) {
        Cursor res;
        int i, total = 0,ch = 1, even = 2, odd = 3;

        int empty = 0;
        if (month == 1)
            string = "January";
        if (month == 2)
            string = "February";
        if (month == 3)
            string = "March";
        if (month == 4)
            string = "April";
        if (month == 5)
            string = "May";
        if (month == 6)
            string = "June";
        if (month == 7)
            string = "July";
        if (month == 8)
            string = "August";
        if (month == 9)
            string = "September";
        if (month == 10)
            string = "October";
        if (month == 11)
            string = "November";
        if (month == 12)
            string = "December";
//        tvmonth.setText("Month Of "+string+" "+year);
        res = mydb.checkdata(month, year);
        total = res.getCount();
        while (res.moveToNext()){
            if(Integer.valueOf(res.getString(1)) == ch)
                total = total-1;
        }
        if (res.getCount() != 0) {
            empty = res.getCount();
        }
        if (empty != 0) {
            for (i = 1; i <= 31; i++) {
                res = mydb.show_data_by_selected_month_and_year(i, month, year);
                while (res.moveToNext()) {
                    if (Integer.valueOf(res.getString(1)) == ch)
                        row_dataArrayList.add(new Row_data(res.getString(2),res.getString(0)));
                    else{
                        Row_data row_data = new Row_data(res.getString(0),res.getString(1),res.getString(2));
                        row_dataArrayList.add(row_data);
                    }
                }
                ECNumber_adapter = new EC_Number_Adapter(this, row_dataArrayList);
                listView.setAdapter(ECNumber_adapter);
            }
            Toast.makeText(Main2Activity.this, "Total Emergencies Month of " + string + " is = " + total, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(Main2Activity.this, "There Is No Records of " + string + " " + year, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mdelete:
                deletedata();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deletedata() {
        builder.setTitle("Delete Records").setMessage("Really? Do You Want To Delete Records of " + string)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Cursor res = mydb.checkdata(mymonth, year);
                if (res.getCount() == 0)
                    Toast.makeText(Main2Activity.this, "There is No any Records of " + string, Toast.LENGTH_LONG).show();
                else {
                    mydb.deletebymonth(mymonth);
                    Toast.makeText(Main2Activity.this, " You Have Deleted Records Month of " + string, Toast.LENGTH_LONG).show();
                    row_dataArrayList.clear();
                    ECNumber_adapter.notifyDataSetChanged();
                }
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    };

}
