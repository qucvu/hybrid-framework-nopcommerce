package pageObjects.wordpress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import jdbc.database.test.MySQLConnUtils;
import pageUIs.wordpress.AdminUserPageUI;

public class AdminUserPO extends BasePage {
	private WebDriver driver;

	public AdminUserPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public int getUserNumberAtUI() {
		waitForElementVisibility(driver, AdminUserPageUI.QUANTITY_USER_ITEM);
		String totalUserText = getElementText(driver, AdminUserPageUI.QUANTITY_USER_ITEM);
		return Integer.parseInt(totalUserText.split("")[0]);
	}

	public int getUserNumberAtDB() {
		Connection conn = MySQLConnUtils.getMySQLConnection();
		Statement statement;
		List<Integer> totalUser = new ArrayList<>();
		try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM wp_users";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				totalUser.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		
		return totalUser.size();
	}

}
