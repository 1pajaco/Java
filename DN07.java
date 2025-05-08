import java.util.*;
import java.io.*;

public class DN07 {

    public static class Planet{
        private String ime;
        private int radij;

        public Planet(String ime, int radij){
            this.radij = radij;
            this.ime = ime;
        }

        public String getIme(){
            return ime;
        }

        public double povrsina(){
            return 4 * Math.PI * Math.pow(radij, 2) / 1000000;
        }
    }

    static Planet[] preberiPlanet(String datoteka) throws Exception{
        Scanner sc = new Scanner(new File(datoteka));
        Planet[] planeti = new Planet[8];

        for (int i = 0; i < planeti.length; i++) {
            String[] planet = sc.nextLine().split(":");
            planeti[i] = new Planet(planet[0], Integer.parseInt(planet[1]));
        }

        sc.close();

        return planeti;
    }

    public static int validPlanet(Planet[] planeti, String imePlaneta){
        for (Planet planet : planeti) {
            if(planet.getIme().toUpperCase().equals(imePlaneta.toUpperCase())){
                return (int)Math.round(planet.povrsina());
            }
        }
        return 0;
    }

    public static void izpis(String ime, Planet[] planeti){
        String[] imePlaneta = ime.split("\\+");
        int sum = 0;

        for (int i = 0; i < imePlaneta.length; i++) {
            sum += validPlanet(planeti, imePlaneta[i]);
        }
        System.out.print("Povrsina planetov \"" + ime + "\" je " + sum + " milijonov km2");
    }

    public static void main(String[] args) throws Exception{
        izpis(args[1], preberiPlanet(args[0]));
    }
}
