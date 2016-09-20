package ca.mcgill.ecse321.soccerscorekeeping.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

public class XStreamPersistence
{
	private static XStream xstream = new XStream();
	private static String filename = "/home/2010/jselwy/public_html/soccer.xml";
	private static String filename2 = "/home/2010/jselwy/public_html/soccerscores.xml";
	
	public static boolean saveToXMLwithXStream(Object obj)
	{
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); //save our xml file
		
		try
		{
			FileWriter writer = new FileWriter(filename);
			FileWriter writer2 = new FileWriter(filename2);
			writer.write(xml);
			writer2.write(xml);
			writer.close();
			writer2.close();
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static Object loadFromXMLwithXStream()
	{
		xstream.setMode(XStream.ID_REFERENCES);
		try
		{
			FileReader fileReader = new FileReader(filename);//load our xml file
			return xstream.fromXML(fileReader);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setAlias(String xmlTagName, Class<?> className)
	{
		xstream.alias(xmlTagName, className);
	}
	
	public static void setFilename(String fn)
	{
		filename=fn;
	}
}