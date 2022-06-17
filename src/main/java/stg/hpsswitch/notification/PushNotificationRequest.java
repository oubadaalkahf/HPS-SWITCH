package stg.hpsswitch.notification;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor @NoArgsConstructor
public class PushNotificationRequest {

	private String title;
	private String message;
	private String topic;
	private List<String> token = new ArrayList<>();

}


