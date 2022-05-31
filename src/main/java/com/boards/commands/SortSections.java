package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;

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
    public InsertOneResult  execute() {

        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.SECTION.get());


            if(order=="asc") {
                taskCollection.find().sort(Sorts.ascending(sort));
            }
            else{
                taskCollection.find().sort(Sorts.descending(sort));
            }
            return null;

//       todolistCollection.find(filter).forEach(doc -> System.out.println(doc));


    }
}
