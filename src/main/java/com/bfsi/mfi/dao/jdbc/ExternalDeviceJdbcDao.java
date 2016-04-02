package com.bfsi.mfi.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.bfsi.mfi.dao.ExternalexternalDeviceDao;
import com.bfsi.mfi.entity.ExternalDevice;
import com.bfsi.mfi.query.ApplicationQuery;

@Repository("externalexternalDeviceDao")
public class ExternalDeviceJdbcDao extends MaintenanceJdbcDao<ExternalDevice> implements
        ExternalexternalDeviceDao {
	
    private static String version = null;
	private static int version_no = 0;
	private static String success = "success";

	
	@Override
	protected String getInsertQuery() {
		return ApplicationQuery.EXT_DEV_QUERY_INSERT;
	}

	@Override
	protected RowMapper<ExternalDevice> getRowMapper() {
		return new DeviceMapper();
	}

	@Override
	protected String getSelectAllQuery() {
		return ApplicationQuery.EXT_DEV_QUERY_SELECT_ALL;
	}

	@Override
	protected String getUpdateQuery() {
		return ApplicationQuery.EXT_DEV_QUERY_UPDATE;
	}

	@Override
	protected String getDeleteQuery() {
		return ApplicationQuery.EXT_DEV_QUERY_DELETE;
	}

	@Override
	protected String getSelectByPkQuery() {
		return ApplicationQuery.EXT_DEV_QUERY_SELECT_BY_PK;
	}

	@Override
	protected String getUpdateByAuthQuery() {
		return ApplicationQuery.EXT_DEV_QUERY_UPDATE_BY_AUTH;
	}

	@Override
	public List<ExternalDevice> getDevicesForAllocation() {
		Map<String, String> params = new HashMap<String, String>();
		params.put(null, null);
		return jdbcTemplate
				.query(ApplicationQuery.EXT_DEV_QUERY_DEVICE_ID_ALLOC, params, getRowMapper());
	}

	@Override
	public List<ExternalDevice> getAllocatedDevices(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		return jdbcTemplate
				.query(ApplicationQuery.EXT_DEV_QUERY_ALLOC_DEVICES, params, getRowMapper());
	}
	
	public RowMapper<String> getDeviceKeyRowMapper() {
		return new DeviceIDRowMapper();
	}

	@Override
	public String getAutoDeviceId() {
		/*Map<String, String> params = new HashMap<String, String>();
		params.put(null, null);
		return jdbcTemplate.queryForObject(ApplicationQuery.EXT_DEV_QUERY_DEVICE_AUTO_ID, params,
				new DeviceIDRowMapper());*/
		String autoGenid = getAutoGenFunCreateId(15); //Pass Your module code.
		return autoGenid;

	}

	private static final class DeviceIDRowMapper implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExternalDevice device = new ExternalDevice();
			String deviceAutoID = rs.getString("EXTERNAL_DEVICEID");
			String setID = null;
			String value = "00001";
			String codes = "EXD";

			if (deviceAutoID == null || deviceAutoID.equals("")) {
				setID = codes + value;
				device.setId(setID);

			} else {
				String code = deviceAutoID.substring(0, 3);
				String ActualId = deviceAutoID.substring(3, 8);
				int ActualIdValue = Integer.parseInt(ActualId);
				int one = 1;
				int incrementId = ActualIdValue + one;
				String incrementIdValue = Integer.toString(incrementId);

				if (incrementIdValue.length() == 1) {
					setID = code + "0000" + incrementIdValue;
					device.setId(setID);
				} else if (incrementIdValue.length() == 2) {
					setID = code + "000" + incrementIdValue;
					device.setId(setID);
				} else if (incrementIdValue.length() == 3) {
					setID = code + "00" + incrementIdValue;
					device.setId(setID);
				} else if (incrementIdValue.length() == 4) {
					setID = code + "0" + incrementIdValue;
					device.setId(setID);
				} else {
					setID = code + incrementIdValue;
					device.setId(setID);
				}
			}
			return setID;
		}
	}

	private static final class DeviceMapper implements RowMapper<ExternalDevice> {

		public ExternalDevice mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExternalDevice device = new ExternalDevice();
			
			device.setId(rs.getString("EXTERNAL_DEVICEID"));
			device.setMacId(rs.getString("MAC_ID"));  
			device.setManufacturer(rs.getString("MANUFACTURER"));   
			device.setIssuedDate(BaseJdbcDao.getUtilDate(rs.getDate("ISSUED_DATE")));
			device.setIssuedByCo(rs.getString("ISSUED_BY_CO"));
			device.setModelNo(rs.getString("MODEL_NO")); 
			device.setAuthStatus(rs.getString("AUTH_STATUS"));
			device.setIsActive(rs.getString("IS_ACTIVE"));
			device.setLicenceFile(rs.getBytes("LICENCE_FILE"));
			device.setLicenceFileName(rs.getString("LICENCE_FILE_NAME"));
			
			return device;
		}
	}
		
	@Override
	public boolean isUniqueIdAvailable(String macId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("macId", macId);
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(ApplicationQuery.EXT_DEV_QUERY_UNIQUE_ID_AVAILABLE,
				params);
		return !rowset.next();// true if there is no next, false if there is
								// next => role with given roleName
	}
	
	//For ModifyUniqueID
	@Override
	public boolean isModifyUniqueIdAvailable(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(ApplicationQuery.EXT_DEV_QUERY_MODIFY_UNIQUE_ID_AVAILABLE,
				params);
		return !rowset.next();// true if there is no next, false if there is
								// next => role with given roleName
	}
	
	private static final class VersionNoRowMapper implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			String version_no=rs.getString(1);
			return version_no;
		}
	}

	
	public RowMapper<String> getVersionNoRowMapper() {
		return new VersionNoRowMapper();
	}


	@Override
	public ExternalDevice getDeviceDetail(String uniqueId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uniqueId", uniqueId);
		return jdbcTemplate.queryForObject(ApplicationQuery.EXT_DEV_QUERY_DEVICE_DETAIL, params,
				new DeviceMapper());
	}
	// For delete Operation
	
	@Override
	public String deleteDevice(String id) 
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		version= jdbcTemplate.queryForObject(ApplicationQuery.EXT_DEV_QUERY_AUDIT_VERSION, params,new VersionNoRowMapper());
		
		if (version == null){
			version_no = 0;
		}
		if (version != null){
			version_no = Integer.parseInt(version + 0);
		}
		if (version_no<=1)
		{
			jdbcTemplate.execute(ApplicationQuery.EXT_DEV_QUERY_DELETE, params,new PreparedStatementCallback<Object>() {
				@Override
				public Object doInPreparedStatement(PreparedStatement stmt)	throws SQLException, DataAccessException 
				{
					return stmt.executeUpdate();
				}
			});
			jdbcTemplate.execute(ApplicationQuery.EXT_DEV_QUERY_DELETE_AUDIT, params,new PreparedStatementCallback<Object>() {
				@Override
				public Object doInPreparedStatement(PreparedStatement stmt)	throws SQLException, DataAccessException 
				{
					return stmt.executeUpdate();
				}
			});
		}
		return success;
	}

}
