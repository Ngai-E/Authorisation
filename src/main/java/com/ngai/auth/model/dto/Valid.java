package com.ngai.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONObject;

@AllArgsConstructor
public class Valid {
    private boolean valid;
    private String msg;
    private String user;
    private String data;
}
