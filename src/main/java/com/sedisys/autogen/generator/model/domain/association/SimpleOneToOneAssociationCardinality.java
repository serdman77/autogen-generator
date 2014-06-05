package com.sedisys.autogen.generator.model.domain.association;

import com.sedisys.autogen.generator.model.cardinality.Cardinality;
import com.sedisys.autogen.generator.model.cardinality.SimpleCardinality;

public class SimpleOneToOneAssociationCardinality implements AssociationCardinality {
	private Cardinality baseCardinality;
	private Cardinality inverseCardinality;

	public SimpleOneToOneAssociationCardinality(){
		SimpleCardinality baseCardinality = new SimpleCardinality();
		baseCardinality.setMin(1);
		baseCardinality.setMax(1);
		this.baseCardinality = baseCardinality;

		SimpleCardinality inverseCardinality = new SimpleCardinality();
		inverseCardinality.setMin(1);
		inverseCardinality.setMax(1);
		this.inverseCardinality = inverseCardinality;
	}

	@Override
	public Cardinality getBaseCardinality(){
		return baseCardinality;
	}

	@Override
	public Cardinality getInverseCardinality(){
		return inverseCardinality;
	}

	@Override
	public AssociationType getAssociationType(){
		return AssociationType.ONE_TO_ONE;
	}

}
