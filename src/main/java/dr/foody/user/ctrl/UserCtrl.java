package dr.foody.user.ctrl;

import dr.foody.student.dto.StudentDto;
import dr.foody.user.dto.UserDto;
import dr.foody.user.svc.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCtrl {
    @Autowired
    UserSvc userService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute UserDto userDto) {
        return userService.getList(userDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute UserDto userDto) {
        return userService.regist(userDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return userService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute UserDto userDto) {
        return userService.modify(userDto);
    }
}
