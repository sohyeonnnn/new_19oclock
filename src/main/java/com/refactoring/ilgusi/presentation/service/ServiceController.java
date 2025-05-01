package com.refactoring.ilgusi.presentation.service;

import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.common.ResultData;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping("/serviceList")
    public String serviceList(){
        //serviceRepository.searchService()
        return "";
    }

    @ResponseBody
    @PostMapping("/isPossibleMakeService")
    public ResponseEntity<?> isPossibleMakeService(@RequestParam("mNo") int mNo) {
        return ResponseEntity.ok(ResultData.builder().data(serviceService.selectFreeServiceCount(mNo)).build());
    }

    @GetMapping("/serviceJoinFrm")
    public String serviceJoinFrm() {
        return "freelancer/servicejoin";
    }

    @PostMapping("/serviceJoin")
    public String serviceJoin(ServiceInsertDto join, Model model, MultipartFile[] ssImg, HttpServletRequest request) {
       /* String root = request.getSession().getServletContext().getRealPath("/");
        String path = root + "/upload/service/";
        ArrayList<ServiceFile> fileList = new ArrayList<ServiceFile>();

        for (MultipartFile file : ssImg) { // 파일이 여러개라 반복문으로 처리
            String filename = file.getOriginalFilename();
            System.out.println("파일 이름" + filename);
            String filepath = CommonUtil.fileRename(path, filename);
            try {
                byte[] bytes = file.getBytes();
                File upFile = new File(path + filepath);
                FileOutputStream fos = new FileOutputStream(upFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(bytes);
                bos.close();

                ServiceFile f = new ServiceFile();
                f.setFilename(filename);
                f.setFilepath(filepath);
                fileList.add(f); // 데이터베이스 처리를 위해 객체화 해서 list에 추가
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        join.setFileList(fileList);
        join.setSImg(fileList.get(0).getFilepath());*/

        serviceService.insertService(join);

        String msg = "서비스를 등록하였습니다";
        String loc = "/freelancerServiceList.do?mId=" + join.getMId() + "&order=refuse";

        return MsgRedirectHelper.success(model,msg,loc);
    }

}
