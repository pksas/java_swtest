package ru.stqa.swtest.sandbox;

public class Equation {

  private int n;
  private double a;
  private double b;
  private double c;

  public Equation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
    double d = b*b - 4*a*c;

    if (d < 0) {
      n = 0;
    }
    else {
      if (d == 0) {
        n = 1;
      }
      else {
        n = 2;
      }
    }
  }

  public int rootQty() {
    return n;
  }
}
