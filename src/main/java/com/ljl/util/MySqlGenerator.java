package com.ljl.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 *     代码生成器工具
 * </p>
 * @author lvjunlong
 * @date 2019/4/24 上午9:30
 */
public class MySqlGenerator {

    /**
     * 自动生成代码
     */
    public static void generateCode() {
        generateByTables("com.ljl", "teacher");
    }

    private static void generateByTables(String packageName, String... tableNames) {


        //数据源配置
        String dbUrl = "jdbc:mysql://192.168.55.34:3318/eusdk?useSSL=false";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("admin")
                .setPassword("yushi@123")
                .setDriverName("com.mysql.cj.jdbc.Driver");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel) // 字段生成策略
                .setEntityLombokModel(false)
                .setTablePrefix("")
                .setInclude(tableNames)
                .setEntityLombokModel(true) // lombok实体
                //.setEntityBuilderModel(false) // 【实体】是否为构建者模型（默认 false）
                .setEntityColumnConstant(false) // 【实体】是否生成字段常量（默认 false）// 可通过常量名获取数据库字段名 // 3.x支持lambda表达式
                .setLogicDeleteFieldName("deleted") // 逻辑删除属性名称


                // 自定义实体父类
                // .setSuperEntityClass(packageName + ".entity.SuperEntity")
                // 自定义实体，公共字段
                //.setSuperEntityColumns(new String[]{"ctime", "utime"})
                .entityTableFieldAnnotationEnable(true);



        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("lvjunlong")
                .setOutputDir("src/main/java")
                //.setFileOverride(false)
                .setEnableCache(false)//XML 二级缓存
                .setBaseResultMap(false)//XML ResultMap
                .setBaseColumnList(true)
                .setKotlin(false)//是否生成 kotlin 代码
                .setMapperName("%sMapper")
                //.setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setDateType(DateType.ONLY_DATE) //只使用 java.util.date 代替
                .setIdType(IdType.ID_WORKER)
                .setOpen(false);


        // 包信息配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent(packageName)
                .setController("controller")
                .setEntity("entity")
                .setMapper("mapper");
        //.setXml("mapper");

        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();

    }

    public static void main(String[] args) {
        generateCode();
    }
}