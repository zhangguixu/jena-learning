package tdb;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;

public class TDBDirectoryExample {
    private final String directory = "../data/";
    private Dataset dataset;
    private Model model;

    public TDBDirectoryExample () {
        // 依照目录路径下的文件创建数据集
        this.dataset = TDBFactory.createDataset(directory);
    }

    public void connect() {
        this.dataset.begin(ReadWrite.WRITE);
    }

    public Model getModel () {
        this.connect();
        this.model = dataset.getDefaultModel();
        return this.model;
    }

    public void close() {
        this.dataset.end();
    }

    public  boolean isExitTDB() {
        return TDBFactory.isBackedByTDB(this.dataset);
    }

    public static void main(String []args){
        TDBDirectoryExample instance = new TDBDirectoryExample();
        Model model = instance.getModel();
        model.write(System.out);
        instance.close();
    }
}
