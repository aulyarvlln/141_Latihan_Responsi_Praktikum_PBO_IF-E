/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.latres.model;

/**
 *
 * @author AULIA
 */

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoRepositoryMySQL 
        implements TodoRepository {
    private final DatabaseConnection conn;
    
    public TodoRepositoryMySQL() {
        this.conn = DatabaseConnection.getInstance();
    }
    
    @Override
    public List<TodoTask> getAll() {
        List<TodoTask> tasks = new ArrayList<>();
        
        try {
            String sql = "SELECT id, title, status FROM todos ORDER BY id";
            
            Connection connection = this.conn.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {                
                TodoTask task = new TodoTask(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("status"));
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
    
    @Override
    public TodoTask getById(int id) {
        try {
            String sql = "SELECT id, title, status FROM todos WHERE id =?";
            
            Connection connection = this.conn.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                TodoTask task = new TodoTask(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("status"));
                return task;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean insert(InsertTodoDTO insertTodoDTO) {
        try {
            String sql = "INSERT INTO todos (title, status) VALUES (?, ?)";
            
            Connection connection = this.conn.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, insertTodoDTO.getTitle());
            stmt.setString(2, insertTodoDTO.getStatus());
            
            int affectedRow = stmt.executeUpdate();
            
            if (affectedRow == 0) {
                System.out.println("Data Gagal ditambahkan");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Boolean update(TodoTask todoTask) {
        try {
            String sql = "UPDATE todos SET title = ?, status = ? WHERE id = ?";
            
            Connection connection = this.conn.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, todoTask.getTitle());
            stmt.setString(2, todoTask.getStatus());
            stmt.setInt(3, todoTask.getId());
            
            int affectedRow = stmt.executeUpdate();
            
            if (affectedRow == 0) {
                System.out.println("Data Gagal ditambahkan");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Boolean deleteById(int id) {
        try {
            String sql = "DELETE FROM todos WHERE id = ?";
            
            Connection connection = this.conn.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            int affectedRow = stmt.executeUpdate();
            
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
