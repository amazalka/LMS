package com.example.lms.mapper;

import com.example.lms.dto.request.GroupRequest;
import com.example.lms.dto.response.GroupResponse;
import com.example.lms.model.GroupEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    GroupResponse toResponse(GroupEntity group);

    GroupEntity toEntity(GroupRequest groupRequest);

    List<GroupResponse> toResponse(List<GroupEntity> groups);

    void updateGroupFromRequest(@MappingTarget GroupEntity group, GroupRequest groupRequest);
}
