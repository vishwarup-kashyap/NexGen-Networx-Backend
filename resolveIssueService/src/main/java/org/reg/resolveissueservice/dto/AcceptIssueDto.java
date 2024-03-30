package org.reg.resolveissueservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class AcceptIssueDto {

    private Long id;
    private String name;
    private String status;

}
