package KBOJDBC;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.db.DbConnect;
import oracle.net.nt.MQLNTAdapter;

public class AllPlayer extends JFrame implements ItemListener{
	KBO_DBModel dbModel = new KBO_DBModel();
	DbConnect db = new DbConnect();
	DefaultTableModel tableModel;
	Vector<KBOPlayerDTO> playerList;
	Font font1 = new Font("�޸յձ�������", 0, 25);
	JTable table;
	JLabel lbTitle;
	//������ư 
	JRadioButton[] rb = new JRadioButton[5];
	//	String selectName;
	//	Vector<JoinDTO> jumunList;

	public AllPlayer() {
		super("��ü���� ����");
		this.setBounds(100, 100, 700, 500);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setDesign() {
		this.setLayout(null);

		lbTitle = new JLabel("KBO ��ü���� ����", JLabel.CENTER);
		lbTitle.setBounds(50, 10, 550, 100);
		lbTitle.setFont(font1);
		this.add(lbTitle);
		
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
		if(select ==1) { //��ü ���� ��ư�� �⺻���� �̸����������� �ǰ�
			sql = "select team, name, backnum, position, birth from kboplayer order by name";
		} else if(select ==2) {
			sql = "select team, name, backnum, position, birth from kboplayer order by team";
		} else if(select ==3) {
			sql = "select team, name, backnum, position, birth from kboplayer order by backnum";			
		} else if(select ==4) {
			sql = "select team, name, backnum, position, birth from kboplayer order by position";	
		} else if(select ==5) {
			sql = "select team, name, backnum, position, birth from kboplayer order by birth";	
		} else if(select ==6) {
			sql = "select rownum no, num id, name, buseo, gender, to_char(pay, 'L999,999,999') pay from sawon where buseo='������'";	
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
				data.add(rs.getString("birth"));
				
				//������ �����͸� ������ �߰�
				tableModel.addRow(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
	}

	public static void main(String[] args) {
		AllPlayer ex = new AllPlayer();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getSource();
		for(int i = 0; i<rb.length; i++) {
			if(rb[i]==ob) {
				this.selectPlayer(i+1);
			}
		}	
	}	
}
