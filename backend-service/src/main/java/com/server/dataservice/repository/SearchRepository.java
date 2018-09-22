package com.server.dataservice.repository;

import com.server.common.model.Property;
import com.server.dataservice.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@Transactional
@Repository
public class SearchRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static final String GLOBAL_SEARCH = "global_search";

    @Autowired
    private PropertyRepository propertyRepository;

    @PersistenceContext
    private EntityManager em;

    public Map<String, List<Object>> search(final String text) {

        final Property property = propertyRepository.findByExternalReference(GLOBAL_SEARCH);
        if (property == null || !hasText(property.getValue())) {
            log.debug("No property or property value is empty.");
            return Collections.emptyMap();
        }

        final String[] tables = property.getValue().split(";");
        if (tables.length == 0 ) {
            log.debug("No table could be parsed from property.");
            return Collections.emptyMap();
        }

        Map<String, List<Object>> results = new HashMap<>();
        for (String table : tables) {
            log.debug("Searching table ", table);
            final String title = table.split(":")[0].split("=")[0];
            List<Object> rows = searchTable(table, text);
            log.debug("Found {} rows for table {}", rows.size(), table);
            results.put(title, rows);
        }

        return results;
    }

    private List<Object> searchTable(final String property, String text) {

        final String[] options = property.split(":");
        final String entity = options[0].split("=")[1];
        final String[] queryColumns = options[1].split(",");
        final String[] searchWords = text.split(" ");
        final String sql = buildQuery(entity, queryColumns, searchWords);

        log.debug("Prepared SQL: ", sql);

        try {
            Class<?> clazz = Class.forName("com.server.common.model." + entity);
            Query query = em.createNativeQuery(sql, clazz);
            populateParamaters(query, searchWords);
            return query.getResultList();
        } catch (ClassNotFoundException e) {
            log.error("class cast exception: ", e.getMessage());
            return Collections.emptyList();
        }
    }

    private void populateParamaters(Query query, String[] searchWords) {
        for (int i = 0, j = searchWords.length; i < j; i++) {
            query.setParameter("searchTerm" + i, "%" + searchWords[i] + "%");
        }
    }

    private String buildQuery(String table, String[] queryColumns, String[] searchWords) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from ");
        builder.append(table);
        builder.append(" where ");
        for (int i = 0, j = searchWords.length; i < j; i++) {
            for (int y= 0, z = queryColumns.length; y < z; y++) {
                builder.append("(" + queryColumns[y] + " like :searchTerm" + i + " )");
                if (y != queryColumns.length - 1) {
                    builder.append(" or ");
                }
            }
            if (i != searchWords.length - 1) {
                builder.append(" or ");
            }
        }
        builder.append(" and deleted = false");
        return builder.toString();
    }
}
