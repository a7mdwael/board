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

public class CreateSubtaskInSection implements Command{

    String subtask_name;
    ObjectId task_ID;

    public CreateSubtaskInSection(String subtaskName, String taskId) {
        this.subtask_name = subtaskName;
        this.task_ID = new ObjectId(taskId);
    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection subTaskCollection = db.dbInit(CollectionNames.SUBTASK.get());

        //Inserting task
        Document subtask = new Document("name", this.subtask_name);
        InsertOneResult result = subTaskCollection.insertOne(subtask);

        //Inserting task in todolist
        Bson filter = Filters.eq("_id", this.task_ID);
        Bson update = Updates.push("subtasks", result.getInsertedId().asObjectId());
        taskCollection.findOneAndUpdate(filter, update);
        return result;  
    }

}
