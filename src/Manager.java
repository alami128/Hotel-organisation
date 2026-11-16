/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
public abstract class Manager {
    
    private String title;

    public Manager(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Each manager type describes its own job
    public abstract String getResponsibility();

    // Builds the right manager type from the enum
    public static Manager createManager(ManagerType type) {
        if (type == ManagerType.HEAD_MANAGER) {
            return new HeadManager();
        } else if (type == ManagerType.ASSISTANT_MANAGER) {
            return new AssistantManager();
        } else if (type == ManagerType.TEAM_LEAD) {
            return new TeamLead();
        }
        return null;
    }
}

class HeadManager extends Manager {
    public HeadManager() {
        super("Head Manager");
    }

    public String getResponsibility() {
        return "Runs the whole hotel";
    }
}



