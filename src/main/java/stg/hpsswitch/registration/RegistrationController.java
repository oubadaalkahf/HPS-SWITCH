package stg.hpsswitch.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

import stg.hpsswitch.login.LoginService;
import stg.hpsswitch.registration.request.RegistrationRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class RegistrationController {

	private final RegistrationService registrationService;
	private final LoginService loginService;

	@PostMapping("registration")
	public ResponseEntity<Object> registrationRequest(@RequestParam("swift") String swift,
			@RequestBody RegistrationRequest registrationRequest) {

		return registrationService.register(swift, registrationRequest);

	}

	@PostMapping("login")
	public ResponseEntity<Object> loginRequest(@RequestParam("swift") String swift, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		return loginService.login(swift, email, password);

	}
	
	@PostMapping(path = "fcm_token")
	public ResponseEntity<Object> fcm(
			@RequestParam("swift") String swift,
			@RequestParam("device_token") String fcmToken,
			@RequestParam("user_email") String email) {
		
		
		return loginService.addFcmToken(swift,fcmToken,email);
		

	}

}
