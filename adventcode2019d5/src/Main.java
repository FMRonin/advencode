import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String puzzle  = getUrlContents("https://adventofcode.com/2019/day/5/input");
        //String puzzle = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,\n"
        //String puzzle = "1101,100,-1,4,0";
        //String puzzle = "3,0,4,0,99";
        puzzle = puzzle.replaceAll("\n","");

        List<String> list = Arrays.asList(puzzle.split(","));
        List<Integer> listV = new ArrayList<Integer>();

        for(String s : list)
            listV.add(Integer.valueOf(s));

        Intcode(listV,5);
    }

    private static  int Intcode(List<Integer> listValues,int input){

        List<Integer> lisIn = new ArrayList<Integer>();
        lisIn = listValues;

        int i = 0;

        while(i < lisIn.size()){

            int indexInstruction = i;
            int apuntador = lisIn.get(i);

            int instruction = apuntador%100;

            System.out.println("Index:" + i + ",Instrucció:" + instruction);

            if(instruction < 99){

            int modo1 = (apuntador/100)%10;
            int modo2 = (apuntador/1000)%10;
            int modo3 = (apuntador/10000)%10;


                if (instruction == 1 || instruction == 2){
                    int value1 = 0;
                    int value2 = 0;

                    if (modo1 == 1){
                        value1 = lisIn.get(indexInstruction+1);
                    }else if (modo1 == 0){
                        value1 = lisIn.get(lisIn.get(indexInstruction+1));
                    }

                    if (modo2 == 1){
                        value2 = lisIn.get(indexInstruction+2);
                    }else if (modo2 == 0){
                        value2 = lisIn.get(lisIn.get(indexInstruction+2));
                    }

                    int result = 0;

                    if (instruction == 1)
                        result = value1 + value2;

                    if (instruction == 2)
                        result = value1 * value2;

                    if (modo3 == 1){
                        lisIn.set(indexInstruction+3,result);
                    }else if (modo3 == 0){
                        lisIn.set(lisIn.get(indexInstruction+3),result);
                    }

                    i = i + 4;
                }else if (instruction == 3){
                    lisIn.set(lisIn.get(indexInstruction+1),input);
                    i = i + 2;
                }else if (instruction == 4){
                    if (modo1 == 1)
                        System.out.println(lisIn.get(indexInstruction+1));
                    else if (modo1 == 0)
                        System.out.println(lisIn.get(lisIn.get(indexInstruction+1)));
                    i = i + 2;
                }else if (instruction == 5 || instruction == 6){
                    int value1 = 0;
                    int value2 = 0;
                    if (modo1 == 1){
                        value1 = lisIn.get(indexInstruction+1);
                    }else if (modo1 == 0){
                        value1 = lisIn.get(lisIn.get(indexInstruction+1));
                    }
                    if (modo2 == 1){
                        value2 = lisIn.get(indexInstruction+2);
                    }else if (modo2 == 0){
                        value2 = lisIn.get(lisIn.get(indexInstruction+2));
                    }
                    if (value1==0 && instruction==6)
                        i = value2;
                    else if (value1!=0 && instruction==5)
                        i = value2;
                    else
                        i = i + 3;
                }else if (instruction == 7 || instruction == 8){
                    int value1 = 0;
                    int value2 = 0;
                    if (modo1 == 1){
                        value1 = lisIn.get(indexInstruction+1);
                    }else if (modo1 == 0){
                        value1 = lisIn.get(lisIn.get(indexInstruction+1));
                    }
                    if (modo2 == 1){
                        value2 = lisIn.get(indexInstruction+2);
                    }else if (modo2 == 0){
                        value2 = lisIn.get(lisIn.get(indexInstruction+2));
                    }

                    if (instruction == 7)
                        if (value1 < value2){
                            if (modo3 == 1){
                                lisIn.set(indexInstruction+3,1);
                            }else if (modo3 == 0){
                                lisIn.set(lisIn.get(indexInstruction+3),1);
                            }
                        }else {
                            if (modo3 == 1){
                                lisIn.set(indexInstruction+3,0);
                            }else if (modo3 == 0){
                                lisIn.set(lisIn.get(indexInstruction+3),0);
                            }
                        }
                    if (instruction == 8)
                        if (value1 == value2){
                            if (modo3 == 1){
                                lisIn.set(indexInstruction+3,1);
                            }else if (modo3 == 0){
                                lisIn.set(lisIn.get(indexInstruction+3),1);
                            }
                        }else {
                            if (modo3 == 1){
                                lisIn.set(indexInstruction+3,0);
                            }else if (modo3 == 0){
                                lisIn.set(lisIn.get(indexInstruction+3),0);
                            }
                        }

                    i = i + 4;
                }
            }else break;
        }

        return lisIn.get(0);
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
