import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Asteroid extends VectorSprite {
    double size;

    public Asteroid() {
        size = 1;
        init();
    }

    public Asteroid(double x, double y, double s) {
        size = s;
        init();
        xposition = x;
        yposition = y;
    }

    public void init() {
        shape = new Polygon();

        drawShape = new Polygon();

        if (PlayMenu.randomOn){
            randomAsteroid();
        }
        else{
            normalAsteroid();
        }

        alive = true;

        double a, t;
        a = Math.random() * (Math.PI * 2);
        t = Math.random() * 2 + 1;

        xspeed = Math.cos(a) * t;
        yspeed = Math.sin(a) * t;

        ROTATION = Math.random() * 0.1;

        double x, y, h;

        h = 0;

        while (h < 100) {
            xposition = Math.random() * 900;
            yposition = Math.random() * 600;

            x = xposition - 450;
            y = yposition - 300;
            h = Math.sqrt((x * x) + (y * y));
        }
        for (int i = 0; i < shape.npoints; i++) {
            shape.xpoints[i] /= size;
            shape.ypoints[i] /= size;

            drawShape.xpoints[i] /= size;
            drawShape.ypoints[i] /= size;
        }

    }

    public void normalAsteroid(){
        shape.addPoint(30, 3);
        shape.addPoint(5, 35);
        shape.addPoint(-25, 10);
        shape.addPoint(-17, -15);
        shape.addPoint(20, -35);

        drawShape.addPoint(30, 3);
        drawShape.addPoint(5, 35);
        drawShape.addPoint(-25, 10);
        drawShape.addPoint(-17, -15);
        drawShape.addPoint(20, -35);
    }

    public void randomAsteroid() {
        for (int i = 0; i < 5; i++) { //to create random asteroids
            int x = 0;
            int y = 0;
            double a = 0;

            while ((x == 0 || y == 0) || ((2 * Math.PI * i) / 5 > a || (2 * Math.PI * (i + 1)) / 5 < a)) {
                x = ThreadLocalRandom.current().nextInt(-35, 36);
                y = ThreadLocalRandom.current().nextInt(-35, 36);

                if (x > 0 && y > 0) {
                    a = Math.atan(y / x);
                }
                if (x < 0 && y > 0) {
                    a = Math.PI - Math.atan(y / x);
                }
                if (x < 0 && y < 0) {
                    a = Math.PI + Math.atan(y / x);
                }
                if (x > 0 && y < 0) {
                    a = (Math.PI * 2) - Math.atan(y / x);
                }
            }
            shape.addPoint(x, y);
            drawShape.addPoint(x, y);
        }
    }

    public void updatePosition() {
        angle += ROTATION;
        super.updatePosition();
    }
}
