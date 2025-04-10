//Done by Lewis Huang

package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);

}
