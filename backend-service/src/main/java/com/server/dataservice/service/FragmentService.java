package com.server.dataservice.service;

import com.server.common.model.Fragment;
import com.server.dataservice.repository.FragmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FragmentService
{
    @Autowired
    private FragmentRepository fragmentRepository;

    public List<Fragment> getAll() {
        return fragmentRepository.findByDeletedFalse();
    }

    public List<Fragment> getAllByUri(String uri) {
        return fragmentRepository.findByUriAndDeletedFalse(uri);
    }

    public Fragment get(Long id) {
        return fragmentRepository.findById(id).get();
    }

    public void update(Fragment fragment) {
        fragmentRepository.save(fragment);
    }
}
