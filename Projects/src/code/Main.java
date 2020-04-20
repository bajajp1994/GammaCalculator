/**
 * 
 */

package code;

/**
 * This Main Function is intializer of our Main Class.
 * @author Amit Sachdeva 
 */

public class Main {
  /**
  * Instantiates a model, view and controller, and call startController function of controller.
  */
  public Main() {
    GammaModel tempModel = new GammaModel();
    GammaView tempView = new GammaView("Gamma Calculator");
    tempView.setBasicLayout(1);
    GammaController tempController = new GammaController(tempModel, tempView);
    tempController.startController();
  }
  
  /**
  *  Main function where application will start.
  *  @param args It is basic format.
  */
  
  public static void main(String[] args) {
    new Main();
  }

}
