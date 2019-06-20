package org.nl.hu.sie.bep.business.models;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class KlantMysqlImpl {
    private volatile Connection con = null;
    private PreparedStatement statement;
    private ResultSet result;

    /**
     * Return entries matching {@code month} from mysql
     *
     * @param month int
     * @return matching entries if any
     */
    public List<Data> get(int month) throws SQLException {
        AggregateIterable<Document> entries = this.getMongoData(month);

        List<Data> data = new ArrayList<>();
        entries.forEach((Consumer<? super Document>) (e) -> {
            try {
                PreparedStatement statement = prepare("SELECT * FROM Klant " +
                        " JOIN Adres A on Klant.KlantID = A.KlantID " +
                        " JOIN Persoon P on Klant.KlantID = P.KlantID " +
                        "WHERE Klant.KlantID = ? AND P.PersoonID = ?"
                );
                statement.setDouble(1, e.getDouble("customerId"));
                statement.setDouble(2, e.getDouble("personId"));

                ResultSet rs = executeQuery(statement);

                while (rs.next()) {
                    Data dd = Data.fromResultSet(rs);

                    if (dd == null) {
                        continue;
                    }

                    dd.note = e.getString("note");
                    dd.invoiceLines = e.getList("invoiceLines", Document.class);

                    data.add(dd);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return data;
    }

    /**
     * Return entries matching {@code month}
     *
     * @param montha int
     * @return matching entries if any
     */
    public AggregateIterable<Document> getMongoData(int montha) {
        MongoClient mongoClient = MongoClients.create();

        MongoDatabase database = mongoClient.getDatabase("bifi");
        MongoCollection<Document> collection = database.getCollection("factuur");
        return collection.aggregate(Arrays.asList(
                Aggregates.project(
                        Projections.fields(
                                Projections.excludeId(),
                                Projections.include("customerId"),
                                Projections.include("personId"),
                                Projections.include("date"),
                                Projections.include("invoiceId"),
                                Projections.include("note"),
                                Projections.include("invoiceLines"),
                                Projections.computed("month", new Document("$month", "$date")
                                )
                        )),
                Aggregates.match(eq("month", montha)))
        );
    }

    /**Standaard code om statements te laten werken
     *
     * @param statement
     * @return
     * @throws SQLException
     */
    public PreparedStatement prepare(final String statement) throws SQLException {
        return this.prepare(statement, Statement.NO_GENERATED_KEYS);
    }

    public PreparedStatement prepare(final String statement, int autoGeneratedKeys) throws SQLException {
        this.close(this.statement, this.result);

        if (this.con == null || this.con.isClosed()) {
            try {
                this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bifi", "root", "root");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        this.statement = this.con.prepareStatement(statement, autoGeneratedKeys);
        return this.statement;
    }

    public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        this.result = statement.executeQuery();

        return this.result;
    }

    public PreparedStatement persist() {
        PreparedStatement stmt = this.statement;
        this.statement = null;
        this.result = null;
        return stmt;
    }

    public void close(PreparedStatement p, ResultSet r) {
        try {
            if (r != null) r.close();
        } catch (Exception ex) {
        }

        try {
            if (p != null) p.close();
        } catch (Exception ex) {
        }
    }
}
