package zbirke;

public class Seznam {
    
    // tabela nizov (tip String[]) za hranjenje elementov seznama (vsak element seznama je niz znakov)
    static String[] elementiSeznama;

    // celoštevilska spremenljivka, ki hrani trenutno število elementov v seznamu.
    static int trenutnoStElem = 0;

    /**
     * iz elementiSeznam naredi seznam dolžine n
     * @param n pozitivno celo število, ki določa kapaciteto seznama (največje število elementov, ki jih lahko shranimo v seznam)
     * @return Če je nov seznam uspešno ustvarjen, metoda vrne true. Če seznam že obstaja, metoda ne naredi novega seznama, ampak vrne false.
     */
    public static boolean narediSeznam(int n){
        if(elementiSeznama != null){
            return false;
        }

        elementiSeznama = new String[n];
        return true;
    }


    /**
     * Na konec seznama doda podani element
     * @param element Element za dodajanje na konec
     * @return  Če je element uspešno dodan, metoda vrne true. Če seznam ne obstaja ali če je seznam že poln, metoda elementa ne more dodati in vrne false.
     */
    public static boolean dodajNaKonecSeznama(String element){
        if(elementiSeznama == null || elementiSeznama.length <= trenutnoStElem){
            return false;
        }
        
        elementiSeznama[trenutnoStElem] = element;
        trenutnoStElem++;
        return true;
    }
    
    /**
     * Na zaslon izpiše vse elemente seznama
     * 
     *  
     */
    public static void izpisiSeznam(){
        if(elementiSeznama == null){
            System.out.println("NAPAKA: Seznam ne obstaja.");
            return;
        }
        if(trenutnoStElem == 0){
            System.out.println("Seznam je prazen (nima elementov).");
            return;
        }
        for (int i = 0; i < elementiSeznama.length; i++) {
            if(elementiSeznama[i] != null){
                System.out.println((i+1) + ": " + elementiSeznama[i]);
            }
        }
    }

    /**
     * Metoda s katero lahko iz seznama odstranimo element na podanem mestu
     * @param mesto
     * @return
     */
    public static String odstraniIzSeznama(int mesto){
        if(trenutnoStElem < mesto){
            return null;
        }

        String temp = elementiSeznama[mesto - 1];

        for (int i = mesto - 1; i < elementiSeznama.length - 1; i++) {
            elementiSeznama[i] = elementiSeznama[i + 1];
        }

        elementiSeznama[trenutnoStElem] = null;

        trenutnoStElem--;
        return temp;
    }

    /**
     * Metoda ki na podano mesto v seznamu doda podani element.
     * @param element
     * @param mesto
     * @return
     */
    public static boolean dodajVSeznam(String element, int mesto){
        if(elementiSeznama == null || trenutnoStElem >= elementiSeznama.length){
            return false;
        }
        
        if(trenutnoStElem < mesto){
            elementiSeznama[trenutnoStElem] = element;
        }else{
            for(int i = trenutnoStElem; i > mesto - 1; i--){
                elementiSeznama[i] = elementiSeznama[i - 1];
            }

            elementiSeznama[mesto - 1] = element;
        }

        trenutnoStElem++;
        return true;
    }

    /**
     * Metoda ki vrne trenutno število elementov v seznamu.
     * @return
     */
    public static int dolzinaSeznama(){
        if(elementiSeznama == null){
            return -1;
        }
        
        return trenutnoStElem;
    }

    /**
     * Metoda ki uniči cel seznam
     * @return
     */
    public static boolean uniciSeznam(){
        if(elementiSeznama == null){
            return false;
        }

        elementiSeznama = null;
        trenutnoStElem = 0;
        return true;
    }
}
