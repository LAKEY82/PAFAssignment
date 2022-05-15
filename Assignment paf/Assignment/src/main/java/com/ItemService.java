 package com; 
import model.Item; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Items") 
public class ItemService 
{ 
 Item itemObj = new Item(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readItems() 
  { 
  return itemObj.readItems(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertItem(@FormParam("period") String period, 
  @FormParam("units") String units, 
  @FormParam("unitPrice") String unitPrice, 
  @FormParam("total") String total) 
 { 
  String output = itemObj.insertItem(period, units, unitPrice, total); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateItem(String itemData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
 //Read the values from the JSON object
  String powerID = itemObject.get("powerID").getAsString(); 
  String period = itemObject.get("period").getAsString(); 
  String units = itemObject.get("units").getAsString(); 
  String unitPrice = itemObject.get("unitPrice").getAsString(); 
  String total = itemObject.get("total").getAsString(); 
  String output = itemObj.updateItem(powerID, period, units, unitPrice, total); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteItem(String itemData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String powerID = doc.select("powerID").text(); 
  String output = itemObj.deleteItem(powerID); 
 return output; 
 }
}
