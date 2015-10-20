import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) 
	{
		int x = 0;
		int y = 0;
		int z = 0;
		int dropTables=1;
		int createTables=1;
		
		Object[] tempFilePath = getPaths();
		String[] filePath = cleanPaths(tempFilePath);
		String[] info = new String[35];
		String[] tempData = new String[50];

		String line = null;

		
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		int pk_origin_airport_id = 0;
		int origin_airport_seq_id = 0;
		int origin_city_market_id = 0;
		String origin_origin = null;
		String origin_city_name = null;
		String origin_state_abr = null;
		String origin_state_nm = null;
		String origin_state_fips = null;
		String origin_wac = null;
		
		int pk_dest_airport_id = 0;
		int dest_airport_seq_id = 0;
		int dest_city_market_id = 0;
		String dest_origin = null;
		String dest_city_name = null;
		String dest_state_abr = null;
		String dest_state_nm = null;
		String dest_state_fips = null;
		String dest_wac = null;
		
		String pk_airline_id = null;
		String unique_carrier = null;
		String unique_carrier_name = null;
		String carrier_name = null;
		String carrier_entity = null;
		String region = null;
		String carrier = null;
		int carrier_group = 0;
		int carrier_group_new = 0;
		
		float passengers = 0;
		float freight = 0;
		float mail = 0;
		float distance = 0;
		String airline_id = null;
		int origin_airport_id = 0;
		int dest_airport_id = 0;
		int year = 0;
		int quarter = 0;
		int month = 0;
		int distance_group = 0;
		String s_class = null;
		
		
		

		System.out.println("Running - Please be patient, may take several minutes...");
		for(x = 0; x < filePath.length-1; x++)
		{
			z = 0;
			if(filePath[x] != null)
			{
				try
				{
					br = new BufferedReader(new FileReader(filePath[x]));
					while((line = br.readLine()) != null)
					{
						if(z != 0)
						{
							if(y == 1)
							{

								fw = new FileWriter("Output/sqlScript1.sql");

								bw = new BufferedWriter(fw);

								if(dropTables == 1)
								{

									bw.write("DROP TABLE UAFLIGHTS.AIRPORTS;");
									bw.newLine();
									bw.write("DROP TABLE UAFLIGHTS.CARRIERS;");
									bw.newLine();
									bw.write("DROP TABLE UAFLIGHTS.FLIGHTS;");
									bw.newLine();
								}
								if(createTables==1)
								{
								bw.write("CREATE TABLE UAFLIGHTS.AIRPORTS("+
										 "AIRPORT_ID INT NOT NULL, " + 
										 "AIRPORT_SEQ_ID INT, " + 
										 "CITY_MARKET_ID INT, " + 
										 "ORIGIN VARCHAR(10), " + 
										 "CITY_NAME VARCHAR(50), " +
										 "STATE_ABR VARCHAR(10), " + 
										 "STATE_NM VARCHAR(50), " + 
										 "STATE_FIPS VARCHAR(10), " + 
										 "WAC VARCHAR(4), " +
										 "PRIMARY KEY(AIRPORT_ID));");
								bw.newLine();
								bw.write("CREATE TABLE UAFLIGHTS.CARRIERS(" +
                                         "AIRLINE_ID INT NOT NULL,        " +
                                         "UNIQUE_CARRIER VARCHAR(10),     " +
                                         "UNIQUE_CARRIER_NAME VARCHAR(50)," +
                                         "CARRIER_NAME VARCHAR(50),       " +
                                         "CARRIER_ENTITY VARCHAR(10),     " +
                                         "REGION VARCHAR(5),              " +
                                         "CARRIER VARCHAR(5),             " +
                                         "CARRIER_GROUP INT,              " +
                                         "CARRIER_GROUP_NEW INT,          " +
                                         "PRIMARY KEY(AIRLINE_ID)         " +
                                         ");");
								bw.newLine();
								bw.write("CREATE TABLE UAFLIGHTS.FLIGHTS (      " +
                                         "FLIGHT_ID INT NOT NULL AUTO_INCREMENT," +
                                         "PASSENGERS INT,                       " +
                                         "FREIGHT INT,                          " +
                                         "MAIL INT,                             " +
                                         "DISTANCE INT,                         " +
                                         "AIRLINE_ID INT,                       " +
                                         "ORIGIN_AIRPORT_ID INT,                " +
                                         "DEST_AIRPORT_ID INT,                  " +
                                         "YEAR INT,                             " +
                                         "QUARTER INT,                          " +
                                         "MONTH INT,                            " +
                                         "DISTANCE_GROUP INT,                   " +
                                         "CLASS VARCHAR(5),                     " +
                                         "PRIMARY KEY (FLIGHT_ID)               " +
                                         ");");
								bw.newLine();
								}
							}
							

							tempData = line.split(",");
							
							info = rebuildData(tempData);
							System.out.println(x + " " + z);
							



							if(info[4].equals(null))
							{
								info[4] = "No Data";
							}
							if(info[5].length() == 0)
							{
								info[5] = 0+"";
							}
							if(info[12].length() == 0)
							{
								info[12] = 0+"";
							}
							
							//System.out.println("pk_origin_airport_id     = " + info[13]);
							//System.out.println("origin_airport_seq_id    = " + info[14]);
							//System.out.println("origin_city_market_id    = " + info[15]);
							//System.out.println("origin_origin            = " + info[16]);
							//System.out.println("origin_city_name         = " + info[17]);
							//System.out.println("origin_state_abr         = " + info[18]);
							//System.out.println("origin_state_nm          = " + info[20]);
							//System.out.println("origin_state_fips        = " + info[19]);
							//System.out.println("origin_wac               = " + info[21]);
							//System.out.println("pk_dest_airport_id       = " + info[22]);
							//System.out.println("dest_airport_seq_id      = " + info[23]);
							//System.out.println("dest_city_market_id      = " + info[24]);
							//System.out.println("dest_origin              = " + info[25]);
							//System.out.println("dest_city_name           = " + info[26]);
							//System.out.println("dest_state_abr           = " + info[27]);
							//System.out.println("dest_state_nm            = " + info[29]);
							//System.out.println("dest_state_fips          = " + info[28]);
							//System.out.println("dest_wac                 = " + info[30]);
							//System.out.println("pk_airline_id            = " + info[5] );
							//System.out.println("unique_carrier           = " + info[4] );
							//System.out.println("unique_carrier_name      = " + info[6] );
							//System.out.println("carrier_entity           = " + info[7] );
							//System.out.println("region                   = " + info[8] );
							//System.out.println("carrier                  = " + info[9] );
							//System.out.println("carrier_name             = " + info[10]);
							//System.out.println("carrier_group            = " + info[11]);
							//System.out.println("carrier_group_new        = " + info[12]);
							//System.out.println("passengers               = " + info[0] );
							//System.out.println("freight                  = " + info[1] );
							//System.out.println("mail                     = " + info[2] );
							//System.out.println("distance                 = " + info[3] );
							//System.out.println("airline_id               = " + info[5] );
							//System.out.println("origin_airport_id        = " + info[13]);
							//System.out.println("dest_airport_id          = " + info[23]);
							//System.out.println("year                     = " + info[31]);
							//System.out.println("quarter                  = " + info[32]);
							//System.out.println("month                    = " + info[33]);
							//System.out.println("distance_group           = " + info[34]);
							//System.out.println("s_class                  = " + info[35]);
							
							
							pk_origin_airport_id     = Integer.parseInt(info[13]);
							origin_airport_seq_id    = Integer.parseInt(info[14]);
							origin_city_market_id    = Integer.parseInt(info[15]);
							origin_origin            = info[16];
							origin_city_name         = info[17];
							origin_state_abr         = info[18];
							origin_state_nm          = info[20];
							origin_state_fips        = info[19];
							origin_wac               = info[21];
							
							pk_dest_airport_id       = Integer.parseInt(info[22]);
							dest_airport_seq_id      = Integer.parseInt(info[23]);
							dest_city_market_id      = Integer.parseInt(info[24]);
							dest_origin              = info[25];
							dest_city_name           = info[26];
							dest_state_abr           = info[27];
							dest_state_nm            = info[29];
							dest_state_fips          = info[28];
							dest_wac                 = info[30];
							
							pk_airline_id            = info[5];
							unique_carrier           = info[4];
							unique_carrier_name      = info[6];
							carrier_entity           = info[7];
							region                   = info[8];
							carrier                  = info[9];
							carrier_name             = info[10];
							carrier_group            = Integer.parseInt(info[11]);
							carrier_group_new        = Integer.parseInt(info[12]);
							
							passengers               = Float.parseFloat(info[0]);
							freight                  = Float.parseFloat(info[1]);
							mail                     = Float.parseFloat(info[2]);
							distance                 = Float.parseFloat(info[3]);
							airline_id               = info[5];
							origin_airport_id        = Integer.parseInt(info[13]);
							dest_airport_id          = Integer.parseInt(info[23]);
							year                     = Integer.parseInt(info[31]);
							quarter                  = Integer.parseInt(info[32]);
							month                    = Integer.parseInt(info[33]);
							distance_group           = Integer.parseInt(info[34]);
							s_class                  = info[35];
							                  
							bw.write("INSERT IGNORE INTO UAFLIGHTS.AIRPORTS(" + 
									 "AIRPORT_ID" 				+ ", "+
									 "AIRPORT_SEQ_ID"			+ ", "+
									 "CITY_MARKET_ID"			+ ", "+
									 "ORIGIN"					+ ", "+
									 "CITY_NAME" 				+ ", "+
									 "STATE_ABR"				+ ", "+
									 "STATE_NM"					+ ", "+
									 "STATE_FIPS"				+ ", "+
									 "WAC"						+ ") "+
									 "VALUES('"					+
									 pk_origin_airport_id		+ "', '" +
									 origin_airport_seq_id  	+ "', '" +
									 origin_city_market_id		+ "', '" +
									 origin_origin				+ "', '" +
									 origin_city_name			+ "', '" +
									 origin_state_abr			+ "', '" +
									 origin_state_nm			+ "', '" +
									 origin_state_fips			+ "', '" +
									 origin_wac					+ "');");
							bw.newLine();
							
							bw.write("INSERT IGNORE INTO UAFLIGHTS.AIRPORTS(" + 
									 "AIRPORT_ID" 				+ ", "+
									 "AIRPORT_SEQ_ID"			+ ", "+
									 "CITY_MARKET_ID"			+ ", "+
									 "ORIGIN"					+ ", "+
									 "CITY_NAME" 				+ ", "+
									 "STATE_ABR"				+ ", "+
									 "STATE_NM"					+ ", "+
									 "STATE_FIPS"				+ ", "+
									 "WAC"						+ ") "+
									 "VALUES('"					+
									 pk_dest_airport_id			+ "', '" +
									 dest_airport_seq_id  		+ "', '" +
									 dest_city_market_id		+ "', '" +
									 dest_origin				+ "', '" +
									 dest_city_name				+ "', '" +
									 dest_state_abr				+ "', '" +
									 dest_state_nm				+ "', '" +
									 dest_state_fips			+ "', '" +
									 dest_wac					+ "');");
							bw.newLine();
							
							bw.write("INSERT IGNORE INTO UAFLIGHTS.CARRIERS( " +
									 "AIRLINE_ID " 				+ ", " +   //1
									 "UNIQUE_CARRIER"           + ", " +   //2
									 "UNIQUE_CARRIER_NAME"      + ", " +
									 "CARRIER_NAME"             + ", " +   //3
									 "CARRIER_ENTITY"           + ", " +   //4
									 "REGION"                   + ", " +   //5
									 "CARRIER"                  + ", " +   //6
									 "CARRIER_GROUP"            + ", " +   //7
									 "CARRIER_GROUP_NEW"        + ") " +
									 "VALUES ('" 				+          //
								     pk_airline_id              + "', '" + //1
							         unique_carrier             + "', '" + //2
							         unique_carrier_name        + "', '" + //3
							         carrier_name               + "', '" + //4
							         carrier_entity             + "', '" + //5
							         region                     + "', '" + //6
							         carrier                    + "', '" + //7
							         carrier_group              + "', '" + //8
							         carrier_group_new          + "');");  //9
							bw.newLine();
							
							bw.write("INSERT IGNORE INTO UAFLIGHTS.FLIGHTS( " +
							         "PASSENGERS"               + ", " +
									 "FREIGHT"                  + ", " +
									 "MAIL"                     + ", " +
									 "DISTANCE"                 + ", " +
									 "AIRLINE_ID"               + ", " +
									 "ORIGIN_AIRPORT_ID"        + ", " +
									 "DEST_AIRPORT_ID"          + ", " +
									 "YEAR"                     + ", " +
									 "QUARTER"                  + ", " +
									 "MONTH"                    + ", " +
									 "DISTANCE_GROUP"           + ", " +
									 "CLASS"                    + ") " +
									 "VALUES ('"                +
									 passengers                 + "', '" +
							         freight                    + "', '" +
							         mail                       + "', '" +
							         distance                   + "', '" +
							         airline_id                 + "', '" +
							         origin_airport_id          + "', '" +
							         dest_airport_id            + "', '" +
							         year                       + "', '" +
							         quarter                    + "', '" +
							         month                      + "', '" +
							         distance_group             + "', '" +
							         s_class                    + "');");
							bw.newLine();
							bw.write("-- " + y);
									
						}
						z++;
						y++;
					}
				}
				catch(FileNotFoundException e)
				{
					e.printStackTrace();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		try {
			bw.close();
			fw.close();
			System.out.println("FINISHED!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String[] rebuildData(String[] tempData)
	{
		int y = 0;
		int x = 0;
		int holder = 0;
		String temp = '"'+"";
		String temp2 = "'"+"";
		String[] finalData = new String[40];
		StringBuilder str = new StringBuilder();
		
		for(x = 0; x < tempData.length; x++)
		{
			if(tempData[x].contains(temp2) == true)
			{
				holder = tempData[x].indexOf("'");
				str = new StringBuilder(tempData[x]);
				str.insert(holder, "'");
				tempData[x] = str.toString();
			}
			if(tempData[x].startsWith(temp) == true)
			{
				if(tempData[x].endsWith(temp) == false)
				{
					finalData[y] = tempData[x] + "," + tempData[x+1];
					x++;
					if(tempData[x].endsWith(temp) == true)
					{
						y++;		
						
					}		
					else
					{
						finalData[y] = finalData[y] + "," + tempData[x+1];
						y++;
						x++;
					}
				}
				else
				{
					finalData[y] = tempData[x];
					y++;
				}
			}
			else
			{
				finalData[y] = tempData[x];
				y++;
			}
		}
		
		for(x = 0 ; x < finalData.length; x++)
		{
			if(finalData[x] != null)
			{
				finalData[x] = finalData[x].replaceAll(temp, "   ");
				finalData[x] = finalData[x].trim();
				
			}
		}
		
		return finalData;
	}
	
	private static Object[] getPaths()
	{
		Object[] tempFilePath = new Object[0];
		try {
		      tempFilePath = Files.walk(Paths.get("Resources")).toArray();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		return tempFilePath;
	}
	
	private static String[] cleanPaths(Object[] tempFilePath)
	{
		String[] filePath = new String[tempFilePath.length];
		
		int y = 0;
		
		
		for(int x = 0; x < tempFilePath.length ; x++)
		{
			if(tempFilePath[x].toString().endsWith(".csv"))
			{
				filePath[y] = tempFilePath[x].toString();
				y++;
			}
		}
		return filePath;
		
	}

}
