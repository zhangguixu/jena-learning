package tdb;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDB;
import org.apache.jena.tdb.TDBFactory;

/**
 * 查询数据库示例，运行的前提条件是
 *
 * 1. 运行TDBDatasetExample，创建了TDB数据，保存在根目录下的dataset
 * 2. 再使用tdbloader导入data目录下的sparql-example.ttl到TDB数据库中
 */

public class TDBQueryExample {

    public static void query(Model m){
        // 编写查询语句，
        String queryString = "SELECT ?mbox " +
                "WHERE { " +
                "?p <http://xmlns.com/foaf/0.1/name> 'Alice' ." +
                "?p <http://xmlns.com/foaf/0.1/mbox> ?mbox ." +
                "}";

        // 通过QueryFactory解析查询语句，获取Query对象
        Query query = QueryFactory.create(queryString);

        // 通过QueryExecution执行查询语句
        try (QueryExecution qexec = QueryExecutionFactory.create(query, m)) {
            // 获取查询之后的结果
            ResultSet results = qexec.execSelect();

            // 格式化后，输出结果
            ResultSetFormatter.out(System.out, results, query);
        }
    }

    public static void main(String[]args) {
        // 建立连接
        Dataset dataset = TDBFactory.createDataset("dataset");

        // 开始数据库操作
        dataset.begin(ReadWrite.READ);
        // 执行查询语句
        query(dataset.getDefaultModel());
        // 数据库操作结束
        dataset.end();

        // 释放资源
        TDB.closedown();
    }
}
