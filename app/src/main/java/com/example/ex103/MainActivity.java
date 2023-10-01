package com.example.ex103;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 29/9/2023
 * The main activity:
 * Collects data of a mathematical series in an alert dialog and displays the 20 first values
 * of the series in a list view. Also shows the data of each series value when it's clicked.
 */
public class MainActivity extends AppCompatActivity{
    AlertDialog.Builder adb;
    LinearLayout dataDialog;
    EditText dgFirstValueEt, dgDiffEt;
    RadioButton dgAriRb, dgGeoRb;
    int seriesType;
    String firstValueStr, diffStr;
    DialogInterface.OnClickListener onDialogBtnClick = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seriesType = 1;
        firstValueStr = diffStr = "";
    }

    /**
     * This function creates and displays the alert dialog which inputs the data of the series.
     * <p>
     *
     * @param view The view object of the button that was clicked in order to input the data.
     */
    public void getSeriesData(View view) {
        dataDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.data_dialog, null);

        dgFirstValueEt = (EditText) dataDialog.findViewById(R.id.dgFirstValueEt);
        dgDiffEt = (EditText) dataDialog.findViewById(R.id.dgDiffEt);
        dgAriRb = (RadioButton) dataDialog.findViewById(R.id.dgAriRb);
        dgGeoRb = (RadioButton) dataDialog.findViewById(R.id.dgGeoRb);

        adb = new AlertDialog.Builder(this);

        adb.setView(dataDialog);
        adb.setTitle("Series data input");

        adb.setPositiveButton("Apply", onDialogBtnClick);
        adb.setNeutralButton("Reset", onDialogBtnClick);
        adb.setNegativeButton("Cancel", onDialogBtnClick);

        // Resets the fields of data to the current displayed series values
        if(seriesType == 0)
            dgAriRb.setChecked(true);
        else
            dgGeoRb.setChecked(true);
        dgFirstValueEt.setText(firstValueStr);
        dgDiffEt.setText(diffStr);

        adb.show();
    }
}