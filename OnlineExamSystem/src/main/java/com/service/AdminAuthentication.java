package com.service;

import com.advices.DataNotFoundedException;
import com.entity.Admin;

public interface AdminAuthentication {
	public String adminLogin(Admin admin) throws DataNotFoundedException;
}
