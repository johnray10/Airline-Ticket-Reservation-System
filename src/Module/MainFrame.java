/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import com.nitido.utils.toaster.Toaster;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author JayJomJohn
 */
public class MainFrame extends javax.swing.JFrame {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    
    public MainFrame() {
        initComponents();
        conn=JavaConnector.ConnectDb();
        ShowDate();
        SetIcon();
        showPassportID();
        showCustomerID();       
        showFlightID();
        showCustomer();
        showFlight();
        showTicketNo();
        showCombo_FlightSource();
        showCombo_Departure();
        showTicket();
        showUser();
        showSchedFlight();
        //COUNTTOTAL 
        showTotal_Customers();
        showTotal_Flight();
        showTotal_Reserved();        
        showTotal_CancelFlight();
        //COMBOBOX DATABASE
        showCombo_CustomerNo();
        showJListcombo();
    }
    
    
//DISPLAY ICON
private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/IconA/icons8_airport_35px.png")));
    }    

//TICKET ID
private void showTicketNo(){
    try {
        String sql = "SELECT MAX(ticketno) FROM tbl_ticket";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
            rs.getString("MAX(ticketno)");
            if(rs.getString("MAX(ticketno)")==null){
                label_ticket.setText("TN001");
            }else{
                long id = Long.parseLong(rs.getString("MAX(ticketno)").substring(2,rs.getString("MAX(ticketno)").length()));
                id++;
                label_ticket.setText("TN"+String.format("%03d", id));                
            }
        }
    } catch (NumberFormatException | SQLException e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
}   


