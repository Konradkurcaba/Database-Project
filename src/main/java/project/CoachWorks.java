/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
/**
 *
 * @author Konrad
 */
class CoachView extends SwingWorker<Void, Object[]>{

    private Connection con;
    private JTable CoachJTable;
    MongoClient mongo = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongo.getDatabase("logs");  
    MongoCredential credential = MongoCredential.createCredential("myUserAdmin", "logs", "abc123".toCharArray()); 
    public CoachView(Connection con,JTable CoachJTable)
    {
        this.con = con;
        this.CoachJTable = CoachJTable;
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        String updateString = null;
        Statement select = null;
        ResultSet rs = null;
        try
        {
            updateString = "SELECT * FROM Coach";
           
            select = con.createStatement();
            rs = select.executeQuery(updateString);
            
             MongoCollection<Document> collection = database.getCollection("logs"); 
             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String data = dateFormat.format(date);
            Document document = new Document("SELECT", "Successful") 
            .append("Table", "Coach")
            .append("Date", data);
         
        collection.insertOne(document); 
        }catch(Exception e)
        {
            System.out.println("Error, cant execute SELECT FROM Coach");
              
             MongoCollection<Document> collection = database.getCollection("logs"); 
             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String data = dateFormat.format(date);
            Document document = new Document("SELECT", "Failed") 
            .append("Table", "Coach")
            .append("Date", data);
         
        collection.insertOne(document); 
        }
            
        try
        {
           while(rs.next())
            {
               
                publish(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
           
            }
        }catch(Exception e)
           {
               System.out.println("error when acces to row");
           }
        return null;
    }
    @Override
    protected void process(List<Object[]> chunks)
    {   DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel();  
        for(Object[] row : chunks)
        {
            
            model.addRow(row);
        }
       // model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
    }
}
    
    
    
    
class CoachCountView extends SwingWorker<Void, Object[]>{

    private Connection con;
    private JTable CoachJTable;
   
    public CoachCountView(Connection con,JTable CoachJTable)
    {
        this.con = con;
        this.CoachJTable = CoachJTable;
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        String updateString = null;
        Statement select = null;
        ResultSet rs = null;
        try
        {
            updateString = "SELECT Club, FirstName as Ilość_trenerów FROM V_Club";
            select = con.createStatement();
            rs = select.executeQuery(updateString);
        }catch(Exception e)
        {
            System.out.println("Error, cant execute SELECT FROM Coach");
        }
            
        try
        {
           while(rs.next())
            {
               
                publish(new Object[]{rs.getString(1),rs.getString(2)});
           
            }
        }catch(Exception e)
           {
               System.out.println("error when acces to row");
           }
        return null;
    }
    @Override
    protected void process(List<Object[]> chunks)
    {   DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel(); 
        model.setRowCount(0);
        for(Object[] row : chunks)
        {
            
            model.addRow(row);
        }
       // model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
    }
    
    
    
    
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

class Coach_Delete extends SwingWorker<Void, Integer>
{
    
    private  boolean insertSQL;
    private Connection con;
    private JTable CoachJTable;
    int selected_row_number;
    JTable CoachCountView;
    public Coach_Delete(Connection con,JTable CoachJTable,boolean insertSQL,JTable CoachCountView)
     
