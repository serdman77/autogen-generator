package com.sedisys.autogen.generator.model.field;

public interface AccessLeveled {
	public AccessLevel getAccessLevel();

	public enum AccessLevel{
		PUBLIC, PROTECTED, PACKAGE, PRIVATE
	}
}
