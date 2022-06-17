package stg.hpsswitch.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFcmTokenRequest {
String email;
String fcmToken;
}
