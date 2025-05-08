import java.io.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws FileNotFoundException {

        String datoteka = args[0];
        int GeDolzina = Integer.parseInt(args[1]);
        int seme = Integer.parseInt(args[2]);

        Scanner sc = new Scanner(new File(datoteka));
        int StBesed = Integer.parseInt(sc.next());

        Random rnd = new Random(seme);
        String[] besede = new String[StBesed];

        for(int i = 0; i < StBesed; i++){
            besede[i] = sc.next();
        }

        String beseda = "";

        for(int i = 0; i < GeDolzina; i++){
            beseda = besede[rnd.nextInt(besede.length)];
            System.out.print(beseda.charAt(rnd.nextInt(beseda.length())));    
        }
    }
}