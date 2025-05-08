package banka;

public class VarcevalniRacun extends Racun{
    private double obresti; 

    VarcevalniRacun(double obresti, String stevilka){
        super(stevilka);
        this.obresti = obresti;
    }

    public double getObresti(){
        return obresti;
    }

    public double setObresti(double Nobresti){
        return this.obresti = Nobresti;
    }

    public void dodajObresti(){
        setStanje(getStanje() * getObresti());
    }

    public String opisRacuna(){
        return "(varcevalni, obrestna mera: " + getObresti() + "%):";
    }
}