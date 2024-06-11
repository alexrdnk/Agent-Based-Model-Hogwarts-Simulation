package hogwarts;

import java.util.Random;

public class Wizard {
    protected int x, y;
    protected int health;
    protected int magicDamage;
    protected int team;

    private int lastDx = 0;
    private int lastDy = 0;
    private static final Random RANDOM = new Random();

    public Wizard(int x, int y, int health, int magicDamage, int team) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.magicDamage = magicDamage;
        this.team = team;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void move(Environment environment) {
        int dx, dy;
        boolean moved = false;
        if (this instanceof HarryPotter) {
            environment.moveWizard(this, 7, 15);
        }
        else if (this instanceof Voldemort) {
            environment.moveWizard(this, 42, 15);
        }
        else {
            for (int attempts = 0; attempts < 10 && !moved; attempts++) {
                // Try to move in a new random direction or continue in the same general direction
                dx = (RANDOM.nextBoolean() ? lastDx : RANDOM.nextInt(3) - 1);
                dy = (RANDOM.nextBoolean() ? lastDy : RANDOM.nextInt(3) - 1);

                int newX = x + dx;
                int newY = y + dy;

                // Check if the new position is valid and not the same as the current position
                if ((dx != 0 || dy != 0) && environment.isValidPosition(newX, newY) && environment.isEmpty(newX, newY)) {
                    environment.moveWizard(this, newX, newY);
                    lastDx = dx; // Update last directions
                    lastDy = dy;
                    moved = true;
                }
            }

            // If no valid move was found, try to stay or reset direction
            if (!moved) {
                lastDx = 0;
                lastDy = 0;
            }
        }
    }

    public void act(Environment environment) {
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int newX = x + xOffset;
                int newY = y + yOffset;
                if (environment.isValidPosition(newX, newY)) {
                    Wizard wizard = environment.getWizardAt(newX, newY);
                    if (wizard != null && wizard.getTeam() != this.getTeam() && (wizard instanceof HarryPotter || wizard instanceof Voldemort)) {
                        move(environment);
                    }
                    else if (wizard != null && wizard.getTeam() != this.getTeam()) {
                        int chanceOfDefense = 10;
                        if (environment.isSunnyWeather() && wizard.getTeam() == 1) {
                            if (RANDOM.nextInt(100) < chanceOfDefense) {
                                move(environment);
                            } else {
                                attack(wizard);
                            }
                        } else if (!environment.isSunnyWeather() && wizard.getTeam() == 2) {
                            if (RANDOM.nextInt(100) < chanceOfDefense) {
                                move(environment);
                            } else {
                                attack(wizard);
                            }
                        }
                    }
                }
            }
        }
    }

    public void attack(Wizard enemy) {
        if (enemy != null && this.team != enemy.getTeam()) {
            enemy.setHealth(enemy.getHealth() - this.magicDamage);
        }
    }

}