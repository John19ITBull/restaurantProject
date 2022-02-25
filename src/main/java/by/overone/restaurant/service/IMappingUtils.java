package by.overone.restaurant.service;

public interface IMappingUtils<DTO, ENT> {
    DTO mapToDto(ENT entity);

    public ENT mapToEntity(DTO dto);
}
