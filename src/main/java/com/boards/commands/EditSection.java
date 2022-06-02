package com.boards.commands;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date; 
public class EditSection implements Command {
    String sectionName;
    ObjectId section_ID;
 
    public EditSection(String sectionID, String sectionName) {
        this.sectionName = sectionName;
        this.section_ID = new ObjectId(sectionID);

    }

    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection sectionCollection = db.dbInit(CollectionNames.SECTION.get());

        // Inserting task in todolist
        Bson filter = Filters.eq("_id", this.section_ID);

        // handel if it is null

        if (sectionName != null) {
            Bson update1 = Updates.set("name", sectionName);
            sectionCollection.updateOne(filter, update1);

        }



        Document result = (Document) sectionCollection.find(filter);
        return result.toJson();

    }
    
}
