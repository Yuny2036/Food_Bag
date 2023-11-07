package dr.foody.student.dao;

import dr.foody.student.dto.StudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description    :
 * packageName    : dr.foody.student.dao
 * fileName       : StudentDao
 * author         : loves
 * date           : 2023-10-18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-18        loves       최초 생성
 */
@Mapper
public interface StudentDao {
    List<StudentDao> getList(StudentDto studentDto );
    Integer mod(StudentDto studentDto);
    Integer reg(StudentDto studentDto);
    Integer del(Integer idx);
}