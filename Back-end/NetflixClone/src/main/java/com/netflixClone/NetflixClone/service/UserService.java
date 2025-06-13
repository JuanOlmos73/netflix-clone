package com.netflixClone.NetflixClone.service;


import  com.netflixClone.NetflixClone.dto.response.UserDto;
import  com.netflixClone.NetflixClone.exception.NotFoundException;
import  com.netflixClone.NetflixClone.model.User;
import com.netflixClone.NetflixClone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    public List<UserDto> findAll() {
        return repo.findAll().stream()
                   .map(u -> new UserDto(u.getId(), u.getEmail()))
                   .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new NotFoundException("User no encontrado"));
        return new UserDto(u.getId(), u.getEmail());
    }

    public UserDto update(Long id, UserDto dto) {
        User u = repo.findById(id).orElseThrow(() -> new NotFoundException("User no encontrado"));
        u.setEmail(dto.getEmail());
        repo.save(u);
        return new UserDto(u.getId(),  u.getEmail());
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con email: " + email));
    }
}
