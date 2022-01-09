package com.myECapplication.sajiiidali.ecsaver;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class DeleteByMonth extends AppCompatActivity {

    AlertDialog.Builder builder;
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    ListView userlist;
    Database mydb;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_by_month);

        listitem = new ArrayList<>();
        mydb = new Database(this);
        userlist = (ListView) findViewById(R.id.listview);
        builder = new AlertDialog.Builder(DeleteByMonth.this);
        showalldata();



        userlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                text = userlist.getItemAtPosition(i).toString();
                builder.setTitle("Delete Record").setMessage("Do you want to delete this Records?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), " You have deleted this record  \n\n" + text, Toast.LENGTH_LONG).show();
                        mydb.delete(text);
                        listitem.clear();
                        showalldata();
                        adapter.notifyDataSetChanged();

                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                return false;
            }
        });
    }

    private void showalldata() {
        int IsDayOff = 1;
        Cursor res = mydb.showdatawithoutarguments();
        if(res.getCount() == 0)
        {
            Toast.makeText(DeleteByMonth.this, "There is No any Records", Toast.LENGTH_LONG).show();
        }
        else
        {
            int i,total;
            total = res.getCount();
            total = res.getCount();
            while (res.moveToNext()){
                if(Integer.parseInt(res.getString(1)) == IsDayOff)
                    total = total-1;
            }
            for (i = 1; i < 12; i++) {
                res = mydb.showawholedate(i);
                while (res.moveToNext()) {
                    if ( Integer.parseInt(res.getString(1)) == IsDayOff )
                        listitem.add(res.getString(0) + "\t\t||\t\t" + res.getString(2));
                    else
                    listitem.add(res.getString(0) + "\t\t||\t\t" + res.getString(1) + "\t\t||\t\t" + res.getString(2));
                }
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listitem);
                userlist.setAdapter(adapter);
            }
            Toast.makeText(DeleteByMonth.this, "Total Emergencies Are = " + total, Toast.LENGTH_LONG).show();
        }
    }
}
