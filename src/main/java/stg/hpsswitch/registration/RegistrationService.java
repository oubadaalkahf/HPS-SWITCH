package stg.hpsswitch.registration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import stg.hpsswitch.registration.request.RegistrationRequest;
import stg.hpsswitch.registration.response.RegistrationResponse;
import stg.hpsswitch.responseHandler.ResponseHandler;

@Service
public class RegistrationService {

	public ResponseEntity<Object> register(String swift, RegistrationRequest registrationRequest) {
		String url = "http://3.217.215.70:8081/";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegistrationRequest> requestEntity =
				new HttpEntity<>(registrationRequest, requestHeaders);

		String END_POINT = null;

		if (swift.equals("attijari")) {
			END_POINT = "attijari";
			url = url + END_POINT + "/registration";

		} else if (swift.equals("cih")) {
			END_POINT = "cih";
			url = url + END_POINT + "/registration";

		} else if (swift.equals("sgma")) {
			END_POINT = "sgma";
			url = url + END_POINT + "/registration";
		}
		ResponseEntity<RegistrationResponse> registratedUser = restTemplate.exchange(url, HttpMethod.POST,
				requestEntity, RegistrationResponse.class);
		if (registratedUser.getStatusCode() == HttpStatus.OK) {
			return ResponseHandler.generateResponse("Registrated", HttpStatus.OK, registratedUser.getBody().getData());

		} else
			return ResponseHandler.generateResponseString("not Registrated", HttpStatus.NOT_ACCEPTABLE);
	}
}
