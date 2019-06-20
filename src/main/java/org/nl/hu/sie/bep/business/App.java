package org.nl.hu.sie.bep.business;

import org.nl.hu.sie.bep.business.models.Data;
import org.nl.hu.sie.bep.business.models.KlantMysqlImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        KlantMysqlImpl data = new KlantMysqlImpl();
        List<Data> entries = data.get(5);

        entries.forEach(System.out::println);
    }
}

