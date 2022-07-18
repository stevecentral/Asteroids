import java.awt.*;

public class Rocket extends Bullet {
    Game game;
    int size;

    public Rocket(double x, double y, double a, Game game) {
        super(x, y, a);
        size = 2;

        this.game = game;

        shape = new Polygon();
        shape.addPoint(5, 0);
        shape.addPoint(2, 1);
        shape.addPoint(0, 3);
        shape.addPoint(-5, 5);
        shape.addPoint(-2, 0);
        shape.addPoint(-5, -5);
        shape.addPoint(0, -3);
        shape.addPoint(2, -1);

        drawShape = new Polygon();
        drawShape.addPoint(5, 0);
        drawShape.addPoint(2, 1);
        drawShape.addPoint(0, 3);
        drawShape.addPoint(-5, 5);
        drawShape.addPoint(-2, 0);
        drawShape.addPoint(-5, -5);
        drawShape.addPoint(0, -3);
        drawShape.addPoint(2, -1);

        THRUST = 5;

        xposition = x;
        yposition = y;

        for (int i = 0; i < shape.npoints; i++){
            shape.xpoints[i] *= size;
            shape.ypoints[i] *= size;
            drawShape.xpoints[i] *= size;
            drawShape.ypoints[i] *= size;
        }
    }

    public void updatePosition(){
        super.updatePosition();

        double h = 800;
        double x, y;

        for (Asteroid a : game.asteroidList){
            x = a.xposition - this.xposition;
            y = a.yposition - this.yposition;
            double temp = Math.sqrt((x * x) + (y * y));
            if (temp < h){
                h = temp;

                if (a.xposition > this.xposition && a.yposition > this.yposition){
                    angle = Math.atan(y/x);
                }
                if (a.xposition < this.xposition && a.yposition > this.yposition){
                    angle =Math.PI - Math.atan(y/ (x * (-1)));
                }
                if (a.xposition < this.xposition && a.yposition < this.yposition){
                    angle =Math.PI + Math.atan(y/x);
                }
                if (a.xposition > this.xposition && a.yposition < this.yposition){
                    angle = (Math.PI * 2) - Math.atan((y * (-1))/x);
                }
            }
        }

        xspeed = Math.cos(angle) * THRUST;
        yspeed = Math.sin(angle) * THRUST;
    }
}
