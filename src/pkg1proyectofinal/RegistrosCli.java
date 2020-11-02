
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


public class RegistrosCli extends javax.swing.JFrame {
    Connection con = null;
    Statement stmt =null;
    String titulos[] = {"Membresía","Nombre","Dirección","Teléfono"};
    String fila[] = new String[4];
    DefaultTableModel modelo;
    String var,var2;
    /**
     * Creates new form RegistrosCli
     */
    public RegistrosCli() {
        initComponents();
        this.setTitle("Registro clientes");
        this.setLocation(400,380);
    }

  
    public void limpiar()
    {
        this.txtmembresiacli.setText("");
        this.txtnombrecli.setText("");
        this.txtdireccioncli.setText("");
        this.txttelefonocli.setText("");     
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txttelefonocli = new javax.swing.JTextField();
        txtmembresiacli = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdireccioncli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtnombrecli = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Telefono");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 56, 20));

        txttelefonocli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttelefonocliMouseClicked(evt);
            }
        });
        txttelefonocli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonocliKeyTyped(evt);
            }
        });
        getContentPane().add(txttelefonocli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 112, -1));

        txtmembresiacli.setEditable(false);
        txtmembresiacli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmembresiacliKeyTyped(evt);
            }
        });
        getContentPane().add(txtmembresiacli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 140, 30));

        jLabel7.setText("Dirección");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));

        txtdireccioncli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccioncliKeyTyped(evt);
            }
        });
        getContentPane().add(txtdireccioncli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 250, -1));

        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        jLabel1.setText("N. Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        txtnombrecli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombrecliActionPerformed(evt);
            }
        });
        txtnombrecli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrecliKeyTyped(evt);
            }
        });
        getContentPane().add(txtnombrecli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 259, -1));

        jButton1.setText("Consultar");
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jButton5.setText("Actualizar");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jButton6.setText("Registrar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 244, -1, -1));

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, -1, -1));

        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 75, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/REGISTROSSOCIOS.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttelefonocliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttelefonocliMouseClicked

    }//GEN-LAST:event_txttelefonocliMouseClicked

    private void txttelefonocliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonocliKeyTyped
        char c = evt.getKeyChar();
        int numerocaracteres=10;

        if(txttelefonocli.getText().length() >= numerocaracteres)
        {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Solo se pueden 10 digitos en el telefono");
        }

        if((c<'0' || c> '9')) evt.consume();
    }//GEN-LAST:event_txttelefonocliKeyTyped

    private void txtmembresiacliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmembresiacliKeyTyped
        char c = evt.getKeyChar();
        int numerocaracteres=12;

        if(txtmembresiacli.getText().length() >= numerocaracteres)
        {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Solo se pueden 12 digitos en la membresía");
        }

        if((c<'0' || c> '9')) evt.consume();
    }//GEN-LAST:event_txtmembresiacliKeyTyped

    private void txtdireccioncliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccioncliKeyTyped
        char c = evt.getKeyChar();
        int numerocaracteres=45;

        if(txtdireccioncli.getText().length() >= numerocaracteres)
        {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Solo se pueden 45 digitos en la direccion");
        }
    }//GEN-LAST:event_txtdireccioncliKeyTyped

    private void txtnombrecliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombrecliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrecliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        registrar();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MousePressed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        actualizar();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        consulta();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtnombrecliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrecliKeyTyped
         char c = evt.getKeyChar();
        int numerocaracteres=45;

        if(txtnombrecli.getText().length() >= numerocaracteres)
        {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Solo se pueden 45 digitos en el nombre");
        }
        if((c<'a' || c>'z') && (c<'A' || c> 'Z') && (c<' ' || c> ' ') ) evt.consume();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrecliKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void registrar(){
        String cadena2, cadena3,cadena4,cadena5;
        cadena2 = txtmembresiacli.getText();
        cadena3 = txtnombrecli.getText();
        cadena4 = txtdireccioncli.getText();
        cadena5 = txttelefonocli.getText();
        if(txtnombrecli.getText().equals("")|txtdireccioncli.getText().equals("")|txttelefonocli.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar todos los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtnombrecli.requestFocus();
        }
        else{
            try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
             con = DriverManager.getConnection(url,usuario,contraseña); 
             if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  ResultSet rs = stmt.executeQuery("select* from clientes");
                  if(rs.next()==false)
                  {
                      stmt.executeUpdate("INSERT INTO clientes(`nom_cli`, `dir_cli`, `tel_cli`) VALUES('"+cadena3+"','"+cadena4+"','"+cadena5+"')");
                      System.out.println("Los valores han sido agregados a la base de datos");
                      javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                  }
                  
                  else
                  {
                    rs = stmt.executeQuery("select* from clientes");
                    if (rs.next()==true)
                    {
                        rs = stmt.executeQuery("select* from clientes where mem_cli = '"+cadena2+"'");
                        if(rs.next()==true)
                        {
                            javax.swing.JOptionPane.showMessageDialog(this,"Ya se registro este numero! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                              stmt.executeUpdate("INSERT INTO clientes(`nom_cli`, `dir_cli`, `tel_cli`) VALUES('"+cadena3+"','"+cadena4+"','"+cadena5+"')");
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
        this.txtmembresiacli.setText("");
        this.txtnombrecli.setText("");
        this.txtdireccioncli.setText("");
        this.txttelefonocli.setText("");
        Clientes.refresh.doClick();
    }
    public void consulta(){
        String cap="";
        ResultSet rs = null;
        var2 = txtmembresiacli.getText();
        String sql2 = "Select mem_cli, nom_cli,dir_cli,tel_cli  FROM clientes where mem_cli = '"+var2+"'";
        
        try{
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
        
        con = DriverManager.getConnection(url,usuario,contraseña);
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
                String id = rs.getString("mem_cli");
                String ino = rs.getString("nom_cli");
                String idir = rs.getString("dir_cli");
                String itel = rs.getString("tel_cli");
                System.out.println("Sitio Web"+(i++)+"\n"
                +id+"\n"
                +ino+"\n"
                +idir+"\n"
                +itel+"\n"
                );
                
                txtmembresiacli.setText(id);
                txtnombrecli.setText(ino);
                txtdireccioncli.setText(idir);
                txttelefonocli.setText(itel);
               
                
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(InstantiationException|IllegalAccessException|ClassNotFoundException ex){
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
        String cadena1,cadena2,cadena3,cadena4;
        cadena1 = txtmembresiacli.getText();
        cadena2 = txtnombrecli.getText();
        cadena3 = txtdireccioncli.getText();
        cadena4 = txttelefonocli.getText();
        
        if(txtmembresiacli.getText().equals("")|txtnombrecli.getText().equals("")|txtdireccioncli.getText().equals("")|txttelefonocli.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"1.-Consulte la membresía del cliente\n 2.-Actualice el dato deseado en el campo correspondiente","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
                stmt.executeUpdate("update ignore clientes set mem_cli = '"+cadena1+"',nom_cli='"+cadena2+"',dir_cli='"+cadena3+"', tel_cli='"+cadena4+"'where mem_cli='"+txtmembresiacli.getText()+"'");
                
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
        this.txtmembresiacli.setText("");
        this.txtnombrecli.setText("");
        this.txtdireccioncli.setText("");
        this.txttelefonocli.setText("");
        Clientes.refresh.doClick();
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
            java.util.logging.Logger.getLogger(RegistrosCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrosCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrosCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrosCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrosCli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtdireccioncli;
    public static javax.swing.JTextField txtmembresiacli;
    private javax.swing.JTextField txtnombrecli;
    private javax.swing.JTextField txttelefonocli;
    // End of variables declaration//GEN-END:variables
}
