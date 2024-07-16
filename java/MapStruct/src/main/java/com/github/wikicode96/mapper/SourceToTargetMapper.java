package com.github.wikicode96.mapper;

import com.github.wikicode96.entity.SourceEntity;
import com.github.wikicode96.entity.TargetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SourceToTargetMapper {
    SourceToTargetMapper INSTANCE = Mappers.getMapper(SourceToTargetMapper.class);

    @Mapping(source = "name", target = "fullName")
    TargetEntity sourceToTarget(SourceEntity source);

    List<TargetEntity> sourceListToTargetList(List<SourceEntity> sourceList);
}
