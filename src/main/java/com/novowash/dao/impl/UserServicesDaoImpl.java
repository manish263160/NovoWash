package com.novowash.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novowash.Enums.CommonEnums;
import com.novowash.Enums.CommonEnums.STATUS;
import com.novowash.dao.UserServicesDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.Service;
import com.novowash.model.ServiceCost;

@Repository
public class UserServicesDaoImpl extends NovoJdbcTemplate implements UserServicesDao {

	private static final String GET_ALL_SERVICES = "select * from service_m where status = ?";

	private static final String GET_SERVICES_COST = "select * from service_cost where status = ? and service_id=? order by price";

	@Override
	public List<Service> getAllServices() {
		return getJdbcTemplate().query(GET_ALL_SERVICES, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID);
	}

	@Override
	public List<ServiceCost> getServicesCostById(long serviceId) {
		return getJdbcTemplate().query(GET_SERVICES_COST, new BeanPropertyRowMapper<ServiceCost>(ServiceCost.class),
				new Object[] { CommonEnums.STATUS.ACTIVE.ID, serviceId });
	}

}
