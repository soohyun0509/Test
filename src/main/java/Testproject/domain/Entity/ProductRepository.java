package Testproject.domain.Entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query( value = "select * from product where mno = :mno", nativeQuery = true)
    Page<ProductEntity> findbyMno(@Param("mno") int mno, Pageable pageable );
}
