package com.example.ex103;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 29/9/2023
 * The main activity:
 * Collects data of a mathematical series in an alert dialog and displays the 20 first values
 * of the series in a list view. Also shows the data of each series value when it's clicked.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    AlertDialog.Builder adb;
    LinearLayout dataDialog;
    EditText dgFirstValueEt, dgDiffEt;
    RadioButton dgAriRb, dgGeoRb;
    int seriesType;
    String firstValueStr, diffStr;
    double firstValue, diff, seriesSum;
    ListView lv;
    ArrayAdapter<Double> adp;
    TextView xOneTv, dTv, nTv, snTv;
    Double[] seriesArr;
    Intent si;
    DialogInterface.OnClickListener onDialogBtnClick = new DialogInterface.OnClickListener() {

        /**
         * This function reacts to the click on one of the dialog buttons - resets the series data,
         * or cancels the action, or calculates the series values.
         * <p>
         *
         * @param dialog The dialog that received the click.
         * @param which The constant of the button that was clicked.
         */
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // Apply button
            if(which == DialogInterface.BUTTON_POSITIVE) {
                // Saves the data of the series
                seriesType = getSeriesType();
                firstValueStr = dgFirstValueEt.getText().toString();
                diffStr = dgDiffEt.getText().toString();

                if(isDataValid(firstValueStr, diffStr)){
                    firstValue = Double.parseDouble(firstValueStr);
                    diff = Double.parseDouble(diffStr);

                    createSeriesArr(seriesType, firstValue, diff, seriesArr);
                    lv.setAdapter(adp);

                    xOneTv.setText(firstValueStr);
                    dTv.setText(diffStr);
                    nTv.setText("");
                    snTv.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid data, please try again!",
                            Toast.LENGTH_LONG).show();
                    firstValueStr = "";
                    diffStr = "";
                }
            }

            // Cancel button
            else if(which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }

            // Reset button
            else{
                resetSeriesData();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seriesType = 0;
        firstValueStr = diffStr = "";
        firstValue = diff = seriesSum = 0;
        seriesArr = new Double[20];

        lv = (ListView) findViewById(R.id.lv);
        xOneTv = (TextView) findViewById(R.id.xOneTv);
        dTv = (TextView) findViewById(R.id.dTv);
        nTv = (TextView) findViewById(R.id.nTv);
        snTv = (TextView) findViewById(R.id.snTv);

        adp = new ArrayAdapter<Double>(MainActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                seriesArr);

        lv.setOnItemClickListener(this);
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
        adb.setCancelable(false);

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

    /**
     * This function checks if the data of the series is valid(there isn't an empty field).
     * <p>
     *
     * @param firstValue The first value of the checked series.
     * @param diff The d/q of the checked series.
     * @return Whether the series data is valid, or not.
     */
    public boolean isDataValid(String firstValue, String diff) {
        return (!firstValue.equals("")) && (!diff.equals(""));
    }

    /**
     * This function calculates the 20 first values of a given series, and saves them into an array.
     * <p>
     *
     * @param type The type of the series - 0 for arithmetic, 1 for geometric.
     * @param first The first value of the series.
     * @param d The difference/quotient of the series.
     * @param arr The array to save the values of the series in.
     */
    public void createSeriesArr(int type, double first, double d, Double[] arr){
        if(type == 0){
            for(int i = 0; i < 20; i++) {
                arr[i] = first + d * i;
            }
        }
        else{
            for(int i = 0; i < 20; i++) {
                arr[i] = first * Math.pow(d, i);
            }
        }
    }

    /**
     * This function converts the chosen series type into a number, and returns it.
     * <p>
     *
     * @return 0 if the series is arithmetic, 1 if it is geometric.
     */
    public int getSeriesType(){
        if(dgAriRb.isChecked())
            return 0;
        else
            return 1;
    }

    /**
     * This function resets the data of the current series in all of the relevant places in the app.
     */
    public void resetSeriesData() {
        lv.setAdapter(null);

        dgFirstValueEt.setText("");
        dgDiffEt.setText("");

        xOneTv.setText("");
        dTv.setText("");
        nTv.setText("");
        snTv.setText("");

        firstValueStr = diffStr = "";
    }

    /**
     * This function displays information about a chosen value from the list view(the series).
     * <p>
     *
     * @param parent The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked (this
     *            will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        nTv.setText((position + 1) + "");

        seriesSum = calcSeriesSum(this.seriesType, position + 1, this.firstValue, this.diff);
        snTv.setText("" + seriesSum);
    }

    /**
     * This function calculates the sum of a given series, from the first value of it to a given
     * value in the series.
     * <p>
     *
     * @param type The type of the series - 0 for arithmetic, 1 for geometric.
     * @param n The index of the given value in the series.
     * @param a1 The first value of the series.
     * @param d The difference/quotient of the series.
     * @return The sum of the series from a1 to an.
     */
    public double calcSeriesSum(int type, int n, double a1, double d){
        double Sn = 0;

        if(type == 0)
            Sn = ((2 * a1 + d * (n - 1)) * n) / 2;
        else
            Sn = a1 * ((Math.pow(d, n) - 1) / (d - 1));

        return Sn;
    }

    /**
     * This function presents the options menu for moving between activities.
     * <p>
     *
     * @param menu The options menu in which you place your items.
     * @return true in order to show the menu, otherwise false.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This function reacts to the user choice in the options menu - it moves to the chosen
     * activity from the menu.
     * <p>
     *
     * @param item The menu item that was selected.
     * @return Must return true for the menu to react.
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menuCredits){
            si = new Intent(this, CreditsActivity.class);
            startActivity(si);
        }

        return super.onOptionsItemSelected(item);
    }
}