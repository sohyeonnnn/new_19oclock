package com.refactoring.ilgusi.presentation.chat;

import com.refactoring.ilgusi.domain.chat.Chat;
import com.refactoring.ilgusi.domain.chat.ChatContent;
import com.refactoring.ilgusi.domain.chat.interfaces.ChatService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.service.ServiceTrade;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;

    // 채팅방 생성
    @ResponseBody
    @PostMapping("/makeRoom")
    public HashMap<String, Integer> makeRoom(int serviceNo, int userNo, int freeNo, int memberNo) {

        // 이미 생성된 방인지 확인
        Chat oneChat = chatService.selectOneRoom(serviceNo, userNo, freeNo);

        if (oneChat != null) {// 이미 있는 방이면
            // 이미 있는 방일때 return값
            HashMap<String, Integer> result = new HashMap<String, Integer>();
            int chatNo = oneChat.getChatNo();
            result.put("chatNo", chatNo);

            // 관리자가 메세지 보낼때
            if (serviceNo == 0) {
                return result;
            } else {////////////////////////////////////////////////////////// 회원이 프리랜서에게 문의버튼 눌렀는데
                ////////////////////////////////////////////////////////// 원래 있는 방일때
                return result;

            }
        } else {

            // 방이 아직 없으면 생성
            chatService.createChat(serviceNo, userNo, freeNo);
            Chat chat = chatService.selectOneRoom(serviceNo, userNo, freeNo);
            int chatNo = chat.getChatNo();

            // 관리자와의 채팅방일경우
            if (serviceNo == 0) {
                HashMap<String, Integer> result = new HashMap<String, Integer>();
                result.put("chatNo", chatNo);
                return result;
            } else {
                // 관리자와의 방이 아닐때
                // 생성한 방에 기본메세지 insert
                String welcomeMsg = "문의를 시작합니다!";

                chatService.insertChat(chatNo, welcomeMsg);
            }

            // 찜한 서비스에서 문의시작 했을경우
            // 채팅방 만든 서비스는 찜한 서비스에서 삭제
           /* Favorite oneFavorite = new Favorite();
            oneFavorite.setMNo(mNo);
            oneFavorite.setSNo(serviceNo);
            chatService.deleteOneFavorite(oneFavorite);*/
        }
        return null;
    }

    // 채팅방 입장
    @RequestMapping("/enterRoom")
    public String enterRoom(HttpServletRequest req, Model model, int chatNo, int serviceNo, Integer yourNo, Integer myNo,
                            String mGrade) {
/*
        if (mGrade == null) {

            if (myNo == null) {/////////////////////////////////////////////////////////////// 채팅리스트에서 채팅방입장할때
                // 채팅방 번호로 채팅내용 불러오기
                List<ChatContent> content = chatService.chatContentList(chatNo);

                // 서비스 정보
                List<ServiceInfoDto> serviceList = chatService.selectService(serviceNo);
                ServiceInfoDto oneService = serviceList.get(0);

                // 상대가 보낸 메세지 읽음으로 update
                HashMap<String, Object> roomAndId = new HashMap<String, Object>();
                roomAndId.put("mId", yourNo);
                roomAndId.put("rNo", chatNo);
                chatService.updateReadStatus(chatNo, yourNo);

                // 견적서 작성여부
                // yourId:상대 아이디, myId:내아이디
                HttpSession session = req.getSession();
                Member loginMember = (Member) session.getAttribute("loginMember");

                HashMap<String, Integer> tradeInfo = new HashMap<String, Integer>();

                // 프리랜서로 로그인했을때
                if (loginMember.getMemberGrade() == RoleEnum.FREELANCER) {
                    // 상대방번호,서비스번호로 거래 견적서 작성 여부 확인
                    Member oneUser = chatService.selectOneMember(yourId);
                    int mNo = oneUser.getMemberNo();
                    tradeInfo.put("serviceNo", serviceNo);
                    tradeInfo.put("mNo", mNo);

                    // 의뢰인의 신고횟수가 4이상인지 확인
                    int warningCount = oneUser.getWarningCount();
                    System.out.println("myId_warningcount:" + warningCount);
                    if (warningCount > 3) {
                        model.addAttribute("black", "black");
                    }

                } else {// 일반회원으로 로그인했을때
                    // 내 회원번호로 견적서 작성 여부 확인
                    int mNo = loginMember.getMemberNo();
                    tradeInfo.put("serviceNo", serviceNo);
                    tradeInfo.put("mNo", mNo);
                }

                List<ServiceTrade> tradeList = chatService.tradeList(tradeInfo);
                if (tradeList.size() != 0) {
                    if (tradeList.get(0) != null) { // 제일 최신 거래내역
                        int status = tradeList.get(0).getTradeStatus();
                        System.out.println("거래상태 :" + status);
                        model.addAttribute("status", status);
                    }
                }

                model.addAttribute("chatNo", chatNo);
                model.addAttribute("freeId", yourNo);
                model.addAttribute("content", content);
                model.addAttribute("service", oneService);
            }

            else if (serviceNo == 0) {///////////////////////////////////////////////////////// 알림 채팅방으로 입장할때
                // mId와 관리자 room 찾기
                HashMap<String, Object> room = new HashMap<String, Object>();
                room.put("serviceNo", serviceNo);
                room.put("userId", myNo);
                room.put("freeId", yourNo);

                Chat noti = chatService.selectOneRoom(serviceNo, myNo, yourNo);
                int rNo = -1;
                // 이미 있는 방이면
                if (noti != null) {
                    rNo = noti.getChatNo();
                }

                // 상대가 보낸 메세지 읽음으로 update
                HashMap<String, Object> roomAndId = new HashMap<String, Object>();
                roomAndId.put("mId", "admin");
                roomAndId.put("rNo", rNo);
                chatService.updateReadStatus(chatNo, yourNo);

                // 채팅방 번호로 채팅내용 불러오기
                List<ChatContent> content = chatService.chatContentList(rNo);

                List<ServiceInfoDto> serviceList = chatService.selectService(0);
                ServiceInfoDto oneService = serviceList.get(0);

                model.addAttribute("chatNo", rNo);
                model.addAttribute("freeId", yourNo);
                model.addAttribute("content", content);
                model.addAttribute("service", oneService);

            }
        } else { // mGrade값을 주면

            // 문의하려는 서비스정보 가져오기
            List<ServiceInfoDto> serviceList = chatService.selectService(serviceNo);
            ServiceInfoDto oneService = serviceList.get(0);

            // 채팅방 정보
            HashMap<String, Object> room = new HashMap<String, Object>();
            room.put("serviceNo", serviceNo);
            room.put("userId", myNo);
            room.put("freeId", yourNo);

            // 만든 방 가져오기
            Chat oneRoom = chatService.selectOneRoom(serviceNo, myNo, yourNo);
            int rNo = oneRoom.getChatNo();

            HashMap<String, Object> roomAndId = new HashMap<String, Object>();
            if (mGrade.equals("1")) {
                // 상대가 보낸 메세지 읽음으로 update
                roomAndId.put("mId", yourNo);
                roomAndId.put("rNo", rNo);
                chatService.updateReadStatus(chatNo, yourNo);

            } else if (mGrade.equals("2")) {///////////////////////////////////////// 프리랜서가 의뢰글보고 채팅시작할때
                // 상대가 보낸 메세지 읽음으로 update
           *//*     System.out.println("내아이디:"+yourId);
                System.out.println("의뢰인아이디:"+myId);*//*
                roomAndId.put("mId", myNo);
                roomAndId.put("rNo", rNo);
                chatService.updateReadStatus(chatNo, yourNo);
                // 의뢰인의 신고횟수가 4이상인지 확인
                Member oneMember = chatService.selectOneMember(myNo);
                int warningCount = oneMember.getWarningCount();
                System.out.println("mId_warningcount:" + warningCount);

                if (warningCount > 3) {
                    model.addAttribute("black", "black");
                }

            }

            // 채팅방 번호로 채팅내용 불러오기
            List<ChatContent> content = chatService.chatContentList(rNo);

            model.addAttribute("content", content);
            model.addAttribute("service", oneService);
            model.addAttribute("userId", myNo);
            model.addAttribute("freeId", yourNo);
            model.addAttribute("chatNo", rNo);

        }*/
        return "chat/chatContent";
    }

}


