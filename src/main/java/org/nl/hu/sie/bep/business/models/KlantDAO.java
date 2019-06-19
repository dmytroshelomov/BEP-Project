package org.nl.hu.sie.bep.business.models;

import com.mongodb.client.AggregateIterable;
import org.bson.Document;

import java.sql.SQLException;

public interface KlantDAO {

    public String get(int month) throws SQLException;

    public AggregateIterable<Document> getMongoData(int month) throws SQLException;
}
