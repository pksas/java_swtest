package ru.stqa.swtest.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void testDistanceCaseOne() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(-3, -4);
    Assert.assertEquals(p1.distance(p2), 10);
  }

  @Test
  public void testDistanceCaseTwo() {
    Point p1 = new Point(-3, 0);
    Point p2 = new Point(0, -4);
    Assert.assertEquals(p1.distance(p2), 5);
  }

  @Test
  public void testDistanceCaseThree() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0);
  }
}
