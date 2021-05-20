import java.util.HashMap;
import java.util.Scanner;

public class numberTranslatorv2{
    
    public static void main(String[] args){
        
        HashMap<String,Integer> dic = numberTranslator.getDic("numbers.txt");
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();

        String[] numbers = numberTranslator.formatNumbers(input, dic).replaceAll("[\\D]", " ").split("\\s+");

        int tempNum = Integer.parseInt(numbers[0]);
        int res = 0;

        for(int i=0; i < numbers.length; i++){
            if(i == numbers.length-1){
                res += tempNum;
                continue;
            }
            if(Integer.parseInt(numbers[i])<Integer.parseInt(numbers[i+1])){
                tempNum *= Integer.parseInt(numbers[i+1]);
                continue;
            }
            res += tempNum;
            tempNum = Integer.parseInt(numbers[i+1]);

        }
        System.out.println(res);
        reader.close();
    }
}
