package com.sedisys.autogen.generator.model.field;

import com.sedisys.autogen.generator.model.domain.association.AssociationCardinality;

public interface EntityFieldModel extends FieldModel{
	public boolean isEntityField();

	public boolean isBidirectional();

	public boolean isOwner();

	public AssociationCardinality getAssociationCardinality();

	public String getInverseFieldName();
}
