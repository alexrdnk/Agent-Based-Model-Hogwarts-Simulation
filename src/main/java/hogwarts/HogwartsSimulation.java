package hogwarts;

import javax.swing.*;
import java.util.*;

import static java.lang.System.*;

public class HogwartsSimulation {
    public long startTime = currentTimeMillis();
    public long endTime;
    private final Environment environment;
    protected final List<Wizard> wizards;
    protected List<HarryPotter> harryList;
    protected List<Voldemort> voldemortList;
    private final SimulationData data = new SimulationData();

    public HogwartsSimulation(Environment environment) {
        this.environment = environment;
        this.wizards = new ArrayList<>();
        this.harryList = new ArrayList<>();
        this.voldemortList = new ArrayList<>();
    }

    public void addWizard(Wizard wizard) {
        wizards.add(wizard);
        environment.placeWizard(wizard, wizard.getX(), wizard.getY());
    }

    public void addHarry(HarryPotter harry) {
        harryList.add(harry);
        addWizard(harry);
        environment.placeWizard(harry, harry.getX(), harry.getY());
    }

    public void addVoldemort(Voldemort voldemort) {
        voldemortList.add(voldemort);
        addWizard(voldemort);
        environment.placeWizard(voldemort, voldemort.getX(), voldemort.getY());
    }

    public void run(int steps) {
        data.addStepCount(steps);
        for (int i = 0; i < steps; i++) {
            environment.updateWeather();

            // Process generic wizards
            processWizards();

            // Process Harry and Voldemort interactions
            processHarryAndVoldemortInteractions();

            // Check if there is a winner and exit early if so
            if (checkForWinner()) {
                break;
            }
        }
        if (wizards.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All wizards have been eliminated. No winner!");
        }
    }

    private void processWizards() {
        List<Wizard> toRemove = new ArrayList<>();
        for (Wizard wizard : wizards) {
            wizard.move(environment);
            wizard.act(environment);
            if (wizard.getHealth() <= 0) {
                toRemove.add(wizard);
                environment.removeWizard(wizard);
            }
        }
        wizards.removeAll(toRemove);
    }

    private void processHarryAndVoldemortInteractions() {
        if (harryList.isEmpty() || voldemortList.isEmpty()) {
            return;
        }

        Iterator<Wizard> wizardIterator = wizards.iterator();
        while (wizardIterator.hasNext()) {
            Wizard wizard = wizardIterator.next();
            if (isHarryOrVoldemort(wizard)) {
                processInteractions(wizardIterator, wizard);
            }
        }
    }

    private boolean isHarryOrVoldemort(Wizard wizard) {
        return wizard instanceof HarryPotter || wizard instanceof Voldemort;
    }

    private void processInteractions(Iterator<Wizard> wizardIterator, Wizard wizard) {

        if (!voldemortList.isEmpty()) {
            for (HarryPotter iHarry : harryList) {
                iHarry.horcruxAttack(environment, voldemortList.getFirst());
            }
            if (voldemortList.getFirst().getHorcrux() <= 0) {
                out.println("Voldemort killed!");
                removeWizardIfMatch(wizardIterator, wizard, Voldemort.class);
                for (Wizard eWizard : wizards) {
                    eWizard.setTeam(1);
                }
            }
        }

        if (!harryList.isEmpty()) {
            for (Voldemort iVoldemort : voldemortList) {
                iVoldemort.killAndTakeSoul(environment, harryList.getFirst());
            }
            if (harryList.getFirst().getHarrySouls() <= 0) {
                out.println("Harry killed!");
                removeWizardIfMatch(wizardIterator, wizard, HarryPotter.class);
                for (Wizard eWizard : wizards) {
                    eWizard.setTeam(2);
                }
            }
        }

    }

    private void removeWizardIfMatch(Iterator<Wizard> wizardIterator, Wizard specialWizard, Class<?> wizardClass) {
        if (wizardClass.isInstance(specialWizard)) {
            wizardIterator.remove();
            environment.removeWizard(specialWizard);
            if (specialWizard instanceof HarryPotter) {
                harryList.removeFirst();
            } else {
                voldemortList.removeFirst();
            }
        }
    }

    public boolean checkForWinner() {
        boolean allAurors = true;
        boolean allDeathEaters = true;
        for (Wizard wizard : wizards) {
            if (wizard.getTeam() == 1 && !(wizard instanceof HarryPotter)) {
                allDeathEaters = false;
            } else if (wizard.getTeam() == 2 && !(wizard instanceof Voldemort)) {
                allAurors = false;
            }
        }
        if (allAurors || allDeathEaters) {
            if (allAurors) declareWinner(1);
            else declareWinner(2);
        }
        return false;
    }

    private void declareWinner(int team) {
        String teamName = team == 1 ? "Aurors" : "Death Eaters";
        if (!harryList.isEmpty() && !voldemortList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The winner is the " + teamName + "!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(null, "The winner is the " + teamName + "!\n             Hero killed!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        data.WinnerInfo(teamName);
        endTime = currentTimeMillis();
        data.addRunTime(endTime - startTime);
        out.println("Time: " + ((endTime - startTime) * 0.001) + " s");
        data.saveDataToFile("src\\main\\resources\\info.txt");
        exit(0);
    }


    public void setup(int aurorsCount, int deathEatersCount, int aurorsHealth, int deathEatersHealth, int aurorsDamage, int deathEatersDamage, int skyMagicLevel,int harrySouls,int voldemortHorcrux) {
        Random rand = new Random();
        for (int i = 0; i < 1; i++){
            HarryPotter harry = new HarryPotter(7, 15, harrySouls, 0.05);
            addHarry(harry);
            Voldemort voldemort = new Voldemort(42, 15, voldemortHorcrux, 0.01);
            addVoldemort(voldemort);
        }
        for (int i = 0; i < aurorsCount; i++) {
            int x = rand.nextInt(environment.getWidth());
            int y = rand.nextInt(environment.getHeight());
            Wizard wizard = new Wizard(x, y, aurorsHealth, aurorsDamage, 1);
            addWizard(wizard);
        }
        // Adding Death Eaters
        for (int i = 0; i < deathEatersCount; i++) {
            int x = rand.nextInt(environment.getWidth());
            int y = rand.nextInt(environment.getHeight());
            Wizard deathEater = new Wizard(x, y, deathEatersHealth, deathEatersDamage, 2);
            addWizard(deathEater);
        }
        environment.setUpdateFrequency(skyMagicLevel);
        data.wizardInfo(aurorsCount, deathEatersCount, aurorsHealth, deathEatersHealth, aurorsDamage, deathEatersDamage, voldemortHorcrux, harrySouls);
        data.WeatherInfo(skyMagicLevel);
    }

    public static void main(String[] args) {
        int width = 50;
        int height = 30;
        int cellSize = 30;

        Environment environment = new Environment(width, height);
        HogwartsSimulation simulation = new HogwartsSimulation(environment);
        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new HogwartsGUI(environment, cellSize, simulation));
    }

}