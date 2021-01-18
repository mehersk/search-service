package com.stackroute.search.service;

import com.stackroute.search.model.UserProfile;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface UserProfileService {

    boolean saveUser(UserProfile userProfile);

    List<UserProfile> getAllUsers();

    List<UserProfile> getAllUsersByRole(String role);

    UserProfile getUserByEmail(String email);

    UserProfile updateUser(UserProfile userProfile);

    SearchHits<UserProfile> search(String keyword);

}
