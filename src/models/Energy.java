package models;

import presenters.Constants;

import java.awt.*;

public class Energy extends Rectangle {
    private boolean isVisible;

    public Energy(int x, int y) {
        super(x, y, Constants.ENERGY_SIZE, Constants.ENERGY_SIZE);
        isVisible = true;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
