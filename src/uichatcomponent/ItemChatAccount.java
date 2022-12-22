
package uichatcomponent;

/**
 *
 * @author HIEN
 */
public class ItemChatAccount {
    private int ID;
    private String name;
    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ItemChatAccount() {
    }
    

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Boolean isStatus() {
        return this.status;
    }
    

    public ItemChatAccount(int ID, String name, Boolean status) {
        this.ID = ID;
        this.name = name;
        this.status = status;
    }

    
    
    
    
}
