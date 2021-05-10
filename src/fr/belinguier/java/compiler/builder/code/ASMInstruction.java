package fr.belinguier.java.compiler.builder.code;

import fr.belinguier.java.compiler.builder.LocalVariableManager;

/**
 * @author Eliott Belinguier
 */
public enum ASMInstruction implements Instruction {

    /** mnemonic
     * Operation:
     *     Short description of the instruction
     * Format:
     *     mnemonic
     *     operand1
     *     operand2
     *     ...
     * Forms:
     *     mnemonic = opcode
     * Operand Stack:
     *     ..., value1, value2 →
     *     ..., value3
     * Description:
     *     A longer description detailing constraints on operand stack contents or constant pool entries, the operation performed, the type of the results, etc.
     * Linking Exceptions:
     *     If any linking exceptions may be thrown by the execution of this instruction, they are set off one to a line, in the order in which they must be thrown.
     * Run-time Exceptions:
     *     If any run-time exceptions can be thrown by the execution of an instruction, they are set off one to a line, in the order in which they must be thrown.
     *
     *     Other than the linking and run-time exceptions, if any, listed for an instruction, that instruction must not throw any run-time exceptions except for instances of VirtualMethodError or its subclasses.
     * Notes:
     *     Comments not strictly part of the specification of an instruction are set aside as notes at the end of the description.
     *
     *     Each cell in the instruction format diagram represents a single 8-bit byte. The instruction's mnemonic is its name.
     *     Its opcode is its numeric representation and is given in both decimal and hexadecimal forms.
     *     Only the numeric representation is actually present in the Java Virtual Machine code in a class file.
     *
     *     Keep in mind that there are "operands" generated at compile time and embedded within Java Virtual Machine instructions, as well as "operands" calculated at run time and supplied on the operand stack.
     *     Although they are supplied from several different areas, all these operands represent the same thing: values to be operated upon by the Java Virtual Machine instruction being executed.
     *     By implicitly taking many of its operands from its operand stack, rather than representing them explicitly in its compiled code as additional operand bytes, register numbers, etc., the Java Virtual Machine's code stays compact.
     *
     *     Some instructions are presented as members of a family of related instructions sharing a single description, format, and operand stack diagram.
     *     As such, a family of instructions includes several opcodes and opcode mnemonics; only the family mnemonic appears in the instruction format diagram, and a separate forms line lists all member mnemonics and opcodes.
     *     For example, the Forms line for the lconst_<l> family of instructions, giving mnemonic and opcode information for the two instructions in that family (lconst_0 and lconst_1), is
     *         lconst_0 = 9 (0x9)
     *         lconst_1 = 10 (0xa)
     *     In the description of the Java Virtual Machine instructions, the effect of an instruction's execution on the operand stack of the current frame is represented textually, with the stack growing from left to right and each value represented separately. Thus,
     *         ..., value1, value2 →
     *         ..., result
     *     shows an operation that begins by having value2 on top of the operand stack with value1 just beneath it.
     *     As a result of the execution of the instruction, value1 and value2 are popped from the operand stack and replaced by result value, which has been calculated by the instruction.
     *     The remainder of the operand stack, represented by an ellipsis (...), is unaffected by the instruction's execution.
     *
     *     Values of types long and double are represented by a single entry on the operand stack.
     *
     *     In The Java Virtual Machine Specification, First Edition, values on the operand stack of types long and double were each represented in the stack diagram by two entries.
     */

    /**
     * Operation:
     *     Load reference from array
     * Format:
     *     aaload
     * Forms:
     *     aaload = 50 (0x32)
     * Operand Stack:
     *     ..., arrayref, index →
     *     ..., value
     * Description:
     *     The arrayref must be of type reference and must refer to an array whose components are of type reference.
     *     The index must be of type int. Both arrayref and index are popped from the operand stack.
     *     The reference value in the component of the array at index is retrieved and pushed onto the operand stack.
     *  Run-time Exceptions:
     *      If arrayref is null, aaload throws a NullPointerException.
     *
     *      Otherwise, if index is not within the bounds of the array referenced by arrayref, the aaload instruction throws an ArrayIndexOutOfBoundsException.
     */
    AALOAD((byte) 0x32, 0, 1, 2),

    /**
     * Operation:
     *     Store into reference array
     * Format:
     *     aastore
     * Forms:
     *     aastore = 83 (0x53)
     * Operand Stack:
     *     ..., arrayref, index, value →
     *     ...
     * Description:
     *     The arrayref must be of type reference and must refer to an array whose components are of type reference.
     *     The index must be of type int and value must be of type reference. The arrayref, index, and value are popped from the operand stack.
     *     The reference value is stored as the component of the array at index.
     *
     *     At run time, the type of value must be compatible with the type of the components of the array referenced by arrayref.
     *     Specifically, assignment of a value of reference type S (source) to an array component of reference type T (target) is allowed only if:
     *
     *     If S is a class type, then:
     *         If T is a class type, then S must be the same class as T, or S must be a subclass of T;
     *         If T is an interface type, then S must implement interface T.
     *
     *     If S is an interface type, then:
     *         If T is a class type, then T must be Object.
     *         If T is an interface type, then T must be the same interface as S or a superinterface of S.
     *
     *     If S is an array type, namely, the type SC[], that is, an array of components of type SC, then:
     *         If T is a class type, then T must be Object.
     *         If T is an interface type, then T must be one of the interfaces implemented by arrays.
     *         If T is an array type TC[], that is, an array of components of type TC, then one of the following must be true:
     *             TC and SC are the same primitive type.
     *             TC and SC are reference types, and type SC is assignable to TC by these run-time rules.
     * Run-time Exceptions:
     *     If arrayref is null, aastore throws a NullPointerException.
     *
     *     Otherwise, if index is not within the bounds of the array referenced by arrayref, the aastore instruction throws an ArrayIndexOutOfBoundsException.
     *
     *     Otherwise, if arrayref is not null and the actual type of value is not assignment compatible with the actual type of the components of the array, aastore throws an ArrayStoreException.
     */
    AASTORE((byte) 0x53, 0, 0, 3),

