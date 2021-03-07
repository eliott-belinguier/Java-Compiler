package fr.belinguier.java.compiler.builder.util;

public class DescriptorBuilder {

    public static String getTypeDescriptor(String className) {
        int firstArrayIndex;
        int lastArrayIndex;

        if (className == null)
            return "V";
        firstArrayIndex = className.indexOf("[");
        if (firstArrayIndex > 0)
            return null;;
        if (firstArrayIndex == 0) {
            lastArrayIndex = className.lastIndexOf("[");
            if (lastArrayIndex < firstArrayIndex)
                return null;
            for (int i = firstArrayIndex; i < lastArrayIndex; i++)
                if (className.charAt(i) != '[')
                    return null;
            return className;
        }
        switch (className) {
            case "void":
                return "V";
            case "byte":
                return "B";
            case "char":
                return "C";
            case "double":
                return "D";
            case "float":
                return "F";
            case "int":
                return "I";
            case "long":
                return "J";
            case "short":
                return "S";
            case "boolean":
                return "Z";
        }
        className = className.replaceAll("\\.", "/");
        return String.format("L%s;", className);
    }

    public static String getTypeDescriptor(Class<?> clazz) {
        if (clazz == null)
            return getTypeDescriptor((String) null);
        return getTypeDescriptor(clazz.getName());
    }

    public static String getMethodDescriptor(String returnClassName, String... parameterClassName) {
        StringBuilder stringBuilder = new StringBuilder("(");

        if (parameterClassName != null && parameterClassName.length > 0) {
            for (String className : parameterClassName) {
                if (className == null)
                    return null;
                stringBuilder.append(getTypeDescriptor(className));
            }
        }
        stringBuilder.append(")");
        stringBuilder.append(getTypeDescriptor(returnClassName));
        return stringBuilder.toString();
    }

    public static String getMethodDescriptor(Class<?> returnClass, Class<?>... parameterClass) {
        StringBuilder stringBuilder = new StringBuilder("(");

        if (parameterClass != null && parameterClass.length > 0) {
            for (Class<?> clazz : parameterClass) {
                if (clazz == null)
                    return null;
                stringBuilder.append(getTypeDescriptor(clazz));
            }
        }
        stringBuilder.append(")");
        stringBuilder.append(getTypeDescriptor(returnClass));
        return stringBuilder.toString();
    }
}
