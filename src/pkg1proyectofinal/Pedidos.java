
package pkg1proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Pedidos extends javax.swing.JFrame {

    Connection con = null;
    Statement stmt = null;
    String titulos[] = {"ID Ped","Nombre Cliente","Ejecutivo","Fecha Entrega", "Estado", "Total"};
    String fila[] = new String[6];
    DefaultTableModel modelo;
    newPed_Reg RP = new newPed_Reg();  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   
    public Pedidos() {
        initComponents();
        this.setTitle("Pedidos");
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
                ResultSet rs = stmt.executeQuery("Select* from vista_pedidos");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("id_ped");
                    fila[1]=rs.getString("nom_cli");
                    fila[2]=rs.getString("nom_emp");
                    fila[3]=rs.getString("DATE_FORMAT(fecha_ped,'%d/%m/%Y')");
                    fila[4]=rs.getString("status_ped");
                    fila[5]=rs.getString("precio_ped");
                    
                    modelo.addRow(fila);
                    
                }
                tabla_ped.setModel(modelo);
                TableColumn cm = tabla_ped.getColumn("ID Ped");
                TableColumn cn = tabla_ped.getColumn("Nombre Cliente");
                TableColumn cd = tabla_ped.getColumn("Ejecutivo");
                TableColumn ct = tabla_ped.getColumn("Fecha Entrega");
                TableColumn ca = tabla_ped.getColumn("Estado");
                TableColumn cq = tabla_ped.getColumn("Total");
                
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
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
                ResultSet rs = stmt.executeQuery("Select* from vista_pedidos");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("id_ped");
                    fila[1]=rs.getString("nom_cli");
                    fila[2]=rs.getString("nom_emp");
                    fila[3]=rs.getString("DATE_FORMAT(fecha_ped,'%d/%m/%Y')");
                    fila[4]=rs.getString("status_ped");
                    fila[5]=rs.getString("precio_ped");
                    
                    modelo.addRow(fila);
                    
                }
                tabla_ped.setModel(modelo);
                TableColumn cm = tabla_ped.getColumn("ID Ped");
                TableColumn cn = tabla_ped.getColumn("Nombre Cliente");
                TableColumn cd = tabla_ped.getColumn("Ejecutivo");
                TableColumn ct = tabla_ped.getColumn("Fecha Entrega");
                TableColumn ca = tabla_ped.getColumn("Estado");
                TableColumn cq = tabla_ped.getColumn("Total");
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
    
   }
    
    public void consultar() throws ParseException
    {
         int fila = tabla_ped.getSelectedRow()+1;
        System.out.println(fila);         
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }        
    else if (fila>=0) {
     
     try { 
         String valor = tabla_ped.getValueAt((fila-1), 0).toString();
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
                  ResultSet rs = stmt.executeQuery("select id_ped, clientes_idclientes, DATE_FORMAT(fecha_ped,'%d/%m/%Y'), empleados_num_emp from pedidos where id_ped = '"+valor+"'");
                  
                  while(rs.next())
                  {
                      txtidpedloc.setText(rs.getString("id_ped"));
                      txtmemloc.setText(rs.getString("clientes_idclientes"));
                      txtfechaloc.setText(rs.getString("DATE_FORMAT(fecha_ped,'%d/%m/%Y')"));
                      txtemploc.setText(rs.getString("empleados_num_emp"));
                  }
                  stmt = con.createStatement(); 
                  rs = stmt.executeQuery( "SELECT nom_emp FROM vista_pedidos WHERE id_ped = '"+valor+"'");
          
                  
                  newPed_Reg.txtid_num.setText(txtidpedloc.getText());
                  newPed_Reg.txtmemloc.setText(txtmemloc.getText());
                  newPed_Reg.txtemploc.setText(txtemploc.getText());
                  newPed_Reg.fechatemp.setText(txtfechaloc.getText());

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
    this.txtidpedloc.setText("");
    this.txtmemloc.setText("");    }
    
    
    
    public void eliminar()
    {
        {
       int fila = tabla_ped.getSelectedRow()+1;
    System.out.println(fila);        
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_ped.getValueAt((fila-1), 0).toString();
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
                  stmt.executeUpdate("DELETE FROM pedidos WHERE id_ped= '"+valor+"'"); 
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
    }
    
    
    
           public void registrarNuevoPedido()
     {
            try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha establecido una conexión con la base de datos"+
                    "\n"+url);
                
                      
                        stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select* from pedidos");
                            stmt.executeUpdate("INSERT INTO pedidos(`precio_ped`,`fecha_ped`,`empleados_num_emp`,`clientes_idclientes`, `status_ped`,`costover`) VALUES ('"+"NULL"+"', '"+"2020-12-31"+"', '"+txtemploc.getText()+"', '"+"-1"+"','"+"Pendiente"+"','"+"no"+"')");
                            System.out.println("Los valores han sido agregados a la base de datos");              
                
            }catch(InstantiationException|IllegalAccessException|ClassNotFoundException|SQLException ex){
        }
            finally{
                if(con != null){
                    try{
                        con.close();
                        stmt.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        
        
     }
           
            public void VerificarCosto()
     {
            try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha establecido una conexión con la base de datos"+
                    "\n"+url);
                        stmt = con.createStatement();
                            stmt.executeUpdate("CALL verificar_costos");
                            System.out.println("VALORES VERIFICADOS");              
                
            }catch(InstantiationException|IllegalAccessException|ClassNotFoundException|SQLException ex){
        }
            finally{
                if(con != null){
                    try{
                        con.close();
                        stmt.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        
        
     }
            
            public void generarreporte()
     {
            try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha establecido una conexión con la base de datos"+
                    "\n"+url);
                        stmt = con.createStatement();
                            stmt.executeUpdate("CALL reporte_pedidos");
                            System.out.println("Reporte generado");              
                
            }catch(InstantiationException|IllegalAccessException|ClassNotFoundException|SQLException ex){
        }
            finally{
                if(con != null){
                    try{
                        con.close();
                        stmt.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        
        
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_ped = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        txtemploc = new javax.swing.JTextField();
        refreshtotal = new javax.swing.JButton();
        gnrreporte = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtidpedloc = new javax.swing.JTextField();
        txtmemloc = new javax.swing.JTextField();
        txtfechaloc = new javax.swing.JTextField();
        Eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Regresar");
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 490, 109, 30));

        tabla_ped.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N. Pedido", "Negocio", "Total", "Fecha de Entrega"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_ped.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_pedMouseClicked(evt);
            }
        });
        tabla_ped.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_pedKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_ped);
        if (tabla_ped.getColumnModel().getColumnCount() > 0) {
            tabla_ped.getColumnModel().getColumn(0).setPreferredWidth(2);
            tabla_ped.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 153, 970, 410));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 370, 109, 30));
        getContentPane().add(txtemploc, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 83, -1));

        refreshtotal.setText("Actualizar Costo");
        refreshtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshtotalActionPerformed(evt);
            }
        });
        getContentPane().add(refreshtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 120, 40));

        gnrreporte.setText("Generar Reporte");
        gnrreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gnrreporteActionPerformed(evt);
            }
        });
        getContentPane().add(gnrreporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 130, 40));

        jButton3.setText("Consultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 410, 110, 30));
        getContentPane().add(txtidpedloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 50, -1));
        getContentPane().add(txtmemloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 50, -1));
        getContentPane().add(txtfechaloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 50, -1));

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        getContentPane().add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 450, 110, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLAS.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 100, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed

    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    dispose();
      
    }//GEN-LAST:event_jButton1MouseClicked

    private void tabla_pedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pedMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_pedMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            String cadena3 = txtemploc.getText();
            registrarNuevoPedido();
            refresh();
       if(RP.isVisible()==true)
        {
            RP.dispose();
            RP.setVisible(true);
            RP.refresh1();
            RP.refresh2();
            RP.limpiar();
            newPed_Reg.txtemploc.setText(cadena3);
        }
        else
        {
            RP.setVisible(true);
            RP.refresh1();
            RP.refresh2();
            RP.limpiar();
            newPed_Reg.txtemploc.setText(cadena3);
        }   
   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
    refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {        
            consultar();
        } catch (ParseException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        eliminar();        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarActionPerformed

    private void tabla_pedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_pedKeyPressed
int car = evt.getKeyCode();
        if(car==127)
        {
            eliminar();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_pedKeyPressed

    private void refreshtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshtotalActionPerformed
    VerificarCosto();
    refresh();
    
    // TODO add your handling code here:
    }//GEN-LAST:event_refreshtotalActionPerformed

    private void gnrreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gnrreporteActionPerformed
    generarreporte();
        // TODO add your handling code here:
    }//GEN-LAST:event_gnrreporteActionPerformed

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
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    public static javax.swing.JButton gnrreporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JButton refresh;
    public static javax.swing.JButton refreshtotal;
    private javax.swing.JTable tabla_ped;
    public static javax.swing.JTextField txtemploc;
    private javax.swing.JTextField txtfechaloc;
    private javax.swing.JTextField txtidpedloc;
    private javax.swing.JTextField txtmemloc;
    // End of variables declaration//GEN-END:variables
}
