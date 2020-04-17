
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import entity.Agency;
import util.ConnectUtil;

public class GetAgencies implements RequestHandler<Map<String, String>, Object> {

	public Object handleRequest(Map<String, String> event, Context context) {
		List<Agency> agencies = new ArrayList<Agency>();
		Connection connection = null;
		try {
			connection = ConnectUtil.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Agency");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Agency agency = new Agency();
				agency.setId(rs.getLong("agencyId"));
				agency.setName(rs.getString("agencyName"));
				agency.setAddress(rs.getString("agencyAddress"));
				agency.setPhone(rs.getString("agencyPhone"));
				agencies.add(agency);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return agencies;
	}
}