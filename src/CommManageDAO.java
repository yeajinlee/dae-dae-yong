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

	//������
	CommManageDAO(){
		try {
			Class.forName(driver); //����̹� �ε�
		} catch (ClassNotFoundException e) {// ����̹� �ε�����
			e.printStackTrace();
			System.out.println("�ε� ����");
		} 

	}
	//DB���� MemInfo ������ �ҷ�����
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
				//�Խñ� �� ī��Ʈ
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
				//��� �� ī��Ʈ
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

	//DB���� PostInfo ������ �ҷ�����
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
				//�����ͺ��̽� �ڿ��� �ݵ�� �ݾ��־�� �Ѵ�. �׷��� finally�� �� ��
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;
	}
	//DB���� MemInfo ���� �˻��ؼ� �ҷ�����
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
				//�Խñ� ī��Ʈ
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
				//��� �� ī��Ʈ
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
	//DB���� postinfo ���ǰ˻� �ҷ�����
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
				//�����ͺ��̽� �ڿ��� �ݵ�� �ݾ��־�� �Ѵ�. �׷��� finally�� �� ��
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;
	}



	// ȸ�� ���� �����ϱ� 
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
			if(n == 1) { //������ ������ ���
				System.out.println("���� ����");
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�."); // ���̾�α�â���� �����Ǿ��ٴ°��� Ȯ��
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
	//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡٵ�� ����
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
				System.out.println("���强����");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ID�Դϴ�.");
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

	public void memdelete(String id) { // ��ϵ� ȸ���� �����ϴ� �Լ�, id�� �Ű������� �޾� ������ ������ �Ǻ�
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
				System.out.println("������");
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
	//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡٵ�� ����
	public void postdelete(String num) // ��ϵ� �Խñ��� �����ϴ� �Լ�, �Ű������� �Խñ۹�ȣ num�� �޾� ������ �Խñ� �Ǵ�
	{ // �� ���� �ϱ�
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "delete from commmanagedb.postinfo where postNum = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			int n = pstmt.executeUpdate();
			if(n == 1) 
				System.out.println("������");
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

	public int login(String ID, String Password) { // � ������ ���� ������ �α����� �õ��ϴ� �Լ�, ���ڰ����� ID�� Password�� �޾� login�� �Ǵ�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String SQL = "SELECT pw FROM managelogininfo WHERE id = ?"; // ������ DB�� �Էµ� SQL��
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  ID);
			rs = pstmt.executeQuery(); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ����� �־��� 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(Password)) {
					JOptionPane.showMessageDialog(null, "������ �������� �α��� �Ǿ����ϴ�."); //swing ���̾�α�â���� Ȯ�� �˾�
					return 1; // �α��� ����
				}
				else {
					JOptionPane.showMessageDialog(null, "��й�ȣ ����");
					return 0; // ��й�ȣ ����ġ
				}
			}
			JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵��Դϴ�.");
			return -1; // ���̵� ����
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
		return -2; // DB ���� 
	}
}
