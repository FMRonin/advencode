import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //String output  = getUrlContents("https://adventofcode.com/2019/day/3/input");
        String output = "R8,U5,L5,D3\nU7,R6,D4,L4";

        Model model = new Model();

        String[] whires = output.split("\\r?\\n");

        String[] whire1 = whires[0].split(",");
        String[] whire2 = whires[1].split(",");

        int[] lastPoint_w1 = {0,0};
        int[] lastPoint_w2 = {0,0};

        int contador = 0;

        for (String path_1 : whire1) {
            String direction1 = path_1.substring(0,1);
            int step1 = Integer.parseInt(path_1.substring(1));
            for (String path_2 : whire2){
                contador += model.isCross(lastPoint_w1,path_1,lastPoint_w2,path_2);
                model.updatePoint(lastPoint_w2,path_2);
            }
            lastPoint_w2[0]=0;lastPoint_w2[1]=0;
            model.updatePoint(lastPoint_w1,path_1);
        }

        System.out.println(contador);

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

