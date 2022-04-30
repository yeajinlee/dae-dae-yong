import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CommManageDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/commmanagedb";
	String userid="root";
	String passwd = "1234";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null; 
	ResultSet rs = null;
	int postCount;
	String cnt = null;
	int commentCount;
	int postComment;

	//생성자
	CommManageDAO(){
		try {
			Class.forName(driver); //드라이버 로딩
		} catch (ClassNotFoundException e) {// 드라이버 로딩실패
			e.printStackTrace();
			System.out.println("로딩 실패");
		} 

	}
	//DB에서 MemInfo 테이터 불러오기
	public ArrayList<CommManageDTO> memInfoselect() {
		ArrayList<CommManageDTO> list = new ArrayList<CommManageDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from meminfo";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommManageDTO dto = new CommManageDTO();
				dto.setName(rs.getString("name"));
				dto.setID(rs.getString("id"));
				dto.setLevel(rs.getInt("level"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getString("joinDate"));
				dto.setLogDate(rs.getString("logDate"));
				//게시글 수 카운트
				Connection conn2 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs2 = null;
				conn2 = DriverManager.getConnection(url, userid, passwd);
				String query2 = "select id, count(*) as postCount from postinfo where id = ?";
				pstmt2 = conn2.prepareStatement(query2);
				String ID = dto.getID();
				pstmt2.setString(1, ID);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					cnt = rs2.getString("postCount");
				}
				postCount = Integer.parseInt(cnt);
				dto.setMemPost(postCount);
				//댓글 수 카운트
				Connection conn3 = null;
				PreparedStatement pstmt3 = null;
				ResultSet rs3 = null;
				conn3 = DriverManager.getConnection(url, userid, passwd);
				String query3 = "select id, count(*) as commentCount from commentinfo where id = ?";
				pstmt3 = conn3.prepareStatement(query3);
				//ID = dto.getID();
				pstmt3.setString(1, ID);
				rs3 = pstmt3.executeQuery();
				if (rs3.next()) {
					cnt = rs3.getString("commentCount");
				}
				commentCount = Integer.parseInt(cnt);
				dto.setMemComment(commentCount);

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	} 

	//DB에서 PostInfo 데이터 불러오기
	public ArrayList<CommManageDTO> postInfoselect() {
		ArrayList<CommManageDTO> list = new ArrayList<CommManageDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from postinfo";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommManageDTO dto = new CommManageDTO();
				dto.setPostNum(rs.getInt("postNum"));
				dto.setTitle(rs.getString("title"));
				dto.setID(rs.getString("id"));
				dto.setPostDate(rs.getString("postDate"));
				dto.setView(rs.getInt("view"));
				Connection conn2 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs2 = null;
				conn2 = DriverManager.getConnection(url, userid, passwd);
				String query2 = "select postNum, count(*) as commCount from commmanagedb.commentinfo where postNum = ?";
				pstmt2 = conn2.prepareStatement(query2);
				int pNum = dto.getPostNum();
				pstmt2.setInt(1, pNum);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					postComment = rs2.getInt("commCount");
				}
				dto.setPostComment(postComment);
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//데이터베이스 자원은 반드시 닫아주어야 한다. 그래서 finally에 쓴 것
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;
	}
	//DB에서 MemInfo 조건 검색해서 불러오기
	public ArrayList<CommManageDTO> memInfoSearch(String filter, String keyword) {
		ArrayList<CommManageDTO> list = new ArrayList<CommManageDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = null;
			switch (filter) {
			case "id" : query = "select * from commmanagedb.meminfo where id like concat('%', ? ,'%')"; 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "name" : query = "select * from commmanagedb.meminfo where name like concat('%', ? ,'%')";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "level" : query = "select * from commmanagedb.meminfo where level = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "email" : query = "select * from commmanagedb.meminfo where email like concat('%', ? ,'%')";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "all" : query = "select * from commmanagedb.meminfo where name like concat('%', ? ,'%') or id like concat('%', ? ,'%') or email like concat('%', ? ,'%')";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			break;
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommManageDTO dto = new CommManageDTO();
				dto.setName(rs.getString("name"));
				dto.setID(rs.getString("id"));
				dto.setLevel(rs.getInt("level"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getString("joinDate"));
				dto.setLogDate(rs.getString("logDate"));
				//게시글 카운트
				Connection conn2 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs2 = null;
				conn2 = DriverManager.getConnection(url, userid, passwd);
				String query2 = "select id, count(*) as postCount from postinfo where id = ?";
				pstmt2 = conn2.prepareStatement(query2);
				String ID = dto.getID();
				pstmt2.setString(1, ID);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					cnt = rs2.getString("postCount");
				}
				postCount = Integer.parseInt(cnt);
				dto.setMemPost(postCount);
				//댓글 수 카운트
				Connection conn3 = null;
				PreparedStatement pstmt3 = null;
				ResultSet rs3 = null;
				conn3 = DriverManager.getConnection(url, userid, passwd);
				String query3 = "select id, count(*) as commentCount from commentinfo where id = ?";
				pstmt3 = conn3.prepareStatement(query3);
				//ID = dto.getID();
				pstmt3.setString(1, ID);
				rs3 = pstmt3.executeQuery();
				if (rs3.next()) {
					cnt = rs3.getString("commentCount");
				}
				commentCount = Integer.parseInt(cnt);
				dto.setMemComment(commentCount);


				list.add(dto);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return list;
	}
	//DB에서 postinfo 조건검색 불러오기
	public ArrayList<CommManageDTO> postInfoSearch(String filter, String keyword) {
		ArrayList<CommManageDTO> list = new ArrayList<CommManageDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = null;
			switch (filter) {
			case "pNum" : query = "select * from commmanagedb.postinfo where postNum = ?"; 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "title" : query = "select * from commmanagedb.postinfo where title like concat('%', ? ,'%')";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "id" : query = "select * from commmanagedb.postinfo where id like concat('%', ? ,'%')";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			break;
			case "all" : query = "select * from commmanagedb.postinfo where id like concat('%', ? ,'%') or title like concat('%', ? ,'%') or postNum = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			break;
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommManageDTO dto = new CommManageDTO();
				dto.setPostNum(rs.getInt("postNum"));
				dto.setTitle(rs.getString("title"));
				dto.setID(rs.getString("id"));
				dto.setPostDate(rs.getString("postDate"));
				dto.setView(rs.getInt("view"));
				Connection conn2 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs2 = null;
				conn2 = DriverManager.getConnection(url, userid, passwd);
				String query2 = "select postNum, count(*) as commCount from commmanagedb.commentinfo where postNum = ?";
				pstmt2 = conn2.prepareStatement(query2);
				int pNum = dto.getPostNum();
				pstmt2.setInt(1, pNum);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					postComment = rs2.getInt("commCount");
				}
				dto.setPostComment(postComment);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//데이터베이스 자원은 반드시 닫아주어야 한다. 그래서 finally에 쓴 것
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;
	}



	// 회원 정보 수정하기 
	public void update(String id, String name, String level, String email, String joinDate, String logDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "update meminfo set name=?, level=? ,email=?, joinDate=?, logDate=? where id =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(level));
			pstmt.setString(3, email);
			pstmt.setString(4, joinDate);
			pstmt.setString(5, logDate);
			pstmt.setString(6, id);

			int n = pstmt.executeUpdate();
			if(n == 1) { //수정에 성공할 경우
				System.out.println("수정 성공");
				JOptionPane.showMessageDialog(null, "수정되었습니다."); // 다이얼로그창으로 수정되었다는것을 확인
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String detailPostDisplay(int pNum) {

		String postContent = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommManageDTO dto = new CommManageDTO();

		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from commmanagedb.postinfo where postNum = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setPostContent(rs.getString("pContent"));
				postContent = dto.getPostContent();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return postContent;
	}

	public ArrayList<CommManageDTO> detailCommentDisplay(int pNum) {
		ArrayList<CommManageDTO> list = new ArrayList<CommManageDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from commmanagedb.commentinfo where postNum = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommManageDTO dto = new CommManageDTO();
				dto.setCommId(rs.getString("id"));
				dto.setCommDate(rs.getString("comDate"));
				dto.setCommContent(rs.getString("cContent"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}
	//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆등록 삭제
	public void insert(String name, String id, String level, String email) 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;

		try
		{
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into meminfo(id, name, level, email, joinDate, logDate) values(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, Integer.parseInt(level));
			pstmt.setString(4, email);
			LocalDate now = LocalDate.now();
			String today = now.toString();
			pstmt.setString(5, today);
			pstmt.setString(6, today);



			int n = pstmt.executeUpdate();
			if(n == 1)
				System.out.println("저장성공함");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				conn.close();
				pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void memdelete(String id) { // 등록된 회원을 삭제하는 함수, id를 매개변수로 받아 삭제할 데이터 판별
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "delete from commmanagedb.meminfo where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			int n = pstmt.executeUpdate();
			if(n == 1) 
				System.out.println("삭제됨");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
	//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆등록 삭제
	public void postdelete(String num) // 등록된 게시글을 삭제하는 함수, 매개변수로 게시글번호 num을 받아 삭제할 게시글 판단
	{ // 글 삭제 하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "delete from commmanagedb.postinfo where postNum = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			int n = pstmt.executeUpdate();
			if(n == 1) 
				System.out.println("삭제됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}

	public int login(String ID, String Password) { // 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String SQL = "SELECT pw FROM managelogininfo WHERE id = ?"; // 실제로 DB에 입력될 SQL문
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  ID);
			rs = pstmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(Password)) {
					JOptionPane.showMessageDialog(null, "관리자 권한으로 로그인 되었습니다."); //swing 다이얼로그창으로 확인 팝업
					return 1; // 로그인 성공
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호 오류");
					return 0; // 비밀번호 불일치
				}
			}
			JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -2; // DB 오류 
	}
}
