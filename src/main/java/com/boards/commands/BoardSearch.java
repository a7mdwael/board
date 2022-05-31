package com.boards.commands;
import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;

import org.bson.conversions.Bson;

public class BoardSearch implements Command{

    String board_name;


    public BoardSearch(String boardName) {
        this.board_name = boardName;

    }




    @Override
    public InsertOneResult  execute() {

        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());


        //Inserting task in todolist
        Bson filter = Filters.eq("name", this.board_name);
        Bson projection = Projections.include("name");
        return null;

    }


}

