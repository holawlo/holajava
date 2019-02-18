package boxer;

import java.util.*;

public class FightApp {
    // nice beginnings:)

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int rounds = 0;

        Boxer boxer1 = new Boxer("Ola", 9);
        boxer1.name = "Ola";
        Boxer boxer2 = new Boxer("Dresik", 6);
        boxer2.name = "Dresik";

        System.out.println("Todays match is " + boxer1.name + " vs " + boxer2.name);
        System.out.println("type number of rounds");
        rounds = input.nextInt();
        System.out.println("after " + rounds + " rounds");

        for (int i = 0; i < rounds; i++) {

            boxer1.setStrengthUnits(rolldice());
            boxer2.setStrengthUnits(rolldice());
            if (boxer1.getStrengthUnits() > boxer2.getStrengthUnits()) {
                boxer1.wonRound();
            } else if (boxer1.getStrengthUnits() < boxer2.getStrengthUnits()) {
                boxer2.wonRound();
            } else {
                boxer1.wonRound();
                boxer2.wonRound();
            }
        }

        System.out.println(boxer2.getName() + ": " + boxer2.won() + " vs. " + boxer1.getName() + ": " + boxer1.won());

        if (boxer1.won() > boxer2.won()) {
            System.out.println(boxer1.getName() + " won!");
        } else if (boxer1.won() < boxer2.won()) {
            System.out.println(boxer2.getName() + " won!");
        } else {
            System.out.println("It's tie!");
        }
    }

    public static double rolldice() {
        double x = Math.random() * 6;
        return Math.ceil(x);
    }
}


    
 