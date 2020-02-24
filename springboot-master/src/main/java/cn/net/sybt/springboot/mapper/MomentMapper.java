package cn.net.sybt.springboot.mapper;

import cn.net.sybt.springboot.bean.Moment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface MomentMapper {
    @Select("SELECT * FROM moment WHERE class_id = #{classId} ORDER BY upload_time DESC")
    List<Moment> getClassMoment(Integer classId);

    @Insert("insert into moment(class_id, picture_link, upload_time, phone_number) " +
            "values(#{classId}, #{pictureLink}, #{uploadTime}, #{phoneNumber})")
    Integer addMoment(Moment moment);

    @Select("select picture_link from moment where class_id = #{classId} and upload_time = #{uploadTime} " +
            "and phone_number = #{phoneNumber}")
    List<String> getMomentLinks(Integer classId, Date uploadTime, String phoneNumber);

    @Select("SELECT upload_time FROM moment WHERE class_id = #{classId} ORDER BY upload_time DESC")
    List<Date> getAllTimes(Integer classId);
}
