import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.util.FileManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 将InfModel数据写入文件中
 *
 */
public class RDFWrite {

    public static void main(String []args) throws IOException {
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

}
