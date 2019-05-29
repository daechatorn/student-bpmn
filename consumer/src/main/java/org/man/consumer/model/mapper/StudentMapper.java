package org.man.consumer.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.man.consumer.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


@Slf4j
public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int i) throws SQLException {
        return new Student()
                .setId(rs.getInt("id"))
                .setCid(rs.getString("cid"))
                .setName(rs.getString("name"))
                .setDate(rs.getDate("created_date"));
    }
}
