package com.otpp.batchJob.domain.mappers;


import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class DefaultMapper<DomainClass, DatabaseClass> {
    private final MapperFactory defaultMapperFactory;

    @Autowired
    public DefaultMapper(MapperFactory mapperFactory) {
        this.defaultMapperFactory = mapperFactory;
    }

    public List<DomainClass> mapDbToDomain(List<DatabaseClass> dbObjects) {
        return (List<DomainClass>) getMapperFacade().mapAsList(dbObjects, domainClass());
    }

    public DomainClass mapDbToDomain(DatabaseClass dbObject) {
        return (DomainClass) getMapperFacade().map(dbObject, domainClass());
    }

    public List<DatabaseClass> mapDomainToDb(List<DomainClass> domainObjects) {
        return (List<DatabaseClass>) getMapperFacade().mapAsList(domainObjects, dbClass());
    }

    public DatabaseClass mapDomainToDb(DomainClass domainObject) {
        return (DatabaseClass) getMapperFacade().map(domainObject, dbClass());
    }

    /*
     * Subclasses that need to change the mapper factory configuration can override this method.
     * Common use cases for overriding are when most of the fields are copied by default but there are some exceptions,
     * mappers can still use the default mapper but need to specify additionally the mappings for the exceptional fields.
     */
    protected void configureMapperFactory() {
        getDefaultMapperFactory().classMap(dbClass(), domainClass()).byDefault().register();
    }

    protected MapperFactory getDefaultMapperFactory() {
        return defaultMapperFactory;
    }

    protected abstract Class<DatabaseClass> dbClass();

    protected abstract Class<DomainClass> domainClass();

    private MapperFacade getMapperFacade() {
        return getDefaultMapperFactory().getMapperFacade();
    }
}
