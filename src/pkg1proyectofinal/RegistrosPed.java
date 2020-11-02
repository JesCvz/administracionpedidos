/*
Para resetear el conteo de pedidos ALTER TABLE pedidos AUTO_INCREMENT = 1;
 */
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


public class RegistrosPed extends javax.swing.JFrame {
    Connection con = null;
    Statement stmt =null;
    TABLACLI TC = new TABLACLI();
    TABLAPRO TP = new TABLAPRO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String titulos[] = {"ID_NUM", "ITEM","Descripcion","PRECIO","Descuento","Cantidad","Subtotal"};
    String fila[] = new String[7];
    DefaultTableModel modelo;
    TableRowSorter trs;
    //
    public RegistrosPed() {
        
          initComponents();
          int contar = tabla_cot.getRowCount();
          int suma=0;
        this.setTitle("Cotizar Pedido");
        this.setLocation(200,100);
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
               ResultSet rs = stmt.executeQuery("SELECT id_num FROM pedidos WHERE id_num = (SELECT MAX(id_NUM) FROM pedidos)");
                        if(rs.next()==false)
                        {
                            txtid_num.setText("1");
                        }
                        else
                        {
                             while(rs.next())
                            {
                                String id = rs.getString("id_num"); 
                                int num = Integer.parseInt(id);
                                num += 1;
                                id = Integer.toString(num);
                                txtid_num.setText(id);
                           
                            }
                        }
              
          }
         catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
             JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
         }
        
                
         
           
            
    }
        
        public void refresh1()
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
               ResultSet rs = stmt.executeQuery("SELECT id_num FROM pedidos WHERE id_num = (SELECT MAX(id_NUM) FROM pedidos)");
                        while(rs.next())
                        {
                            String id = rs.getString("id_num"); 
                            int num = Integer.parseInt(id);
                            num += 1;
                            id = Integer.toString(num);
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
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha estableciso una conexion con la base de datos"+"\n"+url);
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select* from cot_ped where id_num = '"+txtid_num.getText()+"'");
                
                modelo = new DefaultTableModel(null,titulos);
                while(rs.next()){
                    fila[0]=rs.getString("id");
                    fila[1]=rs.getString("item");
                    fila[2]=rs.getString("desc_pro");
                    fila[3]=rs.getString("precio_pro");
                    fila[4]=rs.getString("descuento");
                    fila[5]=rs.getString("cantidad");
                    float num = (Float.parseFloat(fila[3])-Float.parseFloat(fila[4]))*(Float.parseFloat(fila[5]));
                    fila[6] = Float.toString(num);
                    
                    modelo.addRow(fila);
                    
                }
                tabla_cot.setModel(modelo);
                TableColumn cq = tabla_cot.getColumn("ID_NUM");
                TableColumn cm = tabla_cot.getColumn("ITEM");
                TableColumn cn = tabla_cot.getColumn("Descripcion");
                TableColumn ca = tabla_cot.getColumn("PRECIO");
                TableColumn cc = tabla_cot.getColumn("Descuento");
                TableColumn cd = tabla_cot.getColumn("Cantidad");
                TableColumn cv = tabla_cot.getColumn("Subtotal");
             
              for(int i=0;i<tabla_cot.getRowCount();i++)
             {
                 suma = suma + Float.parseFloat(tabla_cot.getValueAt(i, 6).toString());
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
        
        public void consultar ()
        {
            String cap="";
        ResultSet rs = null;
        String var2 = txtid_num.getText();
        String sql2 = "Select status_ped FROM pedidos where id_num = '"+var2+"'";
         System.out.println(var2);
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

            while(rs.next()){
              
                String isp = rs.getString("status_ped");
                txtstatusped.setSelectedItem(isp);
                }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(InstantiationException|IllegalAccessException|ClassNotFoundException ex){
            Logger.getLogger(RegistrosPed.class.getName()).log(Level.SEVERE,null,ex);
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
        this.txtfechaped.setDate(date1);
        System.out.println(sDate1+"\t"+date1);  
       
   }
    //Consulta la membresia que fue ingresada en la tabla emergente
     public void consultarmem(){
        String cap="";
        ResultSet rs = null;
        String var2 = txtmemloc.getText();
        String sql2 = "Select mem_cli, nom_cli FROM clientes where mem_cli = '"+var2+"'";
         System.out.println(var2);
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

            while(rs.next()){
                String id = rs.getString("mem_cli");
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
            Logger.getLogger(RegistrosPed.class.getName()).log(Level.SEVERE,null,ex);
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
     public void actualizar()
      {
        String cadena1, cadena2, cadena3,cadena4,cadena5,cadena6;
         
        cadena1 = txtmemloc.getText();
        cadena2 = txtnomloc.getText();
        cadena3 = sdf.format(txtfechaped.getDate());
        cadena4 = txtemploc.getText();
        cadena5 = txtstatusped.getSelectedItem().toString();
        cadena6 = txtsumaef.getText();
      
     
     try { 
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement();
                  ResultSet rs = stmt.executeQuery("select* from pedidos where id_num = '"+txtid_num.getText()+"'");
                  if(rs.next()==false)
                  {
                      System.out.println("f");
                  }
                  else
                  {
                  stmt.executeUpdate("update ignore pedidos set mem_cli = '"+cadena1+"' , nom_cli = '"+cadena2+"', fecha_ped = '"+cadena3+"', nom_emp = '"+cadena4+"', status_ped = '"+cadena5+"', precio_ped = '"+cadena6+"' where id_num = '"+txtid_num.getText()+"' "); 
                  System.out.println("Los valores han sido Actualizados"); 
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
                      } catch( Exception e ) { 
                          System.out.println( e.getMessage()); 
                      } 
                  } 
     }
     
      
        Pedidos.refresh.doClick();
      }
     
     public void registrar()
     {
         String cadena2, cadena3, cadena4,cadena5,cadena6,cadena7;
         if(txtfechaped.getDate() == null)
         {
             javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar todos los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
         }
         else
         {
             cadena2 = txtmemloc.getText();
        cadena3 = txtnomloc.getText();
        cadena4 = sdf.format(txtfechaped.getDate());
        cadena5 = txtemploc.getText();
        cadena6 = txtstatusped.getSelectedItem().toString();
        cadena7 = txtsumaef.getText();
        
        
        if((txtmemloc.getText().equals(""))||(txtnomloc.getText().equals(""))||txtfechaped.getDateFormatString().equals(""))
        
        {
            System.out.println(txtfechaped.getDate());
            javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar todos los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
                        ResultSet rs = stmt.executeQuery("select* from pedidos");
                        if(rs.next()==false)
                        {
                            stmt.executeUpdate("INSERT INTO pedidos(`id_num`, `mem_cli`, `nom_cli`, `fecha_ped`, `nom_emp`, `status_ped`, `precio_ped`) VALUES('"+txtid_num.getText()+"','"+cadena2+"','"+cadena3+"','"+cadena4+"','"+cadena5+"','"+cadena6+"','"+cadena7+"')");
                            System.out.println("Los valores han sido agregados a la base de datos");
                            javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        }
                          else
                         {
                             rs = stmt.executeQuery("select* from pedidos");
                                if (rs.next()==true)
                                {
                                            rs = stmt.executeQuery("select* from pedidos where id_num = '"+txtid_num.getText()+"'");
                                             if(rs.next()==true)
                                             {
                                                 System.out.println("pase por aqui jejtl");
                                                   actualizar();
                                             }

                                else
                                {
                                    stmt.executeUpdate("INSERT INTO pedidos(`id_num`, `mem_cli`, `nom_cli`, `fecha_ped`, `nom_emp`, `status_ped`, `precio_ped`) VALUES('"+txtid_num.getText()+"','"+cadena2+"','"+cadena3+"','"+cadena4+"','"+cadena5+"','"+cadena6+"','"+cadena7+"')");
                                    System.out.println("Los valores han sido agregados a la base de datos");
                                    javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                    Timer timer = new Timer();
                                    TimerTask tarea = new TimerTask() {
                                            @Override
                                            public void run() {
                                            refresh1();
                                            refresh2();
                                        }
                                            };

                                      timer.schedule(tarea, 100);
                                }
                            }
                  }
                        
                
            }catch(InstantiationException|IllegalAccessException|ClassNotFoundException|SQLException ex){
            Logger.getLogger(RegistrosPed.class.getName()).log(Level.SEVERE, null, ex);
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
        Pedidos.refresh.doClick();
        
     }
         }
        
     
      
     
     //agregar el producto a la tabla de cot
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
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                con = DriverManager.getConnection(url,usuario,contraseña);
                if(con!=null)
                    System.out.println("Se ha establecido una conexión con la base de datos"+
                    "\n"+url);
                
                      
                        stmt = con.createStatement();
                                                //id_num,item, desc_pro, precio_pro, id, cantidad, descuentoS
                        stmt.executeUpdate("INSERT INTO cot_ped(`id_num`, `item`, `desc_pro`, `precio_pro`,`cantidad`, `descuento`) VALUES('"+txtid_num.getText()+"','"+cadena2+"','"+cadena3+"','"+cadena4+"','"+1+"','"+0+"')");
                        System.out.println("Los valores han sido agregados a la base de datos");
                        
                
            }catch(InstantiationException|IllegalAccessException|ClassNotFoundException|SQLException ex){
            Logger.getLogger(RegistrosPed.class.getName()).log(Level.SEVERE, null, ex);
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
                        actualizar();
                    }
            };

      timer.schedule(tarea, 100);
    }
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
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  stmt.executeUpdate("DELETE FROM cot_ped WHERE id = '"+valor+"'"); 

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
    actualizar();
    
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
            String url = "jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "JM5038766866"; 
                  
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();  
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  stmt.executeUpdate("update ignore cot_ped set precio_pro = '"+tabla_cot.getValueAt((fila-1), 3)+"'  ,descuento = '"+tabla_cot.getValueAt((fila-1), 4)+"' , cantidad= '"+tabla_cot.getValueAt((fila-1), 5)+"'  WHERE id = '"+valor+"'"); 
                  stmt.executeUpdate("update ignore productos set precio_pro = '"+tabla_cot.getValueAt((fila-1), 3)+"' WHERE item = '"+tabla_cot.getValueAt((fila-1), 1)+"'"); 
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
    actualizar();
    }
    
    public void limpiar()
    {
        this.txtmemloc.setText("");
        this.txtnomloc.setText("");
        this.txtfechaped.setDate(null);
        this.txtstatusped.setSelectedItem("Pendiente");

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
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
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_cot = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
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

        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("Tel: 81 - 1224 - 0050");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 100, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Carretera Nacional");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("Sucursal: Monterrey III 5304");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 60, -1, -1));

        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Costco de Mexico S.A. de C.V.");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, -1));

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
        getContentPane().add(txtsumach, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 460, 100, 30));

        txtsumaef.setEditable(false);
        getContentPane().add(txtsumaef, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 360, 100, 30));

        txtsumatc.setEditable(false);
        getContentPane().add(txtsumatc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 410, 100, -1));

        jLabel5.setText("Total con T/Credito:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 390, 120, 20));

        jLabel6.setText("Total con cheque:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 440, 110, 20));

        jLabel4.setText("Total Efectivo:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 340, 100, 20));

        jButton6.setText("Eliminar Articulos");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 270, 120, 30));

        jButton2.setText("Agregar Articulos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 240, 120, -1));

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

        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 110, -1));

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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 540, 120, 20));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TABLAS.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltroActionPerformed

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

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        refresh1();
        refresh2();
    }//GEN-LAST:event_refreshActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        agregar();        // TODO add your handling code here:
    }//GEN-LAST:event_agregarActionPerformed

    private void txtmemlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmemlocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemlocActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        eliminar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    registrar();     
    limpiar();
    refresh1();
    refresh2();
    dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
    txtfiltro.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
           trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtfiltro.getText(), 0, 1,2));
        }      
    });     
    trs = new TableRowSorter(modelo);
    tabla_cot.setRowSorter(trs);
    }//GEN-LAST:event_txtfiltroKeyTyped

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
            java.util.logging.Logger.getLogger(RegistrosPed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrosPed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrosPed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrosPed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrosPed().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton agregar;
    public static javax.swing.JTextField fechatemp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
