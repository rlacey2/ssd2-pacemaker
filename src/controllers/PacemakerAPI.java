package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;

public class PacemakerAPI
{
 // private List <User> users = new ArrayList<User>();
 // private Map<String, User> users = new HashMap<String, User>();
 // private Map<String, User> users = new HashMap<>();

	  private Map<Long, User>     userIndex       = new HashMap<>();
	  private Map<String, User>   emailIndex      = new HashMap<>();	
	
	
  public Collection<User> getUsers ()
  {
    return userIndex.values();
  }

  public  void deleteUsers() 
  {
	  userIndex.clear();
	  emailIndex.clear();
  }

  public User createUser(String firstName, String lastName, String email, String password) 
  {
    User user = new User (firstName, lastName, email, password);
    userIndex.put(user.id, user);
    emailIndex.put(email, user);
    return user;
  }

  public User getUserByEmail(String email) 
  {
    return emailIndex.get(email);
  }

  public User getUser(Long id) 
  {
    return userIndex.get(id);
  }
  
  public void deleteUser(Long id) 
  {
    User user = userIndex.remove(id);
    emailIndex.remove(user.email);
  }
  
  
  
} 