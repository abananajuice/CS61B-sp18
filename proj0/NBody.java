public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int count = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int count = in.readInt();
        double Radius = in.readDouble();
        Planet[] planets = new Planet[count];
        int i = 0;

        while (i < count){
            /* 传入参数 */
            planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());       
            i++;
        }
        return planets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = readRadius(filename);
        Planet[] planets = new Planet[5];
        planets = readPlanets(filename);        
        StdDraw.setScale(-Radius,Radius);
        
        // int i = 0;
        // while (i < planets.length ){
        //     planets[i].draw();
        //     i++;
        // }

        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T){
            int j = 0;
            int i =0;
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            while (j < planets.length){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
                j++;
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            while(i<planets.length){
                planets[i].update(dt,xForces[i],yForces[i]);
                planets[i].draw();

                i++;
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}