//FLIGHT ID
private void showFlightID(){
    try {
        String sql = "SELECT MAX(`FlightID`) FROM tbl_flight";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
            rs.getString("MAX(`FlightID`)");
            if(rs.getString("MAX(`FlightID`)")==null){
                f_flightid.setText("FI001");
            }else{
                long id = Long.parseLong(rs.getString("MAX(`FlightID`)").substring(2,rs.getString("MAX(`FlightID`)").length()));
                id++;
                f_flightid.setText("FI"+String.format("%03d", id));
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
}   


//CUSTOMER ID
private void showCustomerID(){
    try {
        String sql = "SELECT MAX(customer_no) FROM tbl_customer";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
            rs.getString("MAX(customer_no)");
            if(rs.getString("MAX(customer_no)")==null){
                c_customerno.setText("CS001");
            }else{
                long id = Long.parseLong(rs.getString("MAX(customer_no)").substring(2,rs.getString("MAX(customer_no)").length()));
                id++;
                c_customerno.setText("CS"+String.format("%03d", id));
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
}    
        

//PASSPORTID    
private void showPassportID(){
    Random ra = new Random();
    c_passportid.setText(""+ra.nextInt(10000+1));
}    


//DATE & TIME    
private void ShowDate() {
    java.util.Date d =new java.util.Date();
    SimpleDateFormat dd = new SimpleDateFormat("EEE LLL dd yyyy |");
    Date.setText(dd.format(d));
       
    new Timer(0, (ActionEvent e) -> {
    java.util.Date d1 = new java.util.Date();
    SimpleDateFormat dd1 = new SimpleDateFormat("hh:mm:ss aa");
    Time.setText(dd1.format(d1));
  
}).start();       
}     
    

//SETFONTS
public void setFont(JLabel label) {    
        label.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
    }
public void resetFont(JLabel label){
        label.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N        
    }    


//SETFOREGROUND
public void setForegounds(JLabel label){
    label.setForeground(new Color(238, 238, 238));
}
public void resetForegounds(JLabel label){
    label.setForeground(Color.white);
}


//SETHOVERBOARD    
public void setColor (JPanel pane) {
        pane.setBackground(new Color(211, 84, 0));
    }
    public void resetColor(JPanel []pane, JPanel[] indicators)
    {
        for (int i=0; i<pane.length; i++) {
           pane[i].setBackground(new Color(1,50,67));
            
        }for (int i=0; i<indicators.length; i++) {
           indicators[i].setOpaque(false);          
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        Sidebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BTN1 = new javax.swing.JPanel();
        PN1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        BTN2 = new javax.swing.JPanel();
        PN2 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        BTN3 = new javax.swing.JPanel();
        PN3 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        BTN4 = new javax.swing.JPanel();
        PN4 = new javax.swing.JPanel();
        label4 = new javax.swing.JLabel();
        BTN5 = new javax.swing.JPanel();
        PN5 = new javax.swing.JPanel();
        label5 = new javax.swing.JLabel();
        BTN6 = new javax.swing.JPanel();
        PN6 = new javax.swing.JPanel();
        label6 = new javax.swing.JLabel();
        BTN7 = new javax.swing.JPanel();
        PN7 = new javax.swing.JPanel();
        label7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelhead = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        label_user = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cardpanel = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        Count_Reserved = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        label_cancel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        Count_customers = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        Count_flight = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_cancelflight = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable_schedflight = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        customer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        c_firstname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        c_customerno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        c_lastname = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        c_middlename = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        c_passportid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        c_address = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        c_birthdate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        c_male = new javax.swing.JRadioButton();
        c_female = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        c_contact = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_customers = new javax.swing.JTable();
        c_delete = new javax.swing.JButton();
        c_add = new javax.swing.JButton();
        c_update = new javax.swing.JButton();
        c_clear = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        Flight = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        f_flightid = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        f_source = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        f_departure = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        f_departuretime = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        f_airtime = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        f_flightcharge = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        f_cmb_flightname = new javax.swing.JComboBox<>();
        f_cmb_customerno = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_flight = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        f_add = new javax.swing.JButton();
        f_update = new javax.swing.JButton();
        f_clear = new javax.swing.JButton();
        f_delete = new javax.swing.JButton();
        f_setflight = new com.toedter.calendar.JDateChooser();
        ticket = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        cmb_source = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        cmb_departure = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_flightdetail = new javax.swing.JTable();
        t_search = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        t_Customerno = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        t_fullname = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        t_passportid = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        t_flightno = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        t_flightname = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        t_depttime = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        t_price = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton11 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        cmb_class = new javax.swing.JComboBox<>();
        t_total = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        label_ticket = new javax.swing.JLabel();
        book = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        panelreceipt = new javax.swing.JPanel();
        label_1 = new javax.swing.JLabel();
        label_2 = new javax.swing.JLabel();
        label_3 = new javax.swing.JLabel();
        label_4 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        label_5 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        label_6 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        label_7 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        label_8 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        label_9 = new javax.swing.JLabel();
        label_record = new javax.swing.JLabel();
        label_amount = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        label_10 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        setting = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_ticket = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1300, 650));
        setMinimumSize(new java.awt.Dimension(1300, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(1300, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sidebar.setBackground(new java.awt.Color(1, 50, 67));
        Sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_manager_127px_1.png"))); // NOI18N
        Sidebar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 12, 216, 144));
        Sidebar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 216, 10));

        BTN1.setBackground(new java.awt.Color(211, 84, 0));
        BTN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN1MousePressed(evt);
            }
        });

        PN1.setBackground(new java.awt.Color(211, 84, 0));

        javax.swing.GroupLayout PN1Layout = new javax.swing.GroupLayout(PN1);
        PN1.setLayout(PN1Layout);
        PN1Layout.setHorizontalGroup(
            PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN1Layout.setVerticalGroup(
            PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_home_35px.png"))); // NOI18N
        label1.setText("Dashboard");
        label1.setIconTextGap(10);

        javax.swing.GroupLayout BTN1Layout = new javax.swing.GroupLayout(BTN1);
        BTN1.setLayout(BTN1Layout);
        BTN1Layout.setHorizontalGroup(
            BTN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN1Layout.createSequentialGroup()
                .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN1Layout.setVerticalGroup(
            BTN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 192, -1, 60));

        BTN2.setBackground(new java.awt.Color(1, 50, 67));
        BTN2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN2MousePressed(evt);
            }
        });

        PN2.setBackground(new java.awt.Color(211, 84, 0));
        PN2.setOpaque(false);

        javax.swing.GroupLayout PN2Layout = new javax.swing.GroupLayout(PN2);
        PN2.setLayout(PN2Layout);
        PN2Layout.setHorizontalGroup(
            PN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN2Layout.setVerticalGroup(
            PN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_add_user_35px.png"))); // NOI18N
        label2.setText("Customer");
        label2.setIconTextGap(10);

        javax.swing.GroupLayout BTN2Layout = new javax.swing.GroupLayout(BTN2);
        BTN2.setLayout(BTN2Layout);
        BTN2Layout.setHorizontalGroup(
            BTN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN2Layout.createSequentialGroup()
                .addComponent(PN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN2Layout.setVerticalGroup(
            BTN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 252, -1, 60));

        BTN3.setBackground(new java.awt.Color(1, 50, 67));
        BTN3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN3MousePressed(evt);
            }
        });

        PN3.setBackground(new java.awt.Color(211, 84, 0));
        PN3.setOpaque(false);

        javax.swing.GroupLayout PN3Layout = new javax.swing.GroupLayout(PN3);
        PN3.setLayout(PN3Layout);
        PN3Layout.setHorizontalGroup(
            PN3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN3Layout.setVerticalGroup(
            PN3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_airport_35px.png"))); // NOI18N
        label3.setText("Flight");
        label3.setIconTextGap(10);

        javax.swing.GroupLayout BTN3Layout = new javax.swing.GroupLayout(BTN3);
        BTN3.setLayout(BTN3Layout);
        BTN3Layout.setHorizontalGroup(
            BTN3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN3Layout.createSequentialGroup()
                .addComponent(PN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN3Layout.setVerticalGroup(
            BTN3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 312, -1, 60));

        BTN4.setBackground(new java.awt.Color(1, 50, 67));
        BTN4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN4MousePressed(evt);
            }
        });

        PN4.setBackground(new java.awt.Color(211, 84, 0));
        PN4.setOpaque(false);

        javax.swing.GroupLayout PN4Layout = new javax.swing.GroupLayout(PN4);
        PN4.setLayout(PN4Layout);
        PN4Layout.setHorizontalGroup(
            PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN4Layout.setVerticalGroup(
            PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_add_ticket_35px.png"))); // NOI18N
        label4.setText("Ticket");
        label4.setIconTextGap(10);

        javax.swing.GroupLayout BTN4Layout = new javax.swing.GroupLayout(BTN4);
        BTN4.setLayout(BTN4Layout);
        BTN4Layout.setHorizontalGroup(
            BTN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN4Layout.createSequentialGroup()
                .addComponent(PN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN4Layout.setVerticalGroup(
            BTN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 372, -1, 60));

        BTN5.setBackground(new java.awt.Color(1, 50, 67));
        BTN5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN5MousePressed(evt);
            }
        });

        PN5.setBackground(new java.awt.Color(211, 84, 0));
        PN5.setOpaque(false);

        javax.swing.GroupLayout PN5Layout = new javax.swing.GroupLayout(PN5);
        PN5.setLayout(PN5Layout);
        PN5Layout.setHorizontalGroup(
            PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN5Layout.setVerticalGroup(
            PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label5.setForeground(new java.awt.Color(255, 255, 255));
        label5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_ticket_purchase_35px.png"))); // NOI18N
        label5.setText("Book");
        label5.setIconTextGap(10);

        javax.swing.GroupLayout BTN5Layout = new javax.swing.GroupLayout(BTN5);
        BTN5.setLayout(BTN5Layout);
        BTN5Layout.setHorizontalGroup(
            BTN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN5Layout.createSequentialGroup()
                .addComponent(PN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN5Layout.setVerticalGroup(
            BTN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 432, -1, 60));

        BTN6.setBackground(new java.awt.Color(1, 50, 67));
        BTN6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN6MousePressed(evt);
            }
        });

        PN6.setBackground(new java.awt.Color(211, 84, 0));
        PN6.setOpaque(false);

        javax.swing.GroupLayout PN6Layout = new javax.swing.GroupLayout(PN6);
        PN6.setLayout(PN6Layout);
        PN6Layout.setHorizontalGroup(
            PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN6Layout.setVerticalGroup(
            PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label6.setForeground(new java.awt.Color(255, 255, 255));
        label6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_settings_35px.png"))); // NOI18N
        label6.setText("Setting");
        label6.setIconTextGap(10);

        javax.swing.GroupLayout BTN6Layout = new javax.swing.GroupLayout(BTN6);
        BTN6.setLayout(BTN6Layout);
        BTN6Layout.setHorizontalGroup(
            BTN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN6Layout.createSequentialGroup()
                .addComponent(PN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN6Layout.setVerticalGroup(
            BTN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 492, -1, 60));

        BTN7.setBackground(new java.awt.Color(1, 50, 67));
        BTN7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN7MousePressed(evt);
            }
        });

        PN7.setBackground(new java.awt.Color(211, 84, 0));
        PN7.setOpaque(false);

        javax.swing.GroupLayout PN7Layout = new javax.swing.GroupLayout(PN7);
        PN7.setLayout(PN7Layout);
        PN7Layout.setHorizontalGroup(
            PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        PN7Layout.setVerticalGroup(
            PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        label7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label7.setForeground(new java.awt.Color(255, 255, 255));
        label7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_logout_rounded_35px.png"))); // NOI18N
        label7.setText("Log-Out");
        label7.setIconTextGap(10);

        javax.swing.GroupLayout BTN7Layout = new javax.swing.GroupLayout(BTN7);
        BTN7.setLayout(BTN7Layout);
        BTN7Layout.setHorizontalGroup(
            BTN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN7Layout.createSequentialGroup()
                .addComponent(PN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        BTN7Layout.setVerticalGroup(
            BTN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BTN7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        Sidebar.add(BTN7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 588, -1, 60));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sidebar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 156, 216, 24));

        getContentPane().add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 264, 648));

        panelhead.setBackground(new java.awt.Color(211, 84, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_user_male_circle_35px.png"))); // NOI18N

        label_user.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_user.setForeground(new java.awt.Color(255, 255, 255));
        label_user.setText("USER");

        Time.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Time.setForeground(new java.awt.Color(255, 255, 255));
        Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time.setText("Time");

        Date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 255, 255));
        Date.setText("Date");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_airport_35px.png"))); // NOI18N
        jLabel3.setText("Airline Ticket Reservation System");

        javax.swing.GroupLayout panelheadLayout = new javax.swing.GroupLayout(panelhead);
        panelhead.setLayout(panelheadLayout);
        panelheadLayout.setHorizontalGroup(
            panelheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelheadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(label_user, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelheadLayout.setVerticalGroup(
            panelheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelheadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(panelhead, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 0, 1032, 60));

        cardpanel.setBackground(new java.awt.Color(204, 204, 204));
        cardpanel.setLayout(new java.awt.CardLayout());

        dashboard.setBackground(new java.awt.Color(1, 50, 67));
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dashboard");
        dashboard.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 228, 48));
        dashboard.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 60, 1008, 12));

        jPanel17.setBackground(new java.awt.Color(58, 83, 155));

        Count_Reserved.setFont(new java.awt.Font("Tahoma", 1, 65)); // NOI18N
        Count_Reserved.setForeground(new java.awt.Color(255, 255, 255));
        Count_Reserved.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel23.setBackground(new java.awt.Color(51, 51, 51));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_ticket_purchase_35px.png"))); // NOI18N
        jLabel23.setText("Total Reserved");
        jLabel23.setOpaque(true);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Count_Reserved, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Count_Reserved, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        dashboard.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 300, 180, 190));

        jPanel18.setBackground(new java.awt.Color(150, 40, 27));

        label_cancel.setFont(new java.awt.Font("Tahoma", 1, 65)); // NOI18N
        label_cancel.setForeground(new java.awt.Color(255, 255, 255));
        label_cancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel25.setBackground(new java.awt.Color(51, 51, 51));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_airplane_mode_off_35px.png"))); // NOI18N
        jLabel25.setText("Total Cancel Flight");
        jLabel25.setOpaque(true);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        dashboard.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 300, 180, 190));

        jPanel19.setBackground(new java.awt.Color(211, 84, 0));

        Count_customers.setFont(new java.awt.Font("Tahoma", 1, 65)); // NOI18N
        Count_customers.setForeground(new java.awt.Color(255, 255, 255));
        Count_customers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel27.setBackground(new java.awt.Color(51, 51, 51));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_groups_35px_1.png"))); // NOI18N
        jLabel27.setText("Total Customers");
        jLabel27.setOpaque(true);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Count_customers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Count_customers, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        dashboard.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 84, 180, 190));

        jPanel20.setBackground(new java.awt.Color(30, 130, 76));

        Count_flight.setFont(new java.awt.Font("Tahoma", 1, 65)); // NOI18N
        Count_flight.setForeground(new java.awt.Color(255, 255, 255));
        Count_flight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel29.setBackground(new java.awt.Color(51, 51, 51));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_airport_35px.png"))); // NOI18N
        jLabel29.setText("Total Flight");
        jLabel29.setOpaque(true);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Count_flight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Count_flight, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        dashboard.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 84, 180, 190));

        jTable_cancelflight.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_cancelflight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_cancelflight.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jScrollPane7.setViewportView(jTable_cancelflight);

        dashboard.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 348, 564, 144));

        jTable_schedflight.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_schedflight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_schedflight.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jTable_schedflight.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(jTable_schedflight);

        dashboard.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 132, 564, 144));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cancel Flight");
        dashboard.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 300, 276, 36));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Schedule Flight");
        dashboard.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 84, 276, 36));

        cardpanel.add(dashboard, "card2");

        customer.setBackground(new java.awt.Color(1, 50, 67));
        customer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(1, 50, 67));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "Customer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Firstname");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 84, 130, 40));

        c_firstname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_firstname.setForeground(new java.awt.Color(255, 255, 255));
        c_firstname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        c_firstname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        c_firstname.setCaretColor(new java.awt.Color(211, 84, 0));
        c_firstname.setOpaque(false);
        jPanel1.add(c_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 84, 220, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Customer No:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 130, 40));

        c_customerno.setEditable(false);
        c_customerno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_customerno.setForeground(new java.awt.Color(255, 255, 255));
        c_customerno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        c_customerno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        c_customerno.setCaretColor(new java.awt.Color(211, 84, 0));
        c_customerno.setOpaque(false);
        jPanel1.add(c_customerno, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 36, 220, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lastname:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 132, 130, 40));

        c_lastname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_lastname.setForeground(new java.awt.Color(255, 255, 255));
        c_lastname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        c_lastname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        c_lastname.setCaretColor(new java.awt.Color(211, 84, 0));
        c_lastname.setOpaque(false);
        jPanel1.add(c_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 132, 220, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Middlename:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 130, 40));

        c_middlename.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_middlename.setForeground(new java.awt.Color(255, 255, 255));
        c_middlename.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        c_middlename.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        c_middlename.setCaretColor(new java.awt.Color(211, 84, 0));
        c_middlename.setOpaque(false);
        jPanel1.add(c_middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 180, 220, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Birth of Date:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 36, 130, 40));

        c_passportid.setEditable(false);
        c_passportid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_passportid.setForeground(new java.awt.Color(255, 255, 255));
        c_passportid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        c_passportid.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        c_passportid.setCaretColor(new java.awt.Color(211, 84, 0));
        c_passportid.setOpaque(false);
        jPanel1.add(c_passportid, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 228, 220, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Passport ID:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 228, 130, 40));

        c_address.setBackground(new java.awt.Color(1, 50, 67));
        c_address.setColumns(20);
        c_address.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        c_address.setForeground(new java.awt.Color(255, 255, 255));
        c_address.setRows(5);
        c_address.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(211, 84, 0)));
        c_address.setCaretColor(new java.awt.Color(211, 84, 0));
        jScrollPane1.setViewportView(c_address);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 180, 432, 96));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Address:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 180, 130, 40));

        c_birthdate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(211, 84, 0)));
        c_birthdate.setDateFormatString("MMM-d-yyyy");
        c_birthdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_birthdate.setMinSelectableDate(new java.util.Date(631126866000L));
        jPanel1.add(c_birthdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 36, 192, 36));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gender:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 84, 130, 40));

        buttonGroup1.add(c_male);
        c_male.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_male.setForeground(new java.awt.Color(255, 255, 255));
        c_male.setText("Male");
        c_male.setOpaque(false);
        jPanel1.add(c_male, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 84, 110, 40));

        buttonGroup1.add(c_female);
        c_female.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_female.setForeground(new java.awt.Color(255, 255, 255));
        c_female.setText("Female");
        c_female.setOpaque(false);
        jPanel1.add(c_female, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 84, 110, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Contact:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 132, 130, 40));

        c_contact.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_contact.setForeground(new java.awt.Color(255, 255, 255));
        c_contact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        c_contact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        c_contact.setCaretColor(new java.awt.Color(211, 84, 0));
        c_contact.setOpaque(false);
        c_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                c_contactKeyTyped(evt);
            }
        });
        jPanel1.add(c_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 132, 220, 40));

        jTable_customers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable_customers.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_customers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_customers.setGridColor(new java.awt.Color(255, 255, 255));
        jTable_customers.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jTable_customers.getTableHeader().setReorderingAllowed(false);
        jTable_customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_customersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_customers);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 367, 972, 180));

        c_delete.setBackground(new java.awt.Color(150, 40, 27));
        c_delete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_delete.setForeground(new java.awt.Color(255, 255, 255));
        c_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_delete_file_35px_1.png"))); // NOI18N
        c_delete.setText("DELETE");
        c_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(c_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 312, 228, 48));

        c_add.setBackground(new java.awt.Color(58, 83, 155));
        c_add.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_add.setForeground(new java.awt.Color(255, 255, 255));
        c_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_add_file_35px_2.png"))); // NOI18N
        c_add.setText("CREATE");
        c_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_addActionPerformed(evt);
            }
        });
        jPanel1.add(c_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 312, 240, 48));

        c_update.setBackground(new java.awt.Color(30, 130, 76));
        c_update.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_update.setForeground(new java.awt.Color(255, 255, 255));
        c_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_edit_file_35px_1.png"))); // NOI18N
        c_update.setText("UPDATE");
        c_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_updateActionPerformed(evt);
            }
        });
        jPanel1.add(c_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 312, 228, 48));

        c_clear.setBackground(new java.awt.Color(0, 0, 0));
        c_clear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c_clear.setForeground(new java.awt.Color(255, 255, 255));
        c_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_broom_35px_1.png"))); // NOI18N
        c_clear.setText("CLEAR");
        c_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_clearActionPerformed(evt);
            }
        });
        jPanel1.add(c_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 312, 228, 48));

        jSeparator3.setBackground(new java.awt.Color(211, 84, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 288, 960, 12));

        customer.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1008, 564));

        cardpanel.add(customer, "card3");

        Flight.setBackground(new java.awt.Color(1, 50, 67));
        Flight.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(1, 50, 67));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "Flight", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Customer No:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 130, 40));

        f_flightid.setEditable(false);
        f_flightid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_flightid.setForeground(new java.awt.Color(255, 255, 255));
        f_flightid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        f_flightid.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        f_flightid.setCaretColor(new java.awt.Color(211, 84, 0));
        f_flightid.setOpaque(false);
        jPanel2.add(f_flightid, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 84, 220, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Flight Name:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 132, 130, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Source:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 130, 40));

        f_source.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_source.setForeground(new java.awt.Color(255, 255, 255));
        f_source.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        f_source.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        f_source.setCaretColor(new java.awt.Color(211, 84, 0));
        f_source.setOpaque(false);
        jPanel2.add(f_source, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 180, 220, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Set Flight:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 36, 156, 40));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Departure:");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 228, 130, 40));

        f_departure.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_departure.setForeground(new java.awt.Color(255, 255, 255));
        f_departure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        f_departure.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        f_departure.setCaretColor(new java.awt.Color(211, 84, 0));
        f_departure.setOpaque(false);
        jPanel2.add(f_departure, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 228, 220, 40));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Departure Time:");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 84, 156, 40));

        f_departuretime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_departuretime.setForeground(new java.awt.Color(255, 255, 255));
        f_departuretime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        f_departuretime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        f_departuretime.setCaretColor(new java.awt.Color(211, 84, 0));
        f_departuretime.setOpaque(false);
        f_departuretime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                f_departuretimeKeyTyped(evt);
            }
        });
        jPanel2.add(f_departuretime, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 84, 288, 40));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Air Time:");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 132, 156, 40));

        f_airtime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_airtime.setForeground(new java.awt.Color(255, 255, 255));
        f_airtime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        f_airtime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        f_airtime.setCaretColor(new java.awt.Color(211, 84, 0));
        f_airtime.setOpaque(false);
        f_airtime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                f_airtimeKeyTyped(evt);
            }
        });
        jPanel2.add(f_airtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 132, 288, 40));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Flight Charge:");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 180, 156, 40));

        f_flightcharge.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_flightcharge.setForeground(new java.awt.Color(255, 255, 255));
        f_flightcharge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        f_flightcharge.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        f_flightcharge.setCaretColor(new java.awt.Color(211, 84, 0));
        f_flightcharge.setOpaque(false);
        f_flightcharge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                f_flightchargeKeyTyped(evt);
            }
        });
        jPanel2.add(f_flightcharge, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 180, 288, 40));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Flight ID:");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 84, 130, 40));

        f_cmb_flightname.setBackground(new java.awt.Color(211, 84, 0));
        f_cmb_flightname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_cmb_flightname.setForeground(new java.awt.Color(255, 255, 255));
        f_cmb_flightname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "Philippines Airline", "Cebu Pacific", "Zesto Airline" }));
        f_cmb_flightname.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(f_cmb_flightname, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 132, 216, 36));

        f_cmb_customerno.setBackground(new java.awt.Color(211, 84, 0));
        f_cmb_customerno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_cmb_customerno.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(f_cmb_customerno, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 36, 216, 36));

        jTable_flight.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable_flight.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_flight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_flight.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jTable_flight.getTableHeader().setReorderingAllowed(false);
        jTable_flight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_flightMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_flight);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 367, 972, 180));

        jSeparator4.setBackground(new java.awt.Color(211, 84, 0));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 288, 960, 12));

        f_add.setBackground(new java.awt.Color(58, 83, 155));
        f_add.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_add.setForeground(new java.awt.Color(255, 255, 255));
        f_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_add_file_35px_2.png"))); // NOI18N
        f_add.setText("ADD");
        f_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        f_add.setIconTextGap(10);
        f_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_addActionPerformed(evt);
            }
        });
        jPanel2.add(f_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 312, 240, 48));

        f_update.setBackground(new java.awt.Color(30, 130, 76));
        f_update.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_update.setForeground(new java.awt.Color(255, 255, 255));
        f_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_edit_file_35px_1.png"))); // NOI18N
        f_update.setText("UPDATE");
        f_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        f_update.setIconTextGap(10);
        f_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_updateActionPerformed(evt);
            }
        });
        jPanel2.add(f_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 312, 228, 48));

        f_clear.setBackground(new java.awt.Color(0, 0, 0));
        f_clear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_clear.setForeground(new java.awt.Color(255, 255, 255));
        f_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_broom_35px_1.png"))); // NOI18N
        f_clear.setText("CLEAR");
        f_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        f_clear.setIconTextGap(10);
        f_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_clearActionPerformed(evt);
            }
        });
        jPanel2.add(f_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 312, 228, 48));

        f_delete.setBackground(new java.awt.Color(150, 40, 27));
        f_delete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        f_delete.setForeground(new java.awt.Color(255, 255, 255));
        f_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_delete_file_35px_1.png"))); // NOI18N
        f_delete.setText("DELETE");
        f_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        f_delete.setIconTextGap(10);
        f_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_deleteActionPerformed(evt);
            }
        });
        jPanel2.add(f_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 312, 228, 48));
        jPanel2.add(f_setflight, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 36, 288, 36));

        Flight.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1008, 564));

        cardpanel.add(Flight, "card4");

        ticket.setBackground(new java.awt.Color(1, 50, 67));
        ticket.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(1, 50, 67));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "Book Ticket", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 2, new java.awt.Color(211, 84, 0)), "SELECT COUNTRY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Source:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 130, 36));

        cmb_source.setBackground(new java.awt.Color(211, 84, 0));
        cmb_source.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmb_source.setForeground(new java.awt.Color(255, 255, 255));
        cmb_source.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT" }));
        cmb_source.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmb_source, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 36, 240, 36));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Departure:");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 96, 130, 36));

        cmb_departure.setBackground(new java.awt.Color(211, 84, 0));
        cmb_departure.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmb_departure.setForeground(new java.awt.Color(255, 255, 255));
        cmb_departure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT" }));
        cmb_departure.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmb_departure, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 96, 240, 36));

        jTable_flightdetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable_flightdetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Flight ID", "Customer No", "Flight Name", "Source", "Departure", "SetFlight", "Departure Time", "Air Time", "Flight Charge"
            }
        ));
        jTable_flightdetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_flightdetail.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jTable_flightdetail.getTableHeader().setReorderingAllowed(false);
        jTable_flightdetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_flightdetailMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_flightdetail);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 204, 384, 144));

        t_search.setBackground(new java.awt.Color(211, 84, 0));
        t_search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_search.setForeground(new java.awt.Color(255, 255, 255));
        t_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_search_35px.png"))); // NOI18N
        t_search.setText("SEARCH");
        t_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        t_search.setIconTextGap(10);
        t_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_searchActionPerformed(evt);
            }
        });
        jPanel5.add(t_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 144, 384, 48));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 432, 360));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 2, new java.awt.Color(211, 84, 0)), "SEARCH CUSTOMER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Customer No:");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 130, 40));

        t_Customerno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_Customerno.setForeground(new java.awt.Color(255, 255, 255));
        t_Customerno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_Customerno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_Customerno.setCaretColor(new java.awt.Color(211, 84, 0));
        t_Customerno.setOpaque(false);
        jPanel6.add(t_Customerno, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 36, 336, 40));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Full Name:");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 144, 130, 40));

        t_fullname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_fullname.setForeground(new java.awt.Color(255, 255, 255));
        t_fullname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_fullname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_fullname.setCaretColor(new java.awt.Color(211, 84, 0));
        t_fullname.setOpaque(false);
        jPanel6.add(t_fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 144, 336, 40));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Passport ID:");
        jPanel6.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 192, 130, 40));

        t_passportid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_passportid.setForeground(new java.awt.Color(255, 255, 255));
        t_passportid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_passportid.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_passportid.setCaretColor(new java.awt.Color(211, 84, 0));
        t_passportid.setOpaque(false);
        jPanel6.add(t_passportid, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 192, 336, 40));

        jButton10.setBackground(new java.awt.Color(30, 139, 195));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_search_35px.png"))); // NOI18N
        jButton10.setText("FIND CUSTOMER'S");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setIconTextGap(10);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 84, 480, 48));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Flight No:");
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 264, 84, 40));

        t_flightno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_flightno.setForeground(new java.awt.Color(255, 255, 255));
        t_flightno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_flightno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_flightno.setCaretColor(new java.awt.Color(211, 84, 0));
        t_flightno.setOpaque(false);
        jPanel6.add(t_flightno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 264, 132, 40));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 240, 480, 12));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Flight Name:");
        jPanel6.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 312, 108, 40));

        t_flightname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_flightname.setForeground(new java.awt.Color(255, 255, 255));
        t_flightname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_flightname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_flightname.setCaretColor(new java.awt.Color(211, 84, 0));
        t_flightname.setOpaque(false);
        jPanel6.add(t_flightname, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 312, 372, 40));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Departure Time:");
        jPanel6.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 264, 144, 40));

        t_depttime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_depttime.setForeground(new java.awt.Color(255, 255, 255));
        t_depttime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_depttime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_depttime.setCaretColor(new java.awt.Color(211, 84, 0));
        t_depttime.setOpaque(false);
        jPanel6.add(t_depttime, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 264, 96, 40));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Total:");
        jPanel6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 456, 84, 36));

        t_price.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        t_price.setForeground(new java.awt.Color(255, 255, 255));
        t_price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_price.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 84, 0), 1, true));
        t_price.setCaretColor(new java.awt.Color(211, 84, 0));
        t_price.setOpaque(false);
        jPanel6.add(t_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 132, 40));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Class:");
        jPanel6.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 360, 84, 40));

        jSpinner1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jPanel6.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 408, 132, 36));

        jButton11.setBackground(new java.awt.Color(0, 51, 102));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_ticket_35px.png"))); // NOI18N
        jButton11.setText("BOOK");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setIconTextGap(10);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 444, 192, 48));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Price:");
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 360, 84, 40));

        cmb_class.setBackground(new java.awt.Color(211, 84, 0));
        cmb_class.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmb_class.setForeground(new java.awt.Color(255, 255, 255));
        cmb_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "Ordinary", "Tourist", "Business" }));
        cmb_class.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.add(cmb_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 156, 36));

        t_total.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        t_total.setForeground(new java.awt.Color(211, 84, 0));
        t_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(t_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 456, 132, 36));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Seat No:");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 408, 84, 36));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 24, 528, 516));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 2, new java.awt.Color(211, 84, 0)), "TICKET", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_ticket.setFont(new java.awt.Font("Segoe UI", 0, 65)); // NOI18N
        label_ticket.setForeground(new java.awt.Color(211, 84, 0));
        label_ticket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ticket.setText("jLabel7");
        jPanel7.add(label_ticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 384, 72));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 432, 132));

        ticket.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1008, 564));

        cardpanel.add(ticket, "card5");

        book.setBackground(new java.awt.Color(1, 50, 67));
        book.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(1, 50, 67));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "Book", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "List Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel9.setOpaque(false);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setBackground(new java.awt.Color(1, 50, 67));
        jList1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jList1.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jList1);

        jPanel9.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 288, 444));

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 336, 504));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "Book Of Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setOpaque(false);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelreceipt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 84, 0)), "Book Ticket", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(153, 153, 153))); // NOI18N
        panelreceipt.setOpaque(false);
        panelreceipt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_1.setBackground(new java.awt.Color(153, 0, 0));
        label_1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_1.setForeground(new java.awt.Color(153, 153, 153));
        label_1.setText("--------------------------------");
        panelreceipt.add(label_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 24, 360, 24));

        label_2.setBackground(new java.awt.Color(153, 0, 0));
        label_2.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_2.setForeground(new java.awt.Color(153, 153, 153));
        label_2.setText("--------------------------------");
        panelreceipt.add(label_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 60, 360, 24));

        label_3.setBackground(new java.awt.Color(153, 0, 0));
        label_3.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_3.setForeground(new java.awt.Color(153, 153, 153));
        label_3.setText("--------------------------------");
        panelreceipt.add(label_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 96, 360, 24));

        label_4.setBackground(new java.awt.Color(153, 0, 0));
        label_4.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_4.setForeground(new java.awt.Color(153, 153, 153));
        label_4.setText("--------------------------------");
        panelreceipt.add(label_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 132, 360, 24));

        jLabel48.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 153));
        jLabel48.setText("Ticket No:");
        panelreceipt.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 24, 132, 24));

        jLabel49.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(153, 153, 153));
        jLabel49.setText("Customer No:");
        panelreceipt.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 60, 132, 24));

        jLabel51.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(153, 153, 153));
        jLabel51.setText("Name:");
        panelreceipt.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 96, 132, 24));

        jLabel53.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(153, 153, 153));
        jLabel53.setText("Passport ID:");
        panelreceipt.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 132, 132, 24));

        jLabel55.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(153, 153, 153));
        jLabel55.setText("Flight No:");
        panelreceipt.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 168, 132, 24));

        label_5.setBackground(new java.awt.Color(153, 0, 0));
        label_5.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_5.setForeground(new java.awt.Color(153, 153, 153));
        label_5.setText("--------------------------------");
        panelreceipt.add(label_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 168, 360, 24));

        jLabel57.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(153, 153, 153));
        jLabel57.setText("Flight Name:");
        panelreceipt.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 204, 132, 24));

        label_6.setBackground(new java.awt.Color(153, 0, 0));
        label_6.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_6.setForeground(new java.awt.Color(153, 153, 153));
        label_6.setText("--------------------------------");
        panelreceipt.add(label_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 204, 360, 24));

        jLabel59.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(153, 153, 153));
        jLabel59.setText("Dept Time:");
        panelreceipt.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 240, 132, 24));

        label_7.setBackground(new java.awt.Color(153, 0, 0));
        label_7.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_7.setForeground(new java.awt.Color(153, 153, 153));
        label_7.setText("--------------------------------");
        panelreceipt.add(label_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 240, 360, 24));

        jLabel61.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(153, 153, 153));
        jLabel61.setText("Price:");
        panelreceipt.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 276, 132, 24));

        label_8.setBackground(new java.awt.Color(153, 0, 0));
        label_8.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_8.setForeground(new java.awt.Color(153, 153, 153));
        label_8.setText("--------------------------------");
        panelreceipt.add(label_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 276, 360, 24));

        jLabel63.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(153, 153, 153));
        jLabel63.setText("Seat No:");
        panelreceipt.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 312, 132, 24));

        label_9.setBackground(new java.awt.Color(153, 0, 0));
        label_9.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_9.setForeground(new java.awt.Color(153, 153, 153));
        label_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_9.setText("--------");
        panelreceipt.add(label_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 312, 96, 24));

        label_record.setBackground(new java.awt.Color(153, 0, 0));
        label_record.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_record.setForeground(new java.awt.Color(153, 153, 153));
        panelreceipt.add(label_record, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 348, 240, 48));

        label_amount.setBackground(new java.awt.Color(153, 0, 0));
        label_amount.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        label_amount.setForeground(new java.awt.Color(153, 153, 153));
        label_amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_amount.setText("00.0");
        panelreceipt.add(label_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 348, 132, 24));

        jLabel67.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(153, 153, 153));
        jLabel67.setText("Class:");
        panelreceipt.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 312, 72, 24));

        label_10.setBackground(new java.awt.Color(153, 0, 0));
        label_10.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label_10.setForeground(new java.awt.Color(153, 153, 153));
        label_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_10.setText("---------------");
        panelreceipt.add(label_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 312, 168, 24));

        jLabel66.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(153, 153, 153));
        jLabel66.setText("Amount:");
        panelreceipt.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 348, 132, 24));

        jPanel10.add(panelreceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 36, 552, 408));

        jButton12.setBackground(new java.awt.Color(102, 51, 153));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_print_35px.png"))); // NOI18N
        jButton12.setText("PRINT");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.setIconTextGap(10);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 456, 192, 48));

        jButton3.setBackground(new java.awt.Color(153, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_delete_ticket_35px.png"))); // NOI18N
        jButton3.setText("Cancel Fight");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setIconTextGap(10);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 456, 216, 48));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 24, 600, 516));

        book.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1008, 564));

        cardpanel.add(book, "card6");

        setting.setBackground(new java.awt.Color(1, 50, 67));
        setting.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(1, 50, 67));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 3, 2, 3, new java.awt.Color(211, 84, 0)), "Setting", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(1, 50, 67));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel11.setBackground(new java.awt.Color(1, 50, 67));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_ticket.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable_ticket.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_ticket.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jScrollPane6.setViewportView(jTable_ticket);

        jPanel11.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 72, 936, 324));

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_ms_excel_35px.png"))); // NOI18N
        jButton1.setText("Export");
        jButton1.setIconTextGap(10);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 12, 240, 48));

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_print_35px.png"))); // NOI18N
        jButton2.setText("PRINT TABLE");
        jButton2.setIconTextGap(10);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 12, 240, 48));

        jButton6.setBackground(new java.awt.Color(102, 0, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_delete_file_35px_1.png"))); // NOI18N
        jButton6.setText("DELETE");
        jButton6.setIconTextGap(10);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 408, 228, 48));

        jButton5.setBackground(new java.awt.Color(0, 102, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_edit_file_35px_1.png"))); // NOI18N
        jButton5.setText("UPDATE");
        jButton5.setIconTextGap(10);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 408, 240, 48));

        jTabbedPane1.addTab("Ticket Record", jPanel11);

        jPanel12.setBackground(new java.awt.Color(1, 50, 67));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setSelectionBackground(new java.awt.Color(211, 84, 0));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(jTable1);

        jPanel12.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 960, 384));

        jButton4.setBackground(new java.awt.Color(0, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconA/icons8_print_35px.png"))); // NOI18N
        jButton4.setText("PRINT TABLE");
        jButton4.setIconTextGap(10);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 252, 48));

        jTabbedPane1.addTab("Login Detail", jPanel12);

        jPanel8.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 36, 984, 504));

        setting.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1008, 564));

        cardpanel.add(setting, "card7");

        getContentPane().add(cardpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 60, 1032, 588));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN1MousePressed
    setColor(BTN1);
    PN1.setOpaque(true);
    resetColor(new JPanel[]{BTN2,BTN3,BTN4,BTN5,BTN6,BTN7},new JPanel[]{PN2,PN3,PN4,PN5,PN6,PN7}); 
    }//GEN-LAST:event_BTN1MousePressed

    private void BTN2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN2MousePressed
    setColor(BTN2);
    PN2.setOpaque(true);
    resetColor(new JPanel[]{BTN1,BTN3,BTN4,BTN5,BTN6,BTN7},new JPanel[]{PN1,PN3,PN4,PN5,PN6,PN7}); 
    }//GEN-LAST:event_BTN2MousePressed

    private void BTN3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN3MousePressed
    setColor(BTN3);
    PN3.setOpaque(true);
    resetColor(new JPanel[]{BTN1,BTN2,BTN4,BTN5,BTN6,BTN7},new JPanel[]{PN1,PN2,PN4,PN5,PN6,PN7}); 
    }//GEN-LAST:event_BTN3MousePressed

    private void BTN4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN4MousePressed
    setColor(BTN4);
    PN4.setOpaque(true);
    resetColor(new JPanel[]{BTN1,BTN2,BTN3,BTN5,BTN6,BTN7},new JPanel[]{PN1,PN2,PN3,PN5,PN6,PN7}); 
    }//GEN-LAST:event_BTN4MousePressed

    private void BTN5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN5MousePressed
    setColor(BTN5);
    PN5.setOpaque(true);
    resetColor(new JPanel[]{BTN1,BTN2,BTN3,BTN4,BTN6,BTN7},new JPanel[]{PN1,PN2,PN3,PN4,PN6,PN7}); 
    }//GEN-LAST:event_BTN5MousePressed

    private void BTN6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN6MousePressed
    setColor(BTN6);
    PN6.setOpaque(true);
    resetColor(new JPanel[]{BTN1,BTN2,BTN3,BTN4,BTN5,BTN7},new JPanel[]{PN1,PN2,PN3,PN4,PN5,PN7}); 
    }//GEN-LAST:event_BTN6MousePressed

    private void BTN7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN7MousePressed
    setColor(BTN7);
    PN7.setOpaque(true);
    resetColor(new JPanel[]{BTN1,BTN2,BTN3,BTN4,BTN5,BTN6},new JPanel[]{PN1,PN2,PN3,PN4,PN5,PN6}); 
    }//GEN-LAST:event_BTN7MousePressed

    private void BTN1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN1MouseEntered
        setFont(label1);
    }//GEN-LAST:event_BTN1MouseEntered

    private void BTN1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN1MouseExited
        resetFont(label1);
    }//GEN-LAST:event_BTN1MouseExited

    private void BTN2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN2MouseEntered
        setFont(label2);
    }//GEN-LAST:event_BTN2MouseEntered

    private void BTN2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN2MouseExited
        resetFont(label2);
    }//GEN-LAST:event_BTN2MouseExited

    private void BTN3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN3MouseEntered
        setFont(label3);
    }//GEN-LAST:event_BTN3MouseEntered

    private void BTN3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN3MouseExited
        resetFont(label3);
    }//GEN-LAST:event_BTN3MouseExited

    private void BTN4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN4MouseEntered
        setFont(label4);
    }//GEN-LAST:event_BTN4MouseEntered

    private void BTN4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN4MouseExited
        resetFont(label4);
    }//GEN-LAST:event_BTN4MouseExited

    private void BTN5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN5MouseEntered
        setFont(label5);
    }//GEN-LAST:event_BTN5MouseEntered

    private void BTN5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN5MouseExited
        resetFont(label5);
    }//GEN-LAST:event_BTN5MouseExited

    private void BTN6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN6MouseEntered
        setFont(label6);
    }//GEN-LAST:event_BTN6MouseEntered

    private void BTN6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN6MouseExited
        resetFont(label6);
    }//GEN-LAST:event_BTN6MouseExited

    private void BTN7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN7MouseEntered
        setFont(label7);
    }//GEN-LAST:event_BTN7MouseEntered

    private void BTN7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN7MouseExited
        resetFont(label7);
    }//GEN-LAST:event_BTN7MouseExited

    private void BTN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN1MouseClicked
       cardpanel.removeAll();
       cardpanel.repaint();
       cardpanel.revalidate();
       cardpanel.add(dashboard);
       cardpanel.repaint();
       cardpanel.revalidate();
       
        
        setForegounds(label1);
        resetForegounds(label2);
        resetForegounds(label3);
        resetForegounds(label4);
        resetForegounds(label5);
        resetForegounds(label6);
        resetForegounds(label7);
        
    }//GEN-LAST:event_BTN1MouseClicked

    private void BTN2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN2MouseClicked
       cardpanel.removeAll();
       cardpanel.repaint();
       cardpanel.revalidate();
       cardpanel.add(customer);
       cardpanel.repaint();
       cardpanel.revalidate();
       
        setForegounds(label2);
        resetForegounds(label1);
        resetForegounds(label3);
        resetForegounds(label4);
        resetForegounds(label5);
        resetForegounds(label6);
        resetForegounds(label7);
       
    }//GEN-LAST:event_BTN2MouseClicked

    private void BTN3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN3MouseClicked
       cardpanel.removeAll();
       cardpanel.repaint();
       cardpanel.revalidate();
       cardpanel.add(Flight);
       cardpanel.repaint();
       cardpanel.revalidate();
       
        setForegounds(label3);
        resetForegounds(label1);
        resetForegounds(label2);
        resetForegounds(label4);
        resetForegounds(label5);
        resetForegounds(label6);
        resetForegounds(label7);
    }//GEN-LAST:event_BTN3MouseClicked

    private void BTN4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN4MouseClicked
       cardpanel.removeAll();
       cardpanel.repaint();
       cardpanel.revalidate();
       cardpanel.add(ticket);
       cardpanel.repaint();
       cardpanel.revalidate();
       
        setForegounds(label4);
        resetForegounds(label1);
        resetForegounds(label2);
        resetForegounds(label3);
        resetForegounds(label5);
        resetForegounds(label6);
        resetForegounds(label7);
    }//GEN-LAST:event_BTN4MouseClicked

    private void BTN5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN5MouseClicked
       cardpanel.removeAll();
       cardpanel.repaint();
       cardpanel.revalidate();
       cardpanel.add(book);
       cardpanel.repaint();
       cardpanel.revalidate();
       
          setForegounds(label5);
        resetForegounds(label1);
        resetForegounds(label2);
        resetForegounds(label3);
        resetForegounds(label4);
        resetForegounds(label6);
        resetForegounds(label7);
    }//GEN-LAST:event_BTN5MouseClicked

    private void BTN6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN6MouseClicked
       cardpanel.removeAll();
       cardpanel.repaint();
       cardpanel.revalidate();
       cardpanel.add(setting);
       cardpanel.repaint();
       cardpanel.revalidate();
       
          setForegounds(label6);
        resetForegounds(label1);
        resetForegounds(label2);
        resetForegounds(label3);
        resetForegounds(label4);
        resetForegounds(label5);
        resetForegounds(label7);
    }//GEN-LAST:event_BTN6MouseClicked

    private void BTN7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN7MouseClicked
      
          setForegounds(label7);
        resetForegounds(label1);
        resetForegounds(label2);
        resetForegounds(label3);
        resetForegounds(label4);
        resetForegounds(label5);
        resetForegounds(label6);
        
    int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?","System Logout", JOptionPane.YES_NO_OPTION);
    if(YesOrNo == 1)
    {
    //IF CLICK NO
    }
    if(YesOrNo == 0)
    {
    //IF CLICK YES
        
        this.setVisible(false);
	Login l = new Login();
	l.setVisible(true);
    }
    }//GEN-LAST:event_BTN7MouseClicked

    private void c_contactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_contactKeyTyped
            String s=c_contact.getText();            
            if (!(s.length()<11 )){                
                 evt.consume();
                 getToolkit().beep();           
            }
    }//GEN-LAST:event_c_contactKeyTyped

    private void c_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_clearActionPerformed
       c_firstname.setText("");
       c_lastname.setText("");
       c_middlename.setText("");
       buttonGroup1.clearSelection();
       ((JTextField)c_birthdate.getDateEditor().getUiComponent()).setText("");
       c_contact.setText("");
       c_address.setText("");
       showCustomerID();
       showPassportID();
    }//GEN-LAST:event_c_clearActionPerformed
    private boolean checkCustomerNo(){
        boolean found = false;
        String sql = "SELECT Customer_no FROM tbl_customer WHERE Customer_no='"+c_customerno.getText()+"' ";
        try{
         pst=conn.prepareStatement(sql);
         rs=pst.executeQuery();
         if(rs.next()){
             found=true;
         }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",2);
        }
        return found;
    }
    private void c_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_addActionPerformed
    
    int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Add Record?","AddRecord",JOptionPane.YES_NO_OPTION);
    if(x==0){ 
        
        if(c_firstname.getText().isEmpty()
                ||c_lastname.getText().isEmpty()
                ||c_middlename.getText().isEmpty()
                ||((JTextField)c_birthdate.getDateEditor().getUiComponent()).getText().isEmpty()
                ||c_male.isSelected()==false && c_female.isSelected()==false
                ||c_contact.getText().isEmpty()
                ||c_address.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Complete Customer Information","Missing Information",2);            
        }else{
            
            try {
                if(!checkCustomerNo()){
                 String insert = "INSERT INTO `tbl_customer`(`Customer_No`, `Firstname`, `Lastname`, `Middlename`, `PassportID`, `Birthdate`, `Gender`, `Contact`, `Address`) "
                         + "VALUES (?,?,?,?,?,?,?,?,?)";
                 pst=conn.prepareStatement(insert);
                 pst.setString(1,c_customerno.getText());
                 pst.setString(2,c_firstname.getText().toUpperCase());
                 pst.setString(3,c_lastname.getText().toUpperCase());
                 pst.setString(4,c_middlename.getText().toUpperCase());
                 pst.setString(5,c_passportid.getText());
                 pst.setString(6,((JTextField)c_birthdate.getDateEditor().getUiComponent()).getText());
                 c_male.setActionCommand("Male");
                 c_female.setActionCommand("Female");
                 pst.setString(7,buttonGroup1.getSelection().getActionCommand());
                 pst.setString(8,c_contact.getText());
                 pst.setString(9,c_address.getText());
                 pst.execute();
                 JOptionPane.showMessageDialog(null,"Customer has been Added","Success Operation",1);
                 showCustomer();
                 showCustomerID();
                 showPassportID();
                 showCombo_CustomerNo();
                 showTotal_Customers();
                 showNotif_Customers();
                 showNotif_Flight();
                 showNotif_Reserved();
                 showCombo_FlightSource();
                 showCombo_Departure();
                 
                 
        c_firstname.setText("");
        c_lastname.setText("");
        c_middlename.setText("");
        buttonGroup1.clearSelection();
        ((JTextField)c_birthdate.getDateEditor().getUiComponent()).setText("");
        c_contact.setText("");
        c_address.setText("");
                 
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),"Error",2);
            }
        }
  
    }
    }//GEN-LAST:event_c_addActionPerformed

    private void c_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_updateActionPerformed
    int x = JOptionPane.showConfirmDialog(null,"Are you sure you want to update?","System message!",JOptionPane.OK_OPTION); 
    if (x == 0) {  

         if(c_firstname.getText().isEmpty()
                ||c_lastname.getText().isEmpty()
                ||c_middlename.getText().isEmpty()
                ||((JTextField)c_birthdate.getDateEditor().getUiComponent()).getText().isEmpty()
                ||c_male.isSelected()==false && c_female.isSelected()==false
                ||c_contact.getText().isEmpty()
                ||c_address.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Complete Customer Information","Missing Information",2);            
        }else{
             
                try {
                    
                    c_male.setActionCommand("Male");
                    c_female.setActionCommand("Female");
              
                 String update = "UPDATE `tbl_customer` SET  "
                         + "Firstname='"+c_firstname.getText()+"', "
                         + "Lastname='"+c_lastname.getText()+"', "
                         + "Middlename='"+c_middlename.getText()+"', "
                         + "PassportID='"+c_passportid.getText()+"',"
                         + "Birthdate='"+((JTextField)c_birthdate.getDateEditor().getUiComponent()).getText()+"', "
                         + "Gender='"+buttonGroup1.getSelection().getActionCommand()+"', "
                         + "Contact='"+c_contact.getText()+"', "
                         + "Address='"+c_address.getText()+"' WHERE Customer_No='"+c_customerno.getText()+"'";
                 pst=conn.prepareStatement(update);              
                 pst.executeUpdate();
                 JOptionPane.showMessageDialog(null,"Customer has been Updated","Success Operation",1);
                 showCustomer();
                 showCustomerID();
                 showPassportID();
                 showTotal_Customers();
                 showNotif_Customers();
                 showNotif_Customers();
                 showNotif_Flight();
                 showNotif_Reserved();
                 showCombo_FlightSource();
                 showCombo_Departure();
                 
        c_firstname.setText("");
        c_lastname.setText("");
        c_middlename.setText("");
        buttonGroup1.clearSelection();
        ((JTextField)c_birthdate.getDateEditor().getUiComponent()).setText("");
        c_contact.setText("");
        c_address.setText("");
                 
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),"Error",2);
            }
         }
    }
    }//GEN-LAST:event_c_updateActionPerformed

    private void jTable_customersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_customersMouseClicked

         try{
            int row = jTable_customers.getSelectedRow();
            String Table_click=(jTable_customers.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM tbl_customer WHERE customer_no='"+Table_click+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()) {
   
             c_customerno.setText(rs.getString("customer_no"));
             c_firstname.setText(rs.getString("firstname"));
             c_lastname.setText(rs.getString("lastname"));
             c_middlename.setText(rs.getString("middlename"));
             c_passportid.setText(rs.getString("passportid"));
             
             String gen = rs.getString("gender");
             if("Male".equals(gen)){
                 c_male.setSelected(true);
             }else if("Female".equals(gen)){
                 c_female.setSelected(true);
             }
             
             ((JTextField)c_birthdate.getDateEditor().getUiComponent()).setText(rs.getString("birthdate"));  
             c_contact.setText(rs.getString("contact"));
             c_address.setText(rs.getString("address"));                     

          }           
        }catch(Exception e) {          
            JOptionPane.showMessageDialog(null, e);
        }
      
    }//GEN-LAST:event_jTable_customersMouseClicked

    private void c_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_deleteActionPerformed
 
 if(!jTable_customers.getSelectionModel().isSelectionEmpty()){
    
    int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
      
       
        try{
            
            String sql ="DELETE FROM tbl_customer WHERE customer_no='"+c_customerno.getText()+"'";
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
            showCustomer();
            showCustomerID();
            showPassportID();            
            showTotal_Customers();
            showNotif_Customers();
            showTotal_Customers();
            showNotif_Flight();
            showNotif_Reserved();
            showCombo_FlightSource();
            showCombo_Departure();
            
            }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        } 
    }  
    }//GEN-LAST:event_c_deleteActionPerformed

    private void f_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_addActionPerformed
    int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Add Record?","AddRecord",JOptionPane.YES_NO_OPTION);
    if(x==0){ 
        
        if(f_flightid.getText().isEmpty()
                ||f_cmb_flightname.getSelectedIndex()==0
                ||f_source.getText().isEmpty()
                ||f_departure.getText().isEmpty()
                ||((JTextField)f_setflight.getDateEditor().getUiComponent()).getText().isEmpty()
                ||f_departuretime.getText().isEmpty()
                ||f_airtime.getText().isEmpty()
                ||f_flightcharge.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Complete Flight Information","Missing Information",2);  
        }else{
            
            try {
                String sql = "INSERT INTO `tbl_flight`(`FlightID`,`Customer_no`, `Flight_Name`, `Source`, `Departure`, `Setflight`, `Departure_Time`, `Air_Time`, `Flight_Charge`) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)";
                pst=conn.prepareStatement(sql);        
                pst.setString(1,f_flightid.getText());
                pst.setString(2,f_cmb_customerno.getSelectedItem().toString());
                pst.setString(3,f_cmb_flightname.getSelectedItem().toString());
                pst.setString(4,f_source.getText().toUpperCase());
                pst.setString(5,f_departure.getText().toUpperCase());
                pst.setString(6,((JTextField)f_setflight.getDateEditor().getUiComponent()).getText());
                pst.setString(7,f_departuretime.getText().toUpperCase());
                pst.setString(8,f_airtime.getText().toUpperCase());
                pst.setString(9,f_flightcharge.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null,"Flight has been Added","Success Operation",1);
                showFlight();
                showFlightID();
                showCombo_Departure();
                showTotal_Flight();
                showNotif_Flight();
                 showTotal_Customers();
                 showNotif_Customers();
                 showNotif_Flight();
                 showNotif_Reserved();
                 showCombo_FlightSource();
                 showCombo_Departure();
                 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
            
            
        }
        
        
    }
    }//GEN-LAST:event_f_addActionPerformed

    private void f_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_clearActionPerformed
      
      showCombo_CustomerNo();
      showFlightID();
      f_cmb_customerno.setSelectedIndex(-1);
      f_cmb_flightname.setSelectedIndex(0);      
      f_source.setText("");
      f_departure.setText("");
      ((JTextField)f_setflight.getDateEditor().getUiComponent()).setText("");
      f_departuretime.setText("");
      f_airtime.setText("");
      f_flightcharge.setText("");
      
    }//GEN-LAST:event_f_clearActionPerformed

    private void f_departuretimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_departuretimeKeyTyped
           String a = f_departuretime.getText();            
            if (!(a.length()<5 )){                
                 evt.consume();
                 getToolkit().beep();           
            }
    }//GEN-LAST:event_f_departuretimeKeyTyped

    private void f_airtimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_airtimeKeyTyped
          String b = f_airtime.getText();            
            if (!(b.length()<5 )){                
                 evt.consume();
                 getToolkit().beep();           
            }
    }//GEN-LAST:event_f_airtimeKeyTyped

    private void f_flightchargeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_flightchargeKeyTyped
         String c = f_flightcharge.getText();
         if(!(c.length()<5)){
             evt.consume();
             getToolkit().beep();
         }
    }//GEN-LAST:event_f_flightchargeKeyTyped

    private void jTable_flightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_flightMouseClicked
   
        try{
            int row = jTable_flight.getSelectedRow();
            String Table_click=(jTable_flight.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM tbl_flight WHERE flightid='"+Table_click+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()) {
   
             f_flightid.setText(rs.getString("flightid"));
             f_cmb_customerno.setSelectedItem(rs.getString("customer_no"));
             f_cmb_flightname.setSelectedItem(rs.getString("flight_name"));
             f_source.setText(rs.getString("source"));
             f_departure.setText(rs.getString("departure"));
             ((JTextField)f_setflight.getDateEditor().getUiComponent()).setText(rs.getString("setflight")); 
             f_departuretime.setText(rs.getString("departure_time"));
             f_airtime.setText(rs.getString("air_time"));             
             f_flightcharge.setText(rs.getString("flight_charge"));                    

          }           
        }catch(Exception e) {          
            JOptionPane.showMessageDialog(null, e);
        }
      
    }//GEN-LAST:event_jTable_flightMouseClicked

    private void f_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_updateActionPerformed
    
    int x = JOptionPane.showConfirmDialog(null,"Are you sure you want to update?","System message!",JOptionPane.OK_OPTION); 
    if (x == 0) { 
        
         if(f_flightid.getText().isEmpty()
                ||f_cmb_flightname.getSelectedIndex()==0
                ||f_source.getText().isEmpty()
                ||f_departure.getText().isEmpty()
                ||((JTextField)f_setflight.getDateEditor().getUiComponent()).getText().isEmpty()
                ||f_departuretime.getText().isEmpty()
                ||f_airtime.getText().isEmpty()
                ||f_flightcharge.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Complete Flight Information","Missing Information",2);  
        }else{
             
              try {
                String sql = "UPDATE `tbl_flight` SET "
                        + "Customer_no='"+f_cmb_customerno.getSelectedItem().toString()+"',"
                        + "Flight_Name='"+f_cmb_flightname.getSelectedItem().toString()+"', "
                        + "Source='"+f_source.getText().toUpperCase()+"', "
                        + "Departure='"+f_departure.getText().toUpperCase()+"', "
                        + "Setflight='"+((JTextField)f_setflight.getDateEditor().getUiComponent()).getText()+"', "
                        + "Departure_Time='"+f_departuretime.getText().toUpperCase()+"', "
                        + "Air_Time='"+f_airtime.getText().toUpperCase()+"', "
                        + "Flight_Charge='"+f_flightcharge.getText()+"' WHERE FlightID='"+f_flightid.getText()+"' ";
                pst=conn.prepareStatement(sql);        
                pst.execute();
                JOptionPane.showMessageDialog(null,"Flight has been Updated","Success Operation",1);
                showFlight();
                showFlightID();
                showCombo_FlightSource();
                showCombo_Departure();
                showTotal_Flight();
                showNotif_Flight();
                
      showCombo_CustomerNo();
      showFlightID();
      f_cmb_customerno.setSelectedIndex(-1);
      f_cmb_flightname.setSelectedIndex(0);      
      f_source.setText("");
      f_departure.setText("");
      ((JTextField)f_setflight.getDateEditor().getUiComponent()).setText("");
      f_departuretime.setText("");
      f_airtime.setText("");
      f_flightcharge.setText("");
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
             
         }
        
    }   
        
    }//GEN-LAST:event_f_updateActionPerformed

    private void f_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_deleteActionPerformed
    
    if(!jTable_flight.getSelectionModel().isSelectionEmpty()){
    
    int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
      
       
        try{
            int row =jTable_flight.getSelectedRow();
            String value = (jTable_flight.getModel().getValueAt(row, 0).toString());
            String sql ="DELETE FROM tbl_flight WHERE flightid='"+value+"'";
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
            showFlight();
            showCombo_CustomerNo();
            showFlightID();
            showTotal_Flight();
            showNotif_Flight();
            
            }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        } 
    }  
    }//GEN-LAST:event_f_deleteActionPerformed

    private void t_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_searchActionPerformed
        String source = cmb_source.getSelectedItem().toString();
        String depart = cmb_departure.getSelectedItem().toString();
        
        try {
            String query ="SELECT * FROM tbl_flight WHERE source='"+source+"' AND departure='"+depart+"'";
            pst=conn.prepareStatement(query);           
            rs=pst.executeQuery();
            jTable_flightdetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            ResultSetMetaData rsm = rs.getMetaData();
            int c;
            c = rsm.getColumnCount();
            DefaultTableModel dtm = (DefaultTableModel)jTable_flightdetail.getModel();
            dtm.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("flightid"));
                    v2.add(rs.getString("customer_no"));                    
                    v2.add(rs.getString("flight_name"));
                    v2.add(rs.getString("source"));
                    v2.add(rs.getString("departure"));
                    v2.add(rs.getString("setflight"));
                    v2.add(rs.getString("departure_time"));
                    v2.add(rs.getString("air_time"));
                    v2.add(rs.getString("flight_charge"));                    
                }
                dtm.addRow(v2);
            }                      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }//GEN-LAST:event_t_searchActionPerformed

    private void jTable_flightdetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_flightdetailMouseClicked
        
        DefaultTableModel dtm = (DefaultTableModel)jTable_flightdetail.getModel();
        int selectedrowIndex = jTable_flightdetail.getSelectedRow();
        
        t_flightno.setText(dtm.getValueAt(selectedrowIndex, 0).toString());
        t_flightname.setText(dtm.getValueAt(selectedrowIndex, 2).toString());
        t_depttime.setText(dtm.getValueAt(selectedrowIndex, 6).toString());
        
    }//GEN-LAST:event_jTable_flightdetailMouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
       int price = Integer.parseInt(t_price.getText());
       int qty = Integer.parseInt(jSpinner1.getValue().toString());
       
       int total = price * qty;
       t_total.setText(String.valueOf(total));
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
     
    try{          
         String sql = "SELECT *,CONCAT(Firstname,' ',lastname,' ',middlename) AS FULLNAME FROM tbl_customer WHERE customer_no='"+t_Customerno.getText()+"'";            
         pst=conn.prepareCall(sql);
         rs=pst.executeQuery();
            if(rs.next()==false) {
                  JOptionPane.showMessageDialog(rootPane, "Record Not Found!..");
            }else{
                String fname = rs.getString("FULLNAME");      
                String passs = rs.getString("passportid");     
                
                t_fullname.setText(fname.trim());
                t_passportid.setText(passs.trim());
                
            }  
        }catch(SQLException e) {
        JOptionPane.showMessageDialog(null, e);      
    } 
    }//GEN-LAST:event_jButton10ActionPerformed
