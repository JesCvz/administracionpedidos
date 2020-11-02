
package pkg1proyectofinal;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;

public class TABLAPRO extends javax.swing.JFrame {
    Connection con = null;
    Statement stmt = null;
    String titulos[] = {"ITEM","Descripcion","PRECIO"};
    String fila[] = new String[3];
    DefaultTableModel modelo;
    RegistroPro RP = new RegistroPro();
    TableRowSorter trs;
    
    public TABLAPRO() {
       
        initComponents();
        this.setTitle("Productos");
        this.setLocation(200, 100);
        this.setResizable(false);
         try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha estableciso una conexion con la base de datos"+"\n"+url);
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select* from productos");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("item");
                    fila[1]=rs.getString("desc_pro");
                    fila[2]=rs.getString("precio_pro");
                    modelo.addRow(fila);
                    
                }
                tabla_pro.setModel(modelo);
                TableColumn cm = tabla_pro.getColumn("ITEM");
                TableColumn cn = tabla_pro.getColumn("Descripcion");
                TableColumn ca = tabla_pro.getColumn("PRECIO");
                
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    }

    public void agregar()
    {
          
        int fila = tabla_pro.getSelectedRow()+1;
        System.out.println(fila);
    
    
    
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_pro.getValueAt((fila-1), 0).toString();
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();  
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  ResultSet rs = stmt.executeQuery("select* from productos where item = '"+valor+"'");
                  
                  while(rs.next())
                  {
                      txtitemloc.setText(rs.getString("item"));
                      txtdescloc.setText(rs.getString("desc_pro"));
                      txtprecioloc.setText(rs.getString("precio_pro"));
                  }
                    RegistrosPed.txtitemped.setText(txtitemloc.getText());
                    RegistrosPed.txtdescped.setText(txtdescloc.getText());
                    RegistrosPed.txtprecioped.setText(txtprecioloc.getText());
                    } 
                  catch( SQLException e ) { 
                      e.printStackTrace(); 
                  } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RegistrosEmp.class.getName()).log(Level.SEVERE, null, ex);
        } 
  
              finally { 
                  if ( con != null ) { 
                      try    { 
                          con.close(); 
                          stmt.close(); 
                      } catch( SQLException e ) { 
                          System.out.println( e.getMessage()); 
                      } 
                  } 
     }
                        RegistrosPed.agregar.doClick();
        
     
     } 
        txtitemloc.setText("");
        txtdescloc.setText("");
        txtprecioloc.setText("");
    
    }
    
        public void refresh()
    {
        try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha estableciso una conexion con la base de datos"+"\n"+url);
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select* from productos");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("item");
                    fila[1]=rs.getString("desc_pro");
                    fila[2]=rs.getString("precio_pro");
                    modelo.addRow(fila);
                    
                }
                tabla_pro.setModel(modelo);
                TableColumn cm = tabla_pro.getColumn("ITEM");
                TableColumn cn = tabla_pro.getColumn("Descripcion");
                TableColumn ca = tabla_pro.getColumn("PRECIO");
                
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pro = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtitemloc = new javax.swing.JTextField();
        txtdescloc = new javax.swing.JTextField();
        txtprecioloc = new javax.swing.JTextField();
        txtfiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ITEM", "Producto", "PRECIO"
            }
        ));
        jScrollPane1.setViewportView(tabla_pro);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 375, 275));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));
        getContentPane().add(txtitemloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 40, -1));
        getContentPane().add(txtdescloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 40, -1));
        getContentPane().add(txtprecioloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 40, -1));

        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 290, 20));

        jLabel1.setText("Filtrar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLACHICA.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
       txtfiltro.addKeyListener(new KeyAdapter() {
           @Override
           public void keyReleased(KeyEvent e) {
               trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtfiltro.getText(), 0, 1));
           }
           
           
       });
       
       trs = new TableRowSorter(modelo);
    tabla_pro.setRowSorter(trs);
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TABLAPRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TABLAPRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TABLAPRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TABLAPRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TABLAPRO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_pro;
    private javax.swing.JTextField txtdescloc;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtitemloc;
    private javax.swing.JTextField txtprecioloc;
    // End of variables declaration//GEN-END:variables
}
