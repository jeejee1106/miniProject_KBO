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
	TeamInfo teamInfo0 = new TeamInfo();
	TeamInfo teamInfo;
	
	JLabel title, subTitle;
	JButton btnAllPlayer;
	JButton btnNC, btnDS, btnKT, btnLG, btnKU, btnKIA, btnLT, btnSS, btnSSG, btnHH;
	
	Font font1 = new Font("휴먼둥근헤드라인", 0, 30); //글씨체, 효과(진하게, 기울기), 크기
	
	public KBOMain(){
		super("KBO 관리자 메뉴");
		this.setBounds(200, 60, 1150, 700);
		this.getContentPane().setBackground(Color.white);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void setDesign() {
		this.setLayout(null);
		
		title = new JLabel("[KBO 선수관리자 모드]", JLabel.CENTER);
		title.setBounds(100, 50, 900, 30);
//		title.setBorder(new LineBorder(Color.black));
		title.setFont(font1);
		this.add(title);
		
		subTitle = new JLabel("구단을 선택해주세요");
		this.add(subTitle);
		
		btnAllPlayer = new JButton("모든 선수보기");
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
		btnDS = new JButton(doosan);
		btnDS.setBounds(300, 180, 150, 150);
		this.add(btnDS);
		btnDS.addActionListener(this);
		
		ImageIcon kt = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\KT.png");
		btnKT = new JButton(kt);
		btnKT.setBounds(500, 180, 150, 150);
		this.add(btnKT);
		btnKT.addActionListener(this);
		
		ImageIcon lg = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\LG.png");
		btnLG = new JButton(lg);
		btnLG.setBounds(700, 180, 150, 150);
		this.add(btnLG);
		btnLG.addActionListener(this);
		
		ImageIcon kiwoom = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\KIWOOM.png");
		btnKU = new JButton(kiwoom);
		btnKU.setBounds(900, 180, 150, 150);
		this.add(btnKU);
		btnKU.addActionListener(this);
		
		ImageIcon kia = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\KIA.png");
		btnKIA = new JButton(kia);
		btnKIA.setBounds(100, 400, 150, 150);
		this.add(btnKIA);
		btnKIA.addActionListener(this);
		
		ImageIcon lotte = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\LOTTE.png");
		btnLT = new JButton(lotte);
		btnLT.setBounds(300, 400, 150, 150);
		this.add(btnLT);
		btnLT.addActionListener(this);
		
		ImageIcon samsung = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\SAMSUNG.png");
		btnSS = new JButton(samsung);
		btnSS.setBounds(500, 400, 150, 150);
		this.add(btnSS);
		btnSS.addActionListener(this);
		
		ImageIcon ssg = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\SSG.png");
		btnSSG = new JButton(ssg);
		btnSSG.setBounds(700, 400, 150, 150);
		this.add(btnSSG);
		btnSSG.addActionListener(this);
		
		ImageIcon hanwha = new ImageIcon("C:\\Users\\PC\\Desktop\\study\\image\\KBOProject\\Logo\\HANWHA.png");
		btnHH = new JButton(hanwha);
		btnHH.setBounds(900, 400, 150, 150);
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
			String name = "NC";
			new TeamInfo(name).setVisible(true);
			teamInfo0.teamNc();
		} else if(ob==btnDS) {
			String name = "두산";
			new TeamInfo(name).setVisible(true);
//			teamInfo(name).teamDs();
		} else if(ob==btnKT) {
			teamInfo.setVisible(true);
			teamInfo.teamKt();
		} else if(ob==btnLG) {
			teamInfo.setVisible(true);
			teamInfo.teamLg();
		} else if(ob==btnKU) {
			teamInfo.setVisible(true);
			teamInfo.teamKu();
		} else if(ob==btnKIA) {
			teamInfo.setVisible(true);
			teamInfo.teamKia();
		} else if(ob==btnLT) {
			teamInfo.setVisible(true);
			teamInfo.teamLt();
		} else if(ob==btnSS) {
			teamInfo.setVisible(true);
			teamInfo.teamSs();
		} else if(ob==btnSSG) {
			teamInfo.setVisible(true);
			teamInfo.teamSsg();
		} else if(ob==btnHH) {
			teamInfo.setVisible(true);
			teamInfo.teamHh();
		} else if (ob==btnAllPlayer) {
			AllPlayer ap = new AllPlayer();
		}
		
	}

}
