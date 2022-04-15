package com.storeum.payload.request;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class CreateFolderRequest {

    @NotBlank
    private String title;
    private Long parentFolderId;
}