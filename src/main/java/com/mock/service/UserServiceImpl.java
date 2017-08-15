package com.mock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.mock.model.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<UserVO> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<UserVO> findAllUsers() {
		return users;
	}
	
	public UserVO findById(long id) {
		for(UserVO user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public UserVO findByName(String name) {
		for(UserVO user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(UserVO user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(UserVO user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<UserVO> iterator = users.iterator(); iterator.hasNext(); ) {
		    UserVO user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(UserVO user) {
		return findByName(user.getName())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<UserVO> populateDummyUsers(){
		List<UserVO> users = new ArrayList<UserVO>();
		users.add(new UserVO(counter.incrementAndGet(),"Sam",30, 70000));
		users.add(new UserVO(counter.incrementAndGet(),"Tom",40, 50000));
		users.add(new UserVO(counter.incrementAndGet(),"Jerome",45, 30000));
		users.add(new UserVO(counter.incrementAndGet(),"Silvia",50, 40000));
		return users;
	}

}
