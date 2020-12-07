package pkg1proyectofinal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


public class Productos extends javax.swing.JFrame {
Connection con = null;
    Statement stmt = null;
    String titulos[] = {"ITEM","Descripcion","PRECIO"};
    String fila[] = new String[3];
    DefaultTableModel modelo;
    RegistroPro RP = new RegistroPro();
    TableRowSorter trs;
   
    
    public Productos() {
        initComponents();
        this.setTitle("Productos");
        this.setLocation(200,100);
        this.setResizable(false);
         try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
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
                TableColumn cd = tabla_pro.getColumn("PRECIO");
             
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    
    }
   public void borrar() {           
    int fila = tabla_pro.getSelectedRow()+1;
    System.out.println(fila);        
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_pro.getValueAt((fila-1), 0).toString();
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
                  stmt.executeUpdate("DELETE FROM productos WHERE item = '"+valor+"'"); 

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
  try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
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
                TableColumn cd = tabla_pro.getColumn("PRECIO");
             
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    trs = new TableRowSorter(modelo);
    tabla_pro.setRowSorter(trs);
    txtfiltro.setText("");
    }
public void consultar()
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
                  ResultSet rs = stmt.executeQuery("select* from productos where item = '"+valor+"'");
                  
                  while(rs.next())
                  {
                      txtitemloc.setText(rs.getString("item"));
                  }
                  
                  RegistroPro.txtitempro.setText(txtitemloc.getText());
                  if(RP.isVisible()==true)
                    {
                    RP.dispose();
                    RP.setVisible(true);
                    RP.consulta();
                    }
                    else
                    {
                    RP.setVisible(true);
                    RP.consulta();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pro = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtitemloc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 540, 100, 30));

        tabla_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ITEM", "Descripcion", "Precio "
            }
        ));
        tabla_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_proKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_pro);
        if (tabla_pro.getColumnModel().getColumnCount() > 0) {
            tabla_pro.getColumnModel().getColumn(0).setPreferredWidth(2);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 162, 990, 400));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 420, 98, 30));

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 98, 29));

        jButton3.setText("Consultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 460, 98, 30));

        jButton4.setText("Borrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 500, 98, 30));
        getContentPane().add(txtitemloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 150, 98, -1));

        jLabel2.setText("Filtro:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));

        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 290, 30));

        jButton5.setText("Limpiar texto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLAS.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     if(RP.isVisible()==true)
        {
        RP.dispose();
        RP.limpiar();
        RP.setVisible(true);
        }
        else
        {
        RP.setVisible(true);
        RP.limpiar();
        }     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    consultar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    borrar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
    refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    RP.dispose();        // TODO add your handling code here:
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    trs = new TableRowSorter(modelo);
    tabla_pro.setRowSorter(trs);
    txtfiltro.setText("");        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabla_proKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_proKeyPressed
        int car = evt.getKeyCode();
        if(car==127)
        {
            borrar();
        }       
    }//GEN-LAST:event_tabla_proKeyPressed

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
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
    private javax.swing.JTable tabla_pro;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtitemloc;
    // End of variables declaration//GEN-END:variables
}
