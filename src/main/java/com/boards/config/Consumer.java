// package com.boards.config;

// import com.rabbitmq.client.*;
// import com.boards.commands.*;

// import org.bson.types.Binary;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.util.Date;
// import java.util.concurrent.TimeoutException;

// public class Consumer {

//     public Consumer() {
//     }

//     public void consume() throws IOException, TimeoutException {
//         ConnectionFactory factory =  new ConnectionFactory();
//         factory.setUsername("guest");
//         factory.setPassword("guest");
//         Connection connection = factory.newConnection();
//         Channel channel = connection.createChannel();
//         channel.queueDeclare("create-board",false, false, false, null);
//         channel.queueDeclare("create-task",false, false, false, null);
//         channel.queueDeclare("create-subtask",false, false, false, null);
//         channel.queueDeclare("edit-task",false, false, false, null);
//         channel.queueDeclare("search-board",false, false, false, null);
//         channel.queueDeclare("delete-task",false, false, false, null);
//         channel.queueDeclare("add-comment-task",false, false, false, null);
//         channel.queueDeclare("add-comment-board",false, false, false, null);
//         channel.queueDeclare("upload-media",false, false, false, null);
//         channel.queueDeclare("add-collaborator",false, false, false, null);

//         channel.basicConsume("create-board", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             CreateBoard createBoard = new CreateBoard((String) jsonObject.get("board_name"), (String) jsonObject.get("user_id") );
//             createBoard.execute();
//          }, consumerTag -> {

//         });

//         channel.basicConsume("create-task", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             CreateTaskInSection createTask = new CreateTaskInSection((String) jsonObject.get("task_name"), (String) jsonObject.get("board_id"));
//             createTask.execute();
//         }, consumerTag -> {

//         });


//         channel.basicConsume("create-subtask", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             CreateSubtaskInSection createSubtask = new CreateSubtaskInSection((String) jsonObject.get("task_name"), (String) jsonObject.get("task_id"));
//             createSubtask.execute();
//         }, consumerTag -> {

//         });


//         channel.basicConsume("edit-task", false, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             EditTask editTask =new EditTask((String) jsonObject.get("task_id"),
//             (String) jsonObject.get("task_name"),(String) jsonObject.get("priority"),
//             (Date) jsonObject.get("due_date"),
//              (Boolean) jsonObject.get("done"));

//             editTask.execute();
//         }, consumerTag -> {

//         });


//         channel.basicConsume("search-task", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             BoardSearch boardSearch = new BoardSearch((String) jsonObject.get("board_name"));
//             boardSearch.execute();
//         }, consumerTag -> {

//         });



//         channel.basicConsume("delete-task", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             DeleteTaskFromSection deleteTask = new DeleteTaskFromSection((String) jsonObject.get("task_id"), (String) jsonObject.get("board_id"));
//             deleteTask.execute();
//         }, consumerTag -> {

//         });

//         channel.basicConsume("add-comment-board", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             AddCommentBoard addCommentBoard = new AddCommentBoard((String) jsonObject.get("content"), (String) jsonObject.get("board_id"));
//             addCommentBoard.execute();
//         }, consumerTag -> {

//         });

//         channel.basicConsume("add-comment-task", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             AddCommentTaskSection addCommentTask = new AddCommentTaskSection((String) jsonObject.get("content"), (String) jsonObject.get("task_id"));
//             addCommentTask.execute();
//         }, consumerTag -> {

//         });

//         channel.basicConsume("upload-media", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             UploadMedia uploadMedia = new UploadMedia((MultipartFile) jsonObject.get("content"), (String) jsonObject.get("board_id"));
//             uploadMedia.execute();
//         }, consumerTag -> {

//         });


//         channel.basicConsume("add-collaborator", true, (consumerTag, message) -> {
//             String s = new String(message.getBody(), "UTF-8");
//             Helpers helpers = new Helpers();
//             JSONObject jsonObject = helpers.parseToJson(s);
//             AddCollaborator addCollaborator = new AddCollaborator((String) jsonObject.get("user_id"), (String) jsonObject.get("board_id"));
//             addCollaborator.execute();
//         }, consumerTag -> {

//         });


 



//     }


// }