    /**
     * Operation:
     *     Push null
     * Format:
     *     aconst_null
     * Forms:
     *     aconst_null = 1 (0x1)
     * Operand Stack:
     *     ... →
     *     ..., null
     * Description:
     *     Push the null object reference onto the operand stack.
     * Notes:
     *     The Java Virtual Machine does not mandate a concrete value for null.
     */
    ACONST_NULL((byte) 0x1, 0, 1, 0),

    /**
     * Operation:
     *     Load reference from local variable
     * Format:
     *     aload
     *     index
     * Forms:
     *     aload = 25 (0x19)
     * Operand Stack:
     *     ... →
     *     ..., objectref
     * Description:
     *     The index is an unsigned byte that must be an index into the local variable array of the current frame.
     *     The local variable at index must contain a reference. The objectref in the local variable at index is pushed onto the operand stack.
     * Notes:
     *     The aload instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the astore instruction is intentional.
     *
     *     The aload opcode can be used in conjunction with the wide instruction to access a local variable using a two-byte unsigned index.
     */
    ALOAD((byte) 0x19, 1, 1, 0),

    /**
     * Operation:
     *     Load reference from local variable
     * Format:
     *     aload_<n>
     * Forms:
     *     aload_0 = 42 (0x2a)
     * Operand Stack:
     *     ... →
     *     ..., objectref
     * Description:
     *     The <n> must be an index into the local variable array of the current frame. The local variable at <n> must contain a reference.
     *     The objectref in the local variable at <n> is pushed onto the operand stack.
     * Notes:
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the aload_<n> instructions is the same as aload with an index of <n>, except that the operand <n> is implicit.
     */
    ALOAD_0((byte) 0x2a, 0, 1, 0),

    /**
     * Operation:
     *     Load reference from local variable
     * Format:
     *     aload_<n>
     * Forms:
     *     aload_1 = 43 (0x2b)
     * Operand Stack:
     *     ... →
     *     ..., objectref
     * Description:
     *     The <n> must be an index into the local variable array of the current frame. The local variable at <n> must contain a reference.
     *     The objectref in the local variable at <n> is pushed onto the operand stack.
     * Notes:
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the aload_<n> instructions is the same as aload with an index of <n>, except that the operand <n> is implicit.
     */
    ALOAD_1((byte) 0x2b, 0, 1, 0),

    /**
     * Operation:
     *     Load reference from local variable
     * Format:
     *     aload_<n>
     * Forms:
     *     aload_2 = 44 (0x2c)
     * Operand Stack:
     *     ... →
     *     ..., objectref
     * Description:
     *     The <n> must be an index into the local variable array of the current frame. The local variable at <n> must contain a reference.
     *     The objectref in the local variable at <n> is pushed onto the operand stack.
     * Notes:
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the aload_<n> instructions is the same as aload with an index of <n>, except that the operand <n> is implicit.
     */
    ALOAD_2((byte) 0x2c, 0, 1, 0),

    /**
     * Operation:
     *     Load reference from local variable
     * Format:
     *     aload_<n>
     * Forms:
     *     aload_3 = 45 (0x2d)
     * Operand Stack:
     *     ... →
     *     ..., objectref
     * Description:
     *     The <n> must be an index into the local variable array of the current frame. The local variable at <n> must contain a reference.
     *     The objectref in the local variable at <n> is pushed onto the operand stack.
     * Notes:
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the aload_<n> instructions is the same as aload with an index of <n>, except that the operand <n> is implicit.
     */
    ALOAD_3((byte) 0x2d, 0, 1, 0),

    /**
     * Operation:
     *     Create new array of reference
     * Format:
     *     anewarray
     *     indexbyte1
     *     indexbyte2
     * Forms:
     *     anewarray = 189 (0xbd)
     * Operand Stack:
     *     ..., count →
     *     ..., arrayref
     * Description:
     *     The count must be of type int. It is popped off the operand stack. The count represents the number of components of the array to be created.
     *     The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class, where the value of the index is (indexbyte1 << 8) | indexbyte2.
     *     The run-time constant pool item at that index must be a symbolic reference to a class, array, or interface type.
     *     The named class, array, or interface type is resolved. A new array with components of that type, of length count, is allocated from the garbage-collected heap, and a reference arrayref to this new array object is pushed onto the operand stack.
     *     All components of the new array are initialized to null, the default value for reference types.
     * Linking Exceptions:
     *     During resolution of the symbolic reference to the class, array, or interface type, any of the exceptions can be thrown.
     * Run-time Exceptions:
     *     Otherwise, if count is less than zero, the anewarray instruction throws a NegativeArraySizeException.
     * Notes:
     *     The anewarray instruction is used to create a single dimension of an array of object references or part of a multidimensional array.
     */
    ANEWARRAY((byte) 0xbd, 2, 1, 1),

