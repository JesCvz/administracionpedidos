package pkg1proyectofinal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

public class newPed_Reg extends javax.swing.JFrame {
    Connection con = null;
    Statement stmt =null;
    TABLACLI TC = new TABLACLI();
    TABLAPRO TP = new TABLAPRO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String titulos[] = {"ITEM","Descripcion","PRECIO","Cantidad","Descuento","Subtotal"};
    String fila[] = new String[6];
    DefaultTableModel modelo;
    TableRowSorter trs;
    
    
    public newPed_Reg() {
   initComponents();
          int contar = tabla_cot.getRowCount();
          int suma=0;
        this.setTitle("Cotizar Pedido");
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
               ResultSet rs = stmt.executeQuery("SELECT id_ped FROM pedidos WHERE id_ped = (SELECT MAX(id_ped) FROM pedidos)");
              
          }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }  
        
    }
 public void refresh1()
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
               ResultSet rs = stmt.executeQuery("SELECT id_ped FROM pedidos WHERE id_ped = (SELECT MAX(id_ped) FROM pedidos)");
                        while(rs.next())
                        {
                            String id = rs.getString("id_ped"); 
                            txtid_num.setText(id);
                        }
          }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
             txtfechaped.setDate(null);
        }
  public void refresh2()
        {
            float suma=0;
             try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha estableciso una conexion con la base de datos para refresh2"+"\n"+url);
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from vista_productos_pedidos where id_ped = '"+txtid_num.getText()+"'");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("item");
                    fila[1]=rs.getString("desc_pro");
                    fila[2]=rs.getString("precio_pro");
                    fila[3]=rs.getString("cantidad_pro");
                    fila[4]=rs.getString("descuento_pro");
                    fila[5]=rs.getString("subtotal");
                    
                    modelo.addRow(fila);
                    
                }
                tabla_cot.setModel(modelo);
                TableColumn cm = tabla_cot.getColumn("ITEM");
                TableColumn cn = tabla_cot.getColumn("Descripcion");
                TableColumn ca = tabla_cot.getColumn("PRECIO");
                TableColumn cc = tabla_cot.getColumn("Cantidad");
                TableColumn cd = tabla_cot.getColumn("Descuento");
                TableColumn cv = tabla_cot.getColumn("Subtotal");
             
              for(int i=0;i<tabla_cot.getRowCount();i++)
             {
                 suma = suma + Float.parseFloat(tabla_cot.getValueAt(i, 5).toString());
             }
             
             txtsumaef.setText(Float.toString(suma));
             txtsumatc.setText(Float.toString((float) (suma*1.023)));
             txtsumach.setText(Float.toString((float) (suma*1.0155)));
         }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
            
        }
  
   public void consulta() throws ParseException
        {
            
            consultarmem();
            consultarfecha();
            consultar();
            refresh2();
        }
   
   public void consultar () //Consultar el estado del pedido
        {
            String cap="";
        ResultSet rs = null;
        String var2 = txtid_num.getText();
        String sql2 = "Select status_ped FROM pedidos where id_ped = '"+var2+"'";
         System.out.println(var2);
        try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance(); 
        
        con = DriverManager.getConnection(url,usuario,contraseña);
        if(con != null)
            System.out.println("Se ha establicido una conexión a la base de datos"+"\n"+url);
        
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql2);
        
        int i=1;

            while(rs.next()){
              
                String isp = rs.getString("status_ped");
                txtstatusped.setSelectedItem(isp);
                }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(InstantiationException|IllegalAccessException|ClassNotFoundException ex){
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
  public void consultarfecha() throws ParseException
   {
        String sDate1= fechatemp.getText();  
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
        txtfechaped.setDate(date1);
        System.out.println(sDate1+"\t"+date1);  
       
   }
    
    public void consultarmem(){ //Consulta de la memmbresia
        String cap="";
        ResultSet rs = null;
        String var2 = txtmemloc.getText();
        String sql2 = "Select * FROM clientes where idclientes = '"+var2+"'";
         System.out.println(var2);
        try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña; 
                    Class.forName(LG.driver).newInstance(); 
        
        con = DriverManager.getConnection(url,usuario,contraseña);
        if(con != null)
            System.out.println("Se ha establicido una conexión a la base de datos"+"\n"+url);
        
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql2);
        
        int i=1;

            while(rs.next()){
                String id = rs.getString("idclientes");
                String ino = rs.getString("nom_cli");
                System.out.println("Sitio Web"+(i++)+"\n"
                +id+"\n"
                +ino+"\n"
                );
                txtmemloc.setText(id);
                txtnomloc.setText(ino);

               
                
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(InstantiationException|IllegalAccessException|ClassNotFoundException ex){
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
     
    
    public void confirmardatos(){
        try { 
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
                  ResultSet rs = stmt.executeQuery("select* from pedidos where id_ped = '"+txtid_num.getText()+"'");
              
                  stmt.executeUpdate("update pedidos set fecha_ped = '"+sdf.format(txtfechaped.getDate())+"',status_ped = '"+txtstatusped.getSelectedItem().toString()+"', empleados_num_emp = '"+txtemploc.getText()+"', clientes_idclientes = '"+txtmemloc.getText()+"' where id_ped = '"+txtid_num.getText()+"'"); 
                  System.out.println("Los valores han sido Actualizados"); 
                  javax.swing.JOptionPane.showMessageDialog(this,"Actualizado correctamente!","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
                      } catch( Exception e ) { 
                          System.out.println( e.getMessage()); 
                      } 
                  } 
     }
     Pedidos.refresh.doClick();
     } 
    
    public void agregar()
    {
         String cadena2, cadena3, cadena4;
         if((txtitemped.getText().equals("") )||(txtfechaped.getDate() == null) )
         {
             javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar primero los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
         }
         else{
        cadena2 = txtitemped.getText();
        cadena3 = txtdescped.getText();
        cadena4 = txtprecioped.getText();

        System.out.println(sdf.format(txtfechaped.getDate()));
        if(txtmemloc.getText().equals("")|txtnomloc.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar primero los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
         
        else{
            try{
                     Login LG = new Login();
                    String url = LG.url;
                    String usuario = LG.usuario;
                    String contraseña = LG.contraseña;
                    Class.forName(LG.driver).newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Estoy hasta la madre "+
                    "\n"+url);
                
                      
                        stmt = con.createStatement();
                                                //id_num,item, desc_pro, precio_pro, id, cantidad, descuentoS
                        stmt.executeUpdate("INSERT INTO pedidos_has_productos(`pedidos_id_ped`, `productos_item`,`cantidad_pro`, `descuento_pro`, `desc_aplicado`) VALUES('"+txtid_num.getText()+"','"+txtitemped.getText()+"','"+1+"','"+0+"','"+"no"+"')");
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
        this.txtitemped.setText("");
        this.txtdescped.setText("");
        this.txtprecioped.setText("");
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                        refresh2();
                    }
            };

      timer.schedule(tarea, 100);
    }
         }
    
    
    
    public void registrar()
     {
         String cadena7;

        cadena7 = txtsumaef.getText();
        
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
                        stmt.executeUpdate("UPDATE pedidos SET `precio_ped` =('"+cadena7+"'),`costover` = ('"+"no"+"') WHERE id_ped= ('"+txtid_num.getText()+"')");
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
        
        Pedidos.refresh.doClick();
        
     
         }
    
    public void eliminar()
   {
       int fila = tabla_cot.getSelectedRow()+1;
    System.out.println(fila);        
    if(fila==0)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de seleccionar un registro","AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_cot.getValueAt((fila-1), 0).toString();
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
                  stmt.executeUpdate("DELETE FROM pedidos_has_productos WHERE pedidos_id_ped = '"+txtid_num.getText()+"' AND productos_item = '"+valor+"'"); 

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
     } 
    
    refresh2();
   }
     
    
     public void actualizaritems()
    {
          int fila = tabla_cot.getSelectedRow()+1;
    System.out.println(fila);        
    if(fila==0)
        {
            System.out.print("");
        }
    
    
    else if (fila>=0) {
     
     try { 
         String valor = tabla_cot.getValueAt((fila-1), 0).toString();
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
                  stmt.executeUpdate("UPDATE pedidos_has_productos SET descuento_pro = '"+tabla_cot.getValueAt((fila-1), 4)+"' , cantidad_pro= '"+tabla_cot.getValueAt((fila-1), 3)+"'  WHERE pedidos_id_ped = '"+txtid_num.getText()+"' AND productos_item= '"+valor+"'"); 
                  System.out.println("El item fue actualizado con exito");                 
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
    refresh2();
    }
     
      public void aplicardescuento()
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
                            stmt.executeUpdate("CALL aplicar_descuentos");
                            System.out.println("Descuentos disponibles aplicados");              
                
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

    
     public void limpiar()
    {
        this.txtmemloc.setText("");
        this.txtnomloc.setText("");
        this.txtfechaped.setDate(null);
        this.txtstatusped.setSelectedItem("Pendiente");

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtmemloc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtfechaped = new com.toedter.calendar.JDateChooser();
        txtsumach = new javax.swing.JTextField();
        txtsumaef = new javax.swing.JTextField();
        txtsumatc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gnrreporte = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_cot = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtnomloc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtemploc = new javax.swing.JTextField();
        txtid_num = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtstatusped = new javax.swing.JComboBox<>();
        refresh = new javax.swing.JButton();
        txtitemped = new javax.swing.JTextField();
        txtdescped = new javax.swing.JTextField();
        txtprecioped = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        fechatemp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtmemloc.setEditable(false);
        txtmemloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmemlocActionPerformed(evt);
            }
        });
        getContentPane().add(txtmemloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 40, 30));

        jLabel1.setText("Num Cliente:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 80, 20));

        jLabel2.setText("Fecha de entrega:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, 20));

        txtfechaped.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(txtfechaped, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 160, 30));

        txtsumach.setEditable(false);
        getContentPane().add(txtsumach, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 410, 100, 30));

        txtsumaef.setEditable(false);
        getContentPane().add(txtsumaef, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 310, 100, 30));

        txtsumatc.setEditable(false);
        getContentPane().add(txtsumatc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 360, 100, -1));

        jLabel5.setText("Total con T/Credito:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 340, 120, 20));

        jLabel6.setText("Total con cheque:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 390, 110, 20));

        jLabel4.setText("Total Efectivo:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, 100, 20));

        gnrreporte.setText("Agregar Articulos");
        gnrreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gnrreporteActionPerformed(evt);
            }
        });
        getContentPane().add(gnrreporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 120, -1));

        jButton6.setText("Eliminar Articulos");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 240, 120, 30));

        jButton2.setText("Agregar Articulos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 120, -1));

        tabla_cot.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_cot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_cotKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_cot);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 1000, 340));

        jButton5.setText("Aplicar Descuentos");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 470, 120, 30));

        jLabel3.setText("Nombre del negocio:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, 20));

        txtnomloc.setEditable(false);
        getContentPane().add(txtnomloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 230, -1));

        jLabel7.setText("Filtrar:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 80, 20));

        txtfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfiltroActionPerformed(evt);
            }
        });
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 320, -1));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 30, -1));

        jLabel8.setText("Atendido por:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, -1, 20));

        txtemploc.setEditable(false);
        txtemploc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemplocActionPerformed(evt);
            }
        });
        getContentPane().add(txtemploc, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 200, -1));

        txtid_num.setEditable(false);
        txtid_num.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txtid_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 30, 30));

        jLabel9.setText("NUMERO DE PEDIDO:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 30));

        txtstatusped.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "Entregado" }));
        getContentPane().add(txtstatusped, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 180, 110, 20));

        refresh.setText("refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, -1, -1));
        getContentPane().add(txtitemped, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, 50, -1));
        getContentPane().add(txtdescped, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, 50, 20));
        getContentPane().add(txtprecioped, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 50, -1));

        agregar.setText("agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, -1, -1));

        fechatemp.setEditable(false);
        getContentPane().add(fechatemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 130, -1));

        jLabel10.setText("Estado del pedido:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 110, 20));

        jButton1.setText("Confirmar Pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 510, 120, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLAS.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtmemlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmemlocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemlocActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    eliminar();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(TP.isVisible()==true)
        {
            TP.dispose();
            TP.refresh();
            TP.setVisible(true);

        }
        else
        {
            TP.refresh();
            TP.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla_cotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_cotKeyPressed
    int car = evt.getKeyCode();
        if(car==KeyEvent.VK_ENTER)
        {
            actualizaritems();
        }
    
        if(car==127)
        {
            eliminar();
        }  
    }//GEN-LAST:event_tabla_cotKeyPressed

    private void txtfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltroActionPerformed

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped

    }//GEN-LAST:event_txtfiltroKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(TC.isVisible()==true)
        {
            TC.dispose();
            TC.refresh();
            TC.setVisible(true);
        }
        else
        {
            TC.refresh();
            TC.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed

    }//GEN-LAST:event_refreshActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
    agregar();        // TODO add your handling code here:
    }//GEN-LAST:event_agregarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    confirmardatos();
    registrar();     
    limpiar();
    refresh1();
    refresh2();
    dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtemplocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemplocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemplocActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    aplicardescuento();
    refresh2();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void gnrreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gnrreporteActionPerformed
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
            java.util.logging.Logger.getLogger(newPed_Reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newPed_Reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newPed_Reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newPed_Reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new newPed_Reg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton agregar;
    public static javax.swing.JTextField fechatemp;
    private javax.swing.JButton gnrreporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refresh;
    private javax.swing.JTable tabla_cot;
    public static javax.swing.JTextField txtdescped;
    public static javax.swing.JTextField txtemploc;
    public static com.toedter.calendar.JDateChooser txtfechaped;
    private javax.swing.JTextField txtfiltro;
    public static javax.swing.JTextField txtid_num;
    public static javax.swing.JTextField txtitemped;
    public static javax.swing.JTextField txtmemloc;
    public static javax.swing.JTextField txtnomloc;
    public static javax.swing.JTextField txtprecioped;
    private javax.swing.JComboBox<String> txtstatusped;
    private javax.swing.JTextField txtsumach;
    private javax.swing.JTextField txtsumaef;
    private javax.swing.JTextField txtsumatc;
    // End of variables declaration//GEN-END:variables
}
