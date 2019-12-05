import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String output  = getUrlContents("https://adventofcode.com/2019/day/2/input");
        //String output = "1,9,10,3,2,3,11,0,99,30,40,50";
        output = output.replaceAll("\n","");

        List<String> list = Arrays.asList(output.split(","));
        List<Integer> listV = new ArrayList<Integer>();

        for(String s : list)
            listV.add(Integer.valueOf(s));

        int finalNoun = 0;
        int finalVerb = 0;

        for (int j=99 ; j > 0 ; j--) {
            boolean sussces =false;
            for (int k = 0; k < 99; k++) {
                listV.clear();
                for(String s : list)
                    listV.add(Integer.valueOf(s));
                int result = Intcode(j, k, listV);
                if (result > 19690720)
                    break;
                if (result == 19690720) {
                    finalNoun = j;
                    finalVerb = k;
                    sussces = true;
                    break;
                }

            }
            if(sussces)
                break;
        }

        System.out.println("Respuesta:" + (100*finalNoun + finalVerb));
    }

    private static  int Intcode(int noun, int verb, List<Integer> listValues){

        List<Integer> lisIn = new ArrayList<Integer>();
        lisIn = listValues;

        listValues.set(1,noun);
        listValues.set(2,verb);

        for(int i=0; i < listValues.size()/4; i++){

            int indexInstruction = i*4;
            int instruction = listValues.get(i*4);

            int newValue = instruction;

            if(instruction < 99){
                int value1 = listValues.get(listValues.get(indexInstruction + 1));
                int value2 = listValues.get(listValues.get(indexInstruction + 2));


                if(instruction == 1)
                    newValue = value1 + value2;

                if(instruction == 2)
                    newValue = value1 * value2;

                listValues.set(listValues.get(indexInstruction+3),newValue);

            }
        }

        return listValues.get(0);
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

