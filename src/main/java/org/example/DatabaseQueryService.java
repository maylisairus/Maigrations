package org.example;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseQueryService {
    public static void main(String[] args) {

        List<MaxProjectCountClient> list1 = findMaxProjectsClient();
       for(MaxProjectCountClient item : list1){
           System.out.println(item);
       }

        System.out.println("\n");
        List<LongestProject> list2 = findLongestProject();
        for(LongestProject item : list2){
            System.out.println(item);
        }

        System.out.println("\n");
        List<MaxSalaryWorker> list3 = findMaxSalaryWorker();
        for(MaxSalaryWorker item : list3){
            System.out.println(item);
        }


        System.out.println("\n");
        List<YoungestOldestWorker> list4 = findYoungestOldestWorker();
        for(YoungestOldestWorker item : list4){
            System.out.println(item);
        }

        System.out.println("\n");
        List<ProjectPrice> list5 = findProjectPrice();
        for(ProjectPrice item : list5){
            System.out.println(item);
        }
    }

    public static List<MaxProjectCountClient> findMaxProjectsClient(){
        List<MaxProjectCountClient> list = new ArrayList<>();
        Database db = Database.getInstance();
        Connection conn = db.getConnection();

        try {
            Scanner sc = new Scanner(new File("src\\main\\resources\\sql\\find_max_projects_client.sql"));
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(sc.nextLine());
            sc.close();
           while(resultSet.next()){
               list.add(new MaxProjectCountClient(resultSet.getString(1), resultSet.getInt(2)));
           }
           st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<LongestProject> findLongestProject(){
        List<LongestProject> list = new ArrayList<>();
        Database db = Database.getInstance();
        Connection conn = db.getConnection();

        try {
            Scanner sc = new Scanner(new File("src\\main\\resources\\sql\\find_longest_project.sql"));
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(sc.nextLine());
            sc.close();
            while(resultSet.next()){
                list.add(new LongestProject(resultSet.getInt(1), resultSet.getInt(2)));
            }
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<MaxSalaryWorker> findMaxSalaryWorker(){
        List<MaxSalaryWorker> list = new ArrayList<>();
        Database db = Database.getInstance();
        Connection conn = db.getConnection();

        try {
            Scanner sc = new Scanner(new File("src\\main\\resources\\sql\\find_max_salary_worker.sql"));
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(sc.nextLine());
            sc.close();
            while(resultSet.next()){
                list.add(new MaxSalaryWorker(resultSet.getString(1), resultSet.getInt(2)));
            }
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<YoungestOldestWorker> findYoungestOldestWorker(){
        List<YoungestOldestWorker> list = new ArrayList<>();
        Database db = Database.getInstance();
        Connection conn = db.getConnection();

        try {
            Scanner sc = new Scanner(new File("src\\main\\resources\\sql\\find_youngest_eldest_workers.sql"));
            Statement st = conn.createStatement();
            String query ="";
            while(sc.hasNext()){
                query+=sc.nextLine();
            }
            ResultSet resultSet = st.executeQuery(query);

            sc.close();
            while(resultSet.next()){
                list.add(new YoungestOldestWorker(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3)));
            }
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<ProjectPrice> findProjectPrice(){
        List<ProjectPrice> list = new ArrayList<>();
        Database db = Database.getInstance();
        Connection conn = db.getConnection();

        try {
            Scanner sc = new Scanner(new File("src\\main\\resources\\sql\\print_project_prices.sql"));
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(sc.nextLine());
            sc.close();
            while(resultSet.next()){
                list.add(new ProjectPrice(resultSet.getInt(1), resultSet.getInt(2)));
            }
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
