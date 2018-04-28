/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Konrad
 */
class AchievementView extends SwingWorker<Void, Object[]>{

    private Connection con;
    private JTable JTable;
    private Achievement_table T;
   
    public AchievementView(Connection con,JTable CoachJTable,Achievement_table T)
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
            updateString = "SELECT Achievement.idAchievment,Achievement.idCompetition,Achievement.Competitor,\n" +
"            Competitor.FirstName,Competitor.Surname,(Tournament.Name + ' '+Competition.CompetitionName) AS Nazwa \n" +
"            FROM\n" +
"            Achievement JOIN Competitor ON Achievement.Competitor = Competitor.idCompetitor\n" +
"            JOIN Competition ON Achievement.idCompetition = Competition.idCompetition\n" +
"			JOIN Tournament ON Competition.idTournaments = Tournament.idTournament";
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
               
                publish(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
           
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
            T.idCompetitior.addItem((String)row[2]);
            T.idCompetiton.addItem((String)row[1]);
            
        }
       // model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
    }
}
