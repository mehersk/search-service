package com.stackroute.search.service;

import com.stackroute.search.model.UserProfile;
import com.stackroute.search.repository.UserProfileRepository;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    private UserProfileRepository userProfileRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public boolean saveUser(UserProfile userProfile) {
        return (userProfileRepository.save(userProfile) != null);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll().getContent();
    }

    @Override
    public List<UserProfile> getAllUsersByRole(String role) {
        return userProfileRepository.findAllByRole(role);
    }

    @Override
    public UserProfile getUserByEmail(String email) {
        UserProfile userProfile = null;
        userProfile = userProfileRepository.findById(email).get();
        return userProfile;
    }

    @Override
    public UserProfile updateUser(UserProfile userProfile) {
        UserProfile updatedUserProfile = null;
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfile.getEmail());
        if(optionalUserProfile.isPresent()) {
            updatedUserProfile = userProfileRepository.save(userProfile);
        }
        return updatedUserProfile;
    }

    @Override
    public SearchHits<UserProfile> search(String keyword) {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("*"+keyword+"*").field("email")
                .field("firstName")
                .field("lastName")
                .field("role")
                .field("phoneNo")
                .field("location")
                .field("gender")
                .field("designation")
                .field("domainExperiences")
                .field("skills");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();
        return elasticsearchRestTemplate.search(searchQuery, UserProfile.class);
    }
}
