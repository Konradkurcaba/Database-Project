/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Konrad
 */

    
class CompetitorView extends SwingWorker<Void, Object[]>{

    private Connection con;
    private JTable JTable;
   
    public CompetitorView(Connection con,JTable CoachJTable)
    {
        this.con = con;
        this.JTable = CoachJTable;
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        String updateString = null;
        Statement select = null;
        ResultSet rs = null;
        try
        {
            updateString = "SELECT idCompetitor,Competitor.idCoach,Competitor.FirstName,Competitor.Surname,Competitor.Sex,Competitor.BirthDate,Competitor.Email,\n" +
            "(Coach.FirstName + ' ' + Coach.Surname) as Coach,all_scholarship\n" +
            "\n" +
            "FROM Competitor\n" +
            "JOIN Coach on Competitor.idCoach = Coach.idCoach";
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
               
                publish(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)});
           
            }
        }catch(Exception e)
           {
               System.out.println("error when acces to row");
           }
        return null;
    }
    @Override
    protected void process(List<Object[]> chunks)
    {   DefaultTableModel model = (DefaultTableModel) JTable.getModel();  
        for(Object[] row : chunks)
        {
            
            model.addRow(row);
        }
       // model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
    }
}
    
class get_Coach extends SwingWorker<Void, Object[]>
{
    Competitor_table Frame;
    Connection con;
    ArrayList<String[]> List;
    get_Coach(Competitor_table Frame,Connection con,ArrayList List)
    {
        this.con = con;
        this.Frame = Frame;
        this.List = List;
            
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
        }catch(Exception e)
        {
            System.out.println("Error, cant execute SELECT FROM Coach");
        }
            
        try
        {
           while(rs.next())
            {
                String[] value = {rs.getString(1),rs.getString(2),rs.getString(3)};
                List.add(value);
           
            }
        }catch(Exception e)
           {
               System.out.println(e.getMessage());
               
           }
        return null;
    }

    @Override
    protected void done() {
        for(Object[] i : List)
        {
        Frame.jComboBox_Coach.addItem(i[1]+" "+i[2]);
        }
    }
    
}
    
class Competitor_Delete extends SwingWorker<Void, Integer>
{
    
    
    private Connection con;
    private JTable CompetitorJTable;
    int selected_row_number;
    
    public Competitor_Delete(Connection con,JTable CoachJTable)
     
    {
        selected_row_number = CoachJTable.getSelectedRow();
        this.con = con;
        this.CompetitorJTable = CoachJTable;
        
        
        
    }
    @Override
    protected Void doInBackground() throws Exception {
   
    try
    {
            
        int id = Integer.parseInt(CompetitorJTable.getValueAt(selected_row_number,0).toString()); 
        PreparedStatement s = con.prepareStatement("DELETE FROM Competitor WHERE idCompetitor = ?");
        s.setInt(1, id);
        s.executeUpdate();
        publish(selected_row_number);
        
    }
    catch(SQLException e)
         {  
             message m = new message(e.getMessage());
             m.setVisible(true);
             
         }
    catch(Exception e)
         {
              message m = new message("Blad Wewnetrzny");
              m.setVisible(true);
         }
    
    return null;
    }
        
    @Override
    protected void process(List<Integer> chunks)
    {
        DefaultTableModel model = (DefaultTableModel) CompetitorJTable.getModel();
        for(Integer i : chunks)
        {
            model.removeRow(i);
           
            int rows_count = CompetitorJTable.getRowCount();
            if(i>= rows_count-1)
            {   
                CompetitorJTable.setRowSelectionInterval(rows_count-1, rows_count-1);
            }
            if(i< rows_count-1)
            {
                CompetitorJTable.setRowSelectionInterval(i, i);
            }
            
        }
        
       
        
     //   CoachJTable.setRowSelectionInterval(1, 1);
    }
      
    }
    class Competitor_Add extends SwingWorker<Void, Integer>
    {
    private String[] values;
    private Connection con;
    private JTable JTable;
    Competitor_Add(Connection con,String[] values,JTable CoachJTable)
    {
        this.values = values;
        this.con = con;
        this.JTable = CoachJTable;
    }

    @Override
    protected Void doInBackground() throws Exception {
        
            try
            {
            if (values[0].length() == 0) throw new Exception();
            PreparedStatement s = con.prepareStatement("INSERT INTO Competitor VALUES (?,?,?,?,?,?,?)");
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
        
         
         
        return null;
    }
        @Override
         protected void process(List<Integer> chunks)
         {
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();  
            Object[] data_for_Jtable = {values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7]};
            model.addRow(data_for_Jtable);
            int rows_count = JTable.getRowCount();
            JTable.setRowSelectionInterval(rows_count-1, rows_count-1);
             
         }
    
}

    
    
class Competitor_Change extends SwingWorker<Void,Integer>
{
    
    
    private String[] values;
    private Connection con;
    private JTable CoachJTable;
    private int selected_row_number;
    private String old_id ;
  
    
    Competitor_Change(Connection con,String[] values,JTable CoachJTable,String old_id,int selected_row_before_change)
    {
        
        selected_row_number = selected_row_before_change;
        this.values = values;
        this.con = con;
        this.CoachJTable = CoachJTable;
        this.old_id = old_id;
        
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
       
        try
        {
        if (values[0].length() == 0) throw new Exception();
        PreparedStatement s = con.prepareStatement("UPDATE Competitor SET idCompetitor = ?,idCoach = ?,"
                + "FirstName = ?,Surname = ?,Sex = ?, BirthDate = ?,Email = ?"
                + " WHERE idCompetitor = ?"); 
        s.setString(1,(values[0]));
        s.setString(2, values[1]);
        s.setString(3, values[2]);
        s.setString(4, values[3]);
        s.setString(5, values[4]);
        s.setString(6, values[5]);
        s.setString(7, values[6]);
        s.setString(8, old_id);
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
        
       
     
        return null;
    }
    
@Override
protected void process(List<Integer> chunks)
{
    
    DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel(); 
    
    for(int i = 0;i<8;i++)
    {
        model.setValueAt(values[i], selected_row_number, i);
    }
    CoachJTable.setRowSelectionInterval(selected_row_number, selected_row_number);    
    
    
    
}
    
    
}
    
