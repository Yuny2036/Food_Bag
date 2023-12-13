package dr.foody.listAllergie.ctrl;

import dr.foody.listAllergie.dto.ListAllergieDto;
import dr.foody.listAllergie.svc.ListAllergieSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listallergie")
public class ListAllergieCtrl {
    @Autowired
    ListAllergieSvc listAllergieService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute ListAllergieDto listAllergieDto) {
        return listAllergieService.getList(listAllergieDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute ListAllergieDto listAllergieDto) {
        return listAllergieService.regist(listAllergieDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return listAllergieService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute ListAllergieDto listAllergieDto) {
        return listAllergieService.modify(listAllergieDto);
    }
}
