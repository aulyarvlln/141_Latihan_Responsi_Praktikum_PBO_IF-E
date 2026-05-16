/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.latres.controller;

/**
 *
 * @author AULIA
 */

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.model.TodoRepositoryMySQL;
import com.pbo.latres.model.TodoTask;
import com.pbo.latres.view.TodoView;

public class TodoController {
    private TodoRepository repository;
    private TodoView view;
    
    public TodoController() {
        this.repository = new TodoRepositoryMySQL();
        this.view = new TodoView();
        
        Runnable refreshTable = () -> {
            view.showTodos(repository.getAll());
        };
        
        view.onAdd(e -> {
            String title = view.getTitleInput();
            String status = view.getStatusInput();

            if (title.isEmpty()) {
                view.showMessage("Task tidak boleh kosong");
                return;
            }
            
            repository.insert(new InsertTodoDTO(title,status));

            refreshTable.run();

            view.clearForm();
        });
        
        view.onUpdate(e -> {
            int selectedId = view.getSelectedTodoId();

            if (selectedId == -1) {
                view.showMessage("Pilih data terlebih dahulu");
                return;
            }

            TodoTask task = new TodoTask(selectedId, view.getTitleInput(), view.getStatusInput());

            repository.update(task);

            refreshTable.run();

            view.clearForm();
        });
        
        view.onDelete(e -> {
            int selectedId = view.getSelectedTodoId();

            if (selectedId == -1) {
                view.showMessage("Pilih data terlebih dahulu");
                return;
            }

            repository.deleteById(selectedId);

            refreshTable.run();

            view.clearForm();
        });
        
        view.onClear(e -> {
            view.clearForm();
        });

        view.onTableSelect(e -> {
            int selectedId = view.getSelectedTodoId();

            if (selectedId == -1) {
                return;
            }

            TodoTask task = repository.getById(selectedId);

            if (task != null) {
                view.setForm(task);
            }
        });
        
        refreshTable.run();
    }
}
