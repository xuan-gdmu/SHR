package com.lwx.ucenter.entity.vo;

import lombok.Data;

@Data
public class ForgetPasswordVo {
    private String mobile;

    private String question;

    private String answer;

    private String newPassword;
}
