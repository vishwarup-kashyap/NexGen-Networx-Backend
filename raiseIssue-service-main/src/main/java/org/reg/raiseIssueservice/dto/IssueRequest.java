package org.reg.raiseIssueservice.dto;

import jakarta.persistence.Transient;
import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Data
public class IssueRequest {
    private Long userId;
    private String issueType;
    private String issueName;


}
