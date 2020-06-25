package com.fujitsu.ph.tsup.authz.core.service;

import java.util.List;

public interface AuthorizationService {
	List<String> findByUserName(String userName);

}
