package com.example.cjspiece.kanaquizzer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class KatakanaActivity extends AppCompatActivity {

    private KatakanaLib kataLib = new KatakanaLib();
    private int score;
    private EditText userInput;
    private TextView scoreBox;
    private TextView kanaImg;
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myDrawerToggle;
    private boolean drawerOpen = false;

    public void onSaveAndQuit(View view) {
        KanaQuizzerDatabaseHelper db = new KanaQuizzerDatabaseHelper(this);
        Score myScore = new Score("User", score);
        db.addScore(myScore);

        // Return to the main menu after submitting the score
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

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
            if (this.getClass() != KatakanaActivity.class) {
                listIntent = new Intent(context, KatakanaActivity.class);
                startActivity(listIntent);
            }
        }
        else if (position == 3) {
            // Navigation is tied to the Hiragana Chart activity
            listIntent = new Intent(context, HiraganaChartActivity.class);
            startActivity(listIntent);
        }
        else if (position ==4) {
            // Navigation is tied to the Katakana Chart activity
            listIntent = new Intent(context, KatakanaChartActivity.class);
            startActivity(listIntent);
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
        setContentView(R.layout.activity_hiragana);

        // Displays the home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setup for the hamburger menu
        titles = getResources().getStringArray(R.array.titles);
        myDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new KatakanaActivity.DrawerItemClickListener());
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

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
        }

        // Add a hiragana to the textview when the activity is started
        getNewKana();
        userInput = (EditText)findViewById(R.id.inputEt);
        scoreBox = (TextView)findViewById(R.id.scoreBox);
        scoreBox.setText(String.valueOf(score));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("score", score);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onSubmit(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        // Test the user input against the correct answer
        boolean isCorrect = kataLib.checkInput(userInput.getText().toString());

        if(isCorrect) {
            // increment score by 10 points
            score += 10;
            // update score textview
            scoreBox.setText(String.valueOf(score));

            // Trigger the correct sound effect, then display a toast stating that too
            Intent correctIntent = new Intent(this, CorrectSoundService.class);
            startService(correctIntent);

            CharSequence text = "That is correct!";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            // Trigger the incorrect sound effect, then display a toast stating that too
            Intent wrongIntent = new Intent(this, WrongSoundService.class);
            startService(wrongIntent);

            CharSequence text = "That is incorrect!";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        // Clear in the input EditText
        userInput.setText("");
        // Then generate a new kana
        getNewKana();
    }

    // function will acquire a new kana from the associated class and display it to screen
    private void getNewKana() {
        kanaImg = (TextView)findViewById(R.id.kanaImage);
        kanaImg.setText(kataLib.getNewKana());
    }
}
