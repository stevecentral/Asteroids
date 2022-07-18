import java.awt.*;

public class Spacecraft extends VectorSprite{
    int lives;
    static int shipChoice;

    enum Guns {
        Pistol,
        Machine,
        Shotgun,
        Rocket,
        Mine
    }

    Guns gunChoice;
    int mines = 3;

    public Spacecraft(){
        shape = new Polygon();
        drawShape = new Polygon();

        if (shipChoice == 1){
            shape1();
        }
        if (shipChoice == 1){
            shape1();
        }
        if (shipChoice == 2){
            shape2();
        }
        if (shipChoice == 3){
            shape3();
        }
        if (shipChoice == 4){
            shape4();
        }
        if (shipChoice == 5){
            shape5();
        }
        if (shipChoice == 6){
            shape6();
        }

        xposition = 450;
        yposition = 300;

        THRUST = 0.10;
        ROTATION = 0.1;

        alive = true;
        lives = 3;

        gunChoice = Guns.Pistol;
    }

    public void shape1(){
        shape.addPoint(15, 0);
        shape.addPoint(-10, 10);
        shape.addPoint(-10, -10);

        drawShape.addPoint(15, 0);
        drawShape.addPoint(-10, 10);
        drawShape.addPoint(-10, -10);
    }

    public void shape2(){
        shape.addPoint(15, 0);
        shape.addPoint(2, 3);
        shape.addPoint(-10, 10);
        shape.addPoint(-3,0);
        shape.addPoint(-10,-10);
        shape.addPoint(2, -3);

        drawShape.addPoint(15, 0);
        drawShape.addPoint(2, 3);
        drawShape.addPoint(-10, 10);
        drawShape.addPoint(-3,0);
        drawShape.addPoint(-10,-10);
        drawShape.addPoint(2, -3);
    }

    public void shape3(){
        shape.addPoint(15,0);
        shape.addPoint(6,-5);
        shape.addPoint(10, -5);
        shape.addPoint(10,-6);
        shape.addPoint(4, -6);
        shape.addPoint(0, -10);
        shape.addPoint(-10,-10);
        shape.addPoint(-4, 0);
        shape.addPoint(-10, 10);
        shape.addPoint(0, 10);
        shape.addPoint(4, 6);
        shape.addPoint(10,6);
        shape.addPoint(10,5);
        shape.addPoint(6, 5);

        drawShape.addPoint(15,0);
        drawShape.addPoint(6,-5);
        drawShape.addPoint(10, -5);
        drawShape.addPoint(10,-6);
        drawShape.addPoint(4, -6);
        drawShape.addPoint(0, -10);
        drawShape.addPoint(-10,-10);
        drawShape.addPoint(-4, 0);
        drawShape.addPoint(-10, 10);
        drawShape.addPoint(0, 10);
        drawShape.addPoint(4, 6);
        drawShape.addPoint(10,6);
        drawShape.addPoint(10,5);
        drawShape.addPoint(6, 5);
    }

    public void shape4(){
        shape.addPoint(3, 2);
        shape.addPoint(1, 1);
        shape.addPoint(-1, 1);
        shape.addPoint(-3, 1);
        shape.addPoint(-3, 2);
        shape.addPoint(-4, 1);
        shape.addPoint(-4, -1);
        shape.addPoint(-3, -1);
        shape.addPoint(-3, -2);
        shape.addPoint(-1, -1);
        shape.addPoint(1, -1);
        shape.addPoint(3, -1);
        shape.addPoint(3, -2);
        shape.addPoint(4, -1);
        shape.addPoint(4, 1);
        shape.addPoint(3, 1);

        drawShape.addPoint(3, 2);
        drawShape.addPoint(1, 1);
        drawShape.addPoint(-1, 1);
        drawShape.addPoint(-3, 1);
        drawShape.addPoint(-3, 2);
        drawShape.addPoint(-4, 1);
        drawShape.addPoint(-4, -1);
        drawShape.addPoint(-3, -1);
        drawShape.addPoint(-3, -2);
        drawShape.addPoint(-1, -1);
        drawShape.addPoint(1, -1);
        drawShape.addPoint(3, -1);
        drawShape.addPoint(3, -2);
        drawShape.addPoint(4, -1);
        drawShape.addPoint(4, 1);
        drawShape.addPoint(3, 1);

        increaseSize(5);
    }

    public void shape5(){
        shape.addPoint(-2, 5);
        shape.addPoint(-2, -5);
        shape.addPoint(-2, 0);
        shape.addPoint(0, -2);
        shape.addPoint(4, -2);
        shape.addPoint(6, 0);
        shape.addPoint(6, -5);
        shape.addPoint(6, 5);
        shape.addPoint(6, 0);
        shape.addPoint(4, 2);
        shape.addPoint(0, 2);
        shape.addPoint(-2, 0);

        drawShape.addPoint(-2, 5);
        drawShape.addPoint(-2, -5);
        drawShape.addPoint(-2, 0);
        drawShape.addPoint(0, -2);
        drawShape.addPoint(4, -2);
        drawShape.addPoint(6, 0);
        drawShape.addPoint(6, -5);
        drawShape.addPoint(6, 5);
        drawShape.addPoint(6, 0);
        drawShape.addPoint(4, 2);
        drawShape.addPoint(0, 2);
        drawShape.addPoint(-2, 0);

        increaseSize(5);
    }

    public void shape6(){
        shape.addPoint(15,0);
        shape.addPoint(-5,7);
        shape.addPoint(-10,7);
        shape.addPoint(-6,0);
        shape.addPoint(-10,-7);
        shape.addPoint(-5,-7);

        drawShape.addPoint(15,0);
        drawShape.addPoint(-5,7);
        drawShape.addPoint(-10,7);
        drawShape.addPoint(-6,0);
        drawShape.addPoint(-10,-7);
        drawShape.addPoint(-5,-7);
    }

    public void accelerate(){
        xspeed += Math.cos(angle) * THRUST;
        yspeed += Math.sin(angle) * THRUST;
    }

    public void decelerate() {
        xspeed -= Math.cos(angle) * THRUST;
        yspeed -= Math.sin(angle) * THRUST;
    }

    public void rotateLeft(){
        angle  -= ROTATION;
    }

    public void rotateRight(){
        angle += ROTATION;
    }

    public void stop() {
        xspeed = 0;
        yspeed = 0;
    }

    public void reset(){
        xposition = 450;
        yposition = 300;
        stop();
        angle = 0;
        alive = true;
    }

    public void hit(){
        alive = false;
        counter = 0;
        lives --;
    }
}
