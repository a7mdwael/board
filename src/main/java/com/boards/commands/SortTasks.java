package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.FindIterable;

import com.mongodb.client.model.Filters;

import org.bson.Document;
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
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection sectionCOllection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());
        Bson filter = Filters.eq("_id", this.sectionID);

        Document result = (Document) taskCollection.find();

        if(order=="asc") {
            result = (Document) taskCollection.find().sort(Sorts.ascending(sort));
        }

    if(order=="desc") {
       result = (Document) taskCollection.find().sort(Sorts.descending(sort));
        }


    return result.toJson();

        
    }
}
