import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayMenu extends JFrame {
    int clickNum, colorClickNum = 0;
    JPanel main = new JPanel();

    JButton gameMode = new JButton("Gamemode");
    JButton play = new JButton("Play");
    JButton color = new JButton("Color");
    JCheckBox randomAsteroids = new JCheckBox("Random");

    Color[] colorList = {Color.GREEN, Color.PINK, Color.YELLOW, Color.ORANGE, Color.RED, Color.MAGENTA, Color.CYAN, Color.BLUE};
    static Color shipColor;

    static int difficulty = 2;

    static boolean randomOn;

    public PlayMenu() {
        this.setVisible(true);
        this.setSize(900, 600);
        this.setTitle("Play Menu - Stefan");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        main.setLayout(null);

        addingButtons();
        this.getContentPane().add(main);
    }

    public void addingButtons() {
        gameMode.setBounds(350, 100, 200, 50);
        play.setBounds(350, 200, 200, 50);
        color.setBounds(350, 300, 200, 50);
        randomAsteroids.setBounds(600,100,700,25);

        buttonListener();
    }

    public void buttonListener() {
        gameMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickNum++;
                difficulty = clickNum % 3;

                if (clickNum % 3 == 1) {
                    gameMode.setText("Easy");
                }
                if (clickNum % 3 == 2) {
                    gameMode.setText("Casual");
                }
                if (clickNum % 3 == 0) {
                    gameMode.setText("Hard");
                }
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game asteroids = new Game();
                asteroids.init();
                dispose();
            }
        });

        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorClickNum++;
                color.setBackground(colorList[colorClickNum % 8]);
                shipColor = colorList[colorClickNum % 8];
                if (colorClickNum % 8 == 1) {
                    color.setText("Pink");
                }
                if (colorClickNum % 8 == 2) {
                    color.setText("Yellow");
                }
                if (colorClickNum % 8 == 3) {
                    color.setText("Orange");
                }
                if (colorClickNum % 8 == 4) {
                    color.setText("Red");
                }
                if (colorClickNum % 8 == 5) {
                    color.setText("Magenta");
                }
                if (colorClickNum % 8 == 6) {
                    color.setText("Cyan");
                }
                if (colorClickNum % 8 == 7) {
                    color.setText("Blue");
                }
                if (colorClickNum % 8 == 0) {
                    color.setText("Green");
                }
            }
        });

        randomAsteroids.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (randomAsteroids.isSelected()){
                    randomOn = true;
                }
                else{
                    randomOn = false;
                }
            }
        });

        main.add(gameMode);
        main.add(play);
        main.add(color);
        main.add(randomAsteroids);
    }
}
