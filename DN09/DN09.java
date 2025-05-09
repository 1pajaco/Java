import java.util.*;
import java.io.*;

class Postaja {
    private int ID;
    private String ime;
    private int x;
    private int y;
    private int cakajoci;

    public Postaja(int iD, String ime, int x, int y, int cakajoci) {
        ID = iD;
        this.ime = ime;
        this.x = x;
        this.y = y;
        this.cakajoci = cakajoci;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCakajoci() {
        return cakajoci;
    }

    public void setCakajoci(int cakajoci) {
        this.cakajoci = cakajoci;
    }

    public String toString() {
        return getID() + " " + getIme() + " [" + getX() + "," + getY() + "] čakajoči: " + getCakajoci();
    }
}

class Avtobus {
    private int ID;
    private int steviloPotnikov;
    protected Postaja p;

    Avtobus(int iD, int steviloPotnikov) {
        this.ID = iD;
        this.steviloPotnikov = steviloPotnikov;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getSteviloPotnikov() {
        return steviloPotnikov;
    }

    public void setSteviloPotnikov(int SteviloPotnikov) {
        steviloPotnikov = SteviloPotnikov;
    }

    public void setTrenutnaPostaja(Postaja postaja) {
        p = postaja;
    }

    public Postaja getTrenutnaPostaja() {
        return p;
    }

    public String toString() {
        return getID() + " (" + getSteviloPotnikov() + ") - " + p.getIme();
    }
}

class Linija {
    private int ID;
    private String barvaLinije;

    private List<Postaja> listPostaj = new ArrayList<>();
    protected int maxPostaj = 10;

    private List<Avtobus> listAvtobus = new ArrayList<>();
    protected int maxAvtobusov = 5;

    Linija(int ID) {
        this.ID = ID;
    }

    public boolean dodajPostajo(Postaja postaja) {
        if (listPostaj.size() >= maxPostaj) {
            return false;
        }

        listPostaj.add(postaja);
        return true;
    }

    public boolean dodajAvtobus(Avtobus avtobus) {
        if (listAvtobus.size() >= maxAvtobusov) {
            return false;
        }

        listAvtobus.add(avtobus);
        return true;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getBarva() {
        return barvaLinije;
    }

    public void setBarva(String barvaLinije) {
        this.barvaLinije = barvaLinije;
    }

    public List<Postaja> getPostaje() {
        return listPostaj;
    }

    public List<Avtobus> getAvtobusi() {
        return listAvtobus;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Linija ").append(getID()).append(" - ");

        for (int i = 0; i < listPostaj.size(); i++) {
            if (listPostaj.isEmpty()) {
                return "Na liniji ni postaj";
            }

            String postaja = listPostaj.get(i).getIme();
            for (Avtobus a : listAvtobus) {
                if (a.getTrenutnaPostaja().getIme().equals(postaja)) {
                    postaja += " (bus)";
                    break;
                }
            }
            if (i + 1 == listPostaj.size()) {
                sb.append(postaja);
            } else {
                sb.append(postaja + " -> ");
            }

        }
        return sb.toString();
    }
}

public class DN09 {
    static Postaja[] postaje;

    static Avtobus[] avtobusi;
    static int stAvtobusov = 0;

    static Linija[] linije;
    static int stLinij = 0;

    static void preberiInUredi(String datoteka) throws Exception {
        Scanner sc = new Scanner(new File(datoteka));

        String[] parametri = sc.nextLine().split(",");
        int stPostaj = Integer.parseInt(parametri[0]);
        int stLinij = Integer.parseInt(parametri[1]);
        int stAvtobusov = Integer.parseInt(parametri[2]);

        postaje = new Postaja[stPostaj];
        avtobusi = new Avtobus[stAvtobusov];
        linije = new Linija[stLinij];

        sc.nextLine();

        String[] postajeT = new String[stPostaj];
        String[] linijeT = new String[stLinij];

        for (int i = 0; i < stPostaj; i++) {
            postajeT[i] = sc.nextLine();
        }

        sc.nextLine();

        for (int i = 0; i < stLinij; i++) {
            linijeT[i] = sc.nextLine();
        }
        sc.close();

        populatePostaje(postajeT);
        populateLinije(linijeT);
    }

