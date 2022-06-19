package com.lft.taskservice.tasks.adapters.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFilesRequestDto {

    private String path;
    private String content;

}
