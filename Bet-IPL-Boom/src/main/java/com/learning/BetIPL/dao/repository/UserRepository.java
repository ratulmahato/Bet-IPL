package com.learning.BetIPL.dao.repository;


import org.springframework.stereotype.Repository;

import com.learning.BetIPL.dao.model.User;
import com.learning.BetIPL.framework.entity.BaseJpaRepository;


@Repository
public class UserRepository extends BaseJpaRepository<User, Long>  {

}
	