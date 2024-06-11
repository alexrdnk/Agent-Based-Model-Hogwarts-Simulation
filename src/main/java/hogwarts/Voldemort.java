package hogwarts;

public class Voldemort extends Wizard {
    protected int xVoldemort, yVoldemort, horcrux;
    protected double chanceToKill;

    public Voldemort(int x, int y, int horcrux, double chanceToKill) {
        super(x, y, horcrux, 1, 2);
        this.xVoldemort = x;
        this.yVoldemort = y;
        this.horcrux = horcrux;
        this.chanceToKill = chanceToKill;
    }

    public int getHorcrux() {
        return horcrux;
    }

    public void setHorcrux(int newHorcrux) {
        horcrux = newHorcrux;
    }

    public void killAndTakeSoul(Environment environment, HarryPotter harry) {
        double bonusToKill = 0.03;
        if (!environment.isSunnyWeather()) {
            this.chanceToKill += bonusToKill;
            if (Math.random() < chanceToKill) {
                attackHarry(harry);
            }
            this.chanceToKill -= bonusToKill;
        } else {
            if (Math.random() < chanceToKill) {
                attackHarry(harry);
            }
        }
    }


    public void attackHarry(HarryPotter harry) {
        harry.setHarrySouls(harry.getHarrySouls() - magicDamage);
        setHorcrux(getHorcrux() + 1);
        System.out.println("Voldemort took a Harry`s soul and has one more horcrux\n" + "Harry has: " + harry.getHarrySouls() + " souls");
    }

}
