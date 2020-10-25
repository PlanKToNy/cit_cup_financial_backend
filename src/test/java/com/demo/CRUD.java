package com.demo;

import com.demo.domain.Agency;
import com.demo.domain.IAgency;
import com.demo.domain.IProduct;
import com.demo.domain.Product;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CRUD {
    public CRUD() {
    }


    //产品信息编辑
    static public int updateProduct(String product_name,String product_information) throws IOException {
        if(product_name.equals(""))
            return 1;//产品名不能为空
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProduct mapper = session.getMapper(IProduct.class);
            mapper.updateProduct(product_name,product_information);
            //需要手动提交事务
            session.commit();;
        }
        return 0;
    }
    //机构信息编辑
    static public int updateAgency(String agency_name,String agency_information) throws IOException {
        if(agency_name.equals(""))
            return 1;//机构名不能为空
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAgency mapper = session.getMapper(IAgency.class);
            mapper.updateAgency(agency_name,agency_information);
            //需要手动提交事务
            session.commit();;
        }
        return 0;
    }
    //机构信息显示
    static public void displayAgency(String account) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAgency mapper = session.getMapper(IAgency.class);
            Agency agency = mapper.showAgency(account);
            agency.showInfo();
        }
    }
    //产品信息显示
    static public void displayProduct(String account) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProduct mapper = session.getMapper(IProduct.class);
            List<Product> products = mapper.showProduct(account);
            for (Product product:products) {
                product.showInfo();
            }
        }
    }
    //金融产品首页推荐
    static public void recommendProductByUser(String account) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProduct mapper = session.getMapper(IProduct.class);
            List<Product> products = mapper.recommendProduct(account);
            for (Product product:products) {
                product.showInfo();
            }
        }
    }
    //金融产品分类
    static public void classifyProduct(String classify) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProduct mapper = session.getMapper(IProduct.class);
            List<Product> products = mapper.selectType(classify);
            for (Product product:products) {
                product.showInfo();
            }
        }
    }
    //金融产品搜索
    static public void findProductbyKeyword(String keyword) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProduct mapper = session.getMapper(IProduct.class);
            List<Product> products = mapper.findName(keyword);
            for (Product product:products) {
                product.showInfo();
            }
        }
    }

}
