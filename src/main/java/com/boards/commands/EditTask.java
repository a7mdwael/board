package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date;

public class EditTask implements Command {

    String taskName;
    ObjectId task_ID;
    String priority;
    Date due_date;
    Boolean done;

    public EditTask(String taskID, String taskName, String priority, Date dueDate, Boolean done) {
        this.taskName = taskName;
        this.task_ID = new ObjectId(taskID);
        this.done = done;
        this.due_date = dueDate;
        this.priority = priority;
    }

    @Override
    public InsertOneResult execute() {

        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        // Inserting task in todolist
        Bson filter = Filters.eq("_id", this.task_ID);

        // handel if it is null

        if (taskName != null) {
            Bson update1 = Updates.set("name", taskName);
            taskCollection.updateOne(filter, update1);

        }

        if (done != null) {
            Bson update2 = Updates.set("done", done);
            taskCollection.updateOne(filter, update2);
        }
        if (due_date != null) {
            Bson update3 = Updates.set("due_date", due_date);
            taskCollection.updateOne(filter, update3);
        }
        if (priority != null) {
            Bson update4 = Updates.set("priority", priority);
            taskCollection.updateOne(filter, update4);
        }

        // Updates.combine(),

        return null;

    }
}
