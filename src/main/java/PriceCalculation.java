import java.util.HashMap;
import java.util.Map;

public class PriceCalculation extends Utils {
    static final String PATH = "src/main/java/data/";

    public static void main(String[] args) throws PrefixException {
        String number = "4673212345";
        calculate(number);
    }
    public static void calculate(String number) throws PrefixException {
        try {
            //Initialize the HashMap for hold the data as key value pair
            Map<Integer, Double> operatorAMap = new HashMap<>();
            Map<Integer, Double> operatorBMap = new HashMap<>();

            // calling getDataFromFile abstract method for get the date
            // from the data file as hashmap
            operatorAMap = getDataFromFile(PATH + "operator_a.txt");
            operatorBMap = getDataFromFile(PATH + "operator_b.txt");

            //Initialize the HashMap for hold the match prefix
            Map<Integer, Double> operatorAPrefixMatchMap = new HashMap<>();
            Map<Integer, Double> operatorBPrefixMatchMap = new HashMap<>();

            //calling getPrefixMatchList abstract method for get
            // matching prefix as list
            operatorAPrefixMatchMap = getPrefixMatchMap(operatorAMap, number);
            operatorBPrefixMatchMap = getPrefixMatchMap(operatorBMap, number);

            // condition for validating if matching prefix available or not
            if (!operatorAPrefixMatchMap.isEmpty()) {
                // print the price by calling getLongPrefixPrice abstract method
                System.out.println("You will have to pay " + getLongPrefixPrice(operatorAPrefixMatchMap) + "$ in Operator A ");
            }
            if (!operatorBPrefixMatchMap.isEmpty()) {
                // print the price by calling getLongPrefixPrices abstract method
                System.out.println("You will have to pay " + getLongPrefixPrice(operatorBPrefixMatchMap) + "$ in Operator B ");
            }
            if (operatorAPrefixMatchMap.isEmpty() && operatorBPrefixMatchMap.isEmpty()) {
                System.out.println("No operator price available ");
            }
        } catch (Exception e) {
            throw new PrefixException("Prefix Exception due to : "+e.getMessage());
        }
    }
}
