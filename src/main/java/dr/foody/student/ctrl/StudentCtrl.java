package dr.foody.student.ctrl;

import dr.foody.student.dto.StudentDto;
import dr.foody.student.svc.StudentSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description    :
 * packageName    : dr.foody.student.ctrl
 * fileName       : StudentCtrl
 * author         : loves
 * date           : 2023-10-18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-18        loves       최초 생성
 */
@RestController
@RequestMapping("/student")
public class StudentCtrl {
    @Autowired
    StudentSvc studentService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute StudentDto studentDto) {

        return studentService.getList(studentDto);
    }


    @PostMapping("/regist")
    public Object regInsDev( @ModelAttribute StudentDto studentDto) {
        return studentService.regist(studentDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return studentService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute StudentDto studentDto) {
        return studentService.modify(studentDto);
    }
}
