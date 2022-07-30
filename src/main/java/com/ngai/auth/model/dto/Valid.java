package com.ngai.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONObject;

@Data
@AllArgsConstructor
public class Valid {
    private boolean valid;
    private String msg;
    private String user;
    private Object data;
}
