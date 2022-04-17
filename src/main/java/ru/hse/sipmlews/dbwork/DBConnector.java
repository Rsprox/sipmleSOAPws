package ru.hse.sipmlews.dbwork;


import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:D:/REPO/test.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DBConnector instance = null;

    public static synchronized DBConnector getInstance() throws SQLException {
        if (instance == null)
            instance = new DBConnector();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DBConnector() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

}