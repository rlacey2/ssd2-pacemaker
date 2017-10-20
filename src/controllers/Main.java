package controllers;

import static models.Fixtures.activities;
import static models.Fixtures.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Activity;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main // tdd02
{
  public static void main(String[] args) throws IOException
  {    
	   File  datastore = new File("datastore4.xml");
	    Serializer serializer = new XMLSerializer(datastore);

	    PacemakerAPI pacemakerAPI = new PacemakerAPI(serializer);
	    if (datastore.isFile())
	    {
	      try {
			pacemakerAPI.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }

	    pacemakerAPI.createUser("Bart", "Simpson",   "bart@simpson.com", "secret");
	    pacemakerAPI.createUser("Homer", "Simpson",  "homer@simpson.com", "secret");
	    pacemakerAPI.createUser("Lisa", "Simpson", " lisa@simpson.com", "secret");

	    Collection<User> users = pacemakerAPI.getUsers();
	    System.out.println(users);

	    User homer = pacemakerAPI.getUserByEmail("homer@simpson.com");
	    pacemakerAPI.createActivity(homer.id, "walk", "tramore", 88888);

	    try {
			pacemakerAPI.store();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	    
  }
}


/* ttd01  
package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.User;
import utils.FileLogger;

public class Main // tdd01
{
	  public static void main(String[] args) throws IOException
	  {
		  
		
	    FileLogger logger = FileLogger.getLogger();
	    logger.log("Creating user list");

	    List<User> users = new ArrayList<User>();
	    users.add(new User("Bart", "Simpson", "bart@simpson.com", "secret"));
	    users.add(new User("Homer", "Simpson", "bart@simpson.com", "secret"));
	    users.add(new User("Lisa", "Simpson", "bart@simpson.com", "secret"));
	    System.out.println(users);

	    logger.log("Serializing contacts to XML");
	    XStream xstream = new XStream(new DomDriver());
	    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
	    out.writeObject(users);
	    out.close();    

	    logger.log("Finished - shutting down");
	    
	  }
}

*/ 