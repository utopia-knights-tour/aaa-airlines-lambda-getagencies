package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AgencyDao;
import datasource.HikariCPDataSource;
import entity.Agency;

public class AgentService {

	public List<Agency> getAgencies() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			return new AgencyDao(connection).get();
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.close();
		}
	}
}