    /**
     * Operation:
     *     Return reference from method
     * Format:
     *     areturn
     * Operand Stack:
     *     ..., objectref →
     *     [empty]
     * Description:
     *     The objectref must be of type reference and must refer to an object of a type that is assignment compatible with the type represented by the return descriptor of the current method.
     *     If the current method is a synchronized method, the monitor entered or reentered on invocation of the method is updated and possibly exited as if by execution of a monitorexit instruction in the current thread.
     *     If no exception is thrown, objectref is popped from the operand stack of the current frame and pushed onto the operand stack of the frame of the invoker. Any other values on the operand stack of the current method are discarded.
     *
     *     The interpreter then reinstates the frame of the invoker and returns control to the invoker.
     * Run-time Exceptions:
     *     If the Java Virtual Machine implementation does not enforce the rules on structured locking, then if the current method is a synchronized method and the current thread is not the owner of the monitor entered or reentered on invocation of the method, areturn throws an IllegalMonitorStateException.
     *     This can happen, for example, if a synchronized method contains a monitorexit instruction, but no monitorenter instruction, on the object on which the method is synchronized.
     *
     *     Otherwise, if the Java Virtual Machine implementation enforces the rules on structured locking and if the first of those rules is violated during invocation of the current method, then areturn throws an IllegalMonitorStateException.
     */
    ARETURN((byte) 0xb0, 0, 0, -1),

    /**
     * Operation:
     *     Get length of array
     * Format:
     *     arraylength
     * Forms:
     *     arraylength = 190 (0xbe)
     * Operand Stack:
     *     ..., arrayref →
     *     ..., length
     * Description:
     *     The arrayref must be of type reference and must refer to an array.
     *     It is popped from the operand stack. The length of the array it references is determined.
     *     That length is pushed onto the operand stack as an int.
     * Run-time Exceptions:
     *     If the arrayref is null, the arraylength instruction throws a NullPointerException.
     */
    ARRAYLENGTH((byte) 0xbe, 0, 1, 1),

    /**
     * Operation:
     *     Store reference into local variable
     * Format:
     *     astore
     *     index
     * Forms:
     *     astore = 58 (0x3a)
     * Operand Stack:
     *     ..., objectref →
     *     ...
     * Description:
     *     The index is an unsigned byte that must be an index into the local variable array of the current frame.
     *     The objectref on the top of the operand stack must be of type returnAddress or of type reference.
     *     It is popped from the operand stack, and the value of the local variable at index is set to objectref.
     * Notes:
     *     The astore instruction is used with an objectref of type returnAddress when implementing the finally clause of the Java programming language.
     *
     *     The aload instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the astore instruction is intentional.
     *
     *     The astore opcode can be used in conjunction with the wide instruction to access a local variable using a two-byte unsigned index.
     */
    ASTORE((byte) 0x3a, 1, 0, 1),

    /**
     * Operation:
     *     Store reference into local variable
     * Format:
     *     astore_<n>
     * Forms:
     *     astore_0 = 75 (0x4b)
     * Operand Stack:
     *     ..., objectref →
     *     ...
     * Description:
     *     The <n> must be an index into the local variable array of the current frame.
     *     The objectref on the top of the operand stack must be of type returnAddress or of type reference.
     *     It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.
     * Notes:
     *     An astore_<n> instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language.
     *
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the astore_<n> instructions is the same as astore with an index of <n>, except that the operand <n> is implicit.
     */
    ASTORE_0((byte) 0x4b, 0, 0, 1),

    /**
     * Operation:
     *     Store reference into local variable
     * Format:
     *     astore_<n>
     * Forms:
     *     astore_1 = 76 (0x4c)
     * Operand Stack:
     *     ..., objectref →
     *     ...
     * Description:
     *     The <n> must be an index into the local variable array of the current frame.
     *     The objectref on the top of the operand stack must be of type returnAddress or of type reference.
     *     It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.
     * Notes:
     *     An astore_<n> instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language.
     *
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the astore_<n> instructions is the same as astore with an index of <n>, except that the operand <n> is implicit.
     */
    ASTORE_1((byte) 0x4c, 0, 0, 1),

    /**
     * Operation:
     *     Store reference into local variable
     * Format:
     *     astore_<n>
     * Forms:
     *     astore_2 = 77 (0x4d)
     * Operand Stack:
     *     ..., objectref →
     *     ...
     * Description:
     *     The <n> must be an index into the local variable array of the current frame.
     *     The objectref on the top of the operand stack must be of type returnAddress or of type reference.
     *     It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.
     * Notes:
     *     An astore_<n> instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language.
     *
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the astore_<n> instructions is the same as astore with an index of <n>, except that the operand <n> is implicit.
     */
    ASTORE_2((byte) 0x4d, 0, 0, 1),

