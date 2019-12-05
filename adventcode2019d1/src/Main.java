import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String output  = getUrlContents("https://adventofcode.com/2019/day/1/input");

        List<String> list = Arrays.asList(output.split("\\r?\\n"));
        //List<String> list = new ArrayList<String>();list.add("100756");
        int totalFuel = 0;

        for(String s : list) {
            int fuel = Integer.valueOf(s);
            fuel = fuel/3 - 2;
            int subFuel = fuel/3 - 2;
            while (subFuel > 0){
                fuel = fuel + subFuel;
                subFuel = subFuel/3 - 2;
            }
            totalFuel = totalFuel + fuel;
        }

        System.out.println(totalFuel);

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

