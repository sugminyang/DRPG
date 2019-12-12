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
            String query = "SELECT drug_claim_primary_name FROM `test.dgi_interactions` where gene_name like 'CDH2';";

            // create the java statement
            Statement st = con.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // print the results
        	List<String> listDrug = new ArrayList<String>();
        	
        	// iterate through the java resultset
            while (rs.next())
            {
            	String drugName = rs.getString("drug_claim_primary_name");

            	if(!listDrug.contains(drugName))	{	//new disease related to target gene.
            		listDrug.add(drugName);
            	}
            }
            
            //print distribution of target gene-disease pair.
            for(String drug : listDrug)	{
            	System.out.println(drug);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
