import java.util.*;

public class Kviz1 {

    static void java(){
        System.out.println("   J    a   v     v  a   ");
        System.out.println("   J   a a   v   v  a a  ");
        System.out.println("J  J  aaaaa   V V  aaaaa  ");
        System.out.println(" JJ  a     a   V  a     a");
    }

    static void kalkulator(int a, int b){
        if(b != 0){
            System.out.println(a+" + "+b+" = "+ (a+b));
            System.out.println(a+" - "+b+" = "+ (a-b));
            System.out.println(a+" x "+b+" = "+ (a*b));
            System.out.println(a+" / "+b+" = "+ (a/b));
            System.out.println(a+" % "+b+" = "+ (a%b));
        }else{
            System.out.println("Napaka: deljenje z 0");
        }
    }

    static void nicli(int a, int b, int c){
        double dis = Math.pow(b, 2) - 4*a*c;
        if(dis < 0){
            System.out.println("Napaka: nicli enacbe ne obstajata");
        }else{
            System.out.printf("x1="+"%.2f, ", (-b + Math.sqrt(dis)) / (2 * a));
            System.out.printf("x2="+"%.2f",   (-b - Math.sqrt(dis)) / (2 * a));
            
        }
    }

    static void krog(double r, int d){
        // String format = "%." + d + "f";
        // if(r < 0){
        //     System.out.print("Napaka: negativen polmer");
        // }else if(d < 0){
        //     System.out.print("Napaka: negativen d");
        // }else{
        //     System.out.println("Obseg kroga s polmerom r="+String.format("%.2f",r)+" je "+String.format(format,2*Math.PI*r));
        //     System.out.println("Ploscina kroga s polmerom r="+String.format("%.2f",r)+" je "+String.format(format, (Math.PI*Math.pow(r,2))));
        // }

        String format = "%." + d + "f";
        String formatO = "Obseg kroga s polmerom r=%.2f je " + format + "%n";
        String formatP = "Ploscina kroga s polmerom r=%.2f je " + format;

        if(r < 0){
            System.out.print("Napaka: negativen polmer");
        }else if(d < 0){
            System.out.print("Napaka: negativen d");
        }else{
            System.out.printf(formatO, r, 2*Math.PI*r);
            System.out.printf(formatP, r, Math.PI*Math.pow(r,2));
        }
    }

    static String pretvoriSekunde(int sekunde){
        if(sekunde < 0){
            return "Število sekund ne more biti negativno";
        }
        
        int h = 0, m = 0, s;
        while(sekunde >= 60){
            m += 1;
            sekunde -= 60;

            if(m >= 60){
                h += 1;
                m = 0;
            }
        }

        s = sekunde;
        
        return String.format("%02d",h)+":"+
               String.format("%02d",m)+":"+
               String.format("%02d",s);

    }

    static void javaJavaJava(int n){
        if(n < 0){
            System.out.print("Napaka: negativen n");
        }else{

            String[] text =                 
            {"     J    a   v     v  a   ",
            "     J   a a   v   v  a a  ",
            "  J  J  aaaaa   V V  aaaaa ",
            "   JJ  a     a   V  a     a"};

            for(String x : text){
                for(int i = 0; i < n; i++){
                    System.out.print(x);
                }
                System.out.println();
            }
        }
    }

    static boolean jeFibonaccijevo(int n){
        int st1 = 0;
        int st2 = 1;
        int temp = 0;
        
        while(st2 < n && st1 < n){
            temp = st2;
            st2 = st1 + st2;
            st1 = temp;
            if(st1 == n || st2 == n){
                return true;
            }
        }
        return false;
    }

    static boolean jePrastevilo(int n){
        int count = 0;
        int i = 1;

        if(n < 0){
            return false;
        }

        while(i < n){
            if(n % i == 0){
                count++;
            }

            if(count > 2){
                return false;
            }
            i++;
        }
        return true;
    }

    static void izrisiZastavo(int n){
        for(int i = 0; i < (5 * n) - (5 * n - 3 * n); i++){
            if(i % 2 == 0){
                for(int a = 0; a < 5 * n - (n+1); a++){
                    if(a % 2 == 0){
                        System.out.print("*");
                    }else{
                        System.out.print(" ");
                    }
                }
            }else{
                for(int a = 0; a < 5 * n - (n+1); a++){
                    if(a % 2 == 0){
                        System.out.print(" ");
                    }else{
                        System.out.print("*");
                    }
                
                }
            }
            System.out.print(" ");
            
            for(int j = 0; j < (15 * n) - 4 * n + 1; j++){
                System.out.print("=");
            }
            System.out.println("");
        }

        for(int i = 0; i < (5 * n) - 3 * n; i++){
            for(int j = 0; j < 15 * n + 1; j++){
                System.out.print("=");
            }
            System.out.println("");
        }
    }

    static void vDesetisko(int n){
        int vs = 0;
        int temp = n;
        boolean neki = true;

        for(int i = 0; temp != 0; i++){
            if(temp%10 >= 8 || temp%10 < 0){
                
                System.out.print("Število "+n+" ni število v osmiškem sistemu (števka "+temp%10+")");
                neki = false;
                break;
            }
            vs += temp%10 * Math.pow(8, i);
            temp /= 10;
        }
        if(neki){
            System.out.print(n+"(8) = "+vs+"(10)");
        }
    }

    static String pretvoriVDesetisko(String n, int b){
        int vs = 0;
        int count = 0;
        
        if(b == 2){
            for(int i = n.length() - 1; i >= 0; i--){
                vs += (int)(n.charAt(i)) * Math.pow(2, count);
                count++;
                System.out.println(vs+" "+n.charAt(i) * Math.pow(2, 0));
            }
        }

        return "stfu";
    }
    
    public static void main(String[] args){
        System.out.println(pretvoriVDesetisko("101010", 2));
        // System.out.print('b' - 'a'); // cool shit
    } 
}