package com.boards.commands;

import java.util.Collections;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DeleteTaskFromSection implements Command {

    ObjectId task_ID;
    ObjectId section_ID;

    public DeleteTaskFromSection(String taskID, String sectionID) {
        this.task_ID = new ObjectId(taskID);
        this.section_ID = new ObjectId(sectionID);
    }

    public DeleteResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection sectionCollection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        Bson query = Filters.eq("_id", this.task_ID);
        DeleteResult result = taskCollection.deleteOne(query);

        // //Delete Task
        // sectionCollection.deleteOne(Filters.eq("task_id", task_ID));
        // taskCollection.deleteOne(Filters.eq("task_id", task_ID));

        Bson filter = Filters.eq("_id", this.section_ID);
        Bson update = Updates.pull("tasks", this.task_ID);
        sectionCollection.findOneAndUpdate(filter, update);
        // Deleting task from board
        // Bson filter = Filters.eq("section_id", this.section_ID);
        // Bson update = Updates.pull("tasks", this.task_ID);
        // sectionCollection.findOneAndUpdate(filter, update);
        return result;
    }

}
