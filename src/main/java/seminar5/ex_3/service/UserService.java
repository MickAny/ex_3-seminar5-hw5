package seminar5.ex_3.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import seminar5.ex_3.model.User;
import seminar5.ex_3.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Создание пользователя
     *
     * @param user новый пользователь
     * @return пользователь
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Получение всех пользователей
     *
     * @return список пользователей
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Удаление пользователя
     *
     * @param user пользователь
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
