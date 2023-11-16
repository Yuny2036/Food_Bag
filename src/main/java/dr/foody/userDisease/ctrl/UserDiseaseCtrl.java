package dr.foody.userDisease.ctrl;

import dr.foody.userDisease.dto.UserDiseaseDto;
import dr.foody.userDisease.svc.UserDiseaseSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDisease")
public class UserDiseaseCtrl {
    @Autowired
    UserDiseaseSvc userDiseaseService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute UserDiseaseDto userDiseaseDto) {
        return userDiseaseService.getList(userDiseaseDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute UserDiseaseDto userDiseaseDto) {
        return userDiseaseService.regist(userDiseaseDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return userDiseaseService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute UserDiseaseDto userDiseaseDto) {
        return userDiseaseService.modify(userDiseaseDto);
    }
}
