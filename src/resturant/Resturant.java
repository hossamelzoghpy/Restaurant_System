
package resturant;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Resturant extends Application
{
    @Override
    public  void start(Stage stage) throws Exception{
        Parent root =FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sign In");
        stage.show();
        
    }
    static Connection co;
    public static void main(String[] args) {
        launch(args);
        Sec con=new Sec();
        try{
            co= con.conn();
            System.out.println("ox");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
