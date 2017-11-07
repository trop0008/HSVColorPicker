package model;

import java.util.Observable;

/**
 * The model holds the data.
 * <p>
 * Model color.
 * Based on Hue, Saturation and Values(brightness).
 * <p>
 * Hue: The hue's domain range is: 0 to 359 degrees (inclusive)
 * Saturation: The saturation's domain range is: 0 to 100% (inclusive)
 * Value / Brightness : The value's domain range is: 0 to 100% (inclusive)
 *
 * @author Marjan Tropper (trop0008@algonquinlive.com)
 * @version v.1.0.0
 */

public class HSVModel extends Observable {

    // CLASS VARIABLES
    public static final Integer MAX_HUE = 359;
    public static final Integer MAX_SATURATION = 100;
    public static final Integer MAX_BRIGHTNESS = 100;
    public static final Integer MIN_HUE = 0;
    public static final Integer MIN_SATURATION = 0;
    public static final Integer MIN_BRIGHTNESS = 0;

    //INSTANCE VARIABLES
    private Integer hue;
    private Integer saturation;
    private Integer brightness;

    /**
     * No argument constructor.
     * <p>
     * Instantiate a new instance of this class, and
     * initialize hue, saturation and brightness values.
     */
    public HSVModel() {
        this(MIN_HUE, MIN_SATURATION, MIN_BRIGHTNESS);
    }


    public HSVModel(Integer hue, Integer saturation, Integer brightness) {
        super();
        setHue(hue);
        setSaturation(saturation);
        setBrightness(brightness);
    }

    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged(); // sets dirty flag on the data
        this.notifyObservers(); // broadcasts to all listeners

        // these two methods come from the Observable class
    }

    public void asBlack() {
        setHSV(MIN_HUE, MIN_SATURATION, MIN_BRIGHTNESS);
    }

    public void asWhite() {
        setHSV(MIN_HUE, MIN_SATURATION, MAX_BRIGHTNESS);
    }

    public void asRed() {
        setHSV(MIN_HUE, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asLime() {
        setHSV(120, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asBlue() {
        setHSV(240, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asYellow() {
        setHSV(60, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asCyan() {
        setHSV(180, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asMagenta() {
        setHSV(300, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asSilver() {
        setHSV(MIN_HUE, MIN_SATURATION, 75);
    }

    public void asGray() {
        setHSV(MIN_HUE, MIN_SATURATION, 50);
    }

    public void asMaroon() {
        setHSV(MIN_HUE, MAX_SATURATION, 50);
    }

    public void asOlive() {
        setHSV(60, MAX_SATURATION, 50);
    }

    public void asGreen() {
        setHSV(120, MAX_SATURATION, 50);
    }

    public void asPurple() {
        setHSV(300, MAX_SATURATION, 50);
    }

    public void asTeal() {
        setHSV(180, MAX_SATURATION, 50);
    }

    public void asNavy() {
        setHSV(240, MAX_SATURATION, 50);
    }

    //SETTERS
    public void setHue(Integer hue) {
        if (hue >= MIN_HUE && hue <= MAX_HUE) {
            this.hue = hue;
        } else {
            this.hue = MIN_HUE;
        }

        this.updateObservers();
    }

    public void setSaturation(Integer saturation) {
        if (saturation >= MIN_SATURATION && saturation <= MAX_SATURATION) {
            this.saturation = saturation;
        } else {
            this.saturation = MIN_SATURATION;
        }
        this.updateObservers();
    }

    public void setBrightness(Integer brightness) {
        if (brightness >= MIN_BRIGHTNESS && brightness <= MAX_BRIGHTNESS) {
            this.brightness = brightness;
        } else {
            this.brightness = MIN_BRIGHTNESS;
        }


        this.updateObservers();
    }

    // GETTERS
    public Integer getHue() {
        return this.hue;
    }

    public Integer getSaturation() {
        return this.saturation;
    }

    public Integer getBrightness() {
        return this.brightness;
    }


    public float[] getColor() {

        float[] hsvColor = {this.getHue(), this.getSaturation() / 100.f, this.getBrightness() / 100.f};

        return hsvColor;

    }


    // Convenient setter: set H, S, V
    public void setHSV(Integer hue, Integer saturation, Integer brightness) {
        setHue(hue);
        setSaturation(saturation);
        setBrightness(brightness);
    }

}
