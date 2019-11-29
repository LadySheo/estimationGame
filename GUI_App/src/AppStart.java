import project.controller.*;
import project.model.*;
import project.model.*;
import javax.swing.*;
import java.awt.event.*;

public class AppStart extends JFrame{
    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonCancel;

    public AppStart() {

        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        JFrame frameGame = new JFrame("Game");
        Game newGame = new Game();
        frameGame.setContentPane(newGame.gamePanel);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGame.pack();
        frameGame.setVisible(true);
        setVisible(false);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        JFrame frameApp = new JFrame("App");
        AppStart newApp = new AppStart();
        frameApp.setContentPane(newApp.contentPane);
        frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameApp.pack();
        frameApp.setVisible(true);

    }
}
