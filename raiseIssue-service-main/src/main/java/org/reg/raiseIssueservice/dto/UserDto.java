package org.reg.raiseIssueservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserDto {


    private Long id;

    private String name;

    private Long phoneNo;

    private String email;
}
