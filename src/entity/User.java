package entity;

/**
 * Created by Windows10.0 on 2020/9/7.
 */
public class User {
    private String id = null;
    private String name = null;
    private String password = null;
    private String school = null;
    private int status = -1;
    private double reputation = -1;
    private String preference = null;
    private String imageURL = null;

    public User(String id, String name, String password, String school, int status, double reputation, String preference, String imageURL) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.school = school;
        this.status = status;
        this.reputation = reputation;
        this.preference = preference;
        this.imageURL = imageURL;
    }

    public User() {
    }
    
    public User(String id, String name, String password, String school) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.school = school;
    }
    
    public User(String id, String password){
    	this.id = id;
    	this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
