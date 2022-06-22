package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import main.Driver;

public class Artist implements LoginLogout{
	
	private static final String STRING = "";
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	protected String uid = STRING;
	protected String aid = STRING;
	protected String pwd = STRING;
	Scanner input = new Scanner(System.in);
	PreparedStatement ps = null;

	Artist(Connection c){
		
		this.conn = c;
		
	}
	
	public void ArtistUI(JFrame fr,String uid, String pass,Connection conn) {
		
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
		JButton b2 = new JButton("Upload");
		JButton b3 = new JButton("Edit-Music");
		JButton b4 = new JButton("View-Analytics");
		
		b1.setBounds(300,200,150,25);
		b2.setBounds(300,250,150,25);
		b3.setBounds(300,300,150,25);
		b4.setBounds(300,350,150,25);
		
		
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame newfr = new JFrame("Edit Profile");
				editProfileGUIartist edit = new editProfileGUIartist(conn,newfr,uid,pass);
				fr.setVisible(false);
			}
			
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					Upload ar = new Upload(uid,pass,conn);
					JFrame newFr = new JFrame("Upload: ");
					ar.UploadUI(newFr, uid, pass,conn);
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
					
					EditMusic ar = new EditMusic(uid,pass,conn);
					JFrame newFr = new JFrame("Edit Music: ");
					ar.EditMusicUI(newFr,uid, pass,conn);
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
					
					ViewAnalytics view = new ViewAnalytics(uid,pass,conn);
					JFrame newFr = new JFrame("Edit Music: ");
					view.ViewAnalyticsUI(newFr,uid, pass,conn);
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
	
	public boolean login(String uid, String pwd) throws SQLException{
		
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM users");
		while(rs.next()){
			if( uid.equals(rs.getString("uid")) && pwd.equals(rs.getString("pwd")) && rs.getString("role").equals("ARTIST") ){
				
				this.uid = uid;
				//this.aid = rs.getString("Artist_ID");
				this.pwd = pwd;
				
				return true;
			}
		}
		
		this.logout(false);
		return false;
	}
	
	public boolean editProfileAddr(String newAddr,String aid) throws SQLException {
		
		if (newAddr.equals(STRING)){
			return false;
		}
		
		else {
			
			ps = conn.prepareStatement("Update Address set Address=? where Artist_ID=?");
			ps.setString(1,newAddr);
			ps.setString(2, aid);
	
			ps.executeUpdate();
			
			return true;
		
		}
	}

	public boolean editProfilePwd(String newPwd,String uid) throws SQLException {
		
		if (newPwd.equals(STRING)){
			return false;
		}
		
		else {
			
			ps = conn.prepareStatement("Update users set pwd=? where uid=?");
			ps.setString(1,newPwd);
			ps.setString(2, uid);
	
			ps.executeUpdate();
			
			return true;
		}
	}

	public boolean editProfileUid(String newUid,String uid) throws SQLException {
		
		if (newUid.equals(STRING)){
			return false;
		}
		
		else {
			
			ps = conn.prepareStatement("Update users set uid=? where uid=?");
			ps.setString(1,newUid);
			ps.setString(2, uid);
	
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
	
	public class editProfileGUIartist {
		
		editProfileGUIartist(Connection c,JFrame fr,String uid,String pass) {
			
			Artist ar = new Artist(c);
			
			JLabel label = new JLabel("Enter New ID:");
            label.setBounds(70,100,100,25);
            
            JTextField text =  new JTextField();
            text.setBounds(210,100,200,25);
            
            JLabel label2 = new JLabel("Enter New Pass:");
            label2.setBounds(70,200,100,25);
            
            JTextField text2 =  new JTextField();
            text2.setBounds(210,200,200,25);
            
            JLabel label3 = new JLabel("Enter New Address:");
            label3.setBounds(70,300,200,25);
            
            JTextField text3 =  new JTextField();
            text3.setBounds(210,300,200,25);
            
            JLabel label4 = new JLabel("Enter Artist ID:");
            label4.setBounds(70,340,100,25);
            
            JTextField text4 =  new JTextField();
            text4.setBounds(210,340,200,25);
			
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
								Artist ar = new Artist(c);
								JFrame newFr = new JFrame("Admin");
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
			
				
			panel.setLayout(null);
			fr.setAlwaysOnTop(true);
			
			JButton b1 = new JButton("Edit User-ID");
			JButton b2 = new JButton("Change Password");
			JButton b3 = new JButton("Edit Address");
			
			b1.setBounds(450,100,150,25);
            b2.setBounds(450,200,150,25);
            b3.setBounds(450,320,150,25);
			
			b1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						String newUid = text.getText();
						
						if(ar.editProfileUid(newUid,uid)) {
							
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
						
						if(ar.editProfilePwd(newPass,uid)) {
							
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
			
			b3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						String newAdd = text3.getText();
						String aid = text4.getText();
						if(ar.editProfileAddr(newAdd,aid)) {
							
							JOptionPane.showMessageDialog(fr, "Address Updated", "Success", JOptionPane.PLAIN_MESSAGE);
							
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
			
			panel.add(label3);
			panel.add(text3);
			panel.add(b3);
			
			panel.add(label4);
            panel.add(text4);
			
			fr.setSize(800, 600);
			fr.setVisible(true);
			
		}
		
		
	}
}
