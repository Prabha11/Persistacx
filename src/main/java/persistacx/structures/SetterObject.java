package persistacx.structures;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class SetterObject {

    private String setterName;
    private Method setterMethod;
    private Class dataType;

    public SetterObject(String setterName, Method setterMethod, Class dataType) {
        this.setterName = setterName;
        this.setterMethod = setterMethod;
        this.dataType = dataType;
    }

    public String getSetterName() {
        return setterName;
    }

    public Method getSetterMethod() {
        return setterMethod;
    }

    public Class getDataType() {
        return dataType;
    }
}
