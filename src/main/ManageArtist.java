package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import main.Driver;
import main.Admin.editProfileGUIadmin;

public class ManageArtist extends Admin {
	
    ManageArtist(Connection c, String uid, String pwd) throws SQLException {
    	
        super(c);
        login(uid, pwd);
    }
    
    private void line(){System.out.println("----------------------");}
    private void name(){System.out.println("Enter artist name: ");}

public void ManageArtistUI(JFrame fr,String uid,String pass,Connection c) {
		
	JMenu menu;  
    JMenu a1,a2;
      
    menu = new JMenu("Home");
    JMenuBar m1 = new JMenuBar();
    a1 = new JMenu("Menu");
    a2 = new JMenu("Logout");
    m1.add(menu);
    m1.add(a1);
    m1.add(a2);
      
    fr.setJMenuBar(m1);
   
	a1.addMenuListener(new MenuListener() {

		@Override
		public void menuCanceled(MenuEvent e) {
			
			
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			
			
		}

		@Override
		public void menuSelected(MenuEvent e) {
			
		int result = JOptionPane.showConfirmDialog(fr,"Sure? You want to Go to Menu?", "Confirm",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
			
		            if(result == JOptionPane.YES_OPTION){
		              
		            	//System.out.println("JMenu Clicked.");
						Admin ad = new Admin(c);
						JFrame newFr = new JFrame("Admin");
						ad.AdminGUI(newFr, uid, pass,c);
						fr.setVisible(false);
		            	
		            	
		            }else if (result == JOptionPane.NO_OPTION){
		            	
		       
		            }
		            
		            else {
		               
		            	
		            }
		            
		}
		
	});
	
	a2.addMenuListener(new MenuListener() {

		@Override
		public void menuCanceled(MenuEvent e) {
			
			
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			
			
		}

		@Override
		public void menuSelected(MenuEvent e) {
			
			JOptionPane.showMessageDialog(fr, "Logged Out Successfully", "Success", JOptionPane.OK_CANCEL_OPTION);
			
			//System.out.println("JMenu Clicked.");
			Admin ad = new Admin(c);
			Artist ar = new Artist(c);
			Driver d = new Driver();
			fr.setVisible(false);
			Driver.LoginUI(ad, ar, c);
			
			
		}
		
	});
		
	JPanel panel = new JPanel();
	fr.add(panel);
		
	panel.setLayout(null);
    fr.setAlwaysOnTop(true);
    
    JTextField t1 = new JTextField();
    t1.setBounds(200, 100, 200, 25);
    
    JTextField t2 = new JTextField();
    t2.setBounds(200, 210, 200, 25);
    
    JTextField t3 = new JTextField();
    t3.setBounds(200, 310, 200, 25);
    
    JTextField t4 = new JTextField();
    t4.setBounds(200,130,200,25);
    
    JLabel l1 = new JLabel("Artist ID:");
    l1.setBounds(100,100 , 100, 25);

    JLabel l2 = new JLabel("Artist ID:");
    l2.setBounds(100, 210 , 100, 25);
    
    JLabel l3 = new JLabel("Artist ID:");
    l3.setBounds(100, 310, 100, 25);
    
    JLabel l4 = new JLabel("Artist Name :");
    l4.setBounds(100, 130, 100, 25);
    
    JButton b1 = new JButton("Add Artist");
    JButton b2 = new JButton("Remove Artist");
    JButton b3 = new JButton("Get-Info");
    
    b1.setBounds(450,125,150,25);
    b2.setBounds(450,210,150,25);
    b3.setBounds(450,310,150,25);
    
    panel.add(t1);
    panel.add(b1);
    panel.add(t4);
    panel.add(t2);
    panel.add(b2);
    panel.add(t3);
    panel.add(b3);
    panel.add(l1);
    panel.add(l2);
    panel.add(l3);
    panel.add(l4);
    
    panel.setSize(800, 600);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String id = t1.getText();
				String name = t4.getText();
				
				try {
					
					if(insertartist(name,id)) {
						
						JOptionPane.showMessageDialog(fr, "Artist Added", "Success", JOptionPane.PLAIN_MESSAGE);
						
					}
					
					else {
						
						JOptionPane.showMessageDialog(fr, "Update Failed.", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
				
			}
			
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String id = t2.getText();
				
				try {
					if(removeArtist(id)) {
						
						JOptionPane.showMessageDialog(fr, "Artist Removed", "Success", JOptionPane.PLAIN_MESSAGE);
						
					}
					
					else {
						
						JOptionPane.showMessageDialog(fr, "Update Failed.", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
				} catch (HeadlessException | SQLException e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String id = t3.getText();
				
				try {
					
					getInfo(id,c,uid,pass);
					fr.setVisible(false);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		
		fr.setSize(800, 600);
		fr.setVisible(true);
		
		
	}
    
    private String newId(String name) throws SQLException {
    	
        String id = "";
        rs = st.executeQuery("select * from artist");
        while(rs.next()){
            if(name.equals(rs.getString("artist_name"))){
                break;
            }
            id = rs.getString("artist_id");
        }
        return id.substring(0, 2)+Integer.toString(Integer.parseInt(id.substring(2))+1);
    }

    private void insertusr(String name, String pwd, String aid) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
		ps.setString(1,name);
        ps.setString(2,pwd);
        ps.setString(3,"ARTIST");
        ps.setString(4,aid);
		ps.executeUpdate();
    }

    private boolean insertartist(String name, String aid) throws SQLException {
    	
    	if(name.equals("") || aid.equals("")) {
    		
    		return false;
    	}
    	
    	else {
    		
	        ps = conn.prepareStatement("INSERT INTO artist VALUES (?, ?)");
	        ps.setString(1,aid);
	        ps.setString(2,name);
			ps.executeUpdate();
			
			return true;
	    }
    }

    private void insertaddr(String aid, String addr) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO address VALUES (?, ?)");
        ps.setString(1,addr);
        ps.setString(2,aid);
		ps.executeUpdate();
    }

    public void addArtist() throws SQLException {
    	
        System.out.println("Enter artist name and password: ");
        String name = input.nextLine();
        String pwd = input.nextLine();
        String aid = newId(name);
        insertartist(name, aid);
        insertusr(name, pwd, aid);

        System.out.println("If addr enter (else press enter-key): ");
        String addr = input.nextLine();
        if(addr != null)
            insertaddr(aid, addr);
    }

    private String getId(String name) throws SQLException {
    	
        String id="";
        rs = st.executeQuery("select * from artist");
        while(rs.next()){
            if(name.equals(rs.getString("artist_name"))){
                id = rs.getString("artist_id");
            }
        }
        if(id.equals("")){
            System.out.println("Name not found..");
            return "";
        }
        return id;
    }

    public boolean removeArtist(String id) throws SQLException {
    	
    	if(id.equals("")) {
    		return false;
    	}
    	
    	else {
    		
	        ps = conn.prepareStatement("delete from artist where artist_id=?");
	        ps.setString(1,id);
			ps.executeUpdate();
			
			return true;
	    }
    }

    private void printSongs(String id) throws SQLException {
    	
        System.out.println("Artist Song Details: ");
        ps = conn.prepareStatement("select * from Artist natural join Creates natural join song where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            }
        }
        line();
    }

    private void printAlbums(String id) throws SQLException {
    	
        System.out.println("Artist Album Details: ");
        ps = conn.prepareStatement("select * from Artist natural join Creates natural join album where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            }
        }
        line();
    }

    private void printContracts(String id) throws SQLException {
    	
    
        ps = conn.prepareStatement("select * from Artist natural join is_hired natural join contract where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        
        while (rs.next()) {
        	
       
            String contractID = rs.getString(1);
            String licenseNo = rs.getString(2);
            String artistID = rs.getString(3);
            String artistName = rs.getString(4);
            String contract_Expriry = rs.getString(5);
            String wages = rs.getString(6);
            
            //System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            
           
            
        }
        
    }

    public void getInfo() throws SQLException {
        name();
        String name = input.nextLine();
        String id = getId(name);
        printSongs(id);
        printAlbums(id);
    }

    public void contract() throws SQLException {
        name();
        String name = input.nextLine();
        String id = getId(name);
        printContracts(id);
        
    }
    
    public void getInfo(String id,Connection conn,String uid,String pass) throws Exception{
		
		JFrame fr=new JFrame("Results");
		
		JMenu menu;  
	    JMenu a1,a2;
	      
	    menu = new JMenu("Home");
	    JMenuBar m1 = new JMenuBar();
	    a1 = new JMenu("Menu");
	    a2 = new JMenu("Logout");
	    m1.add(menu);
	    m1.add(a1);
	    m1.add(a2);
	      
	    fr.setJMenuBar(m1);
	   
		a1.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				
				
			}

			@Override
			public void menuSelected(MenuEvent e) {
				
			int result = JOptionPane.showConfirmDialog(fr,"Sure? You want to Go to Menu?", "Confirm",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
				
			            if(result == JOptionPane.YES_OPTION){
			              
			            	//System.out.println("JMenu Clicked.");
							Admin ad = new Admin(conn);
							JFrame newFr = new JFrame("Admin");
							ad.AdminGUI(newFr, uid, pass,conn);
							fr.setVisible(false);
			            	
			            	
			            }else if (result == JOptionPane.NO_OPTION){
			            	
			       
			            }
			            
			            else {
			               
			            	
			            }
			            
			}
			
		});
		
		a2.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				
				
			}

			@Override
			public void menuSelected(MenuEvent e) {
				
				JOptionPane.showMessageDialog(fr, "Logged Out Successfully", "Success", JOptionPane.OK_CANCEL_OPTION);
				
				//System.out.println("JMenu Clicked.");
				Admin ad = new Admin(conn);
				Artist ar = new Artist(conn);
				Driver d = new Driver();
				fr.setVisible(false);
				Driver.LoginUI(ad, ar, conn);
				
				
			}
			
		});
		
		
		JPanel panel = new JPanel();
		fr.add(panel);
		
		panel.setLayout(new FlowLayout());
		panel.setSize(800,600);
        
        DefaultTableModel dtm = new DefaultTableModel();
        JTable table = new JTable(dtm);
        
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        
        panel.add(new JScrollPane(table));
        dtm.addColumn("Artist ID");
        dtm.addColumn("Artist Name");
        dtm.addColumn("Contract ID");
        dtm.addColumn("License No");
        dtm.addColumn("Contract Expiry");
        dtm.addColumn("Wages");
		
        
        ps = conn.prepareStatement("select * from Artist natural join is_hired natural join contract where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        
        while (rs.next()) {
        	
       
            String contractID = rs.getString(1);
            String licenseNo = rs.getString(2);
            String artistID = rs.getString(3);
            String artistName = rs.getString(4);
            String contract_Expiry = rs.getString(5);
            String wages = rs.getString(6);
            
            //System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            
            dtm.addRow(new Object[] {artistID,artistName,contractID,licenseNo,contract_Expiry,wages});
           
            
        }
        
        fr.setLayout(null);
        fr.setSize(800, 600);
        fr.setVisible(true);
	}
}
