// Done by Lewis Huang

package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Customer entity.
 * Extends JpaRepository to provide standard CRUD operations.
 */
//Done by Lewis
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Custom query method to find a customer by their username (used as email).
     * Automatically implemented by Spring Data JPA based on method name.
     */
    Customer findByUsername(String username);

    // Done by Dionis

    /**
     * Custom method to retrieve a customer by ID.
     * Duplicate of findById, but returns Customer directly instead of Optional<Customer>.
     */
    Customer findCustomerById(int id);
}
