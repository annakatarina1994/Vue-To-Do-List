package com.techelevator.city;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCityDAO implements CityDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCityDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(City newCity) {
		String sqlInsertCity = "INSERT INTO city(id, name, countrycode, district, population) " +
							   "VALUES(?, ?, ?, ?, ?)";
		newCity.setId(getNextCityId());
		jdbcTemplate.update(sqlInsertCity, newCity.getId(),
										  newCity.getName(),
										  newCity.getCountryCode(),
										  newCity.getDistrict(),
										  newCity.getPopulation());
	}
	
	@Override
	public City findCityById(long id) {
		City theCity = null;
		String sqlFindCityById = "SELECT id, name, countrycode, district, population FROM city WHERE id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityById, id);
		
		// let's call a helper method called mapRowToCity 
		if (results.next()) {
			theCity = mapRowToCity(results);  //"converts" the psql row to a java object called theCity
		}

		return theCity;
	}

	@Override
	public List<City> findCityByCountryCode(String countryCode) {
		ArrayList<City> cities = new ArrayList<>();
		String sqlFindcityByCountryCode =  "SELECT id, name, countrycode, district, population FROM city WHERE countryCode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindcityByCountryCode, countryCode);
		
		while(results.next()) {
			City theCity = mapRowToCity(results);
			cities.add(theCity);
		}
	
		return cities;
	}

	@Override
	public List<City> findCityByDistrict(String district) {
		ArrayList<City> cities = new ArrayList<>();
		String sqlFindcityByCountryCode =  "SELECT id, name, countrycode, district, population FROM city WHERE district = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindcityByCountryCode, district);
		
		while(results.next()) {
			City theCity = mapRowToCity(results);
			cities.add(theCity);
		}
	
		return cities;
	}

	@Override
	public void update(City city) {
		String sql = "UPDATE city SET name = ?, countrycode = ?, district = ?, population = ? WHERE id = ?";
		
		jdbcTemplate.update(sql, city.getName(), city.getCountryCode(), city.getDistrict(), city.getPopulation(), city.getId() );
		
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM city WHERE id = ?", id);
		
	}

	private long getNextCityId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_city_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}

	private City mapRowToCity(SqlRowSet results) {
		// map the sql columns (results.getXX()) to the POJO (Java object)
		City theCity;  // creates empty city object
		theCity = new City();  
		theCity.setId(results.getLong("id"));  //maps from the psql id column to the city object id attribute
		theCity.setName(results.getString("name"));
		theCity.setCountryCode(results.getString("countrycode"));
		theCity.setDistrict(results.getString("district"));
		theCity.setPopulation(results.getInt("population"));
		return theCity;
	}
}
