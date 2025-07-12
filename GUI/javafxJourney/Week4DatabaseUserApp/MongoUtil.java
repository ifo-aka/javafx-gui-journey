package GUI.javafxJourney.Week4DatabaseUserApp;

import GUI.javafxJourney.Week4DatabaseUserApp.models.User;
import GUI.javafxJourney.Week4DatabaseUserApp.models.UserForUser;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class MongoUtil {
    static MongoClient clients = MongoClients.create("mongodb://localhost:27017");
  static   MongoDatabase database = clients.getDatabase("JavaLogin");
  static MongoCollection<Document> collection = database.getCollection("Login");







   public  static  JSONObject checkLogin(String username,String password){
       String nameFromBase;
       String passwordFromBase;
       String role;
       JSONObject object = new JSONObject();
       for (Document users : collection.find()){
            nameFromBase= users.getString("username");
            passwordFromBase = users.getString("password");
            role = users.getString("role");

            if(nameFromBase.equals(username) && passwordFromBase.equals(password) ){

                object.put("status","success");
                object.put("role",role);
                return object;
            }

       }



     object.put("status","failed");
       object.put("role","norole");
       return object;
   }


   public static String registerToMongo(String name,String email,String password,String role){
       Document user = new Document("username",name).append("email",email).append("password",password).append("role",role);
       collection.insertOne(user);
     return "success";
   }


   public static Document getOneUser(String name, String password){
       System.out.println(name+ password);
       FindIterable<Document> documents  =collection.find(new Document("username",name).append("password",password));
       for (Document user :documents){
          if(user.getString("username").equals(name) && user.getString("password").equals(password)){
              return user;
          }


       }
       return null;
   }



    public static List<Document> getAllUsers() {
       try{
            return collection.find().into(new ArrayList<>());
        } catch (Exception e) {
           System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    public static void updateUserInMongo(User user) {
       Document query = new Document("_id", user.getId()); // assuming username is unique
        Document updatedData = new Document("$set", new Document("email", user.getEmail())
                .append("role", user.getRole()));
        collection.updateOne(query, updatedData);
    }
    public static void updateUserViaUserInMongo(UserForUser user) {
        Document query = new Document("_id", new ObjectId(user.getId()));
        Document updatedData = new Document("$set", new Document("email", user.getEmail())
                .append("password", user.getPassword()).append("username",user.getUserName()));
        collection.updateOne(query, updatedData);
    }
    public static void deleteUser(String id){
        System.out.println("enter");
     collection.deleteOne(new Document("_id",new ObjectId(id)));
    }


}
