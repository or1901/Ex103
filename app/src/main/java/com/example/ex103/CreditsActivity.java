package com.example.ex103;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 2/10/2023
 * The credits activity:
 * It has a general menu to show the different credits.
 */
public class CreditsActivity extends AppCompatActivity {
    TextView creditsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        creditsTv = (TextView) findViewById(R.id.creditsTv);
    }

    /**
     * This function creates the credits option menu.
     * <p>
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add("Personal");
        menu.add("Education");
        menu.add("Software");

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This function reacts to the choice of an item in the options menu - displays the suitable
     * credit in the text view.
     * <p>
     *
     * @param item The menu item that was selected.
     * @return Returns false to allow normal menu processing to proceed, true to consume it here.
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String st = item.getTitle().toString();

        if(st.equals("Personal"))
            creditsTv.setText(R.string.personalCreditStr);
        else if(st.equals("Education"))
            creditsTv.setText(R.string.educationCreditStr);
        else if(st.equals("Software"))
            creditsTv.setText(R.string.softwareCreditStr);

        return super.onOptionsItemSelected(item);
    }

    /**
     * This function returns the user to the main activity.
     * <p>
     *
     * @param view The view object of the button that was clicked in order to go back.
     */
    public void backToMain(View view) {
        finish();
    }
}