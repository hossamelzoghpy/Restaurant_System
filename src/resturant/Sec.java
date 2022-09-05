
package resturant;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class Sec {
   private  String user="root";
   private  String pass="";
   private  String url="jdbc:mysql://localhost/restaurant";
    public Connection conn() throws SQLException{
        Connection c=(Connection) DriverManager.getConnection(url,user,pass);
        return c;
    }
    /////////////////////////////////////count///////////////////////////////////
    public int count(String column,String table) throws SQLException{
        Connection con=conn();
        try {
           PreparedStatement ps=con.prepareStatement("select count("+column+")from("+table+")");
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
             return Integer.parseInt(rs.getString(1));   
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            
              con.close();  
     
            
        }

        
        
        return 0;
    }
    ///////////////////////////////////////Insert/////////////////////////////
    public boolean insert(String table,int id,String name,String type,float cost) throws SQLException{
        Connection con=conn();
        try {
             PreparedStatement ps=con.prepareStatement("insert into "+table+" Values('"+id+"','"+name+"','"+type+"','"+cost+"')");
           return ps.execute();
        } catch (SQLException e) {
            if(table.equals("meals")){
                JOptionPane.showMessageDialog(null, "Number Meal or name already exists ");
            }else{
                JOptionPane.showMessageDialog(null, "Number Drink or name already exists ");
        }
        }finally{
            
              con.close();  
     
            
        }
       
        return true;
    }
    public boolean insertSignup(int id,String user,String password) throws SQLException{
        Connection con=conn();
        try {
             PreparedStatement ps=con.prepareStatement("insert into users Values('"+id+"','"+user+"','"+password+"')");
           return ps.execute();
        } catch (SQLException e) {
            
        
        }finally{
            
              con.close();  
     
            
        }
       
        return true;
    }
    ///////////////////////////////////////////update//////////////////////////
     public boolean update(String table,String where,String name,String type,float cost) throws SQLException{
        Connection con=conn();
        String sql;
        if(table.equals("meals")){
            sql="UPDATE "+table+"SET nameMeal='"+name+"',typeMeal='"+type+"',costMeal='"+cost+"' "+where;
        }else{
            sql="UPDATE "+table+"SET nameDrink='"+name+"',typeDrink='"+type+"',costDrink='"+cost+"' "+where;

        }
         try {
             PreparedStatement ps=con.prepareStatement(sql);
             return ps.execute();///return false so not it///////////
         } catch (SQLException e) {
//          if(table.equals("meals")){
//                JOptionPane.showMessageDialog(null, "Number Meal or name already exists ");
//            }else{
//                JOptionPane.showMessageDialog(null, "Number Drink or name already exists ");
//                }
         }finally{
             con.close();
         }
        return true;
     }
     ///////////////////////////delete///////////////////////////////////////
    public boolean delete(String table,String where) throws SQLException{
        Connection con=conn();
        try {
            PreparedStatement ps=con.prepareStatement("delete from"+table+"where"+where);
            return ps.execute();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "noooooooooooooo ");
        }finally{
            con.close();
        }
        return true;
    }
    public ObservableList<drinks> getDrink() throws SQLException{
        Connection con=conn();
        ObservableList<drinks> list=FXCollections.observableArrayList();
        try {
            PreparedStatement ps=con.prepareStatement("SELECT * FROM drinks");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
list.add(new drinks(rs.getInt("numDrink"),rs.getString("nameDrink"), rs.getString("typeDrink"), rs.getFloat("costDrink")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            con.close();
        }
        return list;
    }
    public ObservableList<Meals> getMeal() throws SQLException{
        Connection con=conn();
        ObservableList<Meals> list=FXCollections.observableArrayList();
        try {
            PreparedStatement ps=con.prepareStatement("SELECT * FROM meals");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
list.add(new Meals(rs.getInt("numMeal"),rs.getString("nameMeal"), rs.getString("typeMeal"), rs.getFloat("costMeal")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            con.close();
        }
        return list;
    }
   
}
