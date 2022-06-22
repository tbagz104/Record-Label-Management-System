package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import javax.swing.*;

public class ManageAlbum extends Admin{
	
	Connection c = null;
	
 	ManageAlbum (Connection c, String uid, String pwd) throws SQLException {
 		
		super(c);
        login(uid, pwd);
        this.c = c;
    }
 	
public void ManageAlbumUI(JFrame fr,String uid,String pass,Connection c) {
		
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
	    t2.setBounds(200, 200, 200, 25);
	    
	    JLabel l1 = new JLabel("Album Name:");
	    l1.setBounds(100,100, 100, 25);
	    
	    JLabel l2 = new JLabel("Album Name:");
	    l2.setBounds(100, 200, 100, 25);
	    
	    JButton b1 = new JButton("Remove Album");
	    JButton b2 = new JButton("Get-Info");
	    
	    b1.setBounds(450,100,150,25);
	    b2.setBounds(450,200,150,25);
	    
	    panel.add(t1);
	    panel.add(b1);
	    panel.add(t2);
	    panel.add(b2);
	    panel.add(l1);
	    panel.add(l2);
	    
	    panel.setSize(800, 600);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String title = t1.getText();
				
				try {
					
					if(removeAlbum(title,c)) {
						
						JOptionPane.showMessageDialog(fr, "Album Removed", "Success", JOptionPane.PLAIN_MESSAGE);
						
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
				
				String title = t2.getText();
				
				try {
					
					getInfo(title,c,uid,pass);
					fr.setVisible(false);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
			}
			
		});
		
		
		panel.add(b1);
		panel.add(b2);
		
		fr.setSize(800, 600);
		fr.setVisible(true);
		
		
	}

	public boolean removeAlbum(String title,Connection conn) throws Exception {
		
		if(title.equals(""))
			return false;
		
		else {
			PreparedStatement ps=null;
			
			ps = conn.prepareStatement("delete from Album where Title = ?;");
			ps.setString(1,title);
			ps.executeUpdate();
			//System.out.println("Album Removed");
			
			return true;
		}
	}
	
	/*public void getInfoUI() {
		
		JFrame fr=new JFrame("Results");
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		fr.add(panel);
		
		fr.setLayout(null);
        fr.setSize(800, 600);
        
        DefaultTableModel dtm = new DefaultTableModel();
        JTable table = new JTable(dtm);
        
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        
        panel.add(new JScrollPane(table));
        
        panel.setBounds(25, 25, 800, 600);
        
        dtm.addColumn("artist name");
        dtm.addColumn("song");
		
		
	}*/
	
	public void getInfo(String title,Connection conn,String uid,String pass) throws Exception{
		
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
		
		panel.setLayout(new FlowLayout());
		panel.setSize(800,600);
        
        DefaultTableModel dtm = new DefaultTableModel();
        JTable table = new JTable(dtm);
        
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        
        panel.add(new JScrollPane(table));
        dtm.addColumn("Album ID");
        dtm.addColumn("Title");
        dtm.addColumn("No. Of Songs");
		
		PreparedStatement ps=null;
		
		ps = conn.prepareStatement("select Album_ID,Title, count(Song_ID) as Song_Count from Song natural join Album where Title = ? group by Album_ID,Title;");
		ps.setString(1,title);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			String albumID = rs.getString(1);
			String albumName =  rs.getString(2);
			int songCount = rs.getInt(3);
			
			dtm.addRow(new Object[] {albumID,albumName,songCount});
			
			/*System.out.println();	
			System.out.println("Album ID: " + albumID);
            System.out.println("Album Name: " + albumName);
            System.out.println("No. of Songs: " + songCount); */
		}
		
		fr.setLayout(null);
        fr.setSize(800, 600);
        fr.setVisible(true);
	}
}
