package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import main.Driver;

public class ViewAnalytics extends Artist{
	
	Connection c = null;
	
    ViewAnalytics(String uid,String pwd,Connection c) throws Exception{
    	
		super(c);
		login(uid,pwd);
	}
    
    public void ViewAnalyticsUI(JFrame fr,String uid,String pass,Connection c) {
    	
    	
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
    		            	Artist ar = new Artist(c);
							JFrame newFr = new JFrame("Artist");
							ar.ArtistUI(newFr, uid, pass,c);
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
    		
    		 panel.setLayout(null);
    		    fr.setAlwaysOnTop(true);
    		    
    
    		    JTextField t2 = new JTextField();
    		    t2.setBounds(200, 200, 200, 25);
    		    
    		    
    		    JLabel l2 = new JLabel("Song Name :");
    		    l2.setBounds(100, 200, 100, 25);
    		
    		JButton b2 = new JButton("View");
    		
    		
    		 b2.setBounds(450,200,150,25);
    	
    	
    		 b2.addActionListener(new ActionListener() {

    			 @Override
			public void actionPerformed(ActionEvent arg0) {
				
				String trackName = t2.getText();
				
				try {
					
					getInfo(trackName,c,uid,pass);
					fr.setVisible(false);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
				
			}
			
		});
    	
	   
	    panel.add(b2);
	    panel.add(t2);
	    panel.add(l2);
		
		fr.setSize(800, 600);
		fr.setVisible(true);
    }
    
    public void getInfo(String trackName,Connection conn,String uid,String pass) throws Exception{
		
    	
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
							Artist ar = new Artist(conn);
							JFrame newFr = new JFrame("Artist");
							ar.ArtistUI(newFr, uid, pass, conn);
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
        dtm.addColumn("Album ID");
        dtm.addColumn("Title");
        dtm.addColumn("Song Name");
        dtm.addColumn("Artist");
        dtm.addColumn("Copyright");
		
        PreparedStatement ps=null;
        
		ps = conn.prepareStatement("select Album_ID ,Title,Song_Name,Artist_Name,copyright from Artist natural join Album natural join Song natural join Creates where Song_Name = ?;");
		ps.setString(1,trackName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			String albumID = rs.getString(1);
			String albumName =  rs.getString(2);
			String songName = rs.getString(3);
			String artistName = rs.getString(4);
			boolean copyright = rs.getBoolean(5);
			
			dtm.addRow(new Object[] {albumID,albumName,songName,artistName,copyright});
			
			
		}
		
		fr.setLayout(null);
        fr.setSize(800, 600);
        fr.setVisible(true);
	}
	
    PreparedStatement ps=null;
    
    public void View() throws SQLException{
    	
        Scanner input=new Scanner(System.in);    
        
        ps=c.prepareStatement("select Song_Name From Creates natural join song natural join users where uid=?");
        ps.setString(1,uid);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
        	
            String songName = rs.getString(1);
            System.out.println("Songs are :" + songName);
            
        }
}
    
    public void share() throws SQLException{
    	
    	//TO-DO
        
    }
    
    public void reportIssue() throws SQLException{
    	
        System.out.println("Issue reported");
    }
    
}