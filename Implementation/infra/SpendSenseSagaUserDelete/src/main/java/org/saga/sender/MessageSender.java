package org.saga.sender;

import com.azure.messaging.servicebus.*;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class MessageSender
{
    private static final String CONNECTION_STRING = "Endpoint=sb://spendsense-service-bus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=LzYR9PAa13NXl3F7EPq97P5HUHzRO9LlM+ASbDSbm0k=";
    private static final String QUEUE_NAME = "spendsense-queue";

    public void sendMessage(int userId) {

        System.out.println("Sending Message");

        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(CONNECTION_STRING)
                .sender()
                .queueName(QUEUE_NAME)
                .buildClient();

        Map<String, Integer> user = new HashMap<>();
        Gson gson = new Gson();

        user.put("userId", userId);

        try {
            String messageBody = gson.toJson(user);
            System.out.println("Message: " + messageBody);
            ServiceBusMessage message = new ServiceBusMessage(messageBody);
            senderClient.sendMessage(message);
            System.out.println("Message Sent");
        } finally {
            senderClient.close();
        }

    }
}
