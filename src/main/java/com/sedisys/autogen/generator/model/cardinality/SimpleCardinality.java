package com.sedisys.autogen.generator.model.cardinality;


public class SimpleCardinality implements MutableCardinality{
	private Integer min;
	private Integer max;

	public SimpleCardinality(){
		setMin(null);
		setMax(null);
	}

	@Override
	public Integer getMin(){
		return min;
	}

	@Override
	public void setMin(Integer min){
		this.min = min;
	}

	@Override
	public Integer getMax(){
		return max;
	}

	@Override
	public void setMax(Integer max){
		this.max = max;
	}

	@Override
	public boolean isPlural(){
		return getMax() == 1;
	}

	@Override
	public String toString(){
		Integer min = getMin();
		Integer max = getMax();

		StringBuilder returnBuilder = new StringBuilder();
		if (min!=null){
			if (min.equals(max)){
				return min.toString();
			} else if (min == 0){
				returnBuilder.append("*");
			} else{
				returnBuilder.append(min);
			}
		} else{
			returnBuilder.append("*");
		}
		if (max==null){
			if (min==null || min==0){
				return returnBuilder.toString();
			} else{
				returnBuilder.insert('[', 0);
				returnBuilder.append(',');
				returnBuilder.append('*');
				returnBuilder.append(']');
			}
		} else{
			returnBuilder.insert('[', 0);
			returnBuilder.append(',');
			returnBuilder.append(max);
			returnBuilder.append(']');
		}
		return returnBuilder.toString();
	}
}
