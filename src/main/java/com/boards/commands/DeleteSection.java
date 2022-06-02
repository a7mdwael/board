package com.boards.commands;

import java.util.Collections;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DeleteSection implements Command {

    ObjectId board_ID;
    ObjectId section_ID;

    public DeleteSection(String boardID, String sectionID) {
        this.board_ID = new ObjectId(boardID);
        this.section_ID = new ObjectId(sectionID);
    }

    public DeleteResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection boardCollection = db.dbInit(CollectionNames.BOARD.get());
        MongoCollection sectionCollection = db.dbInit(CollectionNames.SECTION.get());

        Bson query = Filters.eq("_id", this.section_ID);
        DeleteResult result = sectionCollection.deleteOne(query);
        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.pull("sections", this.section_ID);
        boardCollection.findOneAndUpdate(filter, update);
        return result;
    }

}
