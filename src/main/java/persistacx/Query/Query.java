package persistacx.Query;

import persistacx.structures.FieldObject;

import java.util.Queue;

public class Query {
    private String query;
    private String[] selectFields;

    private QueryService queryService;

    protected QueryService getQueryService() {
        if (queryService == null) {
            queryService = new QueryService();
        }
        return queryService;
    }

    public String getQuery() {
        return query;
    }

    public void createInsertQuery(String tableName, Queue<String> fieldNames, Queue<String> values) {
        query = "INSERT INTO " + tableName + " (";
        query += fieldNames.poll();
        for (String fieldName : fieldNames) {
            query += "," + fieldName;
        }

        query += ") VALUES (";
        query += "'" + values.poll() + "'";

        for (String value : values) {
            query += ", '" + value + "'";
        }

        query += ");";
    }

    public void createSelectQuery(String tableName, Queue<String> fieldNames, Queue<String> values) {
        query = String.format("SELECT * FROM %s WHERE (", tableName);
        query += fieldNames.poll();
        for (String fieldName : fieldNames) {
            query += "," + fieldName;
        }

        query += ") = (";
        query += "'" + values.poll() + "'";

        for (String value : values) {
            query += ", '" + value + "'";
        }

        query += ");";
    }

    public void createDeleteQuery(String tableName, Queue<String> fieldNames, Queue<String> values) {
        query = String.format("DELETE FROM %s WHERE (", tableName);
        query += fieldNames.poll();
        for (String fieldName : fieldNames) {
            query += "," + fieldName;
        }

        query += ") = (";
        query += "'" + values.poll() + "'";

        for (String value : values) {
            query += ", '" + value + "'";
        }

        query += ");";
    }

    public void createCreateTableQuery(String tableName, Queue<FieldObject> fieldObjects) {
        query = "CREATE TABLE " + tableName + " (";
        query = String.format("CREATE TABLE %s (", tableName);

        FieldObject firstFieldObject = fieldObjects.poll();

        query += firstFieldObject.getFieldName() + " " + firstFieldObject.getSqlType();
        if (firstFieldObject.isPrimaryaKey()) query += " PRIMARY KEY";

        for (FieldObject fieldObject : fieldObjects) {
            query += "," + fieldObject.getFieldName() + " " + fieldObject.getSqlType();
            if (fieldObject.isPrimaryaKey()) query += " PRIMARY KEY";

        }

        query += ");";
    }


}
