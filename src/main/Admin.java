package main;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import main.Driver;

public class Admin implements LoginLogout{
	
	String uid = "";
	String pwd = "";
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Scanner input = new Scanner(System.in);
	
	Admin(Connection c) {
		
		conn = c;
		
	}
	
	public void AdminGUI(JFrame fr,String uid,String pass,Connection conn) {
		
		JMenu menu;  
	    JMenu a1,a2;
	      
	    menu = new JMenu("Home");
	    JMenuBar m1 = new JMenuBar();
	    a1 = new JMenu("Logout");
	    m1.add(menu);
	    m1.add(a1);
	      
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
				
			int result = JOptionPane.showConfirmDialog(fr,"Do you want to Exit?", "Confirm",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
				
			            if(result == JOptionPane.YES_OPTION){
			              
			            	Admin ad = new Admin(conn);
							Artist ar = new Artist(conn);
							Driver d = new Driver();
							fr.setVisible(false);
							Driver.LoginUI(ad, ar, conn);
			            	
			            }else if (result == JOptionPane.NO_OPTION){
			            	
			       
			            }
			            
			            else {
			               
			            	
			            }
			            
			}
			
		});
		
		
		JPanel panel = new JPanel();
		fr.add(panel);
		
		JLabel l = new JLabel("Home");
		l.setBounds(330, 50, 80, 75);
		
		Font labelFont = l.getFont();
		String labelText = l.getText();

		int stringWidth = l.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = l.getWidth();

		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = l.getHeight();
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		l.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
			
		panel.setLayout(null);
		fr.setAlwaysOnTop(true);
		
		JButton b1 = new JButton("Edit-Profile");
		JButton b2 = new JButton("Manage-Artist");
		JButton b3 = new JButton("Manage-Album");
		JButton b4 = new JButton("Manage-Track");
		
		b1.setBounds(300,200,150,25);
		b2.setBounds(300,250,150,25);
		b3.setBounds(300,300,150,25);
		b4.setBounds(300,350,150,25);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame newfr = new JFrame("Edit Profile");
				editProfileGUIadmin edit = new editProfileGUIadmin(conn,newfr,uid,pass);
				fr.setVisible(false);
			}
			
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					ManageArtist ad = new ManageArtist(conn,uid,pass);
					//DO HERE
					JFrame newFr = new JFrame("Manage Artist");
					ad.ManageArtistUI(newFr, uid, pass,conn);
					fr.setVisible(false);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		});

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					ManageAlbum ad = new ManageAlbum(conn,uid,pass);
					JFrame newFr = new JFrame("Manage Album");
					ad.ManageAlbumUI(newFr, uid, pass,conn);
					fr.setVisible(false);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					ManageTrack ad = new ManageTrack(conn,uid,pass);
					JFrame newFr = new JFrame("Manage Tracks");
					ad.ManageTracksUI(newFr, uid, pass,conn);
					fr.setVisible(false);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		
		panel.add(l);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		
		fr.setSize(800, 600);
		fr.setVisible(true);
		
	}

    public boolean login(String uid, String pwd) throws SQLException {
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM users");
		
        while(rs.next()){
        	
			if( uid.equals(rs.getString("uid")) && pwd.equals(rs.getString("pwd")) && rs.getString("role").equals("ADMIN") ){
				
				this.uid = uid;
				this.pwd = pwd;
				return true;
			}
		}
        
		this.logout(false);
		
        return false;
    }
	
	public boolean editProfilePwd(String newPwd,String uid) throws SQLException {
		
		if (newPwd.equals("")){
			return false;
		}
		else {
			
			//System.out.println(pass);
			//System.out.println(newPwd);
			
			ps = conn.prepareStatement("Update users set pwd=? where uid=?");
			ps.setString(1,newPwd);
			ps.setString(2,uid);
			ps.executeUpdate();
			
		}
		
		return true;
	}

	public boolean editProfileUid(String newUid,String uid) throws SQLException {
		
		if (newUid.equals("")){
			
			return false;
		}
		
		else {
		
			//System.out.println(uid);
			//System.out.println(newUid);
			
			ps = conn.prepareStatement("Update users set uid=? where uid=?");
			ps.setString(1,newUid);
			ps.setString(2,uid);
			ps.executeUpdate();
			
			return true;
		
		}
	
	}

    /*public void editProfile() throws SQLException {
		System.out.println("1. Edit UserName\n2. Edit Password");
		int option = input.nextInt();
		switch (option) {
			case 1:
				this.editProfileUid();
				break;
			case 2:
				this.editProfilePwd();
				break;
			default:
				System.out.println("Enter Valid Input (1/2)");
		}
	}*/

	public void logout(boolean b){
		try {
			this.uid = null;
			this.close(b);	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void close(boolean b) throws SQLException {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (conn != null && b)
			conn.close();
    }
	
	public class editProfileGUIadmin {
		
		editProfileGUIadmin(Connection c,JFrame fr,String uid,String pass) {
			
			Admin ad = new Admin(c);
			
			JLabel label = new JLabel("Enter New ID:");
			label.setBounds(100,100,100,25);
			
			JTextField text =  new JTextField();
			text.setBounds(210,100,200,25);
			
			JLabel label2 = new JLabel("Enter New Pass:");
			label2.setBounds(100,200,100,25);
			
			JTextField text2 =  new JTextField();
			text2.setBounds(210,200,200,25);
			
			JPanel panel = new JPanel();
			fr.add(panel);
			
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
			
				
			panel.setLayout(null);
			fr.setAlwaysOnTop(true);
			
			JButton b1 = new JButton("Edit User-ID");
			JButton b2 = new JButton("Change Password");
			
			b1.setBounds(450,100,150,25);
			b2.setBounds(450,200,150,25);
			
			b1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						String newUID = text.getText();
						
						if(ad.editProfileUid(newUID,uid)) {
							
							JOptionPane.showMessageDialog(fr, "Username Updated", "Success", JOptionPane.PLAIN_MESSAGE);
							
							JOptionPane.showMessageDialog(fr, "Please Login again with New Credentials.", "Success", JOptionPane.OK_CANCEL_OPTION);
							
							//System.out.println("JMenu Clicked.");
							Admin ad = new Admin(c);
							Artist ar = new Artist(c);
							Driver d = new Driver();
							fr.setVisible(false);
							Driver.LoginUI(ad, ar, c);
							
						}
						
						else {
							
							JOptionPane.showMessageDialog(fr, "Update Failed.", "Error", JOptionPane.ERROR_MESSAGE);
							
						}
						
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				}
			});
			
			b2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						String newPass = text2.getText();
						
						if(ad.editProfilePwd(newPass,uid)) {
							
							JOptionPane.showMessageDialog(fr, "Password Updated", "Success", JOptionPane.PLAIN_MESSAGE);
							
							JOptionPane.showMessageDialog(fr, "Please Login again with New Credentials.", "Success", JOptionPane.OK_CANCEL_OPTION);
							
							//System.out.println("JMenu Clicked.");
							Admin ad = new Admin(c);
							Artist ar = new Artist(c);
							Driver d = new Driver();
							fr.setVisible(false);
							Driver.LoginUI(ad, ar, c);
							
						}
						
						else {
							
							JOptionPane.showMessageDialog(fr, "Update Failed.", "Error", JOptionPane.ERROR_MESSAGE);
							
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				}
			});
			
			panel.add(label);
			panel.add(text);
			panel.add(b1);
			
			panel.add(label2);
			panel.add(text2);
			panel.add(b2);
			
			fr.setSize(800, 600);
			fr.setVisible(true);
			
		}
		
		
	}
	
	
}

