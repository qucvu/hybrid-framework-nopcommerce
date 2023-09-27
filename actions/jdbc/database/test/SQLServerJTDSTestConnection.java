package jdbc.database.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerJTDSTestConnection {

	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return SQLServerJTDSConnUtils.getSQLServerConnection();
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection... ");

		// Lấy ra đối tượng Connection kết nối vào database.
		Connection conn = SQLServerJTDSTestConnection.getMyConnection();

		System.out.println("Opened connection: " + conn);

		Statement statement = conn.createStatement();

		String sql = "SELECT * FROM [AutomationDatabaseTesting].[dbo].[Product_Type];";
		
		String insertSql = "INSERT INTO PRODUCT_TYPE VALUES ('Test2', 'Vu123');";
		
		String sqlPrepareStatement = "SELECT * FROM [AutomationDatabaseTesting].[dbo].[Product_Type] where PRODUCT_TYPE_CD like ?;";
				

//		int row = statement.executeUpdate(insertSql);
//		System.out.println("Row update: " + row);
		
		// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//		ResultSet rs = statement.executeQuery(sql);

		
		// demo prepare statement
		PreparedStatement pstm = conn.prepareStatement(sqlPrepareStatement);
		pstm.setString(1, "test%");
		
		ResultSet rs = pstm.executeQuery();

		// Duyệt trên kết quả trả về
		while (rs.next()) {
			// Di chuyển con trỏ xuống bản ghi kế tiếp.
			String productType = rs.getString(1);
			String name = rs.getString("NAME");

			System.out.println("--------------------");
			System.out.println("Product Type:" + productType);
			System.out.println("Name:" + name);
		}
		// Đóng kết nối
		conn.close();
		System.out.println("---------- Closed connection ----------");
	}


}
