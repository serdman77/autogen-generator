package com.sedisys.autogen.generator.model.field;

import com.sedisys.autogen.generator.model.domain.association.AssociationCardinality;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleAbstractEntityFieldModel extends SimpleFieldModel implements EntityFieldModel{
	private boolean entityField = false;
	private boolean bidirectional = false;
	private boolean owner = false;
	private AssociationCardinality associationCardinality = null;
	private String inverseFieldName = null;

	public SimpleAbstractEntityFieldModel(){
	}

	public SimpleAbstractEntityFieldModel(FieldModel baseModel, TypeModel type){
		super(baseModel, type);
		if (baseModel instanceof EntityFieldModel){
			EntityFieldModel baseEntityModel = (EntityFieldModel) baseModel;
			setEntityField(baseEntityModel.isEntityField());
			setBidirectional(baseEntityModel.isBidirectional());
			setAssociationCardinality(baseEntityModel.getAssociationCardinality());
			setInverseFieldName(baseEntityModel.getInverseFieldName());
		}
	}

	public SimpleAbstractEntityFieldModel(TypeModel type, String name){
		this();
		setType(type);
		setName(name);
	}

	public SimpleAbstractEntityFieldModel(String qualifiedType, String name){
		this(new QualifiedNameTypeModel(qualifiedType), name);
	}

	@Override
	public boolean isEntityField(){
		return entityField;
	}

	public void setEntityField(boolean entityField){
		this.entityField = entityField;
		if (!entityField){
			setBidirectional(false);
		}
	}

	@Override
	public boolean isBidirectional(){
		return bidirectional;
	}

	public void setBidirectional(boolean bidirectional){
		this.bidirectional = bidirectional;
	}

	@Override
	public boolean isOwner(){
		return owner;
	}

	public void setOwner(boolean owner){
		this.owner = owner;
	}

	@Override
	public AssociationCardinality getAssociationCardinality(){
		return associationCardinality;
	}

	public void setAssociationCardinality(AssociationCardinality associationCardinality){
		this.associationCardinality = associationCardinality;
	}

	@Override
	public String getInverseFieldName(){
		return inverseFieldName;
	}

	public void setInverseFieldName(String inverseFieldName){
		this.inverseFieldName = inverseFieldName;
	}
}
