package com.example.alura.forum.mappers;

import com.example.alura.forum.dtos.requests.TopicoRequestDto;
import com.example.alura.forum.dtos.requests.responses.TopicoResponseDto;
import com.example.alura.forum.entities.Topico;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TopicoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Topico toEntity(TopicoRequestDto topicoRequestDto);

    TopicoResponseDto toDto(Topico topico);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    void updateTopicoFromDto(TopicoRequestDto topicoRequestDto, @MappingTarget Topico topico);
}
