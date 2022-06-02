package com.boards.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AddCollaborator implements Command {
    String user_ID;
    ObjectId board_ID;

    public AddCollaborator(String string, String boardID) {
        this.user_ID = string;
        this.board_ID = new ObjectId(boardID);
    }

    public String  execute() {
        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());


        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.push("collaborators", this.user_ID);
        Document result = (Document) boardCollection.findOneAndUpdate(filter, update);
        return result.toJson();
    }
}
