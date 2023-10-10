package br.com.rocketseat.learning.todolist.user;

public class UserModel {
    // Atributos
    private String userName;
    private String name;
    private String password;

    // Propriedades
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
}
