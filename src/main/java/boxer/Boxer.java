package boxer;


public class Boxer {
    String name;
    int strength = 0;
    double strengthUnits = 0;
    int win = 0;

    public Boxer(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setStrengthUnits(double dice_value) {
        strengthUnits = dice_value * strength;
    }

    public double getStrengthUnits() {
        return strengthUnits;
    }

    public void wonRound() {
        win++;
    }

    public int won() {
        return win;
    }

}
    