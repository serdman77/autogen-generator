package com.sedisys.autogen.generator.model.domain.association;

import com.sedisys.autogen.generator.model.cardinality.Cardinality;
import com.sedisys.autogen.generator.model.cardinality.MutableCardinality;
import com.sedisys.autogen.generator.model.cardinality.SimpleCardinality;

public class SimpleManyToOneAssociationCardinality implements AssociationCardinality {
	private MutableCardinality baseCardinality;
	private Cardinality inverseCardinality;

	public SimpleManyToOneAssociationCardinality(){
		baseCardinality = new SimpleCardinality();
		baseCardinality.setMin(0);
		baseCardinality.setMax(null);

		SimpleCardinality inverseCardinality = new SimpleCardinality();
		inverseCardinality.setMin(1);
		inverseCardinality.setMax(1);
		this.inverseCardinality = inverseCardinality;
	}

	@Override
	public MutableCardinality getBaseCardinality(){
		return baseCardinality;
	}

	@Override
	public Cardinality getInverseCardinality(){
		return inverseCardinality;
	}

	@Override
	public AssociationType getAssociationType(){
		return AssociationType.MANY_TO_ONE;
	}
}
