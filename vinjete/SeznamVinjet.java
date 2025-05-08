package vinjete;

import java.util.*;
import java.io.*;

public class SeznamVinjet {
    Vinjeta[] prodaneVinjete;

    public boolean preberiPodatke(String vir) throws Exception {
        try (Scanner sc = new Scanner(new File(vir))) {
            int stVrstic = Integer.parseInt(sc.nextLine());
            prodaneVinjete = new Vinjeta[stVrstic];

            for (int i = 0; i < prodaneVinjete.length; i++) {
                String[] vinjeta = sc.nextLine().split(";");
                prodaneVinjete[i] = new Vinjeta(vinjeta[0], vinjeta[1], vinjeta[2], vinjeta[3]);
            }
            return true;

        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void izpisiVinjete() {
        System.out.println("V sistemu so zabeležene prodane vinjete (" + prodaneVinjete.length + "):");
        for (Vinjeta vi : prodaneVinjete) {
            System.out.println(vi);
        }
    }

    public boolean preveriVinjeto(String registrska) {
        for (Vinjeta vi : prodaneVinjete) {
            if (vi.getRegisterska().equals(registrska)) {
                return true;
            }
        }
        return false;
    }

    public void izpisiVinjete(String vrsta) {
        int count = 0;
        System.out.println("V sistemu je " + vrsta + " vinjeta za naslednja vozila:");

        for (Vinjeta vi : prodaneVinjete) {
            if (vi.getVrsta().equals(vrsta)) {
                System.out.println(vi);
                count++;
            }
        }
        System.out.println("Skupaj " + vrsta + " vinjeta: " + count);
    }

    public void izpisiLetneVeljavnost() {
        System.out.println("Letne vinjete z datumi veljavnosti:");
        for (Vinjeta vi : prodaneVinjete) {
            String[] datum = (vi.getDatum()).split("\\.");
            String dan = datum[0];
            String mesec = datum[1];
            int leto = Integer.parseInt(datum[2]) + 1;
            
            if (vi.getVrsta().equals("letna")) {
                System.out.println(vi.getRegisterska() + ": veljavno do " + dan + "." + mesec + "." + leto);
            }
        }
    }

    public void statistika() {
        Map<String, Map<String, Integer>> razredStevilo = new HashMap<>();

        for (Vinjeta vi : prodaneVinjete) {
            Map<String, Integer> notranjiDel = razredStevilo.get(vi.getRazred());

            if (notranjiDel == null) {
                notranjiDel = new HashMap<>();
                razredStevilo.put(vi.getRazred(), notranjiDel);
            }

            notranjiDel.put(vi.getVrsta(), notranjiDel.getOrDefault(vi.getVrsta(), 0) + 1);
        }

        for (String key : razredStevilo.keySet()) {
            System.out.println(key + " " + razredStevilo.get(key));
        }
    }
}
