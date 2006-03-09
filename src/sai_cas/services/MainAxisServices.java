package sai_cas.services;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import sai_cas.XMLCatalog;
import sai_cas.db.*;

public class MainAxisServices {
	static Logger logger = Logger.getLogger("sai_cas.MainAxisServices");
	
	/**
	 * 
	 * @param uriCatalog  -- The URI of the catalogue
	 * @throws Exception
	 * @return void
	 */
	
	public static void insertCatalogfromURI(URI uriCatalog) throws Exception
	{
		
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(uriCatalog);
			xmlc.insertDataToDB(dbi);
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
			
	}
	
	/**
	 *
	 * @param catalogString -- The whole catalogue as a string
	 * @throws Exception
	 * @return void
	 */
	public static void insertCatalog(String catalogString) throws Exception
	{
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(catalogString);
			xmlc.insertDataToDB(dbi);
			dbi.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally 
		{
			DBInterface.close(dbi, conn);
		}
	}
	
	/**
	 * 
	 * @return String[] -- the array of catalogues in the system
	 * @throws Exception
	 */
	public static String[] getCatalogNames() throws Exception
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getCatalogNames();
		}
		catch(SQLException e)
		{}
		finally
		{
			DBInterface.close(dbi,conn);
		}
		return result;
	}
	/**
	 * 
	 * @param catalogName
	 * @return the array of tables in the catalogue
	 * @throws Exception
	 */
	public static String[] getTableNames(String catalogName) throws Exception
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getTableNames(catalogName);
		}
		catch(SQLException e)
		{
			
		}
		finally
		{
			DBInterface.close(dbi,conn);
		}
		return result;
	}
	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return the array of string pairs (indexName, indexDefinition) for give catalog and table
	 * @throws Exception
	 */
	public static String[][] getIndexes(String catalogName, String tableName) throws Exception
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[][] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getIndexes(catalogName,tableName);
		}
		catch(SQLException e)
		{
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}	
}
