package dr.foody.calenderMemo.ctrl;

import dr.foody.calenderMemo.dto.CalenderMemoDto;
import dr.foody.calenderMemo.svc.CalenderMemoSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calenderMemo")
public class CalenderMemoCtrl {
    @Autowired
    CalenderMemoSvc calenderMemoService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute CalenderMemoDto calenderMemoDto) {
        return calenderMemoService.getList(calenderMemoDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute CalenderMemoDto calenderMemoDto) {
        return calenderMemoService.regist(calenderMemoDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return calenderMemoService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute CalenderMemoDto calenderMemoDto) {
        return calenderMemoService.modify(calenderMemoDto);
    }
}
