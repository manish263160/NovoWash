package com.novowash.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novowash.Enums.CommonEnums;
import com.novowash.Enums.CommonEnums.STATUS;
import com.novowash.dao.UserServicesDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.Service;
import com.novowash.model.ServiceCategory;
import com.novowash.model.ServiceCost;
import com.novowash.model.ServiceEnquire;

@Repository
public class UserServicesDaoImpl extends NovoJdbcTemplate implements UserServicesDao {

	private static final Logger logger = Logger.getLogger(UserServicesDaoImpl.class);
	
	private static final String GET_ALL_SERVICE_CAT = "select * from service_cat_m where status = ?";

	private static final String GET_ALL_SERVICES = "select * from service_m where status = ?";

	private static final String GET_SERVICES_COST = "select * from service_cost where status = ? and service_id=? order by price";

	private static final String BOOK_SERVICE_SQL = "insert into service_enquire(service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by) "
			+ "						values(?,?,?,?,?,?,?,?,?,?,now(),?)";
	
	@Override
	public List<ServiceCategory> getAllServiceCategories() {
		return getJdbcTemplate().query(GET_ALL_SERVICE_CAT, new BeanPropertyRowMapper<ServiceCategory>(ServiceCategory.class), STATUS.ACTIVE.ID);
	}
	
	@Override
	public List<Service> getAllServicesByCatId(long categoryId) {
		String sql = GET_ALL_SERVICES + " and service_cat_id = ?";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID, categoryId);
	}

	@Override
	public List<Service> getAllServices() {
		return getJdbcTemplate().query(GET_ALL_SERVICES, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID);
	}

	@Override
	public List<ServiceCost> getServicesCostById(long serviceId) {
		return getJdbcTemplate().query(GET_SERVICES_COST, new BeanPropertyRowMapper<ServiceCost>(ServiceCost.class),
				new Object[] { CommonEnums.STATUS.ACTIVE.ID, serviceId });
	}

	@Override
	public void bookOrEnquireService(ServiceEnquire enquire) {
		logger.info("UserServicesDaoImpl:bookOrEnquireService() Booking/equiring service for email: " + enquire.getEmail());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(BOOK_SERVICE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				// service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by
				pstmt.setLong(index++, enquire.getServiceId());
				pstmt.setLong(index++, enquire.getServiceCostId());
				if (null != enquire.getServiceDate()) {
					pstmt.setTimestamp(index++, new Timestamp(enquire.getServiceDate().getTime()));
				} else {
					pstmt.setNull(index++, Types.TIMESTAMP);

				}
				pstmt.setString(index++, enquire.getHouse());
				pstmt.setString(index++, enquire.getLandmark());
				pstmt.setString(index++, enquire.getLocality());
				pstmt.setString(index++, enquire.getName());
				pstmt.setString(index++, enquire.getPhone());
				pstmt.setString(index++, enquire.getEmail());
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				pstmt.setString(index++, enquire.getEmail());

				return pstmt;
			}
		}, keyHolder);
		enquire.setId(keyHolder.getKey().longValue());
	}

}
