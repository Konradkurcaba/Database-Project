/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Konrad
 */
class CompetitionView extends SwingWorker<Void, Object[]>{

    private Connection con;
    private JTable JTable;
    private HashMap Map;
    private Competition_table T;
    
    public CompetitionView(Connection con,JTable JTable,HashMap Map,Competition_table T)
    {
        this.con = con;
        this.JTable = JTable;
        this.Map = Map;
        this.T = T;
        
        
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        String updateString = null;
        Statement select = null;
        ResultSet rs = null;
        try
        {
            updateString = "select Competition.idCompetition,Competition.idTournaments,Competition.CompetitionName,Tournament.Name\n" +
            " FROM Competition JOIN Tournament ON Competition.idTournaments = Tournament.idTournament";
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
            if(!Map.containsKey(row[3]))
            {
               Map.put(row[3], row[1]);
               T.Tournament_combo.addItem((String)row[3]); 
            }
             
            
        }
       // model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
    }
}