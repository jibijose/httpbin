package com.jibi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadInfoModel {

  private String uploadedBytesLength;
  private String contentType;
  private String name;
  private String originalFileName;
  private String size;
  private String status;
}
