package aplikacija;

import vinjete.SeznamVinjet;

public class Kontrola {
    public static void main(String[] args) throws Exception {
        SeznamVinjet list = new SeznamVinjet();

        list.preberiPodatke("vinjete/vinjete.txt");
        list.izpisiVinjete();

        // System.out.println(list.preveriVinjeto("GOMARY"));

        // list.izpisiVinjete("tedenska");

        // list.izpisiLetneVeljavnost();

        // list.statistika();
    }
}
