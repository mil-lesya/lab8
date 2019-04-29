package com.mileshko.lesya.dao;

public class ConnectionBuilderFactory {
    public static ConnectionBuilder getConnectionBuilder() {
        return new SimpleConnectionBuilder();
    }

}