private void clearfield(){
    
        showTicketNo();
        t_Customerno.setText("");
        t_fullname.setText("");
        t_passportid.setText("");
        t_flightno.setText("");
        t_flightname.setText("");
        t_depttime.setText("");
        cmb_class.setSelectedItem("Select");
        t_price.setText("");
        t_total.setText("");
        
}
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    
    int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Add Record?","AddRecord",JOptionPane.YES_NO_OPTION);
    if(x==0){ 
        
        if(t_Customerno.getText().isEmpty()
            ||t_fullname.getText().isEmpty()
            ||t_passportid.getText().isEmpty()
            ||t_flightno.getText().isEmpty()
            ||t_depttime.getText().isEmpty()
            ||t_flightname.getText().isEmpty()
            ||t_price.getText().isEmpty()
            ||t_total.getText().isEmpty()
            ||cmb_class.getSelectedIndex()==0){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Complete Flight Information","Missing Information",2);  
        }else{
    
            
            
            try {
                String sql = "INSERT INTO `tbl_ticket`(`TicketNo`, `CustomerNo`, `FullName`, `PassportID`, `FlightNo`, `FlightName`, `Dept_Time`, `Class`, `Seat`, `Price`, `Amount`, `Status`)"
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,'Available')";
                pst=conn.prepareStatement(sql);
                pst.setString(1,label_ticket.getText().toUpperCase());
                pst.setString(2,t_Customerno.getText().toUpperCase());
                pst.setString(3,t_fullname.getText().toUpperCase());
                pst.setString(4,t_passportid.getText());
                pst.setString(5,t_flightno.getText());
                pst.setString(6,t_flightname.getText());
                pst.setString(7,t_depttime.getText());
                pst.setString(8,cmb_class.getSelectedItem().toString());
                pst.setString(9,jSpinner1.getValue().toString());
                pst.setString(10,t_price.getText());
                pst.setString(11,t_total.getText());                
                pst.execute();
                JOptionPane.showMessageDialog(null,"Ticket has been Reserved","Success Operation",1);
                clearfield();
                showTicket();
                showJListcombo();
                showNotif_Reserved();
                showTotal_Reserved();
                showSchedFlight();
                showTotal_CancelFlight();
                
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
        
        
    }     
       
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
    try{
            String list = (String)jList1.getSelectedValue();
            String sql = "SELECT * FROM tbl_ticket WHERE fullname=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,list);
            rs=pst.executeQuery();
            if(rs.next()) {
   
             label_1.setText(rs.getString("ticketno"));
             label_2.setText(rs.getString("customerno"));
             label_3.setText(rs.getString("fullname"));
             label_4.setText(rs.getString("passportid"));
             label_5.setText(rs.getString("flightno"));
             label_6.setText(rs.getString("flightname"));
             label_7.setText(rs.getString("dept_time"));
             label_8.setText(rs.getString("price"));
             label_9.setText(rs.getString("seat"));
             label_10.setText(rs.getString("class"));
             label_amount.setText(rs.getString("amount"));
             label_record.setText(rs.getString("date_record"));
             
          }           
        }catch(Exception e) {          
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data!");        
        job.setPrintable(new Printable() {        
        public int print(Graphics pg, PageFormat pf, int pageNum) {
            if(pageNum>0) {
                return Printable.NO_SUCH_PAGE;  
            }
            Graphics2D g2 = (Graphics2D)pg;
            g2.translate(pf.getImageableX(),pf.getImageableY());
            g2.scale(0.80,0.8);                        
            panelreceipt.paint(g2);
            return Printable.PAGE_EXISTS;           
        }       
    });        
    boolean ok = job.printDialog();    
        if(ok) {            
            try{
                job.print();
                JOptionPane.showMessageDialog(null,"Print Success");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error!");
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     
    int x = JOptionPane.showConfirmDialog(null,"Are you sure you want to update?","System message!",JOptionPane.OK_OPTION); 
    if (x == 0) {  

        
        if(label_1.getText().isEmpty()
            ||label_2.getText().equals("--------------------------------")
            ||label_3.getText().equals("--------------------------------")
            ||label_4.getText().equals("--------------------------------")
            ||label_5.getText().equals("--------------------------------")
            ||label_6.getText().equals("--------------------------------")
            ||label_7.getText().equals("--------------------------------")
            ||label_8.getText().equals("--------------------------------")
            ||label_9.getText().equals("--------------------------------")
            ||label_10.getText().isEmpty()
            ||label_amount.getText().isEmpty()
            ||label_record.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Complete Flight Information","Missing Information",2);  
        }else{
            
           
            try { 
                String sql = "UPDATE `tbl_ticket` SET "   
                    + "Status='Not Available' WHERE TicketNo='"+label_1.getText()+"'";
                pst=conn.prepareStatement(sql);                    
                pst.execute();
                JOptionPane.showMessageDialog(null,"Ticket has been Cancel Reserved","Success Operation",1);
                clearfield();
                showSchedFlight();
                showTotal_CancelFlight();
                showTotal_Reserved();
                showNotif_Reserved();
                showNotif_CancelFlight();
                showTicket();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
            
        }
        
        
    }     
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MessageFormat header = new MessageFormat("Report");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try{
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
        }catch(java.awt.print.PrinterException e){
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MessageFormat header = new MessageFormat("Report");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try{
            jTable_ticket.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
        }catch(java.awt.print.PrinterException e){
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to export?","Operation", JOptionPane.YES_NO_OPTION);
        if(YesOrNo == 1)
        {
            //IF CLICK NO
        }
        if(YesOrNo == 0)
        {

            DefaultTableModel model = (DefaultTableModel)jTable_ticket.getModel();
            String currentDirectoryFilePath = "C:\\Users\\Authentic\\Desktop";
            JFileChooser excelExportChooser = new JFileChooser(currentDirectoryFilePath);

            //Filter Only Excel Files
            FileNameExtensionFilter excelFNEF = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
            excelExportChooser.setFileFilter(excelFNEF);
            excelExportChooser.setDialogTitle("Save Excel...");
            int excelChooser = excelExportChooser.showSaveDialog(null);

            if(excelChooser == JFileChooser.APPROVE_OPTION){

                XSSFWorkbook excelXSSFWorkbookExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelXSSFWorkbookExporter.createSheet("JTable Data");

                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(model.getValueAt(i,j).toString());
                    }
                }
                FileOutputStream excelFIS;
                BufferedOutputStream excelBOU;
                try {

                    excelFIS = new FileOutputStream(excelExportChooser.getSelectedFile()+ ".xlsx");
                    excelBOU = new BufferedOutputStream(excelFIS);
                    excelXSSFWorkbookExporter.write(excelBOU);
                    excelBOU.close();
                    excelXSSFWorkbookExporter.close();

                    JOptionPane.showMessageDialog(rootPane,"Data Exported Successfully!...");
                } catch (IOException ex) {

                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     if(!jTable_ticket.getSelectionModel().isSelectionEmpty()){
    
    int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
      
       
        try{
            int row =jTable_ticket.getSelectedRow();
            String value = (jTable_ticket.getModel().getValueAt(row, 0).toString());
            String sql ="DELETE FROM tbl_ticket WHERE ticketno='"+value+"'";
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
            showTicket();
            showTotal_CancelFlight();
            showSchedFlight();
            showTotal_Flight();
            showTotal_Reserved();
            
        }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        } 
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 int x = JOptionPane.showConfirmDialog(null,"Are you sure you want to update?","System message!",JOptionPane.OK_OPTION); 
    if (x == 0) {  
        
        if(!jTable_ticket.getSelectionModel().isSelectionEmpty()){
        
          try { 
                int row = jTable_ticket.getSelectedRow();
                String value = (jTable_ticket.getModel().getValueAt(row, 0).toString());
                String sql = "UPDATE `tbl_ticket` SET "   
                    + "Status='Available' WHERE TicketNo='"+value+"'";
                pst=conn.prepareStatement(sql);                    
                pst.execute();
                JOptionPane.showMessageDialog(null,"Ticket has been Update Reserved","Success Operation",1);
                clearfield();
                showSchedFlight();
                showNotif_Flight();
                showNotif_Reserved();
                showTicket();
                showTotal_Reserved();
                showTotal_CancelFlight();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }
    }
    }//GEN-LAST:event_jButton5ActionPerformed

//COMBOBOX DATABASE
private void showCombo_CustomerNo() { 
    try {
            String sql ="SELECT DISTINCT customer_no FROM tbl_customer";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();           
            while(rs.next()) {
                String value1 = rs.getString("customer_no");
                f_cmb_customerno.addItem(value1);
            }
        }catch (Exception e){            
            JOptionPane.showMessageDialog(null, e);  
        }           
    } 

//COMBOBOX DATABASE
private void showCombo_FlightSource() {
        try {
            String sql ="SELECT source FROM tbl_flight";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();           
            while(rs.next()) {
                String value2 = rs.getString("source");
                cmb_source.addItem(value2);
            }
        }catch (Exception e){            
            JOptionPane.showMessageDialog(null, e);  
        }           
    } 

private void showCombo_Departure() {
        try {
            String sql ="SELECT departure FROM tbl_flight";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();           
            while(rs.next()) {
                String value3 = rs.getString("Departure");
                cmb_departure.addItem(value3);
            }
        }catch (Exception e){            
            JOptionPane.showMessageDialog(null, e);  
        }           
    } 

//JLIST DATABASE
public void showJListcombo(){
    DefaultListModel DLM = new DefaultListModel();
    try {
        String sql = "SELECT * FROM tbl_ticket";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
            String add = rs.getString("Fullname");
            DLM.addElement(add);
        }
        jList1.setModel(DLM);
        
    } catch (Exception e) {
    }
}
    
    
    
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
                /*if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BTN1;
    private javax.swing.JPanel BTN2;
    private javax.swing.JPanel BTN3;
    private javax.swing.JPanel BTN4;
    private javax.swing.JPanel BTN5;
    private javax.swing.JPanel BTN6;
    private javax.swing.JPanel BTN7;
    private javax.swing.JLabel Count_Reserved;
    private javax.swing.JLabel Count_customers;
    private javax.swing.JLabel Count_flight;
    private javax.swing.JLabel Date;
    private javax.swing.JPanel Flight;
    private javax.swing.JPanel PN1;
    private javax.swing.JPanel PN2;
    private javax.swing.JPanel PN3;
    private javax.swing.JPanel PN4;
    private javax.swing.JPanel PN5;
    private javax.swing.JPanel PN6;
    private javax.swing.JPanel PN7;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JLabel Time;
    private javax.swing.JPanel book;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton c_add;
    private javax.swing.JTextArea c_address;
    private com.toedter.calendar.JDateChooser c_birthdate;
    private javax.swing.JButton c_clear;
    private javax.swing.JTextField c_contact;
    private javax.swing.JTextField c_customerno;
    private javax.swing.JButton c_delete;
    private javax.swing.JRadioButton c_female;
    private javax.swing.JTextField c_firstname;
    private javax.swing.JTextField c_lastname;
    private javax.swing.JRadioButton c_male;
    private javax.swing.JTextField c_middlename;
    private javax.swing.JTextField c_passportid;
    private javax.swing.JButton c_update;
    private javax.swing.JPanel cardpanel;
    private javax.swing.JComboBox<String> cmb_class;
    private javax.swing.JComboBox<String> cmb_departure;
    private javax.swing.JComboBox<String> cmb_source;
    private javax.swing.JPanel customer;
    private javax.swing.JPanel dashboard;
    private javax.swing.JButton f_add;
    private javax.swing.JTextField f_airtime;
    private javax.swing.JButton f_clear;
    private javax.swing.JComboBox<String> f_cmb_customerno;
    private javax.swing.JComboBox<String> f_cmb_flightname;
    private javax.swing.JButton f_delete;
    private javax.swing.JTextField f_departure;
    private javax.swing.JTextField f_departuretime;
    private javax.swing.JTextField f_flightcharge;
    private javax.swing.JTextField f_flightid;
    private com.toedter.calendar.JDateChooser f_setflight;
    private javax.swing.JTextField f_source;
    private javax.swing.JButton f_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_cancelflight;
    private javax.swing.JTable jTable_customers;
    private javax.swing.JTable jTable_flight;
    private javax.swing.JTable jTable_flightdetail;
    private javax.swing.JTable jTable_schedflight;
    private javax.swing.JTable jTable_ticket;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label_1;
    private javax.swing.JLabel label_10;
    private javax.swing.JLabel label_2;
    private javax.swing.JLabel label_3;
    private javax.swing.JLabel label_4;
    private javax.swing.JLabel label_5;
    private javax.swing.JLabel label_6;
    private javax.swing.JLabel label_7;
    private javax.swing.JLabel label_8;
    private javax.swing.JLabel label_9;
    private javax.swing.JLabel label_amount;
    private javax.swing.JLabel label_cancel;
    private javax.swing.JLabel label_record;
    private javax.swing.JLabel label_ticket;
    public static javax.swing.JLabel label_user;
    private javax.swing.JPanel panelhead;
    private javax.swing.JPanel panelreceipt;
    private javax.swing.JPanel setting;
    private javax.swing.JTextField t_Customerno;
    private javax.swing.JTextField t_depttime;
    private javax.swing.JTextField t_flightname;
    private javax.swing.JTextField t_flightno;
    private javax.swing.JTextField t_fullname;
    private javax.swing.JTextField t_passportid;
    private javax.swing.JTextField t_price;
    private javax.swing.JButton t_search;
    private javax.swing.JLabel t_total;
    private javax.swing.JPanel ticket;
    // End of variables declaration//GEN-END:variables

//POPULATE TABLE    
public void showSchedFlight(){
    try {
        String sql = "SELECT `TicketNo`, `CustomerNo`, `FullName`, `PassportID`, `FlightNo`, `FlightName`, `Dept_Time`, `Class`, `Seat`, `Price`, `Amount`,`Status` FROM tbl_ticket WHERE status='Available'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        jTable_schedflight.setModel(DbUtils.resultSetToTableModel(rs));
        jTable_schedflight.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    } catch (Exception e) {
    }
    
    
    try {
        String sql = "SELECT `TicketNo`, `CustomerNo`, `FullName`, `PassportID`, `FlightNo`, `FlightName`, `Dept_Time`, `Class`, `Seat`, `Price`, `Amount`, `Status` FROM tbl_ticket WHERE Status='Not available'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        jTable_cancelflight.setModel(DbUtils.resultSetToTableModel(rs));
        jTable_cancelflight.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    } catch (Exception e) {
    }
    
    
}    

private void showUser(){
    try {
        String query = "SELECT Userid,username,date_login FROM tbl_user";
        pst=conn.prepareStatement(query);
        rs=pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }    
}

private void showCustomer(){
    try {
        String query = "SELECT * FROM tbl_customer";
        pst=conn.prepareStatement(query);
        rs=pst.executeQuery();
        jTable_customers.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }    
}

private void showFlight(){
    try {
        String query = "SELECT * FROM `tbl_flight`";
        pst=conn.prepareStatement(query);
        rs=pst.executeQuery();
        jTable_flight.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }    
}

private void showTicket(){
    try {
        String query = "SELECT * FROM `tbl_ticket`";
        pst=conn.prepareStatement(query);
        rs=pst.executeQuery();
        jTable_ticket.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }    
}


//COUNT
public void showTotal_Customers(){         
    try {
       String sql = "SELECT COUNT(*) CUSTOMER_NO FROM tbl_customer";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("CUSTOMER_NO");
                    Count_customers.setText(sum);                   
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }      
}  

//NOTIFICATION
public void showNotif_Customers(){
      try {
       String sql = "SELECT COUNT(*) CUSTOMER_NO FROM tbl_customer";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("CUSTOMER_NO");
                    Count_customers.setText(sum); 
                    Toaster notifys = new Toaster();
                    ImageIcon ic = new ImageIcon(getClass().getResource("/Image/pushnotif.png"));
                    notifys.showToaster(ic,"\nTotal of Customers \n"+sum);  
                    notifys.setDisplayTime(10000);
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }  
}

public void showTotal_Flight(){         
    try {
       String sql = "SELECT COUNT(*) FLIGHTID FROM tbl_flight";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("FLIGHTID");
                    Count_flight.setText(sum); 
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }      
}  

public void showTotal_CancelFlight(){         
    try {
       String sql = "SELECT COUNT(*) FLIGHTID FROM tbl_ticket WHERE STATUS='Not Available'";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("FLIGHTID");
                    label_cancel.setText(sum); 
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }      
}  

//NOTIFICATION
public void showNotif_Flight(){
    try {
       String sql = "SELECT COUNT(*) FLIGHTID FROM tbl_flight";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("FLIGHTID");
                    Toaster notifys = new Toaster();
                    ImageIcon ic = new ImageIcon(getClass().getResource("/Image/pushnotif.png"));
                    notifys.showToaster(ic,"\nTotal of Flight \n"+sum);  
                    notifys.setDisplayTime(10000);
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }  
}

public void showTotal_Reserved(){         
    try {
       String sql = "SELECT COUNT(*) TICKETNO FROM tbl_ticket WHERE STATUS='Available'";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("TICKETNO");
                    Count_Reserved.setText(sum); 
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }      
}  

public void showNotif_Reserved(){
    try {
       String sql = "SELECT COUNT(*) TICKETNO FROM tbl_ticket WHERE STATUS='Available'";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("TICKETNO");
                    Toaster notifys = new Toaster();
                    ImageIcon ic = new ImageIcon(getClass().getResource("/Image/pushnotif.png"));
                    notifys.showToaster(ic,"\nTotal of Reseved Book \n"+sum);  
                    notifys.setDisplayTime(10000);
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }  
}

public void showNotif_CancelFlight(){         
    try {
       String sql = "SELECT COUNT(*) FLIGHTID FROM tbl_ticket WHERE STATUS='Not Available'";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum = rs.getString("FLIGHTID");
                    Toaster notifys = new Toaster();
                    ImageIcon ic = new ImageIcon(getClass().getResource("/Image/pushnotif.png"));
                    notifys.showToaster(ic,"\nTotal of Reseved Book \n"+sum); 
                }         
    }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e);
    }      
} 
}
