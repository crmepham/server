package com.server.frontendservice.service;

import com.server.common.exception.InvalidInputException;
import com.server.common.exception.InvalidStateException;
import com.server.common.model.Fragment;
import com.server.common.service.BaseService;
import com.server.common.service.PropertyService;
import com.server.frontendservice.repository.FragmentRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.lang.Boolean.parseBoolean;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Transactional
@Service
public class FragmentService extends BaseService {
    @Autowired
    private FragmentRepository fragmentRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Fragment> getAll(final String uri) throws Exception {
        val fragments = fragmentRepository.getAll(uri);

        CompletableFuture.allOf(fragments).join();

        if (fragments.get().isEmpty()) {
            return Collections.emptyMap();
        }

        val fragmentMap = new HashMap<String, Fragment>();
        for (Fragment fragment : fragments.get()) {
            fragmentMap.put(fragment.getExternalReference(), fragment);
        }

        executeFragmentQueries(fragments.get());
        validateTemplates(fragments.get());
        return fragmentMap;

    }

    private void executeFragmentQueries(Collection<Fragment> fragments) {
        for (Fragment fragment : fragments) {

            try {
                executeFragmentQuery(fragment);
            } catch (Exception e) {
                fragment.addError(e.getMessage());
                persistError(e, null, fragment.getClass().getSimpleName(), fragment.getExternalReference(),fragment.getId());
            }
        }
    }

    void executeFragmentQuery(Fragment fragment) {

        val queryParameters = Arrays.asList(fragment.getParameters().split(","));

        if (queryParameters.isEmpty()) throw new InvalidStateException(format("No query parameters specified for fragment '%s'.", fragment.getExternalReference()));

        val query = fragment.getQuery();

        if (!hasText(fragment.getQuery())) throw new InvalidStateException(format("No query specified for fragment '%s'.", fragment.getExternalReference()));

        if (query.startsWith("delete") || query.startsWith("update")) throw new InvalidInputException("Cannot execute SQL query. Query cannot contain 'delete' or 'update'.");

        val values = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {

            @Override
            public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                val result = new HashMap<String, String>();

                for (String key : queryParameters) {
                    result.put(key, rs.getString(key));
                }
                return result;
            }
        });

        fragment.setResultParameters(values);
    }

    private void validateTemplates(Collection<Fragment> fragments) {
        for (val fragment : fragments) {
            try {

                val resource = new ClassPathResource("templates/shared/macros.ftl");
                val data = FileCopyUtils.copyToByteArray(resource.getInputStream());
                val macros = new String(data, StandardCharsets.UTF_8);
                getTemplate(fragment, macros).process(getModel(fragment), new StringWriter());
            } catch (Exception e) {
                fragment.addError(e.getMessage());
                persistError(e, null, fragment.getClass().getSimpleName(), fragment.getExternalReference(), fragment.getId());
            }
        }
    }

    private Map<String, Object> getModel(Fragment fragment) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("fragment", fragment);
        map.put("hideSensitiveData", parseBoolean(propertyService.getByExternalReference("hide_sensitive_data").getValue()));
        return map;
    }

    @SuppressWarnings("deprecation")
    private Template getTemplate(Fragment fragment, String macros) throws IOException {
        val cfg = new Configuration();
        val templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate("template", macros + fragment.getDesign());
        cfg.setTemplateLoader(templateLoader);
        return cfg.getTemplate("template");
    }


    @Async("asyncExecutor")
    public CompletableFuture<List<Fragment>> getAll()
    {
        return fragmentRepository.getAll();
    }

    public Fragment get(Long id) {
        return fragmentRepository.get(id);
    }

    public void update(Fragment fragment) {
        fragmentRepository.update(fragment);
    }
}
