package com.server.dataservice.repository;

import com.server.common.model.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MenuRepositoryTest
{
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testMenus()
    {
        Menu parent = new Menu();
        parent.setExternalReference("PARENT");
        parent.setUri("PARENT");

        menuRepository.save(parent);

        assertThat(menuRepository.findByExternalReferenceAndDeletedFalse("PARENT")).isNotNull();

        Menu child1 = new Menu("CHILD1", "CHILD1");
        child1.setParent(parent);
        Menu child2 = new Menu("CHILD2", "CHILD2");
        child2.setParent(parent);

        List<Menu> children = new ArrayList<Menu>();
        children.add(child1);
        children.add(child2);

        parent.setChildren(children);

        menuRepository.save(parent);

        Menu result = menuRepository.findByExternalReferenceAndDeletedFalse("PARENT");
        assertThat(result).isNotNull();
        assertThat(result.getChildren().size()).isEqualTo(2);
    }
}
