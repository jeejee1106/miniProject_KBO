package KBOJDBC;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class KBOMain extends JFrame implements ActionListener{
	
	TeamInfo teamInfo0;
	TeamInfo teamInfo;
	
	JLabel title, subTitle;
	JButton btnAllPlayer;
	JButton btnNC, btnDS, btnKT, btnLG, btnKU, btnKIA, btnLT, btnSS, btnSSG, btnHH;
	
	Font font1 = new Font("휴먼둥근헤드라인", 0, 30); //글씨체, 효과(진하게, 기울기), 크기
	
	public KBOMain(){
		super("KBO 선수 프로필");
		this.setBounds(200, 60, 1150, 700);
		this.getContentPane().setBackground(Color.white);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void setDesign() {
		this.setLayout(null);
		
		title = new JLabel("[KBO 구단별 선수 프로필]", JLabel.CENTER);
		title.setBounds(100, 50, 900, 30);
//		title.setBorder(new LineBorder(Color.black));
		title.setFont(font1);
		this.add(title);
		
		subTitle = new JLabel("구단을 선택해주세요");
		this.add(subTitle);
		
		btnAllPlayer = new JButton("선수 관리자모드");
		btnAllPlayer.setBounds(900, 80, 150, 30);
		this.add(btnAllPlayer);
		btnAllPlayer.addActionListener(this);
		
		//시간 남으면 버튼 배열로 바꿔보기(0809 Ex03예제 보기)
		ImageIcon nc = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\NC.png");
		ImageIcon changeNc = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeNC.png");
		btnNC = new JButton(nc);
		btnNC.setRolloverIcon(changeNc);
		btnNC.setBounds(100, 180, 150, 150);
		btnNC.setBorderPainted(false);
		this.add(btnNC);
		btnNC.addActionListener(this);
		
		ImageIcon doosan = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\DOOSAN.png");
		ImageIcon changeDs = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeDOOSAN.png");
		btnDS = new JButton(doosan);
		btnDS.setRolloverIcon(changeDs);
		btnDS.setBounds(300, 180, 150, 150);
		btnDS.setBorderPainted(false);
		this.add(btnDS);
		btnDS.addActionListener(this);
		
		ImageIcon kt = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\KT.png");
		ImageIcon changeKt = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeKT.png");
		btnKT = new JButton(kt);
		btnKT.setRolloverIcon(changeKt);
		btnKT.setBounds(500, 180, 150, 150);
		btnKT.setBorderPainted(false);
		this.add(btnKT);
		btnKT.addActionListener(this);
		
		ImageIcon lg = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\LG.png");
		ImageIcon changeLg = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeLG.png");
		btnLG = new JButton(lg);
		btnLG.setRolloverIcon(changeLg);
		btnLG.setBounds(700, 180, 150, 150);
		btnLG.setBorderPainted(false);
		this.add(btnLG);
		btnLG.addActionListener(this);
		
		ImageIcon kiwoom = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\KIWOOM.png");
		ImageIcon changeKw = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeKIWOOM.png");
		btnKU = new JButton(kiwoom);
		btnKU.setRolloverIcon(changeKw);
		btnKU.setBounds(900, 180, 150, 150);
		btnKU.setBorderPainted(false);
		this.add(btnKU);
		btnKU.addActionListener(this);
		
		ImageIcon kia = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\KIA.png");
		ImageIcon changeKia = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeKIA.png");
		btnKIA = new JButton(kia);
		btnKIA.setRolloverIcon(changeKia);
		btnKIA.setBounds(100, 400, 150, 150);
		btnKIA.setBorderPainted(false);
		this.add(btnKIA);
		btnKIA.addActionListener(this);
		
		ImageIcon lotte = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\LOTTE.png");
		ImageIcon changeLt = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeLOTTE.png");
		btnLT = new JButton(lotte);
		btnLT.setRolloverIcon(changeLt);
		btnLT.setBounds(300, 400, 150, 150);
		btnLT.setBorderPainted(false);
		this.add(btnLT);
		btnLT.addActionListener(this);
		
		ImageIcon samsung = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\SAMSUNG.png");
		ImageIcon changeSs = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeSAMSUNG.png");
		btnSS = new JButton(samsung);
		btnSS.setRolloverIcon(changeSs);
		btnSS.setBounds(500, 400, 150, 150);
		btnSS.setBorderPainted(false);
		this.add(btnSS);
		btnSS.addActionListener(this);
		
		ImageIcon ssg = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\SSG.png");
		ImageIcon changeSsg = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeSSG.png");
		btnSSG = new JButton(ssg);
		btnSSG.setRolloverIcon(changeSsg);
		btnSSG.setBounds(700, 400, 150, 150);
		btnSSG.setBorderPainted(false);
		this.add(btnSSG);
		btnSSG.addActionListener(this);
		
		ImageIcon hanwha = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\HANWHA.png");
		ImageIcon changeHh = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\ChangeHANWHA.png");
		btnHH = new JButton(hanwha);
		btnHH.setRolloverIcon(changeHh);
		btnHH.setBounds(900, 400, 150, 150);
		btnHH.setBorderPainted(false);
		this.add(btnHH);
		btnHH.addActionListener(this);
		
	}

	public static void main(String[] args) {
		new KBOMain();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob==btnNC) {
			String teamName = "NC Dinos";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnDS) {
			String teamName = "DOOSAN Bears";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnKT) {
			String teamName = "KT Wiz";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnLG) {
			String teamName = "LG Twins";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnKU) {
			String teamName = "KIWOOM Heroes";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnKIA) {
			String teamName = "KIA Tigers";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnLT) {
			String teamName = "LOTTE Giants";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnSS) {
			String teamName = "SAMSUNG Lions";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnSSG) {
			String teamName = "SSG Landers";
			new TeamInfo(teamName).setVisible(true);
		} else if(ob==btnHH) {
			String teamName = "HANWHA Eagles";
			new TeamInfo(teamName).setVisible(true);
		} else if (ob==btnAllPlayer) {
			AdminMode am = new AdminMode();
		}
		
	}

}
