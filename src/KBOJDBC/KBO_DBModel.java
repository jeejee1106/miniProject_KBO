package KBOJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import oracle.db.DbConnect;

public class KBO_DBModel {
	DbConnect db = new DbConnect();
	
	//DB에 데이터 추가!!!
	public void insertPlayer(KBOPlayerDTO dto) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		String sql = "insert into KBOPLAYER values (SEQ_KBO.nextval, ?,?,?,?,to_date(?,'yyyy-mm-dd'),?)";
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTeam());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getBacknum());
			pstmt.setString(4, dto.getPosition());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getPhoto());
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	//DB데이터에서 선수 이름 배열로 가져오기!(콤보박스에 넣어서 선택하면 그 선수의 프로필 출력)
	public Object[] getNameArray() {
		String sql = "select name from KBOPLAYER order by name";
		Vector<String> nameList = new Vector<String>();
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				nameList.add(rs.getString(1)); //여기 들어가는 숫자가 뭐지??????
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return nameList.toArray();
		
	}
	
	//
	public KBOPlayerDTO getPlayerProfile(String name) {
		KBOPlayerDTO dto = new KBOPlayerDTO();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select team, name, backnum, position, birth, photo from KBOPLAYER where name=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setTeam(rs.getString("team"));
				dto.setName(rs.getString("name"));
				dto.setBacknum(rs.getInt("backnum"));
				dto.setPosition(rs.getString("position"));
				dto.setPhoto(rs.getString("photo"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return dto;	
	}
	
	public Vector<KBOPlayerDTO> playerList(){
		Vector<KBOPlayerDTO> list = new Vector<KBOPlayerDTO>();
		String sql = "select team, name, backnum, position, birth, photo from KBOPLAYER";
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				KBOPlayerDTO dto = new KBOPlayerDTO();
				dto.setTeam(rs.getString("team"));
				dto.setName(rs.getString("name"));
				dto.setBacknum(rs.getInt("backnum"));
				dto.setPosition(rs.getString("position"));
				dto.setBirth(rs.getString("birth"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}
	

	
	

}
