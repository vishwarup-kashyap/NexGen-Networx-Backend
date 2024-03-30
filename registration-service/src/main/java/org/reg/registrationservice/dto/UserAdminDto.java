package org.reg.registrationservice.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserAdminDto {
    private String name;
    private Long phoneNo;
    private String email;
    private String password;
    private Boolean isAdmin=Boolean.FALSE;
}