    /**
     * Operation:
     *     Store reference into local variable
     * Format:
     *     astore_<n>
     * Forms:
     *     astore_3 = 78 (0x4e)
     * Operand Stack:
     *     ..., objectref →
     *     ...
     * Description:
     *     The <n> must be an index into the local variable array of the current frame.
     *     The objectref on the top of the operand stack must be of type returnAddress or of type reference.
     *     It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.
     * Notes:
     *     An astore_<n> instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language.
     *
     *     An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
     *     This asymmetry with the corresponding astore_<n> instruction is intentional.
     *
     *     Each of the astore_<n> instructions is the same as astore with an index of <n>, except that the operand <n> is implicit.
     */
    ASTORE_3((byte) 0x4e, 0, 0, 1),

    /**
     * Operation:
     *     Throw exception or error
     * Format:
     *     athrow
     * Forms:
     *     athrow = 191 (0xbf)
     * Operand Stack:
     *     ..., objectref →
     *     objectref
     * Description:
     *     The objectref must be of type reference and must refer to an object that is an instance of class Throwable or of a subclass of Throwable. It is popped from the operand stack.
     *     The objectref is then thrown by searching the current method for the first exception handler that matches the class of objectref, as given by the algorithm.
     *
     *     If an exception handler that matches objectref is found, it contains the location of the code intended to handle this exception.
     *     The pc register is reset to that location, the operand stack of the current frame is cleared, objectref is pushed back onto the operand stack, and execution continues.
     *
     *     If no matching exception handler is found in the current frame, that frame is popped.
     *     If the current frame represents an invocation of a synchronized method, the monitor entered or reentered on invocation of the method is exited as if by execution of a monitorexit instruction.
     *     Finally, the frame of its invoker is reinstated, if such a frame exists, and the objectref is rethrown. If no such frame exists, the current thread exits.
     * Run-time Exceptions:
     *     If objectref is null, athrow throws a NullPointerException instead of objectref.
     *
     *     Otherwise, if the Java Virtual Machine implementation does not enforce the rules on structured locking,
     *     then if the method of the current frame is a synchronized method and the current thread is not the owner of the monitor entered or reentered on invocation of the method,
     *     athrow throws an IllegalMonitorStateException instead of the object previously being thrown. This can happen, for example, if an abruptly completing synchronized method contains a monitorexit instruction,
     *     but no monitorenter instruction, on the object on which the method is synchronized.
     *
     *     Otherwise, if the Java Virtual Machine implementation enforces the rules on structured locking and if the first of those rules is violated during invocation of the current method,
     *     then athrow throws an IllegalMonitorStateException instead of the object previously being thrown.
     * Notes:
     *     The operand stack diagram for the athrow instruction may be misleading: If a handler for this exception is matched in the current method, the athrow instruction discards all the values on the operand stack,
     *     then pushes the thrown object onto the operand stack. However, if no handler is matched in the current method and the exception is thrown farther up the method invocation chain,
     *     then the operand stack of the method (if any) that handles the exception is cleared and objectref is pushed onto that empty operand stack.
     *     All intervening frames from the method that threw the exception up to, but not including, the method that handles the exception are discarded.
     */
    ATHROW((byte) 0xbf, 0, 1, -1),

    /**
     * Operation:
     *     Load byte or boolean from array
     * Format:
     *     baload
     * Forms:
     *     baload = 51 (0x33)
     * Operand Stack:
     *     ..., arrayref, index →
     *     ..., value
     * Description:
     *     The arrayref must be of type reference and must refer to an array whose components are of type byte or of type boolean.
     *     The index must be of type int. Both arrayref and index are popped from the operand stack.
     *     The byte value in the component of the array at index is retrieved, sign-extended to an int value, and pushed onto the top of the operand stack.
     * Run-time Exceptions:
     *     If arrayref is null, baload throws a NullPointerException.
     *
     *     Otherwise, if index is not within the bounds of the array referenced by arrayref, the baload instruction throws an ArrayIndexOutOfBoundsException.
     * Notes:
     *     The baload instruction is used to load values from both byte and boolean arrays.
     *     In Oracle's Java Virtual Machine implementation, boolean arrays - that is, arrays of type T_BOOLEAN - are implemented as arrays of 8-bit values.
     *     Other implementations may implement packed boolean arrays; the baload instruction of such implementations must be used to access those arrays.
     */
    BALOAD((byte) 0x33, 0, 1, 2),

