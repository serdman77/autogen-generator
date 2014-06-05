package com.sedisys.autogen.generator.model.java.type;

import com.sedisys.autogen.generator.model.type.TypeModel;

public interface MutableClassTypeModel extends ClassTypeModel {

	public TypeModel getGeneric(int index);

	public boolean containsGeneric(TypeModel generic);

	public void addGeneric(TypeModel generic);

	public void insertGeneric(int insertAt, TypeModel generic);

	public void replaceGeneric(int replaceAt, TypeModel generic);

	public TypeModel removeGeneric(int removeAt);

	public void removeGeneric(TypeModel generic);
}
