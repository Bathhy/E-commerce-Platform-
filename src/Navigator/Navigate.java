package Navigator;

import Lib.GUIScreen.Homescreenecom;
import Lib.GUIScreen.LoginScreen;
import Lib.GUIScreen.SignUpScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Navigate implements ActionListener {
    private JFrame currentFrame;

    public Navigate(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void navigateSignup(ActionEvent e) {
        new SignUpScreen();
        currentFrame.dispose();
    }
    public void navigatelogin(ActionEvent e) {
        new LoginScreen();
        currentFrame.dispose();
    }

    public void navigateHome(ActionEvent e){
        new Homescreenecom();
        currentFrame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle generic action events if needed
    }
}
