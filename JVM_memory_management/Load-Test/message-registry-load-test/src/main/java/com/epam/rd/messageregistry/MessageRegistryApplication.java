package com.epam.rd.messageregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class MessageRegistryApplication {

    private List<MessageRecord> messages = new ArrayList<>();
    private final Object messagesLock = new Object();


    public static void main(String[] args) {
        SpringApplication.run(MessageRegistryApplication.class, args);

    }

    @PostMapping("/message")
    public void postMessage(@RequestBody String message) {
        synchronized (messagesLock) {
            messages.add(new MessageRecord(message));
        }
    }

    @GetMapping("/last10")
    public List<MessageRecord> last10Messages() {
        synchronized (messagesLock){
            int from = Math.max(messages.size() - 10, 0);
            int to = messages.size();
            return messages.subList(from, to);
        }
    }

}

class MessageRecord {
    private final String message;
    private final long msgTimeMillis;

    public MessageRecord(final String message) {
        this.message = message;
        msgTimeMillis = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public long getMsgTimeMillis() {
        return msgTimeMillis;
    }
}
