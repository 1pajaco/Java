import java.io.*;
import java.util.*;

public class DN03 {
    public static void main(String[] args) throws Exception {

        String datoteka = args[0];
        int GeDolzina = Integer.parseInt(args[1]);
        int seme = Integer.parseInt(args[2]);

        Scanner sc = new Scanner(new File(datoteka));
        sc.nextLine();

        Random rnd = new Random(seme);
        ArrayList<String> besede = new ArrayList<String>();

        while(sc.hasNextLine()){
            besede.add(sc.nextLine());
        }

        for(int i = 0; i < GeDolzina; i++){
            String beseda = besede.get(rnd.nextInt(besede.size()));
            System.out.print(beseda.charAt(rnd.nextInt(beseda.length())));
        }

        sc.close();
    }
}