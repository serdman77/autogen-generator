package com.sedisys.autogen.generator.model.java.clazz.service;

import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.java.clazz.repository.RepositoryModel;

public class SimpleRepositoriedServiceModel extends SimpleServiceModel implements RepositoriedServiceModel{

	private RepositoryModel repositoryModel;

	@Override
	public DomainModel getDomainModel(){
		return getRepositoryModel().getDomainModel();
	}

	@Override
	public RepositoryModel getRepositoryModel(){
		return repositoryModel;
	}

	public void setRepositoryModel(RepositoryModel repositoryModel){
		this.repositoryModel = repositoryModel;
	}
}
