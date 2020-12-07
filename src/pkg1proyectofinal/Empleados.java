
package pkg1proyectofinal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class Empleados extends javax.swing.JFrame {

   Connection con = null;
   Statement stmt = null;
   String titulos[] = {"Numero Empleado","Nombre","Telefono","Tipo Empleado"};
   String fila[] = new String [4];
   DefaultTableModel modelo;
   RegistrosEmp RE = new RegistrosEmp();
    TableRowSorter trs;
    
    
    public Empleados() {
        initComponents();
        this.setTitle("Consulta empleados");
        this.setLocation(200,100);
        this.setResizable(false);
        
        try {
            
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
               con = DriverManager.getConnection(url,usuario,contraseña);
               if (con!= null)
                   System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
               
               stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery("select* from empleados where num_emp != '"+"999999"+"'");
               
               modelo = new DefaultTableModel(null,titulos);
               
            
               while(rs.next()) {
                   
                   fila[0] = rs.getString("num_emp");
                   fila[1] = rs.getString("nom_emp");
                   fila[2] = rs.getString("tel_emp");
                   fila[3] = rs.getString("tipo_emp");
                   
                   
                   modelo.addRow(fila);     
               }
               tabla_emp.setModel(modelo);
                TableColumn ci = tabla_emp.getColumn("Numero Empleado");
                TableColumn cn = tabla_emp.getColumn("Nombre");
                TableColumn cd = tabla_emp.getColumn("Telefono");
                TableColumn ca = tabla_emp.getColumn("Tipo Empleado");                
               
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
        }
     
    }
    
        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_emp = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtnumemp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_emp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Num. Empleado", "Nombre", "Telefono", "Tipo Empleado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_empKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_emp);
        if (tabla_emp.getColumnModel().getColumnCount() > 0) {
            tabla_emp.getColumnModel().getColumn(0).setResizable(false);
            tabla_emp.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_emp.getColumnModel().getColumn(1).setResizable(false);
            tabla_emp.getColumnModel().getColumn(2).setResizable(false);
            tabla_emp.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1000, 400));

        jButton1.setText("Agregar");
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 410, 110, 30));

        jButton2.setText("Cerrar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 530, 110, 30));

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 490, 110, 30));

        refresh.setText("Refresh");
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1041, 10, 99, -1));

        jButton4.setText("Consultar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 450, 110, 30));

        jLabel2.setText("Filtro:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 30, 20));

        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 300, 30));

        jButton5.setText("Limpiar texto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 100, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLAS.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        txtnumemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumempActionPerformed(evt);
            }
        });
        getContentPane().add(txtnumemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 250, 100, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void borrar() {           
    int fila = tabla_emp.getSelectedRow()+1;
    System.out.println(fila);        
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_emp.getValueAt((fila-1), 0).toString();
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  stmt.executeUpdate("DELETE FROM empleados WHERE num_emp = '"+valor+"'"); 

                  System.out.println("El registro fue eliminado"); 
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
     javax.swing.JOptionPane.showMessageDialog(this,"El registro fue eliminado!","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
     refresh();
     } 
  
    }

public void refresh()
{
    try {
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
               con = DriverManager.getConnection(url,usuario,contraseña);
               if (con!= null)
                   System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
               
               stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery("select* from empleados where num_emp != '"+"999999"+"'");
               
               modelo = new DefaultTableModel(null,titulos);
            
               while(rs.next()) {
                   
                   fila[0] = rs.getString("num_emp");
                   fila[1] = rs.getString("nom_emp");
                   fila[2] = rs.getString("tel_emp");
                   fila[3] = rs.getString("tipo_emp");
                   
                   
                   modelo.addRow(fila);     
               }
               tabla_emp.setModel(modelo);
                TableColumn ci = tabla_emp.getColumn("Numero Empleado");
                TableColumn cn = tabla_emp.getColumn("Nombre");
                TableColumn cd = tabla_emp.getColumn("Telefono");
                TableColumn ca = tabla_emp.getColumn("Tipo Empleado");
                
               
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
        }
    
    trs = new TableRowSorter(modelo);
    tabla_emp.setRowSorter(trs);
    txtfiltro.setText(""); 
}

public void consultar()
{
       int fila = tabla_emp.getSelectedRow()+1;
    System.out.println(fila);
    
    
    
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_emp.getValueAt((fila-1), 0).toString();
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  ResultSet rs = stmt.executeQuery("select* from empleados where num_emp = '"+valor+"'");
                  
                  while(rs.next())
                  {
                      txtnumemp.setText(rs.getString("num_emp"));
                  }
                  
                  RegistrosEmp.txtnumeroemp.setText(txtnumemp.getText());
                  if(RE.isVisible()==true)
                    {
                    RE.dispose();
                    RE.setVisible(true);
                    RE.consulta();
                    }
                    else
                    {
                    RE.setVisible(true);
                    RE.consulta();
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
       
        if(RE.isVisible()==true)
        {
        RE.dispose();
        RE.setVisible(true);
        }
        else
        {
        RE.setVisible(true);
        }          
          
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        borrar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    borrar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
    refresh();
       
    }//GEN-LAST:event_refreshActionPerformed

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
    refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_refreshMouseClicked
    private void tabla_empKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_empKeyPressed
    int car = evt.getKeyCode();
        if(car==127)
        {
            borrar();
        }
    }//GEN-LAST:event_tabla_empKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    RE.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    consultar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtnumempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumempActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    RE.limpiar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped

    txtfiltro.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
          trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtfiltro.getText(), 0, 1));
            
        }
    });
    trs = new TableRowSorter(modelo);
    tabla_emp.setRowSorter(trs);
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    trs = new TableRowSorter(modelo);
    tabla_emp.setRowSorter(trs);
    txtfiltro.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JButton refresh;
    private javax.swing.JTable tabla_emp;
    public static javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtnumemp;
    // End of variables declaration//GEN-END:variables
}
