public class Zanke {

    static void pravokotnikStevilVrstice(int sirina, int visina){
        for(int i = 1; i <= visina; i++){
            for(int j = 0; j < sirina; j++){
                if(i<10){
                    System.out.print(i);
                }else{
                    System.out.print(1);
                }
            }
            System.out.println();
        }
    } 

    static void pravokotnikStevilStolpci(int sirina, int visina){
        int count;
        
        for(int i = 1; i < visina; i++){
            count = 0;
            for(int j = 1; j <= sirina; j++){
                if(j<10){
                    System.out.print(j);
                }else{
                    System.out.print(count);
                    count++;
                }
            }
            System.out.println();
        }
    }

    static void pravokotnik(int odmik, int sirina, int visina){
        for(int i = 0; i < visina; i++){
            for(int j = 0; j < sirina; j++){
                if(j == 0){
                    System.out.printf("%"+odmik+"s","X");
                }else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }    

    }
    static void romb(int odmik, int velikost){
        for(int i = 1; i <= 2*velikost - 1; i++){
            for(int j = 1; j <= 2*velikost - 1; j++){
                if(i / j == 0){
                    System.out.print("# ");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String [] args){
        // pravokotnikStevilVrstice(7,20);
        // pravokotnikStevilStolpci(15,3);
        // pravokotnik(5,7,3);
        romb(1,5);
    }    
}
