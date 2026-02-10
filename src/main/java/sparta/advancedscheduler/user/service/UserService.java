package sparta.advancedscheduler.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.user.dto.ResponseUpdateUserDto;
import sparta.advancedscheduler.user.dto.ResponseUserDto;
import sparta.advancedscheduler.user.dto.ResponseUserListDto;
import sparta.advancedscheduler.user.dto.RequestUserDto;
import sparta.advancedscheduler.user.entity.User;
import sparta.advancedscheduler.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public Long createUser(RequestUserDto requestDto) {
        User user = new User(
                requestDto.getUsername(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        userRepository.save(user);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public List<ResponseUserListDto> getUsers(String username) {
        List<User> users = userRepository.findALlByUsername(username);

        List<ResponseUserListDto> responseUserListDtos = new ArrayList<>();
        for (User user : users) {
            responseUserListDtos.add(new ResponseUserListDto(
                    user.getUsername()
            ));
        }

        return responseUserListDtos;
    }

    @Transactional(readOnly = true)
    public ResponseUserDto getUserByUserId(Long userId) {
        User user = checkIsUser(userId);

        return new ResponseUserDto(user.getUsername(), user.getEmail());
    }

    @Transactional
    public ResponseUpdateUserDto updateUser(Long userId ,ResponseUpdateUserDto requestDto) {
        User user = checkIsUser(userId);
        user.update(requestDto);
        return new ResponseUpdateUserDto(user.getUsername());
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = checkIsUser(userId);

        userRepository.delete(user);
    }

    private User checkIsUser(Long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 유저입니다.")
        );
    }
}
