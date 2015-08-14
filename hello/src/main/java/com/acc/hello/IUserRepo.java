package com.acc.hello;
import org.springframework.data.repository.CrudRepository;

interface IUserRepo extends CrudRepository<User, Integer>{

}
