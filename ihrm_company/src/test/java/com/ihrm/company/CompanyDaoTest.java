package com.ihrm.company;

import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName: CompanyDaoTest
 * Package: com.ihrm.company
 * Description:
 *
 * @Author R
 * @Create 2024/3/21 23:42
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {
    @Autowired
    private CompanyDao companyDao;

    @Test
    public void test() {
        Company company = companyDao.findById("1").get();
        System.out.println(company);
    }
}
