
package code;

import java.util.ArrayList;

/**
 * This class act as a bridge between model and view.
 * @author Amit Sachdeva
 *
 */
public class GammaController {
  GammaModel model;
  GammaView view;
  
  public GammaController(GammaModel tempModel, GammaView tempView) {
    this.model = tempModel;
    this.view = tempView;
  }
  
  /**
   * This startcontroller function attach all buttons with action listener.
   */
  public void startController() {
    this.view.getRefresh().addActionListener(e -> refresh());
    this.view.getReset().addActionListener(e -> resetApplication());
    this.view.getCalculate().addActionListener(e -> calculateGamma());
    this.view.getClose().addActionListener(e -> closeApplication());
  }
  
  private void calculateGamma() {
    if (this.view.getInputField().getText().compareTo("0.0") != 0) {
      this.view.getCalculate().setEnabled(false);
      String input = this.view.getInputField().getText();
      this.view.getInputField().setText("0.0");
      this.view.getCalculate().setEnabled(true);
      int flag = 0;
      String result = this.model.startGamma(input);
      this.view.setResult("");
      this.view.setHistory("");
      if (result.compareTo("negative") == 0) {
        this.view.setResult("Negative input is not allowed.");
        flag = 1;
      } else if (result.compareTo("error") == 0) {
        this.view.setResult("Entered input is not proper real or complex number.");
        flag = 1;
      } else if (result.compareTo("infinity") == 0) {
        this.view.setResult("Infinity");
        flag = 1;
      } else {
        this.view.setResult(result);
      }
      
      if (flag == 1) { 
        this.view.setAlert(1);
      } else {
        this.view.setAlert(2);
      }

      ArrayList<String[]> tempHistoryData = this.model.getHistoryData();
      String finalHistoryString = "";
      for (int i = tempHistoryData.size() - 1; i >= 0; i--) {
        String[] tempString  = tempHistoryData.get(i); 
        finalHistoryString += "Input :->    " + tempString[0] + "          "
           + "Result :->     " + tempString[1] + "\n\n";
      }
      this.view.setHistory(finalHistoryString);
    }
  }
  
  private void closeApplication() {
    System.exit(0);
  }

  private void resetApplication() {
    this.model.setHistoryData();
    this.view.setResult("0.0");
    this.view.getInputField().setText("0.0");
    this.view.setAlert(3);
    this.view.setHistory("*** No history Found ***");
  }
  
  private void refresh() {
    this.view.setResult("0.0");
    this.view.setAlert(3);
  }
}
