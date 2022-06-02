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

public class CreateTaskInSection implements Command {
    String task_name;
    ObjectId section_ID;

    public CreateTaskInSection(String taskName, String sectionID) {
        this.task_name = taskName;
        this.section_ID = new ObjectId(sectionID);
    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection sectionCollection =  db.dbInit(CollectionNames.SECTION.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        //Inserting task
        Document task = new Document("name", this.task_name);
        InsertOneResult res = taskCollection.insertOne(task);

        //Inserting task in board
        Bson filter = Filters.eq("_id", this.section_ID);
        Bson update = Updates.push("tasks", res.getInsertedId().asObjectId());
       sectionCollection.findOneAndUpdate(filter, update);
        return res;
    }
}
