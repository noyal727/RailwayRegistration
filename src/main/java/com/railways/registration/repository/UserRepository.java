package com.railways.registration.repository;

import com.railways.registration.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDetails,String> {
}
