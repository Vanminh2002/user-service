package com.example.userservice.dto.user.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.dto.BaseFilterRequest;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchUserRequest extends BaseFilterRequest {
    private String keyword;
}
