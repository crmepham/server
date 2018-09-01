package com.server.dataservice.repository;

import com.server.common.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findByExternalReferenceAndDeletedFalse(String externalReference);
    List<Menu> findByParentAndDeletedFalseOrderByItemOrderAsc(Menu parent);
    List<Menu> findByParentNullAndDeletedFalseOrderByItemOrder();
}
