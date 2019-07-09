package kr.ac.snu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CalcDrugRelatedtoTargetGene {

    public void testDrugsByTargetGene() throws Exception {
    	
        try  {
        	// create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&amp;serverTimezone=UTC";
            Class.forName(myDriver);
            Connection con = DriverManager.getConnection(myUrl, "root", "1q2w1q2w");
            
        			
//            System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
         // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "select distinct gene_name,drug_name,drug_claim_primary_name,drug_chembl_id from test.`dgi_interactions` WHERE drug_chembl_id != '';";
            String query = "select distinct gene_name,drug_name,drug_claim_primary_name,drug_chembl_id " + 
            		"	from test.`dgi_interactions` " + 
            		"	WHERE drug_chembl_id in (select chem.chembl_id" + 
            		"	 from `chembl_25`.`molecule_dictionary` as chem" + 
            		"	 where chem.`max_phase` = 4 and chem.therapeutic_flag = 1" + 
            		"	);";

            // create the java statement
            Statement st = con.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            
            //1. target gene can have several drugs
            Map<String,List<String>> mapDrugsByTargetGene = new HashMap<String,List<String>>();
            
            while (rs.next())
            {
              String targetGene = rs.getString("gene_name");
              String drug_name = rs.getString("drug_name");
//              String drug_claim_primary_name = rs.getString("drug_claim_primary_name");
//              String drug_chembl_id = rs.getString("drug_chembl_id");
              
              // print the results
              if(mapDrugsByTargetGene.containsKey(targetGene))	{	//exist
            	  List<String> listExistDrug = mapDrugsByTargetGene.get(targetGene);
            	  
            	  if(listExistDrug.contains(drug_name))	{	//exist target gene-disease pair.
            		  continue;
            	  }
            	  else	{	//new disease related to target gene.
            		  listExistDrug.add(drug_name);
            	  }
            	  
            	  mapDrugsByTargetGene.put(targetGene,listExistDrug);
            	  
              }
              else	{	//new TargetGene
            	  List<String> listDrug = new ArrayList<String>();
            	  listDrug.add(drug_name);
            	  
            	  mapDrugsByTargetGene.put(targetGene, listDrug);
              }
            }
            
            //print distribution of target gene-disease pair.
            System.out.println("targetGene\tlist of drugs\tnumber of drugs related target gene");
            for(String gene: mapDrugsByTargetGene.keySet())	{
            	List<String> listDrug = mapDrugsByTargetGene.get(gene);
            	System.out.println(gene + "\t" + listDrug + "\t" + listDrug.size());
            }
            
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
    @Test
    public void testTargetGenesByDrug() throws Exception {
 
		try  {
        	// create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&amp;serverTimezone=UTC";
            Class.forName(myDriver);
            Connection con = DriverManager.getConnection(myUrl, "root", "1q2w1q2w");
            
        			
//            System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
         // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "select distinct gene_name,drug_name,drug_claim_primary_name,drug_chembl_id from test.`dgi_interactions` WHERE drug_chembl_id != '';";
            String query = "select distinct gene_name,drug_name,drug_claim_primary_name,drug_chembl_id " + 
            		"	from test.`dgi_interactions` " + 
            		"	WHERE drug_chembl_id in (select chem.chembl_id" + 
            		"	 from `chembl_25`.`molecule_dictionary` as chem" + 
            		"	 where chem.`max_phase` = 4 and chem.therapeutic_flag = 1" + 
            		"	);";

            // create the java statement
            Statement st = con.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            
            //2.  A drug can have several target genes
            Map<String,List<String>> mapTargetGenesByDrug = new HashMap<String,List<String>>();
            
            while (rs.next())
            {
              String targetGene = rs.getString("gene_name");
              String drug_name = rs.getString("drug_name");
//              String drug_claim_primary_name = rs.getString("drug_claim_primary_name");
//              String drug_chembl_id = rs.getString("drug_chembl_id");
              
              // print the results
              if(mapTargetGenesByDrug.containsKey(drug_name))	{	//exist
            	  List<String> listExistGene = mapTargetGenesByDrug.get(drug_name);
            	  
            	  if(listExistGene.contains(targetGene))	{	//exist target gene-disease pair.
            		  continue;
            	  }
            	  else	{	//new disease related to target gene.
            		  listExistGene.add(targetGene);
            	  }
            	  
            	  mapTargetGenesByDrug.put(drug_name,listExistGene);
            	  
              }
              else	{	//new TargetGene
            	  List<String> listGene = new ArrayList<String>();
            	  listGene.add(targetGene);
            	  
            	  mapTargetGenesByDrug.put(drug_name, listGene);
              }
            }
            
            //print distribution of target gene-disease pair.
            System.out.println("drug\tlist of target Gene\tnumber of target genes related to a drug");
            for(String drugName: mapTargetGenesByDrug.keySet())	{
            	List<String> listGene = mapTargetGenesByDrug.get(drugName);
            	System.out.println(drugName + "\t" + listGene + "\t" + listGene.size());
            }
            
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
