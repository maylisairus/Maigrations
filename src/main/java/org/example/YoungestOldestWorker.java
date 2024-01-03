package org.example;

import java.sql.Date;

public class YoungestOldestWorker {

    private String type;
    private String name;
    private Date birthday;

    public YoungestOldestWorker(String type, String name, Date birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "YoungestOldestWorker{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
