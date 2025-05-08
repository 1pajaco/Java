public class DN04 {
    public static void main(String[] args) {
        String niz = args[0];

        for(int i = 0; i < niz.length(); i += 8){
            System.out.print((char)Integer.parseInt(niz.substring(i, i+8), 2));
        }
    }
}
