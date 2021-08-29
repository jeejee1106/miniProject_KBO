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
	Font font1 = new Font("휴먼둥근헤드라인", 0, 25);
	JTable table;
	JLabel lbTitle;
	//라디오버튼 
	JRadioButton[] rb = new JRadioButton[5];
	//	String selectName;
	//	Vector<JoinDTO> jumunList;

	public AllPlayer() {
		super("전체선수 정보");
		this.setBounds(100, 100, 700, 500);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setDesign() {
		this.setLayout(null);

		lbTitle = new JLabel("KBO 전체선수 정보", JLabel.CENTER);
		lbTitle.setBounds(50, 10, 550, 100);
		lbTitle.setFont(font1);
		this.add(lbTitle);
		
		//그룹으로 묶어서 라디오버튼 생성
		ButtonGroup bg = new ButtonGroup();
		String[] rb_select = {"전체", "소속팀", "등번호", "포지션", "생년월일"};
		int xpos = 50;
		for(int i = 0; i<rb.length; i++) {
			rb[i] = new JRadioButton(rb_select[i], i==0?true:false);
			rb[i].setBounds(xpos, 100, 90, 30);
			rb[i].setOpaque(false); //버튼배경? 없애는 코드
			//이벤트 추가
			rb[i].addItemListener(this);
			bg.add(rb[i]);
			this.add(rb[i]);
			xpos+=100;
		}

		String[] title = {"소속팀", "이름", "등번호", "포지션", "생년월일"};
		tableModel = new DefaultTableModel(title, 0);
		table = new JTable(tableModel);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(50, 150, 600, 300);
		this.add(js);
		getAllPlayer();
	}
	//테이블에 데이터 추가?출력?하는 메서드!!!
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

	//라디오버튼 누르면 그에 맞는 DB가 출력되게 하는 메서드
	public void selectPlayer(int select) {
		String sql = "";
		if(select ==1) { //전체 라디오 버튼은 기본으로 이름오름차순이 되게
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
			sql = "select rownum no, num id, name, buseo, gender, to_char(pay, 'L999,999,999') pay from sawon where buseo='관리부'";	
		}
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			tableModel.setRowCount(0); //기존 데이터 삭제하고 새로운 라디오버튼에 대한 데이터
			
			while(rs.next()) {
				//테이블에 추가할 벡터 선언. DB의 넘버라도 계산할 거 아니면 스트링으로 읽을거임.
				Vector<String> data = new Vector<String>();
				data.add(rs.getString("team"));
				data.add(rs.getString("name"));
				data.add(rs.getString("backnum"));
				data.add(rs.getString("position"));
				data.add(rs.getString("birth"));
				
				//벡터의 데이터를 행으로 추가
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
