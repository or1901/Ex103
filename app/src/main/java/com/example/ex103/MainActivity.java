package com.example.ex103;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 29/9/2023
 * The main activity:
 * Collects data of a mathematical series in an alert dialog and displays the 20 first values
 * of the series in a list view. Also shows the data of each series value when it's clicked.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}