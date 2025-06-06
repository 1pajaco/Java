package banka;

public abstract class Racun {
    private double stanje; // stanje na računu
    private final String stevilka; // številka računa

    /**
     * Konstruktor: ustvari račun s podano številko računa in začetnim stanjem 0,00 EUR.
     *
     * @param stevilka - številka računa
     */
    Racun(String stevilka) {
        this.stanje = 0.0;
        this.stevilka = stevilka;
    }

    /**
     * Položi znesek "znesek" na račun.
     *
     * @param znesek - položen znesek, pozitivno število
     * @return true, če je polog uspešno izveden, sicer false (v primeru nepozitivnega pologa)
     */
    public boolean polog(double znesek) {
        if(znesek > 0){
            setStanje(getStanje() + znesek);
            return true;
        }
        return false;
    }

    /**
     * Dvigne znesek "znesek" z računa. Stanje računa je lahko tudi negativno.
     *
     * @param znesek - dvignjen znesek, pozitivno število
     * @return true, če je dvig uspešno izveden, sicer false (v primeru nepozitivnega zneska)
     */
    public boolean dvig(double znesek) {
        if(znesek > 0){
            setStanje(getStanje() - znesek);
            return true;
        }
        return false;
    }

    /**
     * Vrne trenutno stanje na računu (getter).
     *
     * @return stanje na računu
     */
    public double getStanje() {
        return stanje;
    }

    public double setStanje(double Nstanje){
        return this.stanje = Nstanje;
    }

    /**
     * Vrne številko računa (getter).
     *
     * @return številka računa
     */
    public String getStevilka() {
        return stevilka;
    }

    /**
     * Abstraktna metoda, ki vrne opis računa glede na njegov tip.
     *
     * @return niz z opisom računa
     */
    abstract String opisRacuna();

    /**
     * Pripravi niz, ki opisuje račun in njegovo trenutno stanje.
     * Niz je naslednje oblike: "Račun <stevilka> (<opis>): <stanje> EUR",
     * kjer je <stevilka> številka računa, <stanje> je stanje na računu, <opis> pa je opis tipa računa.
     *
     * @return niz, ki opisuje račun in stanje na njem
     */
    @Override
    public String toString() {
        return "Račun " + stevilka + " " + opisRacuna() + " " + stanje + " EUR";
    }
}
