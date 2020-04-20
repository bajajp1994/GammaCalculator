
package code;

import java.util.ArrayList;

/**
 * This class act as a model which is responsible for data storage.
 * @author Amit Sachdeva
 *
 */
public class GammaModel {
  private ArrayList<String[]> historyData;
  private static final double CONSTANT_HEIGHT = 0.001;
  private static final int UPPER_BOUND = 201;
  private static double EXPONENT;
  
  /**
   * This is constructor just initializing the historyData. 
   */
  public GammaModel() {
    this.historyData = new ArrayList<String[]>();
    EXPONENT = exponentConstant();
  }
  
  public ArrayList<String[]> getHistoryData() {
    return this.historyData;
  }

  public void setHistoryData() {
    this.historyData = new ArrayList<String[]>();
  }
  
  /**
   * This function is responsible for handling errors and performing gamma function.
   * @param input Input by user
   * @return Result after applying gamma function operation.
   */
  
  public String startGamma(String input) {
    String[] storageData = new String[2];
    storageData[0] = input;
    storageData[1] = "Entered input is not proper real or complex number.";
    String output = "error";
    try {
      String[] temp = input.split("\\+");
      double value = Double.valueOf(temp[0].trim());
      if (value < 0) {
        storageData[1] = "Negative input is not allowed.";
        output = "negative";
      } else if (value == 0 || value == 1) {
        storageData[1] = "1.0";
        output = "1.0";
      } else if (value >= 110) {
        storageData[1] = "Infinity";
        output = "infinity";
      } else {
        value = value - 1;
        String result = "0.0";
        if (value < 18) {
          result = String.format("%.03f", this.gammaCalculation(value));
        } else {
          result = String.valueOf(this.gammaCalculation(value));
        }
        
        storageData[1] = result;
        output = result;
      }
    } catch (Exception e) {
      storageData[1] = "Entered input is not proper real or complex number.";
      output = "error";
    }
    this.historyData.add(storageData);
    return output;
  }
  
  private double exponentConstant() {
    double sum = 1.0;
    double ratio = 1;
    sum = sum + ratio;
    for (int i = 2; i <= 475; i++) {
      ratio = ratio * 1 / i;
      sum +=  ratio;
    }
    return sum;
  }
  
  private double integration(double value, double lowerLimit, 
      double upperLimit) {
    double summation = 0.0;
    for (double index = lowerLimit + CONSTANT_HEIGHT; 
        index < upperLimit; index += CONSTANT_HEIGHT) {
      double differnce = index - lowerLimit;
      if (lowerLimit + differnce  != 0 && lowerLimit + differnce  - CONSTANT_HEIGHT != 0) {
        summation += (mainOperation(value, lowerLimit + differnce) 
           + mainOperation(value, lowerLimit + differnce - CONSTANT_HEIGHT));
      }
    }
    summation = (CONSTANT_HEIGHT / 2) * summation;
    return summation;
  }
  
  private double logarithmFunction(double base, double power) {
    double sum = 0;
    double count = 20;
    if (base <= 0.1) {
      count = 10000;
    } else {
      count = 1000;
    }
    double multiple = 1.0;
    for (int i = 1; i < count; i++) {
      multiple = multiple * ((base - 1) / (base + 1));
      if (i % 2 != 0) {
        sum = sum + multiple / i;
      }
    }
    return 2 * sum * power;  
  }

  private double powerFunction(double base, double power) {
    double tempPower;
    double sum;
    if (base ==   EXPONENT) {
      tempPower = power;
      if (tempPower < 0) {
        tempPower = tempPower * -1;
      }
      sum = 1.0;
      double ratio = tempPower;
      sum = sum + ratio;
      for (int i = 2; i <= 475; i++) {
        ratio = ratio * tempPower / i;
        sum +=  ratio;
      }
      sum = 1.0 / sum;
    } else {
      tempPower = logarithmFunction(base,power);
      int flag = 0;
      if (tempPower < 0) {
        tempPower = tempPower * -1;
        flag = 1;
      }
      sum = 1.0;
      double ratio = tempPower;
      sum = sum + ratio;
      for (int i = 2; i <= 475; i++) {
        ratio = ratio * tempPower / i;
        sum +=  ratio;
      }
      if (flag == 1) {
        sum = 1.0 / sum;
      }
    }
    return sum;
  }
  
  private double mainOperation(double value, double x) {
    double tempValue = powerFunction(x, value) * powerFunction(EXPONENT, -x);
    return tempValue;
  }
  
  private double gammaCalculation(double value) {
    return integration(value, 0, UPPER_BOUND);
  }
  
  

}


