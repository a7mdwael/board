package com.boards.Controller;

import com.boards.commands.CreateBoard;
import com.boards.commands.CreateSubtaskInSection;
import com.boards.commands.CreateTaskInSection;
import com.boards.commands.DeleteTaskFromSection;
import com.boards.config.Helpers;
import com.boards.config.RMQConfig.RabbitMQConfig;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import org.json.simple.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
public class Controller {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.RPC_MESSAGE_QUEUE)
    public void listener(Message message) {
        byte[] body = message.getBody();
        Helpers helpers = new Helpers();
        JSONObject jsonObject = helpers.parseToJson(new String(body));
        switch (message.getMessageProperties().getMessageId()){
            case "create-todolist": {
                CreateBoard createTodolist = new CreateBoard((String) jsonObject.get("name"), (String) jsonObject.get("user_id"));
                InsertOneResult result = createTodolist.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            
            case "create-task": {
                CreateTaskInSection createTask = new CreateTaskInSection((String) jsonObject.get("name"), (String) jsonObject.get("section_id"));
                InsertOneResult result = createTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "create-subtask": {
                CreateSubtaskInSection createSubtask = new CreateSubtaskInSection((String) jsonObject.get("name"), (String) jsonObject.get("task_id"));
                InsertOneResult result = createSubtask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }

            case "delete-task": {
                DeleteTaskFromSection deleteTask = new DeleteTaskFromSection((String) jsonObject.get("task_id"), (String) jsonObject.get("todolist_id"));
                DeleteResult result = deleteTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Task was deleted successfully").getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            default:
                break;
        }
    }
}
