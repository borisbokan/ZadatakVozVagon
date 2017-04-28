package zadaci;

/**
 * Created by borcha on 22.04.17..
 */
public class Poruka {

        public   static void text(String _sadrzajPoruke){
            System.out.println(_sadrzajPoruke);
        }


        public  static void linija40(){
            System.out.println("----------------------------------------");
        }


        public  static void noviRed(){
            System.out.println();
        }


    /**
     * Nov red + isprekidana linija + nov red
     */
    public  static void nrlinr(){
            System.out.println("\n----------------------------------------\n");
        }

    /**
     * 1. red ---------------------------------------
     * 2. red          Naslov text
     * 3. red ---------------------------------------
     * @param _naslov
     */
    public static void naslov(String _naslov){
            System.out.println("------------------------------------------");
            System.out.println(_naslov);
            System.out.println("------------------------------------------");
        }


}
