package tdb;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.tdb.TDB;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.apache.jena.tdb.store.DatasetGraphTDB;
import org.apache.jena.vocabulary.RDFS;

/**
 *创建、加载TDB数据库、TDB的
 *
 * （TDB是内存数据库，实际上就是把数据集加载到内存，然后进行操作）
 *
 * 构建TDB数据集，主要是通过类TDBFactory的createDataset方法，
 * 这个方法可以有两个参数：
 *
 * 1. 目录名称，存储数据集和一些辅助信息文件
 * 2. 给一个assembler文件，类似于配置文件，文件中存储数据集的相关信息
 *
 *
 *
 */

public class TDBDatasetExample {
    // 需要在根目录下，创建一个目录dataset，作为TDB数据存储的目录
    public static final String DIR = "dataset";

    public static void write(Model model) {
        String NS = "urn:x-hp-jena:eg/";
        Property p = model.createProperty(NS, "p");
        Property q = model.createProperty(NS, "q");
        model.add(p, RDFS.subPropertyOf, q);
        model.createResource(NS+"a").addProperty(p, "foo");
    }

    /**
     * 写事务操作，在begin() - commit()之间，保证操作的原子性
     *
     * @param dataset
     */

    public static void writeTransaction(Dataset dataset) {
        //进行数据集写操作，写入一些数据
        dataset.begin(ReadWrite.WRITE);
        try {
            // 获取数据集，此时数据集应该为空
            Model model = dataset.getDefaultModel();
            // 为数据集添加数据
            write(model);
            // 提交数据，否则数据集的操作不会被提交
            dataset.commit();
        } finally {
            dataset.end();
        }
    }

    /**
     *
     * 读事务操作
     *
     * @param dataset
     */

    public static void readTransaction(Dataset dataset) {
        // 读取数据
        dataset.begin(ReadWrite.READ);
        Model model = dataset.getDefaultModel();
        model.write(System.out);
        dataset.end();
    }

    /**
     * 通过目录名称构建TDB数据集
     *
     */
    public static void constructingTDBDatasetByDir() {
        System.out.println("给定目录构建数据集...");
        // 根据目录名称，创建数据集
        Dataset dataset = TDBFactory.createDataset(DIR);

        // 进行写事务操作
        writeTransaction(dataset);

        // 进行读操作
        readTransaction(dataset);

        // 保存到磁盘，避免缓存数据丢失
        TDB.sync(dataset);

        // 释放所有TDB占有的系统资源
        TDB.closedown();
    }

    /**
     * 连接已有的TDB数据集，还是通过TDBFactory.createDataset()
     *
     */
    public static void connect() {
        System.out.println("连接数据库...");
        Dataset dataset = TDBFactory.createDataset(DIR);
        // 打印出数据集
        readTransaction(dataset);

        // 释放所有TDB占有的系统资源
        TDB.closedown();
    }


    public static void main(String []args) {
        constructingTDBDatasetByDir();
        connect();
    }

}
