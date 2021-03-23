import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Utils {
    public static Map<Integer, Double> getDataFromFile(String fileName) {
        //Initialize the map for holding the prefix as key and price as value
        Map<Integer, Double> operatorMap = new HashMap<>();
        try {
            //Read the operator file
            BufferedReader operator_reader = new BufferedReader(new FileReader(fileName));
            String line;
            //Loop the file lines until not null
            while ((line = operator_reader.readLine()) != null) {
                //Get the values separated by comma and put it to string array
                String[] parts = line.split(",", 2);
                //validate if array have 2 element
                if (parts.length >= 2) {
                    int key = Integer.parseInt(parts[0]);
                    double value = Double.parseDouble(parts[1]);
                    //Store the key value pair in to map
                    operatorMap.put(key, value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            // Close the file stream
            operator_reader.close();
        } catch (IOException e) {
            System.out.println("ERROR : " + e);
        }
        return operatorMap;
    }

    public static Map<Integer, Double> getPrefixMatchMap(Map<Integer, Double> operatorMap, String matchPrefix) throws PrefixException {
        // define list to add operator prefix matches
        Map<Integer, Double> operatorPrefixMatchMap = new HashMap<>();
        try {
            // Here is the business logic. it check against prefix whether
            // given number starts with the prefix or not
            for (Integer prefix : operatorMap.keySet()) {
                if (matchPrefix.startsWith(prefix.toString())) {
                    operatorPrefixMatchMap.put(prefix, operatorMap.get(prefix));
                }
            }
        } catch (Exception e) {
            throw new PrefixException("Prefix Exception due to : " + e.getMessage());
        }
        return operatorPrefixMatchMap;
    }

    public static double getLongPrefixPrice(Map<Integer, Double> prefixMap) throws PrefixException {
        double operatorCharge = 0.0;
        try {
            // define AtomicInteger for hold the largest prefix
            // used AtomicInteger because of the lambda usage
            AtomicInteger longestPrefix = new AtomicInteger();
            // lambda for each function for get elements from map
            if (prefixMap != null) {
                prefixMap.forEach((key, value) -> {
                    // check longest value and assign to longestPrefix variable
                    if (key.toString().length() > longestPrefix.toString().length()) {
                        longestPrefix.set(key);
                    }
                });
                // get the price by key
                operatorCharge = prefixMap.get(longestPrefix.get());
            }
        } catch (Exception e) {
            throw new PrefixException("Prefix Exception due to : " + e.getMessage());
        }
        return operatorCharge;
    }
}
