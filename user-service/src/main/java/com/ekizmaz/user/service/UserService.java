package com.ekizmaz.user.service;

import com.ekizmaz.common.client.contract.UserDto;
import com.ekizmaz.user.dto.AuthRequest;
import com.ekizmaz.user.dto.AuthResponse;
import com.ekizmaz.user.dto.UserSaveDto;
import com.ekizmaz.user.entity.User;
import com.ekizmaz.user.enums.AppUserRole;
import com.ekizmaz.user.enums.FirmType;
import com.ekizmaz.user.exception.UserNotFoundException;
import com.ekizmaz.user.repository.UserRepository;
import com.ekizmaz.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto get(Long id) {
        User account = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return modelMapper.map(account, UserDto.class);
    }

    @Transactional
    public UserDto save(UserSaveDto userSaveDto) {
        User user = modelMapper.map(userSaveDto, User.class);

        user.setPassword(bCryptPasswordEncoder.encode(userSaveDto.getPassword()));
        user.setAppUserRole(AppUserRole.USER);
        user.setFirmType(FirmType.INDIVIDUAL);
        //account.setPassword(bCryptPasswordEncoder.encode("123456"));
        user = userRepository.save(user);
        //accountDto.setId(account.getId());
        return modelMapper.map(user, UserDto.class);
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        Assert.isNull(id, "Id cannot be null");
        Optional<User> user = userRepository.findById(id);
        User accountToUpdate = user.map(it -> {
            it.setName(userDto.getName());
            it.setSurname(userDto.getSurname());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(userRepository.save(accountToUpdate), UserDto.class);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        userRepository.delete(user);
    }

    public List<UserDto> findAll(Pageable pageable) {
        List<UserDto> accountDtos=userRepository.findAll(pageable)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return accountDtos;
    }
    @Override
    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(usernameOrEmail).orElseThrow();
    }


    public UserDto getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).get();
        return modelMapper.map(user,UserDto.class);
    }
    public AuthResponse getToken(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).get();
        String encodePassword=bCryptPasswordEncoder.encode(request.getPassword());

        User foundUser1 = userRepository.findByEmailAndPassword(request.getEmail(),encodePassword).get();
        User foundUser = userRepository.findByEmailAndPassword(request.getEmail(), bCryptPasswordEncoder.encode(request.getPassword()))
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        String token = jwtUtil.generateToken(foundUser);

        return new AuthResponse(token);

    }
}
