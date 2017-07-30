package com.novowash.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novowash.dao.UserDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.User;

@Repository
public class UserDaoImpl extends NovoJdbcTemplate implements UserDao{
	private Logger logger = Logger.getLogger(UserDaoImpl.class);

	private static final String GET_USER = "select * from user where email = ? and password = ?";

	@Override
	public User findById(long id) {

		return null;
	}

	@Override
	public User findByName(String name) {

		return null;
	}

	@Override
	public void saveUser(User user) {

	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public void deleteUserById(long id) {

	}

	@Override
	public List<User> findAllUsers() {

		return null;
	}

	@Override
	public void deleteAllUsers() {

	}

	@Override
	public boolean isUserExist(User user) {

		return false;
	}

	@Override
	public User userLogin(String userName, String password) {
		logger.info("Start login");
		User user = null;
		try {
			user = getJdbcTemplate().queryForObject(GET_USER, new BeanPropertyRowMapper<User>(User.class), userName, password);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No user found" + e);
		} catch (DataAccessException e) {
			logger.error("No user found" + e);
		}
		return user;
	}

	@Override
	public List<String> getUserRoles(Long userId) {

		return null;
	}

}
