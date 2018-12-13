package com.magicstory.dao;

import com.magicstory.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brandon
 * create on 2018-12-13
 * desc:
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findUserByName(String name);

}
