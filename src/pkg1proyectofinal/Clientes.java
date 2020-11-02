package pkg1proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jes_c
 */
public class Clientes extends javax.swing.JFrame {
    Connection con = null;
    Statement stmt = null;
    String titulos[] = {"Num Cliente","Nombre","Dirección","Teléfono"};
    String fila[] = new String[4];
    DefaultTableModel modelo;
    RegistrosCli RC = new RegistrosCli();
    
    /**
     * Creates new form Clientes
     */
    public Clientes() {
        initComponents();
        this.setTitle("Socios");
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
                ResultSet rs = stmt.executeQuery("Select* from clientes");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("mem_cli");
                    fila[1]=rs.getString("nom_cli");
                    fila[2]=rs.getString("dir_cli");
                    fila[3]=rs.getString("tel_cli");
                    
                    modelo.addRow(fila);
                    
                }
                tabla_cli.setModel(modelo);
                TableColumn cm = tabla_cli.getColumn("Num Cliente");
                TableColumn cn = tabla_cli.getColumn("Nombre");
                TableColumn cd = tabla_cli.getColumn("Dirección");
                TableColumn ct = tabla_cli.getColumn("Teléfono");
                
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_cli = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtnumcli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Cerrar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 520, 99, 30));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 398, 100, 30));

        tabla_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Membresia", "Nombre", "Direccion", "Telefono"
            }
        ));
        tabla_cli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_cliKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_cli);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 1020, 430));

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 480, 99, 30));

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 99, 29));

        jButton4.setText("Consulta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 440, 100, 30));
        getContentPane().add(txtnumcli, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 260, 80, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLAS.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void borrar(){
        Clientes Cl = new Clientes();
        int fila = tabla_cli.getSelectedRow()+1;
        System.out.println(fila);
        
        if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        
        else if(fila>=1){
            String valor = tabla_cli.getValueAt((fila-1), 0).toString();
            try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866";  
            
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                
                if(con != null)
                    System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
                stmt = con.createStatement();
                stmt.executeUpdate("DELETE FROM clientes WHERE mem_cli = '"+valor+"'");
                
                System.out.println("El registro fue eliminado");
                
            }
            catch ( SQLException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE,null,ex);
            }
            finally{
                if(con != null){
                    try{
                        con.close();
                        stmt.close();
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            javax.swing.JOptionPane.showMessageDialog(this, "El registro fue eliminado!","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            refresh();
        }
      
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
                ResultSet rs = stmt.executeQuery("Select* from clientes");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("mem_cli");
                    fila[1]=rs.getString("nom_cli");
                    fila[2]=rs.getString("dir_cli");
                    fila[3]=rs.getString("tel_cli");
                    
                    modelo.addRow(fila);
                    
                }
                tabla_cli.setModel(modelo);
                TableColumn cm = tabla_cli.getColumn("Num Cliente");
                TableColumn cn = tabla_cli.getColumn("Nombre");
                TableColumn cd = tabla_cli.getColumn("Dirección");
                TableColumn ct = tabla_cli.getColumn("Teléfono");
            }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    }

public void consultar()
{
       int fila = tabla_cli.getSelectedRow()+1;
        System.out.println(fila);
    
    
    
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_cli.getValueAt((fila-1), 0).toString();
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866";  
            
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  ResultSet rs = stmt.executeQuery("select* from clientes where mem_cli = '"+valor+"'");
                  
                  while(rs.next())
                  {
                      txtnumcli.setText(rs.getString("mem_cli"));
                  }
                  
                  RegistrosCli.txtmembresiacli.setText(txtnumcli.getText());
                  if(RC.isVisible()==true)
                    {
                    RC.dispose();
                    RC.setVisible(true);
                    RC.consulta();
                    }
                    else
                    {
                    RC.setVisible(true);
                    RC.consulta();
                    } 
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
    
     } 
}
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(RC.isVisible()==true)
        {
        RC.dispose();
        RC.limpiar();
        RC.setVisible(true);
        }
        else
        {
        RC.limpiar();
        RC.setVisible(true);
        }      
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         borrar();    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        refresh();
    }//GEN-LAST:event_refreshActionPerformed

    private void tabla_cliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_cliKeyPressed
        int car = evt.getKeyCode();
        if(car==127)
        {
            borrar();
        }        
    }//GEN-LAST:event_tabla_cliKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      consultar();  // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    RC.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JButton refresh;
    private javax.swing.JTable tabla_cli;
    public static javax.swing.JTextField txtnumcli;
    // End of variables declaration//GEN-END:variables
}