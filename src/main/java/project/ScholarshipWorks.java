/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Konrad
 */
class ScholarshipView extends SwingWorker<Void, Object[]>{

    private Connection con;
    private JTable JTable;
    private Scholarship_table T;
    public ScholarshipView(Connection con,JTable CoachJTable,Scholarship_table T)
    {
        this.con = con;
        this.JTable = CoachJTable;
        this.T = T;
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        String updateString = null;
        Statement select = null;
        ResultSet rs = null;
        try
        {
            updateString = "SELECT * FROM Scholarship";
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
               
                publish(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
           
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
            T.idAchievment.addItem((String)row[1]);
        }
       // model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
    }
}
class Scholarship_Add extends SwingWorker<Void, Integer>
    {
    private String[] values;
    private Connection con;
    private JTable JTable;
    Scholarship_Add(Connection con,String[] values,JTable JTable)
    {
        this.values = values;
        this.con = con;
        this.JTable = JTable;
    }

    @Override
    protected Void doInBackground() throws Exception {
        
            try
            {
                
            
            if (values[0].length() == 0) throw new Exception();
            // Pobranie idZawodnika ktoremu nalezy zwiekszyc ogolna kwote stypendium
            PreparedStatement s2 = con.prepareStatement("SELECT Competitor FROM Achievement WHERE idAchievment = ?");
            s2.setString(1, values[1]);
            ResultSet rs2 = s2.executeQuery();
            String idCompetitor;
            if(rs2.next())
            {
            
            idCompetitor = rs2.getString(1);
            
            }else throw new Exception("Blad przy SELECT FROM Achievement");
            
            PreparedStatement s3 = con.prepareStatement("SELECT all_scholarship,timestample FROM Competitor WHERE idCompetitor = ?");
            
            s3.setString(1, idCompetitor);
            ResultSet rs3 = s3.executeQuery();
            int all_scholarship = 0;
            String timestample = "";
            if(rs3.next())
            { 
                all_scholarship = rs3.getInt(1);
                timestample = rs3.getString(2);
            }
            
            all_scholarship += Double.parseDouble(values[3]);
            
            con.setAutoCommit(false);
            PreparedStatement s1 = con.prepareStatement("INSERT INTO Scholarship VALUES (?,?,?,?)");
            s1.setString(1,(values[0]));
            s1.setString(2, values[1]);
            s1.setString(3, values[2]);
            s1.setString(4, values[3]);
            s1.executeUpdate();
            
            PreparedStatement s4 = con.prepareStatement("UPDATE Competitor SET all_scholarship = ?,timestample = ? WHERE idCompetitor = ? AND timestample = ?");
            s4.setInt(1, all_scholarship);
            s4.setString(3, idCompetitor);
            s4.setString(4, timestample);
            timestample = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());
            s4.setString(2, timestample);
            s4.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            publish();
            }
            catch(SQLException e)
            {  
                message m = new message(e.getMessage());
                m.setVisible(true);
                con.rollback();
            }
            catch(Exception e)
            {
                 message m = new message("Nieprawidlowe Dane Trenera");
                 m.setVisible(true);
                 con.rollback();
            }
        
         
         
        return null;
    }
        @Override
         protected void process(List<Integer> chunks)
         {
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();  
            Object[] data_for_Jtable = {values[0],values[1],values[2],values[3]};
            model.addRow(data_for_Jtable);
            int rows_count = JTable.getRowCount();
            JTable.setRowSelectionInterval(rows_count-1, rows_count-1);
             
         }
    
}