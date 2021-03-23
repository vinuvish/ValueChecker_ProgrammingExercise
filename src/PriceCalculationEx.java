import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceCalculationEx extends Utils {

    public static void main(String[] args) {
        String number = "26820212345";
        try {
            //Initialize the HashMap for hold the data as key value pair
            Map<Integer, Double> operatorAMap = new HashMap<>();
            Map<Integer, Double> operatorBMap = new HashMap<>();

            // calling getDataFromFile abstract method for get the date
            // from the data file as hashmap
            operatorAMap = getDataFromFile("src/data/operator_a.txt");
            operatorBMap = getDataFromFile("src/data/operator_b.txt");

            //Initialize the List for hold the match prefix
            List<Double> operatorAPrefixList = new ArrayList<Double>();// define list to add Opco A prefix matches
            List<Double> operatorBPrefixList = new ArrayList<Double>();// define list to add Opco B matches


            //Initialize the HashMap for hold the match prefix
            Map<Integer, Double> operatorAPrefixMatchMap = new HashMap<>();
            Map<Integer, Double> operatorBPrefixMatchMap = new HashMap<>();

            //calling getPrefixMatchList abstract method for get
            // matching prefix as list
            operatorAPrefixMatchMap = getPrefixMatchList(operatorAMap, number);
            operatorBPrefixMatchMap = getPrefixMatchList(operatorBMap, number);


            // condition for validating if matching prefix available or not
            if (!operatorAPrefixMatchMap.isEmpty()) {
                // print the price by calling getLongPrefixPrice abstract method
                System.out.println("You will have to pay " + getLongPrefixPrice(operatorAPrefixMatchMap) + "$ in Operator A ");
            }
            if (!operatorBPrefixMatchMap.isEmpty()) {
                // print the price by calling getLongPrefixPrice abstract method
                System.out.println("You will have to pay " + getLongPrefixPrice(operatorBPrefixMatchMap) + "$ in Operator B ");
            }
            if(operatorAPrefixMatchMap.isEmpty() || operatorBPrefixMatchMap.isEmpty()){
                System.out.println("No operator price available ");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
