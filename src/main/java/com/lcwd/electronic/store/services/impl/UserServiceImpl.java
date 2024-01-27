package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositiories.UserRepository;
import com.lcwd.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    //Create user
    @Override
    public UserDto createUser(UserDto userDto) {
        //Random generate unique id in String format
        String userId=UUID.randomUUID().toString();
        userDto.setUserId(userId);
        User user =dtoToEntity(userDto);
        User user1=userRepository.save(user);
        UserDto newDto=entityToDto(user1);

        return newDto;
    }


//For update user
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
       User updateUser= userRepository.save(user);
      UserDto updateDto= entityToDto(updateUser);
        return updateDto;
    }
//Delete User
    @Override
    public void deleteUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"));
        userRepository.delete(user);
    }
//Get All User
    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
      Sort sort=  (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page =userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);
        return pageableResponse;
    }
//Get user by Id
    @Override
    public UserDto getSingleUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"));

        return entityToDto(user);
    }
//Get user by email
    @Override
    public UserDto getUserByEmail(String userId) {
       User user= userRepository.findByEmail(userId).orElseThrow(()->new ResourceNotFoundException("User not found by email and password"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> getSearch(String keyword) {
        List<User> listSearch=userRepository.findByNameContaining(keyword);
        List<UserDto> userDtoList=  listSearch.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return userDtoList;
    }

    private User dtoToEntity(UserDto userDto) {
       /*User user= User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .about(userDto.getAbout())
                .gender(userDto.getGender())
               .imageName(userDto.getImageName()).build();*/

       return mapper.map(userDto,User.class);
    }

    private UserDto entityToDto(User user1) {
       /*UserDto userDto =UserDto.builder()
                .userId(user1.getUserId())
                .name(user1.getName())
                .gender(user1.getGender())
                .about(user1.getAbout())
                .email(user1.getEmail())
                .password(user1.getPassword())
                .imageName(user1.getImageName()).build();*/
        return mapper.map(user1,UserDto.class);
    }
}
