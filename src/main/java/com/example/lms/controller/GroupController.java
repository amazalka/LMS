package com.example.lms.controller;

import com.example.lms.dto.request.GroupRequest;
import com.example.lms.dto.response.GroupResponse;
import com.example.lms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public GroupResponse add(@RequestBody GroupRequest request) {
        return groupService.addGroup(request);
    }

    @PutMapping("/{id}")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request) {
        return groupService.updateGroup(id, request);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return id;
    }

    @GetMapping
    public List<GroupResponse> getAll() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public GroupResponse getById(@PathVariable Long id) {
        return groupService.getByIdGroup(id);
    }
}
