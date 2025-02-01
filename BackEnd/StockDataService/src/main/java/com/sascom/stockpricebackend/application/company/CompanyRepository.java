package com.sascom.stockpricebackend.application.company;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<CompanyInfo> findCompanyById(Long companyId) {
        String sql = "select company_id, code, name from company where company_id = ?";

        try {
            CompanyInfo companyInfo = jdbcTemplate.queryForObject(sql, companyInfoRowMapper(), companyId);
            return Optional.ofNullable(companyInfo);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<CompanyInfo> findCompanyByName(String companyName) {
        String sql = "select company_id, code, name from company where name = ?";

        try {
            CompanyInfo companyInfo = jdbcTemplate.queryForObject(sql, companyInfoRowMapper(), companyName);
            return Optional.ofNullable(companyInfo);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }


    public List<CompanyInfo> findAll() {
        String findAllCompanySql = "select company_id, code, name from company";

        return jdbcTemplate.query(findAllCompanySql, (rs, rowNum) -> {
            Long id = rs.getLong("company_id");
            String code = rs.getString("code");
            String name = rs.getString("name");

            return new CompanyInfo(id, code, name);
        });
    }

    public RowMapper<CompanyInfo> companyInfoRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("company_id");
            String code = rs.getString("code");
            String name = rs.getString("name");

            return new CompanyInfo(id, code, name);
        };
    }
}
