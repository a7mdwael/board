package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SortSections implements Command {
    String sort;
    ObjectId SectionID;
    String order;


    public SortSections(String SectionID, String sort, String order) {
        this.sort = sort;
        this.SectionID = new ObjectId(SectionID);
        this.order=order;


    }




    @Override
    public String  execute() {

        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.SECTION.get());


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
