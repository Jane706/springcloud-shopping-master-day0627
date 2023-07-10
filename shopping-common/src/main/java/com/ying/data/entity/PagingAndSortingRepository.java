package com.ying.data.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author：YingJiang
 * @Date:2023 07 02 18:40
 * @Description:
 */

@NoRepositoryBean
public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID>
{
	Iterable<T> findAll(Sort var1);

	Page<T> findAll(Pageable var1);
}
