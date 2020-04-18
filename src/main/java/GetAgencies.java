
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.Agency;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import util.ConnectUtil;

public class GetAgencies implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		List<Agency> agencies = new ArrayList<Agency>();
		Connection connection = null;
		LambdaLogger logger = context.getLogger();
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
		} catch (SQLException e) {
			return new ApiGatewayProxyResponse(400, null, null);
		} catch (ClassNotFoundException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(500, null, null);
		}
		return new ApiGatewayProxyResponse(200, null, new Gson().toJson(agencies));
	}
}