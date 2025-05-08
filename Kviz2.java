import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Kviz2 {

    static int vsotaStevk(String str) {
        int vs = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                vs += Character.getNumericValue(str.charAt(i));
            }
        }
        return vs;
    }

    static boolean preveriRep(String a, String b) {
        if (a.length() <= b.length()) {
            return (b.substring(b.length() - a.length(), b.length()).toUpperCase()).equals(a.toUpperCase());
        }

        return (a.substring(a.length() - b.length(), a.length()).toUpperCase()).equals(b.toUpperCase());
    }

    static int[] range(int a, int b, int c) {
        java.util.ArrayList<Integer> tab = new java.util.ArrayList<Integer>();

        for (int i = 0; i < b - c; i++) {
            if (b > a + i * c) {
                tab.add(a + i * c);
            }
        }

        int[] crigne = tab.stream().mapToInt(i -> i).toArray();

        return crigne;
    }

    static void rotiraj(int[] tabela, int k) {
        java.util.ArrayList<Integer> tab = new java.util.ArrayList<Integer>();

        if (k > tabela.length) {
            k = tabela.length - 1;
        }

        for (int i = k; i < tabela.length; i++) {
            tab.add(tabela[i]);
        }

        for (int i = 0; i < k; i++) {
            tab.add(tabela[i]);
        }

        int[] tabArray = tab.stream().mapToInt(i -> i).toArray();
        System.arraycopy(tabArray, 0, tabela, 0, tab.size());

    }

    static int[] duplikati(int[] tabela) {
        java.util.ArrayList<Integer> tab = new java.util.ArrayList<Integer>();

        for (int vr : tabela) {
            if (!tab.contains(vr)) {
                tab.add(vr);
            }
        }

        return tab.stream().mapToInt(i -> i).toArray();

        // return Arrays.stream(tabela).distinct().toArray();
    }

    static String binarnoSestej(String s, String b) {
        int sLen = s.length() - 1, bLen = b.length() - 1;
        int carry = 0, vs = 0;
        StringBuilder rez = new StringBuilder();

        while (sLen >= 0 || bLen >= 0) {
            if (sLen >= 0) {
                vs += Character.getNumericValue(s.charAt(sLen));
            }
            if (bLen >= 0) {
                vs += Character.getNumericValue(b.charAt(bLen));
            }
            if (vs == 1 || vs == 0) {
                rez.append(vs);
                carry = 0;
            } else if (vs == 2) {
                rez.append("0");
                carry = 1;
            } else {
                rez.append("1");
                carry = 1;
            }

            sLen--;
            bLen--;
            vs = carry;
        }

        if (carry == 1) {
            rez.append("1");
        }

        return rez.reverse().toString();

    }

    static String prevod(String niz) {
        java.util.List<Character> samoglasniki = java.util.Arrays.asList('a', 'e', 'i', 'o', 'u');
        StringBuilder sb = new StringBuilder();
        int count = 0, countPA = 0;

        for (int i = 0; i < niz.length(); i++) {
            if (samoglasniki.contains(niz.charAt(i))) {
                count++;
                if (i + 2 < niz.length() && niz.charAt(i + 1) == 'p' && niz.charAt(i + 2) == 'a') {
                    countPA++;
                }
            }
        }

        if (count - countPA == countPA) {
            String[] test = niz.split("pa");
            java.util.Arrays.stream(test).forEach(x -> sb.append(x));
        } else {
            niz.chars().mapToObj(i -> (char) i)
                    .forEach(i -> sb.append(samoglasniki.contains(i) ? i + "pa" : i));
        }
        return sb.toString();

    }

    static int vsotaSkupnihCifer(int a, int b) {
        java.util.Set<Character> A = Integer.toString(a)
                .chars()
                .mapToObj(i -> (char) i)
                .collect(java.util.stream.Collectors.toSet());

        java.util.Set<Character> B = Integer.toString(b)
                .chars()
                .mapToObj(i -> (char) i)
                .collect(java.util.stream.Collectors.toSet());

        A.retainAll(B);

        int vs = A.stream()
                .mapToInt(i -> Character.getNumericValue(i))
                .sum();

        return vs;
    }

    static String prepleti(String niz1, String niz2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, count = 0;

        while (i < niz1.length() || j < niz2.length()) {
            if (count % 2 == 0) {
                if (i < niz1.length()) {
                    sb.append(niz1.charAt(i++));
                } else {
                    sb.append(" ");
                }
            } else {
                if (j < niz2.length()) {
                    sb.append(niz2.charAt(j++));
                } else {
                    sb.append(" ");
                }
            }
            count++;
        }

        return sb.toString();
    }

    static void odpleti(String niz) {
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        java.util.List<String> list = java.util.Arrays.asList(niz.split(""));

        for (int i = 0; i < list.size(); i++) {
            if(i % 2 == 0){
                s1.append(list.get(i));
            }else{
                s2.append(list.get(i));
            }
        }

        System.out.println(s1);
        System.out.println(s2);
    }

    public static void main(String[] args) {
        // System.out.println(vsotaStevk("1abc2"));
        // // System.out.println(preveriRep("DAN","Dan na dan"));
        // for(int x : range(0, 10, 2)){
        // System.out.println(x);
        // }
        // int[] tab = {1,5,8,56,3,9,1,43,1,2,56,12,1,3};
        // duplikati(tab);

        // for(int x : tab){
        // System.out.print(x+",");
        // }
        // System.out.println(binarnoSestej("10011010010", "1000011100001"))

        // String KMS = "Danes je lep dan";
        // int i = 0;
        // System.out.println(KMS.substring(i,i+5));
        // i += 6;
        // System.out.println(KMS.substring(i,i+2));

        // System.out.println(prevod("Napaka"));
        // System.out.println(prevod("Popamlapad pripahapajapa"));
        // System.out.println(prevod("Kappa"));

        // System.out.print(vsotaSkupnihCifer(1321, 30));

        // System.out.println(prepleti("pomlad", "JESEN"));
        // System.out.println(prepleti("december", "maj"));
        odpleti("dmeacje m b e r ");
    }
}
