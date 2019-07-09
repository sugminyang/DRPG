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

public class CalcDiseasesRelatedtoDrug {

	@Test
	public void testDiseasesRelatedtoDrug() throws Exception {
		 
        try  {
        	// create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&amp;serverTimezone=UTC";
            Class.forName(myDriver);
            Connection con = DriverManager.getConnection(myUrl, "root", "1q2w1q2w");
            
        			
//            System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
         // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "select distinct dgi.gene_name as gene, triplet.disease as disease, dgi.drug_name as drug from test.`disease-gene_pairs` as triplet join test.`dgi_interactions` as dgi on(triplet.gene = dgi.gene_name) WHERE drug_chembl_id != '';";
            String query = "select distinct triplet.disease as disease, dgi.drug_name as drug " + 
            		"	from test.`disease-gene_pairs` as triplet " + 
            		"	join test.`dgi_interactions` as dgi " + 
            		"	on(triplet.gene = dgi.gene_name) " + 
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
            Map<String,List<String>> mapDiseasesByDrug = new HashMap<String,List<String>>();
            
            while (rs.next())
            {
              String disease = rs.getString("disease");
              String drug_name = rs.getString("drug");
              
//              String drug_claim_primary_name = rs.getString("drug_claim_primary_name");
//              String drug_chembl_id = rs.getString("drug_chembl_id");
              
              // print the results
              if(mapDiseasesByDrug.containsKey(drug_name))	{	//exist
            	  List<String> listExistDisease = mapDiseasesByDrug.get(drug_name);
            	  
            	  if(listExistDisease.contains(disease))	{	//exist target gene-disease pair.
            		  continue;
            	  }
            	  else	{	//new disease related to target gene.
            		  if(disease.equalsIgnoreCase("tumor") || disease.equalsIgnoreCase("tumors") 
            				  || disease.equalsIgnoreCase("carcinoma") || disease.equalsIgnoreCase("carcinomas")
            				  || disease.equalsIgnoreCase("tumour") || disease.equalsIgnoreCase("cancer")
            				  || disease.equalsIgnoreCase("cancers") || disease.equalsIgnoreCase("metastasis")
            				  || disease.equalsIgnoreCase("adenocarcinoma") || disease.equalsIgnoreCase("tumours")
            				  || disease.equalsIgnoreCase("Adenoma") || disease.equalsIgnoreCase("Adenomas")
            				  || disease.equalsIgnoreCase("adenocarcinomas") || disease.equalsIgnoreCase("metastases")
            				  || disease.equalsIgnoreCase("overall survival") || disease.equalsIgnoreCase("os")
            				  || disease.equalsIgnoreCase("death") || disease.equalsIgnoreCase("malignancies")
            				  || disease.contains("[OBSOLETE]") || disease.equalsIgnoreCase("Sarcoma")
            				  || disease.equalsIgnoreCase("Neoplasms") || disease.equalsIgnoreCase("Neoplasm")
            				  || disease.equalsIgnoreCase("Neoplasm Metastasis"))	{
            		  }
            		  else	{
            			  listExistDisease.add(disease);
            			  mapDiseasesByDrug.put(drug_name,listExistDisease);
            		  }

            	  }
            	  
            	  
            	  
              }
              else	{	//new TargetGene
            	  
            	  if(disease.equalsIgnoreCase("tumor") || disease.equalsIgnoreCase("tumors") 
        				  || disease.equalsIgnoreCase("carcinoma") || disease.equalsIgnoreCase("carcinomas")
        				  || disease.equalsIgnoreCase("tumour") || disease.equalsIgnoreCase("cancer")
        				  || disease.equalsIgnoreCase("cancers") || disease.equalsIgnoreCase("metastasis")
        				  || disease.equalsIgnoreCase("adenocarcinoma") || disease.equalsIgnoreCase("tumours")
        				  || disease.equalsIgnoreCase("Adenoma") || disease.equalsIgnoreCase("Adenomas")
        				  || disease.equalsIgnoreCase("adenocarcinomas") || disease.equalsIgnoreCase("metastases")
        				  || disease.equalsIgnoreCase("overall survival") || disease.equalsIgnoreCase("os")
        				  || disease.equalsIgnoreCase("death") || disease.equalsIgnoreCase("malignancies")
        				  || disease.contains("[OBSOLETE]") || disease.equalsIgnoreCase("Sarcoma")
        				  || disease.equalsIgnoreCase("Neoplasms") || disease.equalsIgnoreCase("Neoplasm")
        				  || disease.equalsIgnoreCase("Neoplasm Metastasis"))	{
        		  }
        		  else	{
        			  List<String> listDisease = new ArrayList<String>();
        			  listDisease.add(disease);
        			  mapDiseasesByDrug.put(drug_name, listDisease);
        		  }
              }
            }
            
            //print distribution of target gene-disease pair.
            System.out.println("drug\tlist of diseases\tnumber of diseases related to a drug");
            for(String drug: mapDiseasesByDrug.keySet())	{
            	List<String> listDisease = mapDiseasesByDrug.get(drug);
            	System.out.println(drug + "\t" + listDisease + "\t" + listDisease.size());
            }
            
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
