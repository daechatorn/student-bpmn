package org.man.consumer.repository;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.exception.BusinessException;
import org.man.consumer.model.Student;
import org.man.consumer.model.mapper.StudentMapper;
import org.man.consumer.model.request.StudentInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Repository
@Slf4j
public class StudentRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "student";

    public Optional<List<Student>> getAll() {

        final String sql = new StringJoiner(SPACE)
                .add("SELECT *")
                .add("FROM "+TABLE_NAME)
                .toString();

        log.debug("SQL getAllStudent = {}", sql);
        try {
            return Optional.of(jdbcTemplate.query(sql, new StudentMapper()));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new BusinessException(1999);
        }
    }

    public int insert(StudentInfoRequest request){
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(cid, name, created_date)")
                .append("VALUES (:cid, :name, now())");
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("cid", request.getCid());
        namedParameters.put("name", request.getName());
        return namedParameterJdbcTemplate.update(sql.toString(), namedParameters);
    }

}
