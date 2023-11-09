package dr.foody.list_illness.ctrl;

import dr.foody.list_illness.dto.List_illnessDto;
import dr.foody.list_illness.svc.List_illnessSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list_illness")
public class List_illnessCtrl {
    @Autowired
    List_illnessSvc list_illnessService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute List_illnessDto list_illnessDto) {
        return list_illnessService.getList(list_illnessDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute List_illnessDto list_illnessDto) {
        return list_illnessService.regist(list_illnessDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return list_illnessService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute List_illnessDto list_illnessDto) {
        return list_illnessService.modify(list_illnessDto);
    }
}
