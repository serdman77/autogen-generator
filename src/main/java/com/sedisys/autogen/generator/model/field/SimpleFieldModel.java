package com.sedisys.autogen.generator.model.field;

import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleFieldModel implements FieldModel {

	private TypeModel type;
	private AccessLevel accessLevel;
	private String name;
	private List<Constraint> constraints;

	public SimpleFieldModel(){
	}

	public SimpleFieldModel(FieldModel baseModel, TypeModel type){
		this();
		setType(type);
		setAccessLevel(baseModel.getAccessLevel());
		setName(baseModel.getName());
	}

	public SimpleFieldModel(TypeModel type, String name){
		this();
		setType(type);
		setName(name);
	}

	public SimpleFieldModel(String qualifiedType, String name){
		this(new QualifiedNameTypeModel(qualifiedType), name);
	}

	public void setType(TypeModel type){
		this.type = type;
	}

	@Override
	public TypeModel getType(){
		return type;
	}

	public void setAccessLevel(AccessLevel accessLevel){
		this.accessLevel = accessLevel;
	}

	@Override
	public AccessLevel getAccessLevel(){
		return accessLevel;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String getName(){
		return name;
	}

	public void setConstraints(List<Constraint> constraints){
		this.constraints = constraints;
	}

	@Override
	public List<Constraint> getConstraints(){
		return constraints;
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		return getType().getReferencedTypes();
	}
}
