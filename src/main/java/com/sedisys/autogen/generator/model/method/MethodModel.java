package com.sedisys.autogen.generator.model.method;

import java.util.List;

import com.sedisys.autogen.generator.model.Named;
import com.sedisys.autogen.generator.model.field.AccessLeveled;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.annotation.Annotated;
import com.sedisys.autogen.generator.model.type.References;
import com.sedisys.autogen.generator.model.type.Typed;

public interface MethodModel extends Annotated, Typed, AccessLeveled, Named, References{

	public List<FieldModel> getParameters();

	public String getMethodBody();
}
