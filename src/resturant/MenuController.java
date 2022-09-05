
package resturant;
import java.lang.NullPointerException;
import javafx.event.Event;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MenuController implements Initializable {
    @FXML
    TextField numM;
    @FXML
    TextField numD;
    @FXML
    Pane paneM;
    @FXML
    Pane paneD;
    @FXML
    ComboBox typeDO;
    @FXML
    TextField nameDO;
    @FXML
    TextField numDO;
    @FXML
    TextField priceDO;
    @FXML
    ComboBox typeMO;
    @FXML
    TextField nameMO;
    @FXML
    TextField numMO;
    @FXML
    TextField priceMO;
    @FXML
    TableView<Meals> tableM;
    @FXML
    TableColumn<Meals,Integer>numMC1;
    @FXML
    TableColumn<Meals,String>nameMC2;
    @FXML
    TableColumn<Meals,String>typeMC3;
    @FXML
    TableColumn<Meals,Float>priceMC4;
     @FXML
    TableView<drinks> tableD;
    @FXML
    TableColumn<drinks,Integer>numDC1;
    @FXML
    TableColumn<drinks,String>nameDC2;
    @FXML
    TableColumn<drinks,String>typeDC3;
    @FXML
    TableColumn<drinks,Float>priceDC4;
    @FXML
    Text done;
    @FXML
    TextField searchM;
    @FXML
    TextField searchD;
    ObservableList<Meals> listM;
    ObservableList<drinks> listD;
    int indexM=-1;
    int indexD=-1;
    Sec db=new Sec();
    
    public void entred(Event ee){
        ((Button)ee.getSource()).setScaleX(1.1);
        ((Button)ee.getSource()).setScaleY(1.1);
        ((Button)ee.getSource()).setTextFill(Color.ORANGE);
      
    }
    public void exited(Event ee){
        ((Button)ee.getSource()).setScaleX(1);
        ((Button)ee.getSource()).setScaleY(1);
        ((Button)ee.getSource()).setTextFill(Color.WHITE);
        
    }
    public void meal(){
        paneM.setVisible(true);
        paneD.setVisible(false);
        
        done.setText("");
//        numMO.setText("");
//        nameMO.setText("");
//        typeMO.getSelectionModel().select(-1);
//        priceMO.setText("");
       
        
        
}
    public void drink(){
        paneM.setVisible(false);
        paneD.setVisible(true);
        done.setText("");
    
//        numDO.setText("");
//        nameDO.setText("");
//        typeDO.getSelectionModel().select(-1);
//        priceDO.setText("");
        
}
    /////////////////////////////////insert meal///////////////////////////////
    public void insertmeal() throws SQLException{
        try{
        int id=Integer.parseInt(numMO.getText());
        String name=nameMO.getText();
        String type=typeMO.getSelectionModel().getSelectedItem().toString();
        float price=Float.parseFloat(priceMO.getText());
        
        if(!db.insert("meals", id, name, type, price)){
           listM.add(new Meals(id, name, type, price));
           done.setText("!Insert Done!");
           done.setVisible(true);
           numM.setText(Integer.parseInt(numM.getText())+1+"");
        }}catch(Exception e){
            JOptionPane.showMessageDialog(null, "No Data To Insert ");
        }
        //emptyMeal();
        
    }
    ////////////////////////insert drink////////////////////////////////////////
     public void insertdrink() throws SQLException{
        try{
        int id=Integer.parseInt(numDO.getText());
        String name=nameDO.getText();
        String type=typeDO.getSelectionModel().getSelectedItem().toString();
        float price=Float.parseFloat(priceDO.getText());
        
        if(!db.insert("drinks", id, name, type, price)){
           listD.add(new drinks(id, name, type, price));
           done.setText("!Insert Done!");
           done.setVisible(true);
           numD.setText(Integer.parseInt(numD.getText())+1+"");
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No Data To Insert ");
        }
       // emptyDrink();
    }
     //////////////////////////select from meal table//////////////////////////
     public void selectMeal(){
        indexM=tableM.getSelectionModel().getSelectedIndex();
        if(indexM<=-1){
            return;
        }
        
        nameMO.setText(nameMC2.getCellData(indexM).toString());
        typeMO.getSelectionModel().select(typeMC3.getCellData(indexM));
        priceMO.setText(priceMC4.getCellData(indexM).toString());
        numMO.setText(numMC1.getCellData(indexM).toString());
     }
     //////////////////////////select from drink table////////////////////////
     public void selectDrink(){
        indexD=tableD.getSelectionModel().getSelectedIndex();
        if(indexD<=-1){
            return;
        }
        
        nameDO.setText(nameDC2.getCellData(indexD).toString());
        typeDO.getSelectionModel().select(typeDC3.getCellData(indexD));
        priceDO.setText(priceDC4.getCellData(indexD).toString());
        numDO.setText(numDC1.getCellData(indexD).toString());
     }
     ///////////////////////////////////update meal///////////////////////
     public void updateMeal() throws SQLException{
        try{
        int id=Integer.parseInt(numMO.getText());
        String name=nameMO.getText();
        String type=typeMO.getSelectionModel().getSelectedItem().toString();
        float price=Float.parseFloat(priceMO.getText());
        if(db.update("meals", "where numMeal="+id, name, type, price)){
            listM.set(indexM,new Meals(listM.get(indexM).getId(), name, type, price));//list for table
            done.setText("!Updating Done");
            
        }}catch(Exception e){
//            JOptionPane.showMessageDialog(null, "No Data To Updata ");
        }
       //emptyMeal();
     }
     /////////////////////////////update drink//////////////////////////////////
     public void updateDrink() throws SQLException{
        try{
        int id=Integer.parseInt(numDO.getText());
        String name=nameDO.getText();
        String type=typeDO.getSelectionModel().getSelectedItem().toString();
        float price=Float.parseFloat(priceDO.getText());
        if(db.update("drinks", "where numDrink="+id, name, type, price)){
            listD.set(indexD,new drinks(listD.get(indexD).getId(), name, type, price));//list for table
            done.setText("!Updating Done");
            
        }}catch(Exception e){
//            JOptionPane.showMessageDialog(null, "No Data To Update ");
        }
        //emptyDrink();
     }
     ////////////////////////////delete meal/////////////////////////////
    public void deleteMeal() throws SQLException{
    if(indexM==-1)
        return;
    if(db.delete("meals", "numMeal="+numMC1.getCellData(indexM))){
      done.setText("!Deleting Done!");
      numM.setText(Integer.parseInt(numM.getText())-1+"");
      listM.remove(indexM);
      indexM=-1;
     // emptyMeal();
    }
}
      ////////////////////////////////delete drink/////////////////////////////////
    public void deleteDrink() throws SQLException{
    if(indexD==-1)
        return;
    if(db.delete("drinks", "numDrink="+numDC1.getCellData(indexD))){
      done.setText("!Deleting Done!");
      numD.setText(Integer.parseInt(numD.getText())-1+"");
      listD.remove(indexD);
      indexD=-1;
      //emptyDrink();
    }
}
    /////////////////////////////Empty meal///////////////////////////////
     public void emptyMeal(){
         numMO.clear();
         nameMO.clear();
         typeMO.getSelectionModel().select(-1);
         priceMO.clear();
         done.setText("");
     }
     ////////////////////////////empty meal////////////////////////////////
     public void emptyDrink(){
         numDO.clear();
         nameDO.clear();
         typeDO.getSelectionModel().select(-1);
         priceDO.clear();
         done.setText("");
     }
     //////////////////////////////log out///////////////////////////////////////////////
    public void logout(Event ev){
        try {
            Parent root =FXMLLoader.load(getClass().getResource("signin.fxml"));
                Scene scene=new Scene(root);
                Stage stage=(Stage)((Node)ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Sign In");
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                stage.setX((rd.getWidth()-stage.getWidth()) /2);
                stage.setY((rd.getHeight()-stage.getHeight())/2 );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
    /////////////////////////////////////search meal/////////////////////////////////
    public void searchMeal(){
       searchM.textProperty().addListener(new InvalidationListener() {
           
           public void invalidated(javafx.beans.Observable observ) {
               if(searchM.textProperty().get().isEmpty()){
                   tableM.setItems(listM);
                   return;
               }
               ObservableList<Meals> tableitems=FXCollections.observableArrayList();
               ObservableList<TableColumn<Meals,?>> columns=tableM.getColumns();
               for(int i=0;i<listM.size();i++){
                   for(int j=0;j<columns.size();j++){
                       TableColumn col=columns.get(j);
                       String cellValue=col.getCellData(listM.get(i)).toString();
                       cellValue=cellValue.toLowerCase();
                       if(cellValue.contains(searchM.getText().toLowerCase())&&
                               cellValue.startsWith(searchM.getText().toLowerCase())){
                           tableitems.add(listM.get(i));
                           break;
                       }
                   }
               }
               tableM.setItems(tableitems);
           }

           
       });
    }
    
    /////////////////////////////////////////////search drink////////////////
    
    public void searchDrink(){
       searchD.textProperty().addListener(new InvalidationListener() {
           
           public void invalidated(javafx.beans.Observable observ) {
               if(searchD.textProperty().get().isEmpty()){
                   tableD.setItems(listD);
                   return;
               }
               ObservableList<drinks> tableitems=FXCollections.observableArrayList();
               ObservableList<TableColumn<drinks,?>> columns=tableD.getColumns();
               for(int i=0;i<listD.size();i++){
                   for(int j=0;j<columns.size();j++){
                       TableColumn col=columns.get(j);
                       String cellValue=col.getCellData(listD.get(i)).toString();
                       cellValue=cellValue.toLowerCase();
                       if(cellValue.contains(searchD.getText().toLowerCase())&&
                          cellValue.startsWith(searchD.getText().toLowerCase())){
                           tableitems.add(listD.get(i));
                           break;
                       }
                   }
               }
               tableD.setItems(tableitems);
           }

           
       });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //////////////////////////////table meals///////////////////////////
        numMC1.setCellValueFactory(new PropertyValueFactory<Meals,Integer>("id"));
        nameMC2.setCellValueFactory(new PropertyValueFactory<Meals,String>("name"));
        typeMC3.setCellValueFactory(new PropertyValueFactory<Meals,String>("type"));
        priceMC4.setCellValueFactory(new PropertyValueFactory<Meals,Float>("cost"));
        //table drink
        numDC1.setCellValueFactory(new PropertyValueFactory<drinks,Integer>("id"));
        nameDC2.setCellValueFactory(new PropertyValueFactory<drinks,String>("name"));
        typeDC3.setCellValueFactory(new PropertyValueFactory<drinks,String>("type"));
        priceDC4.setCellValueFactory(new PropertyValueFactory<drinks,Float>("cost"));
        try {
            //insert table meals
            listM=db.getMeal();
            tableM.setItems(listM);
            //insert in table drinks
            listD=db.getDrink();
            tableD.setItems(listD);
            //////////////////////////////////////////////////////////
            numM.setText(db.count("numMeal", "meals")+""); //to put data from database to table and convert it to string so i use +""
            numD.setText(db.count("numDrink", "drinks")+"");
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList listM=FXCollections.observableArrayList("Fishs","Gril","Meats","Fast Food");
            typeMO.setItems(listM);
        ObservableList listD=FXCollections.observableArrayList("Cold","Hot");
            typeDO.setItems(listD);
          

        
        
    }    
    
}
