package kr.smhrd.domain;

import lombok.Data;

@Data
public class AttachFileVO {
  private String fileName;
  private String uploadPath;
  private String uuid;
}
