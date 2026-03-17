package com.example.lms.service;

import com.example.lms.dto.request.GroupRequest;
import com.example.lms.dto.response.GroupResponse;
import com.example.lms.exception.GroupNotFoundException;
import com.example.lms.mapper.GroupMapper;
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
    private final GroupMapper groupMapper;

    public GroupResponse addGroup(GroupRequest request) {
        GroupEntity group = groupMapper.toEntity(request);
        groupRepository.save(group);
        return groupMapper.toResponse(group);
    }

    public void deleteGroup(Long id) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        groupRepository.delete(group);
    }

    public GroupResponse updateGroup(Long id, GroupRequest request) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        groupMapper.updateGroupFromRequest(group, request);
        return groupMapper.toResponse(group);
    }

    public List<GroupResponse> getAllGroups() {
        return groupMapper.toResponse(groupRepository.findAll());
    }

    public GroupResponse getByIdGroup(Long id) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        return groupMapper.toResponse(group);
    }
}
