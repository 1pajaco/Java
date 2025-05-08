import java.io.FileNotFoundException;

public class Kviz3 {
    public static boolean jeAnagram(String prvaBeseda, String drugaBeseda, boolean zanemariVelikost){
        if(prvaBeseda.equals(drugaBeseda)){
            return false;
        }
        if(prvaBeseda.length() < drugaBeseda.length() || prvaBeseda.length() < drugaBeseda.length()){
            return false;

        }

        if(zanemariVelikost){
            prvaBeseda = prvaBeseda.toUpperCase();
            drugaBeseda = drugaBeseda.toUpperCase();
            
            
        }else{
            for (int i = 0; i < prvaBeseda.length(); i++) {
                boolean whatever = true;
                for (int j = 0; j < drugaBeseda.length(); j++) {
                    if(prvaBeseda.charAt(i) == drugaBeseda.charAt(j)){
                        whatever = false;
                        break;
                    }
                }
                if(whatever){
                    return false;
                }
            }
        }

        return true;
        
    }

    static String najdaljsiPalindrom(String niz, boolean presledki){
        if(presledki){
            String[] besede = niz.split(" ");
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < besede.length; i++) {
                for (int j = 2; j < besede[i].length() + 1; j++) {
                    sb.append(besede[i].substring(0, j));
                    if (sb.toString().equals(sb.reverse().toString())) {
                        System.out.println("yes " + sb);
                    }else{
                        System.out.println("no1 " + sb.reverse());
                    }
                    sb.delete(0, sb.length());
                }
                for (int j = 2; j < besede[i].length() + 1; j++) {
                    sb.append(besede[i].substring(besede[i].length()-1, besede[i].length()-2));
                    if (sb.toString().equals(sb.reverse().toString())) {
                        System.out.println("yes " + sb);
                    }else{
                        System.out.println("no2 " + sb.reverse());
                    }
                    sb.delete(0, sb.length());
                }
            }
        }

        return "KYS";
    }



        public static class Tocka {
            public int x;
            public int y;
        
            public Tocka(int x, int y) {
                this.x = x;
                this.y = y;
            }
        
            static Tocka[] preberiTocke(String imeDatoteke) {
                try(java.util.Scanner sc = new java.util.Scanner(new java.io.File(imeDatoteke))){
                    java.util.ArrayList<Tocka> seznam = new java.util.ArrayList<>();
            
                    while (sc.hasNextLine()) {
                        java.lang.String vrstica = sc.nextLine().trim();
                        if (vrstica.isEmpty()) continue;
                        java.lang.String[] deli = vrstica.split("\\s+");
                        if (deli.length >= 2) {
                            int x = java.lang.Integer.parseInt(deli[0]);
                            int y = java.lang.Integer.parseInt(deli[1]);
                            seznam.add(new Tocka(x, y));
                        }
                    }
                    sc.close();
                    return seznam.toArray(new Tocka[0]);

                }catch(Exception e){
                    return null;
                }
                
            }
    
        static String tabelaToString(Tocka[] tocke) {
            java.lang.StringBuilder sb = new java.lang.StringBuilder();
            sb.append("[");
            for (int i = 0; i < tocke.length; i++) {
                sb.append("(").append(tocke[i].x).append(",").append(tocke[i].y).append(")");
                if (i < tocke.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static class Matrika{
        private int[][] matrix;

        public Matrika(int velikost){
            this.matrix = new int[velikost][velikost];
        }

        public int[][] getMatrika(){
            return matrix;
        }


        public static Matrika preberiMatriko(String imeDatoteke){
            try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(imeDatoteke))){
                int steviloVr = Integer.parseInt(sc.nextLine());
                Matrika matrika = new Matrika(steviloVr);

                for (int i = 0; i < matrika.matrix.length; i++) {
                    for (int j = 0; j < matrika.matrix[i].length; j++) {
                        matrika.matrix[i][j] = Integer.parseInt(sc.next());
                    }
                }

                return matrika;
                
            }catch(Exception e){
                return null;
            }
        }

        public void izpisi(){
            for (int vrstica[] : matrix) {
                for(int el : vrstica){
                    System.out.printf("%2d", el);
                }
                System.out.println();
            }
        } 

        public Matrika zmnozi(Matrika mat1){
            Matrika rezM = new Matrika(matrix.length);

            for (int i = 0; i < rezM.matrix.length; i++) {
                for (int j = 0; j < rezM.matrix[i].length; j++) {
                    int sum = 0;
                    for (int k = 0; k < matrix[i].length; k++) {
                        sum += matrix[i][k] *  mat1.matrix[k][j];
                        // System.out.println(matrix[i][k] + " " +  mat1.matrix[k][j]);
                    }
                    // System.out.println(" Sum " + sum);

                    rezM.matrix[i][j] = sum;
                }
            }

            return rezM;
        }
    }

    static int[] sestejPolinoma(int[] a, int[] b){
        if(a.length > b.length){
            for (int i = 0; i < b.length; i++) {
                a[i] = a[i] + b[i];
            }

            return a;
        }else{
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i] + b[i];
            }

            return b;
        }
    }

    static int[] zmnoziPolinoma(int[] a, int[] b){
        if(a.length > b.length){
            for (int i = 0; i < b.length; i++) {
                a[i] = a[i] * b[i];
            }

            return a;
        }else{
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i] * b[i];
            }

            return b;
        }
    }
    
    public static void main(String[] args) throws java.lang.Exception{
        // System.out.println(Tocka.tabelaToString(Tocka.preberiTocke("tocke.txt")));
        Matrika a = Matrika.preberiMatriko("m1.txt");
        Matrika b = Matrika.preberiMatriko("m2.txt");
        Matrika c = b.zmnozi(a);

        c.izpisi();

    }
}
