package stg.hpsswitch.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import stg.hpsswitch.login.LoginRequest;
import stg.hpsswitch.login.LoginService;
import stg.hpsswitch.notification.NotificationService;
import stg.hpsswitch.notification.PushNotificationRequest;
import stg.hpsswitch.registration.request.RegistrationRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/")
@CrossOrigin("*")
public class RegistrationController {

	private final RegistrationService registrationService;
	private final LoginService loginService;
	private final NotificationService notificationService;

	@PostMapping("registration")
	public ResponseEntity<Object> registrationRequest(@RequestParam("swift") String swift,
			@RequestBody RegistrationRequest registrationRequest) {

		return registrationService.register(swift, registrationRequest);

	}

	@PostMapping("login")
	public ResponseEntity<Object> loginRequest(@RequestParam("swift") String swift, @RequestBody LoginRequest loginReq) {

		return loginService.login(swift,loginReq.getEmail(),loginReq.getPassword());

	}

	@PostMapping(path = "fcm_token")
	public ResponseEntity<Object> fcm(@RequestParam("swift") String swift,
			@RequestBody AddFcmTokenRequest request) {

		return loginService.addFcmToken(swift, request.getFcmToken(), request.getEmail());
	}
	
	@PostMapping(path = "remove_fcm_token")
	public ResponseEntity<Object> removefcm(@RequestParam("swift") String swift,
		@RequestParam("user_email") String email) {

		return loginService.removeFcmToken(swift, email);
	}
	

	@GetMapping("users")
	public ResponseEntity<Object> getAllUsers() {

		return loginService.getAllUsers();
	}
	
	@GetMapping("user")
	public ResponseEntity<Object> loadUserByEmaill(
			@RequestParam("email") String email,
			@RequestParam("swift") String swift
			) {
		return loginService.loadUserByemail(email,swift);
	}
	
	@PostMapping("notification")
	public ResponseEntity<Object> sendNotification(
			@RequestBody PushNotificationRequest request
			
			) {
		return notificationService.sendNotification(request);
	}
	
	
	
	

}
