package com.some;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryDAO {

	@Autowired
	DataSource dataSource;
	
	public int insert(AliceInWords aliceInWords){
		try {
			PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("insert into repository(word,meaning) values(?,?)");
			preparedStatement.setString(1, aliceInWords.getWord());
			preparedStatement.setString(2, aliceInWords.getMeaning());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	
	public List<AliceInWords> getAll(){
		List<AliceInWords> theList = new ArrayList<AliceInWords>();
			try {
				PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("select word,meaning from repository");
				ResultSet resultSet =  preparedStatement.executeQuery();
				while(resultSet.next()){
					AliceInWords aliceInWords = new AliceInWords();
					aliceInWords.setWord(resultSet.getString(1));
					aliceInWords.setMeaning(resultSet.getString(2));
					theList.add(aliceInWords);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return theList;
	}
	
	
	public String getMeaning(String word){
		String meaning = "Error!! Meaning not found";
			try {
				PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("select meaning from repository where word=?");
				preparedStatement.setString(1, word);
				ResultSet resultSet =  preparedStatement.executeQuery();
				while(resultSet.next()){
					meaning = resultSet.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return meaning;
	}
	
	
	public String remove(String word){

		String meaning = "Number of rows deleted = ";
		int deletedRows=0;
			try {
				PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("delete from repository where word=?");
				preparedStatement.setString(1, word);
				deletedRows =  preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return meaning+deletedRows;
	
	}
	
	
}
