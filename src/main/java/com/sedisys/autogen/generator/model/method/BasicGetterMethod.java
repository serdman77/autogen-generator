package com.sedisys.autogen.generator.model.method;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class BasicGetterMethod extends SimpleMethodModel {

	private FieldModel field;

	public BasicGetterMethod(){
	}

	public BasicGetterMethod(FieldModel field){
		this.field = field;
	}

	public FieldModel getField(){
		return field;
	}

	public void setField(FieldModel field){
		this.field = field;
	}

	@Override
	public TypeModel getType(){
		return getField().getType();
	}

	@Override
	public AccessLevel getAccessLevel(){
		return AccessLevel.PUBLIC;
	}

	@Override
	public String getName(){
		return "get" + StringUtils.capitalize(getField().getName());
	}

	@Override
	public List<FieldModel> getParameters(){
		List<FieldModel> returnParameters = new ArrayList<>();
		return returnParameters;
	}

	@Override
	public String getMethodBody(){
		return "return " + getField().getName() + ';';
	}
}
