package mg.raseta.car_show.controller;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.User;
import mg.raseta.car_show.service.implementations.UserService;
import mg.raseta.car_show.specification.GenericModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final GenericModelSpecification<User> genericModelSpecification;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.save(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<Page<User>> searchUser(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit
    )
    {
        Specification<User> specification = Specification.where(null);

        if (userId != null) {
            specification = specification.and(genericModelSpecification.hasInteger(userId, "userId"));
        }
        if (name != null) {
            specification = specification.and(genericModelSpecification.hasString(name, "name"));
        }
        if (email != null) {
            specification = specification.and(genericModelSpecification.hasString(email, "email"));
        }
        if (password != null) {
            specification = specification.and(genericModelSpecification.hasString(password, "password"));
        }

        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(userService.searchUser(specification, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable int id,
            @RequestBody User user
    )
    {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}