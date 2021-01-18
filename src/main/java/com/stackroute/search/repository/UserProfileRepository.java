package com.stackroute.search.repository;

import com.stackroute.search.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends ElasticsearchRepository<UserProfile, String> {

    Page<UserProfile> findAll();

    List<UserProfile> findAllByRole(String role);

}
