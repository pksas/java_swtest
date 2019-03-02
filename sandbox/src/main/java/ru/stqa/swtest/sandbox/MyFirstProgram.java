package ru.stqa.swtest.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(4, 3);
    Point p2 = new Point(0, 0);
    System.out.println(p1.distance(p2));
    hello("world");
    hello("user");
    hello("Alexey");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}