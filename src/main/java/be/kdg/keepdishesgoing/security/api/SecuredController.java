//package be.kdg.keepdishesgoing.security.api;
//
//import be.kdg.keepdishesgoing.security.api.dtos.SecuredMessageDto;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/secured")
//public class SecuredController {
//
//    @GetMapping("message")
//    public SecuredMessageDto getMessage(){
//        return SecuredMessageDto.builder()
//                .message("I'm a secured message")
//                .build();
//    }
//
//}
