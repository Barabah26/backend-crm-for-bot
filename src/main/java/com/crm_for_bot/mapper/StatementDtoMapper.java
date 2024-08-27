package com.crm_for_bot.mapper;


import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.entity.StatementInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatementDtoMapper extends Mappable<StatementInfo, StatementDto> {
    @Override
    StatementInfo toEntity(StatementDto dto);

    @Override
    StatementDto toDto(StatementInfo entity);

    @Override
    List<StatementDto> toDto(List<StatementInfo> entity);
}
