package org.saga.sender;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.RestController;

//@RestController
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageSender messageSender = new MessageSender();

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam int userId) {
        messageSender.sendMessage(userId);
        return ResponseEntity.ok("Message sent for userId: " + userId);
    }
}