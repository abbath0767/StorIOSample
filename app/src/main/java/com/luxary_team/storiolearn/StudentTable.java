package com.luxary_team.storiolearn;


import com.pushtorefresh.storio.sqlite.queries.Query;

public class StudentTable {

    public static final String TABLE = "students";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_AVERAGE = "average";

    public static final Query QUERY_ALL = Query.builder().
                                                table(TABLE).
                                                build();

    private StudentTable() {
        throw new IllegalStateException("No instances please");
    }

    public static String getCreateTabelQuery() {
        return "CREATE TABLE " + TABLE + "("
                    + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                    + COLUMN_NAME + " TEXT NOT NULL"
                    + COLUMN_AVERAGE + " INTEGER NOT NULL" + ");";
    }

}
