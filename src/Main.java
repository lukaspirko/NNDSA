import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener{


    public void actionPerformed(ActionEvent e)
    {
        //clicked = !clicked;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
//        if (clicked) {
//            //Draw upside up
//        } else {
//            //Draw upside down
//        }

        g.drawLine(125,75,100,200);
        g.drawLine(125,75,150,200);
        g.drawLine(100,200,150,200);


    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("My frame");
        //Initialize your Triangle here
        //frame.add(triangle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}