    {
        selected_row_number = CoachJTable.getSelectedRow();
        this.con = con;
        this.CoachJTable = CoachJTable;
        this.insertSQL = insertSQL;
        this.CoachCountView = CoachCountView;
        
    }
    @Override
    protected Void doInBackground() throws Exception {
    MongoClient mongo = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongo.getDatabase("logs");  
    MongoCredential credential = MongoCredential.createCredential("myUserAdmin", "logs", "abc123".toCharArray()); 
    if(insertSQL == true)    
    {
    try
    {
            
        int id = Integer.parseInt(CoachJTable.getValueAt(selected_row_number,0).toString()); 
        PreparedStatement s = con.prepareStatement("DELETE FROM Coach WHERE idCoach = ?");
        s.setInt(1, id);
        s.executeUpdate();
        publish(selected_row_number);
        MongoCollection<Document> collection = database.getCollection("logs"); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
        Document document = new Document("DELETE", "Succesful") 
        .append("Table", "Coach")
        .append("DeletedID", id)
        .append("Date", data);
         
            collection.insertOne(document); 
        
    }
    catch(SQLException e)
         {  
             message m = new message(e.getMessage());
             m.setVisible(true);
             
                
             MongoCollection<Document> collection = database.getCollection("logs"); 
             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
             Date date = new Date();
             String data = dateFormat.format(date);
             Document document = new Document("DELETE", "Failed") 
             .append("Table", "Coach")
             .append("Message", e.getMessage())
             .append("Date", data);
         
             collection.insertOne(document); 
             
         }
    catch(Exception e)
         {
              message m = new message("Blad Wewnetrzny");
              m.setVisible(true);
         }
    }else
    {
        int id = Integer.parseInt(CoachJTable.getValueAt(selected_row_number,0).toString()); 
        CallableStatement cStmt = con.prepareCall("{call delete_coach(?)}");
        cStmt.setInt(1, id);
        try
        {
        cStmt.execute();
        }  catch(SQLException e)
         {  
             message m = new message(e.getMessage());
             m.setVisible(true);
             
         }
    catch(Exception e)
         {
              message m = new message("Blad Wewnetrzny");
              m.setVisible(true);
         }
        
        publish(selected_row_number);
        CoachCountView view = new CoachCountView(con,CoachCountView);
        view.execute();
        
    
    }
    return null;
    }
        
    @Override
    protected void process(List<Integer> chunks)
    {
        DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel();
        for(Integer i : chunks)
        {
            model.removeRow(i);
           
            int rows_count = CoachJTable.getRowCount();
            if(i>= rows_count-1) CoachJTable.setRowSelectionInterval(0, rows_count-1);
            if(i< rows_count-1) CoachJTable.setRowSelectionInterval(0, i);
            
        }
        
       
        
     //   CoachJTable.setRowSelectionInterval(1, 1);
    }
      
    }
    
class Coach_Add extends SwingWorker<Void, Integer>
{
    private String[] values;
    private Connection con;
    private JTable CoachJTable;
    private boolean AddSQL;
    private JTable CoachCountView;
    Coach_Add(Connection con,String[] values,JTable CoachJTable,boolean AddSQL,JTable CoachCountView)
    {
        this.values = values;
        this.con = con;
        this.CoachJTable = CoachJTable;
        this.AddSQL = AddSQL;
        this.CoachCountView = CoachCountView;
    }

    @Override
    protected Void doInBackground() throws Exception {
         
            MongoClient mongo = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongo.getDatabase("logs");  
            MongoCredential credential = MongoCredential.createCredential("myUserAdmin", "logs", "abc123".toCharArray()); 
            
         if(AddSQL == true)
         {
            try
            {
            if (values[0].length() == 0) throw new Exception();
            PreparedStatement s = con.prepareStatement("INSERT INTO Coach VALUES (?,?,?,?,?,?,?)");
            s.setString(1,(values[0]));
            s.setString(2, values[1]);
            s.setString(3, values[2]);
            s.setString(4, values[3]);
            s.setString(5, values[4]);
            s.setString(6, values[5]);
            s.setString(7, values[6]);
            s.executeUpdate();
            
            publish();
           
            
            
             MongoCollection<Document> collection = database.getCollection("logs"); 
             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String data = dateFormat.format(date);
            Document document = new Document("INSERT", "Succesful") 
            .append("Table", "Coach")
            .append("InsertedID", (String)values[0])
            .append("Date", data);
         
            collection.insertOne(document); 
            }
            catch(SQLException e)
            {  
                message m = new message(e.getMessage());
                m.setVisible(true);
                MongoCollection<Document> collection = database.getCollection("logs"); 
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String data = dateFormat.format(date);
                Document document = new Document("INSERT", "Failed") 
                .append("Table", "Coach")
                .append("Message", e.getMessage())
                .append("Date", data);
         
                collection.insertOne(document); 

            }
            catch(Exception e)
            {
                 message m = new message("Nieprawidlowe Dane Trenera");
                 m.setVisible(true);
            }
         }else
         {
                  try
            {
            if (values[0].length() == 0) throw new Exception();
            CallableStatement s = con.prepareCall("{call add_coach(?,?,?,?,?,?,?)}");
            s.setString(1,(values[0]));
            s.setString(2, values[1]);
            s.setString(3, values[2]);
            s.setString(4, values[3]);
            s.setString(5, values[4]);
            s.setString(6, values[5]);
            s.setString(7, values[6]);
            s.executeUpdate();

            publish();

            }
            catch(SQLException e)
            {  
                message m = new message(e.getMessage());
                m.setVisible(true);

            }
            catch(Exception e)
            {
                 message m = new message("Nieprawidlowe Dane Trenera");
                 m.setVisible(true);
            }
         }
         CoachCountView view = new CoachCountView(con,CoachCountView);
         view.execute();
         
        return null;
    }
        @Override
         protected void process(List<Integer> chunks)
         {
            DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel();  
           
            model.addRow((Object[]) values);
            int rows_count = CoachJTable.getRowCount();
            CoachJTable.setRowSelectionInterval(0, rows_count-1);
             
         }
    
}



class Coach_Change extends SwingWorker<Void,Integer>
{
    
    
    private String[] values;
    private Connection con;
    private JTable CoachJTable;
    private int selected_row_number;
    private String old_id ;
    private boolean ChangeSQL;
    private JTable CoachCountView;
    
