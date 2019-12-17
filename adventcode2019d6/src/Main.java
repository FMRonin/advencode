import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.sun.tools.doclint.Entity.ge;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("input.txt"));
        //Scanner input = new Scanner(new File("inputTest.txt"));

        HashMap<String,List<String>> planetas = new HashMap<>();

        while (input.hasNext()) {

            String orbita = input.nextLine();

            String planet = orbita.split("\\)")[0];
            String orbital = orbita.split("\\)")[1];

            if (planetas.get(planet) == null){
                List<String> orbitales = new ArrayList<>();
                planetas.put(planet,orbitales);
            }
            planetas.get(planet).add(orbital);

        }

        int minA = 100000;
        int minB = 100000;

        String nodo = "";

        for (Map.Entry<String,List<String>> entry : planetas.entrySet()){

            if (minA > getNumberOrbitsToPlanet(entry.getKey(),"YOU",planetas) && getNumberOrbitsToPlanet(entry.getKey(),"YOU",planetas) != 0)
            {
                if (minB > getNumberOrbitsToPlanet(entry.getKey(),"SAN",planetas) && getNumberOrbitsToPlanet(entry.getKey(),"SAN",planetas) != 0) {
                    minA = getNumberOrbitsToPlanet(entry.getKey(), "YOU", planetas);
                    minB = getNumberOrbitsToPlanet(entry.getKey(), "SAN", planetas);
                    nodo = entry.getKey();
                }
            }
        }

        int result = minA + minB - 4;

        System.out.println("minA:" + minA + ", minB:" + minB + ", nodo:" + nodo);
        System.out.println("Resultado:" + result);

    }


    private static int getNumberOrbitsToPlanet(String planetCenter, String planetFinded, HashMap<String, List<String>> planetas){

        List<String> orbitales = planetas.get(planetCenter);
        int numeroOrbitales = 0;

        if (planetCenter.equals(planetFinded)){
            return 1;
        }else if (orbitales != null) {
            for (String orbtal : orbitales) {
               numeroOrbitales = getNumberOrbitsToPlanet(orbtal,planetFinded,planetas);
               if (numeroOrbitales > 0) {
                   numeroOrbitales++;
                   break;
               }
            }
        }
        return numeroOrbitales;

    }

    private static int getNumberOrbits(String planet, HashMap<String, List<String>> planetas){

        List<String> orbitales = planetas.get(planet);
        int numeroOrbitales = 0;

        if (orbitales != null)
            for (String orbtal : orbitales)
                numeroOrbitales = 1 + numeroOrbitales +  getNumberOrbits(orbtal, planetas);

        return numeroOrbitales;
    }


}
