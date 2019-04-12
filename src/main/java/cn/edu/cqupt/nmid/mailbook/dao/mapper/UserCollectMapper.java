package cn.edu.cqupt.nmid.mailbook.dao.mapper;

import cn.edu.cqupt.nmid.mailbook.pojo.UserCollect;
import cn.edu.cqupt.nmid.mailbook.pojo.UserCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectMapper {
    long countByExample(UserCollectExample example);

    int deleteByExample(UserCollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    List<UserCollect> selectByExample(UserCollectExample example);

    UserCollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCollect record, @Param("example") UserCollectExample example);

    int updateByExample(@Param("record") UserCollect record, @Param("example") UserCollectExample example);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);
}