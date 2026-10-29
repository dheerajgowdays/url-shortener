package com.dheeraj.url_shortener.repository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.dheeraj.url_shortener.entity.Plan;
import com.dheeraj.url_shortener.entity.User;
import com.dheeraj.url_shortener.enums.PlanName;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Test
    void shouldCreateAndSaveUserWithFreePlan() {
        Optional<Plan> freePlanOptional = planRepository.findAll().stream()
            .filter(plan -> plan.getPlanName() == PlanName.FREE)
            .findFirst();

        assertThat(freePlanOptional).as("FREE plan must be seeded in database").isPresent();

        Plan freePlan = freePlanOptional.get();

        String email = "test-"+UUID.randomUUID()+"@example.com";
        String hashPassword = "$2a$10$wE1VfQ9g.examplePlaceholderHash1234567890";

        User user = new User(email, hashPassword, freePlan);
        User savedUser = userRepository.save(user);

        UUID savedUserId = savedUser.getId();

        userRepository.flush();

        userRepository.findById(savedUserId)
                        .ifPresentOrElse(
                            reloaderUser ->{
                                assertThat(reloaderUser.getEmail()).isEqualTo(email);
                                assertThat(reloaderUser.getPlan().getPlanName()).isEqualTo(PlanName.FREE);
                            },
                            ()->{
                                throw new AssertionError("Saved user could not be reloaded");
                            }
                        );

        assertThat(savedUser.getId())
                        .as("Generated User Id must not be null after persist ")
                        .isNotNull();

        assertThat(savedUser.getEmail())
                        .as("Persisted email should match the input email")
                        .isEqualTo(email);
                        
        assertThat(savedUser.getPlan())
                    .as("Associated plan must match the FREE plan")
                    .isEqualTo(freePlan);
    }

    @Test
    void  shouldFindUserByEmailIgnoreCase(){
        Optional<Plan> freePlanOptional = planRepository.findAll().stream()
                                .filter(plan -> plan.getPlanName() == PlanName.FREE)
                                .findFirst();

        assertThat(freePlanOptional).as("FREE plan must be seeded in database").isPresent();
        
        Plan freePlan = freePlanOptional.get();

        String email = "Dheeraj.Test@example.com";
        String hashPassword = "$2a$10$wE1VfQ9g.examplePlaceholderHash1234567890";

        User user = new User(email, hashPassword, freePlan);
        User userSaved = userRepository.save(user);

        assertThat(userSaved.getId())
            .as("Saved User Id must be generated")
            .isNotNull();

        String queryEmail="dheeraj.test@example.com";
        Optional<User> emailOptional =  userRepository.findByEmailIgnoreCase(queryEmail);

        assertThat(emailOptional)
                .as("User should be found regardless of case differences")
                .isPresent();
        assertThat(emailOptional.get().getId())
                .as("Returned Id should match with the saved Id")
                .isEqualTo(userSaved.getId());
    }

    @Test
    void shouldReturnEmptyWhenEmailDoesNotExist(){
        Optional<User> user = userRepository.findByEmailIgnoreCase("Dheeraj@email.com");

        assertThat(user)
            .as("Quering that non-existing email should return Optional.empty()")
            .isEmpty();
    }
}