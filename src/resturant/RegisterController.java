/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Hossam Asaad
 */
public class RegisterController implements Initializable {

      @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    TextField id;
    Sec db=new Sec();
    public void register(Event en){
        try{
        int ID=Integer.parseInt(id.getText());
        String user=username.getText();
        String pass=password.getText();
        
        if(!db.insertSignup(ID, user,pass)){
            JOptionPane.showMessageDialog(null, "User Register");
                Parent root =FXMLLoader.load(getClass().getResource("signin.fxml"));
                Scene scene=new Scene(root);
                Stage stage=(Stage)((Node)en.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Sign In");
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                stage.setX((rd.getWidth()-stage.getWidth()) /2);
                stage.setY((rd.getHeight()-stage.getHeight()) /2);
           
        }
        else{
            JOptionPane.showMessageDialog(null, "User Or ID Already Exists ");
        }
            }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
            public void signin(Event en){
        try {
            
                Parent root =FXMLLoader.load(getClass().getResource("signin.fxml"));
                Scene scene=new Scene(root);
                Stage stage=(Stage)((Node)en.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Sign In");
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
        // TODO
    }    
    
}
