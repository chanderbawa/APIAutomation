package genericFunctions;

import java.io.File;

import TestScripts.BaseTest;

public class GenericFunctions extends BaseTest{
	
	/**
	 * @param	userName 			
	 * 			passWord 
	 * 
	 * @return 	returns login token
	 * */
	public String getLoginToken() {
		File jsonDataInFile = new File("src/test/resources/payloads/AuthPayload.json");

		res = utils.RestAssuredUtil.postCallWithHeader("auth", jsonDataInFile);
		testUtil.checkStatusCode(res, 201);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		String token = jp.get("token");
		System.out.println("token: " + token);
		return token;
	}

}
