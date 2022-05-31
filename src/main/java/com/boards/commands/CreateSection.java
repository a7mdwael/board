package com.boards.commands;

import java.util.ArrayList;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;

public class CreateSection implements Command {
    String section_name;
    String user_Id;
    String board_Id;

    public CreateSection(String sectionName, String userId, String boardId) {
        this.section_name = sectionName;
        this.user_Id = userId;
        this.board_Id = boardId;

    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection = db.dbInit(CollectionNames.SECTION.get());
        Document section = new Document("name", this.section_name).append("creator", this.user_Id)
                .append("board_id", this.board_Id);
        InsertOneResult result =  collection.insertOne(section);
        return result;
    }
}
