package hogwarts;
import java.io.FileWriter;
import java.io.IOException;


public class SimulationData {

    private int stepSum = 0;
    protected long runTimes;
    protected int stepCounts, aurorsCount, deathEatersCount, aurorsHealth, deathEatersHealth, aurorsDamage, deathEatersDamage, weatherLevel, horcrux, souls;
    protected String winner;

    public void WinnerInfo(String winner) {
        this.winner = winner;
    }

    public void addRunTime(long runTime) {
        this.runTimes = runTime;
    }

    public void addStepCount(int steps) {
        stepSum += steps;
        System.out.println("Step count: " + stepSum);
    }

    public void wizardInfo(int aurorsCount, int deathEatersCount, int aurorsHealth, int deathEatersHealth, int aurorsDamage, int deathEatersDamage, int horcrux, int souls) {
        this.aurorsCount = aurorsCount;
        this.deathEatersCount = deathEatersCount;
        this.aurorsHealth = aurorsHealth;
        this.deathEatersHealth = deathEatersHealth;
        this.aurorsDamage = aurorsDamage;
        this.deathEatersDamage = deathEatersDamage;
        this.horcrux = horcrux;
        this.souls = souls;
    }

    public void WeatherInfo(int skyMagicLevel) {
        weatherLevel = skyMagicLevel;
    }

    public void saveDataToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            stepCounts = stepSum;
            writer.append("Winner: ").append(winner).append("\n");
            writer.append("\nRun time: ");
            writer.append(String.format("%s", runTimes));
            writer.append("\nSteps: ");
            writer.append(String.format("%s", stepCounts));
            writer.append("\nAurors Count: ");
            writer.append(String.format("%s", aurorsCount));
            writer.append("\nDeath Eaters Count: ");
            writer.append(String.format("%s", deathEatersCount));
            writer.append("\nAurors Health: ");
            writer.append(String.format("%s", aurorsHealth));
            writer.append("\nDeath Eaters Health: ");
            writer.append(String.format("%s", deathEatersHealth));
            writer.append("\nAurors Damage: ");
            writer.append(String.format("%s", aurorsDamage));
            writer.append("\nDeath Eaters Damage: ");
            writer.append(String.format("%s", deathEatersDamage));
            writer.append("\nHarry`s souls count: ");
            writer.append(String.format("%s", souls));
            writer.append("\nVoldemort horcrux count: ");
            writer.append(String.format("%s", horcrux));
            writer.append("\nWeather level: ");
            writer.append(String.format("%s", weatherLevel));
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private void handleIOException(IOException e) {
        System.err.println("Failed to write to file: " + e.getMessage());
    }

}
