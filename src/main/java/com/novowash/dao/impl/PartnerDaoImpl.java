package com.novowash.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novowash.Enums.CommonEnums.STATUS;
import com.novowash.dao.PartnerDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.Partner;

@Repository
public class PartnerDaoImpl extends NovoJdbcTemplate implements PartnerDao {
	
	private static final Logger logger = Logger.getLogger(PartnerDaoImpl.class);
	
	private static final String ADD_PARTNER = "INSERT INTO partner(partner_name,email,contact_number,city,message,status,created_on,created_by) VALUES (?,?,?,?,?,?,now(),?)";

	@Override
	public void addPartner(Partner partner) {
		logger.info("PartnerDaoImpl:addPartner() Adding aprtner: " + partner.getEmail());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(ADD_PARTNER, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setString(index++, partner.getPartnerName());
				pstmt.setString(index++, partner.getEmail());
				pstmt.setString(index++, partner.getContactNumber());
				pstmt.setString(index++, partner.getCity());
				pstmt.setString(index++, partner.getMessage());
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				pstmt.setString(index++, partner.getEmail());
				return pstmt;
			}
		}, keyHolder);
		partner.setId(keyHolder.getKey().longValue());
	}

}
