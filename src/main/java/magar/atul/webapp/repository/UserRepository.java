package magar.atul.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import magar.atul.webapp.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByName(String name);
}
