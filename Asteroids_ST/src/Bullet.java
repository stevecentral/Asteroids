import java.awt.*;

public class Bullet extends VectorSprite{
    public Bullet(double x, double y, double a){
        shape = new Polygon();

        shape.addPoint(1,1);
        shape.addPoint(-1,1);
        shape.addPoint(1,-1);
        shape.addPoint(-1,-1);

        drawShape = new Polygon();

        drawShape.addPoint(1,1);
        drawShape.addPoint(-1,1);
        drawShape.addPoint(1,-1);
        drawShape.addPoint(-1,-1);

        xposition = x;
        yposition = y;
        angle = a;

        THRUST = 10;

        yspeed = Math.sin(angle) * THRUST;
        xspeed = Math.cos(angle) * THRUST;

        alive = true;
    }
}
