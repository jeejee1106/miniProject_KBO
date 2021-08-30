package KBOJDBC;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class playerUpdateForm extends JFrame implements ActionListener{

	KBO_DBModel dbModel = new KBO_DBModel();

	Font font1 = new Font("�޸յձ�������", 0, 20); //�۾�ü, ȿ��(���ϰ�, ����), ũ��
	Font font2 = new Font("���� ����", 0, 15); //�۾�ü, ȿ��(���ϰ�, ����), ũ��

	JLabel lbTitle, lbTeam, lbName, lbBacknum, lbPosition, lbBirth, lbPhoto;
	JTextField tfTeam, tfName, tfBacknum, tfPosition, tfBirth;
	JButton btnImage, btnUpdate;

	String imageName;



	public playerUpdateForm() {
		super("������������");
		this.setBounds(900, 100, 300, 470);
		this.setDesign();
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setDesign() {
		this.setLayout(null);

		lbTitle = new JLabel("<������ ���� ���� �Է�>", JLabel.CENTER);
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

		btnUpdate = new JButton("�����ϱ�");
		btnUpdate.setBounds(90, 370, 100, 30);
		btnUpdate.addActionListener(this);
		this.add(btnUpdate);

	}

	public static void main(String[] args) {
		playerUpdateForm ex = new playerUpdateForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==btnImage) {
			FileDialog dlg = new FileDialog(this, "�̹��� ��������", FileDialog.LOAD); //������ �������� �� â �ߴ� �ڵ�
			dlg.setVisible(true); //�� â�� ���̰�!!!
			//��� ������ �޼��� ����
			if(dlg.getDirectory()==null) {
				return;
			}
			imageName = dlg.getDirectory() + dlg.getFile();
			lbPhoto.setText(imageName);
		
		} else if(ob==btnUpdate) {
			KBOPlayerDTO dto = new KBOPlayerDTO();
			dto.setTeam(tfTeam.getText());
			dto.setName(tfName.getText());
			dto.setBacknum(Integer.parseInt(tfBacknum.getText()));
			dto.setPosition(tfPosition.getText());
			dto.setPhoto(lbPhoto.getText());
			
			dbModel.updatePlayer(dto);
			JOptionPane.showMessageDialog(this, "���������� ����Ǿ����ϴ�.");
		}

	}
}