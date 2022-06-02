package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;

import java.util.ArrayList;

public class CreateBoard implements Command {
    String board_name;
    String user_Id;
    ArrayList <String> creators;

    public CreateBoard(String boardName, String userId) {
        this.board_name = boardName;
        this.user_Id = userId;
        this.creators = new ArrayList<>();
        this.creators.add(userId);
    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection =  db.dbInit(CollectionNames.BOARD.get());
        Document board = new Document("name", this.board_name).append("creator", this.user_Id).append("collaborators", this.creators);
        InsertOneResult  result = collection.insertOne(board);
        return result; 
    
    }
}
