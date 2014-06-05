package com.sedisys.autogen.generator.model.type;

import java.util.HashSet;
import java.util.Set;


public abstract class AbstractTypeModel implements TypeModel {

	@Override
	public Set<TypeModel> getContainedTypeReferences(){
		Set<TypeModel> referencedTypes = new HashSet<>();
		for (TypeModel containedType : getContainedTypes()){
			referencedTypes.add(containedType);
			referencedTypes.addAll(containedType.getContainedTypeReferences());
		}
		return referencedTypes;
	}

	@Override
	public boolean equals(Object compareTo){
		if (compareTo instanceof TypeModel){
			return getQualifiedName().equals(((TypeModel) compareTo).getQualifiedName());
		}
		return false;
	}

	@Override
	public int hashCode(){
		return getQualifiedName().hashCode();
	}
}
