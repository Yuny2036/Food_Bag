package dr.foody.listillness.ctrl;

import dr.foody.listillness.dto.ListillnessDto;
import dr.foody.listillness.svc.ListillnessSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listillness")
public class ListillnessCtrl {
    @Autowired
    ListillnessSvc list_illnessService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute ListillnessDto listillnessDto) {
        return list_illnessService.getList(listillnessDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute ListillnessDto listillnessDto) {
        return list_illnessService.regist(listillnessDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return list_illnessService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute ListillnessDto listillnessDto) {
        return list_illnessService.modify(listillnessDto);
    }
}
