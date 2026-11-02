package pl.edu.pwr.mf.jp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);

        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
            st.execute("PRAGMA busy_timeout = 5000");
        } catch (SQLException e) {
            conn.close();
            throw e;
        }

        return conn;
    }

    public static void init() throws SQLException {
        try (Connection conn = connect(); Statement st = conn.createStatement()) {
            st.execute("""
                        CREATE TABLE IF NOT EXISTS sprzet (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nazwa TEXT NOT NULL,
                            cena REAL NOT NULL
                        )
                        """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS klient (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nazwa TEXT NOT NULL
                        )
                        """);

            st.execute("""
                        CREATE TABLE IF NOT EXISTS rezerwacja (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            id_sprzetu INTEGER NOT NULL REFERENCES sprzet(id),
                            id_klienta INTEGER NOT NULL REFERENCES klient(id),
                            data_poczatkowa TEXT NOT NULL,
                            data_koncowa TEXT NOT NULL,
                            status TEXT NOT NULL CHECK (status IN ('ZALOZONA', 'WYCOFANA', 'REALIZOWANA', 'ZAKONCZONA')),
                            platnosc INTEGER NOT NULL DEFAULT 0
                        )
                        """);
        }
    }
}