    static void populatePostaje(String[] p) {
        for (int i = 0; i < p.length; i++) {
            String[] postaja = p[i].split(",");

            int id = Integer.parseInt(postaja[0]);
            String ime = postaja[1];
            int x = Integer.parseInt(postaja[2]);
            int y = Integer.parseInt(postaja[3]);
            int cakajoci = Integer.parseInt(postaja[6]);

            postaje[i] = new Postaja(id, ime, x, y, cakajoci);

            if (!postaja[5].isBlank()) {
                String[] avtobusIdStPotnikov = postaja[5].split(";");

                for (int j = 0; j < avtobusIdStPotnikov.length; j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(avtobusIdStPotnikov[j]);
                    sb.replace(avtobusIdStPotnikov[j].indexOf("("), avtobusIdStPotnikov[j].indexOf("(") + 1, ",");
                    sb.replace(avtobusIdStPotnikov[j].indexOf(")"), avtobusIdStPotnikov[j].indexOf(")") + 1, "");
                    String[] avtobus = sb.toString().split(",");

                    int idA = Integer.parseInt(avtobus[0]);
                    int stPotnikov = Integer.parseInt(avtobus[1]);

                    if (!obstajaAvtobus(idA)) {
                        avtobusi[stAvtobusov] = new Avtobus(idA, stPotnikov);
                        avtobusi[stAvtobusov].setTrenutnaPostaja(postaje[i]);
                        stAvtobusov++;
                    }
                }
            }

            String[] idLinije = postaja[4].split(";");
            for (String idL : idLinije) {
                int idLint = Integer.parseInt(idL);
                if (!obstajaLinija(idLint)) {
                    linije[stLinij] = new Linija(idLint);
                    stLinij++;
                }
            }
        }
    }

    static void populateLinije(String[] lin) {
        for (int i = 0; i < lin.length; i++) {
            String[] linija = lin[i].split(",");
            int id = 0;

            for (int j = 0; j < stLinij; j++) {
                if (linije[j].getID() == Integer.parseInt(linija[0])) {
                    id = j;
                }
            }

            String barva = linija[1];
            String idAvtobusov = linija[2];
            String idPostaj = linija[3];

            linije[id].setBarva(barva);

            String[] idAvtobusa = idAvtobusov.split(";");
            for (String idA : idAvtobusa) {
                for (int j = 0; j < stAvtobusov; j++) {
                    if (Integer.parseInt(idA) == avtobusi[j].getID()) {
                        linije[id].dodajAvtobus(avtobusi[j]);
                    }
                }
            }

            String[] idPostaje = idPostaj.split("\\|");
            for (String idP : idPostaje) {
                for (int j = 0; j < postaje.length; j++) {
                    if (Integer.parseInt(idP) == postaje[j].getID()) {
                        linije[id].dodajPostajo(postaje[j]);
                    }
                }
            }

        }
    }

    static boolean obstajaAvtobus(int id) {
        for (int i = 0; i < stAvtobusov; i++) {
            if (avtobusi[i].getID() == id) {
                return true;
            }
        }
        return false;
    }

    static boolean obstajaLinija(int id) {
        for (int i = 0; i < stLinij; i++) {
            if (linije[i].getID() == id) {
                return true;
            }
        }
        return false;
    }

    static void izpisi() {
        for (Linija string : linije) {
            System.out.println(string);
        }
    }

