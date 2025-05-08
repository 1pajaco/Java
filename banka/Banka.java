package banka;

public class Banka {
    private Racun[] racun = new Racun[500];
    private int steviloRacunov;

    public Racun[] getRacun() {
        return racun;
    }

    public void setRacun(Racun[] racun) {
        this.racun = racun;
    }

    public void setSteviloRacunov(int steviloRacunov) {
        this.steviloRacunov = steviloRacunov;
    }

    public int getSteviloRacunov() {
        return steviloRacunov;
    }

    public boolean dodajTekociRacun(String stevilka, double limit) {
        for (int i = 0; i < getSteviloRacunov(); i++) {
            if (getRacun()[i].getStevilka().equals(stevilka)) {
                return false;
            }
        }

        racun[steviloRacunov] = new TekociRacun(limit, stevilka);
        setSteviloRacunov(getSteviloRacunov() + 1);
        return true;

    }

    public boolean dodajVarcevalniRacun(String stevilka, double obresti) {
        for (int i = 0; i < getSteviloRacunov(); i++) {
            if (getRacun()[i].getStevilka().equals(stevilka)) {
                return false;
            }
        }
        racun[steviloRacunov] = new VarcevalniRacun(obresti, stevilka);
        setSteviloRacunov(getSteviloRacunov() + 1);
        setRacun(racun);
        return true;
    }

    public void izpisiRacune() {
        int count = 0;
        for (int i = 0; i < getSteviloRacunov(); i++) {
            count++;
            System.out.println(getRacun()[i]);
        }

        System.out.println(count);
    }

    public void izpisiRacune(boolean varcevalni) {
        int count = 0;
        if (varcevalni) {
            for (int i = 0; i < getSteviloRacunov(); i++) {
                if (getRacun()[i] instanceof VarcevalniRacun) {
                    count++;
                    System.out.println(getRacun()[i]);

                }
            }
        }else{
            for (int i = 0; i < getSteviloRacunov(); i++) {
                if (getRacun()[i] instanceof TekociRacun) {
                    count++;
                    System.out.println(getRacun()[i]);

                }
            }
        }
        System.out.println(count);
    }

    public boolean dvig(String stevilka, double znesek) {
        for (int i = 0; i < getSteviloRacunov(); i++) {
            if (getRacun()[i].getStevilka().equals(stevilka)) {
                getRacun()[i].dvig(znesek);
                return true;
            }
        }
        return false;
    }
    
    public boolean polog(String stevilka, double znesek) {
        for (int i = 0; i < getSteviloRacunov(); i++) {
            if (getRacun()[i].getStevilka().equals(stevilka)) {
                getRacun()[i].polog(znesek);
                return true;
            }
        }
        return false;
    }

    public void dodajObresti() {
        for (int i = 0; i < getSteviloRacunov(); i++) {
            if (getRacun()[i] instanceof VarcevalniRacun) {
                ((VarcevalniRacun)getRacun()[i]).dodajObresti();
            }
        }
    }

}
