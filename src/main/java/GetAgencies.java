
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.Agency;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import service.AgentService;

public class GetAgencies implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	private AgentService agentService = new AgentService();

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		LambdaLogger logger = context.getLogger();
		try {
			List<Agency> agencies = agentService.getAgencies();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Access-Control-Allow-Origin", "*");
			return new ApiGatewayProxyResponse(200, headers, new Gson().toJson(agencies));
		} catch (SQLException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(400, null, null);
		} catch (ClassNotFoundException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(500, null, null);
		}
	}
}