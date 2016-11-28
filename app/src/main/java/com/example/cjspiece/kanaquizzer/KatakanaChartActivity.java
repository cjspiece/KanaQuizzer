package com.example.cjspiece.kanaquizzer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class KatakanaChartActivity extends AppCompatActivity {
    private KatakanaLib myLib = new KatakanaLib();
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myDrawerToggle;
    private boolean drawerOpen = false;

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            myDrawerLayout.closeDrawer(drawerList);
        }
    };

    private void selectItem(int position) {
        Context context = getApplicationContext();
        Intent listIntent;

        if (position == 0) {
            // Navigation is tied to the Main Activity
            listIntent = new Intent(context, MainActivity.class);
            startActivity(listIntent);
        }
        else if (position == 1) {
            // Navigation is tied to the Hiragana activity
            listIntent = new Intent(context, HiraganaActivity.class);
            startActivity(listIntent);
        }
        else if (position == 2) {
            // Navigation is tied to the Katakana activity
            listIntent = new Intent(context, KatakanaActivity.class);
            startActivity(listIntent);
        }
        else if (position == 3) {
            // Navigation is tied to the Hiragana Chart activity
            listIntent = new Intent(context, HiraganaChartActivity.class);
            startActivity(listIntent);
        }
        else if (position ==4) {
            // Navigation is tied to the Katakana Chart activity
            if (this.getClass() != KatakanaChartActivity.class) {
                listIntent = new Intent(context, KatakanaChartActivity.class);
                startActivity(listIntent);
            }
        }
        else if (position ==5) {
            // Navigation is tied to the Statistics activity
            listIntent = new Intent(context, StatisticsActivity.class);
            startActivity(listIntent);
        }

        // Close the drawer
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                if (drawerOpen == false) {
                    drawerOpen = true;
                    myDrawerLayout.openDrawer(GravityCompat.START);
                    return true;
                }
                else {
                    drawerOpen = false;
                    myDrawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        myDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katakana_chart);

        // Displays the home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setup for the hamburger menu
        titles = getResources().getStringArray(R.array.titles);
        myDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new KatakanaChartActivity.DrawerItemClickListener());
        myDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            // Called when a drawer has settled in a completely closed state
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            // Called when a drawer has settled in a completely open state
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        myDrawerLayout.setDrawerListener(myDrawerToggle);

        GridView gridview = (GridView)findViewById(R.id.activity_kata_chart);
        gridview.setAdapter(new KataAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(KatakanaChartActivity.this, myLib.getAnswerAt(position) , Toast.LENGTH_SHORT).show();
            }
        });
    }
}