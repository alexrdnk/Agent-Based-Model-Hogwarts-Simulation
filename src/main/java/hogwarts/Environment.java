package hogwarts;

import java.util.Random;

public class Environment {
    private final Wizard[][] grid;
    private final int width;
    private final int height;
    private boolean isSunnyWeather;
    private static final Random random = new Random();
    private int stepCounter = 0;  // To keep track of the number of simulation steps
    private int updateFrequency;  // To determine how often the weather should change

    public Environment(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Wizard[width][height];
        this.isSunnyWeather = random.nextBoolean();
    }

    // Update frequency based on magic level
    public void setUpdateFrequency(int magicLevel) {
        switch (magicLevel) {
            case 1:
                updateFrequency = 5;
                break;
            case 2:
                updateFrequency = 10;
                break;
            case 3:
                updateFrequency = 15;
                break;
            case 4:
                updateFrequency = 20;
                break;
            case 5:
                updateFrequency = 30;
                break;
        }
    }

    public int getUpdateFrequency() {
        return updateFrequency;
    }

    public void updateWeather() {
        if (++stepCounter >= updateFrequency) {
            isSunnyWeather = random.nextBoolean();
            stepCounter = 0;  // Reset the counter after updating the weather
        }
    }

    public boolean isSunnyWeather() {
        return isSunnyWeather;
    }


    public boolean isValidPosition(int x, int y) {
        // Check if the position is within the grid bounds
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        // Check if the position is within the invalid rectangle
        return x < 8 || x > 41 || y < 14 || y > 16;
    }

    public boolean isEmpty(int x, int y) {
        return grid[x][y] == null;
    }

    public void moveWizard(Wizard wizard, int newX, int newY) {
        grid[wizard.getX()][wizard.getY()] = null;
        wizard.x = newX;
        wizard.y = newY;
        grid[newX][newY] = wizard;
    }

    public void removeWizard(Wizard wizard) {
        grid[wizard.getX()][wizard.getY()] = null;
    }


    public Wizard getWizardAt(int x, int y) {
        if (isValidPosition(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    public void placeWizard(Wizard wizard, int x, int y) {
        if (isValidPosition(x, y) && isEmpty(x, y)) {
            grid[x][y] = wizard;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
