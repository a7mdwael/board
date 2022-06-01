package com.boards.commands;

import java.sql.Date;
import java.util.ArrayList;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
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
        // CreateBoard CreateTask = new CreateBoard("board22","123123");
        // InsertOneResult result1 = CreateTask.execute();

        // CreateTaskInSection CreateTask = new CreateTaskInSection("our first task",
        // "6297a61af000916519b07da8");
        // InsertOneResult result = CreateTask.execute();

        // CreateSection CreateTask = new CreateSection("section3","1234",
        // "6297a1a561279967af4413dc");
        // InsertOneResult result = CreateTask.execute();

    //    Date x =new Date(2022,12,2);
    //     EditTask CreateTask = new EditTask("6297a6a805881c2ccfd617c4","not first task","", x,true);
    //     InsertOneResult result1 = CreateTask.execute();

        // TaskSearch serachboard = new TaskSearch("our first task");
        // FindIterable result = serachboard.execute();
        // for (Object object : result) {
        // System.out.println(object.toString());
        // }
    
        DeadlineTask serachboard = new DeadlineTask();
        FindIterable result = serachboard.execute();
        for (Object object : result) {
        System.out.println(object.toString());
        }
    }
}
