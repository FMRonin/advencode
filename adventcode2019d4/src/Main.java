import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        int initRange = 183564;
        //int initRange = 111122;
        int finalRange = 657474;

        int contador = 0;

        for (int i = initRange; i < finalRange; i++){

            int testPass = i;

            int mod = 100000;

            List<Integer> repeats = new ArrayList<Integer>();

            boolean success = false;

            boolean doble = false;

            int twice = 0;

            boolean twiceValidation = false;

            while (mod > 1){
                if (testPass/mod <= testPass%mod/(mod/10)){
                    if (testPass/mod == testPass%mod/(mod/10)){
                        doble = true;
                        twice++;
                    }else{
                        repeats.add(twice);
                        twice = 0;
                    }
                    testPass = testPass%mod;
                    mod = mod/10;
                    if (mod < 10)
                        success = true;
                }else {
                    mod = 0;
                }
            }

            repeats.add(twice);

            for (int once : repeats)
                if (once == 1)
                    twiceValidation = true;

            if (success && doble && twiceValidation){
                System.out.println(i);
                contador++;
            }

        }

        System.out.println(contador);
    }
}
