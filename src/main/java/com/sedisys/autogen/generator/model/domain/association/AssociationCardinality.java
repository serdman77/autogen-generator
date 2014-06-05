package com.sedisys.autogen.generator.model.domain.association;

import com.sedisys.autogen.generator.model.cardinality.Cardinality;

public interface AssociationCardinality {
	public Cardinality getBaseCardinality();

	public Cardinality getInverseCardinality();

	public AssociationType getAssociationType();

	public static enum AssociationType {
		ONE_TO_ONE, ONE_TO_MANY, MANY_TO_ONE, MANY_TO_MANY;
	}
}
