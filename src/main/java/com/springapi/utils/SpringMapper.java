package com.springapi.utils;

import com.springapi.entities.Person;
import com.springapi.models.Developer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpringMapper {
    SpringMapper INSTANCE = Mappers.getMapper( SpringMapper.class );
    @Mapping(source = "name", target = "fullName")
    Person modelToPerson(Developer developer);
}
