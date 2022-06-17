package stg.hpsswitch.notification;

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
public class NotificationService {

	public ResponseEntity<Object> sendNotification(PushNotificationRequest request) {
		String url = "http://3.217.215.70:8081/notification-sender/notification/tokens";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PushNotificationRequest> requestEntity =
				new HttpEntity<>(request, requestHeaders);
		
		ResponseEntity<String> sendNotification = restTemplate.exchange(url, HttpMethod.POST,
				requestEntity,String.class);
		if (sendNotification.getStatusCode() == HttpStatus.OK) {
			return ResponseHandler.generateResponseString("Notification Sent", HttpStatus.OK);

		} else
			return ResponseHandler.generateResponseString("notification Not sent", HttpStatus.NOT_ACCEPTABLE);

	}

}
