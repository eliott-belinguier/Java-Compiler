package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.attribute.Attribute;
import fr.belinguier.java.compiler.builder.util.ConstantRegisterUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Eliott Belinguier
 */
public class AttributeManager {

    private final Map<String, Attribute> attributes;

    public AttributeManager() {
        this.attributes = new HashMap<String, Attribute>();
    }

    public Attribute[] getConstantPools() {
        return this.attributes.values().toArray(new Attribute[this.attributes.size()]);
    }

    public boolean register(String name, Attribute attribute) {
        if (name == null || attribute == null)
            return false;
        this.attributes.put(name, attribute);
        return true;
    }

    public boolean remove(String name) {
        if (name == null)
            return false;
        return this.attributes.remove(name) != null;
    }

    public Attribute get(String name) {
        if (name == null)
            return null;
        return this.attributes.get(name);
    }

    public boolean registerAll(ConstantManager constantManager, Set<Attribute> attributes) {
        Attribute attribute;

        if (constantManager == null || attributes == null)
            return false;
        for (Map.Entry<String, Attribute> entry : this.attributes.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                attribute = entry.getValue();
                attribute.nameIndex = ConstantRegisterUtil.registerUtf8Constant(constantManager, entry.getKey());
                if (attribute.nameIndex == 0)
                    return false;
            }
        }
        if (this.attributes.size() < 1)
            return true;
        return attributes.addAll(this.attributes.values());
    }
    
    public void clear() {
        this.attributes.clear();
    }
}
