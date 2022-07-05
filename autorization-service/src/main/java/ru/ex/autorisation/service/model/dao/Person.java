package ru.ex.autorisation.service.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ex.autorisation.service.model.dto.PersonDto;
import ru.ex.autorisation.service.model.mapper.CustomMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements CustomMapper<PersonDto> {
    private String name;
    private String email;
    private String role;
    private String pass;
    private boolean isBlocked;

    @Override
    public PersonDto mapToDto() {
        return PersonDto.builder()
                .email(this.email)
                .name(this.name)
                .role(this.role)
                .build();
    }
}
