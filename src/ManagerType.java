/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author simo
 */
public enum ManagerType {
   
    HEAD_MANAGER(1, "Head Manager"),
    ASSISTANT_MANAGER(2, "Assistant Manager"),
    TEAM_LEAD(3, "Team Lead");

    private final int number;
    private final String title;

   
    ManagerType(int number, String title) {
        this.number = number;
        this.title = title;
}

public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }