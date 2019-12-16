package com.ycl.register.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 鏁帮拷?锟藉簱杩炴帴涓庡叧闂伐鍏风被銆�
 */
public abstract class BaseDao {
	private static String driver = 
		"com.mysql.jdbc.Driver";// 鏁帮拷?锟藉簱椹卞姩瀛楃涓�
	private static String url = 
		"jdbc:mysql://localhost:3306/ycl_user?useUnicode=true&characterEncoding=utf-8";// 杩炴帴URL瀛楃涓�
	private static String user = "root"; // 鏁帮拷?锟藉簱鐢ㄦ埛锟�??
	private static String password = "root"; // 鐢ㄦ埛瀵嗭拷?
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	/**
	 * 鑾凤拷?锟芥暟锟�?锟藉簱杩炴帴瀵硅薄銆�
	 */
	public Connection getConnection() {
		Connection conn = null;// 鏁帮拷?锟借繛鎺ュ璞�
		// 鑾凤拷?锟借繛鎺ュ苟锟�?锟借幏寮傚父
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);			
		} catch (Exception e) {
			e.printStackTrace();// 寮傚父澶勶拷?锟�
		}
		return conn;// 杩斿洖杩炴帴瀵硅薄
	}
	/**
	 * 鍏抽棴鏁帮拷?锟藉簱杩炴帴銆�
	 * @param conn 鏁帮拷?锟藉簱杩炴帴
	 * @param stmt Statement瀵硅薄
	 * @param rs 缁撴灉闆�
	 */
	public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		// 鑻ョ粨鏋滈泦瀵硅薄锟�?涓虹┖锛屽垯鍏抽棴
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 鑻tatement瀵硅薄锟�?涓虹┖锛屽垯鍏抽棴
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 鑻ユ暟锟�?锟藉簱杩炴帴瀵硅薄锟�?涓虹┖锛屽垯鍏抽棴
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeAll() {
		closeAll(this.conn, this.pstmt, this.rs);
	}
	
	/**
	 * 澧烇拷?鍒狅拷?鏀癸拷?浣�
	 * @param sql sql璇拷?锟�
	 * @param params 锟�?锟芥暟鏁扮粍
	 * @return 鎵ц缁撴灉
	 */
	//
	public int exceuteUpdate(String sql, Object...params){
		int result = 0;
		conn = this.getConnection();
		try {
			if(conn != null && conn.isClosed() == false) {
				pstmt = conn.prepareStatement(sql);
				for(int i = 0;i < params.length;i++){
					pstmt.setObject(i + 1, params[i]);	
				}
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		
		return result;
	}
	
	public Object executeQuery(RSProcessor processor, String sql, Object...params) {
		Object result = null;
		conn = this.getConnection();
		try {
			if(conn != null && conn.isClosed() == false) {
				pstmt = conn.prepareStatement(sql);
				for(int i = 0;i < params.length;i++){
					pstmt.setObject(i + 1, params[i]);	
				}
				rs = pstmt.executeQuery();
				result = processor.process(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return result;
	}
}
