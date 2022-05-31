package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AddCommentBoard implements Command{

    String content;
    ObjectId boardID;

    public AddCommentBoard(String comment, String boardID) {
        this.content = comment;
        this.boardID = new ObjectId(boardID);

    }

    public InsertOneResult  execute() {
        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.COMMENT.get());


        Document comment = new Document("content", this.content);
        InsertOneResult result = commentCollection.insertOne(comment);


        Bson filter = Filters.eq("_id", this.boardID);

        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        boardCollection.findOneAndUpdate(filter, update);
        return result;
    }

}