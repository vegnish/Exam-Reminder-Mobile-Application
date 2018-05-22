package mobileapptaylors.example.vegnish.MAD_assignment1;

public class AnnouncementModel {
    private String announcementType;
    private String announcementDescription;
    private int quantity;

    public AnnouncementModel(String announcementType, String announcementDescription, int quantity) {
        this.announcementType = announcementType;
        this.announcementDescription = announcementDescription;
        this.quantity = quantity;
    }

    public String getAnnouncementType() {
        return announcementType;
    }

    public void setAnnouncementType(String announcementType) {
        this.announcementType = announcementType;
    }

    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public void setAnnouncementDescription(String announcementDescription) {
        this.announcementDescription = announcementDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
