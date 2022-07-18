import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener, ActionListener {
    public Window panel;
    Spacecraft ship;
    ArrayList<Asteroid> asteroidList;
    ArrayList<Bullet> bulletList;
    ArrayList<Debris> debrisList;
    ArrayList<SpaceMine> mineList;

    Timer timer;
    Image offscreen;
    Graphics offg;

    boolean leftKey, rightKey, upKey, downKey, controlKey, spaceKey;

    int score, numAsteroids;

    public void init() {
        this.setVisible(true);
        this.setSize(900, 600);
        this.setTitle("Asteroids by Stefan");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        ship = new Spacecraft();
        timer = new Timer(20, this);

        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
        debrisList = new ArrayList<>();
        mineList = new ArrayList<>();

        if (PlayMenu.difficulty == 1){
            numAsteroids = 4;
        }
        if (PlayMenu.difficulty == 2){
            numAsteroids = 6;
        }
        if (PlayMenu.difficulty == 0){
            numAsteroids = 8;
        }

        for (int i = 0; i < numAsteroids; i++) {
            asteroidList.add(new Asteroid());
        }

        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();

        add(this.panel = new Window(this), BorderLayout.CENTER);

        score = 0;

        pack();
        timer.start();
    }

    public void keyCheck() {
        if (upKey) {
            ship.accelerate();
        }
        if (downKey) {
            ship.decelerate();
        }
        if (leftKey) {
            ship.rotateLeft();
        }
        if (rightKey) {
            ship.rotateRight();
        }
        if (controlKey) {
            ship.stop();
        }
        if (spaceKey) {
            fireBullet();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightKey = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftKey = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            upKey = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            downKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            controlKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceKey = true;
        }
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            upKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            downKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            controlKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            ship.gunChoice = Spacecraft.Guns.Pistol;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            ship.gunChoice = Spacecraft.Guns.Machine;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            ship.gunChoice = Spacecraft.Guns.Shotgun;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            ship.gunChoice = Spacecraft.Guns.Rocket;
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            ship.gunChoice = Spacecraft.Guns.Mine;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && Window.gameOver) {
            new StartMenu();
            dispose();
        }
    }

    public boolean collision(VectorSprite thing1, VectorSprite thing2) {
        int x, y;

        for (int i = 0; i < thing1.drawShape.npoints; i++) {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];

            if (thing2.drawShape.contains(x, y)) {
                return true;
            }
        }

        for (int i = 0; i < thing2.drawShape.npoints; i++) {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];

            if (thing1.drawShape.contains(x, y)) {
                return true;
            }
        }

        return false;
    }


    public void checkCollisions() {
        for (Asteroid asteroid : asteroidList) {
            if (collision(ship, asteroid) && ship.alive) {
                score -= 20;
                makeDebris(ship);
                ship.hit();
            }
            for (Bullet b : bulletList) {
                if (collision(asteroid, b)) {
                    makeDebris(asteroid);
                    score += 20;
                    b.alive = false;
                    asteroid.alive = false;
                }
            }

            for (int i = 0; i < mineList.size(); i++){
                if (collision(mineList.get(i), asteroid)){
                    makeDebris(asteroid);
                    makeDebris(mineList.get(i));
                    asteroid.alive = false;
                    mineList.remove(i);
                }
            }
        }
    }

    public void respawnShip() {
        if (!ship.alive && ship.counter > 100 && isRespawnSafe()) {
            ship.reset();
        }
    }

    public boolean isRespawnSafe() {
        int x, y, h;

        for (Asteroid a : asteroidList) {
            x = (int) (450 - a.xposition);
            y = (int) (300 - a.yposition);
            h = (int) Math.sqrt((x * x) + (y * y));

            if (h < 100) {
                return false;
            }
        }
        return true;
    }

    public void fireBullet() {

        // for the pistol
        if (ship.gunChoice == Spacecraft.Guns.Pistol) {
            if (ship.counter > 25 && ship.alive) {
                bulletList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle));
                ship.counter = 0;
            }
        }

        // for the machine gun
        if (ship.gunChoice == Spacecraft.Guns.Machine) {
            if (ship.counter > 4 && ship.alive) {
                bulletList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle));
                ship.counter = 0;
            }
        }

        // for the shotgun
        if (ship.gunChoice == Spacecraft.Guns.Shotgun) {
            if (ship.counter > 25 && ship.alive) {
                for (double i = ship.angle - (Math.PI / 4); i < ship.angle + (Math.PI / 4); i += (Math.PI / 14)) {
                    bulletList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], i));
                }
                ship.counter = 0;
            }
        }

        // for the rocket
        if (ship.gunChoice == Spacecraft.Guns.Rocket) {
            if (ship.counter > 50 && ship.alive) {
                bulletList.add(new Rocket(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle, this));
                ship.counter = 0;
            }
        }

        // for the mine
        if (ship.gunChoice == Spacecraft.Guns.Mine){
            if (ship.mines > 0 && ship.alive && ship.counter > 50){
                mineList.add(new SpaceMine(ship.xposition, ship.yposition, ship.angle));
                ship.mines --;
                ship.counter = 0;
            }
        }
    }

    public void checkAsteroidCollision() {
        for (int i = 0; i < asteroidList.size(); i++) {
            if (!asteroidList.get(i).alive) {
                if (asteroidList.get(i).size < 2) {
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, asteroidList.get(i).size + 0.5));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, asteroidList.get(i).size + 0.5));
                }
                asteroidList.remove(i);
            }
        }
    }

    public void makeDebris(VectorSprite v) {
        double randomNum = Math.random() * 5 + 5;

        for (int i = 0; i < randomNum; i++) {
            debrisList.add(new Debris(v.xposition, v.yposition));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyCheck();
        respawnShip();
        ship.updatePosition();
        checkAsteroidCollision();

        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).updatePosition();
        }

        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).updatePosition();

            if (ship.gunChoice == Spacecraft.Guns.Shotgun) {
                if (bulletList.get(i).counter >= 25) {
                    bulletList.remove(i);
                }
            } else {
                if ((bulletList.get(i).counter >= 50 && ship.gunChoice != Spacecraft.Guns.Rocket) || !bulletList.get(i).alive) {
                    bulletList.remove(i);
                }
            }
        }

        for (SpaceMine s : mineList){
            s.updatePosition();
        }

        for (int i = 0; i < debrisList.size(); i++) {
            debrisList.get(i).updatePosition();

            if (debrisList.get(i).counter >= 50) {
                debrisList.remove(i);
            }
        }
        checkCollisions();
        checkAsteroidCollision();
    }
}