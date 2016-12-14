package arq;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

/**
 * ARQ中基础查询的例子，学习如何解析SPARQL查询语句，并且执行查询
 *
 * 关键类有
 *
 * 1. Query
 * 2. QueryFactory
 * 3. QueryExecution
 * 4. QueryExecutionFactory
 * 5. DatasetFactory
 *
 * 查询之后，返回结果中的涉及的关键类有
 *
 * 1. QuerySolution
 * 2. ResultSet
 * 3. ResultSetFormatter
 */

public class QueryExample {

    // 完整的解析->查询->处理结果流程
    public static void query() {
        // 加载数据
        Model model = FileManager.get().loadModel("data/sparql-example.ttl");

        // 编写查询语句，可以看到挺麻烦的
        String queryString = "SELECT ?mbox " +
                "WHERE { " +
                "?p <http://xmlns.com/foaf/0.1/name> 'Alice' ." +
                "?p <http://xmlns.com/foaf/0.1/mbox> ?mbox ." +
                "}";

        // 通过QueryFactory解析查询语句，获取Query对象
        Query query = QueryFactory.create(queryString);

        // 通过QueryExecution执行查询语句
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            // 获取查询之后的结果
            ResultSet results = qexec.execSelect();

            // 遍历结果，进行处理
            for(; results.hasNext() ; ) {
                // 获取每一条查询结果
                QuerySolution soln = results.nextSolution();
                Resource r = soln.getResource("mbox");
                // RDFNode x = soln.get("varName")
                // Literal l = soln.getLiteral("varL")
                System.out.println("查询结果为：" + r.toString());
            }
        }
    }

    // 上面的步骤可以简化成以下的
    public static void simplifyQuery() {
        // 加载数据
        Model model = FileManager.get().loadModel("data/sparql-example.ttl");

        // 编写查询语句，可以看到挺麻烦的
        String queryString = "SELECT ?mbox " +
                "WHERE { " +
                "?p <http://xmlns.com/foaf/0.1/name> 'Alice' ." +
                "?p <http://xmlns.com/foaf/0.1/mbox> ?mbox ." +
                "}";
        // 直接通过QueryExecutionFactory来完成解析，并且获取执行器
        try (QueryExecution qexec = QueryExecutionFactory.create(queryString, model)) {
            // 获取查询之后的结果
            ResultSet results = qexec.execSelect();

            // 遍历结果，进行处理
            for(; results.hasNext() ; ) {
                // 获取每一条查询结果
                QuerySolution soln = results.nextSolution();
                Resource r = soln.getResource("mbox");
                // RDFNode x = soln.get("varName")
                // Literal l = soln.getLiteral("varL")
                System.out.println("查询结果为：" + r.toString());
            }
        }
    }

    public static void main(String []args) {
        System.out.println("------完整的查询流程---------");
        query();
        System.out.println("------简化的查询流程---------");
        simplifyQuery();
    }

}
