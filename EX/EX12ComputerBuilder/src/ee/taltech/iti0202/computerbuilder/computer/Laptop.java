package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Battery;
import ee.taltech.iti0202.computerbuilder.components.Keyboard;
import ee.taltech.iti0202.computerbuilder.components.Screen;
import ee.taltech.iti0202.computerbuilder.components.Touchpad;

public class Laptop extends Computer {
    private Battery battery;
    private Keyboard keyboard;
    private Touchpad touchpad;
    private Screen screen;

    public Laptop() {
        super();
        this.battery = null;
        this.keyboard = null;
        this.touchpad = null;
        this.screen = null;
    }

    public Battery getBattery() {

        return battery;
    }

    public Keyboard getKeyboard() {

        return keyboard;
    }

    public Touchpad getTouchpad() {

        return touchpad;
    }

    public Screen getScreen() {

        return screen;
    }

    public void setBattery(Battery battery) {

        this.battery = battery;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setTouchpad(Touchpad touchpad) {
        this.touchpad = touchpad;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public int getComputerValue() {
        return super.getComputerValue() + battery.getPrice() + keyboard.getPrice()
                + touchpad.getPrice() + screen.getPrice();
    }
}
