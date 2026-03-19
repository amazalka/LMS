package com.example.lms.service;

import com.example.lms.exception.GroupNotFoundException;
import com.example.lms.model.GroupEntity;
import com.example.lms.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupEntity addGroup(GroupEntity group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public GroupEntity updateGroup(Long id, GroupEntity updateGroup) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        group.setName(updateGroup.getName());
        return group;
    }

    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    public GroupEntity getByIdGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }
}
