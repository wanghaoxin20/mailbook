package cn.edu.cqupt.nmid.mailbook.dao.mapper;

import cn.edu.cqupt.nmid.mailbook.pojo.BookRecord;
import cn.edu.cqupt.nmid.mailbook.pojo.BookRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookRecordMapper {
    long countByExample(BookRecordExample example);

    int deleteByExample(BookRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookRecord record);

    int insertSelective(BookRecord record);

    List<BookRecord> selectByExample(BookRecordExample example);

    BookRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookRecord record, @Param("example") BookRecordExample example);

    int updateByExample(@Param("record") BookRecord record, @Param("example") BookRecordExample example);

    int updateByPrimaryKeySelective(BookRecord record);

    int updateByPrimaryKey(BookRecord record);
}