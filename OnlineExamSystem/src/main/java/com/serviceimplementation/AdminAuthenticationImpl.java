package com.serviceimplementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.DataNotFoundedException;
import com.entity.Admin;
import com.repository.AdminRepository;
import com.service.AdminAuthentication;

@Service
public class AdminAuthenticationImpl implements AdminAuthentication{

	@Autowired
	AdminRepository adminRepository;
	
	protected boolean isLogin = false;
	
	@PostConstruct
	public void regAdmin()
	{
		long c = adminRepository.count();
		
		if(c == 0)
		{
		Admin a = new Admin();
		a.setUserName("admin");
		a.setPassword("admin");
		adminRepository.save(a);
		}
		return;
	}
	
	@Override
	public String adminLogin(Admin admin) throws DataNotFoundedException {
		regAdmin();
		List<Admin> a1 = adminRepository.findAll();
		Admin a2 = a1.get(0);
		
		if(a2.getUserName().equals(admin.getUserName()) && a2.getPassword().equals(admin.getPassword()))
		{
			isLogin = true;
			return "You're Successfully Logged in as a Administartor";
		}
		else
		{
			return "Unsuccessful Authentication";
		}
		
	}

}
