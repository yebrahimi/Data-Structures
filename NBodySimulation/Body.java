import java.lang.Math;

public class Body{

  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Body(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body(Body b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b){
    return Math.sqrt( Math.pow(this.xxPos - b.xxPos, 2.0) + Math.pow(this.yyPos - b.yyPos, 2.0) );
  }

  public double calcForceExertedBy(Body b){
    return (6.67 * Math.pow(10.0, -11.0)) * (this.mass * b.mass) / Math.pow(this.calcDistance(b), 2.0);
  }

  public double calcForceExertedByX(Body b){
    return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
  }

  public double calcForceExertedByY(Body b){
    return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
  }

  public double calcNetForceExertedByX(Body [] bodies){
    double total = 0.0;
    for (Body b : bodies){
      if (b.equals(this))
        continue;
      total += this.calcForceExertedByX(b);
    }
    return total;
  }

  public double calcNetForceExertedByY(Body [] bodies){
    double total = 0.0;
    for (Body b : bodies){
      if (b.equals(this))
        continue;
      total += this.calcForceExertedByY(b);
    }
    return total;
  }

  public void update(double dt, double fX, double fY){
    double aX = fX / this.mass;
    double aY = fY / this.mass;
    this.xxVel = this.xxVel + aX * dt;
    this.yyVel = this.yyVel + aY * dt;
    this.xxPos = this.xxPos + this.xxVel * dt;
    this.yyPos = this.yyPos + this.yyVel * dt;
  }

  public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, "/images/" + this.imgFileName);
  }
}
