package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;

import org.bson.types.ObjectId;

public class SortTasks implements Command {
    String sort;
    String order;
    ObjectId sectionID;


    public SortTasks(String sectionID, String sort, String order) {
        this.sort = sort;
        this.sectionID = new ObjectId(sectionID);

        this.order=order;


    }




    @Override
    public InsertOneResult  execute() {

        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


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
