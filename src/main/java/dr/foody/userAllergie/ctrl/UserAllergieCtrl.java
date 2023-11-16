package dr.foody.userAllergie.ctrl;

import dr.foody.userAllergie.dto.UserAllergieDto;
import dr.foody.userAllergie.svc.UserAllergieSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAllergie")
public class UserAllergieCtrl {
    @Autowired
    UserAllergieSvc userAllergieService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute UserAllergieDto userAllergieDto) {
        return userAllergieService.getList(userAllergieDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute UserAllergieDto userAllergieDto) {
        return userAllergieService.regist(userAllergieDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return userAllergieService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute UserAllergieDto userAllergieDto) {
        return userAllergieService.modify(userAllergieDto);
    }
}
