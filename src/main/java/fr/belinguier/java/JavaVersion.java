package fr.belinguier.java;

/**
 * Enumeration of java versions compatible or soon compatible with the compiler.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see <a href="https://en.wikipedia.org/wiki/Java_class_file">Java class file (Wikipedia)</a>
 * @see <a href="https://docs.oracle.com/javase/specs/">Oracle JVM Docs</a>
 */
public enum JavaVersion {

    JAVA_1_1((short) 45),
    JAVA_1_2((short) 46),
    JAVA_1_3((short) 47),
    JAVA_1_4((short) 48),
    JAVA_5((short) 49),
    JAVA_6((short) 50),
    JAVA_7((short) 51),
    JAVA_8((short) 52),
    JAVA_9((short) 53),
    JAVA_10((short) 54),
    JAVA_11((short) 55),
    JAVA_12((short) 56),
    JAVA_13((short) 57),
    JAVA_14((short) 58),
    JAVA_15((short) 59),
    JAVA_16((short) 60),
    JAVA_17((short) 61),
    JAVA_18((short) 62);

    /**
     * Java version byte code number.
     * @since 1.0
     * @see <a href="https://en.wikipedia.org/wiki/Java_class_file">Java class file (Wikipedia)</a>
     * @see <a href="https://docs.oracle.com/javase/specs/">Oracle JVM Docs</a>
     */
    public final short byteCode;

    /**
     * @param byteCode Java version byte code number.
     * @since 1.0
     */
    JavaVersion(short byteCode) {
        this.byteCode = byteCode;
    }
}
