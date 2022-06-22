package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Driver {
	
	public static void main(String[] args) throws Exception {
		
		Connection connect = null;
		//Statement st = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");

			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MusicCompany", "postgres", "dognose1@2");

			//st = connect.createStatement();
			
		}
		
		catch(Exception e) {
			
			throw e;
			
		}
		
		System.out.println("CONNECTED TO DATABASE...\n");
		
		Admin admin = new Admin(connect);
		Artist artist = new Artist(connect);
		
		//LOGIN-PAGE
		
		LoginUI(admin,artist,connect);
		
	}
	
public static void LoginUI(Admin admin,Artist artist,Connection c) {
		
		JFrame frame = new JFrame();
		frame.setTitle("Music Recording Company");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
			
		panel.setLayout(null);
		frame.setAlwaysOnTop(true);
		
		JLabel l = new JLabel("Login");
		l.setBounds(330, 10, 80, 75);
		
		Font labelFont = l.getFont();
		String labelText = l.getText();

		int stringWidth = l.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = l.getWidth();

		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = l.getHeight();
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		l.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		
		JLabel label = new JLabel("Username");
		label.setBounds(200,120,80,25);
		
		JLabel password = new JLabel("Password");
		password.setBounds(200,180,80,25);
		
		JTextField username = new JTextField(20);
		username.setBounds(300, 120, 165, 25);
		
		JTextField password1 = new JTextField(20);
		password1.setBounds(300, 180, 165, 25);
		
		JButton button1 = new JButton("Login as Admin");
		button1.setBounds(200,300,150,25);
		
		button1.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				String userName = username.getText();
				String Pwd = password1.getText();
				
				try {
					
					if(admin.login(userName, Pwd)) {
						
						JFrame s = new JFrame("Admin");
						admin.AdminGUI(s,userName,Pwd,c);
						username.setText(null);
						password1.setText(null);
						frame.setVisible(false);
						
						//MENU TO-DO
						
						ManageAlbum ad1 = new ManageAlbum(c,userName,Pwd);
						ManageTrack ad2 = new ManageTrack(c,userName,Pwd);

						System.out.println(".....Admin Menu Opens Up.....");
						
						//admin.editProfile();
						//ad1.removeAlbum();
						//ad1.getInfo();
						//ad2.removeTrack();
						//ad2.getInfo();
						
					}
					
					else {
						
						JOptionPane.showMessageDialog(frame, "Invalid Login Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
						
						username.setText(null);
						password1.setText(null);
					}
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			
		
		});
		
		JButton button2 = new JButton("Login as Artist");
		button2.setBounds(400, 300, 150, 25);
		
		button2.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				String userName = username.getText();
				String Pwd = password1.getText();
				
				try {
					
					if(artist.login(userName, Pwd)) {
						
						username.setText(null);
						password1.setText(null);
						
						JFrame a = new JFrame("Artist");
						artist.ArtistUI(a,userName,Pwd,c);
						frame.setVisible(false);
						
						//MENU TO-DO
						
						Upload ar1= new Upload(userName,Pwd,c);
						EditMusic ar2= new EditMusic(userName,Pwd,c);
						
						System.out.println(".....Artist Menu Opens Up.....");

						//artist.editProfile();
						//ar1.Publish();
						//ar2.removeAlbum();
						//ar2.removeTrack();
						
					}
					else {
						JOptionPane.showMessageDialog(frame, "Invalid Login Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
						
						username.setText(null);
						password1.setText(null);
					}
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			
		
		});
		
		panel.add(l);
		panel.add(label);
		panel.add(password);
		panel.add(username);
		panel.add(password1);
		panel.add(button1);
		panel.add(button2);
	    
		frame.setVisible(true);
		frame.setSize(800,600);		
	}

	
}

//--------------OLD STUFF-------------------

/*
Scanner input = new Scanner(System.in);
System.out.println("Enter Username & Password : \n");
String userid = input.nextLine();
String passwd = input.nextLine();
*/

/*if(admin.login(userid, passwd)) {
	
	//MENU TO-DO
	
	System.out.println(".....Admin Menu Opens Up.....");
	
	//admin.editProfile();
	//ad1.removeAlbum();
	//ad1.getInfo();
	//ad2.removeTrack();
	//ad2.getInfo();
}
else if(artist.login(userid, passwd)) {
	
	//MENU TO-DO
	
	System.out.println(".....Artist Menu Opens Up.....");
	//artist.editProfile();
	//ar1.Publish();
	//ar2.removeAlbum();
	//ar2.removeTrack();
	
}
else {
	
	System.out.println("Login Failed.");
	System.exit(0);
}*/

