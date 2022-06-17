package stg.hpsswitch.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import stg.hpsswitch.registration.request.RegistrationRequest;
import stg.hpsswitch.registration.response.Data;
import stg.hpsswitch.registration.response.ListRegistrationResponse;
import stg.hpsswitch.registration.response.NullResponse;
import stg.hpsswitch.registration.response.RegistrationResponse;
import stg.hpsswitch.responseHandler.ResponseHandler;

@Service
public class LoginService {

	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<Object> login(String swift, String email, String password) {
		String url = "http://3.217.215.70:8081/";
		String END_POINT = null;
		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap();
		bodyMap.add("email", email);
		bodyMap.add("password", password);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, requestHeaders);
		if (swift.equals("attijari")) {
			END_POINT = "attijari";
			url = url + END_POINT + "/login";

		} else if (swift.equals("cih")) {
			END_POINT = "cih";
			url = url + END_POINT + "/login";

		} else if (swift.equals("sgma")) {
			END_POINT = "sgma";
			url = url + END_POINT + "/login";
		}

		ResponseEntity<RegistrationResponse> loggedInUser = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				RegistrationResponse.class);
		HttpStatus http;
		if (loggedInUser.getBody().getStatus() == 200) {
			http = HttpStatus.OK;
		} else {
			http = HttpStatus.NOT_ACCEPTABLE;
		}

		if (loggedInUser.getBody().getData() != null) {

			return ResponseHandler.generateResponseWithToken(loggedInUser.getBody().getMessage(), http,
					loggedInUser.getBody().getData(), loggedInUser.getBody().getToken());

		} else
			return ResponseHandler.generateResponseString(loggedInUser.getBody().getMessage(), http);

	}

	public ResponseEntity<Object> addFcmToken(String swift, String fcmToken, String email) {
		String END_POINT = null;
		String url = "http://3.217.215.70:8081/";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(requestHeaders);
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
		HttpStatus http;

		ResponseEntity<NullResponse> sendFcmToken = restTemplate.exchange(url + "user_email={email}&device_token={fcmToken}",
				HttpMethod.POST, requestEntity, NullResponse.class, email, fcmToken);

		if (sendFcmToken.getBody().getStatus().equals("OK")) {
			http = HttpStatus.OK;
			return ResponseHandler.generateResponseString("Fcm Token Saved Successful", http);
		} else {
			http = HttpStatus.NOT_ACCEPTABLE;
			return ResponseHandler.generateResponseString("Fcm token Failed", http);
		}

	}

	public ResponseEntity<Object> getAllUsers() {

		List<String> END_POINT = Arrays.asList("attijari", "cih", "sgma");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(requestHeaders);
		List<Data> allUsers = new ArrayList<>();
		END_POINT.forEach(b -> {
			String url = "http://3.217.215.70:8081/" + b + "/registration/allUsers";
			ResponseEntity<ListRegistrationResponse> banqueUsers = restTemplate.exchange(url, HttpMethod.GET,
					requestEntity, ListRegistrationResponse.class);

			banqueUsers.getBody().getData().forEach(user -> {
				allUsers.add(user);
			});

		});
		if (!allUsers.isEmpty()) {
			return ResponseHandler.generateResponse("get all Users Successfull", HttpStatus.OK, allUsers);
		} else
			return ResponseHandler.generateResponseString("get all Users Failed", HttpStatus.NOT_ACCEPTABLE);

	}

	public ResponseEntity<Object> loadUserByemail(String email, String swift) {
		String END_POINT = null;
		String url = "http://3.217.215.70:8081/";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(requestHeaders);

		if (swift.equals("attijari")) {
			END_POINT = "attijari";
			url = url + END_POINT + "/registration/user?";

		} else if (swift.equals("cih")) {
			END_POINT = "cih";
			url = url + END_POINT + "/registration/user?";

		} else if (swift.equals("sgma")) {
			END_POINT = "sgma";
			url = url + END_POINT + "/registration/user?";
		}
		ResponseEntity<RegistrationResponse> loadUserByemail = restTemplate.exchange(
				url + "email={email}&swift={swift}", HttpMethod.GET, requestEntity, RegistrationResponse.class, email,
				swift);
		if (loadUserByemail.getBody().getData() != null) {
			return ResponseHandler.generateResponse("successfull", HttpStatus.OK, loadUserByemail.getBody().getData());
		} else
			return ResponseHandler.generateResponseString("Failed", HttpStatus.NOT_ACCEPTABLE);
	}

	public ResponseEntity<Object> removeFcmToken(String swift, String email) {

		String END_POINT = null;
		String url = "http://3.217.215.70:8081/";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(requestHeaders);

		if (swift.equals("attijari")) {
			END_POINT = "attijari";
			url = url + END_POINT + "/registration/remove_fcm_token?";

		} else if (swift.equals("cih")) {
			END_POINT = "cih";
			url = url + END_POINT + "/registration/remove_fcm_token?";

		} else if (swift.equals("sgma")) {
			END_POINT = "sgma";
			url = url + END_POINT + "/registration/remove_fcm_token?";
		}
		ResponseEntity<String> removeToken = restTemplate.exchange(url + "user_email={email}", HttpMethod.POST,
				requestEntity, String.class, email);
		if (removeToken.getBody().contains("200")) {
			return ResponseHandler.generateResponse("successfull", HttpStatus.OK, removeToken.getBody());
		} else
			return ResponseHandler.generateResponseString("Failed remove Token", HttpStatus.OK);

	}

}
