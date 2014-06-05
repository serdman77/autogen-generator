package com.sedisys.autogen.generator.model.cardinality;

public interface Cardinality {
	public Integer getMin();

	public Integer getMax();

	public boolean isPlural();
}
