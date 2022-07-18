import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {

    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();

    ImageIcon one = new ImageIcon("Ship1.png");
    ImageIcon two = new ImageIcon("Ship2.png");
    ImageIcon three = new ImageIcon("Ship3.png");
    ImageIcon four = new ImageIcon("Ship4.png");
    ImageIcon five = new ImageIcon("Ship5.png");
    ImageIcon six = new ImageIcon("Ship6.png");

    public StartMenu(){
        this.setVisible(true);
        this.setSize(900, 600);
        this.setTitle("Start Menu - Stefan");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        addButtons();
    }

    public void addButtons(){
        button1.setIcon(one);
        button2.setIcon(two);
        button3.setIcon(three);
        button4.setIcon(four);
        button5.setIcon(five);
        button6.setIcon(six);

        button1.setBounds(100,100,200,100);
        button2.setBounds(350,100,200,100);
        button3.setBounds(600,100,200,100);
        button4.setBounds(100,300,200,100);
        button5.setBounds(350,300,200,100);
        button6.setBounds(600,300,200,100);

        buttonListeners();

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
    }

    public void buttonListeners(){

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spacecraft.shipChoice = 1;
                new PlayMenu();
                dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spacecraft.shipChoice = 2;
                new PlayMenu();
                dispose();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spacecraft.shipChoice = 3;
                new PlayMenu();
                dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spacecraft.shipChoice = 4;
                new PlayMenu();
                dispose();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spacecraft.shipChoice = 5;
                new PlayMenu();
                dispose();
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spacecraft.shipChoice = 6;
                new PlayMenu();
                dispose();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Ariel", Font.PLAIN, 50));
        g.drawString("CHOOSE YOUR SHIP", 200, 100);
    }
}
