/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SigninController implements Initializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    Sec db=new Sec();
    
    public void signin(Event ev) throws SQLException{
        Connection con=db.conn();
        String user=username.getText();
        String pass=password.getText();
        String sql = "SELECT * FROM users Where username = ? and password = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs= ps.executeQuery();
            if (!rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Enter Correct Username / Password");
            }
        

        else{   
                JOptionPane.showMessageDialog(null, "Hello");
                Parent root =FXMLLoader.load(getClass().getResource("menu.fxml"));
                Scene scene=new Scene(root);
                Stage stage=(Stage)((Node)ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Menu");
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                stage.setX((rd.getWidth()-stage.getWidth()) /2);
                stage.setY((rd.getHeight()-stage.getHeight()) /2);
                
                
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void signUp(Event en){
        try {
            
                Parent root =FXMLLoader.load(getClass().getResource("register.fxml"));
                Scene scene=new Scene(root);
                Stage stage=(Stage)((Node)en.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Sign Up");
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                stage.setX((rd.getWidth()-stage.getWidth()) /2);
                stage.setY((rd.getHeight()-stage.getHeight()) /2);
                
                
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
}
    public void exit(){
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
