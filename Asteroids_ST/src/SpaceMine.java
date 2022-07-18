import java.awt.*;

public class SpaceMine extends VectorSprite{
    public SpaceMine(double x, double y, double a){
        shape = new Polygon();

        shape.addPoint(5,0);
        shape.addPoint(8,3);
        shape.addPoint(4,4);
        shape.addPoint(3,8);
        shape.addPoint(0,5);
        shape.addPoint(-3,8);
        shape.addPoint(-4,4);
        shape.addPoint(-8,3);
        shape.addPoint(-5,0);
        shape.addPoint(-8,-3);
        shape.addPoint(-4,-4);
        shape.addPoint(-3,-8);
        shape.addPoint(0,-5);
        shape.addPoint(3,-8);
        shape.addPoint(4,-4);
        shape.addPoint(8,-3);

        drawShape = new Polygon();
        drawShape.addPoint(5,0);
        drawShape.addPoint(8,3);
        drawShape.addPoint(4,4);
        drawShape.addPoint(3,8);
        drawShape.addPoint(0,5);
        drawShape.addPoint(-3,8);
        drawShape.addPoint(-4,4);
        drawShape.addPoint(-8,3);
        drawShape.addPoint(-5,0);
        drawShape.addPoint(-8,-3);
        drawShape.addPoint(-4,-4);
        drawShape.addPoint(-3,-8);
        drawShape.addPoint(0,-5);
        drawShape.addPoint(3,-8);
        drawShape.addPoint(4,-4);
        drawShape.addPoint(8,-3);

        THRUST = 3;

        xposition = x - (Math.cos(a) * THRUST);
        yposition = y - (Math.sin(a) * THRUST);

        xspeed = 0;
        yspeed = 0;
    }

    public void paint(Graphics g){
        g.fillPolygon(drawShape);
    }
}
