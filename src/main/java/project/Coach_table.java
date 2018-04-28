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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author Konrad
 */
public class Coach_table extends javax.swing.JFrame {
    Connection con = null;
    String id_before_change;
    int selected_row_before_chage;
    MongoClient mongo = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongo.getDatabase("logs");  
    MongoCredential credential = MongoCredential.createCredential("myUserAdmin", "logs", "abc123".toCharArray()); 
    /**
     * Creates new form Gui
     */
    public Coach_table(String connectionUrl) {
        initComponents();
        try
        {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
            
        }
         SwingUtilities.updateComponentTreeUI(this);
        setLocationRelativeTo(null);
        String[] columnNames = {"Imie","Nazwisko","Plec","Data Urodzenia","E-mail","Klub"};
        CoachJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       // show_row();
        try{
        con = DriverManager.getConnection(connectionUrl);
        
         MongoCollection<Document> collection = database.getCollection("logs"); 
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         String data = dateFormat.format(date);
         Document document = new Document("Connection", "Successful") 
         
        
        .append("Date", data)
        .append("Parametrs",connectionUrl);
         
        collection.insertOne(document); 
         
        }catch(Exception e)
        {
         MongoCollection<Document> collection = database.getCollection("logs"); 
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         String data = dateFormat.format(date);
         Document document = new Document("Connection", "Failed") 
         
        
        .append("Date", data)
        .append("Parametrs",connectionUrl);
         
        collection.insertOne(document); 
        }
        CoachView show = new CoachView(con,CoachJTable);
        show.execute();
        
         Coach_count_view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         CoachCountView view = new CoachCountView(con,Coach_count_view);
         view.execute();
    }
    
