package com.sedisys.autogen.generator.model.domain.association;

import com.sedisys.autogen.generator.model.cardinality.MutableCardinality;
import com.sedisys.autogen.generator.model.cardinality.SimpleCardinality;

public class SimpleManyToManyAssociationCardinality implements AssociationCardinality {
	private MutableCardinality baseCardinality;
	private MutableCardinality inverseCardinality;

	public SimpleManyToManyAssociationCardinality(){
		baseCardinality = new SimpleCardinality();
		baseCardinality.setMin(0);
		baseCardinality.setMax(null);

		inverseCardinality = new SimpleCardinality();
		inverseCardinality.setMin(0);
		inverseCardinality.setMax(null);
	}

	@Override
	public MutableCardinality getBaseCardinality(){
		return baseCardinality;
	}

	@Override
	public MutableCardinality getInverseCardinality(){
		return inverseCardinality;
	}

	@Override
	public AssociationType getAssociationType(){
		return AssociationType.MANY_TO_ONE;
	}

}
