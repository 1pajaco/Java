abstract class Lik {
    abstract public double obseg();
    abstract public int getA();
}

class Kvadrat extends Lik {
    private int a;

    Kvadrat(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public double obseg() {
        return 4 * a;
    }
}

class Pravokotnik extends Kvadrat {
    private int b;

    Pravokotnik(int a, int b) {
        super(a);
        this.b = b;
    }

    public int getN() {
        return b;
    }

    public double obseg() {
        return 2*getA() + 2*b;
    }
}

class NKotnik extends Pravokotnik {
    NKotnik(int a, int n) {
        super(a, n);
    }

    public double obseg() {
        return getN() * getA();
    }
}

public class DN08 {
    static String[] liki;

    static int skupniObseg(String[] liki) {
        int rez = 0;
        for (String lik : liki) {
            String[] splitLik = lik.split(":");
            String ime = splitLik[0];
            int a = Integer.parseInt(splitLik[1]);
            int b;

            switch (ime) {
                case "kvadrat":
                    Kvadrat kvadrat = new Kvadrat(a);
                    rez += kvadrat.obseg();
                    break;

                case "pravokotnik":
                    b = Integer.parseInt(splitLik[2]);
                    Pravokotnik pravokotnik = new Pravokotnik(a, b);
                    rez += pravokotnik.obseg();
                    break;

                case "nkotnik":
                    b = Integer.parseInt(splitLik[2]);
                    NKotnik nkotnik = new NKotnik(a, b);
                    rez += nkotnik.obseg();
                    break;
            }
        }
        return rez;
    }

    public static void main(String[] args) {
        liki = new String[args.length];
        for (int i = 0; i < liki.length; i++) {
            liki[i] = args[i];
        }
        System.out.println(skupniObseg(liki));
    }
}
