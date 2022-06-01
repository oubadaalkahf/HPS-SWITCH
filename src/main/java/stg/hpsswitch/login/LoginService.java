package stg.hpsswitch.login;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import stg.hpsswitch.registration.request.RegistrationRequest;
import stg.hpsswitch.registration.response.RegistrationResponse;
import stg.hpsswitch.responseHandler.ResponseHandler;

@Service
public class LoginService {

	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<Object> login(String swift, String email, String password) {
		String url = "http://3.217.215.70:8081/";
		String END_POINT = null;
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(requestHeaders);

		if (swift.equals("attijari")) {
			END_POINT = "attijari";
			url = url + END_POINT + "/login?";

		} else if (swift.equals("cih")) {
			END_POINT = "cih";
			url = url + END_POINT + "/login?";

		} else if (swift.equals("sgma")) {
			END_POINT = "sgma";
			url = url + END_POINT + "/login?";
		}

		ResponseEntity<RegistrationResponse> loggedInUser = restTemplate.exchange(
				url + "email={email}&password={password}", HttpMethod.POST, requestEntity, RegistrationResponse.class,
				email, password);

		if (loggedInUser.getStatusCode() == HttpStatus.OK) {

			return ResponseHandler.generateResponseWithToken("Login Successful", HttpStatus.OK,
					loggedInUser.getBody().getData(), loggedInUser.getBody().getToken());

		} else
			return ResponseHandler.generateResponseString("Login Failed", HttpStatus.NOT_ACCEPTABLE);

	}

	public ResponseEntity<Object> addFcmToken(String swift, String fcmToken, String email) {
		String END_POINT = null;
		String url = "http://3.217.215.70:8081/";
		if (swift.equals("attijari")) {
			END_POINT = "attijari";
			url = url + END_POINT + "/registration/fcm_token?";

		} else if (swift.equals("cih")) {
			END_POINT = "cih";
			url = url + END_POINT + "/registration/fcm_token?";

		} else if (swift.equals("sgma")) {
			END_POINT = "sgma";
			url = url + END_POINT + "/registration/fcm_token?";
		}
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(requestHeaders);

		ResponseEntity<String> sendFcmToken = restTemplate.exchange(url + "user_email={email}&device_token={fcmToken}",
				HttpMethod.POST, requestEntity, String.class, email, fcmToken);

		if (sendFcmToken.getStatusCode() == HttpStatus.OK) {

			return ResponseHandler.generateResponse("Fcm Token Saved Successful", HttpStatus.OK,
					sendFcmToken.getBody());

		} else
			return ResponseHandler.generateResponseString("Fcm token Failed", HttpStatus.NOT_ACCEPTABLE);
	}

}
