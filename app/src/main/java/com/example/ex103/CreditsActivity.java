package com.example.ex103;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 2/10/2023
 * The credits activity:
 * It has a general menu to show the different credits.
 */
public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }
}