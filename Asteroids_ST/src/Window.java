import javax.swing.*;
import java.awt.*;


public class Window extends JPanel { //screen where we draw stuff
    Game game;
    int[] x;
    int[] y;

    static boolean gameOver = false;

    public Window(Game game){
        this.game = game;

        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.BLACK);

       x = new int[3]; //one way of initializing an array
       x[0] = 15;
       x[1] = 0;
       x[2] = 30;

       y = new int[]{20, 50, 50}; //other way
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.offg.setColor(Color.BLACK);
        game.offg.fillRect(0,0, 900, 600);
        game.offg.setColor(Color.GREEN);
        game.offg.setColor(PlayMenu.shipColor);
        if (game.ship.alive) {
            game.ship.paint(game.offg);
        }

        game.offg.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < game.asteroidList.size(); i++) {
            game.asteroidList.get(i).paint(game.offg);
        }

        for (Bullet b : game.bulletList){
            game.offg.setColor(Color.LIGHT_GRAY);
            b.paint(game.offg);
        }

        for (Debris debris : game.debrisList){
            debris.paint(game.offg);
        }

        for (SpaceMine s : game.mineList){
            s.paint(game.offg);
        }

        if (game.asteroidList.isEmpty() && game.ship.lives > 0){
            game.offg.setColor(Color.LIGHT_GRAY);
            game.offg.setFont(new Font("Ariel", Font.ITALIC, 25));
            game.offg.drawString("YOU WIN!", 375, 250);
            game.offg.drawString("Score: " + game.score, 390, 300);
            game.offg.drawString("press enter to return to the selection screen", 375, 800);
            gameOver = true;
        }

        if (game.ship.lives == 0){
            game.offg.setColor(Color.LIGHT_GRAY);
            game.offg.setFont(new Font("Ariel", Font.BOLD, 25));
            game.offg.drawString("GAME OVER", 375, 250);
            game.offg.drawString("Score: " + game.score, 390, 300);
            game.offg.drawString("press enter to return to the selection screen", 375, 800);
            game.asteroidList.clear();
            gameOver = true;
        }

        game.offg.setColor(Color.LIGHT_GRAY);
        game.offg.setFont(new Font("Ariel", Font.PLAIN, 10));
        game.offg.drawString("Lives: " + game.ship.lives, 20, 580);
        game.offg.drawString("Score: " + game.score, 820, 580);

        g.drawImage(game.offscreen, 0, 0, this);
        repaint();
    }
}
