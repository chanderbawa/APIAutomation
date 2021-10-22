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
public class BasicApiTest extends BaseTest {
	@Test(priority = 1, enabled = true)
	@Description("Validate get booking details")
	@Epic("Booking Details")
	@Feature("Get Booking Details")
	@Story("Get user bookings details")
	@Step("Validate User booking status code")
	@Severity(SeverityLevel.MINOR)
	public void T01_validateGettingAuthToken() {
		File jsonDataInFile = new File("src/test/resources/payloads/AuthPayload.json");

		res = utils.RestAssuredUtil.postCallWithHeader("auth", jsonDataInFile);
		testUtil.checkStatusCode(res, 201);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		String token = jp.get("token");
		System.out.println("token: " + token);

	}

	@Test(priority = 2, enabled = true)
	@Description("Validate get booking ID")
	@Epic("Booking Details")
	@Feature("Get Booking IDs")
	@Story("Get user bookings ID")
	@Step("Validate User booking ID")
	@Severity(SeverityLevel.CRITICAL)
	public void T02_ValidateGetBookingIds() {
		res = utils.RestAssuredUtil.getResponse("/booking");
		testUtil.checkStatusIs200(res);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		// System.out.println(testUtil.getClients(jp));
		String bookingID = jp.getString("bookingid[0]");
		Assert.assertEquals(bookingID, "11");
	}

	@Test(priority = 2, enabled = true)
	@Description("Validate update booking details")
	@Epic("Update Booking")
	@Feature("Update Booking")
	@Story("Update user bookings details")
	@Step("Validate Update booking status code")
	@Severity(SeverityLevel.NORMAL)
	public void T01_validateGetBookingDetails() {
		File jsonAuthFile = new File("src/test/resources/payloads/AuthPayload.json");
		File jsonUserBookingFile = new File("src/test/resources/payloads/userBookingDetails.json");

		
		res = utils.RestAssuredUtil.postCallWithHeader("auth", jsonAuthFile);
		testUtil.checkStatusCode(res, 200);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		String token = jp.get("token");
		System.out.println("token: " + token);

		res = utils.RestAssuredUtil.putCallWithAuthAndHeader("/booking/1", token, jsonUserBookingFile);
		testUtil.checkStatusCode(res, 200);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		// System.out.println(testUtil.getClients(jp));
		Assert.assertEquals(jp.get("firstname"), "Amod");
	}

	@Test(priority = 2, enabled = true)
	@Description("Validate Create booking")
	@Epic("Create Booking")
	@Feature("Create Booking")
	@Story("Create user bookings")
	@Step("Validate Create booking status code")
	@Severity(SeverityLevel.NORMAL)
	public void T01_validateCreateBooking() {
		File jsonAuthFile = new File("src/test/resources/payloads/AuthPayload.json");
		File jsonCreateBookingFile = new File("src/test/resources/payloads/createBooking.json");

		res = utils.RestAssuredUtil.postCallWithHeader("auth", jsonAuthFile);
		testUtil.checkStatusCode(res, 200);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		String token = jp.get("token");
		System.out.println("token: " + token);

		res = utils.RestAssuredUtil.postCallWithAuthAndHeader("/booking", token, jsonCreateBookingFile);
		testUtil.checkStatusCode(res, 200);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		// System.out.println(testUtil.getClients(jp));
		Assert.assertEquals(jp.get("booking.firstname"), "Jim");
	}

	@Test(priority = 2, enabled = true)
	@Description("Validate Delete booking details")
	@Epic("Booking")
	@Feature("Delete Booking")
	@Story("Delete user bookings")
	@Step("Validate Delete booking status code")
	@Severity(SeverityLevel.NORMAL)
	public void T01_validateDeleteBooking() {
		File jsonAuthFile = new File("src/test/resources/payloads/AuthPayload.json");
		// File jsonCreateBookingFile = new
		// File("src/test/resources/payloads/createBooking.json");

		res = utils.RestAssuredUtil.getResponse("/booking");
		testUtil.checkStatusIs200(res);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		// System.out.println(testUtil.getClients(jp));
		String bookingID = jp.getString("bookingid[0]");

		res = utils.RestAssuredUtil.postCallWithHeader("auth", jsonAuthFile);
		testUtil.checkStatusCode(res, 200);
		jp = utils.RestAssuredUtil.getJsonPath(res);
		String token = jp.get("token");
		System.out.println("token: " + token);

		res = utils.RestAssuredUtil.DeleteCallWithAuth("/booking/" + bookingID, token);
		testUtil.checkStatusCode(res, 201);
	}
}