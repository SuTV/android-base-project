package com.planday.biz.factory;

import com.planday.core.repository.IRepository;

/**
 * Created by Su on 12/29/2015.
 */

public class RepositoryFactory {
    public static <TEntity, TRepository extends IRepository<TEntity>> TRepository getRepository(Class<TRepository> repository) {
        try {
            return repository.newInstance();
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e){

        }

        return null;
    }
}
