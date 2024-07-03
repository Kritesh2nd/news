/*
package com.exm.news.service;


import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import com.exm.news.entity.auth.Login;
import com.exm.news.repository.auth.LoginRepository;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.dto.user.LoginUserDto;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.dto.user.UpdateAuthorityDto;
import com.exm.news.dto.user.UpdateUserDto;
import com.exm.news.entity.app.Authority;
import com.exm.news.entity.app.User;
import com.exm.news.repository.app.AuthorityRepository;
import com.exm.news.repository.app.UserRepository;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.response.LoginResponse;
import com.exm.news.security.authentication.UserAuth;
import com.exm.news.security.provider.BasicAuthProvider;
import com.exm.news.service.interfaces.UserServiceInterfaces;


@Service
public class UserServiceClone implements UserServiceInterfaces{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BasicAuthProvider authProvider;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<GeneralUserDto> getGeneralUserList() {
        List<User> userList = userRepository.findAll();
        List<GeneralUserDto> generalUserList = userList.stream()
                .map(user -> {
                    Set<Authority> authorities = user.getAuthorities();
                    GeneralUserDto generalUser = modelMapper.map(user, GeneralUserDto.class);
                    List<String> authorityNames = authorities.stream()
                            .map(Authority::getName)
                            .collect(Collectors.toList());
                    generalUser.setRole(authorityNames);
                    return generalUser;
                })
                .collect(Collectors.toList());

        return generalUserList;
    }

    @Override
    public GeneralUserDto getGeneralUserById(Long id) {
        User user = getUserById(id);

        GeneralUserDto generalUser = modelMapper.map(user, GeneralUserDto.class);

        Set<Authority> authorities = user.getAuthorities();

        List<String> authorityNames = authorities.stream()
                .map(Authority::getName)
                .collect(Collectors.toList());

        generalUser.setRole(authorityNames);

        return generalUser;
    }

    @Override
    public UserAuth authenticate(LoginUserDto input) {
        UserAuth userAuth = modelMapper.map(input, UserAuth.class);
        try {
            UserAuth auth = (UserAuth) authProvider.authenticate(userAuth);
            if(!auth.isAuthenticated()) {
                throw new UsernameNotFoundException("Username not found");
            }
            User registeredUser = userRepository.findUserByEmail(auth.getEmail());

            UserAuth userDetail = modelMapper.map(registeredUser, UserAuth.class);

            return userDetail;
        }
        catch(Exception e) {
            throw new UsernameNotFoundException("User email not found");
        }
    }

    @Override
    public BasicResponseDto signup(RegisterUserDto newUser) {

        Login loginUserEmail = loginRepository.findLoginByEmail(newUser.getEmail());
        if(loginUserEmail!=null) {
            return new BasicResponseDto("User with this email already exits.",false);
        }
        String encryptedPassword = encodePassword(newUser.getPassword());

        Login login = modelMapper.map(newUser, Login.class);
        login.setPassword(encryptedPassword);
        loginRepository.save(login);

        User user = modelMapper.map(newUser, User.class);

        Set<Authority> userAuthorities = new HashSet<Authority>();
        Authority userAuthority = authorityRepository.findAuthorityByName("reader");
        userAuthorities.add(userAuthority);
        user.setAuthorities(userAuthorities);

        userRepository.save(user);

        return new BasicResponseDto("New user added successfully.",true);
    }

    @Override
    public BasicResponseDto updateUser(UpdateUserDto newUserData) {

        UserAuth userAuth = (UserAuth) SecurityContextHolder.getContext().getAuthentication();
        User updateThisUser = getUserById(newUserData.getUserId());

        if(userAuth == null || updateThisUser == null) {
            throw new NoSuchElementException("User not found");
        }

        User loggedInUser = userRepository.findUserByEmail(userAuth.getEmail());
        User newUserEmail = userRepository.findUserByEmail(newUserData.getEmail());

        if(!loggedInUser.getUserId().equals(newUserData.getUserId())) {
            throw new AccessDeniedException("Cannot update this user");
        }

        if(newUserEmail == null) {

        }
        else if(!newUserData.getEmail().equals(loggedInUser.getEmail()) && newUserData.getEmail().equals(newUserEmail.getEmail())) {
            throw new DuplicateKeyException("Cannot update. This email already in use");
        }
        updateThisUser.setEmail(newUserData.getEmail());
        updateThisUser.setFirstName(newUserData.getFirstName());
        updateThisUser.setLastName(newUserData.getLastName());
        updateThisUser.setUsername(newUserData.getUsername());
        userRepository.save(updateThisUser);

        return new BasicResponseDto("User updated successfully.",true);
    }

    @Override
    public BasicResponseDto updateUserPassword(Long id, String password) {
        User newUser = getUserById(id);

        newUser.setPassword(password);
        userRepository.save(newUser);

        return new BasicResponseDto("Password updated successfully.",true);
    }


    @Override
    public BasicResponseDto deleteMyAccount() {
        UserAuth userAuth = (UserAuth) SecurityContextHolder.getContext().getAuthentication();

        if(userAuth == null) {
            throw new NoSuchElementException("user not found, cannot delete the user account");
        }
        User deleteUser = userRepository.findUserByEmail(userAuth.getEmail());
        userRepository.delete(deleteUser);

        return new BasicResponseDto("Your account is deleted successfully.",true);
    }

    @Override
    public LoginResponse getUserToken() {
        UserAuth userAuth = (UserAuth) SecurityContextHolder.getContext().getAuthentication();

        if(userAuth == null) {
            throw new NoSuchElementException("user not found, cannot make token");
        }

        String jwtToken = jwtService.generateToken(userAuth);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }


    @Override
    public BasicResponseDto updateUserAuthority(UpdateAuthorityDto userAuthority) {
        try {
            User user = getUserById(userAuthority.getUserId());

            Set<Authority> userAuthorities = user.getAuthorities();
            Authority newUserAuthority = authorityRepository.findAuthorityByName("editor");
            userAuthorities.add(newUserAuthority);
            user.setAuthorities(userAuthorities);

            userRepository.save(user);
        }
        catch(Exception e) {
            return new BasicResponseDto ("Unable to update user authority for id: "+ userAuthority.getUserId(),false);
        }
        return new BasicResponseDto("User authority updated successfully.",true);
    }

    @Override
    public BasicResponseDto removeUserAuthority(UpdateAuthorityDto userAuthority) {
        try {
            User user = getUserById(userAuthority.getUserId());

            Set<Authority> userAuthorities = user.getAuthorities();
            Authority newUserAuthority = authorityRepository.findAuthorityByName("editor");
            userAuthorities.remove(newUserAuthority);
            user.setAuthorities(userAuthorities);

            userRepository.save(user);
        }
        catch(Exception e) {
            return new BasicResponseDto ("Unable to remove user authority for id: "+ userAuthority.getUserId(),false);
        }
        return new BasicResponseDto("User authority removed successfully.",true);
    }

    public GeneralUserDto getMe() {
        UserAuth userAuth = (UserAuth) SecurityContextHolder.getContext().getAuthentication();

        if(userAuth == null) {
            return null;
        }
        User user = userRepository.findUserByEmail(userAuth.getEmail());

        GeneralUserDto generalUser = modelMapper.map(user, GeneralUserDto.class);

        Set<Authority> authorities = user.getAuthorities();

        List<String> authorityNames = authorities.stream()
                .map(Authority::getName)
                .collect(Collectors.toList());

        generalUser.setRole(authorityNames);

        return generalUser;

    }

    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw new NoSuchElementException("User not found");
        }
        return user;
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }



}








*/
