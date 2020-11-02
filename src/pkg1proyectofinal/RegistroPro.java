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


public class RegistroPro extends javax.swing.JFrame {
    Connection con = null;
    Statement stmt =null;
    String titulos[] = {"Membresía","Nombre","Dirección","Teléfono"};
    String fila[] = new String[4];
    DefaultTableModel modelo;
    String var,var2;
  
    public RegistroPro() {
        initComponents();
        this.setTitle("Registro Productos");
        this.setLocation(400, 380);
        this.setResizable(false);
    }

   
    public void limpiar()
 
    {
        this.txtitempro.setText("");
        this.txtdescripcionpro.setText("");
        this.txtpreciopro.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtitempro = new javax.swing.JTextField();
        txtdescripcionpro = new javax.swing.JTextField();
        txtpreciopro = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ITEM");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 70, 20));

        jLabel2.setText("Descripcion ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 20));

        jLabel3.setText("PRECIO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 56, 20));

        txtitempro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtitemproActionPerformed(evt);
            }
        });
        txtitempro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtitemproKeyTyped(evt);
            }
        });
        getContentPane().add(txtitempro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 80, -1));

        txtdescripcionpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionproKeyTyped(evt);
            }
        });
        getContentPane().add(txtdescripcionpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 160, -1));

        txtpreciopro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioproKeyTyped(evt);
            }
        });
        getContentPane().add(txtpreciopro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 110, -1));

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 80, 30));

        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 80, 30));

        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 80, 30));

        jButton4.setText("Consultar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 80, 30));

        jButton5.setText("limpiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 80, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/REGISTROS.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtitemproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitemproKeyTyped
         char c = evt.getKeyChar();
        int numerocaracteres=7;

        if(txtitempro.getText().length() >= numerocaracteres)
        {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Solo se pueden 7 digitos en el ITEM");
        }

        if((c<'0' || c> '9')) evt.consume();
    }//GEN-LAST:event_txtitemproKeyTyped

    private void txtprecioproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioproKeyTyped
         char c = evt.getKeyChar();
         if((c<'0' || c> '9') && (c < '.' || c> '.')) evt.consume();  // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioproKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    registrar();    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    actualizar();     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    consulta();        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    limpiar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtitemproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtitemproActionPerformed
        
    }//GEN-LAST:event_txtitemproActionPerformed

    private void txtdescripcionproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionproKeyTyped
       char c = evt.getKeyChar();
        int numerocaracteres=45;

        if(txtdescripcionpro.getText().length() >= numerocaracteres)
        {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Solo se pueden 45 digitos en la descripcion");
        }
        if((c<'a' || c>'z') && (c<'A' || c> 'Z') && (c<' ' || c> ' ') && (c<'0' || c> '9')) evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionproKeyTyped
 public void registrar(){
        String cadena2, cadena3, cadena4;
        cadena2 = txtitempro.getText();
        cadena3 = txtdescripcionpro.getText();
        cadena4 = txtpreciopro.getText();
        if(txtitempro.getText().equals("")|txtdescripcionpro.getText().equals("")|txtpreciopro.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar todos los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtdescripcionpro.requestFocus();
        }
        else{
            try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha establecido una conexión con la base de datos"+
                    "\n"+url);
                
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select* from productos");
                
                if(rs.next()==false)
                {
                        stmt.executeUpdate("INSERT INTO productos(`item`, `desc_pro`, `precio_pro`) VALUES('"+cadena2+"','"+cadena3+"','"+cadena4+"')");
                        System.out.println("Los valores han sido agregados a la base de datos");
                        javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    rs = stmt.executeQuery("Select* from productos");
                if(rs.next()==true)
                {
                    rs=stmt.executeQuery("Select* from productos where item='"+cadena2+"'");
                    if(rs.next()==true)
                    {
                        javax.swing.JOptionPane.showMessageDialog(this,"Ya se registro este numero!\n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        stmt.executeUpdate("INSERT INTO productos(`item`, `desc_pro`, `precio_pro`) VALUES('"+cadena2+"','"+cadena3+"','"+cadena4+"')");
                        System.out.println("Los valores han sido agregados a la base de datos");
                        javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                }
                
            }catch(InstantiationException|IllegalAccessException|ClassNotFoundException|SQLException ex){
            Logger.getLogger(RegistrosCli.class.getName()).log(Level.SEVERE, null, ex);
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
        this.txtitempro.setText("");
        this.txtdescripcionpro.setText("");
        this.txtpreciopro.setText("");
        Productos.refresh.doClick();
    }
    
    public void consulta(){
        String cap ="";
        ResultSet rs = null;
        var2 = txtitempro.getText();
        String sql2 = "Select item,desc_pro,precio_pro FROM productos where item = '"+var2+"'";
        try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
            
            con=DriverManager.getConnection(url,usuario,contraseña);
            if(con != null)
                System.out.println("Se ha establicido una conexión a la base de datos"+"\n"+url);
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql2);
            
            int i=1;
            if(!rs.next())
            {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontro ningun registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            rs = stmt.executeQuery(sql2);
            while(rs.next()){
                String iite =rs.getString("item");
                String ides =rs.getString("desc_pro");
                String ipre =rs.getString("precio_pro");
                System.out.println("Sitio web"+(i++)+"\n"
                +iite+"\n"
                +ides+"\n"
                +ipre+"\n"
                );
                txtitempro.setText(iite);
                txtdescripcionpro.setText(ides);
                txtpreciopro.setText(ipre);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(InstantiationException|IllegalAccessException|ClassNotFoundException ex){
            Logger.getLogger(RegistrosCli.class.getName()).log(Level.SEVERE,null,ex);
        }
        finally{
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(con != null){
                try{
                    con.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
}
    
    public void actualizar(){
        String cadena1,cadena2,cadena3;
        cadena1 = txtitempro.getText();
        cadena2 = txtdescripcionpro.getText();
        cadena3 = txtpreciopro.getText();
        
        if(txtitempro.getText().equals("")|txtdescripcionpro.getText().equals("")|txtpreciopro.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"1.-Consulte el item del producto\n 2.-Actualice el dato deseado en el campo correspondiente","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con != null)
                    System.out.println("Se ha establedido una conexión a la base de datos"+"\n"+url);
                
                stmt = con.createStatement();
                stmt.executeUpdate("update ignore productos set item = '"+cadena1+"',desc_pro='"+cadena2+"',precio_pro='"+cadena3+"'where item='"+txtitempro.getText()+"'");
                
                System.out.println("Los valores han sido actualizados");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            Logger.getLogger(RegistrosCli.class.getName()).log(Level.SEVERE,null,ex);    
            }
            finally{
                if(con!=null){
                    try{
                        con.close();
                        stmt.close();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            javax.swing.JOptionPane.showMessageDialog(this,"Actualizado correctamente!","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }   
        this.txtitempro.setText("");
        this.txtdescripcionpro.setText("");
        this.txtpreciopro.setText("");
        Productos.refresh.doClick();
    }
   
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
            java.util.logging.Logger.getLogger(RegistroPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPro().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtdescripcionpro;
    public static javax.swing.JTextField txtitempro;
    private javax.swing.JTextField txtpreciopro;
    // End of variables declaration//GEN-END:variables
}
