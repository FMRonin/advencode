import java.util.ArrayList;
import java.util.List;

public
class Planeta {

    private String nombre;
    private List<Planeta> orbit;

    public
    Planeta(String nombre) {
        this.nombre = nombre;
        orbit = new ArrayList<Planeta>();
    }

    public
    String getNombre() {
        return nombre;
    }

    public
    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public
    List<Planeta> getOrbits() {
        return orbit;
    }

    public
    void addOrbit(Planeta orbit) {
        this.orbit.add(orbit);
    }

    public
    void setOrbit(Planeta planeta){
        boolean update = false;
        for (int i=0 ; i < orbit.size() ; i++) {
            if (orbit.get(i).getNombre().equals(planeta.getNombre())) {
                orbit.set(i,planeta);
                update = true;
                break;
            }
        }
        if (!update)
            orbit.add(planeta);
    }

    public
    Planeta getOrbit(String nombrePlaneta){
        for (Planeta plantet : orbit) {
            if (plantet.getNombre().equals(nombrePlaneta)) {
                return plantet;
            }else if(plantet.getOrbit(nombrePlaneta) != null)
                return plantet.getOrbit(nombrePlaneta);
        }
        return null;
    }

    public
    String toString(){
        String result = "-" + nombre;
        for (Planeta planeta : orbit){
            result = result + planeta.toString() + "\t";
        }
        return result + "\n";
    }
}
