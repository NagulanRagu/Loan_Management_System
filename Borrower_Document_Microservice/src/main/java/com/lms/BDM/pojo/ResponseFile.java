package com.lms.BDM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {
    
    private String fileName;
    private String fileDetail;
    private String fileType;
    private String fileUri;
    private long fileSize;
}
