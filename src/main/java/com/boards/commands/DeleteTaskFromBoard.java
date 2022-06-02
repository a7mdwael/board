package com.boards.commands;

import java.util.Collections;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DeleteTaskFromBoard implements Command {

    ObjectId task_ID;
    ObjectId board_ID;

    public DeleteTaskFromBoard(String taskID, String boardID) {
        this.task_ID = new ObjectId(taskID);
        this.board_ID = new ObjectId(boardID);
    }

    public DeleteResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection sectionCollection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        Bson query = Filters.eq("_id", this.task_ID);
        DeleteResult result = taskCollection.deleteOne(query);

        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.pull("tasks", this.task_ID);
       sectionCollection.findOneAndUpdate(filter, update);

        return result;
    }

}