   /* public void show_row()
    {
           DefaultTableModel model = (DefaultTableModel) CoachJTable.getModel();  
           ResultSet rs = Database_connect.get_Coach();
           try
           {
           while(rs.next())
           {
               
           model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
           
           }
           }catch(Exception e)
           {
               System.out.println("error with load coach - GUI");
           }
    }*/
    
   
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jDialog1 = new javax.swing.JDialog();
        button1 = new java.awt.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        CoachJTable = new javax.swing.JTable();
        Delete_Coach_Button = new javax.swing.JButton();
        add_Coach_Button = new javax.swing.JButton();
        Accept_Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BirthDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        FirstName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Surname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Sex = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        idCoach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Club = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        Change_Coach_Button = new javax.swing.JButton();
        Clear_button = new javax.swing.JButton();
        add_coach_procedure_button = new javax.swing.JButton();
        Delete_Coach_Procedure_button = new javax.swing.JButton();
        Accept_button_procedure = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Coach_count_view = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        button1.setLabel("button1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CoachJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id","Imie", "Nazwisko", "Plec","Data Urodzenia", "E-mail","Klub"
            }
        )
        {public boolean isCellEditable(int row, int column){return false;}}
    );
    CoachJTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jScrollPane3.setViewportView(CoachJTable);

    Delete_Coach_Button.setText("Kasuj trenera");
    Delete_Coach_Button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Delete_Coach_ButtonActionPerformed(evt);
        }
    });

    add_Coach_Button.setText("Dodaj nowego trenera");
    add_Coach_Button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            add_Coach_ButtonActionPerformed(evt);
        }
    });

    Accept_Button.setText("Zatwierdź zmiany");
    Accept_Button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Accept_ButtonActionPerformed(evt);
        }
    });

    jLabel1.setText("ID:");

    jLabel2.setText("Imie:");

    jLabel3.setText("Naziwsko:");

    jLabel4.setText("Plec:");

    jLabel5.setText("Data Urodzenia:");

    jLabel6.setText("E-mail:");

    jLabel7.setText("Klub:");

    Change_Coach_Button.setText("Zmień dane");
    Change_Coach_Button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Change_Coach_ButtonActionPerformed(evt);
        }
    });

    Clear_button.setText("Wyczyść");
    Clear_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Clear_buttonActionPerformed(evt);
        }
    });

    add_coach_procedure_button.setText("Dodaj nowego trenera (procedura)");
    add_coach_procedure_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            add_coach_procedure_buttonActionPerformed(evt);
        }
    });

    Delete_Coach_Procedure_button.setText("Kasuj trenera (procedura)");
    Delete_Coach_Procedure_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Delete_Coach_Procedure_buttonActionPerformed(evt);
        }
    });

    Accept_button_procedure.setText("Zatwierdź zmiany (procedura)");
    Accept_button_procedure.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Accept_button_procedureActionPerformed(evt);
        }
    });

    Coach_count_view.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
        },
        new String [] {
            "Klub", "Ilość trenerów"
        }
    ));
    jScrollPane1.setViewportView(Coach_count_view);

    jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    jLabel8.setText("Rekordy w bazie danych:");

    jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    jLabel9.setText("Ilośc trenerów w klubach:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(add_Coach_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(add_coach_procedure_button, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Delete_Coach_Procedure_button, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Delete_Coach_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Accept_Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Accept_button_procedure, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jLabel9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(idCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addGap(15, 15, 15))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(FirstName))
                    .addGap(24, 24, 24)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addComponent(Surname))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(jLabel4)
                            .addGap(13, 13, 13)
                            .addComponent(Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Club, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Change_Coach_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap(56, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(11, 11, 11)
            .addComponent(jLabel9)
            .addGap(6, 6, 6)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(11, 11, 11)
            .addComponent(jLabel8)
            .addGap(6, 6, 6)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addComponent(Sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(idCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addComponent(jLabel4))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGroup(layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Club, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Change_Coach_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))))
            .addGap(4, 4, 4)
            .addComponent(Clear_button)
            .addGap(9, 9, 9)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Delete_Coach_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Accept_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(add_Coach_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(6, 6, 6)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete_Coach_Procedure_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_coach_procedure_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(Accept_button_procedure, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(28, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Delete_Coach_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Coach_ButtonActionPerformed
        // TODO add your handling code here:
        
       Coach_Delete d = new Coach_Delete(con,CoachJTable,true,Coach_count_view);
       d.execute();
        
      
        
    }//GEN-LAST:event_Delete_Coach_ButtonActionPerformed

    private void add_Coach_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_Coach_ButtonActionPerformed
        
      String BirthData = BirthDate.getText();
      StringBuilder b = new StringBuilder();
      if(BirthData.length() == 8)
      {
          for(int i = 0;i<8;i++)
          {
          
          b.append(BirthData.charAt(i));
          if(i == 3 || i == 5) b.append('-');
          }
      }else b.append(BirthData);
      String[] values = new String[]{idCoach.getText(),FirstName.getText(),Surname.getText(),Sex.getText(),b.toString(),Email.getText(),Club.getText()};
      Coach_Add a = new Coach_Add(con,values,CoachJTable,true,Coach_count_view);
      a.execute();
        
        
        
        
        
    }//GEN-LAST:event_add_Coach_ButtonActionPerformed

    private void Change_Coach_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Change_Coach_ButtonActionPerformed
        
        
     idCoach.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),0).toString()); 
     id_before_change = CoachJTable.getValueAt(CoachJTable.getSelectedRow(),0).toString();
     selected_row_before_chage = CoachJTable.getSelectedRow();
     FirstName.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),1).toString());
     Surname.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),2).toString());
     Sex.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),3).toString());
     BirthDate.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),4).toString());
     Email.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),5).toString());
     Club.setText(CoachJTable.getValueAt(CoachJTable.getSelectedRow(),6).toString());
        
        
        
        
    }//GEN-LAST:event_Change_Coach_ButtonActionPerformed

    private void Clear_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_buttonActionPerformed
        // TODO add your handling code here:
        idCoach.setText(""); 
        FirstName.setText("");
        Surname.setText("");
        Sex.setText("");
        BirthDate.setText("");
        Email.setText("");
        Club.setText("");
        
        
    }//GEN-LAST:event_Clear_buttonActionPerformed

    private void Accept_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Accept_ButtonActionPerformed
        // TODO add your handling code here:
      String BirthData = BirthDate.getText();
      StringBuilder b = new StringBuilder();
      if(BirthData.length() == 8)
      {
          for(int i = 0;i<8;i++)
          {
          
          b.append(BirthData.charAt(i));
          if(i == 3 || i == 5) b.append('-');
          }
      }else b.append(BirthData);
      String[] values = new String[]{idCoach.getText(),FirstName.getText(),Surname.getText(),Sex.getText(),b.toString(),Email.getText(),Club.getText()};
      Coach_Change a = new Coach_Change(con,values,CoachJTable,id_before_change,selected_row_before_chage,true,Coach_count_view);
      a.execute();
        
        
    }//GEN-LAST:event_Accept_ButtonActionPerformed

    private void Delete_Coach_Procedure_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Coach_Procedure_buttonActionPerformed
        // TODO add your handling code here:
         Coach_Delete d = new Coach_Delete(con,CoachJTable,false,Coach_count_view);
         d.execute();
        
    }//GEN-LAST:event_Delete_Coach_Procedure_buttonActionPerformed

    private void Accept_button_procedureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Accept_button_procedureActionPerformed
        // TODO add your handling code here:
          String BirthData = BirthDate.getText();
      StringBuilder b = new StringBuilder();
      if(BirthData.length() == 8)
      {
          for(int i = 0;i<8;i++)
          {
          
          b.append(BirthData.charAt(i));
          if(i == 3 || i == 5) b.append('-');
          }
      }else b.append(BirthData);
      String[] values = new String[]{idCoach.getText(),FirstName.getText(),Surname.getText(),Sex.getText(),b.toString(),Email.getText(),Club.getText()};
      Coach_Change a = new Coach_Change(con,values,CoachJTable,id_before_change,selected_row_before_chage,false,Coach_count_view);
      a.execute();
        
    }//GEN-LAST:event_Accept_button_procedureActionPerformed

    private void add_coach_procedure_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_coach_procedure_buttonActionPerformed
        // TODO add your handling code here:
           
      String BirthData = BirthDate.getText();
      StringBuilder b = new StringBuilder();
      if(BirthData.length() == 8)
      {
          for(int i = 0;i<8;i++)
          {
          
          b.append(BirthData.charAt(i));
          if(i == 3 || i == 5) b.append('-');
          }
      }else b.append(BirthData);
      String[] values = new String[]{idCoach.getText(),FirstName.getText(),Surname.getText(),Sex.getText(),b.toString(),Email.getText(),Club.getText()};
      Coach_Add a = new Coach_Add(con,values,CoachJTable,false,Coach_count_view);
      a.execute();
    }//GEN-LAST:event_add_coach_procedure_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Coach_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Coach_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Coach_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Coach_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
        "databaseName=Tournaments;user=test;password=test";  
        try
        {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        
        
        }catch(Exception e)
        {
            System.out.print("Leci wyjatek");
        }
        
        
        
        
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Coach_table(connectionUrl).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Accept_Button;
    private javax.swing.JButton Accept_button_procedure;
    private javax.swing.JTextField BirthDate;
    private javax.swing.JButton Change_Coach_Button;
    private javax.swing.JButton Clear_button;
    private javax.swing.JTextField Club;
    private javax.swing.JTable CoachJTable;
    private javax.swing.JTable Coach_count_view;
    private javax.swing.JButton Delete_Coach_Button;
    private javax.swing.JButton Delete_Coach_Procedure_button;
    javax.swing.JTextField Email;
    private javax.swing.JTextField FirstName;
    private javax.swing.JTextField Sex;
    private javax.swing.JTextField Surname;
    private javax.swing.JButton add_Coach_Button;
    private javax.swing.JButton add_coach_procedure_button;
    private java.awt.Button button1;
    private javax.swing.JTextField idCoach;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}