import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String output  = getUrlContents("https://adventofcode.com/2019/day/3/input");
        //String output = "R8,U5,L5,D3\nU7,R6,D4,L4";
        //String output = "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83";
        //String output = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7";

        Model model = new Model();

        String[] whires = output.split("\\r?\\n");

        String[] whire1 = whires[0].split(",");
        String[] whire2 = whires[1].split(",");

        List<Integer> steps1 = new ArrayList<Integer>();
        List<Integer> steps2 = new ArrayList<>();

        int[] initPoint = {0,0};

        List<int[]> testwhire = new ArrayList<>();
        testwhire.add(initPoint);

        List<int[]> testwhire2 = new ArrayList<>();
        testwhire2.add(initPoint);

        List<int[]> crossPoints = new ArrayList<>();

        for (String path_1 : whire1) {
            model.addPath(testwhire,path_1);
        }

        for (String path_2 : whire2){
            model.addPath(testwhire2,path_2);
        }

        int steptscount1 = -1;

        for(int[] point : testwhire){
            steptscount1++;
            int steptscount2 = -1;
            for (int[] point1 : testwhire2){
                steptscount2++;
                if(Arrays.equals(point1,point) && point[0] != 0 && point[1] != 0){
                    crossPoints.add(point);
                    steps1.add(steptscount1);
                    steps2.add(steptscount2);
                }
            }
        }

        int lessSteps = 1000000;

        for (int i = 0 ; steps1.size() > i ; i++){
            int totalSteps = steps1.get(i) + steps2.get(i);
            if (lessSteps > totalSteps)
                lessSteps = totalSteps;
        }

        System.out.println(lessSteps);

        int finalValue = 10000000;

        for (int[] point : crossPoints){
            int value = Math.abs(point[0]) + Math.abs(point[1]);
            if (value != 0 && value < finalValue)
                finalValue=value;
        }

        System.out.println(finalValue);

    }




    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            urlConnection.addRequestProperty("Cookie","session=53616c7465645f5f22d3405f1c9bbdc14a42a88f9fba1de5e83ae08203e5c0ff51c6be4317d1da6a5f15414a23652a4e");


            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}

