public class Model {


    public int isCross(int[] point1, String path1, int[] point2, String path2){
        String direction1 = path1.substring(0,1);
        int step1 = Integer.parseInt(path1.substring(1));

        String direction2 = path2.substring(0,1);
        int step2 = Integer.parseInt(path2.substring(1));

        int contador = 0;

        if (("U".equals(direction1)||"D".equals(direction1)) && ("R".equals(direction2)||"L".equals(direction1))){
            //wire1 vertical
            if ("R".equals(direction2))
                contador += Comparation(point1[0],point2[0],point2[0]+step2);
            if ("L".equals(direction2))
                contador += Comparation(point1[0],point2[0],point2[0]-step2);

            if ("U".equals(direction2))
                contador += Comparation(point2[1],point1[1],point1[1]+step1);
            if ("D".equals(direction2))
                contador += Comparation(point2[1],point1[1],point1[1]-step1);
        }

        if (("U".equals(direction2)||"D".equals(direction2)) && ("R".equals(direction1)||"L".equals(direction1))){
            //wire1 horizaontal
            if ("R".equals(direction1))
                contador += Comparation(point1[1],point2[1],point2[1]+step2);
            if ("L".equals(direction1))
                Comparation(point1[1],point2[1],point2[1]-step2);

            if ("U".equals(direction2))
                contador += Comparation(point2[0],point1[0],point1[0]+step1);
            if ("D".equals(direction2))
                contador += Comparation(point2[0],point1[0],point1[0]-step1);
        }
        return contador;
    }

    public int Comparation(int position, int init, int finish){
        if (init > finish && init > position && position > finish)
            return position;
        if (init < finish && finish > position && position > init)
            return position;
        return 0;
    }

    public void function(String opt1, String opt2){
        String direction1 = "[0-9]".split(opt1)[0];
        int steps1 = Integer.parseInt(opt1.split("[A-Z]")[1]);
        String direction2 = "[0-9]".split(opt2)[0];
        int steps2 = Integer.parseInt("[A-Z]".split(opt2)[1]);
    }

    public void updatePoint(int[] poin, String opt){
        String direction = opt.split("[0-9]")[0];
        int steps = Integer.parseInt(opt.split("[A-Z]")[1]);
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
