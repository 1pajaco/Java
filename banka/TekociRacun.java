package banka;

public class TekociRacun extends Racun {
    private double limit;

    TekociRacun(double limit, String stevilka){
        super(stevilka);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public boolean dvig(double znesek){
        if(znesek > limit){
            return false;
        }
        return super.dvig(znesek);
    }

    public String opisRacuna(){
        return "(tekoči, limit: " + getLimit() + " EUR):";
    }
}
