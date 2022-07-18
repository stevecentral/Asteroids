 import java.awt.*;

    public class Debris extends VectorSprite {
        public Debris(double x, double y){
            shape = new Polygon();

            shape.addPoint(1, 1);
            shape.addPoint(-1, 1);
            shape.addPoint(-1, -1);
            shape.addPoint(1, -1);

            drawShape = new Polygon();

            drawShape.addPoint(1, 1);
            drawShape.addPoint(-1, 1);
            drawShape.addPoint(-1, -1);
            drawShape.addPoint(1, -1);

            xposition = x;
            yposition = y;

            double a;
            a = Math.random() * Math.PI * 2;

            yspeed = Math.sin(a) * a;
            xspeed = Math.cos(a) * a;

            alive = true;
        }
    }
