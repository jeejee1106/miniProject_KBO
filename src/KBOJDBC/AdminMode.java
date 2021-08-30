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
	Font font1 = new Font("휴먼둥근헤드라인", 0, 25);
	JButton btnAddPlayer, btnUpPlayer, btnDelPlayer, btnRefresh;
	JTable table;
	JLabel lbTitle;
	//라디오버튼 
	JRadioButton[] rb = new JRadioButton[5];

	int row = -1;
	
	public AdminMode() {
		super("전체선수 정보");
		this.setBounds(100, 100, 700, 500);
		this.setDesign();
		this.selectPlayer(1); //프레임 열리자마자 테이블을 어떻게 정렬할 건지? 이 코드를 안써주면 시퀀스 번호대로(등록한 순서대로) 테이블이 출력된다.
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setDesign() {
		this.setLayout(null);

		lbTitle = new JLabel("KBO 전체선수 정보", JLabel.CENTER);
		lbTitle.setBounds(50, 10, 550, 100);
		lbTitle.setFont(font1);
		this.add(lbTitle);
		
		/////////선수정보 변경 버튼/////////
		btnAddPlayer = new JButton("선수 등록");
		btnAddPlayer.setBounds(550, 20, 120, 20);
		btnAddPlayer.addActionListener(this);
		this.add(btnAddPlayer);

		btnUpPlayer = new JButton("선수정보 변경");
		btnUpPlayer.setBounds(550, 50, 120, 20);
		btnUpPlayer.addActionListener(this);
		this.add(btnUpPlayer);

		btnDelPlayer = new JButton("선수 삭제");
		btnDelPlayer.setBounds(550, 80, 120, 20);
		btnDelPlayer.addActionListener(this);
		this.add(btnDelPlayer);
		
		btnRefresh = new JButton("새로고침");
		btnRefresh.setBounds(30, 30, 100, 20);
		btnRefresh.addActionListener(this);
		this.add(btnRefresh);

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
		if(select ==1) { //전체 라디오 버튼은 기본으로 이름오름차순이 되게 하기 위함
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

			tableModel.setRowCount(0); //기존 데이터 삭제하고 새로운 라디오버튼에 대한 데이터

			while(rs.next()) {
				//테이블에 추가할 벡터 선언. DB의 넘버라도 계산할 거 아니면 스트링으로 읽을거임.
				Vector<String> data = new Vector<String>();
				data.add(rs.getString("team"));
				data.add(rs.getString("name"));
				data.add(rs.getString("backnum"));
				data.add(rs.getString("position"));
				data.add(rs.getString("birth").substring(0, 10));

				//벡터의 데이터를 행으로 추가
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
	
	
	//라디오버튼 다른걸로 누르면 그에 맞는 셀렉트플레이어 호출?
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getSource();
		for(int i = 0; i<rb.length; i++) {
			if(rb[i]==ob) {
				this.selectPlayer(i+1);
			}
		}	
	}

	//버튼이벤트 인터페이스 메서드 오버라이드!
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==btnAddPlayer) {
			PlayerAddForm add = new PlayerAddForm();
		} else if(ob==btnUpPlayer){
			String name =JOptionPane.showInputDialog(btnUpPlayer, "수정할 선수의 이름을 입력해주세요");
			if(name==null) { // 엑스 누르면 null반환
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
				JOptionPane.showMessageDialog(this, "삭제할 선수를 선택해주세요");
				return;
			} 
			
			String name = (String) tableModel.getValueAt(row, 1); //원하는 행의 컬럼번호! (name은 컬럼범호 1)
			
			int scd = JOptionPane.showConfirmDialog(this, name + "선수를 삭제하시겠습니까?");
			if(scd==0) {
				String sql = "delete from KBOPLAYER where name=?";
				Connection conn = db.getLocalOracle();
				PreparedStatement pstmt = null;
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.execute();
					
					this.selectPlayer(1); //삭제 후 전체 데이터 다시 불러오기
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					db.dbClose(pstmt, conn);
				}
			}
		} else if(ob==btnRefresh) {
			getAllPlayer(); //새로 테이블을 가져오면 되는거라 테이블 출력하는 메서드를 호출해줬다.
		}
	}
}
