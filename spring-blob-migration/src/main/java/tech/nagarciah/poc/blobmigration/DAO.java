package tech.nagarciah.poc.blobmigration;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class DAO {
	JdbcTemplate jdbcTemplate;


	public byte[] read(int id) {
		byte[] serializedData = getJdbcTemplate().queryForObject(
				"select template from tbl_bio_biom_data_pers where id = ?", byte[].class,
				id);

		// Sólo en caso de que se requiera convertir a un objeto intermedio, se puede usar
		// org.apache.commons.lang.SerializationUtils para serializar/deserializar el byte[]
		//
		//double[][] data = (double[][]) SerializationUtils.deserialize(serializedData);
		
		return serializedData;
	}

	public void write(int id, byte[] data) {
		// Sólo en caso de que se requiera convertir a un objeto intermedio, se puede usar
		// org.apache.commons.lang.SerializationUtils(Object data) para serializar/deserializar el byte[]
		//
		//byte[] serializedData = SerializationUtils.serialize(data);
		byte[] serializedData = data;

		getJdbcTemplate().update(
				"update tbl_bio_biom_data_pers set template = ? where id = ?", 
				serializedData, id);
		//getJdbcTemplate().getDataSource().getConnection().commit();
	}
	
	public List<Integer> getIDs(){
		return getJdbcTemplate().queryForList("Select ID from tbl_bio_biom_data_pers where ID is not null", Integer.class);
	}
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
