
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test class for the class vector
 */

public class testVector {
  private vector v1;
  private vector v2;
  private vector v3;
  private vector v4;
  private vector v5;

  @Before
  public void setup() {
    v1 = new vector(0.005,42,75);
    v2 = new vector(-20.0923,18,-7);
    v3 = new vector(-100,-54.678,-75);
    v4 = new vector(0,0,0); //no magnitude
    v5 = new vector(100,876,9324.12);
  }

  @Test
  public void testToString() {
    assertEquals("(0.01,42.00,75.00)", v1.toString());
    assertEquals("(-20.09,18.00,-7.00)", v2.toString());
    assertEquals("(0.00,0.00,0.00)", v4.toString());
    assertEquals("(100.00,876.00,9324.12)", v5.toString());
  }


  @Test
  public void testNormalize() {
    assertEquals("(0.00,0.49,0.87)",(v1.normalize()).toString());
    assertEquals("(-0.73,-0.40,-0.55)", (v3.normalize()).toString());
  }


  @Test
  public void testAdd() {
    vector addTest1 = v1.add(v2);
    assertEquals("(-20.09,60.00,68.00)", addTest1.toString());
    vector addTest2 = v3.add(v5);
    assertEquals("(0.00,821.32,9249.12)", addTest2.toString());
    vector addTest3 = v4.add(v1);
    assertEquals("(0.01,42.00,75.00)", addTest3.toString());
  }



  @Test
  public void testMultiply() {
    vector multTest1 = v1.multiply(1);
    assertEquals("(0.01,42.00,75.00)", multTest1.toString());
    vector multTest2 = v2.multiply(-23.234);
    assertEquals("(466.82,-418.21,162.64)", multTest2.toString());
    vector multTest3 = v5.multiply(0);
    assertEquals("(0.00,0.00,0.00)", multTest3.toString());
  }

  @Test
  public void testDotProduct() {
    double product1 = v2.dotProduct(v3);
    assertEquals(1550.026, product1, 0.01);
    double product2 = v5.dotProduct(v4);
    assertEquals(0.00, product2, 0.01);
  }

  @Test (expected = IllegalArgumentException.class)
  // Testing normalize error
  public void testInvalidNormalize() throws Exception {
    v4.normalize(); //cannot divide by zero
    v1.angleBetween(v4); //cannot divide  by 0
  }

  @Test
  public void testAngleBetween() {
  double angle1 = v2.angleBetween(v3);
  double angle2 = v1.angleBetween(v5);
  assertEquals(65.94, angle1, 0.1);
  assertEquals(23.83,angle2, 0.1);
  }

  @Test (expected = IllegalArgumentException.class)
  // Test normalize angleBetween
  public void testInvalidAngleBetween() throws Exception {
    v1.angleBetween(v4); //cannot divide  by 0
  }





}
