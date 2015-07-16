package org.neo4j.examples.server.plugins;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
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
    public Integer getZDRY( @Source GraphDatabaseService graphDb )
    {
        Integer personCount = 0;
        try (Transaction tx = graphDb.beginTx())
        {
        	ExecutionEngine engine = new ExecutionEngine( graphDb, null );
        	ExecutionResult result = engine.execute( "match (p: Person) return p" );
        	personCount = result.length();
        	
            tx.success();
        }
        return personCount;
    }
}
// END SNIPPET: GetAll
