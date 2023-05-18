package com.MADG2.security.dto;

import com.MADG2.security.entity.Rol;
import lombok.Data;

@Data
public class RolUserDto {

    private Long idUser;
    private Rol rol;
}
