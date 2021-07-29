package kr.smhrd.web;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.smhrd.domain.AttachFileVO;
import kr.smhrd.mapper.BoardMapper;

@Controller
public class UploadController {
   // @Autowired //DI->이거 해주면 알아서 찾아감
   // @RequestMapping이거를 겟방식인지 포스트방식인지에 따라서 겟매핑, 포스트매핑 이러케 바꿔도 됨!!
   @Inject
   private BoardMapper boardmapper;

   @GetMapping("/uploadForm.do")
   public void uploadForm() {
      // uploadForm.jsp로 감 void면 일로간다!!!
   }

   @PostMapping("/uploadFormAction.do")
   public void uploadFormAction(MultipartFile[] uploadFile, Model model) { // MultipartFile: 파일 경로나 사이즈 다 들어가야하니까
                                                         // 배열로!(파일이름, 파일 사이즈)

      List<AttachFileVO> list = new ArrayList<AttachFileVO>();

      String uploadFolder = "C:\\upload";
      String uploadFolderPath = getFolder(); // 2021\07\27
      File uploadPath = new File(uploadFolder, uploadFolderPath);
      if (uploadPath.exists() == false) {
         uploadPath.mkdirs(); // 디렉토리 생성
      }

      for (MultipartFile multipartFile : uploadFile) {
         System.out.println(multipartFile.getOriginalFilename() + ":"); // 파일 이름 출력
         System.out.println(multipartFile.getSize()); // 파일 사이즈 출력

         AttachFileVO vo = new AttachFileVO();

         String uploadFileName = multipartFile.getOriginalFilename();
         vo.setFileName(uploadFileName);
         UUID uuid = UUID.randomUUID(); // UUID: 중복을 방지하기 윟서 랜덤함수 사용
         uploadFileName = uuid.toString() + "_" + uploadFileName;
         File saveFile = new File(uploadPath, uploadFileName);
         try {
            multipartFile.transferTo(saveFile);
            vo.setUuid(uuid.toString()); // o
            vo.setUploadPath(uploadFolderPath); // o
            list.add(vo);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
      model.addAttribute("list", list);
   }

   private String getFolder() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
      // SimpleDateFormat: Date클래스를 이용하여 현재 날짜를 출력하면 영문으로 된 날짜를 리턴한다. 특정 문자열 포맷으로
      // 얻고싶으면 쓰셈 ㅎ

      Date date = new Date();
      String str = sdf.format(date); // 년월일 형식으로 바뀜
      // return str.replace("-", File.separator); //separator: 경로를 저장해줄때 사용
      return str.replace("-", "/");
   } // MIME TYPE(application/OCTET_STREAM)

   @GetMapping(value = "/download.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
   @ResponseBody // download.jsp(X)않갈라면 어노테이션 해야됨 ㅎ다른페이지로 넘어가질 않는다
   // Resource : 파일임
   public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
      System.out.println(fileName);
      Resource resource = new FileSystemResource("C:\\upload\\" + fileName); // 리소스 안의 실제 파일의 경로가 완성!
      String resourceName = resource.getFilename();
      System.out.println(resourceName);

      String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
      // 다운로드 작업 HttpHeaders :데이터를 보낼껀데 이건 html이 아니라 바이너리 데이터니까 다운로드 준비를 해라라는 객체
      // resourceOriginalName : 파일이름임
      HttpHeaders headers = new HttpHeaders();
      try {
         String downloadName = null;
         if (userAgent.contains("Trident")) {
            System.out.println("IE");
            downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replaceAll("\\+", " ");
         } else if (userAgent.contains("Edge")) {
            System.out.println("Edge");
            downloadName =URLEncoder.encode(resourceOriginalName,"UTF-8");
         } else {
            System.out.println("Chrome");
            downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
         }
         headers.add("Content-Disposition", "attachment;filename=" + downloadName);
         // 다운로드 창을 띄우려고 하는거
         // headers.add("Content-Disposition", "attachment;filename="); : 이건 않바뀜
      } catch (Exception e) {
         e.printStackTrace();
      }
      return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
      // 리소스 객체와 헤더 성공여부를 보내면 클라이언트가 어태치문을 띄움
   }
   // @RequestMapping("/download.do")
   // public void download(AttachFileVO vo) {
   // System.out.println(vo);
   // String downFile="C:\\upload\\" + vo.getUploadPath().replaceAll("-",
   // "\\\\")+"\\"+vo.getFileName();
   // System.out.println(downFile);
   // File file = new File(downFile);

   // }

}