/**
 * 
 */

package test;

import static org.junit.Assert.assertEquals;

import code.GammaController;
import code.GammaModel;
import code.GammaView;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This class is testing working of gamma functionality.
 * @author Amit Sachdeva
 *
 */
public class GammaAllTest {
  static GammaController gammaController;
  static GammaModel gammaModel;
  static GammaView gammaView;
  
  /**
  * In this method initializing of controller, view, model is done for testing.
  */
  @BeforeClass public static void beforeClass() {
    gammaModel = new GammaModel();
    gammaView = new GammaView("Gamma Function");
    gammaView.setBasicLayout(0);
    gammaController = new GammaController(gammaModel, gammaView);
    gammaController.startController();
  }

  @Test
  public void testLargePositiveNumber1() {
    System.out.println("Testing Large Positive number = 110.0");
    assertEquals("infinity", gammaModel.startGamma("110.0"));
  }
  
  @Test
  public void testLargePositiveNumber2() {
    System.out.println("Testing Large Positive number = 130.0");
    assertEquals("infinity", gammaModel.startGamma("130.0"));
  }
  
  
  @Test
  public void testNegativeNumber1() {
    System.out.println("Testing negative number = -2.0");
    assertEquals("negative", gammaModel.startGamma("-2.0"));
  }
  
  
  @Test
  public void testNegativeNumber2() {
    System.out.println("Testing boundary negative input = -0.0");
    assertEquals("1.0", gammaModel.startGamma("-0.0"));
  }
  
  
  @Test
  public void testZeroValue() {
    System.out.println("Testing zero value");
    assertEquals("1.0", gammaModel.startGamma("0.0"));
  }
  
  @Test
  public void testNormalPositiveNumber1() {
    System.out.println("Positive value in a domain = 13.0");
    assertEquals("479001600.000", gammaModel.startGamma("13.0"));
  }
  
  @Test
  public void testNormalPositiveNumber2() {
    System.out.println("Positive value in a domain = 6.0");
    assertEquals("120.000", gammaModel.startGamma("6.0"));
  }
  
  @Test
  public void testKnownConstant() {
    System.out.println("Testing already defined value = 0.5");
    assertEquals("1.710", gammaModel.startGamma("0.5"));
  }  
  
  @Test
  public void testWrongInput1() {
    System.out.println("Testing wrong input of complex number = i9");
    assertEquals("error", gammaModel.startGamma("i9"));
  } 
  
  @Test
  public void testWrongInput2() {
    System.out.println("Testing any random wrong input = gjhfjjf ");
    assertEquals("error", gammaModel.startGamma("gjhfjjf"));
  } 
  
  @Test
  public void testComplexNumber1() { 
    System.out.println("Testing correct complex number = 0+i9");
    assertEquals("1.0", gammaModel.startGamma("0 + i9"));
  } 
  
  @Test
  public void testComplexNumber2() {
    System.out.println("Testing correct complex number = 6+i9");
    assertEquals("120.000", gammaModel.startGamma("6 + i9"));
  } 

}
