package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.FindIterable;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class SortTasks implements Command {
    String sort;
    String order;
    ObjectId sectionID;

    public SortTasks(String sectionID, String sort, String order) {
        this.sort = sort;
        this.sectionID = new ObjectId(sectionID);

        this.order = order;

    }

    @Override
    public InsertOneResult execute() {

        MongoDB db = new MongoDB();
        MongoCollection SectionCollection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection TaskCollection = db.dbInit(CollectionNames.TASK.get());
        Bson filter = Filters.eq("_id", this.sectionID);

        if (order.equals("asec")) {
        FindIterable sortedTasks = TaskCollection.find().sort(Sorts.ascending(sort));
        } else {
        FindIterable sortedTasks = TaskCollection.find().sort(Sorts.descending(sort));
        }
    //    foreach(x in sortedTasks){

    //    }

        return null;

        // todolistCollection.find(filter).forEach(doc -> System.out.println(doc));

    }
}
