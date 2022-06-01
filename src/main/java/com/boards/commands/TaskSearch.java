package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;

import org.bson.conversions.Bson;

public class TaskSearch implements Command{
    String task_name;

    public TaskSearch(String taskName) {
        this.task_name = taskName;

    }

    @Override
    public FindIterable execute() {

        MongoDB db = new MongoDB();
        MongoCollection boardCollection = db.dbInit(CollectionNames.TASK.get());

        // Inserting task in todolist
        Bson filter = Filters.eq("name", this.task_name);
        // Bson projection = Projections.include("name");
        // System.out.println("Projections: "+filter);
        FindIterable result = boardCollection.find(filter);
        return result;

    }
}
