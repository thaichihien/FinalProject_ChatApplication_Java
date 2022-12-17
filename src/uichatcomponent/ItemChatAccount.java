
package uichatcomponent;

/**
 *
 * @author HIEN
 */
public class ItemChatAccount {
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
    
    public ItemChatAccount(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }
    
    
    
    
    
}
