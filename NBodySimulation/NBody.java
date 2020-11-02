public class NBody{

  public static double readRadius(String fileName){
    In in = new In(fileName);
    in.readInt();
    return in.readDouble();
  }

  public static Body[] readBodies(String fileName){
    In in = new In(fileName);
    int planets = in.readInt();
    Body [] bodies = new Body[planets];
    in.readDouble();
    for (int b = 0; b < planets; b++){
      bodies[b] = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
      in.readDouble(), in.readDouble(), in.readString());
    }
    return bodies;
  }

  public static void main(String[] args) {
    // Collecting All Needed Input
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Body [] bodies = readBodies(filename);
    double G = 6.67 * Math.pow(10.0, -11.0);
    double radius = readRadius(filename);
    int n = bodies.length;

    // Drawing the Background
    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    for (Body b: bodies){
      b.draw();
    }

    // Creating an Animation
    double time = 0.0;
    while(time <= T){
      StdDraw.enableDoubleBuffering();
      StdDraw.clear();
      double [] xForces = new double [n];
      double [] yForces = new double [n];
      for (int i = 0; i < n; i++){
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
        bodies[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
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
