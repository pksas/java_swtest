package ru.stqa.swtest.sandbox;

public class MyTask1 {
  public static void main(String[] args) {
    Point p1 = new Point(4, 3);
    Point p2 = new Point(0, 0);
    System.out.println(p1.distance(p2));
  }
}