    static void izpisNajboljObremenjenePostaje(int kapaciteta) {
        if (kapaciteta <= 0) {
            System.out.println("Kapaciteta je manjsa ali enaka 0");
            return;
        }

        Map<String, Object> najPostaja = new HashMap<>();
        boolean temp = false;

        for (int i = 0; i < postaje.length; i++) {
            double stCakajoci = postaje[i].getCakajoci();
            double stProstihMest = 0;

            for (int j = 0; j < linije.length; j++) {
                if (linije[j].getPostaje().contains(postaje[i])) {
                    List<Avtobus> avtobusi = linije[j].getAvtobusi();

                    for (Avtobus avtobus : avtobusi) {
                        stProstihMest += ((kapaciteta - avtobus.getSteviloPotnikov()) >= 0)
                                ? kapaciteta - avtobus.getSteviloPotnikov()
                                : 0;
                    }
                }
            }
            if (!najPostaja.containsKey("Razmerje")) {
                najPostaja.put("Razmerje", stProstihMest);
                najPostaja.put("idP", postaje[i].getID());
                najPostaja.put("imePostaje", postaje[i].getIme());
                najPostaja.put("stProstihMest", stProstihMest);
                najPostaja.put("stCakajocih", stCakajoci);
            }

            double Razmerje = 0;

            if (stCakajoci <= 0) {
                Razmerje = stProstihMest;
            } else {
                Razmerje = stProstihMest / stCakajoci;
            }

            if ((double) najPostaja.get("Razmerje") > Razmerje) {
                najPostaja.put("Razmerje", Razmerje);
                najPostaja.put("idP", postaje[i].getID());
                najPostaja.put("imePostaje", postaje[i].getIme());
                najPostaja.put("stProstihMest", stProstihMest);
                najPostaja.put("stCakajocih", stCakajoci);
            } else if ((double) najPostaja.get("Razmerje") == Razmerje) {
                if ((int) najPostaja.get("idP") > postaje[i].getID()) {
                    najPostaja.put("idP", postaje[i].getID());
                    najPostaja.put("imePostaje", postaje[i].getIme());
                    najPostaja.put("stProstihMest", stProstihMest);
                    najPostaja.put("stCakajocih", stCakajoci);
                }
            }
        }

        System.out.println(String.format(
                "Najbolj obremenjena postaja: %d %s",
                (int) najPostaja.get("idP"),
                najPostaja.get("imePostaje")));

        System.out.println(String.format(
                "Cakajoci: %d, Stevilo prostih mest: %d, Razmerje: %.2f",
                ((Number) najPostaja.get("stCakajocih")).intValue(),
                ((Number) najPostaja.get("stProstihMest")).intValue(),
                ((Number) najPostaja.get("Razmerje")).doubleValue()));

    }

    static void naslednjeStanje() {
        for (int i = 0; i < linije.length; i++) {
            Linija linija = linije[i];

            List<Postaja> postaja = linija.getPostaje();
            List<Avtobus> temp = new ArrayList<>();

            for (int j = postaja.size()-1; j > 0; j--) {
                String postajaIme = postaja.get(j).getIme();
                for (Avtobus avtobus : linija.getAvtobusi()) {
                    if (avtobus.getTrenutnaPostaja().getIme().equals(postajaIme)) {
                        if (j + 1 < postaja.size()) {
                            avtobus.setTrenutnaPostaja(postaja.get(j + 1));
                        } else {
                            temp.add(avtobus);
                        }
                    }
                }
            }

            for (int j = 0; j < postaja.size(); j++) {
                for (Avtobus avtobus : temp) {
                    String postajaIme = postaja.get(j).getIme();
                    if (avtobus.getTrenutnaPostaja().getIme().equals(postajaIme)) {
                        if (j - 1 >= 0) {
                            avtobus.setTrenutnaPostaja(postaja.get(j - 1));
                        }
                    }
                }
            }

        }
        izpisi();

    }

    public static void main(String[] args) throws Exception {
        preberiInUredi(args[0]);

        if (args[1].equals("izpisi")) {
            izpisi();
        }
        if (args[1].equals("najboljObremenjena")) {
            izpisNajboljObremenjenePostaje(Integer.parseInt(args[2]));
        }
        if (args[1].equals("premik")) {
            naslednjeStanje();
        }

        // for (Postaja string : postaje) {
        // System.out.println(string);
        // }

        // for (Avtobus string : avtobusi) {
        // System.out.println(string.getTrenutnaPostaja());
        // }

    }
}