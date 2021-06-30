package resources;

import pojoPayload.CreateRepoPojo;

public class TestDataBuilder {

CreateRepoPojo req_payload = new CreateRepoPojo();
	
	public CreateRepoPojo createRepoPayload(String name, String desccription) {
		req_payload.setName(name);
	    req_payload.setDescription(desccription);
	    return req_payload;
	}

	public String getRepoName() {
		return req_payload.getName();
	}
	
}
