package kr.ac.snu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MySQLConnectionTest {
    
    @Inject
    private DataSource ds;
 
    @Test
    public void testConnection() throws Exception {
 
        try  {
        	Connection con = ds.getConnection();
        			
            System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
         // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "select distinct * from test.`disease-gene_pairs`;";

            // create the java statement
            Statement st = con.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            Map<String,List<String>> mapDiseaseByGene = new HashMap<String,List<String>>();
            
            while (rs.next())
            {
              String targetGene = rs.getString("gene");
              String disease = rs.getString("disease");
              
              // print the results
              if(mapDiseaseByGene.containsKey(targetGene))	{	//exist
            	  List<String> listExistDisease = mapDiseaseByGene.get(targetGene);
            	  
            	  if(listExistDisease.contains(disease))	{	//exist target gene-disease pair.
            		  continue;
            	  }
            	  else	{	//new disease related to target gene.
            		  listExistDisease.add(disease);
            	  }
            	  
            	  mapDiseaseByGene.put(targetGene,listExistDisease);
            	  
              }
              else	{	//new TargetGene
            	  List<String> listDisease = new ArrayList<String>();
            	  listDisease.add(disease);
            	  
            	  mapDiseaseByGene.put(targetGene, listDisease);
              }
            }
            
            //print distribution of target gene-disease pair.
            System.out.println("targetGene\tassociated with better prognosis diseases\tnumber of diseases related target gene");
            for(String gene: mapDiseaseByGene.keySet())	{
            	List<String> listDisease = mapDiseaseByGene.get(gene);
            	System.out.println(gene + "\t" + listDisease + "\t" + listDisease.size());
            }
            
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
