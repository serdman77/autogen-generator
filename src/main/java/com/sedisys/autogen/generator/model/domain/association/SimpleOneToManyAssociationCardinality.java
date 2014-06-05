package com.sedisys.autogen.generator.model.domain.association;

import com.sedisys.autogen.generator.model.cardinality.Cardinality;
import com.sedisys.autogen.generator.model.cardinality.MutableCardinality;
import com.sedisys.autogen.generator.model.cardinality.SimpleCardinality;

public class SimpleOneToManyAssociationCardinality implements AssociationCardinality {
	private Cardinality baseCardinality;
	private MutableCardinality inverseCardinality;

	public SimpleOneToManyAssociationCardinality(){
		SimpleCardinality baseCardinality = new SimpleCardinality();
		baseCardinality.setMin(1);
		baseCardinality.setMax(1);
		this.baseCardinality = baseCardinality;

		inverseCardinality = new SimpleCardinality();
		inverseCardinality.setMin(0);
		inverseCardinality.setMax(null);
	}

	@Override
	public Cardinality getBaseCardinality(){
		return baseCardinality;
	}

	@Override
	public MutableCardinality getInverseCardinality(){
		return inverseCardinality;
	}

	@Override
	public AssociationType getAssociationType(){
		return AssociationType.ONE_TO_MANY;
	}

}
