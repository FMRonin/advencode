import java.util.List;

public class Model {


    public int[] isCross(int[] point1, String path1, int[] point2, String path2){
        String direction1 = path1.substring(0,1);
        int step1 = Integer.parseInt(path1.substring(1));

        String direction2 = path2.substring(0,1);
        int step2 = Integer.parseInt(path2.substring(1));

        int[] crossPoints = {0,0};

        if (("U".equals(direction1)||"D".equals(direction1)) && ("R".equals(direction2)||"L".equals(direction2))){
            //wire1 vertical
            if ("R".equals(direction2))
                crossPoints[0] = Comparation(point2[0],point1[0],point1[0]+step1);
            if ("L".equals(direction2))
                crossPoints[0] = Comparation(point2[0],point1[0],point1[0]-step1);

            if ("U".equals(direction1))
                crossPoints[1] = Comparation(point1[1],point2[1],point2[1]+step2);
            if ("D".equals(direction1))
                crossPoints[1]= Comparation(point1[1],point2[1],point2[1]-step2);
        }

        if (("U".equals(direction2)||"D".equals(direction2)) && ("R".equals(direction1)||"L".equals(direction1))){
            //wire1 horizaontal
            if ("R".equals(direction1))
                crossPoints[0] = Comparation(point1[0],point2[0],point2[0]+step2);
            if ("L".equals(direction1))
                crossPoints[0] = Comparation(point1[0],point2[0],point2[0]-step2);

            if ("U".equals(direction2))
                crossPoints[1] = Comparation(point2[1],point1[1],point1[1]+step1);
            if ("D".equals(direction2))
                crossPoints[1] = Comparation(point2[1],point1[1],point1[1]-step1);
        }
        return crossPoints;
    }

    public int Comparation(int position, int init, int finish){
        if (init > finish && init > position && position > finish)
            return position;
        if (init < finish && finish > position && position > init)
            return position;
        return 0;
    }

    public void updatePoint(int[] poin, String opt){
        String direction = opt.split("[0-9]")[0];
        int steps = Integer.parseInt(opt.split("[A-Z]")[1]);
        for (int i= 0 ; steps > i ; i++){
            if ("U".equals(direction))
                poin[0]=poin[0]+steps;
            if ("D".equals(direction))
                poin[0]=poin[0]-steps;
            if ("R".equals(direction))
                poin[1]=poin[1]+steps;
            if ("L".equals(direction))
                poin[1]=poin[1]-steps;
        }
    }


    public void addPath(List<int[]> wire, String opt){
        String direction = opt.split("[0-9]")[0];
        int steps = Integer.parseInt(opt.split("[A-Z]")[1]);
        int[] start = wire.get(wire.size()-1);
        for (int i = 0 ; steps > i ; i++) {
            if ("U".equals(direction)) {
                int[] resut = {start[0] + 1, start[1]};
                wire.add(resut);
                start = resut;
            }
            if ("D".equals(direction)) {
                int[] resut = {start[0] - 1, start[1]};
                wire.add(resut);
                start = resut;
            }
            if ("R".equals(direction)) {
                int[] resut = {start[0], start[1] + 1};
                wire.add(resut);
                start = resut;
            }
            if ("L".equals(direction)) {
                int[] resut = {start[0], start[1] - 1};
                wire.add(resut);
                start = resut;
            }
        }
    }
}
