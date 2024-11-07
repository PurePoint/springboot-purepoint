package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CommunityRepository extends JpaRepository<Community, UUID> {

}
