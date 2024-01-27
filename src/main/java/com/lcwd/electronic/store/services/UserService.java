package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;

import java.util.List;

public interface UserService {
    //Create
     UserDto createUser(UserDto userDto);
    //Update
     UserDto updateUser(UserDto userDto,String userId);
    //Delete
     void deleteUser(String userId);
    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
    //get single user by id
    UserDto getSingleUser(String userId);
    //get user by email
    UserDto getUserByEmail(String userId);
    //Search user
    List<UserDto> getSearch(String keyword);
    //Other user specific features
}
