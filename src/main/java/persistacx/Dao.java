package persistacx;

import persistacx.Query.Query;
import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;
import persistacx.structures.SetterObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class Dao {

    protected DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
    private DaoService daoService;

    protected DaoService getDaoService() {
        if (daoService == null) {
            daoService = new DaoService();
        }
        return daoService;
    }

    public boolean save(Object object) throws Exception {
        Queue<String> fieldNames = new LinkedList<String>();
        Queue<Method> annotatedMethods = new LinkedList<Method>();

        Class objectClass = object.getClass();
        Method[] methods = objectClass.getMethods();

        String entityName;
        Entity entity = (Entity) objectClass.getAnnotation(Entity.class);
        entityName = entity.name();

        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            try {
                if (field != null) {
                    String fieldName = field.name();
                    fieldNames.add(fieldName);
                    annotatedMethods.add(method);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Queue<String> values = new LinkedList<String>();

        for (Method method : annotatedMethods) {
            try {
                values.add(method.invoke(object).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (Method method : methods) {
            ForeignKey foreignKey = method.getAnnotation(ForeignKey.class);
            try {
                if (foreignKey != null) {
                    String foreignKeyName = foreignKey.name();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (foreignKey != null) {
                    Object foreignObject = method.invoke(object);
                    Class foreignObjectClass = foreignObject.getClass();
                    Method[] foreignObjectMethods = foreignObjectClass.getMethods();

                    for (Method foreignObjectMethod : foreignObjectMethods) {
                        PrimaryKey primaryKey = foreignObjectMethod.getAnnotation(PrimaryKey.class);
                        try {
                            if (primaryKey != null) {
                                String fieldName = foreignKey.name();
                                fieldNames.add(fieldName);
                                values.add(foreignObjectMethod.invoke(foreignObject).toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    this.save(foreignObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Query query = new Query();
        query.createInsertQuery(entityName, fieldNames, values);

        return databaseConnector.executeSaveQuery(query.getQuery());
    }

    public Object open(Object sample) throws Exception {
        Class objectClass = sample.getClass();
        Object object = null;

        try {
            object = objectClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Queue<String> fieldNames = new LinkedList<String>();
        Queue<String> values = new LinkedList<String>();
        Queue<SetterObject> setters = new LinkedList<SetterObject>();
        Queue<SetterObject> foreignObjectSetters = new LinkedList<SetterObject>();

        String entityName;
        Entity entity = (Entity) objectClass.getAnnotation(Entity.class);
        entityName = entity.name();

        Method[] methods = objectClass.getMethods();

        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            try {
                if (field != null) {

                    String fieldName = field.name();
                    Object value = method.invoke(sample);

                    if ((value != null) && (!value.toString().equals("0.0")) && (!value.toString().equals("0"))) {
                        fieldNames.add(fieldName);
                        values.add(value.toString());
                    }

                    String getterName = method.getName();
                    String setterName = getDaoService().getSetterNameFromGetterName(getterName);
                    Class dataType = method.getReturnType();
                    Method setter = objectClass.getMethod(setterName, dataType);
                    SetterObject setterObject = new SetterObject(fieldName, setter, dataType);
                    setters.add(setterObject);

                } else {
                    ForeignKey foreignKey = method.getAnnotation(ForeignKey.class);

                    if (foreignKey != null) {
                        String foreignKeyName = foreignKey.name();
                        String getterName = method.getName();
                        String setterName = getDaoService().getSetterNameFromGetterName(getterName);
                        Class dataType = method.getReturnType();
                        Method setter = objectClass.getMethod(setterName, dataType);
                        SetterObject setterObject = new SetterObject(foreignKeyName, setter, dataType);
                        foreignObjectSetters.add(setterObject);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Query query = new Query();
        query.createSelectQuery(entityName, fieldNames, values);

        ResultSet resultSet = databaseConnector.executeSelectQuery(query.getQuery());

        try {
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (SetterObject setter : setters) {
            try {
                setter.getSetterMethod().invoke(
                        object,
                        resultSet.getObject(setter.getSetterName(), setter.getDataType())
                );
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (SetterObject foreignObjectSetter : foreignObjectSetters) {
            try {
                Class foreignObjectClass = foreignObjectSetter.getDataType();
                Method foreignKeyGetMethod = daoService
                        .getMethodsByAnnotation(foreignObjectClass, PrimaryKey.class).poll();
                String foreignKeyGetMethodName = foreignKeyGetMethod.getName();
                String foreignKeySetMethodName = daoService.getSetterNameFromGetterName(foreignKeyGetMethodName);
                Object foreignValue = resultSet
                        .getObject(foreignObjectSetter.getSetterName(), foreignKeyGetMethod.getReturnType());
                Method foreignKeySetMethod = foreignObjectClass
                        .getMethod(foreignKeySetMethodName, foreignKeyGetMethod.getReturnType());

                Object foreignObject = foreignObjectClass.newInstance();
                foreignKeySetMethod.invoke(foreignObject, foreignValue);

                foreignObjectSetter.getSetterMethod().invoke(object, foreignObject);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return object;
    }
}
