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
    AALOAD((byte) 0x32),

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
    AASTORE((byte) 0x53),

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
    ACONST_NULL((byte) 0x1),

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
    ALOAD((byte) 0x19),

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
    ALOAD_0((byte) 0x2a),

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
    ALOAD_1((byte) 0x2b),

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
    ALOAD_2((byte) 0x2c),

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
    ALOAD_3((byte) 0x2d),

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
    ANEWARRAY((byte) 0xbd),

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
    ARETURN((byte) 0xb0),

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
    ARRAYLENGTH((byte) 0xbe),

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
    ASTORE((byte) 0x3a),

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
    ASTORE_0((byte) 0x4b),

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
    ASTORE_1((byte) 0x4c),

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
    ASTORE_2((byte) 0x4d),

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
    ASTORE_3((byte) 0x4e),

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
    ATHROW((byte) 0xbf),

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
    BALOAD((byte) 0x33),

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
    BASTORE((byte) 0x54),

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
    BIPUSH((byte) 0x10),

    BREAKPOINT((byte) 0xca),

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
    CALOAD((byte) 0x34),

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
    CASTORE((byte) 0x55),

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
    CHECKCAST((byte) 0xc0),

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
    D2F((byte) 0x90),

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
    D2I((byte) 0x8e),

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
    D2L((byte) 0x8f),
    DADD((byte) 0x63),
    DALOAD((byte) 0x31),
    DASTORE((byte) 0x52),
    DCMPG((byte) 0x98),
    DCMPL((byte) 0x97),
    DCONST_0((byte) 0x0e),
    DCONST_1((byte) 0x0f),
    DDIV((byte) 0x6f),
    DLOAD((byte) 0x18),
    DLOAD_0((byte) 0x26),
    DLOAD_1((byte) 0x27),
    DLOAD_2((byte) 0x28),
    DLOAD_3((byte) 0x29),
    DMUL((byte) 0x6b),
    DNEG((byte) 0x77),
    DREM((byte) 0x73),
    DRETURN((byte) 0xaf),
    DSTORE((byte) 0x39),
    DSTORE_0((byte) 0x47),
    DSTORE_1((byte) 0x48),
    DSTORE_2((byte) 0x49),
    DSTORE_3((byte) 0x4a),
    DSUB((byte) 0x67),
    DUP((byte) 0x59),
    DUP_X1((byte) 0x5a),
    DUP_X2((byte) 0x5b),
    DUP2((byte) 0x5c),
    DUP2_X1((byte) 0x5d),
    DUP2_X2((byte) 0x5e),
    F2D((byte) 0x8d),
    F2I((byte) 0x8b),
    F2L((byte) 0x8c),
    FADD((byte) 0x62),
    FALOAD((byte) 0x30),
    FASTORE((byte) 0x51),
    FCMPG((byte) 0x96),
    FCMPL((byte) 0x95),
    FCONST_0((byte) 0x0b),
    FCONST_1((byte) 0x0c),
    FCONST_2((byte) 0x0d),
    FDIV((byte) 0x6e),
    FLOAD((byte) 0x17),
    FLOAD_0((byte) 0x22),
    FLOAD_1((byte) 0x23),
    FLOAD_2((byte) 0x24),
    FLOAD_3((byte) 0x25),
    FMUL((byte) 0x6a),
    FNEG((byte) 0x76),
    FREM((byte) 0x72),
    FRETURN((byte) 0xae),
    FSTORE((byte) 0x38),
    FSTORE_0((byte) 0x43),
    FSTORE_1((byte) 0x44),
    FSTORE_2((byte) 0x45),
    FSTORE_3((byte) 0x46),
    FSUB((byte) 0x66),
    GETFIELD((byte) 0xb4),
    GETSTATIC((byte) 0xb2),
    GOTO((byte) 0xa7),
    GOTO_W((byte) 0xc8),
    I2B((byte) 0x91),
    I2C((byte) 0x92),
    I2D((byte) 0x87),
    I2F((byte) 0x86),
    I2L((byte) 0x85),
    I2S((byte) 0x93),
    IADD((byte) 0x60),
    IALOAD((byte) 0x2e),
    IAND((byte) 0x7e),
    IASTORE((byte) 0x4f),
    ICONST_M1((byte) 0x2),
    ICONST_0((byte) 0x3),
    ICONST_1((byte) 0x4),
    ICONST_2((byte) 0x5),
    ICONST_3((byte) 0x6),
    ICONST_4((byte) 0x7),
    ICONST_5((byte) 0x8),
    IDIV((byte) 0x6c),
    IF_ACMPEQ((byte) 0xa5),
    IF_ACMPNE((byte) 0xa6),
    IF_ICMPEQ((byte) 0x9f),
    IF_ICMPGE((byte) 0xa2),
    IF_ICMPGT((byte) 0xa3),
    IF_ICMPLE((byte) 0xa4),
    IF_ICMPLT((byte) 0xa1),
    IF_ICMPNE((byte) 0xa0),
    IFEQ((byte) 0x99),
    IFGE((byte) 0x9c),
    IFGT((byte) 0x9d),
    IFLE((byte) 0x9e),
    IFLT((byte) 0x9b),
    IFNE((byte) 0x9a),
    IFNONNULL((byte) 0xc7),
    IFNULL((byte) 0xc6),
    IINC((byte) 0x84),
    ILOAD((byte) 0x15),
    ILOAD_0((byte) 0x1a),
    ILOAD_1((byte) 0x1b),
    ILOAD_2((byte) 0x1c),
    ILOAD_3((byte) 0x1d),
    IMPDEP1((byte) 0xfe),
    IMPDEP2((byte) 0xff),
    IMUL((byte) 0x68),
    INEG((byte) 0x74),
    INSTANCEOF((byte) 0xc1),
    INVOKEDYNAMIC((byte) 0xba),
    INVOKEINTERFACE((byte) 0xb9),
    INVOKESPECIAL((byte) 0xb7),
    INVOKESTATIC((byte) 0xb8),
    INVOKEVIRTUAL((byte) 0xb6),
    IOR((byte) 0x80),
    IREM((byte) 0x70),
    IRETURN((byte) 0xac),
    ISHL((byte) 0x78),
    ISHR((byte) 0x7a),
    ISTORE((byte) 0x36),
    ISTORE_0((byte) 0x3b),
    ISTORE_1((byte) 0x3c),
    ISTORE_2((byte) 0x3d),
    ISTORE_3((byte) 0x3e),
    ISUB((byte) 0x64),
    IUSHR((byte) 0x7c),
    IXOR((byte) 0x82),
    JSR((byte) 0xa8),
    JSR_W((byte) 0xc9),
    L2D((byte) 0x8a),
    L2F((byte) 0x89),
    L2I((byte) 0x88),
    LADD((byte) 0x61),
    LALOAD((byte) 0x2f),
    LAND((byte) 0x7f),
    LASTORE((byte) 0x50),
    LCMP((byte) 0x94),
    LCONST_0((byte) 0x9),
    LCONST_1((byte) 0x0a),
    LDC((byte) 0x12),
    LDC_W((byte) 0x13),
    LDC2_W((byte) 0x14),
    LDIV((byte) 0x6d),
    LLOAD((byte) 0x16),
    LLOAD_0((byte) 0x1e),
    LLOAD_1((byte) 0x1f),
    LLOAD_2((byte) 0x20),
    LLOAD_3((byte) 0x21),
    LMUL((byte) 0x69),
    LNEG((byte) 0x75),
    LOOKUPSWITCH((byte) 0xab),
    LOR((byte) 0x81),
    LREM((byte) 0x71),
    LRETURN((byte) 0xad),
    LSHL((byte) 0x79),
    LSHR((byte) 0x7b),
    LSTORE((byte) 0x37),
    LSTORE_0((byte) 0x3f),
    LSTORE_1((byte) 0x40),
    LSTORE_2((byte) 0x41),
    LSTORE_3((byte) 0x42),
    LSUB((byte) 0x65),
    LUSHR((byte) 0x7d),
    LXOR((byte) 0x83),
    MONITORENTER((byte) 0xc2),
    MONITOREXIT((byte) 0xc3),
    MULTIANEWARRAY((byte) 0xc5),
    NEW((byte) 0xbb),
    NEWARRAY((byte) 0xbc),
    NOP((byte) 0x0),
    POP((byte) 0x57),
    POP2((byte) 0x58),
    PUTFIELD((byte) 0xb5),
    PUTSTATIC((byte) 0xb3),
    RET((byte) 0xa9),
    RETURN((byte) 0xb1),
    SALOAD((byte) 0x35),
    SASTORE((byte) 0x56),
    SIPUSH((byte) 0x11),
    SWAP((byte) 0x5f),
    TABLESWITCH((byte) 0xaa),
    WIDE((byte) 0xc4);

    private final byte opCode;

    ASMInstruction(byte opCode) {
        this.opCode = opCode;
    }

    public byte getOpCode() {
        return this.opCode;
    }
}
