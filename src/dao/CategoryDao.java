/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import cafe.management.DB_Operations;
import javax.swing.JOptionPane;
import model.Category;
import java.sql.*;
import java.util.*;
/**
 *
 * @author rakes
 */
public class CategoryDao {
    
    public static void save(Category category){
        String query = "insert into category(name) values('"+category.getName()+"')";
        DB_Operations.setDataOrDelete(query, "Category added Successfully");
    }
    
    public static ArrayList<Category> getAllRecords(){
        ArrayList<Category> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DB_Operations.getData("select * from category");
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString(("name")));
                arrayList.add(category);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return arrayList;
    }
    
    public static void delete(String id){
        String query = "delete from category where id='"+id+"'";
        DB_Operations.setDataOrDelete(query, "Category Deleted Successfully");
    }
}
