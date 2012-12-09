package com.mast.util;

public class RuntimeSettings {
	public static final String APPLICATION_NAME = "BookStore";
	public static final String VERSION_ID = " version 0.1"
			+ " dated October 28, 2012";
	public static final String SERVER_IP = "localhost";
	static String databaseName = "bookstore";
	static String dbUserID = "root";
	// "" for apple Macbook Air
	static String dbPassword = "root"; 
	public static String RUN_MODE = "Test"; // "Production"; //
	public static boolean IS_IN_DEBUG_MODE = true; // false; //
	static int portNo = 3306;// 5432; //
	public static String scriptSql[] = {
			// " drop database if exists bookstore;",
			// " create database bookstore;",
			" use bookstore;",
			"DROP TABLE IF EXISTS books;",
			"CREATE TABLE  books ("
					+ "id int(10) unsigned NOT NULL AUTO_INCREMENT,"
					+ "title varchar(45) NOT NULL,"
					+ "author varchar(45) NOT NULL,"
					+ "description varchar(500) default '',"
					+ "price int(5) NOT NULL,"
					+ "image_url varchar(100) default '',"
					+ "quantity int(5) default 0,"
					+ "updated_at datetime,"
					+ "created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (`id`));",
			"insert into books (title, author, description, price, " +
			"image_url, quantity) " +
			" values('Head First Servlets & JSP', 'Bert Bates', " +
			"'text book', 400, 'hf_servlets_and_jsp.jpg', 0); "
	    };

}
