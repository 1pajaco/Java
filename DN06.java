public class DN06 {
    public static void main(String[] args) {
        String podatki = args[0];

        int scale = 9;
        StdDraw.setScale(scale, 0);

        for (double i = 0; i < scale; i++){
            double x = 0.5;
            for (int j = scale - 1, count = 0; j >= 0; j--, count++) {
                StdDraw.square(x+j, x+i, x);
                String vrednost = Character.toString(podatki.charAt(((int)(count + i*scale))));
                if(!vrednost.equals("0")){
                    StdDraw.text(x+j, x+i, vrednost);  
                }
            }
        }
        
        StdDraw.setPenRadius(0.006);
        
        for (int i = 0; i < scale; i += 3){
            double x = 1.5;
            for (int j = 0; j < scale; j += 3) {
                StdDraw.square(x+i, x+j, x);   
            }
        }
    }
}