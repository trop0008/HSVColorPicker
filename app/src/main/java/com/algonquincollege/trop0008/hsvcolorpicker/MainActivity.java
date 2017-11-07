package com.algonquincollege.trop0008.hsvcolorpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 * HSC Color Picker app
 *
 * @author Marjan Tropper (trop0008@algonquinlive.com)
 * @version v.1.0.0
 */

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {
    private static final String ABOUT_DIALOG_TAG = "About Dialog";
    private static final String LOG_TAG = "HSV";

    // INSTANCE VARIABLES
    // Pro-tip: different naming style; the 'm' means 'member'

    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mBrightnessSB;


    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mBrightnessTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Instantiate a new HSV model
        // Initialize the model hue (min), saturation (min), brightness (min)
        mModel = new HSVModel();
        mModel.setHue(HSVModel.MIN_HUE);
        mModel.setSaturation(HSVModel.MIN_SATURATION);
        mModel.setBrightness(HSVModel.MIN_BRIGHTNESS);

        // The Model is observing this Controller (class MainActivity implements Observer)
        mModel.addObserver(this);

        // reference each View
        mColorSwatch = findViewById(R.id.colorSwatch);
        mHueSB = findViewById(R.id.hueSB);
        mSaturationSB = findViewById(R.id.saturationSB);
        mBrightnessSB = findViewById(R.id.brightnessSB);

        mHueTV = findViewById(R.id.hueTV);
        mSaturationTV = findViewById(R.id.saturationTV);
        mBrightnessTV = findViewById(R.id.brightnessTV);

        // set the domain (i.e. max) for each component

        mHueSB.setMax(HSVModel.MAX_HUE);
        mSaturationSB.setMax(HSVModel.MAX_SATURATION);
        mBrightnessSB.setMax(HSVModel.MAX_BRIGHTNESS);

        // register the event handler for each <SeekBar>

        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mBrightnessSB.setOnSeekBarChangeListener(this);

        // initialize the View to the values of the Model
        this.updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Event handler for the <SeekBar>s: Hue, saturation and Brightness(value).
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Did the user cause this event?
        // YES > continue
        // NO  > leave this method
        if (fromUser == false) {
            return;
        }


        // Determine which <SeekBark> caused the event (switch + case)
        // GET the SeekBar's progress, and SET the model to it's new value
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue(mHueSB.getProgress());
                mHueTV.setText(getResources().getString(R.string.hueProgress, progress).toUpperCase());
                break;


            case R.id.saturationSB:
                mModel.setSaturation(mSaturationSB.getProgress());
                mSaturationTV.setText(getResources().getString(R.string.saturationProgress, progress).toUpperCase());
                break;


            case R.id.brightnessSB:
                mModel.setBrightness(mBrightnessSB.getProgress());
                mBrightnessTV.setText(getResources().getString(R.string.brightnessProgress, progress).toUpperCase());
                break;


        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                Toast.makeText(getApplicationContext(), getHSVToastMessage(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                Toast.makeText(getApplicationContext(), getHSVToastMessage(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.brightnessSB:
                mBrightnessTV.setText(getResources().getString(R.string.brightness));
                Toast.makeText(getApplicationContext(), getHSVToastMessage(), Toast.LENGTH_SHORT).show();
                break;

        }
    }

    // The Model has changed state!
    // Refresh the View to display the current values of the Model.
    @Override
    public void update(Observable observable, Object data) {
        Log.i(LOG_TAG, "The color (int) is: " + Color.HSVToColor(mModel.getColor()) + "");

        this.updateView();
    }


    private void updateColorSwatch() {
        //GET the model's SET the background colour of the swatch <TextView>

        mColorSwatch.setBackgroundColor(Color.HSVToColor(mModel.getColor()));
    }

    private void updateHueSB() {
        //GET the model's hue value, and SET the Hue <SeekBar>
        mHueSB.setProgress(mModel.getHue());

    }

    private void updateSaturationSB() {
        //GET the model's saturation value, and SET the saturation <SeekBar>
        mSaturationSB.setProgress(mModel.getSaturation());

    }

    private void updateBrightnessSB() {
        // //GET the model's brightness value, and SET the brighntness <SeekBar>
        mBrightnessSB.setProgress(mModel.getBrightness());

    }


    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateBrightnessSB();
    }


    //BUTTON METHODS IMPLEMENTATION

    public void onColorButtonClick(View view) {
        switch (view.getId()) {
            case R.id.blackButton:
                mModel.asBlack();
                break;
            case R.id.redButton:
                mModel.asRed();
                break;
            case R.id.limeButton:
                mModel.asLime();
                break;
            case R.id.blueButton:
                mModel.asBlue();
                break;
            case R.id.yellowButton:
                mModel.asYellow();
                break;
            case R.id.cyanButton:
                mModel.asCyan();
                break;
            case R.id.magentaButton:
                mModel.asMagenta();
                break;
            case R.id.silverButton:
                mModel.asSilver();
                break;
            case R.id.grayButton:
                mModel.asGray();
                break;
            case R.id.maroonButton:
                mModel.asMaroon();
                break;
            case R.id.oliveButton:
                mModel.asOlive();
                break;
            case R.id.greenButton:
                mModel.asGreen();
                break;
            case R.id.purpleButton:
                mModel.asPurple();
                break;
            case R.id.tealButton:
                mModel.asTeal();
                break;
            case R.id.navyButton:
                mModel.asNavy();
                break;
        }
        this.updateView();
        Toast.makeText(getApplicationContext(), getHSVToastMessage(), Toast.LENGTH_SHORT).show();

    }

    //return a formatted message with hue, saturation and value
    private String getHSVToastMessage() {
        return getResources().getString(
                R.string.hsv_values, mModel.getHue(),
                mModel.getSaturation(),
                mModel.getBrightness());
    }


} // end of class
