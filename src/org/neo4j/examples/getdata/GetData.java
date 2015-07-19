package org.neo4j.examples.getdata;

import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class GetData {
	private static GraphDatabaseService db;

    private static final String DB_PATH = "/Users/zhaomengjia/neo4j-community-2.3.0-M01/data/graph.db";

    public static void main(String args[]) throws IOException{
    	db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
    	registerShutdownHook();
    	try ( Transaction tx = db.beginTx() )
        {
        	String query = "match (p: Person) return count(p)";
        	Result result = db.execute( query );
        	Object personCount = result.columnAs("count(p)").next();
        	System.out.println(personCount);
            tx.success();
        }
    	System.out.println( "Shutting down database ..." );
        db.shutdown();
    }
    
    private static void registerShutdownHook()
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running example before it's completed)
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                db.shutdown();
            }
        } );
    }
}
