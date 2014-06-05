package com.sedisys.autogen.generator.model.java.annotation;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

import com.sedisys.autogen.generator.model.java.converter.annotation.AnnotationAnnotationModel;
import com.sedisys.autogen.generator.model.type.AbstractTypeModel;

public abstract class AbstractAnnotationModel extends AbstractTypeModel implements AnnotationModel {

	@Override
	public abstract Map<String, Object> getAttributes();

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getDisplayAttributes(){
		Map<String, Object> baseAttributes = getAttributes();
		Map<String, String> returnAttributes = new LinkedHashMap<>();
		for (String key : baseAttributes.keySet()){
			Object value = baseAttributes.get(key);
			if (value == null){
				returnAttributes.put(key, "null");
			} else{
				if (value instanceof String){
					returnAttributes.put(key, '"' + value.toString() + '"');
				} else if (value instanceof Class){
					returnAttributes.put(key, ((Class<? extends Object>) value).getCanonicalName());
				} else if (value instanceof Annotation || value instanceof AnnotationModel){
					AnnotationModel model;
					if (value instanceof Annotation){
						model = new AnnotationAnnotationModel((Annotation) value);
					} else{
						model = (AnnotationModel) value;
					}
					StringBuilder builder = new StringBuilder();
					builder.append("@").append(getSimpleAnnotationName());
					Map<String, String> displayAttributes = model.getDisplayAttributes();
					if (displayAttributes.size()>0){
						builder.append("(");
						for (String modelKey : displayAttributes.keySet()){
							builder.append(modelKey).append(" = ").append(displayAttributes.get(modelKey)).append(',');
						}
						builder.setLength(builder.length() - 1);
						builder.append(")");
					}
				} else{
					returnAttributes.put(key, value.toString());
				}
			}
		}
		return returnAttributes;
	}

}
