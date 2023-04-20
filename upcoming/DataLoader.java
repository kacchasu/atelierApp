import com.example.atelierapp.models.Role;
import com.example.atelierapp.models.User;
import com.example.atelierapp.repositories.RoleRepository;
import com.example.atelierapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"));
        admin.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
        userRepository.save(admin);

        User user = new User("user", new BCryptPasswordEncoder().encode("user"));
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
