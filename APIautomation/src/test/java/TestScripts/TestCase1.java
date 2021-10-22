package TestScripts;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({ Allure_Listener.class })
public class TestCase1 extends BaseTest {
	
	@Test(priority = 2, enabled = true)
	@Description("Validate Delete booking details")
	@Epic("Booking")
	@Feature("Delete Booking")
	@Story("Delete user bookings")
	@Step("Validate Delete booking status code")
	@Severity(SeverityLevel.NORMAL)
	public void T01_validateDeleteBooking() {
		File jsonAuthFile = new File("src/test/resources/payloads/AuthPayload2.json");
		// File jsonCreateBookingFile = new
		// File("src/test/resources/payloads/createBooking.json");

		//res = utils.RestAssuredUtil.getResponse("/booking");
		//testUtil.checkStatusIs200(res);
		//jp = utils.RestAssuredUtil.getJsonPath(res);
		// System.out.println(testUtil.getClients(jp));
		//String bookingID = jp.getString("bookingid[0]");

		res = utils.RestAssuredUtil.postCallWithHeader("https://dev.bc.conns.com/oauth/client_credential/accesstoken?grant_type=client_credentials", jsonAuthFile);
		testUtil.checkStatusCode(res, 200);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		String token = jp.get("token");
		System.out.println("token: " + token);

		//res = utils.RestAssuredUtil.DeleteCallWithAuth("/booking/" + bookingID, token);
		//testUtil.checkStatusCode(res, 201);
	}
}