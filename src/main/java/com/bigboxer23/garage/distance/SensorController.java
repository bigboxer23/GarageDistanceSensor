package com.bigboxer23.garage.distance;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller to return data about sensor's input
 */
@RestController
@EnableAutoConfiguration
public class SensorController
{
	private static Logger myLogger = Logger.getLogger("com.bigboxer23");

	/**
	 * Get the distance to the object in front of sensor, by calling python script
	 *
	 * @return
	 */
	@RequestMapping("/Distance")
	private String getDistance()
	{
		String aResult = "";
		try
		{
			myLogger.config("Reading value from sensor");
			Process aProcess = Runtime.getRuntime().exec("/home/pi/usonic.py");
			BufferedReader aReader = new BufferedReader(new InputStreamReader(aProcess.getInputStream()));
			String aLine = null;
			while ((aLine = aReader.readLine()) != null)
			{
				myLogger.config("reading line: " + aLine);
				aResult += aLine;
			}
			myLogger.config("done reading value from sensor...");
		}
		catch (Exception theException)
		{
			myLogger.log(Level.WARNING, "getDistance:", theException);
			return null;
		}
		return aResult;
		//return "201";
	}
}
