package com.todoist.server.controller;

import com.todoist.server.config.List.*;
import com.todoist.server.config.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/todolist")
public class ListController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/create")
    public Map createTodolist(@RequestBody String body){
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-todolist");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("task/create")
    public Map createTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("subtask/create")
    public Map createSubtask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-subtask");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("task/assign")
    public Map assignTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("assign-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("task/edit")
    public Map editTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("edit-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("/search")
    public Map searchList(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("search-todolist");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("task/search")
    public Map searchTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("search-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("task/sort")
    public Map sortTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("sort-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("task/deadline")
    public Map deadlineTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("task-deadline");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @DeleteMapping("task/delete")
    public Map deleteTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("delete-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("comments/add")
    public Map addComment(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-comment");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("collaborators/add")
    public Map addCollaborator(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-collaborator");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }
    
    @PostMapping("collaborators/add")
    public Map addCollaborator(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-collaborator");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }
}