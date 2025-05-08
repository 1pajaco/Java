public class DN02 {
    public static void main(String[] args){
        if(args.length != 4){
            System.out.print("Vnesi 4 stevila prosim!");
            System.exit(0);
        }

        int 
        s = Integer.parseInt(args[0]),
        v = Integer.parseInt(args[1]), 
        x = Integer.parseInt(args[2]),
        y = Integer.parseInt(args[3]);

        String crta = "", prostor = "";

        for(int i = 0; i < (s-1)*x + 1; i++){
            crta += "*";
            
            if(i % (s-1) == 0){
                prostor += "*";
            }else{
                prostor += " ";
            }
        }
    
        for(int vrst = 0; vrst < (v-1)*y + 1; vrst++){
            if(vrst % (v-1) == 0){
                System.out.println(crta);
            }else{
                System.out.println(prostor);
            }
        }

    }
}
