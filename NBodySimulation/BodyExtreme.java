import java.lang.Math;


public class BodyExtreme {

  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public BodyExtreme(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    if (img.equals("star_destroyer.gif")){
      imgFileName = img;
    } else if (yyVel == 0){
      imgFileName = img;
    } else {
      String [] FileNames = {"earth.gif", "mars.gif", "mercury.gif", "venus.gif",
      "jupiter.gif", "neptune.gif", "saturn.gif", "uranus.gif"};
      int index = (int)(Math.random() * FileNames.length);
      imgFileName = FileNames[index];

    }
  }

  public BodyExtreme(BodyExtreme b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(BodyExtreme b){
    return Math.sqrt( Math.pow(this.xxPos - b.xxPos, 2.0) + Math.pow(this.yyPos - b.yyPos, 2.0) );
  }

  public double calcForceExertedBy(BodyExtreme b){
    return (6.67 * Math.pow(10.0, -11.0)) * (this.mass * b.mass) / Math.pow(this.calcDistance(b), 2.0);
  }

  public double calcForceExertedByX(BodyExtreme b){
    return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
  }

  public double calcForceExertedByY(BodyExtreme b){
    return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
  }

  public double calcNetForceExertedByX(BodyExtreme [] bodies){
    double total = 0.0;
    for (BodyExtreme b : bodies){
      if (b.equals(this))
        continue;
      total += this.calcForceExertedByX(b);
    }
    return total;
  }

  public double calcNetForceExertedByY(BodyExtreme [] bodies){
    double total = 0.0;
    for (BodyExtreme b : bodies){
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
  public void ellasticUpdate(double dt, double fX, double fY){
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
