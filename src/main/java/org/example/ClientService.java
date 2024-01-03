package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Передбач валідацію вхідних даних в методах класу ClientService. Якщо якісь вхідні дані некоректні (наприклад, ми намагаємось створити клієнта з занадто коротким чи довгим іменем) - відповідний метод має викидати Exception.
public class ClientService {
    
    long create(String name){
        if(name.length()<2||name.length()>1000 || !name.matches("^[a-zA-Z]*$")){
       
            throw  new IllegalArgumentException("Incorrect value for name");
        }
        Database db = Database.getInstance();
        Connection conn = db.getConnection();
        long max=0;
        try{
            List<Client> clients= listAll();
          
            for(int i =0;i<clients.size();i++){
                if(clients.get(i).getId()>max){
                    max = clients.get(i).getId();
                }
            }
            String query = "Insert into client values(?,?);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,max);
            st.setString(2,name);
            st.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
     return max;
    }
    String getById(long id){
        if(!exist(id)){
            throw new IllegalArgumentException("Client with such id doesnt exist");
        }
        Database db = Database.getInstance();
        Connection conn = db.getConnection();
        String name= null;
        try{
            String query = "Select name from client where id = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet resultSet= st.executeQuery();
            resultSet.next();
            name = resultSet.getString(1);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return name;
    }
    void setName(long id, String name){
        if(name.length()<2||name.length()>1000 || !name.matches("^[a-zA-Z]*$")){

            throw  new IllegalArgumentException("Incorrect value for name");
        }
        Database db = Database.getInstance();
        Connection conn = db.getConnection();

        try{
            String query = "UPDATE client SET name = ? WHERE id=?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(2,id);
            st.setString(1,name);
           st.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    private boolean exist(long id){
        List<Client> clients =listAll();
        boolean exists = false;
        for(Client cl : clients){
            if(cl.getId()==id){
                exists=true;
                break;
            }
        }
        return exists;
    }
    void deleteById(long id){
        if(!exist(id)){
            throw new IllegalArgumentException("Client with such id doesnt exist");
        }
        Database db = Database.getInstance();
        Connection conn = db.getConnection();
        try{
            String query = "DELETE from client where id = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    List<Client> listAll(){
        List<Client> all = new ArrayList<>();
        Database db = Database.getInstance();
        Connection conn = db.getConnection();
        try{
            String query = "Select * from client";
            Statement st = conn.createStatement();

            ResultSet resultSet = st.executeQuery(query);
            
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                all.add(new Client(id, name));
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return all;
    }
}
