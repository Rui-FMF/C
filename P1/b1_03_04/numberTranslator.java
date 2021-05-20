import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class numberTranslator {
    
    public static void main(String[] args) {

        HashMap<String,Integer> dic = getDic("numbers.txt");
        
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        String output = formatNumbers(input,dic);
        
        reader.close();
        System.out.println(output);
    }

    public static HashMap<String, Integer> getDic(String file){
        HashMap<String, Integer> dic = new HashMap<String, Integer>();

        try {

            Scanner sc = new Scanner(new File(file));

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] elements = line.split("\\W+");
                dic.put(elements[1], Integer.parseInt(elements[0]));
            }
            sc.close();


        } catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", file);
			e.printStackTrace();
			return null;
        }
        return dic;
    }

    public static String formatNumbers(String input, HashMap<String, Integer> dic){
        String[] words = input.split("\\s+|-");

        String output = "";

        for(String word : words){
            if(dic.containsKey(word)){
                output += Integer.toString(dic.get(word)) + " ";
            } else{
                output += word+" ";
            }
        }
        return output;
    }
}