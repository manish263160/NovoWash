package com.novowash.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novowash.Enums.STATUS;
import com.novowash.dao.UserServicesDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.Service;

@Repository
public class UserServicesDaoImpl extends NovoJdbcTemplate implements UserServicesDao {
	
	private static final String GET_ALL_SERVICES = "select * from service_m where status = ?";
	
	@Override
	public List<Service> getAllServices() {
		return  getJdbcTemplate().query(GET_ALL_SERVICES, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID);
	}

}
