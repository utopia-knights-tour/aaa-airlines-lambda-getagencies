package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Agency;

public class AgencyDao {

	private Connection connection;

	public AgencyDao(Connection connection) {
		this.connection = connection;
	}

	public List<Agency> get() throws SQLException {
		List<Agency> agencies = new ArrayList<Agency>();
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
		return agencies;
	}
}
