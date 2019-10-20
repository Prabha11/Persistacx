package persistacx;

import persistacx.Query.Query;
import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;
import persistacx.structures.FieldObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Queue;

public class AdvancedDao extends Dao {
    public String makeDBQuery(Class entityClass) {
        Entity entity = (Entity) entityClass.getAnnotation(Entity.class);
        String entityName;

        Queue<Method> methods = getDaoService().getMethodsByAnnotation(entityClass, Field.class);
        methods = getDaoService().getMethodsByAnnotationAddToQueue(entityClass, ForeignKey.class, methods);

        Queue<FieldObject> fieldObjects = new LinkedList<>();

        if (entity != null) {

            entityName = entity.name();
            for (Method method : methods) {
                Field field = method.getAnnotation(Field.class);
                if (field != null) {
                    String fieldName = field.name();
                    String sqlType = getDaoService().dataTypeMapJavaToSQL(method.getReturnType());

                    boolean isPrimaryKey = method.getAnnotation(PrimaryKey.class) != null;

                    FieldObject fieldObject = new FieldObject(sqlType, fieldName, isPrimaryKey);
                    fieldObjects.add(fieldObject);
                } else {
                    ForeignKey foreignKey = method.getAnnotation(ForeignKey.class);

                    if (foreignKey != null) {
                        String fieldName = foreignKey.name();
                        Class foreignKeyClass = method.getReturnType();
                        Method foreignObjectPrimaryKetGetMethod = getDaoService()
                                .getFirstMethodByAnnotation(foreignKeyClass, PrimaryKey.class);

                        Type foreignKeyIdType = foreignObjectPrimaryKetGetMethod.getReturnType();
                        String sqlType = getDaoService().dataTypeMapJavaToSQL(foreignKeyIdType);
                        FieldObject fieldObject = new FieldObject(sqlType, fieldName);
                        fieldObjects.add(fieldObject);
                    }
                }
            }

            Query query = new Query();
            query.createCreateTableQuery(entityName, fieldObjects);

            return query.getQuery();
        }

        return null;
    }
}
