package KBOJDBC;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import oracle.db.DbConnect;

public class TeamInfo extends JFrame{
	KBOPlayerDTO playerdto = new KBOPlayerDTO();
	KBO_DBModel dbModel = new KBO_DBModel();
	DbConnect db = new DbConnect();
	playerProfile profileDraw = new playerProfile();
	JComboBox<Object> cbPlayerName;
	
	JLabel lbCbName, lbTeam, lbPTeam, lbPName, lbPBacknum, lbPosition, lbPPosition, lbBirth, lbPBirth;
	
	String teamName;
	
	Font BNfont = new Font("�޸յձ�������", 0, 22);
	Font Nfont = new Font("�޸յձ�������", 0, 18);	
	Font Infofont = new Font("�޸յձ�������", 0, 13);	
	
	String team, name, position, birth, photo;
	String imageName;
	int backnum;
	
	public TeamInfo() {
		
	}
	
	public TeamInfo(String name) {
		super(name + " ������");
		teamName=name;
		this.setBounds(400, 70, 500, 650);
		this.setDesign();
		this.getContentPane().setBackground(Color.white);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setVisible(true);
	}
	
	
	public void setDesign() {
		this.setLayout(null);
		
		lbCbName = new JLabel("������");
		lbCbName.setBounds(50, 60, 100, 20);
		this.add(lbCbName);
		
		/////////���� ������///////////
		profileDraw.setBounds(125, 130, 250, 250);
		profileDraw.setBackground(Color.black);
		this.add(profileDraw);
		
		lbPBacknum = new JLabel();
		lbPBacknum.setBounds(125, 385, 40, 40);
		lbPBacknum.setFont(BNfont);
		lbPBacknum.setForeground(new Color(255,177,106));
		this.add(lbPBacknum);
		
		lbPName = new JLabel();
		lbPName.setBounds(175, 390, 100, 30);
		lbPName.setFont(Nfont);
		lbPName.setForeground(new Color(0,0,127));
		this.add(lbPName);
		
		lbTeam = new JLabel("�� �� ��");
		lbTeam.setBounds(125, 435, 60, 20);
		lbTeam.setFont(Infofont);
		this.add(lbTeam);
		lbPTeam = new JLabel();
		lbPTeam.setBounds(185, 435, 100, 20);
		this.add(lbPTeam);
		
		lbPosition = new JLabel("�� �� ��");
		lbPosition.setBounds(125, 460, 60, 20);
		lbPosition.setFont(Infofont);
		this.add(lbPosition);
		lbPPosition = new JLabel();
		lbPPosition.setBounds(185, 460, 80, 20);
		this.add(lbPPosition);
		
		lbBirth = new JLabel("�������");
		lbBirth.setBounds(125, 485, 60, 20);
		lbBirth.setFont(Infofont);
		this.add(lbBirth);
		lbPBirth = new JLabel();
		lbPBirth.setBounds(185, 485, 80, 20);
		this.add(lbPBirth);
		
		///////////�޺��ڽ�////////////
		Object[] playerName = dbModel.getNameArray(teamName);
		cbPlayerName = new JComboBox<Object>(playerName);
		cbPlayerName.setBounds(100, 60, 100, 20);
		this.add(cbPlayerName);
		
		//�޺��ڽ� �ʱⰪ �ڵ�
		String firstname = cbPlayerName.getSelectedItem().toString();
		playerdto = dbModel.getPlayerProfile(firstname);
//		lbPBacknum.setText(String.valueOf(playerdto.getBacknum()));
//		lbPName.setText(playerdto.getName());
//		lbPTeam.setText(playerdto.getTeam());
//		lbPPosition.setText(playerdto.getPosition());
//		lbPBirth.setText(playerdto.getBirth());
		imageName = playerdto.getPhoto();
		profileDraw.repaint();
		
		
		cbPlayerName.addItemListener(new ItemListener() {
			//�޺��ڽ� ü���� �̺�Ʈ!!
			@Override
			public void itemStateChanged(ItemEvent e) {
				String changeName = cbPlayerName.getSelectedItem().toString(); //�޺��ڽ��� Object Ÿ������ ��� ������ toString�� �̿��ؼ� String���� ����ȯ
				playerdto = dbModel.getPlayerProfile(changeName);
				lbPBacknum.setText(String.valueOf(playerdto.getBacknum()));
				lbPName.setText(playerdto.getName());
				lbPTeam.setText(playerdto.getTeam());
				lbPPosition.setText(playerdto.getPosition());
				lbPBirth.setText(playerdto.getBirth());
				imageName = playerdto.getPhoto();
				profileDraw.repaint();
				
				
				
//				team = playerdto.getTeam();
//				name = playerdto.getName();
//				backnum = playerdto.getBacknum();
//				position = playerdto.getPosition();
//				birth = playerdto.getBirth();
//				photo = playerdto.getPhoto();
				repaint();
				
				
			}
		});
		
	}
	
	class playerProfile extends Canvas{
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if(imageName!=null) {
				Image image = new ImageIcon(imageName).getImage();
				g.drawImage(image, 0, 0, 250, 250, this);
			}
		}
	}

//	public void cbPlayer(String t) {
//		Object[] playerName = dbModel.getNameArray(team);
//		cbPlayerName = new JComboBox<Object>(playerName);
//		cbPlayerName.setBounds(100, 60, 100, 20);
//		this.add(cbPlayerName);
//	}
	
	public void teamNc() {
//		Connection conn = db.getLocalOracle();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "���� NC Dinos�� �������� ���� ¥��";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			db.dbClose(rs, pstmt, conn);
//		}
		
		
	}

//	public void teamDs() {
//		
//	}
//
//	public void teamKt() {
//		
//	}
//
//	public void teamLg() {
//		
//	}
//
//	public void teamKu() {
//		
//	}
//
//	public void teamKia() {
//		
//	}
//
//	public void teamLt() {
//		
//	}
//
//	public void teamSs() {
//		
//	}
//
//	public void teamSsg() {
//		
//	}
//
//	public void teamHh() {
//		
//	}
	
//	public static void main(String[] args) {
//		String name = "�׽�Ʈ â";
//		new TeamInfo(name);
//		
//	}
}
