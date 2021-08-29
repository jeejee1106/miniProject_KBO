package KBOJDBC;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PlayerAddForm extends JFrame implements ActionListener{
//	PlayerImage playerImage = new PlayerImage(); //캔버스
	KBO_DBModel dbModel = new KBO_DBModel();
	
	Font font1 = new Font("휴먼둥근헤드라인", 0, 20); //글씨체, 효과(진하게, 기울기), 크기
	Font font2 = new Font("맑은 고딕", 0, 15); //글씨체, 효과(진하게, 기울기), 크기

	JLabel lbTitle, lbTeam, lbName, lbBacknum, lbPosition, lbBirth, lbPhoto;
	JTextField tfTeam, tfName, tfBacknum, tfPosition, tfBirth;
	JButton btnImage, btnInsert;

	String imageName;



	public PlayerAddForm() {
		super("선수 등록");
		this.setBounds(900, 100, 300, 470);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	private void setDesign() {
		this.setLayout(null);
		
		lbTitle = new JLabel("<선수 정보 입력>", JLabel.CENTER);
		lbTitle.setBounds(10, 10, 260, 50);
		lbTitle.setFont(font1);
		this.add(lbTitle);

		//팀명 라벨, 텍스트
		lbTeam = new JLabel("팀   명");
		lbTeam.setBounds(30, 70, 50, 30);
		lbTeam.setFont(font2);
		this.add(lbTeam);
		tfTeam = new JTextField();
		tfTeam.setBounds(90, 70, 100, 30);
		this.add(tfTeam);

		//선수명 라벨, 텍스트
		lbName = new JLabel("이   름");
		lbName.setBounds(30, 110, 50, 30);
		lbName.setFont(font2);
		this.add(lbName);
		tfName = new JTextField();
		tfName.setBounds(90, 110, 100, 30);
		this.add(tfName);

		//등번호 라벨, 텍스트
		lbBacknum = new JLabel("등번호");
		lbBacknum.setBounds(30, 150, 50, 30);
		lbBacknum.setFont(font2);
		this.add(lbBacknum);
		tfBacknum = new JTextField();
		tfBacknum.setBounds(90, 150, 100, 30);
		this.add(tfBacknum);

		//포지션 라벨, 텍스트
		lbPosition = new JLabel("포지션");
		lbPosition.setBounds(30, 190, 50, 30);
		lbPosition.setFont(font2);
		this.add(lbPosition);
		tfPosition = new JTextField();
		tfPosition.setBounds(90, 190, 100, 30);
		this.add(tfPosition);

		//생년월일 라벨, 텍스트
		lbBirth = new JLabel("생년월일");
		lbBirth.setBounds(30, 230, 50, 30);
		//lbBirth.setFont(font2);
		this.add(lbBirth);
		tfBirth = new JTextField();
		tfBirth.setBounds(90, 230, 100, 30);
		this.add(tfBirth);
		
		//사진경로 라벨
		lbPhoto = new JLabel();
		lbPhoto.setBounds(30, 310, 220, 45);
		lbPhoto.setBorder(new TitledBorder("사진경로"));
		this.add(lbPhoto);

		btnImage = new JButton("사진 선택");
		btnImage.setBounds(30, 270, 100, 30);
		btnImage.addActionListener(this);
		this.add(btnImage);

		btnInsert = new JButton("등록하기");
		btnInsert.setBounds(90, 370, 100, 30);
		btnInsert.addActionListener(this);
		this.add(btnInsert);
		
//		playerImage.setBounds(90, 320, 100, 100);
//		playerImage.setBackground(Color.black);
//		this.add(playerImage);
	}

//	class PlayerImage extends Canvas {
//		@Override
//		public void paint(Graphics g) {
//			super.paint(g);
//			if(imageName!=null) {
//				Image image = new ImageIcon(imageName).getImage();
//				g.drawImage(image, 0, 0, 100, 100, this);
//				//playerImage.setBounds(90, 320, 100, 100);여기서 만들어진 위치에 사진을 넣겠다. 90,320으로 만들어진 이미지액자 안에 이미지의 시작점을 모서리 끝부터!(0,0)!!!!!!!!
//			}
//		}
//	}


	public static void main(String[] args) {
		PlayerAddForm ex = new PlayerAddForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==btnImage) {
			if(ob==btnImage) {
				FileDialog dlg = new FileDialog(this, "이미지 가져오기", FileDialog.LOAD); //파일을 가져오는 그 창 뜨는 코드
				dlg.setVisible(true); //그 창이 보이게!!!
				//취소 누르면 메서드 종료
				if(dlg.getDirectory()==null) {
					return;
				}
				imageName = dlg.getDirectory() + dlg.getFile();
				lbPhoto.setText(imageName);
//				playerImage.repaint();
			}
		} else if(ob==btnInsert) {
			KBOPlayerDTO dto = new KBOPlayerDTO();
			
			dto.setTeam(tfTeam.getText());
			dto.setName(tfName.getText());
			dto.setBacknum(Integer.parseInt(tfBacknum.getText()));
			dto.setPosition(tfPosition.getText());
			dto.setBirth(tfBirth.getText());
			dto.setPhoto(imageName);
			
			dbModel.insertPlayer(dto);
			
			JOptionPane.showMessageDialog(this, "선수 등록이 완료되었습니다.");
			
			tfTeam.setText("NC Dinos");
			tfName.setText("");
			tfBacknum.setText("");
			tfPosition.setText("");
			tfBirth.setText("");
			imageName = null;
			
		}
	}
}
