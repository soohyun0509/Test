package Testproject.domain.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "select * from product where mno= :mno" , nativeQuery = true)
    List<ProductEntity> findbyMno(int mno);

}