    /**
     * Operator:
     *     Store into byte or boolean array
     * Format:
     *     bastore
     * Forms:
     *     bastore = 84 (0x54)
     * Operand Stack:
     *     ..., arrayref, index, value →
     *     ...
     * Description:
     *     The arrayref must be of type reference and must refer to an array whose components are of type byte or of type boolean.
     *     The index and the value must both be of type int. The arrayref, index, and value are popped from the operand stack.
     *     The int value is truncated to a byte and stored as the component of the array indexed by index.
     * Run-time Exceptions:
     *     If arrayref is null, bastore throws a NullPointerException.
     *
     *     Otherwise, if index is not within the bounds of the array referenced by arrayref, the bastore instruction throws an ArrayIndexOutOfBoundsException.
     * Notes:
     *     The bastore instruction is used to store values into both byte and boolean arrays.
     *     In Oracle's Java Virtual Machine implementation, boolean arrays - that is, arrays of type T_BOOLEAN - are implemented as arrays of 8-bit values.
     *     Other implementations may implement packed boolean arrays; in such implementations the bastore instruction must be able to store boolean values into packed boolean arrays as well as byte values into byte arrays.
     */
    BASTORE((byte) 0x54, 0, 0, 3),

    /**
     * Operator:
     *     Push byte
     * Format:
     *     bipush
     *     byte
     * Forms:
     *     bipush = 16 (0x10)
     * Operand Stack:
     *     ... →
     *     ..., value
     * Description:
     *     The immediate byte is sign-extended to an int value. That value is pushed onto the operand stack.
     */
    BIPUSH((byte) 0x10, 1, 1, 0),

    BREAKPOINT((byte) 0xca, 0, 0, 0),

    /**
     * Operator:
     *     Load char from array
     * Format:
     *     caload
     * Forms:
     *     caload = 52 (0x34)
     * Operand Stack:
     *     ..., arrayref, index →
     *     ..., value
     * Description:
     *     The arrayref must be of type reference and must refer to an array whose components are of type char.
     *     The index must be of type int. Both arrayref and index are popped from the operand stack.
     *     The component of the array at index is retrieved and zero-extended to an int value. That value is pushed onto the operand stack.
     * Run-time Exceptions:
     *     If arrayref is null, caload throws a NullPointerException.
     *
     *     Otherwise, if index is not within the bounds of the array referenced by arrayref, the caload instruction throws an ArrayIndexOutOfBoundsException.
     */
    CALOAD((byte) 0x34, 0, 1, 2),

    /**
     * Operator:
     *     Store into char array
     * Format:
     *     castore
     * Forms:
     *     castore = 85 (0x55)
     * Operand Stack:
     *     ..., arrayref, index, value →
     *     ...
     * Description:
     *     The arrayref must be of type reference and must refer to an array whose components are of type char.
     *     The index and the value must both be of type int. The arrayref, index, and value are popped from the operand stack.
     *     The int value is truncated to a char and stored as the component of the array indexed by index.
     * Run-time Exceptions:
     *     If arrayref is null, castore throws a NullPointerException.
     *
     *     Otherwise, if index is not within the bounds of the array referenced by arrayref, the castore instruction throws an ArrayIndexOutOfBoundsException.
     */
    CASTORE((byte) 0x55, 0, 0, 3),

    /**
     * Operator:
     *     Check whether object is of given type
     * Format:
     *     checkcast
     *     indexbyte1
     *     indexbyte2
     * Forms:
     *     checkcast = 192 (0xc0)
     * Operand Stack:
     *     ..., objectref →
     *     ..., objectref
     * Description:
     *     The objectref must be of type reference.
     *     The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class, where the value of the index is (indexbyte1 << 8) | indexbyte2.
     *     The run-time constant pool item at the index must be a symbolic reference to a class, array, or interface type.
     *
     *     If objectref is null, then the operand stack is unchanged.
     *
     *     Otherwise, the named class, array, or interface type is resolved.
     *     If objectref can be cast to the resolved class, array, or interface type, the operand stack is unchanged; otherwise, the checkcast instruction throws a ClassCastException.
     *
     *     The following rules are used to determine whether an objectref that is not null can be cast to the resolved type:
     *     if S is the class of the object referred to by objectref and T is the resolved class, array, or interface type,
     *     checkcast determines whether objectref can be cast to type T as follows:
     *
     *         If S is an ordinary (nonarray) class, then:
     *             If T is a class type, then S must be the same class as T, or S must be a subclass of T;
     *             If T is an interface type, then S must implement interface T.
     *         If S is an interface type, then:
     *             If T is a class type, then T must be Object.
     *             If T is an interface type, then T must be the same interface as S or a superinterface of S.
     *         If S is a class representing the array type SC[], that is, an array of components of type SC, then:
     *             If T is a class type, then T must be Object.
     *             If T is an interface type, then T must be one of the interfaces implemented by arrays.
     *             If T is an array type TC[], that is, an array of components of type TC, then one of the following must be true:
     *                 TC and SC are the same primitive type.
     *                 TC and SC are reference types, and type SC can be cast to TC by recursive application of these rules.
     * Linking Exceptions:
     *     During resolution of the symbolic reference to the class, array, or interface type, any of the exceptions can be thrown.
     * Run-time Exception:
     *     Otherwise, if objectref cannot be cast to the resolved class, array, or interface type, the checkcast instruction throws a ClassCastException.
     * Notes:
     *     The checkcast instruction is very similar to the instanceof instruction.
     *     It differs in its treatment of null, its behavior when its test fails (checkcast throws an exception, instanceof pushes a result code), and its effect on the operand stack.
     */
    CHECKCAST((byte) 0xc0, 2, 1, 1),

