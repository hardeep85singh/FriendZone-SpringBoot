package org.example.FriendZone.service;

import org.example.FriendZone.model.User;
import org.example.FriendZone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(new User(user.getFirstName(),
                user.getLastName(), user.getUsername(), user.getPassword()));

    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void addHobbies(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            User existingUser = userRepository.findByUsername(user.getUsername());
            existingUser.setHobbies(user.getHobbies());

            User newUser = userRepository.save(existingUser);
            System.out.println(user.getHobbies());
        }
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> searchUser(String keyword) {
        if (keyword != null) {
            return userRepository.searchUsers(keyword);
        }
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User addFriend(Long userId, Long friendId) {
        User friend = userRepository.findByUserId(friendId);
        return friend;
    }


}