    Coach_Change(Connection con,String[] values,JTable CoachJTable,String old_id,int selected_row_before_change,boolean ChangeSQL,JTable CoachCountView)
    {
        
        selected_row_number = selected_row_before_change;
        this.values = values;
        this.con = con;
        this.CoachJTable = CoachJTable;
        this.old_id = old_id;
        this.ChangeSQL = ChangeSQL;
        this.CoachCountView = CoachCountView;
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        if(ChangeSQL == true)
        {
        try
        {
        if (values[0].length() == 0) throw new Exception();
        PreparedStatement s = con.prepareStatement("UPDATE Coach SET idCoach = ?,"
                + "FirstName = ?,Surname = ?,Sex = ?, BirthDate = ?,Email = ?,"
                + "Club = ? WHERE idCoach = ?"); 
        s.setString(1,(values[0]));
        s.setString(2, values[1]);
        s.setString(3, values[2]);
        s.setString(4, values[3]);
        s.setString(5, values[4]);
        s.setString(6, values[5]);
        s.setString(7, values[6]);
        s.setString(8, old_id);
        
        s.executeUpdate();
        con.commit();
        
         
        }
        catch(SQLException e)
        {  
            message m = new message(e.getMessage());
            m.setVisible(true);
             
        }
         catch(Exception e)
        {
            message m = new message("Nieprawidlowe Dane Trenera");
            m.setVisible(true);
        }
        
        }else
        {
              try
              {
                  
              if (values[0].length() == 0) throw new Exception();
              CallableStatement s= con.prepareCall("{call change_coach(?,?,?,?,?,?,?,?)}");
              s.setString(1,(values[0]));
              s.setString(2, values[1]);
              s.setString(3, values[2]);
              s.setString(4, values[3]);
              s.setString(5, values[4]);
              s.setString(6, values[5]);
              s.setString(7, values[6]);
              s.setString(8, old_id);
              
              s.execute();
              publish();
              
              }catch(SQLException e)
              {
                    message m = new message(e.getMessage());
                    m.setVisible(true);
              }catch(Exception e)
              {
                    message m = new message(e.getMessage());
                    m.setVisible(true);
              }
              
            
            
        }
        CoachCountView view = new CoachCountView(con,CoachCountView);
        view.execute();
        return null;
    }
    
@Override
protected void process(List<Integer> chunks)
{
    
    DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel(); 
    
    for(int i = 0;i<7;i++)
    {
        model.setValueAt(values[i], selected_row_number, i);
    }
    CoachJTable.setRowSelectionInterval(0, selected_row_number);    
    
    
    
}
    
    
}