    /**
     * Operator:
     *     Convert double to float
     * Format:
     *     d2f
     * Forms:
     *     d2f = 144 (0x90)
     * Operand Stack:
     *      ..., value →
     *      ..., result
     * Descriptor:
     *     The value on the top of the operand stack must be of type double.
     *     It is popped from the operand stack and undergoes value set conversion resulting in value'.
     *     Then value' is converted to a float result using IEEE 754 round to nearest mode. The result is pushed onto the operand stack.
     *
     *     Where an d2f instruction is FP-strict, the result of the conversion is always rounded to the nearest representable value in the float value set.
     *
     *     Where an d2f instruction is not FP-strict, the result of the conversion may be taken from the float-extended-exponent value set;
     *     it is not necessarily rounded to the nearest representable value in the float value set.
     *
     *     A finite value' too small to be represented as a float is converted to a zero of the same sign;
     *     a finite value' too large to be represented as a float is converted to an infinity of the same sign. A double NaN is converted to a float NaN.
     * Notes:
     *     The d2f instruction performs a narrowing primitive conversion.
     *     It may lose information about the overall magnitude of value' and may also lose precision.
     */
    D2F((byte) 0x90, 0, 1, 1),

    /**
     * Operator:
     *     Convert double to int
     * Format:
     *     d2i
     * Forms:
     *     d2i = 142 (0x8e)
     * Operand Stack:
     *     ..., value →
     *     ..., result
     * Description:
     *     The value on the top of the operand stack must be of type double.
     *     It is popped from the operand stack and undergoes value set conversion resulting in value'.
     *     Then value' is converted to an int. The result is pushed onto the operand stack:
     *
     *         If the value' is NaN, the result of the conversion is an int 0.
     *
     *         Otherwise, if the value' is not an infinity, it is rounded to an integer value V, rounding towards zero using IEEE 754 round towards zero mode.
     *         If this integer value V can be represented as an int, then the result is the int value V.
     *
     *         Otherwise, either the value' must be too small (a negative value of large magnitude or negative infinity),
     *         and the result is the smallest representable value of type int, or the value' must be too large (a positive value of large magnitude or positive infinity),
     *         and the result is the largest representable value of type int.
     * Notes:
     *     The d2i instruction performs a narrowing primitive conversion. It may lose information about the overall magnitude of value' and may also lose precision.
     */
    D2I((byte) 0x8e, 0, 1, 1),

