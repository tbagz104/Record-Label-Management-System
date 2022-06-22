package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class EditMusic extends Artist{
	
	Connection c = null;
	
	EditMusic(String uid,String pwd,Connection c) throws Exception {
		
		super(c);
		login(uid,pwd);
		
	}
	
	public void EditMusicUI(JFrame fr,String uid,String pass,Connection c) {
		
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
	    
	    JTextField t1 = new JTextField();
	    t1.setBounds(200, 100, 200, 25);
	    JTextField t2 = new JTextField();
	    t2.setBounds(200, 200, 200, 25);
	    
	    JLabel l1 = new JLabel("Album Name :");
	    l1.setBounds(100,100 , 100, 25);
	    
	    JLabel l2 = new JLabel("Song Name :");
	    l2.setBounds(100, 200, 100, 25);
	    
	    JButton b1 = new JButton("Remove Album");
	    JButton b2 = new JButton("Remove Song");
	    
	    b1.setBounds(450,100,150,25);
	    b2.setBounds(450,200,150,25);
	    
	    panel.add(t1);
	    panel.add(b1);
	    panel.add(t2);
	    panel.add(b2);
	    panel.add(l1);
	    panel.add(l2);
		
		
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
					
					if(removeTrack(title,c)) {
						
						JOptionPane.showMessageDialog(fr, "Track Removed", "Success", JOptionPane.PLAIN_MESSAGE);
						
					}
					
					else {
						
						JOptionPane.showMessageDialog(fr, "Update Failed.", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
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
        
		if(title.equals("")) {
			return false;
		}
		else {
			PreparedStatement ps=null;
			
			ps = conn.prepareStatement("delete from Album where Title = ?;");
			ps.setString(1,title);
			ps.executeUpdate();
			//System.out.println("Album Removed");
			
			return true;
		}
        
    }
	
	public boolean removeTrack(String title,Connection conn) throws Exception {
        
		if(title.equals(""))
			return false;
		
		else {
			
			PreparedStatement ps=null;
			
			ps = conn.prepareStatement("delete from Song where Song_Name = ?;");
			ps.setString(1,title);
			ps.executeUpdate();
			System.out.println("Track Removed");
			
			return true;
		}
    }
}
