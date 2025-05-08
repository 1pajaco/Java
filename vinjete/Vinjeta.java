package vinjete;

public class Vinjeta {
    private String registerska;
    private String razred;
    private String Dzacetek;
    private String vrsta;

    public Vinjeta(String registerska, String razred, String Dzacetek, String vrsta) {
        this.registerska = registerska;
        this.razred = razred;
        this.Dzacetek = Dzacetek;
        this.vrsta = vrsta;
    }

    public String getRegisterska() {
        return registerska;
    }

    public String getVrsta() {
        return vrsta;
    }

    public String getDatum() {
        return Dzacetek;
    }

    public String getRazred() {
        return razred;
    }

    public void setRegisterska(String registerska) {
        this.registerska = registerska;
    }

    @Override
    public String toString() {
        String temp = "%s [%s]: %s (%s)";
        // return String.format(temp, registerska, razred, vrsta, Dzacetek);
        return registerska + " [" + razred + "]: " + vrsta + " (" + Dzacetek + ")";
    }
}
