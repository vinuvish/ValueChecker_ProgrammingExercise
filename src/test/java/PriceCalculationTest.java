import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PriceCalculationTest {
    static final String PATH = "src/main/java/data/";
    static final String NUMBER = "4673212345";

    @Test
    void getOperatorADataTest() throws Exception {
        try {
            Map<Integer, Double> operatorATestMap = new HashMap<>();
            operatorATestMap.put(1, 0.9);
            operatorATestMap.put(268, 5.1);
            operatorATestMap.put(46, 0.17);
            operatorATestMap.put(4620, 0.0);
            operatorATestMap.put(468, 0.15);
            operatorATestMap.put(4631, 0.15);
            operatorATestMap.put(4673, 0.9);
            operatorATestMap.put(46732, 1.1);

            Map<Integer, Double> operatorAMap = new HashMap<>();
            operatorAMap = Utils.getDataFromFile(PATH + "operator_a.csv");
            System.out.println("\n");
            System.out.println("Test,get operators A data from file");
            System.out.println("Expected Map : " + operatorATestMap);
            System.out.println("Actual Map : " + operatorAMap);
            Assertions.assertTrue(operatorATestMap.equals(operatorAMap));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test
    void getOperatorBDataTest() throws Exception {
        try {
            // insert dummy prefix data of operator A
            Map<Integer, Double> operatorBTestMap = new HashMap<>();
            operatorBTestMap.put(1, 0.92);
            operatorBTestMap.put(44, 0.5);
            operatorBTestMap.put(46, 0.2);
            operatorBTestMap.put(467, 1.0);
            operatorBTestMap.put(48, 1.2);

            Map<Integer, Double> operatorBMap = new HashMap<>();
            operatorBMap = Utils.getDataFromFile(PATH + "operator_b.csv");
            System.out.println("\n");
            System.out.println("Test,get operators B data from file");
            System.out.println("Expected Map : " + operatorBTestMap);
            System.out.println("Actual Map : " + operatorBMap);
            Assertions.assertTrue(operatorBTestMap.equals(operatorBMap));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test
    void getOperatorAPrefixMatchTest() throws Exception {
        try {
            // calling getDataFromFile abstract method for get the date
            Map<Integer, Double> operatorMap = new HashMap<>();
            operatorMap = Utils.getDataFromFile(PATH + "operator_a.csv");

            // insert dummy prefix data of operator A
            Map<Integer, Double> operatorATestPrefixMatch = new HashMap<>();
            operatorATestPrefixMatch.put(4673, 0.9);
            operatorATestPrefixMatch.put(46732, 1.1);
            operatorATestPrefixMatch.put(46, 0.17);

            Map<Integer, Double> matchPrefixMap = new HashMap<>();
            matchPrefixMap = Utils.getPrefixMatchMap(operatorMap,NUMBER);
            System.out.println("\n");
            System.out.println("Test,operator A match prefix for number "+NUMBER);
            System.out.println("Expected Map : " + operatorATestPrefixMatch);
            System.out.println("Actual Map : " + matchPrefixMap);
            Assertions.assertTrue(operatorATestPrefixMatch.equals(matchPrefixMap));
        } catch (Exception e ) {
            throw new Exception(e);
        }

    }

    @Test
    void getOperatorBPrefixMatchTest() throws Exception {
        try {
            // calling getDataFromFile abstract method for get the date
            Map<Integer, Double> operatorMap = new HashMap<>();
            operatorMap = Utils.getDataFromFile(PATH + "operator_b.csv");

            // insert dummy prefix data of operator A
            Map<Integer, Double> operatorTestPrefixMatch = new HashMap<>();
            operatorTestPrefixMatch.put(467, 1.0);
            operatorTestPrefixMatch.put(46, 0.2);

            Map<Integer, Double> matchPrefixMap = new HashMap<>();
            matchPrefixMap = Utils.getPrefixMatchMap(operatorMap,NUMBER);
            System.out.println("\n");
            System.out.println("Test,operator B match prefix for number "+NUMBER);
            System.out.println("Expected Map : " + operatorTestPrefixMatch);
            System.out.println("Actual Map : " + matchPrefixMap);
            Assertions.assertTrue(operatorTestPrefixMatch.equals(matchPrefixMap));
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
    @Test
    void getLongPrefixOperatorAPriceTest() throws Exception {
        try {
            // calling getDataFromFile abstract method for get the date
            Map<Integer, Double> operatorMap = new HashMap<>();
            operatorMap = Utils.getDataFromFile(PATH + "operator_a.csv");

            Map<Integer, Double> matchPrefixMap = new HashMap<>();
            matchPrefixMap = Utils.getPrefixMatchMap(operatorMap,NUMBER);
            System.out.println("\n");
            System.out.println("Test,operator A longest prefix for number "+NUMBER);
            System.out.println("Expected prefix : 1.1" );
            System.out.println("Actual prefix : " + Utils.getLongPrefixPrice(matchPrefixMap));
            Assertions.assertEquals(Utils.getLongPrefixPrice(matchPrefixMap),1.1);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
    @Test
    void getLongPrefixOperatorBTest() throws Exception {
        try {
            // calling getDataFromFile abstract method for get the date
            Map<Integer, Double> operatorMap = new HashMap<>();
            operatorMap = Utils.getDataFromFile(PATH + "operator_b.csv");

            Map<Integer, Double> matchPrefixMap = new HashMap<>();
            matchPrefixMap = Utils.getPrefixMatchMap(operatorMap,NUMBER);
            System.out.println("\n");
            System.out.println("Test,operator B longest prefix for number "+NUMBER);
            System.out.println("Expected prefix : 1.0" );
            System.out.println("Actual prefix : " + Utils.getLongPrefixPrice(matchPrefixMap));
            Assertions.assertEquals(Utils.getLongPrefixPrice(matchPrefixMap),1.0);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}
