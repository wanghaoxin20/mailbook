package cn.edu.cqupt.nmid.mailbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "cn.edu.cqupt.nmid.mailbook.dao.mapper")
public class MailbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailbookApplication.class, args);
    }

}
