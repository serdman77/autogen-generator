package com.sedisys.autogen.generator.model.java.field;

import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;

public interface MutableAnnotationsClassFieldModel extends ClassFieldModel{

	public AnnotationModel getAnnotation(int index);

	public boolean containsAnnotation(AnnotationModel annotation);

	public void addAnnotation(AnnotationModel annotation);

	public void insertAnnotation(int insertAt, AnnotationModel annotation);

	public void replaceAnnotation(int replaceAt, AnnotationModel annotation);

	public AnnotationModel removeAnnotation(int removeAt);

	public void removeAnnotation(AnnotationModel annotation);

	public int getAnnotationCount();
}
