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
        protected Postaja postaja;
        private boolean smerNaprej;

        Avtobus(int iD, int steviloPotnikov) {
            this.ID = iD;
            this.steviloPotnikov = steviloPotnikov;
            this.smerNaprej = true;
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

        public void setTrenutnaPostaja(Postaja Tpostaja) {
            postaja = Tpostaja;
        }

        public Postaja getTrenutnaPostaja() {
            return postaja;
        }

        public void setSmerNaprej(boolean smerNaprej) {
            this.smerNaprej = smerNaprej;
        }

        public boolean isSmerNaprej() {
            return smerNaprej;
        }

        public String toString() {
            return getID() + " (" + getSteviloPotnikov() + ") - "
                    + (postaja != null ? postaja.getIme() : "Neznana postaja");
        }
    }

    class EkspresniAvtobus extends Avtobus {
        Postaja[] preskociPostaje;

        public EkspresniAvtobus(int iD, int steviloPotnikov, Postaja[] preskociPostaje, boolean smerNaprej) {
            super(iD, steviloPotnikov);
            this.preskociPostaje = preskociPostaje;
        }

        public Postaja[] getPreskociPostaje() {
            return preskociPostaje;
        }

        public void setPreskociPostaje(Postaja[] preskociPostaje) {
            this.preskociPostaje = preskociPostaje;
        }

        public String toString() {
            return getID() + " (" + getSteviloPotnikov() + ") - "
                    + (postaja != null ? postaja.getIme() : "Neznana postaja");
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
                String oznaka = "";
                boolean ekspresNajden = false;
                boolean busNajden = false;

                for (Avtobus avtobus : listAvtobus) {
                    if (avtobus.getTrenutnaPostaja().getIme().equals(postaja)) {
                        if (avtobus instanceof EkspresniAvtobus) {
                            ekspresNajden = true;
                        } else {
                            busNajden = true;
                        }
                    }
                }
                if (ekspresNajden) {
                    oznaka += " (ekspres)";
                }
                if (busNajden) {
                    oznaka += " (bus)";
                }
                postaja += oznaka;

                sb.append(postaja);

                if (i < listPostaj.size() - 1) {
                    sb.append(" -> ");
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
                List<Avtobus> avtobusiNaLiniji = linija.getAvtobusi();

                for (Avtobus avtobus : avtobusiNaLiniji) {
                    Postaja trenutnaPostaja = avtobus.getTrenutnaPostaja();
                    int trenutniIndeks = postaja.indexOf(trenutnaPostaja);

                    if (trenutniIndeks != -1 && !(avtobus instanceof EkspresniAvtobus)) {
                        if (avtobus.isSmerNaprej()) {
                            if (trenutniIndeks < postaja.size() - 1) {
                                avtobus.setTrenutnaPostaja(postaja.get(trenutniIndeks + 1));
                            } else {
                                avtobus.setSmerNaprej(false);
                                if (postaja.size() > 1) {
                                    avtobus.setTrenutnaPostaja(postaja.get(trenutniIndeks - 1));
                                }
                            }
                        } else {
                            if (trenutniIndeks > 0) {
                                avtobus.setTrenutnaPostaja(postaja.get(trenutniIndeks - 1));
                            } else {
                                avtobus.setSmerNaprej(true);
                                if (postaja.size() > 1) {
                                    avtobus.setTrenutnaPostaja(postaja.get(trenutniIndeks + 1));
                                }
                            }
                        }
                    }
                }
            }
        }

        static void izpisiPoNPremikih(int n) {
            System.out.println("Zacetno stanje");
            izpisi();
            System.out.println();

            for (int i = 0; i < n; i++) {
                naslednjeStanje();
            }

            System.out.println("Stanje po " + n + " premikih");
            izpisi();
        }

        static void dodajEkspresneAvtobuse() {
            Random rnd = new Random();

            for (Linija linija : linije) {
                Postaja[] preskociPostajo = new Postaja[Math.max(0, linija.getPostaje().size() - 2)];
                for (int i = 1; i < linija.getPostaje().size() - 1; i++) {
                    preskociPostajo[i - 1] = linija.getPostaje().get(i);
                }

                EkspresniAvtobus novAvtobusExpr = new EkspresniAvtobus(rnd.nextInt(), rnd.nextInt(), preskociPostajo, true);
                novAvtobusExpr.setTrenutnaPostaja(linija.getPostaje().get(0));
                linija.dodajAvtobus(novAvtobusExpr);
            }
        }

        static void naslednjeStanjeEkspres() {
            for (int i = 0; i < linije.length; i++) {
                Linija linija = linije[i];
                List<Avtobus> avtobusiNaLiniji = linija.getAvtobusi();

                for (Avtobus avtobus : avtobusiNaLiniji) {
                    if (avtobus instanceof EkspresniAvtobus) {
                        if (avtobus.isSmerNaprej()) {
                            avtobus.setTrenutnaPostaja(linija.getPostaje().get(linija.getPostaje().size() - 1));
                            avtobus.setSmerNaprej(false);
                        } else {
                            avtobus.setTrenutnaPostaja(linija.getPostaje().get(0));
                            avtobus.setSmerNaprej(true);
                        }
                    }
                }
            }
        }

        static void izpisiPoNPremikihEkspres(int n) {
            System.out.println("Zacetno stanje");
            izpisi();
            System.out.println();

            for (int i = 0; i < n; i++) {
                naslednjeStanjeEkspres();
                naslednjeStanje();
            }

            System.out.println("Stanje po " + n + " premikih");
            izpisi();
        }

        static void casiPrihodov(int IDpostaje, int maxRazdalja) {
            Postaja postaja = postaje[IDpostaje - 1];
            System.out.println("Postaja " + postaja.getIme() + ":");

            for (Linija linija : linije) {
                int indexPostaje = linija.getPostaje().indexOf(postaja);
                if (indexPostaje != -1) {
                    for (Avtobus avtobus : linija.getAvtobusi()) {
                        int indexAvtobusa = linija.getPostaje().indexOf(avtobus.getTrenutnaPostaja());
                        int Razdalja = Math.abs(indexAvtobusa - indexPostaje);

                        if (maxRazdalja >= Razdalja) {
                            System.out.println(
                                    "Linija " + linija.getID() + " Avtobus " + avtobus.getID() + " -  " + Razdalja);
                        }
                    }
                }
            }
        }

        // Uporabljam BFS ~ Breadth-first, ker Dijkstra algoritem mi ni uspelo
        // implementirati
        static void steviloPrestopov(int zacetekID, int konecID) {
            Postaja zacetekPostaja = null, koncnPostaja = null;

            for (Postaja postaja : postaje) {
                if (postaja.getID() == zacetekID) {
                    zacetekPostaja = postaja;
                }
                if (postaja.getID() == konecID) {
                    koncnPostaja = postaja;
                }
            }

            for (Linija linija : linije) {
                if (linija.getPostaje().contains(koncnPostaja) && linija.getPostaje().contains(zacetekPostaja)) {
                    System.out.println(
                            "Za pot " + zacetekPostaja.getIme() + " -> " + koncnPostaja.getIme() + " prestop ni potreben");
                    return;
                }
            }

            Queue<PostajaInfo> queue = new LinkedList<>();
            Map<Postaja, Integer> minPrestopi = new HashMap<>();

            queue.add(new PostajaInfo(zacetekPostaja, 0));
            minPrestopi.put(zacetekPostaja, 0);

            while (!queue.isEmpty()) {
                PostajaInfo trenutnaInfo = queue.poll();
                Postaja trenutnaPostaja = trenutnaInfo.postaja;
                int prestopi = trenutnaInfo.prestopi;

                if (trenutnaPostaja == koncnPostaja) {
                    System.out.println("Za pot " + zacetekPostaja.getIme() + " -> " + koncnPostaja.getIme()
                            + " je potrebno prestopiti " + (prestopi - 1) + "-krat");
                    return;
                }

                for (Linija linija : linije) {
                    if (linija.getPostaje().contains(trenutnaPostaja)) {
                        for (Postaja sosednjaPostaja : linija.getPostaje()) {
                            int noviPrestopi = prestopi + 1;
                            if (!minPrestopi.containsKey(sosednjaPostaja)
                                    || noviPrestopi < minPrestopi.get(sosednjaPostaja)) {
                                minPrestopi.put(sosednjaPostaja, noviPrestopi);
                                queue.add(new PostajaInfo(sosednjaPostaja, noviPrestopi));
                            }
                        }
                    }
                }
            }

            System.out.println("Za pot " + zacetekPostaja.getIme() + " -> " + koncnPostaja.getIme() + " pot ni mozna");

        }

        static class PostajaInfo {
            Postaja postaja;
            int prestopi;

            public PostajaInfo(Postaja postaja, int prestopi) {
                this.postaja = postaja;
                this.prestopi = prestopi;
            }
        }

        static boolean[] postajaObstaja(String Indentifierpostaja) {
            boolean postajaObstaja[] = { false, false };

            try {
                int idPostaje = Integer.parseInt(Indentifierpostaja.trim());
                for (Postaja postaja : postaje) {
                    if (postaja.getID() == idPostaje) {
                        postajaObstaja[0] = true;
                        break;
                    }
                }
            } catch (Exception e) {
                postajaObstaja[1] = true;
                for (Postaja postaja : postaje) {
                    if (postaja.getIme().equals(Indentifierpostaja.trim())) {
                        postajaObstaja[0] = true;
                        break;
                    }
                }
            }
            return postajaObstaja;
        }

        static void izpisNajblizjePostaje(String imePostaje) {
            boolean[] obstajaPostaja = postajaObstaja(imePostaje);

            if (!obstajaPostaja[0]) {
                if(obstajaPostaja[1]){
                    System.out.println("Postaja z imenom " + imePostaje + "ne obstaja.");
                }else{
                    System.out.println("Postaja z ID " + imePostaje + "ne obstaja.");
                }
                return;
            }

            Postaja zacetnPostaja = null;
            if (obstajaPostaja[1]) {
                for (Postaja postaja : postaje) {
                    if (postaja.getIme().equals(imePostaje.trim())) {
                        zacetnPostaja = postaja;
                    }
                }
            } else {
                for (Postaja postaja : postaje) {
                    if (postaja.getID() == Integer.parseInt(imePostaje.trim())) {
                        zacetnPostaja = postaja;
                    }
                }
            }

            double x1 = zacetnPostaja.getX(), y1 = zacetnPostaja.getY();
            double minRazdalja = -1;
            Postaja minPostaja = null;
            for (Postaja postaja : postaje) {
                if (postaja != zacetnPostaja) {
                    double x2 = postaja.getX(), y2 = postaja.getY();
                    double razdalja = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                    if (minRazdalja == -1) {
                        minRazdalja = razdalja;
                        minPostaja = postaja;
                    }
                    if (razdalja < minRazdalja) {
                        if (razdalja == minRazdalja && postaja.getIme().compareTo(minPostaja.getIme()) < 0) {
                            minRazdalja = razdalja;
                            minPostaja = postaja;
                        } else {
                            minRazdalja = razdalja;
                            minPostaja = postaja;
                        }
                    }
                }
            }

            System.out
                    .println(String.format("Najblizja postaja %s (ID %d):\n%d. %s - razdalja: %.2f",
                    zacetnPostaja.getIme(), zacetnPostaja.getID(), minPostaja.getID(), minPostaja.getIme(), minRazdalja));
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
                izpisiPoNPremikih(Integer.parseInt(args[2]));
            }
            if (args[1].equals("ekspres")) {
                dodajEkspresneAvtobuse();
                izpisiPoNPremikihEkspres(Integer.parseInt(args[2]));
            }
            if (args[1].equals("prihodi")) {
                casiPrihodov(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            if (args[1].equals("stPrestopov")) {
                steviloPrestopov(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            if (args[1].equals("najblizja")) {
                String imePostaje = "";
                for (int i = 2; i < args.length; i++) {
                    imePostaje += args[i] + " ";
                }
                izpisNajblizjePostaje(imePostaje);

            }
        }
    }