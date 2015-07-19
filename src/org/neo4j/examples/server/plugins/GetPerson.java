package org.neo4j.examples.server.plugins;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.server.plugins.Description;
import org.neo4j.server.plugins.PluginTarget;
import org.neo4j.server.plugins.ServerPlugin;
import org.neo4j.server.plugins.Source;

// START SNIPPET: GetAll
public class GetPerson extends ServerPlugin
{
    @Description( "Get all person amount" )
    @PluginTarget( GraphDatabaseService.class )
    public String getPersonNum( @Source GraphDatabaseService graphDb )
    {
    	String personCount;
        try ( Transaction tx = graphDb.beginTx() )
        {
        	String query = "match (p: Person) return count(p)";
        	Result result = graphDb.execute( query );
        	personCount = result.resultAsString();
//        	System.out.println(result.columnAs("count(p)").next());
            tx.success();
        }
		return personCount;
    }
}
// END SNIPPET: GetAll
