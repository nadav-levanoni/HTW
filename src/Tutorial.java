import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tutorial extends JFrame implements ActionListener {

    public Tutorial() {
        super("Tutorial");

        setSize(500,477);

        // Can resize the window with corners
        setResizable(true);

        // Upon the exit button it will break the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Setting the layout of the JFrame to the default Java Swing library

        setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(Tutorial.this.getClass().getResource("redbrick.png"));

        JLabel background = new JLabel(imageIcon);

        background.setLayout(new FlowLayout());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        /** DEBUG **/
        //System.out.println("Button has been clicked!");

        String name = e.getActionCommand();

    }

}
