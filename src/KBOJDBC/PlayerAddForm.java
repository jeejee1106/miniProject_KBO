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
//	PlayerImage playerImage = new PlayerImage(); //ĵ����
	KBO_DBModel dbModel = new KBO_DBModel();
	
	Font font1 = new Font("�޸յձ�������", 0, 20); //�۾�ü, ȿ��(���ϰ�, ����), ũ��
	Font font2 = new Font("���� ���", 0, 15); //�۾�ü, ȿ��(���ϰ�, ����), ũ��

	JLabel lbTitle, lbTeam, lbName, lbBacknum, lbPosition, lbBirth, lbPhoto;
	JTextField tfTeam, tfName, tfBacknum, tfPosition, tfBirth;
	JButton btnImage, btnInsert;

	String imageName;



	public PlayerAddForm() {
		super("���� ���");
		this.setBounds(900, 100, 300, 470);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	private void setDesign() {
		this.setLayout(null);
		
		lbTitle = new JLabel("<���� ���� �Է�>", JLabel.CENTER);
		lbTitle.setBounds(10, 10, 260, 50);
		lbTitle.setFont(font1);
		this.add(lbTitle);

		//���� ��, �ؽ�Ʈ
		lbTeam = new JLabel("��   ��");
		lbTeam.setBounds(30, 70, 50, 30);
		lbTeam.setFont(font2);
		this.add(lbTeam);
		tfTeam = new JTextField();
		tfTeam.setBounds(90, 70, 100, 30);
		this.add(tfTeam);

		//������ ��, �ؽ�Ʈ
		lbName = new JLabel("��   ��");
		lbName.setBounds(30, 110, 50, 30);
		lbName.setFont(font2);
		this.add(lbName);
		tfName = new JTextField();
		tfName.setBounds(90, 110, 100, 30);
		this.add(tfName);

		//���ȣ ��, �ؽ�Ʈ
		lbBacknum = new JLabel("���ȣ");
		lbBacknum.setBounds(30, 150, 50, 30);
		lbBacknum.setFont(font2);
		this.add(lbBacknum);
		tfBacknum = new JTextField();
		tfBacknum.setBounds(90, 150, 100, 30);
		this.add(tfBacknum);

		//������ ��, �ؽ�Ʈ
		lbPosition = new JLabel("������");
		lbPosition.setBounds(30, 190, 50, 30);
		lbPosition.setFont(font2);
		this.add(lbPosition);
		tfPosition = new JTextField();
		tfPosition.setBounds(90, 190, 100, 30);
		this.add(tfPosition);

		//������� ��, �ؽ�Ʈ
		lbBirth = new JLabel("�������");
		lbBirth.setBounds(30, 230, 50, 30);
		//lbBirth.setFont(font2);
		this.add(lbBirth);
		tfBirth = new JTextField();
		tfBirth.setBounds(90, 230, 100, 30);
		this.add(tfBirth);
		
		//������� ��
		lbPhoto = new JLabel();
		lbPhoto.setBounds(30, 310, 220, 45);
		lbPhoto.setBorder(new TitledBorder("�������"));
		this.add(lbPhoto);

		btnImage = new JButton("���� ����");
		btnImage.setBounds(30, 270, 100, 30);
		btnImage.addActionListener(this);
		this.add(btnImage);

		btnInsert = new JButton("����ϱ�");
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
//				//playerImage.setBounds(90, 320, 100, 100);���⼭ ������� ��ġ�� ������ �ְڴ�. 90,320���� ������� �̹������� �ȿ� �̹����� �������� �𼭸� ������!(0,0)!!!!!!!!
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
				FileDialog dlg = new FileDialog(this, "�̹��� ��������", FileDialog.LOAD); //������ �������� �� â �ߴ� �ڵ�
				dlg.setVisible(true); //�� â�� ���̰�!!!
				//��� ������ �޼��� ����
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
			
			JOptionPane.showMessageDialog(this, "���� ����� �Ϸ�Ǿ����ϴ�.");
			
			tfTeam.setText("NC Dinos");
			tfName.setText("");
			tfBacknum.setText("");
			tfPosition.setText("");
			tfBirth.setText("");
			imageName = null;
			
		}
	}
}
