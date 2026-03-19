package com.example.lms.mapper;

import com.example.lms.dto.request.GroupRequest;
import com.example.lms.dto.response.GroupResponse;
import com.example.lms.model.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    GroupResponse toResponse(GroupEntity course);
    GroupEntity toEntity(GroupRequest courseRequest);
}
