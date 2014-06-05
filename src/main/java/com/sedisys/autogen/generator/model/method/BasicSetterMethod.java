package com.sedisys.autogen.generator.model.method;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class BasicSetterMethod extends SimpleMethodModel {

	private FieldModel field;

	public BasicSetterMethod(){
	}

	public BasicSetterMethod(FieldModel field){
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
		return null;
	}

	@Override
	public AccessLevel getAccessLevel(){
		return AccessLevel.PUBLIC;
	}

	@Override
	public String getName(){
		return "set" + StringUtils.capitalize(getField().getName());
	}

	@Override
	public List<FieldModel> getParameters(){
		List<FieldModel> returnParameters = new ArrayList<>();
		returnParameters.add(getField());
		return returnParameters;
	}

	@Override
	public String getMethodBody(){
		return "this." + getField().getName() + " = " + getField().getName() + ';';
	}
}
