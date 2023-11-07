package dr.foody.student.svc;

import dr.foody.student.dao.StudentDao;
import dr.foody.student.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description    :
 * packageName    : dr.foody.student.svc
 * fileName       : StudentSvc
 * author         : loves
 * date           : 2023-10-18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-18        loves       최초 생성
 */
@Slf4j
@Service
public class StudentSvc  {
    @Autowired
    StudentDao studentDao ;


    public Object getList( StudentDto studentDto){
        return studentDao.getList(studentDto );
    }
    public Object modify( StudentDto studentDto ){
        return studentDao.mod(studentDto);
    }
    public Object regist( StudentDto studentDto ){
        return studentDao.reg(studentDto);
    }
    public Object delete( Integer idx ){
        return studentDao.del(idx);
    }

}
