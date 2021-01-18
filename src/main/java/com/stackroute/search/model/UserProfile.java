package com.stackroute.search.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "userprofile", shards = 5)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNo;
    private Date dateOfBirth;
    private String location;
    private String gender;;
    private boolean isEmployee;
    private boolean isAvailableForProject;
    private String designation;
    private float CTC;
    private float experienceInYrs;
    @Field(type = FieldType.Object)
    private List<DomainExperience> domainExperiences;
    private List<String> skills;

}
