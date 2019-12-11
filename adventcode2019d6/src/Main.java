import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("input.txt"));
        //Scanner input = new Scanner(new File("inputTest.txt"));
        //String input = "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L";
        //String input = "COM)B\nB)C\nB)D";


        //String[] puzzle = input.split("\\n");

        List<Planeta> planetas = new ArrayList<>();

        while (input.hasNext()){

            String orbita = input.nextLine();

            String nombrePlaneta = orbita.split("\\)")[0];
            String nombreOrbita = orbita.split("\\)")[1];

            if (planetas.size() == 0){
                Planeta planeta = new Planeta(nombrePlaneta);
                planeta.setOrbit(new Planeta(nombreOrbita));
                planetas.add(planeta);
            }

            Boolean finded = false;

            for (int i = 0 ; i < planetas.size() ; i++){
                System.out.println(orbita);
                if (nombrePlaneta.equals(planetas.get(i).getNombre())){
                    planetas.get(i).setOrbit(new Planeta(nombreOrbita));
                    finded = true;
                }else if (planetas.get(i).getOrbit(nombrePlaneta) != null){
                    planetas.get(i).getOrbit(nombrePlaneta).setOrbit(new Planeta(nombreOrbita));
                    finded = true;
                }else if (nombreOrbita.equals(planetas.get(i).getNombre())){
                    Planeta addPlanet = new Planeta(nombrePlaneta);
                    addPlanet.setOrbit(planetas.get(i));
                    planetas.add(addPlanet);
                    finded = true;
                }
            }
            if (!finded) {
                Planeta addPlanet = new Planeta(nombrePlaneta);
                addPlanet.setOrbit(new Planeta(nombreOrbita));
                planetas.add(addPlanet);
            }
        }

        for (int i=0 ; i < planetas.size() ; i++){
            for (int j=0 ; j < planetas.size() ; j++){
                if (planetas.get(i).getOrbit(planetas.get(j).getNombre()) != null){
                    planetas.get(i).setOrbit(planetas.get(j));
                    planetas.remove(planetas.get(j));
                }
            }
        }

        System.out.println(planetas.toString());
    }
}
