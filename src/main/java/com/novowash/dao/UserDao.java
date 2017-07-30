package com.novowash.dao;

import java.util.List;

import com.novowash.model.User;

public interface UserDao {

	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);

	User userLogin(String userName, String password);

	List<String> getUserRoles(Long userId);
}
