import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import entity.Agency;

public class GetAgenciesTests {

	@Test
	public void getAgencies() {
		GetAgencies getAgencies = new GetAgencies();
		List<Agency> agencies = (List<Agency>) getAgencies.handleRequest(null, null);
		assertFalse(agencies.isEmpty());
	}

}
