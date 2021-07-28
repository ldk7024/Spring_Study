package kr.smhrd.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.smhrd.domain.AttachFileVO;
import kr.smhrd.mapper.BoardMapper;
import lombok.Data;

@Controller
public class UploadController {
   //@Autowired      //DI->이거 해주면 알아서 찾아감
   //@RequestMapping이거를 겟방식인지 포스트방식인지에 따라서 겟매핑, 포스트매핑 이러케 바꿔도 됨!!
   @Inject
   private BoardMapper boardmapper;
   
   @GetMapping("/uploadForm.do")
   public void uploadForm() {
      //uploadForm.jsp로 감 void면 일로간다!!!
   }
   @PostMapping("/uploadFormAction.do")
   public void uploadFormAction(MultipartFile[] uploadFile, Model model) {    //MultipartFile: 파일 경로나 사이즈 다 들어가야하니까 배열로!(파일이름, 파일 사이즈)
      List<AttachFileVO> list = new ArrayList<AttachFileVO>();
      
	  String uploadFolder="C:\\upload";
      String uploadFolderPath = getFolder();   //2021\07\27
      File uploadPath = new File(uploadFolder,uploadFolderPath);
      if(uploadPath.exists()==false) {
         uploadPath.mkdirs();  //디렉토리 생성
      }
      
      for( MultipartFile multipartFile: uploadFile) {
         System.out.println(multipartFile.getOriginalFilename()+":");     //파일 이름 출력
         System.out.println(multipartFile.getSize());                 //파일 사이즈 출력
         
         AttachFileVO vo = new AttachFileVO();
         
         String uploadFileName = multipartFile.getOriginalFilename();
         vo.setFileName(uploadFileName);  
         UUID uuid = UUID.randomUUID();
         uploadFileName = uuid.toString()+"_"+uploadFileName;
         File saveFile = new File(uploadPath,uploadFileName);
         try {
            multipartFile.transferTo(saveFile);
            vo.setUuid(uuid.toString());
            vo.setUploadPath(uploadFolderPath);
            list.add(vo);
         } catch (Exception e) {
            e.printStackTrace();
         }
         
      }
      model.addAttribute("list",list);
   }
   private String getFolder() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      String str = sdf.format(date);    //년월일 형식으로 바뀜
      return str.replace("-", File.separator);
   }
   @RequestMapping("/download.do")
   public void download(AttachFileVO vo) {
	   System.out.println(vo);
	   String downFile = "C:\\upload\\"+vo.getUploadPath().replaceAll("-","\\\\")+"\\"+vo.getFileName();
	   System.out.println(downFile);
	   File file = new File(downFile);
	   // 다운로드 구현------
	   
   }
}