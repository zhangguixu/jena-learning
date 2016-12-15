package inference;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 我们通过reasoner和data来获取的InfModel。
 * InfModel的实质就是根据已有的规则和数据合成了新的全面的RDF数据。
 * 因此它可以被存储，读取，查询。
 * 也就是说reasoner应用于Data的时刻在于生成InfModel，后面的查询都不涉及运算。
 */
public class InfModelStore {

    /**
     * 生成InfModel，将其存储到文件中
     *
     * @throws IOException
     */
    public static void write() throws IOException {
        // 创建一个文件
        File f = new File("data/infmodel.ttl");
        if(f.exists() == false) {
            f.createNewFile();
        }
        // 通过文件对象创建输出流
        FileOutputStream fos = new FileOutputStream(f);


        // 可以将其视为本体库
        Model schema = FileManager.get().loadModel("data/owlDemoSchema.owl");
        // 加载rdf数据
        Model data = FileManager.get().loadModel("data/owlDemoData.rdf");

        // 获取推理器
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();

        // 加入我们预先设置好的模式（本体库）
        reasoner = reasoner.bindSchema(schema);

        // 根据数据和reasoner，获取InfModel
        InfModel inf = ModelFactory.createInfModel(reasoner, data);

        // 将获取的InfModel保存下来
        RDFDataMgr.write(fos, inf, RDFFormat.TTL);
    }

    /**
     * 从文件中读取InfModel（其实就是普通的rdf数据）
     * 进行查询。
     *
     */
    public static void read() {
        Model inf = FileManager.get().loadModel("data/infmodel.ttl");

        System.out.println("查询示例： 实例识别");
        Resource gamingComputer = inf.getResource("urn:x-hp:eg/GamingComputer");
        Resource whiteBox = inf.getResource("urn:x-hp:eg/whiteBoxZX");
        if(inf.contains(whiteBox, RDF.type, gamingComputer)) {
            System.out.println("White box recognized as gaming computer");
        } else {
            System.out.println("Failed to recognize white box correctly");
        }
    }


    public static void main(String []args) throws IOException {
        System.out.println("写入infModel数据到文件中");
        write();
        System.out.println("读infModel数据，然后进行查询");
        read();
    }

}
