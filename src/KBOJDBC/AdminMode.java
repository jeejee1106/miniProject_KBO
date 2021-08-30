package KBOJDBC;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.db.DbConnect;
import oracle.net.nt.MQLNTAdapter;

public class AdminMode extends JFrame implements ItemListener, ActionListener{
	KBO_DBModel dbModel = new KBO_DBModel();
	DbConnect db = new DbConnect();
	DefaultTableModel tableModel;
	Vector<KBOPlayerDTO> playerList;
	Font font1 = new Font("�޸յձ�������", 0, 25);
	JButton btnAddPlayer, btnUpPlayer, btnDelPlayer, btnRefresh;
	JTable table;
	JLabel lbTitle;
	//������ư 
	JRadioButton[] rb = new JRadioButton[5];

	int row = -1;
	
	public AdminMode() {
		super("��ü���� ����");
		this.setBounds(100, 100, 700, 500);
		this.setDesign();
		this.selectPlayer(1); //������ �����ڸ��� ���̺��� ��� ������ ����? �� �ڵ带 �Ƚ��ָ� ������ ��ȣ���(����� �������) ���̺��� ��µȴ�.
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setDesign() {
		this.setLayout(null);

		lbTitle = new JLabel("KBO ��ü���� ����", JLabel.CENTER);
		lbTitle.setBounds(50, 10, 550, 100);
		lbTitle.setFont(font1);
		this.add(lbTitle);
		
		/////////�������� ���� ��ư/////////
		btnAddPlayer = new JButton("���� ���");
		btnAddPlayer.setBounds(550, 20, 120, 20);
		btnAddPlayer.addActionListener(this);
		this.add(btnAddPlayer);

		btnUpPlayer = new JButton("�������� ����");
		btnUpPlayer.setBounds(550, 50, 120, 20);
		btnUpPlayer.addActionListener(this);
		this.add(btnUpPlayer);

		btnDelPlayer = new JButton("���� ����");
		btnDelPlayer.setBounds(550, 80, 120, 20);
		btnDelPlayer.addActionListener(this);
		this.add(btnDelPlayer);
		
		btnRefresh = new JButton("���ΰ�ħ");
		btnRefresh.setBounds(30, 30, 100, 20);
		btnRefresh.addActionListener(this);
		this.add(btnRefresh);

		//�׷����� ��� ������ư ����
		ButtonGroup bg = new ButtonGroup();
		String[] rb_select = {"��ü", "�Ҽ���", "���ȣ", "������", "�������"};
		int xpos = 50;
		for(int i = 0; i<rb.length; i++) {
			rb[i] = new JRadioButton(rb_select[i], i==0?true:false);
			rb[i].setBounds(xpos, 100, 90, 30);
			rb[i].setOpaque(false); //��ư���? ���ִ� �ڵ�
			//�̺�Ʈ �߰�
			rb[i].addItemListener(this);
			bg.add(rb[i]);
			this.add(rb[i]);
			xpos+=100;
		}

		String[] title = {"�Ҽ���", "�̸�", "���ȣ", "������", "�������"};
		tableModel = new DefaultTableModel(title, 0);
		table = new JTable(tableModel);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(50, 150, 600, 300);
		this.add(js);
		getAllPlayer();
	}


	//���̺� ������ �߰�?���?�ϴ� �޼���!!!
	public void getAllPlayer() {
		tableModel.setRowCount(0);
		playerList = dbModel.playerList();

		for(KBOPlayerDTO dto : playerList) {
			Vector<String> data = new Vector<String>();
			data.add(dto.getTeam());
			data.add(dto.getName());
			data.add(String.valueOf(dto.getBacknum()));
			data.add(dto.getPosition());
			data.add(dto.getBirth());

			tableModel.addRow(data);
		}

	}

	//������ư ������ �׿� �´� DB�� ��µǰ� �ϴ� �޼���
	public void selectPlayer(int select) {
		String sql = "";
		if(select ==1) { //��ü ���� ��ư�� �⺻���� �̸����������� �ǰ� �ϱ� ����
			sql = "select team, name, backnum, position, birth from kboplayer order by name";
		} else if(select ==2) {
			sql = "select team, name, backnum, position, birth from kboplayer order by team";
		} else if(select ==3) {
			sql = "select team, name, backnum, position, birth from kboplayer order by backnum";			
		} else if(select ==4) {
			sql = "select team, name, backnum, position, birth from kboplayer order by position";	
		} else if(select ==5) {
			sql = "select team, name, backnum, position, birth from kboplayer order by birth";	
		} 
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			tableModel.setRowCount(0); //���� ������ �����ϰ� ���ο� ������ư�� ���� ������

			while(rs.next()) {
				//���̺� �߰��� ���� ����. DB�� �ѹ��� ����� �� �ƴϸ� ��Ʈ������ ��������.
				Vector<String> data = new Vector<String>();
				data.add(rs.getString("team"));
				data.add(rs.getString("name"));
				data.add(rs.getString("backnum"));
				data.add(rs.getString("position"));
				data.add(rs.getString("birth").substring(0, 10));

				//������ �����͸� ������ �߰�
				tableModel.addRow(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
	}

//	public static void main(String[] args) {
//		AdminMode ex = new AdminMode();
//	}
	
	
	//������ư �ٸ��ɷ� ������ �׿� �´� ����Ʈ�÷��̾� ȣ��?
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getSource();
		for(int i = 0; i<rb.length; i++) {
			if(rb[i]==ob) {
				this.selectPlayer(i+1);
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
			String name =JOptionPane.showInputDialog(btnUpPlayer, "������ ������ �̸��� �Է����ּ���");
			if(name==null) { // ���� ������ null��ȯ
				return;
			} else {
				KBOPlayerDTO dto = dbModel.getPlayerProfile(name);
				playerUpdateForm updateForm = new playerUpdateForm();
				
				updateForm.tfTeam.setText(dto.getTeam());
				updateForm.tfName.setText(dto.getName());
				updateForm.tfBacknum.setText(String.valueOf(dto.backnum));
				updateForm.tfPosition.setText(dto.getPosition());
				updateForm.tfBirth.setText(String.valueOf(dto.getBirth()));
				updateForm.lbPhoto.setText(dto.getPhoto());
				
				updateForm.setVisible(true);
			}
		} else if(ob==btnDelPlayer){
			row = table.getSelectedRow();
			
			if(row==-1) {
				JOptionPane.showMessageDialog(this, "������ ������ �������ּ���");
				return;
			} 
			
			String name = (String) tableModel.getValueAt(row, 1); //���ϴ� ���� �÷���ȣ! (name�� �÷���ȣ 1)
			
			int scd = JOptionPane.showConfirmDialog(this, name + "������ �����Ͻðڽ��ϱ�?");
			if(scd==0) {
				String sql = "delete from KBOPLAYER where name=?";
				Connection conn = db.getLocalOracle();
				PreparedStatement pstmt = null;
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.execute();
					
					this.selectPlayer(1); //���� �� ��ü ������ �ٽ� �ҷ�����
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					db.dbClose(pstmt, conn);
				}
			}
		} else if(ob==btnRefresh) {
			getAllPlayer(); //���� ���̺��� �������� �Ǵ°Ŷ� ���̺� ����ϴ� �޼��带 ȣ�������.
		}
	}
}
