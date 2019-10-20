package persistacx.structures;

public class FieldObject {
    private String sqlType;
    private String fieldName;
    private boolean isPrimaryaKey;

    public FieldObject(String sqlType, String fieldName) {
        this.sqlType = sqlType;
        this.fieldName = fieldName;
        this.isPrimaryaKey = false;
    }

    public FieldObject(String sqlType, String fieldName, boolean isPrimaryaKey) {
        this.sqlType = sqlType;
        this.fieldName = fieldName;
        this.isPrimaryaKey = isPrimaryaKey;
    }

    public String getSqlType() {
        return sqlType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean isPrimaryaKey() {
        return isPrimaryaKey;
    }
}
