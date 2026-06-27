package org.lollipop.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author: lollipop
 * @Date: 2026/3/19 20:03
 * @Description:
 **/
public class CodeGenerator {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://123.57.108.236:3306/hakimi-dev?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "Hakimi2108.";
        String[] tables = {"user", "media"};

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(
                jdbcUrl,
                username,
                password)
                .build();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(getOutputDir())                       // 输出目录
                .author("lollipop")                              // 作者
                .dateType(DateType.ONLY_DATE)                    // 日期类型
                .commentDate("yyyy-MM-dd")                       // 注释日期格式
                .disableOpenDir()                                // 生成后不打开输出目录
                .build();

        // 包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("org.lollipop")                          // 父包名
                .moduleName("")                                  // 无模块名，直接生成在 parent 下
                .entity("entity")                                // 实体类包名
                .mapper("mapper")                                // mapper 包名
                .xml("mapper.xml")                               // mapper.xml 包名
                .pathInfo(new java.util.HashMap<>() {{
                    put(OutputFile.mapper, getOutputDir() + "/java/org/lollipop/mapper");
                    put(OutputFile.xml, getOutputDir() + "/resources/org/lollipop/mapper");
                    put(OutputFile.entity, getOutputDir() + "/java/org/lollipop/entity");
                }})
                .build();

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude(tables)                      // 需要生成的表名
//                .addTablePrefix("t_", "sys_")                    // 表前缀过滤
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)       // 表名下划线转驼峰
                .columnNaming(NamingStrategy.underline_to_camel) // 列名下划线转驼峰
                .enableChainModel()                              // 链式模型
                .logicDeleteColumnName("is_deleted")             // 逻辑删除字段名
                .versionColumnName("version")                    // 乐观锁字段名
                .enableFileOverride()                            //开启文件覆盖
                .mapperBuilder()
                .enableBaseResultMap()                           // 生成通用 resultMap
                .enableBaseColumnList()                          // 生成通用 columnList
                .enableFileOverride()                            //开启文件覆盖
                .controllerBuilder().disable()
                .serviceBuilder().disable()
                .build();

        // 创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig)
                .packageInfo(packageConfig)
                .strategy(strategyConfig)
                .execute();

        // 执行生成
        autoGenerator.execute();

        System.out.println("代码生成完成！");
    }

    /**
     * 获取输出目录
     */
    private static String getOutputDir() {
        return System.getProperty("user.dir") + "/hakimi-module-repository/src/main";
    }
}