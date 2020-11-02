import java.util.Arrays;
import java.util.List;

public class NBodyExtreme {

  public static boolean click = false;
  public static double xxPos;
  public static double yyPos;
  public static boolean click2 = false;

  public static double readRadius(String fileName){
    In in = new In(fileName);
    in.readInt();
    return in.readDouble();
  }

  public static void clicked() {
    click = true;
  }

  public static BodyExtreme[] readBodies(String fileName){
    In in = new In(fileName);
    int planets = in.readInt();
    BodyExtreme [] bodies = new BodyExtreme[planets];
    in.readDouble();
    for (int b = 0; b < planets; b++){
      bodies[b] = new BodyExtreme(in.readDouble(), in.readDouble(), in.readDouble(),
      in.readDouble(), in.readDouble(), in.readString());
    }
    return bodies;
  }

  public static void main(String[] args) {
    // Collecting All Needed Input
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    BodyExtreme [] bodies = readBodies(filename);
    double G = 6.67 * Math.pow(10.0, -11.0);
    double radius = readRadius(filename);

    // Drawing the Background
    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    for (BodyExtreme b: bodies){
      b.draw();
    }

    // Creating an Animation
    double time = 0.0;

    // Runs for specified amount of time
    while(time <= T){
      // Picture buffering
      StdDraw.enableDoubleBuffering();
      StdDraw.clear();

      // Once mouse is clicked, initializes x and y location of starship
      // Should only run once - only one click allowed
      if (click){
        xxPos = StdDraw.mouseX();
        yyPos = StdDraw.mouseY();
        click2 = true;
        click = false;
      }

      // New bodies2 that includes starship
      if (click2) {
        BodyExtreme [] bodies2 = new BodyExtreme [bodies.length + 1];
        for (int i = 0; i < bodies.length; i++){
          bodies2[i] = bodies[i];
        }
        bodies2[bodies.length] = new BodyExtreme(xxPos, yyPos, 0.0, 30000.0, 5.0 * Math.pow(10, 18), "star_destroyer.gif");
        bodies = bodies2;
        click2 = false;
      }

      // Initializes the length and forces (including starship if clicked)
      int n = bodies.length;
      double [] xForces = new double [n];
      double [] yForces = new double [n];

      // for every body (including starship here)
      for (int i = 0; i < n; i++){
        boolean ellastic = false;
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
        for (int j = 0; j < n; j++){
          if (i != j && bodies[i].calcDistance(bodies[j]) <= 8 * Math.pow(10, 9)){
            bodies[i].ellasticUpdate(dt, -xForces[i], -yForces[i]);
            ellastic = true;
          }
        }
        if (!ellastic){
          bodies[i].update(dt, xForces[i], yForces[i]);
        }
      }

      // Draw background
      StdDraw.picture(0, 0, "images/starfield.jpg");

      // Draw every body
      for (int i = 0; i < n; i++){
        bodies[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      time = time + dt;
    }

    // Printing the Universe
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
      bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
      bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }

  }

}
