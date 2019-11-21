package co.com.openintl.bank.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class MessageConsumer {
	
	private final static Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	
	@JmsListener(destination ="retiros" )
	public void listener(String message) {
		log.info("Message: {}", message);
		
	}
}
