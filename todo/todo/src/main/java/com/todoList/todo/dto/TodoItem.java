package com.todoList.todo.dto;

import java.util.Date;

public class TodoItem {
    private Long id;
    private String subject;
    private String content;
    private Date createDate;
    private boolean fin;


    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean isFin() {
        return fin;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", fin=" + fin +
                '}';
    }
}
