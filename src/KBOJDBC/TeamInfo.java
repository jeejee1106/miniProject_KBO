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

public class TeamInfo extends JFrame implements ActionListener{
	KBOPlayerDTO playerdto = new KBOPlayerDTO();
	KBO_DBModel dbModel = new KBO_DBModel();
	DbConnect db = new DbConnect();
	playerProfile profileDraw = new playerProfile();
	JComboBox<Object> cbPlayerName;
	
	JButton btnAddPlayer, btnUpPlayer, btnDelPlayer;
	
	JLabel lbTeam, lbPTeam, lbPName, lbPBacknum, lbPosition, lbPPosition, lbBirth, lbPBirth;
	
	Font BNfont = new Font("�޸յձ�������", 0, 22);
	Font Nfont = new Font("�޸յձ�������", 0, 18);	
	
	String team, name, position, birth, photo;
	String imageName;
	int backnum;
	
	public TeamInfo() {
		
	}
	
	public TeamInfo(String name) {
		super(name);
		this.setBounds(400, 70, 500, 650);
		this.setDesign();
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void setDesign() {
		this.setLayout(null);
		/////////�������� ���� ��ư/////////
		btnAddPlayer = new JButton("���� ���");
		btnAddPlayer.setBounds(30, 20, 100, 30);
		btnAddPlayer.addActionListener(this);
		this.add(btnAddPlayer);
		
		btnUpPlayer = new JButton("�������� ����");
		btnUpPlayer.setBounds(170, 20, 120, 30);
		btnUpPlayer.addActionListener(this);
		this.add(btnUpPlayer);
		
		btnDelPlayer = new JButton("���� ����");
		btnDelPlayer.setBounds(330, 20, 100, 30);
		btnDelPlayer.addActionListener(this);
		this.add(btnDelPlayer);
		
		/////////���� ������///////////
		profileDraw.setBounds(125, 130, 250, 250);
		profileDraw.setBackground(Color.black);
		this.add(profileDraw);
		
		lbPBacknum = new JLabel("0");
		lbPBacknum.setBounds(125, 385, 40, 40);
		lbPBacknum.setFont(BNfont);
		lbPBacknum.setForeground(new Color(255,177,106));
		this.add(lbPBacknum);
		
		lbPName = new JLabel("000");
		lbPName.setBounds(175, 390, 100, 30);
		lbPName.setFont(Nfont);
		lbPName.setForeground(new Color(0,0,127));
		this.add(lbPName);
		
		lbTeam = new JLabel("�� �� ��");
		lbTeam.setBounds(125, 435, 50, 20);
		this.add(lbTeam);
		lbPTeam = new JLabel("1121");
		lbPTeam.setBounds(185, 435, 100, 20);
		this.add(lbPTeam);
		
		lbPosition = new JLabel("�� �� ��");
		lbPosition.setBounds(125, 460, 50, 20);
		this.add(lbPosition);
		lbPPosition = new JLabel("555");
		lbPPosition.setBounds(185, 460, 80, 20);
		this.add(lbPPosition);
		
		lbBirth = new JLabel("�������");
		lbBirth.setBounds(125, 485, 50, 20);
		this.add(lbBirth);
		lbPBirth = new JLabel("5555");
		lbPBirth.setBounds(185, 485, 80, 20);
		this.add(lbPBirth);
		
		
		
		
		///////////�޺��ڽ�////////////
		Object[] playerName = dbModel.getNameArray();
		cbPlayerName = new JComboBox<Object>(playerName);
		cbPlayerName.setBounds(30, 60, 100, 20);
		this.add(cbPlayerName);
		cbPlayerName.addItemListener(new ItemListener() {
			//�޺��ڽ� ü���� �̺�Ʈ!!
			@Override
			public void itemStateChanged(ItemEvent e) {
				String changeName = cbPlayerName.getSelectedItem().toString(); //�޺��ڽ��� Object Ÿ������ ��� ������ toString�� �̿��ؼ� String���� ����ȯ
				playerdto = dbModel.getPlayerProfile(changeName);
				lbPBacknum.setText(String.valueOf(playerdto.getBacknum())); //������
				lbPName.setText(playerdto.getName());
				lbPTeam.setText(playerdto.getTeam());
				lbPPosition.setText(playerdto.getPosition());
				lbPBirth.setText(playerdto.getBirth()); //�� �ȵ���, String.valueOf���� �ΰ� ����!
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
	
	//��ư�̺�Ʈ �������̽� �޼��� �������̵�!
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==btnAddPlayer) {
			PlayerAddForm add = new PlayerAddForm();
		} else if(ob==btnUpPlayer){
			
		} else if(ob==btnDelPlayer){
			
		}
	}

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

	public void teamDs() {
		
	}

	public void teamKt() {
		
	}

	public void teamLg() {
		
	}

	public void teamKu() {
		
	}

	public void teamKia() {
		
	}

	public void teamLt() {
		
	}

	public void teamSs() {
		
	}

	public void teamSsg() {
		
	}

	public void teamHh() {
		
	}
	
	public static void main(String[] args) {
		String name = "�׽�Ʈ â";
		new TeamInfo(name);
		
	}
}
