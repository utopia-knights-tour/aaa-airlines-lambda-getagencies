package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AgencyDao;
import entity.Agency;
import util.ConnectUtil;

public class AgentService {

	public List<Agency> getAgencies() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = ConnectUtil.getInstance().getConnection();
			return new AgencyDao(connection).get();
		} catch (SQLException e) {
			throw e;
		}
	}
}
