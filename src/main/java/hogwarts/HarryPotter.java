package hogwarts;

public class HarryPotter extends Wizard {
    protected int xHarry, yHarry, HarryPotterSouls;
    protected double chanceToBreakHorcrux;

    public HarryPotter(int x, int y, int souls, double chanceToBreakHorcrux) {
        super(x, y, souls, 1, 1);
        this.xHarry = x;
        this.yHarry = y;
        this.HarryPotterSouls = souls;
        this.chanceToBreakHorcrux = chanceToBreakHorcrux;
    }

    public int getHarrySouls() {
        return HarryPotterSouls;
    }

    public void setHarrySouls(int souls) {
        HarryPotterSouls = souls;
    }

    public void horcruxAttack(Environment environment, Voldemort voldemort) {
        double bonusToHit = 0.05;
        if (environment.isSunnyWeather()) {
            this.chanceToBreakHorcrux += bonusToHit;
            if (Math.random() < chanceToBreakHorcrux) {
                attackVoldemort(voldemort);
            }
            this.chanceToBreakHorcrux -= bonusToHit;
        } else {
            if (Math.random() < chanceToBreakHorcrux) {
                attackVoldemort(voldemort);
            }
        }
    }

    public void attackVoldemort(Voldemort voldemort) {
        voldemort.setHorcrux(voldemort.getHorcrux() - magicDamage);
        System.out.println("Harry has break a horcrux\nVoldemort has " + voldemort.getHorcrux() + " horcruxes");
    }

}
