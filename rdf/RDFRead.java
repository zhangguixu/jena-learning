package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.*;

public class RDFRead {

    private static String fileName = "data/example.rdf";
    private Model model;

    public Model getModel(){
        return this.model;
    }

    public RDFRead(){
        this.model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);

        if (in == null) {
            throw new IllegalArgumentException("File:" + fileName + " not found");
        }
        model.read(in, "");
    }

    public static void main(String []args){

        Model model = new RDFRead().getModel();

        model.write(System.out);
    }
}
