package Navigator;

import Lib.GUIScreen.Homescreenecom;
import Lib.GUIScreen.LoginScreen;
import Lib.GUIScreen.SignUpScreen;
import Lib.GUIScreen.TableWithDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Navigate  {
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
    public void navigateOrderDetails(ActionEvent e){
        new TableWithDetails();
        currentFrame.dispose();
    }

}
