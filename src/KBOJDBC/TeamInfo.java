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
	
	Font BNfont = new Font("휴먼둥근헤드라인", 0, 22);
	Font Nfont = new Font("휴먼둥근헤드라인", 0, 18);	
	Font Infofont = new Font("휴먼둥근헤드라인", 0, 13);	
	
	String team, name, position, birth, photo;
	String imageName;
	int backnum;
	
	public TeamInfo() {
		
	}
	
	public TeamInfo(String name) {
		super(name + " 선수단");
		teamName=name;
		this.setBounds(400, 70, 500, 650);
		this.setDesign();
		this.getContentPane().setBackground(Color.white);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setVisible(true);
	}
	
	
	public void setDesign() {
		this.setLayout(null);
		
		lbCbName = new JLabel("선수명");
		lbCbName.setBounds(50, 60, 100, 20);
		this.add(lbCbName);
		
		/////////선수 프로필///////////
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
		
		lbTeam = new JLabel("소 속 팀");
		lbTeam.setBounds(125, 435, 60, 20);
		lbTeam.setFont(Infofont);
		this.add(lbTeam);
		lbPTeam = new JLabel();
		lbPTeam.setBounds(185, 435, 100, 20);
		this.add(lbPTeam);
		
		lbPosition = new JLabel("포 지 션");
		lbPosition.setBounds(125, 460, 60, 20);
		lbPosition.setFont(Infofont);
		this.add(lbPosition);
		lbPPosition = new JLabel();
		lbPPosition.setBounds(185, 460, 80, 20);
		this.add(lbPPosition);
		
		lbBirth = new JLabel("생년월일");
		lbBirth.setBounds(125, 485, 60, 20);
		lbBirth.setFont(Infofont);
		this.add(lbBirth);
		lbPBirth = new JLabel();
		lbPBirth.setBounds(185, 485, 80, 20);
		this.add(lbPBirth);
		
		///////////콤보박스////////////
		Object[] playerName = dbModel.getNameArray(teamName);
		cbPlayerName = new JComboBox<Object>(playerName);
		cbPlayerName.setBounds(100, 60, 100, 20);
		this.add(cbPlayerName);
		
		//콤보박스 초기값 코드
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
			//콤보박스 체인지 이벤트!!
			@Override
			public void itemStateChanged(ItemEvent e) {
				String changeName = cbPlayerName.getSelectedItem().toString(); //콤보박스를 Object 타입으로 줬기 때문에 toString을 이용해서 String으로 형변환
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
//		String sql = "팀중 NC Dinos만 나오도록 쿼리 짜기";
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
//		String name = "테스트 창";
//		new TeamInfo(name);
//		
//	}
}
