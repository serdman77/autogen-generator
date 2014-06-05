package com.sedisys.autogen.generator.model.domain.field;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.constraint.SimpleConstraint;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.field.SimpleAbstractEntityFieldModel;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleDomainFieldModel extends SimpleAbstractEntityFieldModel implements DomainFieldModel{
	private List<Constraint> constraints;

	public SimpleDomainFieldModel(){
		constraints = new ArrayList<>();
	}

	public SimpleDomainFieldModel(FieldModel baseModel, TypeModel type){
		super(baseModel, type);
		constraints = new ArrayList<>();
		if (baseModel instanceof DomainFieldModel){
			DomainFieldModel baseDomainModel = (DomainFieldModel) baseModel;
			for (Constraint constraint : baseDomainModel.getConstraints()){
				addConstraint(new SimpleConstraint(constraint));
			}
		}
	}

	public SimpleDomainFieldModel(TypeModel type, String name){
		this();
		setType(type);
		setName(name);
	}

	public SimpleDomainFieldModel(String qualifiedType, String name){
		this(new QualifiedNameTypeModel(qualifiedType), name);
	}

	@Override
	public List<Constraint> getConstraints(){
		return constraints;
	}

	public void setConstraints(List<Constraint> constraints){
		this.constraints = constraints;
	}

	public Constraint getConstraint(int index){
		return getConstraints().get(index);
	}

	public boolean containsConstraint(Constraint constraint){
		return getConstraints().contains(constraint);
	}

	public void addConstraint(Constraint constraint){
		getConstraints().add(constraint);
	}

	public void insertConstraint(int insertAt, Constraint constraint){
		getConstraints().add(insertAt, constraint);
	}

	public void replaceConstraint(int replaceAt, Constraint constraint){
		getConstraints().set(replaceAt, constraint);
	}

	public Constraint removeConstraint(int removeAt){
		return getConstraints().remove(removeAt);
	}

	public void removeConstraint(Constraint constraint){
		getConstraints().remove(constraint);
	}

	public int getConstraintCount(){
		return getConstraints().size();
	}
}
