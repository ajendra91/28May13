package com.example.Tiwillio;

import java.net.URI;

import com.twilio.rest.api.v2010.account.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.voice.*;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.type.PhoneNumber;

@SpringBootApplication
@RestController
public class Application {

	private final static String ACCOUNT_SID = "AC31aa5f4914352593e3d62413798a2329";
	private final static String AUTH_ID = "56b60c6008bc05bcb3386a076a22c975";


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		Twilio.init(ACCOUNT_SID, AUTH_ID);

		Message.creator(new PhoneNumber("+919354187162"),new PhoneNumber("+15052162315"), "ajendra is here").create();

		/*Twilio.init(AUTH_SID, AUTH_TOKEN);
		        Message message = Message.creator(
		                new com.twilio.type.PhoneNumber("whatsapp:+919354187162"),
		                new com.twilio.type.PhoneNumber("whatsapp:+15052162315"),
		                "Hello there!")
		            .create();

		        System.out.println(message.getSid());*/

		Say say = new Say.Builder("Hello World").build();
		VoiceResponse response = new VoiceResponse.Builder().say(say).build();

		try {
			System.out.println(response.toXml());
		} catch (TwiMLException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value="/voice-note", method=RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> getVoiceNote() throws TwiMLException {
		String otp = "12345";
		Say say = new Say.Builder("Your OTP is: "+otp).voice(Say.Voice.WOMAN).build();
		VoiceResponse response = new VoiceResponse.Builder().say(say).build();
		return new ResponseEntity<>(response.toXml(), HttpStatus.OK);
	}

	@RequestMapping(value="/calls", method=RequestMethod.GET)
	public ResponseEntity<Object> makeCall() {

		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_ID).build();
		PhoneNumber to_number = new PhoneNumber("+919354187162");
		PhoneNumber from_number = new PhoneNumber("+15052162315");
		URI uri = URI.create("https://talk2amar-projectid.appspot.com/voice-note?otp=345678");
		Call call = Call.creator(to_number, from_number, uri).create(client);
		return new ResponseEntity<Object>("Call has initiated successfully and call SID is:"+call.getSid(), HttpStatus.OK);
	}

	@RequestMapping(value="/calls2", method=RequestMethod.GET)
	public void makeCall2() {

		Twilio.init(ACCOUNT_SID, AUTH_ID);
		Call call = Call.creator(
				new com.twilio.type.PhoneNumber("+919354187162"),
				new com.twilio.type.PhoneNumber("+15052162315"),
				URI.create("http://demo.twilio.com/docs/voice.xml"))
				.create();
		System.out.println(call.getSid());
	}
}