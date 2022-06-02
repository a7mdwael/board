package com.boards.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AssignTask implements Command{

    String assignee;
    ObjectId taskID;

    public AssignTask(String taskID, String assignee) {
        this.taskID = new ObjectId(taskID);
        this.assignee = assignee;
    }


    public String execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());
        Bson filter = Filters.eq("_id", taskID);
        Bson update = Updates.push("assignee", assignee);
        Document result = (Document) taskCollection.findOneAndUpdate(filter, update);
        return result.toJson();
    }
}