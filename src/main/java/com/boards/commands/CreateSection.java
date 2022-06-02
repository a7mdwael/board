package com.boards.commands;

import java.sql.Date;
import java.util.ArrayList;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.time.LocalDate;

public class CreateSection implements Command {
    String section_name;
    String user_Id;
    ObjectId board_Id;

    public CreateSection(String sectionName, String userId, String boardId) {
        this.section_name = sectionName;
        this.user_Id = userId;
        this.board_Id = new ObjectId(boardId);

    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection boardCollection = db.dbInit(CollectionNames.BOARD.get());

        Document section = new Document("name", this.section_name).append("creator", this.user_Id)
                .append("board_id", this.board_Id);
        InsertOneResult result = collection.insertOne(section);

        Bson filter = Filters.eq("_id", this.board_Id);
        Bson update = Updates.push("sections", result.getInsertedId().asObjectId());
        boardCollection.findOneAndUpdate(filter, update);

        return result;
    }

    public static void main(String[] args) {
        // CreateBoard CreateTask = new CreateBoard("board23","123123");
        // InsertOneResult result1 = CreateTask.execute();

        // CreateTaskInSection CreateTask = new CreateTaskInSection("first task",
        // "6298e3483fd76c57fb247f01");
        // InsertOneResult result = CreateTask.execute();


        // CreateSection CreateTask = new CreateSection("section3","1234",
        // "6298e3125a557606d6e20099");
        // InsertOneResult result = CreateTask.execute();
    //    Date x =new Date(2022,12,2);
    //     EditTask CreateTask = new EditTask("6297a6a805881c2ccfd617c4","not first task","", x,true);
    //     InsertOneResult result1 = CreateTask.execute();

        // TaskSearch serachboard = new TaskSearch("first task");
        // FindIterable result = serachboard.execute();
        // for (Object object : result) {
        // System.out.println(object.toString());
        // }
    
        
        // DeadlineTask serachboard = new DeadlineTask();
        // FindIterable result = serachboard.execute();
        // for (Object object : result) {
        // System.out.println(object.toString());
        // }

        
        // AssignTask CreateTask1 = new AssignTask("6298d27fde27fb73125cc03a","salma");
        // Object result1 = CreateTask1.execute();

        DeleteTaskFromBoard CreateTask1 = new DeleteTaskFromBoard("6298e07975352a72f726f72d","6297a1a561279967af4413dc");
        Object result1 = CreateTask1.execute();

        // SortTasks SortTasks = new SortTasks("6297a1a561279967af4413dc","6297a8676cbac86cf70767c3");
        // InsertOneResult result2 = SortTasks.execute();

     //   UploadMedia upload = new UploadMedia("index.jpg", "6297a1a561279967af4413dc")

    }
}
