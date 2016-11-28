package com.example.cjspiece.kanaquizzer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class DbAdapter extends BaseAdapter {
    private Context mContext;
    private KanaQuizzerDatabaseHelper db;
    private List<Score> listScores;
    private int length;

    // Parameterized constructor for the adapter
    public DbAdapter(Context c) {
        mContext = c;
        db = new KanaQuizzerDatabaseHelper(mContext);
        listScores = db.getAllScores();
        length = listScores.size();
    }

    // Getter method that returns the length of the list from the database
    public int getCount() {
        return length;
    }

    // getter method that returns the Objects position: 0,1,2...etc
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // Creates a new TextField for each entry in the list
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView myText;
        if (convertView == null) {
            myText = new TextView(mContext);
            myText.setLayoutParams(new GridView.LayoutParams(1000, 85));
        }
        else {
            myText = (TextView) convertView;
        }

        myText.setText(String.valueOf(listScores.get(position).getID())
                + "                     " + listScores.get(position).getName()
                + "                                 " + listScores.get(position).getScore());
        return myText;
    }
}