    /**
     * Operator:
     *     Convert double to long
     * Format:
     *     d2l
     * Forms:
     *     d2l = 143 (0x8f)
     * Operand Stack:
     *     ..., value →
     *     ..., result
     * Description:
     *     The value on the top of the operand stack must be of type double.
     *     It is popped from the operand stack and undergoes value set conversion resulting in value'.
     *     Then value' is converted to a long. The result is pushed onto the operand stack:
     *
     *         If the value' is NaN, the result of the conversion is a long 0.
     *
     *         Otherwise, if the value' is not an infinity, it is rounded to an integer value V, rounding towards zero using IEEE 754 round towards zero mode.
     *         If this integer value V can be represented as a long, then the result is the long value V.
     *
     *         Otherwise, either the value' must be too small (a negative value of large magnitude or negative infinity),
     *         and the result is the smallest representable value of type long, or the value' must be too large (a positive value of large magnitude or positive infinity),
     *         and the result is the largest representable value of type long.
     * Notes:
     *     The d2l instruction performs a narrowing primitive conversion. It may lose information about the overall magnitude of value' and may also lose precision.
     */
    D2L((byte) 0x8f, 0, 1, 1),
    DADD((byte) 0x63, 0, 1, 2),
    DALOAD((byte) 0x31, 0, 1, 2),
    DASTORE((byte) 0x52, 0, 0, 3),
    DCMPG((byte) 0x98, 0, 1, 2),
    DCMPL((byte) 0x97, 0, 1, 2),
    DCONST_0((byte) 0x0e, 0, 1, 0),
    DCONST_1((byte) 0x0f, 0, 1, 0),
    DDIV((byte) 0x6f, 0, 1, 2),
    DLOAD((byte) 0x18, 1, 1, 0),
    DLOAD_0((byte) 0x26, 0, 1, 0),
    DLOAD_1((byte) 0x27, 0, 1, 0),
    DLOAD_2((byte) 0x28, 0, 1, 0),
    DLOAD_3((byte) 0x29, 0, 1, 0),
    DMUL((byte) 0x6b, 0, 1, 2),
    DNEG((byte) 0x77, 0, 1, 1),
    DREM((byte) 0x73, 0, 1, 2),
    DRETURN((byte) 0xaf, 0, 0, -1),
    DSTORE((byte) 0x39, 1, 0, 1),
    DSTORE_0((byte) 0x47, 0, 0, 1),
    DSTORE_1((byte) 0x48, 0, 0, 1),
    DSTORE_2((byte) 0x49, 0, 0, 1),
    DSTORE_3((byte) 0x4a, 0, 0, 1),
    DSUB((byte) 0x67, 0, 1, 2),
    DUP((byte) 0x59, 0, 2, 1),
    DUP_X1((byte) 0x5a, 0, 3, 2),
    DUP_X2((byte) 0x5b, 0, 4, 3),
    DUP2((byte) 0x5c, 0, 4, 2),
    DUP2_X1((byte) 0x5d, 0, 5, 3),
    DUP2_X2((byte) 0x5e, 0, 6, 4),
    F2D((byte) 0x8d, 0, 1, 1),
    F2I((byte) 0x8b, 0, 1, 1),
    F2L((byte) 0x8c, 0, 1, 1),
    FADD((byte) 0x62, 0, 1, 2),
    FALOAD((byte) 0x30, 0, 1, 2),
    FASTORE((byte) 0x51, 0, 0, 3),
    FCMPG((byte) 0x96, 0, 1, 2),
    FCMPL((byte) 0x95, 0, 1, 2),
    FCONST_0((byte) 0x0b, 0, 1, 0),
    FCONST_1((byte) 0x0c, 0, 1, 0),
    FCONST_2((byte) 0x0d, 0, 1, 0),
    FDIV((byte) 0x6e, 0, 1, 2),
    FLOAD((byte) 0x17, 1, 1, 0),
    FLOAD_0((byte) 0x22, 0, 1, 0),
    FLOAD_1((byte) 0x23, 0, 1, 0),
    FLOAD_2((byte) 0x24, 0, 1, 0),
    FLOAD_3((byte) 0x25, 0, 1, 0),
    FMUL((byte) 0x6a, 0, 1, 2),
    FNEG((byte) 0x76, 0, 1, 1),
    FREM((byte) 0x72, 0, 1, 2),
    FRETURN((byte) 0xae, 0, 0, -1),
    FSTORE((byte) 0x38, 1, 0, 1),
    FSTORE_0((byte) 0x43, 0, 0, 1),
    FSTORE_1((byte) 0x44, 0, 0, 1),
    FSTORE_2((byte) 0x45, 0, 0, 1),
    FSTORE_3((byte) 0x46, 0, 0, 1),
    FSUB((byte) 0x66, 0, 1, 2),
    GETFIELD((byte) 0xb4, 2, 1, 1),
    GETSTATIC((byte) 0xb2, 2, 1, 0),
    GOTO((byte) 0xa7, 2, 0, 0),
    GOTO_W((byte) 0xc8, 4, 0, 0),
    I2B((byte) 0x91, 0, 1, 1),
    I2C((byte) 0x92, 0, 1, 1),
    I2D((byte) 0x87, 0, 1, 1),
    I2F((byte) 0x86, 0, 1, 1),
    I2L((byte) 0x85, 0, 1, 1),
    I2S((byte) 0x93, 0, 1, 1),
    IADD((byte) 0x60, 0, 1, 2),
    IALOAD((byte) 0x2e, 0, 1, 2),
    IAND((byte) 0x7e, 0, 1, 2),
    IASTORE((byte) 0x4f, 0, 0, 3),
    ICONST_M1((byte) 0x2, 0, 1, 0),
    ICONST_0((byte) 0x3, 0, 1, 0),
    ICONST_1((byte) 0x4, 0, 1, 0),
    ICONST_2((byte) 0x5, 0, 1, 0),
    ICONST_3((byte) 0x6, 0, 1, 0),
    ICONST_4((byte) 0x7, 0, 1, 0),
    ICONST_5((byte) 0x8, 0, 1, 0),
    IDIV((byte) 0x6c, 0, 1, 2),
    IF_ACMPEQ((byte) 0xa5, 2, 0, 2),
    IF_ACMPNE((byte) 0xa6, 2, 0, 2),
    IF_ICMPEQ((byte) 0x9f, 2, 0, 2),
    IF_ICMPGE((byte) 0xa2, 2, 0, 2),
    IF_ICMPGT((byte) 0xa3, 2, 0, 2),
    IF_ICMPLE((byte) 0xa4, 2, 0, 2),
    IF_ICMPLT((byte) 0xa1, 2, 0, 2),
    IF_ICMPNE((byte) 0xa0, 2, 0, 2),
    IFEQ((byte) 0x99, 2, 0, 1),
    IFGE((byte) 0x9c, 2, 0, 1),
    IFGT((byte) 0x9d, 2, 0, 1),
    IFLE((byte) 0x9e, 2, 0, 1),
    IFLT((byte) 0x9b, 2, 0, 1),
    IFNE((byte) 0x9a, 2, 0, 1),
    IFNONNULL((byte) 0xc7, 2, 0, 1),
    IFNULL((byte) 0xc6, 2, 0, 1),
    IINC((byte) 0x84, 2, 0, 0),
    ILOAD((byte) 0x15, 1, 1, 0),
    ILOAD_0((byte) 0x1a, 0, 1, 0),
    ILOAD_1((byte) 0x1b, 0, 1, 0),
    ILOAD_2((byte) 0x1c, 0, 1, 0),
    ILOAD_3((byte) 0x1d, 0, 1, 0),
    IMPDEP1((byte) 0xfe, -1, -1, -1),
    IMPDEP2((byte) 0xff, -1, -1, -1),
    IMUL((byte) 0x68, 0, 1, 2),
    INEG((byte) 0x74, 0, 1, 1),
    INSTANCEOF((byte) 0xc1, 2, 1, 1),
    INVOKEDYNAMIC((byte) 0xba, 4, 0, -2),
    INVOKEINTERFACE((byte) 0xb9, 4, 0, -2),
    INVOKESPECIAL((byte) 0xb7, 2, 0, -2),
    INVOKESTATIC((byte) 0xb8, 2, 0, -2),
    INVOKEVIRTUAL((byte) 0xb6, 2, 0, -2),
    IOR((byte) 0x80, 0, 1, 2),
    IREM((byte) 0x70, 0, 1, 2),
    IRETURN((byte) 0xac, 0, 0, -1),
    ISHL((byte) 0x78, 0, 1, 2),
    ISHR((byte) 0x7a, 0, 1, 2),
    ISTORE((byte) 0x36, 1, 0, 1),
    ISTORE_0((byte) 0x3b, 0, 0, 1),
    ISTORE_1((byte) 0x3c, 0, 0, 1),
    ISTORE_2((byte) 0x3d, 0, 0, 1),
    ISTORE_3((byte) 0x3e, 0, 0, 1),
    ISUB((byte) 0x64, 0, 1, 2),
    IUSHR((byte) 0x7c, 0, 1, 2),
    IXOR((byte) 0x82, 0, 1, 2),
    JSR((byte) 0xa8, 2, 1, 0),
    JSR_W((byte) 0xc9, 4, 1, 0),
    L2D((byte) 0x8a, 0, 1, 1),
    L2F((byte) 0x89, 0, 1, 1),
    L2I((byte) 0x88, 0, 1, 1),
    LADD((byte) 0x61, 0, 1, 2),
    LALOAD((byte) 0x2f, 0, 1, 2),
    LAND((byte) 0x7f, 0, 1, 2),
    LASTORE((byte) 0x50, 0, 0, 3),
    LCMP((byte) 0x94, 0, 1, 2),
    LCONST_0((byte) 0x9, 0, 1, 0),
    LCONST_1((byte) 0x0a, 0, 1, 0),
    LDC((byte) 0x12, 1, 1, 0),
    LDC_W((byte) 0x13, 2, 1, 0),
    LDC2_W((byte) 0x14, 2, 1, 0),
    LDIV((byte) 0x6d, 0, 1, 2),
    LLOAD((byte) 0x16, 1, 1, 0),
    LLOAD_0((byte) 0x1e, 0, 1, 0),
    LLOAD_1((byte) 0x1f, 0, 1, 0),
    LLOAD_2((byte) 0x20, 0, 1, 0),
    LLOAD_3((byte) 0x21, 0, 1, 0),
    LMUL((byte) 0x69, 0, 1, 2),
    LNEG((byte) 0x75, 0, 1, 1),
    LOOKUPSWITCH((byte) 0xab, -1, 0, 1),
    LOR((byte) 0x81, 0, 1, 2),
    LREM((byte) 0x71, 0, 1, 2),
    LRETURN((byte) 0xad, 0, 0, -1),
    LSHL((byte) 0x79, 0, 1, 2),
    LSHR((byte) 0x7b, 0, 1, 2),
    LSTORE((byte) 0x37, 1, 0, 1),
    LSTORE_0((byte) 0x3f, 0, 0, 1),
    LSTORE_1((byte) 0x40, 0, 0, 1),
    LSTORE_2((byte) 0x41, 0, 0, 1),
    LSTORE_3((byte) 0x42, 0, 0, 1),
    LSUB((byte) 0x65, 0, 1, 2),
    LUSHR((byte) 0x7d, 0, 1, 2),
    LXOR((byte) 0x83, 0, 1, 2),
    MONITORENTER((byte) 0xc2, 0, 0, 1),
    MONITOREXIT((byte) 0xc3, 0, 0, 1),
    MULTIANEWARRAY((byte) 0xc5, 3, 1, -2),
    NEW((byte) 0xbb, 2, 1, 0),
    NEWARRAY((byte) 0xbc, 1, 1, 1),
    NOP((byte) 0x0, 0, 0, 0),
    POP((byte) 0x57, 0, 0, 1),
    POP2((byte) 0x58, 0, 0, 2),
    PUTFIELD((byte) 0xb5, 2, 0, 2),
    PUTSTATIC((byte) 0xb3, 2, 0, 1),
    RET((byte) 0xa9, 1, 0, 0),
    RETURN((byte) 0xb1, 0, 0, -1),
    SALOAD((byte) 0x35, 0, 1, 2),
    SASTORE((byte) 0x56, 0, 0, 3),
    SIPUSH((byte) 0x11, 2, 1, 0),
    SWAP((byte) 0x5f, 0, 2, 2),
    TABLESWITCH((byte) 0xaa, -1, 0, 1),
    WIDE((byte) 0xc4, 5, 0, 0);

    public final byte opCode;
    public final int parameters;
    public final int addOperandStack;
    public final int removeOperandStack;

    ASMInstruction(byte opCode, int parameters, int addOperandStack, int removeOperandStack) {
        this.opCode = opCode;
        this.parameters = parameters;
        this.addOperandStack = addOperandStack;
        this.removeOperandStack = removeOperandStack;
    }
}
