package chain;

import org.testng.annotations.Test;

public class DeleteIncident extends BaseRestImpl {
	
	@Test(dependsOnMethods = "chain.PutIncident.sendPutRequest")
	public void sendDeleteRequest() {	
		response = inputRequest
					.when()
					.delete(global_sys_id);
	}

}
